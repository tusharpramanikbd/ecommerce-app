package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.R;

public class PaymentMethodActivity extends AppCompatActivity {

    private AppCompatSpinner paymentSpinner;
    private LinearLayout cardPaymentLayout, bkashPaymentLayout;
    private AppCompatButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        cardPaymentLayout = findViewById(R.id.cardPaymentLayout);
        bkashPaymentLayout = findViewById(R.id.bkashPaymentLayout);
        paymentSpinner = findViewById(R.id.paymentSpinner);
        btnDone = findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethodActivity.this, CongratulationScreenActivity.class) );

            }
        });

        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(paymentSpinner.getSelectedItem().toString().equals("Pay by card")){
                    cardPaymentLayout.setVisibility(View.VISIBLE);
                    bkashPaymentLayout.setVisibility(View.GONE);
                }
                else {
                    cardPaymentLayout.setVisibility(View.GONE);
                    bkashPaymentLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cardPaymentLayout.setVisibility(View.VISIBLE);
                bkashPaymentLayout.setVisibility(View.GONE);
            }
        });

    }
}
