package com.looploop.testint20h.network;

import retrofit2.Callback;

public class Requester {
    private static final String TAG = "Requester";
    private ExchangeApi apiInstance;

    public Requester() {
        apiInstance = RetrofitService.getInstance().getApi();
    }

    public void getOrderBook(String apiUrl, Callback callback) {
        apiInstance.getOrderBook(apiUrl).enqueue(callback);


    }
    public void getChartData(String apiUrl, Callback callback) {
        apiInstance.getChartData(apiUrl).enqueue(callback);


    }
}
