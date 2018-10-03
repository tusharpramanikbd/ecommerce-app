package com.nitto.tushar.nrrii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Services.OrderService;

public class OrderDetailsActivity extends AppCompatActivity {

    AppCompatTextView tv_order_number, tv_order_id,tv_order_description,tv_order_price,tv_order_quantity,tv_order_date,tv_order_status,tv_shop_id,tv_shop_name,tv_delivered_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initializeUI();
    }


    private void initializeUI(){
        tv_order_number = findViewById(R.id.tv_order_number);
        tv_order_id = findViewById(R.id.tv_order_id);
        tv_order_description = findViewById(R.id.tv_order_description);
        tv_order_price = findViewById(R.id.tv_order_price);
        tv_order_quantity = findViewById(R.id.tv_order_quantity);
        tv_order_date = findViewById(R.id.tv_order_date);
        tv_order_status = findViewById(R.id.tv_order_status);
        tv_shop_id = findViewById(R.id.tv_shop_id);
        tv_shop_name = findViewById(R.id.tv_shop_name);
        tv_delivered_to = findViewById(R.id.tv_delivered_to);

        setTextViewData();
    }

    private void setTextViewData(){
        OrderItem orderItem = OrderService.getInstance().getTmpOrderForDetails();

        tv_order_number.setText(orderItem.getOrderNumber());
        tv_order_id.setText(String.valueOf(orderItem.getOid()));
        tv_order_description.setText(orderItem.getOrderDescription());
        tv_order_price.setText(String.valueOf(orderItem.getOrderPrice()));
        tv_order_quantity.setText(String.valueOf(orderItem.getQuantity()));

        String tmpDate = orderItem.getOrderDate();
        tmpDate = tmpDate.substring(0,10)+" "+tmpDate.substring(30,34);

        tv_order_date.setText(tmpDate);
        if(orderItem.isOrderComplete()){
            tv_order_status.setText("Complete");
        }else {
            tv_order_status.setText("Pending");
        }
        tv_shop_id.setText(orderItem.getShopId());
        tv_shop_name.setText(orderItem.getShopName());
        tv_delivered_to.setText(orderItem.getDeliveredTo());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
