package com.nitto.tushar.nrrii.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.Activity.FullScreenImageActivity;
import com.nitto.tushar.nrrii.Entity.ImageItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<ImageItem> imageItems;
    private LayoutInflater inflater;
    private Context mContext;

    public MyPagerAdapter(ArrayList<ImageItem> imageItems, Context mContext) {
        this.imageItems = imageItems;
        this.mContext = mContext;
        this.inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.layout_image_slide, container, false);

        AppCompatImageView slideImage = view.findViewById(R.id.slideImage);

        //slideImage.setImageResource(images[position]);

        Glide
                .with(mContext)
                .load(imageItems.get(position).getSrc())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(slideImage);

        container.addView(view);

        final String link = imageItems.get(position).getSrc();

        slideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FullScreenImageActivity.class);
                i.putExtra("imageLink", link);
                mContext.startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
    }
}
