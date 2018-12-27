package com.nitto.tushar.nrrii.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.CartItem;
import com.nitto.tushar.nrrii.Entity.User;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoggedInShippingAddressActivity extends AppCompatActivity {

    private AppCompatButton btnNext;
    private String orderData;
    //private AppCompatTextView userName, userMobile, userAddress;

    private AppCompatEditText firstName, lastName, address1, address2, city, state, postcode, country, email, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_shipping_address);


        initializeUI();


        //Button Events........................
//        btnAddNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoggedInShippingAddressActivity.this, ShippingAddressActivity.class) );
//            }
//        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String address_1 = address1.getText().toString();
                String address_2 = address2.getText().toString();
                String cityName = city.getText().toString();
                String stateName = state.getText().toString();
                String postCodeNumber = postcode.getText().toString();
                String countryName = country.getText().toString();
                String phone = phoneNumber.getText().toString();
                String email_address = email.getText().toString();


                if(fName.equals("") ||lName.equals("") ||address_1.equals("") ||address_2.equals("") ||cityName.equals("") ||stateName.equals("") ||postCodeNumber.equals("") ||countryName.equals("") ||phone.equals("") ||email_address.equals("")){
                    Toast.makeText(LoggedInShippingAddressActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                }
                else {

                    ArrayList<CartItem> cartItems = CartService.getInstance().getAllCartItem();

                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("payment_method", "processing");
                        jsonObject.put("set_paid", true);

                        JSONObject jsonObjectBilling = new JSONObject();
                        jsonObjectBilling.put("first_name", fName);
                        jsonObjectBilling.put("last_name", lName);
                        jsonObjectBilling.put("address_1", address_1);
                        jsonObjectBilling.put("address_2", address_2);
                        jsonObjectBilling.put("city", cityName);
                        jsonObjectBilling.put("state", stateName);
                        jsonObjectBilling.put("postcode", postCodeNumber);
                        jsonObjectBilling.put("country", countryName);
                        jsonObjectBilling.put("email", email_address);
                        jsonObjectBilling.put("phone", phone);

                        jsonObject.put("billing", jsonObjectBilling);

                        JSONObject jsonObjectShipping = new JSONObject();
                        jsonObjectShipping.put("first_name", fName);
                        jsonObjectShipping.put("last_name", lName);
                        jsonObjectShipping.put("address_1", address_1);
                        jsonObjectShipping.put("address_2", address_2);
                        jsonObjectShipping.put("city", cityName);
                        jsonObjectShipping.put("state", stateName);
                        jsonObjectShipping.put("postcode", postCodeNumber);
                        jsonObjectShipping.put("country", countryName);

                        jsonObject.put("shipping", jsonObjectShipping);

                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i<cartItems.size(); i++){
                            CartItem cartItem = cartItems.get(i);

                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("product_id", cartItem.getProductId());
                            jsonObject1.put("quantity", cartItem.getProductQuantity());
                        }

                        jsonObject.put("line_items", jsonArray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    orderData = jsonObject.toString();


                    showMessageOKCancel("Are you sure that all the information is correct?",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(LoggedInShippingAddressActivity.this, PaymentMethodActivity.class);

                                    intent.putExtra("orderData", orderData);

                                    startActivity(intent);
                                }
                            });
                }
            }
        });
    }

    private void initializeUI() {
        //initializing the buttons.....................
//        btnAddNew = findViewById(R.id.btnAddNew);
        btnNext = findViewById(R.id.btnNext);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        postcode = findViewById(R.id.postcode);
        country = findViewById(R.id.country);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);

//        userName = findViewById(R.id.userName);
//        userMobile = findViewById(R.id.userMobile);
//        userAddress = findViewById(R.id.userAddress);

//        if(UserService.getInstance().getStoredUsersNumber() > 0){
//            User user = UserService.getInstance().getUserArrayList().get(0);
//
//            userName.setText(user.getUserName());
//            userAddress.setText(user.getUserAddress());
//            userMobile.setText(user.getUserMobileNumber());
//        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoggedInShippingAddressActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
