package com.looploop.testint20h.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.looploop.testint20h.R;
import com.looploop.testint20h.model.Ask;
import com.looploop.testint20h.model.Bid;
import com.looploop.testint20h.model.ExmoOrders;
import com.looploop.testint20h.model.KrakenOrders;
import com.looploop.testint20h.model.YobitOrders;
import com.looploop.testint20h.network.Requester;
import com.looploop.testint20h.utils.Const;
import com.google.gson.JsonObject;
import com.looploop.testint20h.utils.Exchanger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Requester mRequester;
    private Callback<JsonObject> krakenCallback;
    private Callback<JsonObject> yobitCallback;
    private Callback<JsonObject> exmoCallback;
    private YobitOrders yobitOrders;
    private ExmoOrders exmoOrders;
    private KrakenOrders krakenOrders;


    private EditText usdCountEt;
    private TextView resultTv;
    private Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usdCountEt = findViewById(R.id.usd_count);
        enterBtn = findViewById(R.id.enter_btn);
        resultTv = findViewById(R.id.result_txt);

        yobitOrders = new YobitOrders();
        exmoOrders = new ExmoOrders();
        krakenOrders = new KrakenOrders();

        mRequester = new Requester();

        yobitCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body yobit: " + object.toString());

                JsonObject btcUsd = object.getAsJsonObject("btc_usd");
                Log.d(TAG, "BtcUsd yobit: " + btcUsd.toString());

                JsonArray asks = btcUsd.getAsJsonArray("asks");
                Log.d(TAG, "Asks yobit: " + asks.toString());

                JsonArray bids = btcUsd.getAsJsonArray("bids");
                Log.d(TAG, "bids yobit: " + bids.toString());
                List<Ask> asksList = new ArrayList<>();
                for (int i = 0; i < asks.size(); i++) {
                    Ask ask = new Ask();

                    JsonArray askOrder = asks.get(i).getAsJsonArray();
                    Log.d(TAG, "askOrder yobit: " + askOrder.toString());
                    Double price = askOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = askOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    ask.setPrice(price);
                    ask.setVolume(volume);
                    asksList.add(ask);

                }
                yobitOrders.setAsks(asksList);
                List<Bid> bidsList = new ArrayList<>();
                for (int i = 0; i < bids.size(); i++) {
                    Bid bid = new Bid();

                    JsonArray bidOrder = bids.get(i).getAsJsonArray();
                    Log.d(TAG, "bidOrder yobit: " + bidOrder.toString());
                    Double price = bidOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = bidOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    bid.setPrice(price);
                    bid.setVolume(volume);
                    bidsList.add(bid);


                }
                yobitOrders.setBids(bidsList);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.YOBIT_URL, yobitCallback);

        krakenCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body kraken: " + object.toString());

                JsonObject result = object.getAsJsonObject("result");
                Log.d(TAG, "BtcUsd kraken: " + result.toString());

                JsonObject btcUsd = result.getAsJsonObject("XXBTZUSD");
                Log.d(TAG, "BtcUsd kraken: " + btcUsd.toString());

                JsonArray asks = btcUsd.getAsJsonArray("asks");
                Log.d(TAG, "Asks kraken: " + asks.toString());

                JsonArray bids = btcUsd.getAsJsonArray("bids");
                Log.d(TAG, "bids kraken: " + bids.toString());

                List<Ask> asksList = new ArrayList<>();
                for (int i = 0; i < asks.size(); i++) {
                    Ask ask = new Ask();

                    JsonArray askOrder = asks.get(i).getAsJsonArray();
                    Log.d(TAG, "askOrder kraken: " + askOrder.toString());
                    Double price = askOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = askOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    ask.setPrice(price);
                    ask.setVolume(volume);
                    asksList.add(ask);

                }
                krakenOrders.setAsks(asksList);

                List<Bid> bidsList = new ArrayList<>();
                for (int i = 0; i < bids.size(); i++) {
                    Bid bid = new Bid();

                    JsonArray bidOrder = bids.get(i).getAsJsonArray();
                    Log.d(TAG, "bidOrder kraken: " + bidOrder.toString());
                    Double price = bidOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = bidOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    bid.setPrice(price);
                    bid.setVolume(volume);
                    bidsList.add(bid);

                }
                krakenOrders.setBids(bidsList);


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.KRAKEN_URL, krakenCallback);

        exmoCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                Log.d(TAG, "Body exmo: " + object.toString());

                JsonObject btcUsd = object.getAsJsonObject("BTC_USD");
                Log.d(TAG, "BtcUsd exmo: " + btcUsd.toString());

                JsonArray asks = btcUsd.getAsJsonArray("ask");
                Log.d(TAG, "Asks exmo: " + asks.toString());

                JsonArray bids = btcUsd.getAsJsonArray("bid");
                Log.d(TAG, "bids exmo: " + bids.toString());

                List<Ask> asksList = new ArrayList<>();
                for (int i = 0; i < asks.size(); i++) {
                    Ask ask = new Ask();

                    JsonArray askOrder = asks.get(i).getAsJsonArray();
                    Log.d(TAG, "askOrder exmo: " + askOrder.toString());
                    Double price = askOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = askOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    ask.setPrice(price);
                    ask.setVolume(volume);
                    asksList.add(ask);

                }
                exmoOrders.setAsks(asksList);

                List<Bid> bidsList = new ArrayList<>();
                for (int i = 0; i < bids.size(); i++) {
                    Bid bid = new Bid();

                    JsonArray bidOrder = bids.get(i).getAsJsonArray();
                    Log.d(TAG, "bidOrder exmo: " + bidOrder.toString());
                    Double price = bidOrder.get(0).getAsDouble();
                    Log.d(TAG, String.valueOf(price));
                    Double volume = bidOrder.get(1).getAsDouble();
                    Log.d(TAG, String.valueOf(volume));
                    bid.setPrice(price);
                    bid.setVolume(volume);
                    bidsList.add(bid);

                }
                exmoOrders.setBids(bidsList);


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        };

        mRequester.getOrderBook(Const.EXMO_URL, exmoCallback);


        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTv.setText(exchange());
            }
        });

    }


    private String exchange() {

        String result = "The best way is ";

        //спершу шукаємо де будуть мінімальні затрати
        double expensesInYobit = Exchanger.getYobitExpenses(yobitOrders.getAsks());
        double expensesInExmo = Exchanger.getExmoExpenses(exmoOrders.getAsks());
        double expensesInKraken = Exchanger.getKrakenExpenses(krakenOrders.getAsks());

        //тепер скільки з цими затратами ми матимемо біткойнів після виводу
        double cryptoAmountInYobit = Exchanger.getYobitCryptoWithdraw(1);
        double cryptoAmountInExmo = Exchanger.getExmoCryptoWithdraw(1);
        double cryptoAmountInKraken = Exchanger.getKrakenCryptoWithdraw(1);

        double bestTradeProfit;

        //знайшли обмінник, на якому найдешевше купити криптовалюту, тепер дивимось де її найдорожче продати
        double tradeYobitExmoProfit = Exchanger.getExmoProfit(exmoOrders.getBids(), cryptoAmountInYobit) - expensesInYobit;
        double tradeYobitKrakenProfit = Exchanger.getKrakenProfit(krakenOrders.getBids(), cryptoAmountInYobit) - expensesInYobit;
        //How about sell on the same service?
        if (tradeYobitExmoProfit >= tradeYobitKrakenProfit) {
            bestTradeProfit = tradeYobitExmoProfit;
            result = "buy in Yobit, sell in Exmo";
        } else {
            bestTradeProfit = tradeYobitKrakenProfit;
            result = "buy in Yobit, sell in Kraken";
        }

        double tradeExmoYobitProfit = Exchanger.getYobitProfit(yobitOrders.getBids(), cryptoAmountInExmo) - expensesInExmo;
        double tradeExmoKrakenProfit = Exchanger.getKrakenProfit(krakenOrders.getBids(), cryptoAmountInExmo) - expensesInExmo;
        double bestExmoProfit;
        String exmoResult;
        if (tradeExmoYobitProfit >= tradeExmoKrakenProfit) {
            bestExmoProfit = tradeExmoYobitProfit;
            exmoResult = "buy in Exmo, sell in Yobit";
        } else {
            bestExmoProfit = tradeExmoKrakenProfit;
            exmoResult = "buy in Exmo, sell in Kraken";
        }
        if (bestExmoProfit >= bestTradeProfit) {
            bestTradeProfit = bestExmoProfit;
            result = exmoResult;
        }

        double tradeKrakenYobitProfit = Exchanger.getYobitProfit(yobitOrders.getBids(), cryptoAmountInKraken) - expensesInKraken;
        double tradeKrakenExmoProfit = Exchanger.getExmoProfit(exmoOrders.getBids(), cryptoAmountInKraken) - expensesInKraken;
        double bestKrakenProfit;
        String krakenResult;
        if (tradeKrakenYobitProfit >= tradeKrakenExmoProfit) {
            bestKrakenProfit = tradeKrakenYobitProfit;
            krakenResult = "buy in Kraken, sell in Yobit";
        } else {
            bestKrakenProfit = tradeKrakenExmoProfit;
            krakenResult = "buy in Kraken, sell in Exmo";
        }
        if (bestKrakenProfit >= bestTradeProfit) {
            bestTradeProfit = bestKrakenProfit;
            result = krakenResult;
        }

        result += ", profit is " + Double.toString(bestTradeProfit);

        return result;
    }


}
