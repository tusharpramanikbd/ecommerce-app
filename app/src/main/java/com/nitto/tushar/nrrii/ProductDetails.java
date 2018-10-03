package com.nitto.tushar.nrrii;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Services.ProductService;
import com.nitto.tushar.nrrii.Utils.DatabaseInitializer;

import java.io.IOException;
import java.io.InputStream;

public class ProductDetails extends Activity implements View.OnClickListener {
    private View llAllReviews;
    private AppCompatImageView
            ivProductImage,
            ivBtnDescription,
            ivBtnReviews,
            ivBtnWarrenty;
    private AppCompatTextView
            tvProductName,
            tvShopName,
            tvProductPrice,
            tvDescription,
            tvFirstCustomer,
            tvSecondCustomer,
            tvThirdCustomer,
            tvFourthCustomer,
            tvFifthCustomer,
            tvWarrenty;
    private AppCompatRatingBar
            rbRatingBar,
            rbFirstRatingBar,
            rbSecondRatingBar,
            rbThirdRatingBar,
            rbFourthRatingBar,
            rbFifthRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initializeUI();
        populateProductItemView();
    }

    private void initializeUI() {
        this.llAllReviews = findViewById(R.id.llAllReviews);
        this.ivProductImage = findViewById(R.id.ivProductImage);
        this.ivBtnDescription = findViewById(R.id.ivBtnDescription);
        this.ivBtnReviews = findViewById(R.id.ivBtnReviews);
        this.ivBtnWarrenty = findViewById(R.id.ivBtnWarrenty);
        this.tvProductName = findViewById(R.id.tvProductName);
        this.tvShopName = findViewById(R.id.tvShopName);
        this.tvProductPrice = findViewById(R.id.tvProductPrice);
        this.tvDescription = findViewById(R.id.tvDescription);
        this.tvFirstCustomer = findViewById(R.id.tvFirstCustomer);
        this.tvSecondCustomer = findViewById(R.id.tvSecondCustomer);
        this.tvThirdCustomer = findViewById(R.id.tvThirdCustomer);
        this.tvFourthCustomer = findViewById(R.id.tvFourthCustomer);
        this.tvFifthCustomer = findViewById(R.id.tvFifthCustomer);
        this.tvWarrenty = findViewById(R.id.tvWarrenty);
        this.rbRatingBar = findViewById(R.id.rbRatingBar);
        this.rbFirstRatingBar = findViewById(R.id.rbFirstRatingBar);
        this.rbSecondRatingBar = findViewById(R.id.rbSecondRatingBar);
        this.rbThirdRatingBar = findViewById(R.id.rbThirdRatingBar);
        this.rbFourthRatingBar = findViewById(R.id.rbFourthRatingBar);
        this.rbFifthRatingBar = findViewById(R.id.rbFifthRatingBar);

        this.ivBtnDescription.setOnClickListener(this);
        this.ivBtnReviews.setOnClickListener(this);
        this.ivBtnWarrenty.setOnClickListener(this);
    }

    private void populateProductItemView() {
        //int productID = getIntent().getIntExtra("product_id", -1);
        //ProductItem productItem = DatabaseInitializer.getProductItemByID(AppDatabase.getInstance(), productID);
        ProductItem productItem = ProductService.getInstance().getInsertedProductItem();

        this.tvDescription.setText(productItem.getProductDescription());
        this.tvProductName.setText(productItem.getName());
        this.tvProductPrice.setText(String.valueOf(productItem.getPrice()));
        this.rbRatingBar.setRating(Float.valueOf(productItem.getRating()));

        String imgUrl = "http://myfordo.com/prod_images/" + productItem.getImage();

        Glide
                .with(this)
                .load(imgUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(this.ivProductImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBtnDescription:
                if (tvDescription.getVisibility() == View.VISIBLE) {
                    tvDescription.setVisibility(View.GONE);
                    ivBtnDescription.setImageResource(R.drawable.ic_arrow_down);
                }
                else {
                    tvDescription.setVisibility(View.VISIBLE);
                    ivBtnDescription.setImageResource(R.drawable.ic_arrow_up);
                }
                break;
            case R.id.ivBtnReviews:
                if (llAllReviews.getVisibility() == View.VISIBLE) {
                    llAllReviews.setVisibility(View.GONE);
                    ivBtnReviews.setImageResource(R.drawable.ic_arrow_down);
                }
                else {
                    llAllReviews.setVisibility(View.VISIBLE);
                    ivBtnReviews.setImageResource(R.drawable.ic_arrow_up);
                }
                break;
            case R.id.ivBtnWarrenty:
                if (tvWarrenty.getVisibility() == View.VISIBLE) {
                    tvWarrenty.setVisibility(View.GONE);
                    ivBtnWarrenty.setImageResource(R.drawable.ic_arrow_down);
                }
                else {
                    tvWarrenty.setVisibility(View.VISIBLE);
                    ivBtnWarrenty.setImageResource(R.drawable.ic_arrow_up);
                }
                break;
        }
    }
}