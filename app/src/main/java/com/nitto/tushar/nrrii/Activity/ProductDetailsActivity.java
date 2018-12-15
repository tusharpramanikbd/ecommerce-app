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

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.nitto.tushar.nrrii.Adapter.MyPagerAdapter;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.ProductService;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager mPager;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btnReadMore;

    private LayoutInflater inflater;
    private LinearLayout linearLayout;

    private LinearLayout pager_indicator;
    private int dotsCount;
    private AppCompatImageView[] dots;

    private ReadMoreTextView text_view;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private LinearLayout layoutMyProfile, layoutCategory, layoutOrders, layoutCart, layoutSettings, layoutLogout;
    private AppCompatTextView tvPrice, dressTitle;

//    private AppCompatImageView
//            ivWalkThroughCircle1,
//            ivWalkThroughCircle2,
//            ivWalkThroughCircle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_product_details);
        
        initializeUI();
        
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
//                //reset all tab selection to not selected
//                resetWalkthroughTabSelectionColor();
//                //highlight custom tab button on selected page position here
//                setSelectionColorOnSelectedPagePosition(position);
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
                startActivity(new Intent(ProductDetailsActivity.this, CheckoutActivity.class) );
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
    }

    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager_indicator = findViewById(R.id.viewPagerCountDots);

        this.drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        increaseHamburgerSize();

        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        this.ivWalkThroughCircle1 = findViewById(R.id.ivWalkThroughCircle1);
//        this.ivWalkThroughCircle2 = findViewById(R.id.ivWalkThroughCircle2);
//        this.ivWalkThroughCircle3 = findViewById(R.id.ivWalkThroughCircle3);

        initializeViewPager();

        tvPrice = findViewById(R.id.tvPrice);
        tvPrice.setText(ProductService.getInstance().getDressDetails().getActualPrice());

        dressTitle = findViewById(R.id.dressTitle);
        dressTitle.setText(ProductService.getInstance().getDressDetails().getDressTitle());

        text_view = findViewById(R.id.text_view);
        text_view.setText(ProductService.getInstance().getDressDetails().getDressDetails());

        btnReadMore = findViewById(R.id.btnReadMore);

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
    
//    private void resetWalkthroughTabSelectionColor() {
//        this.ivWalkThroughCircle1.setBackgroundResource(R.drawable.ic_circle_ash);
//        this.ivWalkThroughCircle2.setBackgroundResource(R.drawable.ic_circle_ash);
//        this.ivWalkThroughCircle3.setBackgroundResource(R.drawable.ic_circle_ash);
//    }

//    private void setSelectionColorOnSelectedPagePosition(int position) {
//        switch (position) {
//            case 0:
//                this.ivWalkThroughCircle1.setBackgroundResource(R.drawable.ic_circle_ash_selected);
//                break;
//            case 1:
//                this.ivWalkThroughCircle2.setBackgroundResource(R.drawable.ic_circle_ash_selected);
//                break;
//            case 2:
//                this.ivWalkThroughCircle3.setBackgroundResource(R.drawable.ic_circle_ash_selected);
//                break;
//        }
//    }

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
