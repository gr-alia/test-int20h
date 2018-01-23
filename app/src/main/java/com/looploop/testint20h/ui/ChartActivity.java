package com.looploop.testint20h.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.looploop.testint20h.R;
import com.looploop.testint20h.model.Entry;
import com.looploop.testint20h.network.Requester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    private Requester mRequester;

    private LineChart chart;
    private List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart = findViewById(R.id.chart);
        mRequester = new Requester();

      //  mRequester.getChartData("https://blockchain.info/charts/market-price?format=json", );

        entries = new ArrayList<>();

    }




}
