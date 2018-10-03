package com.nitto.tushar.nrrii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Services.CartService;

public class PaymentMethodsActivity extends AppCompatActivity {

    private boolean isPaidShipping = false;
    private AppCompatTextView tvTotalAmount;
    private AppCompatCheckBox cbPaymentMethodBkash, cbPaymentMethodCOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment Details");

        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        cbPaymentMethodBkash = findViewById(R.id.cbPaymentMethodBkash);
        cbPaymentMethodCOD = findViewById(R.id.cbPaymentMethodCOD);

        SharedPreferences prefs = getSharedPreferences("shipping_methods", MODE_PRIVATE);
        String restoredText = prefs.getString("delivery_method", null);

        if (restoredText != null) {
            String methodName = prefs.getString("delivery_method", "0");
            if (methodName.equals("1")) {
                isPaidShipping = true;
            }
        }

        if(isPaidShipping){
            tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice() + 50));
        }else {
            tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
        }

        cbPaymentMethodBkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbPaymentMethodBkash.isChecked()){
                    cbPaymentMethodCOD.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("payment_method", "1");
                    editor.commit();
                }
                else {
                    cbPaymentMethodCOD.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("payment_method", "0");
                    editor.commit();
                }
            }
        });

        cbPaymentMethodCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbPaymentMethodCOD.isChecked()){
                    cbPaymentMethodBkash.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("payment_method", "2");
                    editor.commit();
                }
                else {
                    cbPaymentMethodBkash.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("payment_method", "0");
                    editor.commit();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shipping_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_proceed:

                if(!cbPaymentMethodBkash.isChecked() && !cbPaymentMethodCOD.isChecked()){
                    Toast.makeText(this, "You can not proceed", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(this, CartSummaryActivity.class) );
                }
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
