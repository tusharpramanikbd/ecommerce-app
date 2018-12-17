package com.nitto.tushar.nrrii.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.nitto.tushar.nrrii.Adapter.MyPagerAdapter;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements CartService.OnUpdateUIListener {

    private ViewPager mPager;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btnCartBag,btnReadMore, btnAddToCart, btnQuantityIndicator, btnQuantityIndicatorDrawer;

    private LayoutInflater inflater;
    private LinearLayout linearLayout;

    private LinearLayout pager_indicator;
    private int dotsCount;
    private AppCompatImageView[] dots;

    private ReadMoreTextView text_view;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout;
    private AppCompatTextView tvPrice, dressTitle, totalPriceAfterAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_product_details);
        
        initializeUI();

        //Button event to go to cart............
        btnCartBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(ProductDetailsActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(ProductDetailsActivity.this, CheckoutActivity.class) );
                }
            }
        });
        
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                Log.d("###onPageSelected, pos ", String.valueOf(position));
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflater = (LayoutInflater) getApplicationContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                inflater = LayoutInflater.from(ProductDetailsActivity.this);

                linearLayout = findViewById(R.id.layoutReview);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(0, 0, 0, 30);

                for (int i = 1; i < 4; i++) {
                    final View v1 = inflater.inflate(R.layout.review_item, null);
                    linearLayout.addView(v1, layoutParams);
                }
            }
        });

        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, MyProfileActivity.class) );
            }
        });

        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, ProductCategoryActivity.class) );
            }
        });

        layoutOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, MyOrdersActivity.class) );
            }
        });

        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartService.getInstance().getTotalQuantity()< 1){
                    Toast.makeText(ProductDetailsActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(ProductDetailsActivity.this, CheckoutActivity.class) );
                }
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, ResetPasswordActivity.class) );
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, LoginActivity.class) );
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CartService.getInstance().isAvailableInCart(ProductService.getInstance().getDressDetails().getDressId())){
                    CartService.getInstance().increaseQuantity(ProductService.getInstance().getDressDetails().getDressId());
                }
                else {
                    CartItem cartItem = new CartItem();
                    cartItem.setProductId(ProductService.getInstance().getDressDetails().getDressId());
                    cartItem.setProductTitle(ProductService.getInstance().getDressDetails().getDressTitle());
                    cartItem.setProductPrice(Double.parseDouble(ProductService.getInstance().getDressDetails().getActualPrice()));
                    cartItem.setProductPhoto(ProductService.getInstance().getDressDetails().getImages().get(0).getSrc());

                    cartItem.setProductQuantity(1);

                    CartService.getInstance().addToCart(cartItem);
                }

                Toast.makeText(ProductDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CartService.getInstance().AddOnUpdateUIListener(this);

        pager_indicator = findViewById(R.id.viewPagerCountDots);

        this.drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        increaseHamburgerSize();

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeViewPager();

        btnCartBag = findViewById(R.id.btnCartBag);

        tvPrice = findViewById(R.id.tvPrice);
        tvPrice.setText(ProductService.getInstance().getDressDetails().getActualPrice());

        dressTitle = findViewById(R.id.dressTitle);
        dressTitle.setText(ProductService.getInstance().getDressDetails().getDressTitle());

        text_view = findViewById(R.id.text_view);
        text_view.setText(ProductService.getInstance().getDressDetails().getDressDetails());

        totalPriceAfterAddToCart = findViewById(R.id.totalPriceAfterAddToCart);
        totalPriceAfterAddToCart.setText(String.valueOf(CartService.getInstance().getTotalPrice()));

        btnReadMore = findViewById(R.id.btnReadMore);


        btnQuantityIndicator = findViewById(R.id.btnQuantityIndicator);
        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        btnQuantityIndicatorDrawer = findViewById(R.id.btnQuantityIndicatorDrawer);
        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        btnAddToCart = findViewById(R.id.btnAddToCart);

        //Setting more dress item....................
        dressArrayList = new ArrayList<>();
        dressArrayList.addAll(ProductService.getInstance().getAllDress());
        for (int i=0; i<this.dressArrayList.size(); i++){
            if(this.dressArrayList.get(i).getDressId().equals(ProductService.getInstance().getDressDetails().getDressId())){
                dressArrayList.remove(i);
            }
        }

        initializeRecyclerView();

        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutCategory = findViewById(R.id.layoutCategory);
        layoutOrders = findViewById(R.id.layoutOrders);
        layoutCart = findViewById(R.id.layoutCart);
        layoutSettings = findViewById(R.id.layoutSettings);
        layoutLogout = findViewById(R.id.layoutLogout);
    }

    private void initializeViewPager() {
        mPager = findViewById(R.id.viewpager);
        myPagerAdapter = new MyPagerAdapter(ProductService.getInstance().getDressDetails().getImages(), this);
        mPager.setAdapter(myPagerAdapter);
        mPager.setOffscreenPageLimit(2);

        setPageViewIndicator();
    }

    private void setPageViewIndicator() {
        if(dotsCount!=0) {
            pager_indicator.removeAllViews();
        }
        Log.d("###setPageViewIndicator", " : called");
        dotsCount = myPagerAdapter.getCount();
        dots = new AppCompatImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new AppCompatImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;

            dots[presentPosition].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mPager.setCurrentItem(presentPosition);
                }

            });

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }
    
    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDress);
        RecyclerViewAdapterDress myAdapter = new RecyclerViewAdapterDress(this,dressArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setNestedScrollingEnabled(false);
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
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        closeDrawerWithOutAnimation();
    }

    @Override
    public void onItemPriceUpdated(double totalPriceBeforeChange) {

    }

    @Override
    public void onItemDeleted(List<CartItem> cartItems) {

    }

    @Override
    public void onTotalPriceAndQuantityIndicatorUpdated() {
        totalPriceAfterAddToCart.setText(String.valueOf(CartService.getInstance().getTotalPrice()));

        btnQuantityIndicator.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));

        btnQuantityIndicatorDrawer.setText(String.valueOf(CartService.getInstance().getTotalQuantity()));
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

    @Override
    protected void onDestroy() {
        CartService.getInstance().RemoveOnUpdateUIListener(this);
        super.onDestroy();
    }
}
