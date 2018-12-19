package com.nitto.tushar.nrrii.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitto.tushar.nrrii.R;

public class FullScreenImageActivity extends AppCompatActivity {

    private AppCompatImageView imageView, imageArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_full_screen_image);

        Bundle extras = getIntent().getExtras();
        String imageLink = null;

        if (extras != null) {
            imageLink = extras.getString("imageLink");
        }

        imageView = findViewById(R.id.imageView);
        imageArrow = findViewById(R.id.imageArrow);

        Glide
                .with(this)
                .load(imageLink)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_default_product_image)
                        .error(R.drawable.ic_default_product_image))
                .into(imageView);


        imageArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
