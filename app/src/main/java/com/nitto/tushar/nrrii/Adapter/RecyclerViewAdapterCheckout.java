package com.nitto.tushar.nrrii.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class RecyclerViewAdapterCheckout extends RecyclerView.Adapter<RecyclerViewAdapterCheckout.ViewHolder> {


    private static final String TAG = "MyRecyclerView";

    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterCheckout(Context context, ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_checkout_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        CartItem cartItem = cartItems.get(position);

        holder.productPrice.setText(String.valueOf(cartItem.getProductPrice()));

        holder.productQuantity.setText(String.valueOf(cartItem.getProductQuantity()));

        holder.productSize.setText(String.valueOf(cartItem.getProductSize()));

        holder.productColor.setText(String.valueOf(cartItem.getProductColor()));

        if(position == cartItems.size()-1){
            holder.checkoutDevider.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView productPrice, productQuantity, productSize, productColor;
        LinearLayout checkoutDevider;


        public ViewHolder(View itemView) {
            super(itemView);
            this.productPrice = itemView.findViewById(R.id.productPrice);
            this.productQuantity = itemView.findViewById(R.id.productQuantity);
            this.productSize = itemView.findViewById(R.id.productSize);
            this.productColor = itemView.findViewById(R.id.productColor);
            this.checkoutDevider = itemView.findViewById(R.id.checkoutDevider);
        }
    }
}
