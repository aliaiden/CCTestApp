package com.alihaider.myapp.retro;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {
    @GET("forecast")
    Call<JsonElement> userSignIn(@Query("q") String cityCountry, @Query("appid") String appID);

}
