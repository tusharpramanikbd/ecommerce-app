package com.nitto.tushar.nrrii;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Services.CartService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masud on 2/12/2018.
 */

public class CartItemListSummaryAdapter extends ArrayAdapter<ProductItem> {

    private Context context;
    private List<ProductItem> productItems;
    private LayoutInflater inflater;
    private List<Integer> productQuantityList;

    public CartItemListSummaryAdapter(@NonNull Context context, @NonNull List<ProductItem> productItems) {
        super(context, 0, productItems);
        this.context = context;
        this.productItems = productItems;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.getProductSizes();
    }

    private void getProductSizes() {
        this.productQuantityList = new ArrayList<>();
        for(int j=1;j<=10;j++) {
            this.productQuantityList .add(j);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ProductItem productItem = getItem(position);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.item_shopping_cart_summary, null);
            holder = new ViewHolder();
            holder.ivProductImage = convertView.findViewById(R.id.ivProductImage);
            holder.tvProductName = convertView.findViewById(R.id.tvProductName);
            holder.tvProductColor = convertView.findViewById(R.id.tvProductColor);
            holder.tvProductSize = convertView.findViewById(R.id.tvProductSize);
            holder.etQuantity = convertView.findViewById(R.id.etQuantity);
            holder.tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvProductName.setText(productItem.getName());

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_dropdown_item, this.productQuantityList);

        holder.etQuantity.setText(String.valueOf(productItem.getQuantity()));

        String imgUrl = "http://myfordo.com/prod_images/" + productItem.getImage();

        Glide
                .with(context)
                .load(imgUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(holder.ivProductImage);

        holder.tvProductPrice.setText(String.valueOf(productItem.getPrice()));
        return convertView;
    }

    static class ViewHolder{
        AppCompatImageView ivProductImage;
        AppCompatTextView tvProductName;
        AppCompatTextView tvProductColor;
        AppCompatTextView tvProductSize;
        AppCompatEditText etQuantity;
        AppCompatTextView tvProductPrice;
    }
}