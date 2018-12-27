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
    @POST("orders?per_page=50&consumer_key=ck_c8a5753c20021f7f009633b32eaec7be5535b025&consumer_secret=cs_4700d25eb3d82d4bfeafa1c0d7a785da797d15af")
    Call<ResponseBody> sendOrder(@Body RequestBody order);
}
