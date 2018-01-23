package com.looploop.testint20h.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Alyona on 22.01.2018.
 */

public interface ExchangeApi {
    @GET
    Call<JsonObject> getOrderBook(@Url String apiUrl);

    @GET
    Call<JsonObject> getChartData(@Url String apiUrl);
}
