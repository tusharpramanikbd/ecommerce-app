package com.nitto.tushar.nrrii.Network.ApiCalls;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by kpit on 8/10/18.
 */


public interface ApiSendOrder {
    @POST("order")
    Call<ResponseBody> sendOrder(@Body RequestBody order);
}
