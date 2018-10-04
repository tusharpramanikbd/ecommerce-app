package com.nitto.tushar.nrrii.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Dress> dressArrayList;

    public RecyclerViewAdapter(Context mContext, ArrayList<Dress> dressArrayList) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvActualPrice.setText(dressArrayList.get(position).getActualPrice());
        holder.tvActualPrice.setPaintFlags(holder.tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tvActualPriceBDT.setPaintFlags(holder.tvActualPriceBDT.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tvPromotionalPrice.setText(dressArrayList.get(position).getPromotionalPrice());

    }

    @Override
    public int getItemCount() {
        return dressArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tvActualPrice, tvPromotionalPrice, tvActualPriceBDT;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvActualPrice = itemView.findViewById(R.id.tvActualPrice);
            tvPromotionalPrice = itemView.findViewById(R.id.tvPromotionalPrice);
            tvActualPriceBDT = itemView.findViewById(R.id.tvActualPriceBDT);

        }
    }
}
