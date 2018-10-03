package com.nitto.tushar.nrrii;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Services.CartService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;

public class CartActivity extends AppCompatActivity implements CartService.OnUpdateUIListener {

    private List<ProductItem> productItems;
    private CartItemListAdapter cartItemListAdapter;
    private ListView lvCartItems;

    private AppCompatCheckBox cbCourrierMethodDHL, cbCourrierMethodUKpost;
    private View llPaymentMethodMasterCard, llPaymentMethodPayPal;
    private AppCompatTextView tvTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cart");
        initializeUI();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeUI() {

        CartService.getInstance().AddOnUpdateUIListener(this);

        this.productItems = new ArrayList<>();

        this.lvCartItems = findViewById(R.id.lvCartItems);
        this.lvCartItems.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
//        this.cbCourrierMethodDHL = findViewById(R.id.cbCourrierMethodDHL);
//        this.cbCourrierMethodUKpost = findViewById(R.id.cbCourrierMethodUKpost);
//        this.llPaymentMethodMasterCard = findViewById(R.id.llPaymentMethodMasterCard);
//        this.llPaymentMethodPayPal = findViewById(R.id.llPaymentMethodPayPal);
        this.tvTotalAmount = findViewById(R.id.tvTotalAmount);

        this.cartItemListAdapter = new CartItemListAdapter(this, this.productItems);
        this.cartItemListAdapter.SetOnDeleteCartItemListener(new CartItemListAdapter.OnDeleteCartItemListener() {
            @Override
            public void onDeleteCartItem(int productUID) {
                new DeleteCartItem(productUID).execute();
            }
        });

        this.lvCartItems.setAdapter(cartItemListAdapter);
        getCartItems();
        setListViewHeightBasedOnChildren(this.lvCartItems);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_check:
                startActivity(new Intent(this, ShippingAndBillingAddressActivity.class) );
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onItemPriceUpdated(double totalPriceBeforeChange) {
        this.tvTotalAmount.setText(String.valueOf(totalPriceBeforeChange));
    }

    @SuppressLint("StaticFieldLeak")
    class DeleteCartItem extends AsyncTask<Void, Void, Void> {
        private int uid;

        DeleteCartItem(int uid) {
            this.uid = uid;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            CartService.getInstance().deleteCartItem(uid);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    productItems.clear();
                    productItems.addAll(CartService.getInstance().getProductCart());
                    cartItemListAdapter.notifyDataSetChanged();
                    refreshTotalPriceInUI();
                    setListViewHeightBasedOnChildren(lvCartItems);
                }
            });
        }
    }

    private void refreshTotalPriceInUI() {
        this.tvTotalAmount.setText(String.valueOf(CartService.getInstance().getTotalPrice()));
    }

    @Override
    protected void onDestroy() {
        CartService.getInstance().RemoveOnUpdateUIListener(this);
        super.onDestroy();
    }
}
