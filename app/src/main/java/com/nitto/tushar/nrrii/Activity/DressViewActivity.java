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
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class DressViewActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btn_berger, btnCartBag;
    ActionBarDrawerToggle toggle;
    private LinearLayout layoutMyProfile, orderList, btnSettings, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        increaseHamburgerSize();
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.mipmap.berger_menu);
        //toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
//        toggle.setDrawerArrowDrawable(R.drawable.berger_menu);
//        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDrawer();
//            }
//        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        btn_berger = findViewById(R.id.btn_berger);
//        btn_berger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(DressViewActivity.this, MenuItemsActivity.class) );
//                openDrawer();
//            }
//        });

//        btnCartBag = findViewById(R.id.btnCartBag);
//        btnCartBag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DressViewActivity.this, CheckoutActivity.class) );
//            }
//        });

        dressArrayList = new ArrayList<>();
        dressArrayList.add(new Dress("5000", "4000"));
        dressArrayList.add(new Dress("6000", "5000"));
        dressArrayList.add(new Dress("7000", "5000"));
        dressArrayList.add(new Dress("8000", "4000"));
        dressArrayList.add(new Dress("9000", "4000"));
        dressArrayList.add(new Dress("9000", "8000"));
        dressArrayList.add(new Dress("8000", "7000"));
        dressArrayList.add(new Dress("7000", "7000"));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDress);
        RecyclerViewAdapterDress myAdapter = new RecyclerViewAdapterDress(this,dressArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(myAdapter);


        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DressViewActivity.this, MyProfileActivity.class) );
                //closeDrawerWithOutAnimation();
            }
        });

        orderList = findViewById(R.id.orderList);
        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, MyOrdersActivity.class) );
                //closeDrawerWithOutAnimation();

            }
        });

        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, ResetPasswordActivity.class) );
                //closeDrawerWithOutAnimation();

            }
        });

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, LoginActivity.class) );
                //closeDrawerWithOutAnimation();

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


    public class HamburgerDrawable extends DrawerArrowDrawable{

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
