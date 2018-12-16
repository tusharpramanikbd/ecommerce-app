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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Network.ApiCalls.ApiSearchProduct;
import com.nitto.tushar.nrrii.Network.RetrofitInstance;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DressViewActivity extends AppCompatActivity implements ProductService.UpdateUIOnProductRetrieveListener {

    private DrawerLayout drawer;
    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btnCartBag, btnQuantityIndicator, btnQuantityIndicatorDrawer;
    private ActionBarDrawerToggle toggle;
    private RecyclerViewAdapterDress myAdapter;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);


        initializeUI();


        //Button event to go to cart............
        btnCartBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(DressViewActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(DressViewActivity.this, CheckoutActivity.class) );
                }
            }
        });


        //Button Event For Menu Drawer..........
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, MyProfileActivity.class) );
            }
        });

        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, ProductCategoryActivity.class) );
            }
        });

        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, MyOrdersActivity.class) );
            }
        });

        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(DressViewActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(DressViewActivity.this, CheckoutActivity.class) );
                }
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, ResetPasswordActivity.class) );
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, LoginActivity.class) );
            }
        });
    }

    private void initializeUI() {
        //Initializing the service calls.........
        ProductService.getInstance().AddUpdateUIOnProductRetrieveListener(this);

        dressArrayList = new ArrayList<>();
        //dressArrayList.addAll(ProductService.getInstance().getAllDress());

        //Initializing the menu toolbar..........
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding drawer to hamburger menu in toolbar..........
        this.drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //Setting hamburger size..................
        increaseHamburgerSize();

        //Adding toggle listener to drawer..............
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeRecyclerView();

        //Initializing cart button in toolbar...........
        btnCartBag = findViewById(R.id.btnCartBag);

        //Initializing menu buttons in menu drawer...........
        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutCategory = findViewById(R.id.layoutCategory);
        layoutOrders = findViewById(R.id.layoutOrders);
        layoutCart = findViewById(R.id.layoutCart);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutLogout = findViewById(R.id.layoutLogout);

        btnQuantityIndicator = findViewById(R.id.btnQuantityIndicator);
        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        btnQuantityIndicatorDrawer = findViewById(R.id.btnQuantityIndicatorDrawer);
        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        ProductService.getInstance().getProductItemsFromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));
        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDress);
        myAdapter = new RecyclerViewAdapterDress(this,dressArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(myAdapter);
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

    @Override
    public void onProductRetrieve(List<ProductItem> productItems) {

        for (int i = 0; i < productItems.size(); i++){
            Dress dress = new Dress();
            dress.setDressId(String.valueOf(productItems.get(i).getUid()));
            dress.setDressTitle(productItems.get(i).getName());
            dress.setImages(productItems.get(i).getImages());
            dress.setActualPrice(String.valueOf(productItems.get(i).getPrice()));
            dress.setDressDetails(productItems.get(i).getProductDescription());
            dressArrayList.add(dress);
        }

        myAdapter.notifyDataSetChanged();
    }


    public class HamburgerDrawable extends DrawerArrowDrawable{

        HamburgerDrawable(Context context){
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

    @Override
    protected void onDestroy() {
        ProductService.getInstance().RemoveUpdateUIOnProductRetrieveListener();
        super.onDestroy();
    }
}
