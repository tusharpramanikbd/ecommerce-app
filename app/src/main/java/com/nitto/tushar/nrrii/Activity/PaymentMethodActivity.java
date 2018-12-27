package com.nitto.tushar.nrrii.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Network.ApiCalls.ApiSendOrder;
import com.nitto.tushar.nrrii.Network.RetrofitInstance;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.OrderService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodActivity extends AppCompatActivity {

    private AppCompatSpinner paymentSpinner;
    private LinearLayout cardPaymentLayout, bkashPaymentLayout, cashOnDeliveryPaymentLayout;
    private AppCompatButton btnDone;
    private String orderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);


        initializeUI();


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObject = new JSONObject(orderData);
                    jsonObject.put("status", "completed");

                    sendOrder(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(paymentSpinner.getSelectedItem().toString().equals("Pay by card")){
                    cardPaymentLayout.setVisibility(View.VISIBLE);
                    bkashPaymentLayout.setVisibility(View.GONE);
                    cashOnDeliveryPaymentLayout.setVisibility(View.GONE);
                }
                else if(paymentSpinner.getSelectedItem().toString().equals("Bkash")) {
                    cardPaymentLayout.setVisibility(View.GONE);
                    cashOnDeliveryPaymentLayout.setVisibility(View.GONE);
                    bkashPaymentLayout.setVisibility(View.VISIBLE);
                }
                else {
                    cardPaymentLayout.setVisibility(View.GONE);
                    cashOnDeliveryPaymentLayout.setVisibility(View.VISIBLE);
                    bkashPaymentLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cardPaymentLayout.setVisibility(View.VISIBLE);
                bkashPaymentLayout.setVisibility(View.GONE);
                cashOnDeliveryPaymentLayout.setVisibility(View.GONE);
            }
        });
    }

    private void initializeUI() {
        cardPaymentLayout = findViewById(R.id.cardPaymentLayout);
        bkashPaymentLayout = findViewById(R.id.bkashPaymentLayout);
        paymentSpinner = findViewById(R.id.paymentSpinner);
        btnDone = findViewById(R.id.btnDone);
        cashOnDeliveryPaymentLayout = findViewById(R.id.cashOnDeliveryPaymentLayout);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            orderData = extras.getString("orderData");
        }

    }

    public void sendOrder(JSONObject jsonObject){
        String bodyString = jsonObject.toString();
        ApiSendOrder apiSendOrder = RetrofitInstance.getInstance().create(ApiSendOrder.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), bodyString);
        Call<ResponseBody> result;

        result = apiSendOrder.sendOrder(body);

        result.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();

                    JSONObject jsonObject1 = new JSONObject(body);

                    Log.d("ami", String.valueOf(jsonObject1));

                    ArrayList<CartItem> cartItems = CartService.getInstance().getAllCartItem();

                    for(int i = 0; i<cartItems.size(); i++){

                        OrderItem orderItem = new OrderItem();
                        orderItem.setServerOrderId(jsonObject1.get("id").toString());
                        orderItem.setOrderDate(jsonObject1.get("date_created").toString().substring(0, 10));
                        orderItem.setOrderTitle(cartItems.get(i).getProductTitle());
                        orderItem.setQuantity(cartItems.get(i).getProductQuantity());
                        orderItem.setImageLink(cartItems.get(i).getProductPhoto());
                        orderItem.setOrderComplete(false);

                        OrderService.getInstance().insertOrderInDB(orderItem);
                        CartService.getInstance().deleteAllCartItem();

                    }

                    startActivity(new Intent(PaymentMethodActivity.this, CongratulationScreenActivity.class) );

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PaymentMethodActivity.this, "Network Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
