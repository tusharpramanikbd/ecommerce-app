package com.nitto.tushar.nrrii;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.ProductService;

import java.util.List;

/**
 * Created by masud on 2/5/2018.
 */

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {
    Context context, mContext;
    List<ProductItem> productItems;

    public ProductItemAdapter(Context context, List<ProductItem> productItems) {
        this.context = context;
        this.mContext = context;
        this.productItems = productItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProductItem productItem = this.productItems.get(position);
        holder.gridProductDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDetails(productItem);
            }
        });

        holder.ivMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.menu_product);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_product_add_to_cart:
                                addProductToCartIfNotExists(productItem);
                                break;
//                            case R.id.menu_item_product_details:
//                                goToProductDetails(productItem.getUid());
//                                break;
                            case R.id.menu_item_product_share:
                                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        holder.tvProductName.setText(productItem.getName());
        holder.tvProductPrice.setText(String.valueOf(productItem.getPrice()));
        holder.rbRatingBar.setRating(productItem.getRating());

        //loading the image received from server
        loadImageWithGlide(holder, productItem.getImage());

    }

    private void addProductToCartIfNotExists(ProductItem productItem) {
//        if(!CartService.getInstance().isAvailableInCart(productItem)) {
//            productItem.setQuantity(1);
//            CartService.getInstance().addToCart(productItem);
//            Toast.makeText(context, "\"" + productItem.getName() + "\" added to cart", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Already added to cart", Toast.LENGTH_SHORT).show();
//        }
    }

    private void loadImageWithGlide(ViewHolder holder, String img){

        String imgUrl = "http://myfordo.com/prod_images/" + img;

        Glide
                .with(context)
                .load(imgUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(holder.ivProductImage);
    }

    private void goToProductDetails(ProductItem productItem) {
        Intent intent = new Intent(context, ProductDetail.class);
        //intent.putExtra("product_id", itemId);
        ProductService.getInstance().insertProductItem(productItem);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return this.productItems.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public AppCompatTextView tvProductName;
        public AppCompatTextView tvProductPrice;
        public AppCompatImageView ivProductImage, ivMenuButton;
        public AppCompatRatingBar rbRatingBar;
        public LinearLayout gridProductDetails;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            ivMenuButton = itemView.findViewById(R.id.ivMenuButton);
            rbRatingBar = itemView.findViewById(R.id.rbRatingBar);
            gridProductDetails = itemView.findViewById(R.id.gridProductDetails);
        }
    }
}