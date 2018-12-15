package com.nitto.tushar.nrrii.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Activity.ProductDetailsActivity;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.ProductService;

import java.util.ArrayList;

public class RecyclerViewAdapterDress extends RecyclerView.Adapter<RecyclerViewAdapterDress.MyViewHolder> {

    private Context mContext;
    private ArrayList<Dress> dressArrayList;


    public RecyclerViewAdapterDress(Context mContext, ArrayList<Dress> dressArrayList) {
        this.mContext = mContext;
        this.dressArrayList = dressArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_dress, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        //holder.dressCover.setImageResource(dressArrayList.get(position).getImages().get(0).getSrc());

        Glide
                .with(mContext)
                .load(dressArrayList.get(position).getImages().get(0).getSrc())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(holder.dressCover);




        holder.tvActualPrice.setText(dressArrayList.get(position).getActualPrice());
        //holder.tvActualPrice.setPaintFlags(holder.tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        //holder.tvActualPriceBDT.setPaintFlags(holder.tvActualPriceBDT.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        //holder.tvPromotionalPrice.setText(dressArrayList.get(position).getPromotionalPrice());

        holder.dressCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductService.getInstance().setDressForDetails(dressArrayList.get(position));
                mContext.startActivity(new Intent(mContext, ProductDetailsActivity.class) );
            }
        });
    }

    @Override
    public int getItemCount() {
        return dressArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tvActualPrice, tvPromotionalPrice, tvActualPriceBDT;
        private CardView dressCardView;
        AppCompatImageView dressCover;

        public MyViewHolder(View itemView) {
            super(itemView);

            dressCardView = itemView.findViewById(R.id.dressCardView);
            tvActualPrice = itemView.findViewById(R.id.tvActualPrice);
            tvPromotionalPrice = itemView.findViewById(R.id.tvPromotionalPrice);
            tvActualPriceBDT = itemView.findViewById(R.id.tvActualPriceBDT);
            dressCover = itemView.findViewById(R.id.dressCover);

        }
    }
}
