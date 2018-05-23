package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thoithanh on 5/11/18.
 */

public class DlgItemAdapter extends RecyclerView.Adapter<DlgItemAdapter.ViewHolder> {
    int userId;
    ArrayList<DlgItem> dlgItems;
    Context context;


    public DlgItemAdapter(int userId,ArrayList<DlgItem> dlgItems,  Context context) {
        this.dlgItems = dlgItems;
        this.userId = userId;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.dlg_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //holder.iPopItem.setBackground();
		DlgItem item = getItemPosition(position);
        holder.tvItemName.setText(item.getName());
        holder.tvItemOriginalPrice.setText(item.getOriginalPrice());
        holder.tvItemPrice.setText(item.getPrice());
		Uri uri = Uri.parse(item.getImgURL());
		Glide.with(holder.itemView).load(uri).into(holder.iPopItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = "aa";
                Intent intent = new Intent(holder.tvItemName.getContext(),DidClickItem.class);
                intent.putExtra("itemId", dlgItems.get(position).getId());
                intent.putExtra("userId", userId);
                holder.tvItemName.getContext().startActivity(intent);

            }
        });
    }
    public DlgItem getItemPosition(int position){
		return dlgItems.get(position);
	}

    @Override
    public int getItemCount() {
        return dlgItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iItemCage;
        ImageView iPopItem;
        TextView tvItemName;
        TextView tvItemPrice;
        TextView tvItemOriginalPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            iItemCage = (ImageView) itemView.findViewById(R.id.iItemCage);
            iPopItem = (ImageView) itemView.findViewById(R.id.iPopItem);
            tvItemName = (TextView) itemView.findViewById(R.id.dcc_tvItemName);
            tvItemPrice = (TextView) itemView.findViewById(R.id.dcc_tvItemPrice);
            tvItemOriginalPrice = (TextView) itemView.findViewById(R.id.tvItemOriginalPrice);

        }
    }
}
