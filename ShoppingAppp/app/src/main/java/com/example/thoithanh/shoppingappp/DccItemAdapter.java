package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thoithanh on 5/15/18.
 */

public class DccItemAdapter extends RecyclerView.Adapter<DccItemAdapter.ViewHolder> {
    int userId;
    ArrayList<DccItem> dccItems;
    Context context;
    TextView dcc_tvTotalPrice;
    int totalprice;

    public DccItemAdapter(int userId, ArrayList<DccItem> dccItems, Context context, TextView dcc_tvTotalPrice, int totalprice) {
        this.userId = userId;
        this.dccItems = dccItems;
        this.context = context;
        this.dcc_tvTotalPrice = dcc_tvTotalPrice;
        this.totalprice = totalprice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View dccItemView = layoutInflater.inflate(R.layout.item_in_cart,parent,false);
        return new ViewHolder(dccItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //holder.dcc_iItem.setBackground();
		DccItem item = dccItems.get(position);
        holder.dcc_tvItemName.setText(dccItems.get(position).getName());
        holder.dcc_tvItemPrice.setText(dccItems.get(position).getPrice());
        holder.dcc_etQuantity.setText(dccItems.get(position).getQuantity().toString());
		Uri uri = Uri.parse(item.getImgURL());
		Glide.with(holder.itemView).load(uri).into(holder.dcc_iItem);

        holder.dcc_bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                String URL = "http://149.28.26.145:8080/api/cart/" + userId+"/"+dccItems.get(position).getId();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context,"Delete successed!",Toast.LENGTH_LONG).show();
                        totalprice = Integer.valueOf(dcc_tvTotalPrice.getText().toString().substring(1)) -  dccItems.get(position).getQuantity()*Integer.valueOf(dccItems.get(position).getPrice().substring(1));
                        dcc_tvTotalPrice.setText("$"+totalprice);
                        dccItems.remove(dccItems.get(position));
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);
                notifyDataSetChanged();
            }
        });
        holder.dcc_bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                String URL = "http://149.28.26.145:8080/api/cart";

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userId",userId);
                    jsonObject.put("productId",dccItems.get(position).getId());
                    jsonObject.put("quantity",dccItems.get(position).getQuantity()+1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dccItems.get(position).setQuantity(dccItems.get(position).getQuantity()+1);
                        totalprice = Integer.valueOf(dcc_tvTotalPrice.getText().toString().substring(1)) +Integer.valueOf(dccItems.get(position).getPrice().substring(1));
                        dcc_tvTotalPrice.setText("$"+totalprice);
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);
                notifyDataSetChanged();
            }
        });
        holder.dcc_bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dccItems.get(position).getQuantity() > 0){
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                String URL = "http://149.28.26.145:8080/api/cart";

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userId",userId);
                    jsonObject.put("productId",dccItems.get(position).getId());
                    jsonObject.put("quantity",dccItems.get(position).getQuantity()-1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dccItems.get(position).setQuantity(dccItems.get(position).getQuantity()-1);
                        totalprice = Integer.valueOf(dcc_tvTotalPrice.getText().toString().substring(1)) - Integer.valueOf(dccItems.get(position).getPrice().substring(1));
                        dcc_tvTotalPrice.setText("$"+totalprice);
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);
                }
                else{
                    Toast.makeText(context,"Cannot sub anymore",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dccItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView dcc_iItem;
        TextView dcc_tvItemName;
        TextView dcc_tvItemPrice;
        EditText dcc_etQuantity;
        ImageButton dcc_bDelete;
        ImageButton dcc_bPlus;
        ImageButton dcc_bMinus;
        public ViewHolder(View itemView) {
            super(itemView);
            dcc_iItem = (ImageView) itemView.findViewById(R.id.dcc_iItem);
            dcc_tvItemName = (TextView) itemView.findViewById(R.id.dcc_tvItemName);
            dcc_tvItemPrice = (TextView) itemView.findViewById(R.id.dcc_tvItemPrice);
            dcc_etQuantity = (EditText) itemView.findViewById(R.id.dcc_etQuantity);
            dcc_bDelete = (ImageButton) itemView.findViewById(R.id.dcc_bDelete);
            dcc_bPlus = (ImageButton) itemView.findViewById(R.id.dcc_bPlus);
            dcc_bMinus = (ImageButton) itemView.findViewById(R.id.dcc_bMinus);
        }
    }
}
