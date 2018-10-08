package com.nitto.tushar.nrrii.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.OrderService;


import java.util.ArrayList;

public class RecyclerViewAdapterOrder extends RecyclerView.Adapter<RecyclerViewAdapterOrder.ViewHolder> {


    private static final String TAG = "MyRecyclerView";

    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private OrderItem orderItem1 = new OrderItem();
    //private OrderItem orderItem2 = new OrderItem();
    private Context mContext;
    private LinearLayout profileItemLayout;

    public RecyclerViewAdapterOrder(Context context, ArrayList<OrderItem> userProfileModels) {
        this.orderItems = userProfileModels;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_order_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        orderItem1 = orderItems.get(position);

        holder.itemQuantity.setText(String.valueOf(orderItem1.getQuantity()));

        holder.orderDate.setText(orderItem1.getOrderDate());

        String tmp_status;

        if(orderItem1.isOrderComplete()){
            tmp_status = "Delivered";
        }
        else {
            tmp_status = "Pending";
        }

        holder.deliverStatus.setText(tmp_status);

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView deliverStatus, itemQuantity, orderDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.deliverStatus = itemView.findViewById(R.id.deliverStatus);
            this.itemQuantity = itemView.findViewById(R.id.itemQuantity);
            this.orderDate = itemView.findViewById(R.id.orderDate);
        }
    }
}
