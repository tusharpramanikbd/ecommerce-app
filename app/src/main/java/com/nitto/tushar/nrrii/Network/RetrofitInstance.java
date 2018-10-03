package com.nitto.tushar.nrrii.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kpit on 3/7/18.
 */

public class RetrofitInstance {
    private static final String BASE_URL = "http://52.40.71.120/api/";
//    public static final String BASE_URL = "http://192.168.0.109:5000/api/";

    private static Retrofit retrofit;

//    public static Retrofit getInstance() {
//        if(retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

    public static Retrofit getInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100, TimeUnit.SECONDS)
//                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
