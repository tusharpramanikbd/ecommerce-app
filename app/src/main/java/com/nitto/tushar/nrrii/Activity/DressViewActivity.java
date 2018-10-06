package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapter;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;

public class DressViewActivity extends AppCompatActivity {

    private ArrayList<Dress> dressArrayList;
    private AppCompatButton btn_berger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_view);

        btn_berger = findViewById(R.id.btn_berger);
        btn_berger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressViewActivity.this, MenuItemsActivity.class) );
            }
        });

        dressArrayList = new ArrayList<>();
        dressArrayList.add(new Dress("5000", "4000"));
        dressArrayList.add(new Dress("6000", "5000"));
        dressArrayList.add(new Dress("7000", "5000"));
        dressArrayList.add(new Dress("8000", "4000"));
        dressArrayList.add(new Dress("9000", "4000"));
        dressArrayList.add(new Dress("9000", "8000"));
        dressArrayList.add(new Dress("8000", "7000"));
        dressArrayList.add(new Dress("7000", "7000"));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDress);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,dressArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(myAdapter);
    }
}
