package com.nitto.tushar.nrrii.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterCategory;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.R;

public class ProductCategoryActivity extends AppCompatActivity {

    private RelativeLayout layoutCat1;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private AppCompatButton btnCartBag;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout;

    private int[] images = {R.mipmap.cat_1, R.mipmap.cat_2, R.mipmap.cat_3, R.mipmap.cat_4};
    private String[] categoryNames = {"Category Name 1", "Category Name 2", "Category Name 3", "Category Name 4",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_product_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = findViewById(R.id.drawer_layout_product_category);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        increaseHamburgerSize();

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnCartBag = findViewById(R.id.btnCartBag);
        btnCartBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, CheckoutActivity.class) );
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCategory);
        RecyclerViewAdapterCategory myAdapter = new RecyclerViewAdapterCategory(this,images,categoryNames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, MyProfileActivity.class) );
            }
        });

        layoutCategory = findViewById(R.id.layoutCategory);
        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, ProductCategoryActivity.class) );
            }
        });

        layoutOrders = findViewById(R.id.layoutOrders);
        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, MyOrdersActivity.class) );
            }
        });

        layoutCart = findViewById(R.id.layoutCart);
        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, CheckoutActivity.class) );
            }
        });

        layoutSettings = findViewById(R.id.layoutSettings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, ResetPasswordActivity.class) );
            }
        });

        layoutLogout = findViewById(R.id.layoutLogout);
        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        closeDrawerWithOutAnimation();
    }

    private boolean isDrawerOpen() {
        return this.drawer.isDrawerOpen(GravityCompat.START);
    }

    private void openDrawer() {
        this.drawer.openDrawer(GravityCompat.END);
    }

    private void closeDrawer() {
        this.drawer.closeDrawer(GravityCompat.START);
    }

    private void closeDrawerWithOutAnimation() {
        this.drawer.closeDrawer(GravityCompat.START, false);
    }

    private void increaseHamburgerSize(){
        toggle.setDrawerArrowDrawable(new HamburgerDrawable(this));
    }


    public class HamburgerDrawable extends DrawerArrowDrawable {

        public HamburgerDrawable(Context context){
            super(context);
            setColor(context.getResources().getColor(R.color.colorPrimary));
        }

        @Override
        public void draw(Canvas canvas){
            super.draw(canvas);

            setBarLength(60.0f);
            setBarThickness(10.0f);
            setGapSize(10.0f);

        }
    }
}
