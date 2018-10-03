package com.nitto.tushar.nrrii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Services.CartService;

public class ShippingAndBillingAddressActivity extends AppCompatActivity {

    private AppCompatTextView tvTotalAmount;
    private AppCompatEditText etCustomerShippingAddress, etCustomerBillingAddress;
    private AppCompatCheckBox cbSameAsShippingAddress, cbCourrierMethodDHL, cbCourrierMethodUKpost;
    private boolean isPaidShipping = false;
    private boolean isSameAsShippingAddress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_and_billing_address);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shipping Details");

        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        etCustomerShippingAddress = findViewById(R.id.etCustomerShippingAddress);
        etCustomerBillingAddress = findViewById(R.id.etCustomerBillingAddress);
        cbSameAsShippingAddress = findViewById(R.id.cbSameAsShippingAddress);
        cbCourrierMethodDHL = findViewById(R.id.cbCourrierMethodDHL);
        cbCourrierMethodUKpost = findViewById(R.id.cbCourrierMethodUKpost);

        SharedPreferences prefs = getSharedPreferences("shipping_methods", MODE_PRIVATE);
        String restoredText = prefs.getString("delivery_method", null);
        isSameAsShippingAddress = prefs.getBoolean("isSameAsShippingAddress", false);
        if (restoredText != null) {
            String methodName = prefs.getString("delivery_method", "0");
            if(methodName.equals("1")){
                cbCourrierMethodDHL.setChecked(true);
                isPaidShipping = true;
            }
            else if (methodName.equals("2")){
                cbCourrierMethodUKpost.setChecked(true);
            }
        }else {
            isPaidShipping = false;
        }

        String shippingAddress = prefs.getString("shipping_address", null);
        if(isSameAsShippingAddress){
            etCustomerShippingAddress.setText(shippingAddress);
            etCustomerBillingAddress.setText(shippingAddress);
            etCustomerBillingAddress.setEnabled(false);
            cbSameAsShippingAddress.setChecked(true);
        }else if(!isSameAsShippingAddress){
            etCustomerShippingAddress.setText(shippingAddress);
            String billingAddress = prefs.getString("billing_address", null);
            etCustomerBillingAddress.setText(billingAddress);
        }


        if (isPaidShipping){
            tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice() + 50));
        }
        else {
            tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
        }


        cbCourrierMethodDHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCourrierMethodDHL.isChecked()){
                    cbCourrierMethodUKpost.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("delivery_method", "1");
                    editor.commit();

                    tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice() + 50));
                }
                else {
                    cbCourrierMethodUKpost.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("delivery_method", "0");
                    editor.commit();

                    tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
                }

            }
        });

        cbCourrierMethodUKpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCourrierMethodUKpost.isChecked()){
                    cbCourrierMethodDHL.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("delivery_method", "2");
                    editor.commit();

                    tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
                }
                else {
                    cbCourrierMethodDHL.setChecked(false);

                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putString("delivery_method", "0");

                    editor.commit();
                }

            }
        });

        cbSameAsShippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbSameAsShippingAddress.isChecked()){
                    etCustomerBillingAddress.setEnabled(false);
                    //cbSameAsShippingAddress.setChecked(false);
                    isSameAsShippingAddress = true;

                    etCustomerBillingAddress.setText(etCustomerShippingAddress.getText().toString());
                }
                else {
                    etCustomerBillingAddress.setEnabled(true);
                    isSameAsShippingAddress = false;

                    etCustomerBillingAddress.setText("");
                }
            }
        });

        etCustomerShippingAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(isSameAsShippingAddress){
                    etCustomerBillingAddress.setText(s.toString());
                }

                SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                editor.putString("shipping_address", s.toString());
                editor.commit();
            }
        });

        etCustomerBillingAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                editor.putString("billing_address", s.toString());
                editor.commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_proceed:

                if(etCustomerShippingAddress.getText().toString().isEmpty() && etCustomerBillingAddress.getText().toString().isEmpty()){
                    Toast.makeText(this, "You can not proceed", Toast.LENGTH_LONG).show();
                }
                else if (etCustomerShippingAddress.getText().toString().isEmpty() && !cbSameAsShippingAddress.isChecked()){
                    Toast.makeText(this, "You can not proceed", Toast.LENGTH_LONG).show();
                }
                else if (!cbCourrierMethodDHL.isChecked() && !cbCourrierMethodUKpost.isChecked()){
                    Toast.makeText(this, "You can not proceed", Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                    editor.putBoolean("isSameAsShippingAddress", isSameAsShippingAddress);
                    editor.commit();

                    startActivity(new Intent(this, PaymentMethodsActivity.class) );
                }
                break;

            case android.R.id.home:
                SharedPreferences.Editor editor1 = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
                editor1.putBoolean("isSameAsShippingAddress", isSameAsShippingAddress);
                editor1.commit();
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shipping_menu, menu);
        return true;
    }
}
