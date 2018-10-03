package com.nitto.tushar.nrrii.Network.ApiCalls;



import com.nitto.tushar.nrrii.Entity.ProductItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiSearchProduct {

    @GET("search")
    Call<List<ProductItem>> searchProductByCategory(
            @Query("qstr") String searchTerms,
            @Query("fordocatid") int fordocatid,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("search")
    Call<List<ProductItem>> searchProduct(
            @Query("qstr") String searchTerms,
            @Query("offset") int offset,
            @Query("limit") int limit);
    
}
