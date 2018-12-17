package com.nitto.tushar.nrrii.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_checkout_item_new, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final CartItem cartItem = cartItems.get(position);

        holder.productPrice.setText(String.valueOf(cartItem.getProductPrice()));

        holder.productTitle.setText(cartItem.getProductTitle());

        holder.productQuantity.setText(String.valueOf(cartItem.getProductQuantity()));

        holder.editQuantity.setText("0"+String.valueOf(cartItem.getProductQuantity()));

        holder.productSize.setText(String.valueOf(cartItem.getProductSize()));

        holder.productColor.setText(String.valueOf(cartItem.getProductColor()));

        //holder.product_image.setImageResource(cartItem.getProductPhoto());

        Glide
                .with(mContext)
                .load(cartItem.getProductPhoto())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(holder.product_image);

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onDeleteCartItemListener.onDeleteCartItem(cartItem.getProductId());
                CartService.getInstance().deleteCartItem(cartItem.getCartId());
            }
        });

        holder.increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmpQuantity = Integer.parseInt(holder.editQuantity.getText().toString());
                if(tmpQuantity < 10){
                    tmpQuantity++;
                    holder.editQuantity.setText("0"+String.valueOf(tmpQuantity));
                    holder.productQuantity.setText(String.valueOf(tmpQuantity));
                    CartService.getInstance().increaseQuantity(cartItem.getProductId());
                    CartService.getInstance().changeTotalPrice(cartItem.getProductId(), tmpQuantity);
                }
            }
        });

        holder.decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmpQuantity = Integer.parseInt(holder.editQuantity.getText().toString());
                if(tmpQuantity > 1){
                    tmpQuantity--;
                    holder.editQuantity.setText("0"+String.valueOf(tmpQuantity));
                    holder.productQuantity.setText(String.valueOf(tmpQuantity));
                    CartService.getInstance().decreaseQuantity(cartItem.getProductId());
                    CartService.getInstance().changeTotalPrice(cartItem.getProductId(), tmpQuantity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView productPrice, productQuantity, productSize, productColor,editQuantity, productTitle;
        AppCompatImageView removeProduct, product_image;
        AppCompatButton increaseQuantity, decreaseQuantity;

        ViewHolder(View itemView) {
            super(itemView);
            this.productPrice = itemView.findViewById(R.id.productPrice);
            this.productQuantity = itemView.findViewById(R.id.productQuantity);
            this.productSize = itemView.findViewById(R.id.productSize);
            this.productColor = itemView.findViewById(R.id.productColor);
            this.removeProduct = itemView.findViewById(R.id.removeProduct);
            this.product_image = itemView.findViewById(R.id.product_image);
            this.increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            this.decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
            this.editQuantity = itemView.findViewById(R.id.editQuantity);
            this.productTitle = itemView.findViewById(R.id.productTitle);
        }
    }
}
