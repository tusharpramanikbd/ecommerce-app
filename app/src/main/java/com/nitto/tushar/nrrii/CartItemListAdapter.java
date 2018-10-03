package com.nitto.tushar.nrrii;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Services.CartService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masud on 2/12/2018.
 */

public class CartItemListAdapter extends ArrayAdapter<ProductItem> {

    public interface OnDeleteCartItemListener{
        void onDeleteCartItem(int productUID);
    }

    public void SetOnDeleteCartItemListener(OnDeleteCartItemListener listener) {
        this.onDeleteCartItemListener = listener;
    }

    private OnDeleteCartItemListener onDeleteCartItemListener;
    private Context context;
    private List<ProductItem> productItems;
    private LayoutInflater inflater;
    private List<Integer> productQuantityList;

    public CartItemListAdapter(@NonNull Context context, @NonNull List<ProductItem> productItems) {
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
            convertView = this.inflater.inflate(R.layout.item_shopping_cart, null);
            holder = new ViewHolder();
            holder.ivProductImage = convertView.findViewById(R.id.ivProductImage);
            holder.tvProductName = convertView.findViewById(R.id.tvProductName);
            holder.tvProductColor = convertView.findViewById(R.id.tvProductColor);
            holder.tvProductSize = convertView.findViewById(R.id.tvProductSize);
            //holder.spQuantity = convertView.findViewById(R.id.spQuantity);
            holder.etQuantity = convertView.findViewById(R.id.etQuantity);
            holder.ivEditCartListedItem = convertView.findViewById(R.id.ivEditCartListedItem);
            holder.tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
            holder.ivDeleteCartItem = convertView.findViewById(R.id.ivDeleteCartListedItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivDeleteCartItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                onDeleteCartItemListener.onDeleteCartItem(productItem.getUid());
            }
        });

        holder.tvProductName.setText(productItem.getName());

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_dropdown_item, this.productQuantityList);

        holder.etQuantity.setText(String.valueOf(productItem.getQuantity()));

        holder.ivEditCartListedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);

                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

                View mView = inflater.inflate(R.layout.dialog_edit_quantity, null);

                final AppCompatEditText etEditQuantity = mView.findViewById(R.id.etEditQuantity);
                final AppCompatButton btnEditQuantitySubmit = mView.findViewById(R.id.btnEditQuantitySubmit);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                btnEditQuantitySubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tmpQuantity = etEditQuantity.getText().toString();

                        if(tmpQuantity.isEmpty()){

                        }
                        else{
                            int tmpQuantityInInteger = Integer.parseInt(etEditQuantity.getText().toString());

                            if(tmpQuantityInInteger<1){

                            }else {

                                holder.etQuantity.setText(tmpQuantity);
                                dialog.hide();

                                int quantityInNumber = Integer.parseInt(etEditQuantity.getText().toString());

                                CartService.getInstance().changeTotalPrice(holder.tvProductName.getText().toString(), quantityInNumber);
                            }
                        }
                    }
                });
                dialog.show();
            }
        });


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
        //AppCompatSpinner spQuantity;
        AppCompatEditText etQuantity;
        AppCompatImageView ivEditCartListedItem;
        AppCompatTextView tvProductPrice;
        AppCompatImageView ivDeleteCartItem;
    }
}