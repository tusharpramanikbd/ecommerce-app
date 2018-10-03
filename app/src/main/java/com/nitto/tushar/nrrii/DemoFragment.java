package com.nitto.tushar.nrrii;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Network.ApiCalls.ApiSearchProduct;
import com.nitto.tushar.nrrii.Network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoFragment extends Fragment {
    private String pageTitle;
    private List<ProductItem> productItems;
    private RecyclerView rvProductItems;
    private ProductItemAdapter adapter;

    boolean isScrolling = false;
    int currentItems, totalItems, scrolledItems;
    GridLayoutManager manager;
    ProgressBar progressBar;
    //protected boolean isVisible;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.productItems = new ArrayList<>();

        int position = FragmentPagerItem.getPosition(getArguments());
        this.adapter = new ProductItemAdapter(getContext(), this.productItems);
        this.rvProductItems = view.findViewById(R.id.rvProductItems);

        this.progressBar = view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext(), 2);
        this.rvProductItems.setLayoutManager(manager);
        this.rvProductItems.setAdapter(adapter);


        this.populateProductItems();

        //new code
        this.rvProductItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrolledItems == totalItems)){
                    progressBar.setVisibility(View.VISIBLE);
                    int previousProductListSize = productItems.size();
                    getProductItemsFromServer(getCategoryNumber(GetPageTitle()),previousProductListSize);
                    progressBar.setVisibility(View.GONE);
                    isScrolling = false;
                }

            }
        });
    }

    private void populateProductItems() {

        int numberAgainstCategory = getCategoryNumber(GetPageTitle());

        ApiSearchProduct ApiSearchProduct = RetrofitInstance.getInstance().create(ApiSearchProduct.class);

        Call<List<ProductItem>> result;

        if (numberAgainstCategory == 0){
            result = ApiSearchProduct.searchProduct("",0,10);
        }
        else {
            result = ApiSearchProduct.searchProductByCategory("",numberAgainstCategory,0,10);

        }
        result.enqueue(new Callback<List<ProductItem>>()
        {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response)
            {
                List<ProductItem> productItems = response.body();
                //Toast.makeText(getContext(), "Api called Successfully ", Toast.LENGTH_SHORT).show();
                updateUIWithProductItems(productItems);
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t)
            {
                //Toast.makeText(getContext(), "Failed to call", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUIWithProductItems(List<ProductItem> productItems) {
        this.productItems.addAll(productItems);
        this.adapter.notifyDataSetChanged();
    }

    public String GetPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    private static int getCategoryNumber(String type){
        int numberAgainstCategory = 0;
        switch (type) {
            case "ALL ITEMS":
                numberAgainstCategory = 0;
                break;
            case "VEG AND FRUITS":
                numberAgainstCategory = 1;
                break;
            case "FISH AND MEAT":
                numberAgainstCategory = 2;
                break;
            case "GROCERIES":
                numberAgainstCategory = 3;
                break;
            case "MEAL":
                numberAgainstCategory = 4;
                break;
            case "MEDICINE":
                numberAgainstCategory = 5;
                break;
            case "TRAVEL":
                numberAgainstCategory = 6;
                break;
            case "UTILITIES":
                numberAgainstCategory = 7;
                break;
            case "EDUCATION":
                numberAgainstCategory = 8;
                break;
            case "FASHION":
                numberAgainstCategory = 10;
                break;
            case "MISC":
                numberAgainstCategory = 9;
                break;
        }
        return numberAgainstCategory;
    }

    private void getProductItemsFromServer(int categoryNumber, int previousProductListSize){

        ApiSearchProduct ApiSearchProduct = RetrofitInstance.getInstance().create(ApiSearchProduct.class);
        Call<List<ProductItem>> result;
        if (categoryNumber == 0){
            result = ApiSearchProduct.searchProduct("",previousProductListSize,10);
        }
        else {
            result = ApiSearchProduct.searchProductByCategory("",categoryNumber,previousProductListSize,10);
        }
        result.enqueue(new Callback<List<ProductItem>>()
        {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response)
            {
                List<ProductItem> newLoadedProductItems = response.body();
                //Toast.makeText(getContext(), "Image Message Send Successfully", Toast.LENGTH_SHORT).show();
                updateUIWithProductItems(newLoadedProductItems);
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t)
            {
                //Toast.makeText(getContext(), "Failed To Send", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()) {
//            isVisible = true;
//            //this.productItems.clear();
//            //populateProductItems();
//        } else {
//            isVisible = false;
//            //onInvisible();
//        }
//    }
}
