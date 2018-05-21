package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * Created by thoithanh on 5/12/18.
 */

public class DsItemAdapter extends RecyclerView.Adapter<DsItemAdapter.ViewHolder> {
    int userId;
    ArrayList<DsItem> dsItems;
    Context context;
    TextView tvNumberItemInCart;

    public DsItemAdapter(int userId, ArrayList<DsItem> dsItems, Context context, TextView tvNumberItemInCart) {
        this.userId = userId;
        this.dsItems = dsItems;
        this.context = context;
        this.tvNumberItemInCart = tvNumberItemInCart;
    }

    public void postAdd2Cart(JSONObject jsonObject, final Context ct){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        String URL = "http://149.28.26.145:8080/api/cart";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setTvNumberItemInCart(tvNumberItemInCart,userId,ct);
                Toast.makeText(context, "Added to your cart",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

            }
        });


        requestQueue.add(jsonObjectRequest);
    }
    public void setTvNumberItemInCart(final TextView tvNumberItemInCart, int userId,Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String URL = "http://149.28.26.145:8080/api/cart/" + userId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    tvNumberItemInCart.setText(String.valueOf(data.length()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.search_item,parent,false);

        return new DsItemAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //holder.ds_iItem.setBackground();
        holder.ds_tvName.setText(dsItems.get(position).getName());
        holder.ds_tvDescription.setText(dsItems.get(position).getDescription());
        holder.ds_tvPrice.setText(dsItems.get(position).getPrice());
        holder.ds_tvOriginalPrice.setText(dsItems.get(position).getOriginalPrice());

        //
        holder.ds_iItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = "aa";
                Intent intent = new Intent(holder.ds_tvName.getContext(),DidClickItem.class);
                intent.putExtra("userId", userId);
                intent.putExtra("itemId",dsItems.get(position).getItemId());
                holder.ds_tvName.getContext().startActivity(intent);
                setTvNumberItemInCart(tvNumberItemInCart,userId,context);
            }
        });
        //
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("productId",dsItems.get(position).getItemId());
            jsonObject.put("quantity",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //
        holder.ds_bAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAdd2Cart(jsonObject,holder.ds_tvName.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ds_iItem;
        TextView ds_tvName;
        TextView ds_tvDescription;
        TextView ds_tvPrice;
        TextView ds_tvOriginalPrice;
        ImageButton ds_bAddToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ds_tvName = (TextView) itemView.findViewById(R.id.ds_tvName);
            ds_tvDescription =(TextView) itemView.findViewById(R.id.ds_tvDescription);
            ds_tvPrice = (TextView) itemView.findViewById(R.id.ds_tvPrice);
            ds_tvOriginalPrice = (TextView) itemView.findViewById(R.id.ds_tvOriginalPrice);
            ds_bAddToCart = (ImageButton) itemView.findViewById(R.id.ds_bAddToCart);
            ds_iItem = (ImageView) itemView.findViewById(R.id.ds_iItem);
        }
    }
}
