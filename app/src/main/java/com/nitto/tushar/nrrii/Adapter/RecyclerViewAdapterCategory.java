package com.nitto.tushar.nrrii.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nitto.tushar.nrrii.Activity.DressViewActivity;
import com.nitto.tushar.nrrii.Activity.ProductCategoryActivity;
import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class RecyclerViewAdapterCategory extends RecyclerView.Adapter<RecyclerViewAdapterCategory.ViewHolder> {


    private static final String TAG = "MyRecyclerView";
    private int[] images;
    private String[] categoryNames;
    private Context mContext;

    public RecyclerViewAdapterCategory(Context context,int[] images, String[] categoryNames) {
        this.mContext = context;
        this.images = images;
        this.categoryNames = categoryNames;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.ivProductCategory.setBackgroundResource(images[position]);
        holder.tvCategoryName.setText(categoryNames[position]);
        holder.layoutCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DressViewActivity.class) );
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView ivProductCategory;
        AppCompatTextView tvCategoryName;
        RelativeLayout layoutCat;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivProductCategory = itemView.findViewById(R.id.ivProductCategory);
            this.tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            this.layoutCat = itemView.findViewById(R.id.layoutCat);
        }
    }
}
