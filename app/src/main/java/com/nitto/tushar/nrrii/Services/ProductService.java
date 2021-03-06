package com.nitto.tushar.nrrii.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nitto.tushar.nrrii.Activity.DressViewActivity;
import com.nitto.tushar.nrrii.Entity.Dress;
import com.nitto.tushar.nrrii.Entity.ProductItem;
import com.nitto.tushar.nrrii.Network.ApiCalls.ApiSearchProduct;
import com.nitto.tushar.nrrii.Network.RetrofitInstance;
import com.nitto.tushar.nrrii.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductService {
    private ProductItem productItem;
    private ArrayList<Dress> dressItems;
    private Dress dress;
    private UpdateUIOnProductRetrieveListener updateUIOnProductRetrieveListener;
    private String consumer_key = "ck_c8a5753c20021f7f009633b32eaec7be5535b025", consumer_secret = "cs_4700d25eb3d82d4bfeafa1c0d7a785da797d15af";

    private static ProductService productService = new ProductService();

    public static ProductService getInstance() {
        if(productService==null){
            Class clazz = ProductService.class;
            synchronized (clazz){
                productService = new ProductService();
            }
        }

        return productService;
    }

    public interface UpdateUIOnProductRetrieveListener{
        void onProductRetrieve(ArrayList<Dress> dressArrayList);
    }

    public void AddUpdateUIOnProductRetrieveListener(UpdateUIOnProductRetrieveListener listener){
        this.updateUIOnProductRetrieveListener = listener;
    }

    public void RemoveUpdateUIOnProductRetrieveListener(){
        this.updateUIOnProductRetrieveListener = null;
    }

    public void getProductItemsFromServer(final int quantity, final Context context) {


        ApiSearchProduct ApiSearchProduct = RetrofitInstance.getInstance().create(ApiSearchProduct.class);

        Call<List<ProductItem>> result;

        result = ApiSearchProduct.searchProductByCategory(quantity,consumer_key,consumer_secret);

        result.enqueue(new Callback<List<ProductItem>>()
        {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response)
            {
                List<ProductItem> productItems = response.body();
                ArrayList<Dress> tmpDressList = new ArrayList<>();
                Random r = new Random();


                for (int i = 0; i < productItems.size(); i++){

                    Dress dress = new Dress();
                    if(String.valueOf(productItems.get(i).getUid()).isEmpty()){
                        long randomNumber = r.nextInt(20000);
                        dress.setDressId(String.valueOf(randomNumber));
                    }
                    else {
                        dress.setDressId(String.valueOf(productItems.get(i).getUid()));
                    }

                    if(productItems.get(i).getName().isEmpty()){
                        dress.setDressTitle("");
                    }
                    else {
                        dress.setDressTitle(productItems.get(i).getName());
                    }

                    if(productItems.get(i).getImages().isEmpty()){
                        dress.setImages(null);
                    }
                    else {
                        dress.setImages(productItems.get(i).getImages());
                    }

                    if(String.valueOf(productItems.get(i).getPrice()).isEmpty()){
                        dress.setActualPrice("");
                    }
                    else {
                        dress.setActualPrice(String.valueOf(productItems.get(i).getPrice()));
                    }

                    if(productItems.get(i).getProductDescription().isEmpty()){
                        dress.setDressDetails("");
                    }
                    else {
                        dress.setDressDetails(productItems.get(i).getProductDescription());
                    }

                    dressItems.add(dress);
                    tmpDressList.add(dress);
                }

                    updateViewOnProductRetrieve(tmpDressList);

                Toast.makeText(context, "Api called Successfully ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t)
            {
                Toast.makeText(context, "Failed to call", Toast.LENGTH_SHORT).show();
                //Log.d("tushar", "Failed to call");
            }
        });
    }

    private void updateViewOnProductRetrieve(ArrayList<Dress> dressItems) {
        this.updateUIOnProductRetrieveListener.onProductRetrieve(dressItems);
    }

    // Constructor
    private ProductService(){
//        // all class related initialization goes here
//        this.orderItemsList = new ArrayList<>();
    }

    public void InitializeProductService() {
        this.productItem = null;
        this.dressItems = new ArrayList<>();
    }

    public void insertProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ProductItem getInsertedProductItem(){
        return this.productItem;
    }

    public ArrayList<Dress> getAllDress(){

        return this.dressItems;
    }

    public void setDressForDetails(Dress dress){
        this.dress = new Dress();

        this.dress.setDressId(dress.getDressId());
        this.dress.setDressTitle(dress.getDressTitle());
        this.dress.setDressDetails(dress.getDressDetails());
        this.dress.setActualPrice(dress.getActualPrice());
        this.dress.setImages(dress.getImages());
    }

    public Dress getDressDetails(){
        return this.dress;
    }

}
