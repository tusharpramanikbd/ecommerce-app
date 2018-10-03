package com.nitto.tushar.nrrii;

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
import android.widget.TextView;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Services.OrderService;


import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "MyRecyclerView";

    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private OrderItem orderItem1 = new OrderItem();
    //private OrderItem orderItem2 = new OrderItem();
    private Context mContext;
    private LinearLayout profileItemLayout;

    public MyRecyclerViewAdapter(Context context, ArrayList<OrderItem> userProfileModels) {
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

        holder.tv_order_id.setText("Order Id: "+String.valueOf(orderItem1.getOid()));

        String tmp_status;

        if(orderItem1.isOrderComplete()){
            tmp_status = "Complete";
        }
        else {
            tmp_status = "Pending";
        }

        holder.tv_order_status.setText("Order Status: "+tmp_status);

        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderItem1 = orderItems.get(position);
                Toast.makeText(mContext, "Details Clicked", Toast.LENGTH_LONG).show();
                OrderService.getInstance().insertTmpOrderForDetails(orderItem1);
                mContext.startActivity(new Intent(mContext, OrderDetailsActivity.class) );
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tv_order_id, tv_order_status;
        AppCompatButton btn_details;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_order_id = itemView.findViewById(R.id.tv_order_id);
            this.tv_order_status = itemView.findViewById(R.id.tv_order_status);
            this.btn_details = itemView.findViewById(R.id.btn_details);
        }
    }
}
