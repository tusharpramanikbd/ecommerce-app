package com.nitto.tushar.nrrii.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterCheckout;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.CartActivity;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements CartService.OnUpdateUIListener{

    private ArrayList<CartItem> cartItems;
    private AppCompatButton btnContinue;
    private AppCompatTextView subtotalPrice, totalPrice;

    private RecyclerView recyclerView;
    private RecyclerViewAdapterCheckout myAdapter;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout, divider_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_checkout);

        CartService.getInstance().AddOnUpdateUIListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        increaseHamburgerSize();

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, LoggedInShippingAddressActivity.class) );
            }
        });

        cartItems = new ArrayList<>();

        cartItems.addAll(CartService.getInstance().getAllCartItem());

        recyclerView = findViewById(R.id.recyclerViewCart);
        myAdapter = new RecyclerViewAdapterCheckout(this,cartItems );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        myAdapter.SetOnDeleteCartItemListener(new RecyclerViewAdapterCheckout.OnDeleteCartItemListener() {
            @Override
            public void onDeleteCartItem(String dressID) {
                new CheckoutActivity.DeleteCartItem(dressID).execute();
            }
        });

        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, MyProfileActivity.class) );
            }
        });

        layoutCategory = findViewById(R.id.layoutCategory);
        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, ProductCategoryActivity.class) );
            }
        });

        layoutOrders = findViewById(R.id.layoutOrders);
        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, MyOrdersActivity.class) );
            }
        });

        layoutCart = findViewById(R.id.layoutCart);
        layoutCart.setVisibility(View.GONE);

        divider_cart = findViewById(R.id.divider_cart);
        divider_cart.setVisibility(View.GONE);

        layoutSettings = findViewById(R.id.layoutSettings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, ResetPasswordActivity.class) );
            }
        });

        layoutLogout = findViewById(R.id.layoutLogout);
        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, LoginActivity.class) );
            }
        });

        subtotalPrice = findViewById(R.id.subtotalPrice);
        totalPrice = findViewById(R.id.totalPrice);
        subtotalPrice.setText(String.format("%s BDT", String.valueOf(CartService.getInstance().getTotalPrice())));
        totalPrice.setText(String.format("%s BDT", String.valueOf(CartService.getInstance().getTotalPrice() + 100.0)));


        //hide soft keyboard on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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

    @Override
    public void onItemPriceUpdated(double totalPriceBeforeChange) {
        subtotalPrice.setText(String.format("%s BDT", String.valueOf(totalPriceBeforeChange)));
        totalPrice.setText(String.format("%s BDT", String.valueOf(totalPriceBeforeChange + 100.0)));
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

    @SuppressLint("StaticFieldLeak")
    class DeleteCartItem extends AsyncTask<Void, Void, Void> {
        private String  dressId;

        DeleteCartItem(String  dressId) {
            this.dressId = dressId;
        }

        @Override
        protected Void doInBackground(Void... params) {
            CartService.getInstance().deleteCartItem(dressId);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cartItems.clear();
                    cartItems.addAll(CartService.getInstance().getProductCart());
                    myAdapter.notifyDataSetChanged();
                    refreshTotalPriceInUI();
                }
            });
        }
    }

    private void refreshTotalPriceInUI() {
        subtotalPrice.setText(String.format("%s BDT", String.valueOf(CartService.getInstance().getTotalPrice())));
        totalPrice.setText(String.format("%s BDT", String.valueOf(CartService.getInstance().getTotalPrice() + 100.0)));
    }
}
