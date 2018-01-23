package com.looploop.testint20h.ui;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.looploop.testint20h.R;
import com.looploop.testint20h.network.Requester;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {
    private Requester mRequester;
    private Callback<JsonObject> chartCallback;

    private LineChart chart;
    private List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_chart);

        chart = findViewById(R.id.chart);
        mRequester = new Requester();

        entries = new ArrayList<>();

        chartCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                JsonArray dataArray = object.getAsJsonArray("values");

                for (int i = 0; i < dataArray.size(); i++) {
                    JsonObject pricePoint = dataArray.get(i).getAsJsonObject();

                    int x = pricePoint.get("x").getAsInt();
                    //entry.setValueX(x);

                    Date time = new Date((long) x * 1000);

                    double y = pricePoint.get("y").getAsDouble();
                    //entry.setValueY(y);

                    entries.add(new Entry( (float) x, (float) y));
                    System.out.println("Unix time: " + x + "\t Date format: " + time.toString() + "\t Price in USD: " + y);

                    setUpChart();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getChartData("https://blockchain.info/charts/market-price?timespan=180days&format=json", chartCallback);



    }

    private void setUpChart() {
        LineDataSet dataSet = new LineDataSet(entries, "Currency rate");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }


}
