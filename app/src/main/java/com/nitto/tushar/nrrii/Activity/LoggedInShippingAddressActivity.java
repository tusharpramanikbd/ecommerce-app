package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.nitto.tushar.nrrii.R;

public class LoggedInShippingAddressActivity extends AppCompatActivity {

    private AppCompatButton btnAddNew, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_shipping_address);

        btnAddNew = findViewById(R.id.btnAddNew);
        btnNext = findViewById(R.id.btnNext);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedInShippingAddressActivity.this, ShippingAddressActivity.class) );

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedInShippingAddressActivity.this, PaymentMethodActivity.class) );

            }
        });
    }
}
