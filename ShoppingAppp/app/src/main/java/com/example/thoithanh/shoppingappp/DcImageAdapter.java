package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by thoithanh on 5/13/18.
 */

public class DcImageAdapter extends RecyclerView.Adapter<DcImageAdapter.ViewHolder> {
    ArrayList<DcImage> dcImages;
    Context context;

    public DcImageAdapter(ArrayList<DcImage> dcImages, Context context) {
        this.dcImages = dcImages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View imgView = layoutInflater.inflate(R.layout.dc_image,parent,false);
        return new ViewHolder(imgView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.dc_ivImage.setBackground();
		DcImage item = dcImages.get(position);
		Log.e("DcImageAdapter", item.getImgURL());
		Uri uri = Uri.parse(item.getImgURL());
		Glide.with(holder.itemView).load(uri).into(holder.dc_ivImage);
    }

    @Override
    public int getItemCount() {
        return dcImages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView dc_ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            dc_ivImage = (ImageView) itemView.findViewById(R.id.dc_ivImage);
        }
    }
}
