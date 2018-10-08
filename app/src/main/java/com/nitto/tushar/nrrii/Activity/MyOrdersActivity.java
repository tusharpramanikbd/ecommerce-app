package com.nitto.tushar.nrrii.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.OrderItem;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterOrder;
import com.nitto.tushar.nrrii.R;
import com.nitto.tushar.nrrii.Services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity implements OrderService.OnUpdateUIListener{


    ArrayList<OrderItem> orderItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapterOrder myRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order List");

        initializeUI();
    }

    public void initializeUI(){
        OrderService.getInstance().AddOnUpdateUIListener(this);
        //orderItems = OrderService.getInstance().getAllOrderByDeliveredTo("01746941437");
//        if(orderItems != null){
//            OrderItem orderItem = new OrderItem();
//            orderItem.setDeliveredTo("01746941437");
//            orderItem.setOrderComplete(false);
//            orderItem.setOrderDate(Calendar.getInstance().getTime().toString());
//            orderItem.setOrderDescription("Hijibiji");
//            orderItem.setOrderPrice(100);
//            orderItem.setQuantity(2);
//            orderItem.setOrderNumber("1");
//            orderItem.setShopId("420");
//            orderItem.setShopName("Fordo Shop");
//            orderItems.add(orderItem);
        orderItems.add(new OrderItem(2, "2/5/18", true));
        orderItems.add(new OrderItem(1, "8/8/18", false));
        orderItems.add(new OrderItem(3, "22/6/18", true));
        orderItems.add(new OrderItem(5, "2/8/18", false));
        orderItems.add(new OrderItem(2, "6/6/18", true));
        orderItems.add(new OrderItem(3, "5/5/18", true));
            initRecyclerView();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                OrderService.getInstance().deleteAllOrderFromDb();
                //myRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return true;
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        myRecyclerViewAdapter = new RecyclerViewAdapterOrder(this, orderItems);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onItemInserted(List<OrderItem> orderItemList) {
        this.orderItems.clear();
        this.orderItems.addAll(orderItemList);
        this.myRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemUpdated(List<OrderItem> orderItemList) {
        this.orderItems.clear();
        this.orderItems.addAll(orderItemList);
        this.myRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleted(List<OrderItem> orderItemList) {
        this.orderItems.clear();
        this.orderItems.addAll(orderItemList);
        this.myRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        OrderService.getInstance().RemoveOnUpdateUIListener(this);
        super.onDestroy();
    }
}
