package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoithanh on 5/9/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    int userId;
    ArrayList<Category> cats;
    Context context;


    public CategoryAdapter(int userId, ArrayList<Category> cats, Context context) {
        this.userId = userId;
        this.cats = cats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View catView = layoutInflater.inflate(R.layout.category,parent,false);
        return new ViewHolder(catView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvCat.setText(cats.get(position).getName());
        holder.tvComponents.setText(cats.get(position).getComponents());
        holder.tvResultNumber.setText(cats.get(position).getResultNumber());
        if (cats.get(position).getId()==1) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_books_title);
            Log.d("background", "onBindViewHolder: ");
        }
        else if (cats.get(position).getId()==2) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_electronics);
        }
        else if (cats.get(position).getId()==3) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_clothing);
        }
        else if (cats.get(position).getId()==4) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_comestic);
        }
        else if (cats.get(position).getId()==5) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_handmade);
        }
        else if (cats.get(position).getId()==6) {
            holder.ivBackground.setImageResource(R.drawable.dlg_cat_household);
        }
        holder.ivBackground.setOnClickListener(new View.OnClickListener() {
            //When click specific category, code here:
            @Override
            public void onClick(View v) {
                Log.d("MESSAGE: ",cats.get(position).getName());

                //String name = "aa";
                Intent intent = new Intent(holder.ivBackground.getContext(),DidSearch.class);
                intent.putExtra("userId", userId);
                intent.putExtra("title",cats.get(position).getName());
                intent.putExtra("category",cats.get(position).getId());
                holder.ivBackground.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivBackground;
        TextView tvCat;
        TextView tvComponents;
        TextView tvResultNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
            tvCat = (TextView) itemView.findViewById(R.id.tvCat);
            tvComponents = (TextView) itemView.findViewById(R.id.tvComponents);
            tvResultNumber = (TextView) itemView.findViewById(R.id.tvResultNumber);
        }
    }
}
