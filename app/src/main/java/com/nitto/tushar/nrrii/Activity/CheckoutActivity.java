package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterCheckout;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<CartItem> cartItems;
    private AppCompatButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, LoggedInShippingAddressActivity.class) );
            }
        });

        cartItems = new ArrayList<>();

        cartItems.add(new CartItem(2, 5000, "M", "Green"));
        cartItems.add(new CartItem(1, 6000, "L", "Black"));
        cartItems.add(new CartItem(3, 7000, "XL", "Red"));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCart);
        RecyclerViewAdapterCheckout myAdapter = new RecyclerViewAdapterCheckout(this,cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
