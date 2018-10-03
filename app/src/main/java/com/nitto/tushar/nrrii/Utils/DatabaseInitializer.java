package com.nitto.tushar.nrrii.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;


import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Entity.ProductItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by masud on 2/17/2018.
 */

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static ProductItem addProduct(final AppDatabase db, ProductItem productItem) {
        db.productDao().insertAll(productItem);
        return productItem;
    }

    private static ProductItem getProductByUID(AppDatabase db, int id) {
        ProductItem user = db.productDao().getProductByUID(id);
        Log.d(DatabaseInitializer.TAG, "test data: " + user.getName());
        return user;
    }

    public static void populateWithTestData(AppDatabase db) {
        ProductItem productItem = new ProductItem();
        productItem.setName("Iphone");
        productItem.setProductDescription("This is the invention of Apple Inc");
        productItem.setPrice(5000);
        addProduct(db, productItem);
        List<ProductItem> productsList = db.productDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + productsList.size());
    }

    public static String getProductNameByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = getProductByUID(appDatabase, id);
        return productItem.getName();
    }

    public static String getProductDesciptionByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = getProductByUID(appDatabase, id);
        return productItem.getProductDescription();
    }

    public static double getProductPriceByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = getProductByUID(appDatabase, id);
        return productItem.getPrice();
    }

    public static int getProductRatingByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = getProductByUID(appDatabase, id);
        return productItem.getRating();
    }

    public static String getProductImageByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = getProductByUID(appDatabase, id);
        return productItem.getProductImage();
    }

    public static ProductItem getProductItemByID(AppDatabase appDatabase, int id) {
        ProductItem productItem = appDatabase.productDao().getProductByUID(id);
        return productItem;
    }

    public static void InsertDefaultProductsInDB(Context context, AppDatabase db) {
        String defaultProductsJSON = loadJSONFromAsset(context);
        ProductItem[] productItems = getProductsFromJson(defaultProductsJSON);
        db.productDao().insertAll(productItems);
    }

    public static List<ProductItem> getAllProductItems(AppDatabase db) {
        List<ProductItem> tmp =db.productDao().getAll();
        return tmp;
    }

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("default_product_items.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static ProductItem[] getProductsFromJson(String productsAsJson) {
        ProductItem[] productItems = null;
        try {
            JSONObject obj = null;
            try {
                obj = new JSONObject(productsAsJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray productsJsonArray = obj.getJSONArray("product_items");
            productItems = new ProductItem[productsJsonArray.length()];

            for (int i=0;i<productsJsonArray.length();i++) {
                JSONObject innerJsonObject = productsJsonArray.getJSONObject(i);
                String name = innerJsonObject.getString("name");
                String description = innerJsonObject.getString("product_description");
                Double price = innerJsonObject.getDouble("price");
                Integer rating = innerJsonObject.getInt("rating");
                String image = innerJsonObject.getString("image");
                int quantity = innerJsonObject.getInt("quantity");

                ProductItem productItem = new ProductItem();
                productItem.setName(name);
                productItem.setProductDescription(description);
                productItem.setPrice(price);
                productItem.setRating(rating);
                productItem.setImage(image);
                productItem.setQuantity(quantity);
                productItems[i] = productItem;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productItems;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }
        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }
}

