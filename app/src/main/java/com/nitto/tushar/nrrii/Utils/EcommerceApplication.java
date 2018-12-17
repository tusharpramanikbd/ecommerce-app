package com.nitto.tushar.nrrii.Utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import com.nitto.tushar.nrrii.Database.AppDatabase;
import com.nitto.tushar.nrrii.Services.CartService;
import com.nitto.tushar.nrrii.Services.OrderService;
import com.nitto.tushar.nrrii.Services.ProductService;


/**
 * Created by masud on 2/23/2018.
 */

public class EcommerceApplication extends Application {
    static{
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    //public static final String Name = "isFirstRun";

    @Override
    public void onCreate() {
        super.onCreate();
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("com.example.masud.ecommerce.tushar", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();

        AppDatabase.AppDatabaseInitializer(getApplicationContext());
        CartService.InitCart(getApplicationContext());
        OrderService.getInstance().InitializeOrderService();
        CartService.getInstance().InitializeOrderService();
        ProductService.getInstance().InitializeProductService();

        SharedPreferences.Editor editor = getSharedPreferences("shipping_methods", MODE_PRIVATE).edit();
        editor.putBoolean("isSameAsShippingAddress", false);
        editor.putString("shipping_address", null);
        editor.putString("billing_address", null);
        editor.putString("delivery_method", null);
        editor.putString("payment_method", null);
        editor.commit();


//        boolean isFirstRun = pref.getBoolean(Name, true);
//        if (isFirstRun) {
//            AppDatabase appDatabase = AppDatabase.getInstance();
//            DatabaseInitializer.InsertDefaultProductsInDB(getApplicationContext(), appDatabase);
//            editor.putBoolean(Name, false);
//            editor.apply();
//        }
    }
}