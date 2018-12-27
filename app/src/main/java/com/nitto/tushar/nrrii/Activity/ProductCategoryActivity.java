package com.nitto.tushar.nrrii.Activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterCategory;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;

public class ProductCategoryActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ObjectAnimator flipAnimation;
    private AppCompatButton btnCartBag, btnQuantityIndicator, btnQuantityIndicatorDrawer;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout;
    private View imageToAnimate;
    private AppCompatImageView iv_dress, iv_dress2, iv_bag, iv_shoes, iv_perfume, iv_cosmetics, iv_accessories;

    private int[] images = {R.mipmap.cat_1, R.mipmap.cat_2, R.mipmap.cat_3, R.mipmap.cat_4};
    private String[] categoryNames = {"Category Name 1", "Category Name 2", "Category Name 3", "Category Name 4",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_product_category);


        initializeUI();




        //Button event to go to cart............
        btnCartBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(ProductCategoryActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(ProductCategoryActivity.this, CheckoutActivity.class) );
                }
            }
        });


        //Button Event For Menu Drawer..........
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, MyProfileActivity.class) );
            }
        });

        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, ProductCategoryActivity.class) );
            }
        });

        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, MyOrdersActivity.class) );
            }
        });

        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(ProductCategoryActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(ProductCategoryActivity.this, CheckoutActivity.class) );
                }
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, ResetPasswordActivity.class) );
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
            }
        });




        iv_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, DressViewActivity.class) );
            }
        });

        iv_dress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCategoryActivity.this, DressViewActivity.class) );
            }
        });

        iv_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
                Toast.makeText(ProductCategoryActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        iv_perfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
                Toast.makeText(ProductCategoryActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        iv_cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
                Toast.makeText(ProductCategoryActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        iv_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
                Toast.makeText(ProductCategoryActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        iv_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ProductCategoryActivity.this, LoginActivity.class) );
                Toast.makeText(ProductCategoryActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void startFlipAnimation() {
        this.flipAnimation.setDuration(4000);
        this.flipAnimation.setRepeatCount(ValueAnimator.INFINITE);
        this.imageToAnimate.setCameraDistance(10 * this.imageToAnimate.getWidth());
        this.flipAnimation.start();
    }

    private void stopFlipAnimation() {
        if(this.flipAnimation.isStarted()) {
            if(this.flipAnimation.isRunning()) {
                this.flipAnimation.cancel();
                this.flipAnimation.setDuration(0);
                this.flipAnimation.setRepeatCount(0);
                this.flipAnimation.reverse();
            }
        } else {
            Toast.makeText(this, "Start animation first!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeUI() {
        //Initializing the menu toolbar..........
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding drawer to hamburger menu in toolbar..........
        this.drawer = findViewById(R.id.drawer_layout_product_category);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //Setting hamburger size..................
        increaseHamburgerSize();

        //Adding toggle listener to drawer..............
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnCartBag = findViewById(R.id.btnCartBag);

        //initializeRecyclerView();

        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutCategory = findViewById(R.id.layoutCategory);
        layoutOrders = findViewById(R.id.layoutOrders);
        layoutCart = findViewById(R.id.layoutCart);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutLogout = findViewById(R.id.layoutLogout);


        imageToAnimate = findViewById(R.id.imageToAnimate);

        iv_dress = findViewById(R.id.dress1);
        iv_dress2 = findViewById(R.id.dress2);
        iv_bag = findViewById(R.id.bag);
        iv_accessories = findViewById(R.id.accessories);
        iv_cosmetics = findViewById(R.id.cosmatics);
        iv_perfume = findViewById(R.id.perfume);
        iv_shoes = findViewById(R.id.shoes);

        btnQuantityIndicator = findViewById(R.id.btnQuantityIndicator);
        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        btnQuantityIndicatorDrawer = findViewById(R.id.btnQuantityIndicatorDrawer);
        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

//        initializeAnimator();
//
//        startFlipAnimation();
    }

    private void initializeAnimator() {
        this.flipAnimation = ObjectAnimator.ofFloat(this.imageToAnimate, "rotationY", 0.0f, 1440f);
//        enable lines of code given below to introudce a skew/scale animation
//        Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
//        this.imageToAnimate.startAnimation(logoMoveAnimation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));
        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCategory);
        RecyclerViewAdapterCategory myAdapter = new RecyclerViewAdapterCategory(this,images,categoryNames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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


    public class HamburgerDrawable extends DrawerArrowDrawable {

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
}
