package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterCategory;
import com.nitto.tushar.nrrii.Adapter.RecyclerViewAdapterDress;
import com.nitto.tushar.nrrii.R;

public class ProductCategoryActivity extends AppCompatActivity {

    private RelativeLayout layoutCat1;

    private int[] images = {R.mipmap.cat_1, R.mipmap.cat_2, R.mipmap.cat_3, R.mipmap.cat_4};
    private String[] categoryNames = {"Category Name 1", "Category Name 2", "Category Name 3", "Category Name 4",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCategory);
        RecyclerViewAdapterCategory myAdapter = new RecyclerViewAdapterCategory(this,images,categoryNames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
