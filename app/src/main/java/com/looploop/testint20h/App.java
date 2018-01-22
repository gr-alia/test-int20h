package com.looploop.testint20h;

import android.app.Application;

import com.looploop.testint20h.network.RetrofitService;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.initInstance();
    }


}
