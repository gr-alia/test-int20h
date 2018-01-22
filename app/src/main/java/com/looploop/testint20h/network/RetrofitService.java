package com.looploop.testint20h.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitService instance;

    private ExchangeApi serviceImpl;

    private RetrofitService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kraken.com/")     //url doesn't matter here
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        serviceImpl = retrofit.create(ExchangeApi.class);
    }

    public static void initInstance() {
        instance = new RetrofitService();
    }
    public static RetrofitService getInstance() {
        return instance;
    }
    public ExchangeApi getApi(){
        return serviceImpl;
    }

}
