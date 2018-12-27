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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.OrderService;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

        holder.product_title.setText(orderItem1.getOrderTitle());

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

        Glide
                .with(mContext)
                .load(orderItem1.getImageLink())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView deliverStatus, itemQuantity, orderDate, product_title;
        CircleImageView profile_image;

        public ViewHolder(View itemView) {
            super(itemView);
            this.deliverStatus = itemView.findViewById(R.id.deliverStatus);
            this.product_title = itemView.findViewById(R.id.product_title);
            this.itemQuantity = itemView.findViewById(R.id.itemQuantity);
            this.orderDate = itemView.findViewById(R.id.orderDate);
            this.profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
