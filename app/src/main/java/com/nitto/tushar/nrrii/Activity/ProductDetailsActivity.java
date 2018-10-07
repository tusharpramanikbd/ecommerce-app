package com.nitto.tushar.nrrii.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import com.nitto.tushar.nrrii.Adapter.MyPagerAdapter;
import com.nitto.tushar.nrrii.R;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager mPeger;
    private MyPagerAdapter myPagerAdapter;
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
