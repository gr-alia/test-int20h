package com.looploop.testint20h.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.looploop.testint20h.R;
import com.looploop.testint20h.network.Requester;
import com.looploop.testint20h.utils.Const;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Requester mRequester;
    private Callback<JsonObject> krakenCallback;
    private Callback<JsonObject> yobitCallback;
    private Callback<JsonObject> exmoCallback;
    private String usdCount;
    private EditText usdCountEt;
    private Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usdCountEt = findViewById(R.id.usd_count);
        enterBtn = findViewById(R.id.enter_btn);

        usdCount = usdCountEt.getText().toString();

        mRequester = new Requester();

        krakenCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body kraken: " + object.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.KRAKEN_URL, krakenCallback);

        yobitCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body yobit: " + object.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.YOBIT_URL, yobitCallback);

        exmoCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body exmo: " + object.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.EXMO_URL, exmoCallback);


    }

}
