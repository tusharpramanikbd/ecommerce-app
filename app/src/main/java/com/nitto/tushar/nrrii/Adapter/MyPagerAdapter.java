package com.nitto.tushar.nrrii.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitto.tushar.nrrii.R;

public class MyPagerAdapter extends PagerAdapter {

    private int[] images;
    private LayoutInflater inflater;
    private Context mContext;

    public MyPagerAdapter(int[] images, Context mContext) {
        this.images = images;
        this.mContext = mContext;
        this.inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.layout_image_slide, container, false);

        AppCompatImageView slideImage = view.findViewById(R.id.slideImage);

        slideImage.setImageResource(images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
    }
}
