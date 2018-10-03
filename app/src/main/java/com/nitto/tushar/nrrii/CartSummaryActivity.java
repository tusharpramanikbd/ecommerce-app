package com.nitto.tushar.nrrii;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Network.ApiCalls.ApiSendOrder;
import com.nitto.tushar.nrrii.Network.RetrofitInstance;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.OrderService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartSummaryActivity extends AppCompatActivity {

    private List<ProductItem> productItems;
    private CartItemListSummaryAdapter cartItemListAdapter;
    private ListView lvCartItems;
    private boolean isSameAsShippingAddress = false;

    private AppCompatTextView tvTotalAmount;
    private AppCompatEditText etCustomerShippingAddress, etCustomerBillingAddress;

    private LinearLayout method_express, method_post, llPaymentMethodBkash, llPaymentMethodCOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_summary);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cart Summary");

        initializeUI();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeUI() {

        this.productItems = new ArrayList<>();

        this.lvCartItems = findViewById(R.id.lvCartItems);
        this.lvCartItems.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        this.tvTotalAmount = findViewById(R.id.tvTotalAmount);

        this.cartItemListAdapter = new CartItemListSummaryAdapter(this, this.productItems);

        this.lvCartItems.setAdapter(cartItemListAdapter);
        getCartItems();
        setListViewHeightBasedOnChildren(this.lvCartItems);


        //Shipping Address part................................................
        etCustomerShippingAddress = findViewById(R.id.etCustomerShippingAddress);
        etCustomerBillingAddress = findViewById(R.id.etCustomerBillingAddress);

        SharedPreferences prefs = getSharedPreferences("shipping_methods", MODE_PRIVATE);
        isSameAsShippingAddress = prefs.getBoolean("isSameAsShippingAddress", false);

        String shippingAddress = prefs.getString("shipping_address", null);
        if(isSameAsShippingAddress){
            etCustomerShippingAddress.setText(shippingAddress);
            etCustomerBillingAddress.setText(shippingAddress);
            etCustomerShippingAddress.setEnabled(false);
            etCustomerBillingAddress.setEnabled(false);
        }else if(!isSameAsShippingAddress){
            etCustomerShippingAddress.setText(shippingAddress);
            String billingAddress = prefs.getString("billing_address", null);
            etCustomerBillingAddress.setText(billingAddress);
            etCustomerShippingAddress.setEnabled(false);
            etCustomerBillingAddress.setEnabled(false);
        }

        method_express = findViewById(R.id.method_express);
        method_post = findViewById(R.id.method_post);

        String shippingMethod = prefs.getString("delivery_method", "0");
        if(shippingMethod.equals("1")){
            method_express.setVisibility(View.VISIBLE);
        }else if(shippingMethod.equals("2")){
            method_post.setVisibility(View.VISIBLE);
        }

        //Payment method part..............................................
        llPaymentMethodBkash = findViewById(R.id.llPaymentMethodBkash);
        llPaymentMethodCOD = findViewById(R.id.llPaymentMethodCOD);

        String paymentMethod = prefs.getString("payment_method" , "0");

        if(paymentMethod.equals("1")){
            llPaymentMethodBkash.setVisibility(View.VISIBLE);
        }else if(paymentMethod.equals("2")){
            llPaymentMethodCOD.setVisibility(View.VISIBLE);
        }

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void getCartItems() {
        this.productItems.addAll(CartService.getInstance().getProductCart());
        this.refreshTotalPriceInUI();
        this.cartItemListAdapter.notifyDataSetChanged();
    }

    private void refreshTotalPriceInUI() {
        SharedPreferences prefs = getSharedPreferences("shipping_methods", MODE_PRIVATE);
        String shippingMethod = prefs.getString("delivery_method", "0");
        if(shippingMethod.equals("1")){
            this.tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()+50));
        }else if(shippingMethod.equals("2")){
            this.tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
        }

    }

    private void storeOrderToDb() {
        ArrayList<ProductItem> productItems;

        productItems = CartService.getInstance().getProductCart();

        String tmpOrderNumber = OrderService.getInstance().getLastOrderNumberById("01746941437");

        if(tmpOrderNumber == null){
            tmpOrderNumber = "1";
        }
        else {
            int tmpNumber = Integer.parseInt(tmpOrderNumber);
            tmpNumber++;
            tmpOrderNumber = String.valueOf(tmpNumber);
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setDeliveredTo("01746941437");
        orderItem.setOrderComplete(false);
        orderItem.setOrderDate(Calendar.getInstance().getTime().toString());
        orderItem.setOrderDescription(getProductDescription(productItems));
        orderItem.setOrderPrice(CartService.getInstance().getTotalPrice());
        orderItem.setQuantity(productItems.size());
        orderItem.setOrderNumber(tmpOrderNumber);
        orderItem.setShopId("420");
        orderItem.setShopName("Fordo Shop");

        OrderService.getInstance().insertOrderInDB(orderItem);

        CartService.getInstance().deleteAllCartItem();

        sendOrderToServer(orderItem);

    }

    private void sendOrderToServer(OrderItem orderItem) {

        ApiSendOrder apiSendOrder = RetrofitInstance.getInstance().create(ApiSendOrder.class);
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("orderNumber", orderItem.getOrderNumber());
            jsonObject.put("orderDescription", orderItem.getOrderDescription());
            jsonObject.put("orderPrice", String.valueOf(orderItem.getOrderPrice()));
            jsonObject.put("quantity", String.valueOf(orderItem.getQuantity()));
            jsonObject.put("orderDate", orderItem.getOrderDate());
            jsonObject.put("isOrderComplete", String.valueOf(orderItem.isOrderComplete()));
            jsonObject.put("shopId", orderItem.getShopId());
            jsonObject.put("shopName", orderItem.getShopName());
            jsonObject.put("deliveredTo", orderItem.getDeliveredTo());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());

        Call<ResponseBody> result = apiSendOrder.sendOrder(body);
        result.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response)
            {
                Toast.makeText(CartSummaryActivity.this, "Order Send Successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t)
            {
                Toast.makeText(CartSummaryActivity.this, "Failed to send image message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getProductDescription(ArrayList<ProductItem> productItems){
        StringBuilder productDescription= new StringBuilder();

        for (int i = 0; i<productItems.size();i++){
            if(i == 0){
                productDescription.append(productItems.get(i).getName());
            }
            else{
                productDescription.append(",").append(productItems.get(i).getName());
            }
        }

        return productDescription.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_summary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_check:
                Toast.makeText(this, "Order Done", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this, ShippingAndBillingAddressActivity.class) );
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
