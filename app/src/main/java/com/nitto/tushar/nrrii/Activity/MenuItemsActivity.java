package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.R;

public class MenuItemsActivity extends AppCompatActivity {

    private LinearLayout layoutMyProfile, orderList, btnSettings, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);


        initializeUI();


        //Button Events..............................
        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuItemsActivity.this, MyProfileActivity.class) );
            }
        });

        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuItemsActivity.this, MyOrdersActivity.class) );
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuItemsActivity.this, ResetPasswordActivity.class) );
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuItemsActivity.this, LoginActivity.class) );
            }
        });
    }

    private void initializeUI() {
        //Initializing the components....................
        layoutMyProfile = findViewById(R.id.layoutMyProfile);
        orderList = findViewById(R.id.orderList);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogout = findViewById(R.id.btnLogout);
    }
}
