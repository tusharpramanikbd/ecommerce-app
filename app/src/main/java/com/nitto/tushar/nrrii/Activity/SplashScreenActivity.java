package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.ProductService;

public class SplashScreenActivity extends AppCompatActivity implements ProductService.UpdateUIOnProductRetrieveListener {

    private ProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProductService.getInstance().AddUpdateUIOnProductRetrieveListener(this);

        circularProgressBar = findViewById(R.id.progressBar);
        circularProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#9357C1"), android.graphics.PorterDuff.Mode.MULTIPLY);

        ProductService.getInstance().getProductItemsFromServer();
        //just waiting for 1 sec on the splash screen then going to main activity
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class) );
//                finish();
//            }
//        }, 2000);
    }

    @Override
    public void onProductRetrieve() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class) );
        finish();
    }

    @Override
    protected void onDestroy() {
        ProductService.getInstance().RemoveUpdateUIOnProductRetrieveListener();
        super.onDestroy();
    }
}
