package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nitto.tushar.nrrii.MainActivity;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.ShippingAndBillingAddressActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //just waiting for 1 sec on the splash screen then going to main activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class) );
                finish();
            }
        }, 1000);
    }
}
