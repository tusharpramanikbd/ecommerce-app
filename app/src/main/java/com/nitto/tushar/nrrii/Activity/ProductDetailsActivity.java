package com.nitto.tushar.nrrii.Activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.Adapter.MyPagerAdapter;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager mPeger;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btnReadMore;

    private LayoutInflater linf;
    private LinearLayout rr;

    private LinearLayout layoutReview, reviewItem;

    private int[] images = {R.mipmap.rifat_vais_wife_sample, R.mipmap.rifat_vais_wife_sample, R.mipmap.rifat_vais_wife_sample};

    private AppCompatImageView
            ivWalkThroughCircle1,
            ivWalkThroughCircle2,
            ivWalkThroughCircle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.ivWalkThroughCircle1 = findViewById(R.id.ivWalkThroughCircle1);
        this.ivWalkThroughCircle2 = findViewById(R.id.ivWalkThroughCircle2);
        this.ivWalkThroughCircle3 = findViewById(R.id.ivWalkThroughCircle3);

        mPeger = findViewById(R.id.viewpager);
        myPagerAdapter = new MyPagerAdapter(images, this);
        mPeger.setAdapter(myPagerAdapter);

        mPeger.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //reset all tab selection to not selected
                resetWalkthroughTabSelectionColor();
                //highlight custom tab button on selected page position here
                setSelectionColorOnSelectedPagePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnReadMore = findViewById(R.id.btnReadMore);

        btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linf = (LayoutInflater) getApplicationContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                linf = LayoutInflater.from(ProductDetailsActivity.this);

                rr = (LinearLayout) findViewById(R.id.layoutReview);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(0, 0, 0, 30);

                for (int i = 1; i < 4; i++) {
                    final View v1 = linf.inflate(R.layout.review_item, null);
                    rr.addView(v1, layoutParams);
                }
            }
        });

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
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void resetWalkthroughTabSelectionColor() {
        this.ivWalkThroughCircle1.setBackgroundResource(R.drawable.ic_circle_ash);
        this.ivWalkThroughCircle2.setBackgroundResource(R.drawable.ic_circle_ash);
        this.ivWalkThroughCircle3.setBackgroundResource(R.drawable.ic_circle_ash);
    }

    private void setSelectionColorOnSelectedPagePosition(int position) {
        switch (position) {
            case 0:
                this.ivWalkThroughCircle1.setBackgroundResource(R.drawable.ic_circle_ash_selected);
                break;
            case 1:
                this.ivWalkThroughCircle2.setBackgroundResource(R.drawable.ic_circle_ash_selected);
                break;
            case 2:
                this.ivWalkThroughCircle3.setBackgroundResource(R.drawable.ic_circle_ash_selected);
                break;
        }
    }
}
