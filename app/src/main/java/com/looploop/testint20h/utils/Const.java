package com.looploop.testint20h.utils;

public class Const {
    public static final String KRAKEN_URL = "https://api.kraken.com/0/public/Depth?pair=XBTUSD";
    public static final String YOBIT_URL = "https://yobit.io/api/3/depth/btc_usd?limit=100";
    public static final String EXMO_URL = "https://api.exmo.com/v1/order_book/?pair=BTC_USD";

    public static final double YOBIT_CRYPTO_WITHDRAW_FEE = 0.0005;
    public static final double YOBIT_USD_WITHDRAW_FEE = 0.00; //якщо віза/мастеркард, інашке 0.03

    public static final double KRAKEN_MONEY_INPUT_FEE = 10.0; //це не коефіцієнт
    public static final double KRAKEN_CRYPTO_WITHDRAW_FEE = 0.001;
    public static final double KRAKEN_USD_WITHDRAW_FEE = 60.0; //це не коефіцієнт
    public static final double KRAKEN_OPEN_FEE = 0.00005;

    public static final double EXMO_MONEY_INPUT_FEE = 0.000; //якщо віза/мастеркард, інакше в середньому там 0.03
    public static final double EXMO_CRYPTO_WITHDRAW_FEE = 0.001;
    public static final double EXMO_TRADING_FEE = 0.002;
    public static final double EXMO_USD_WITHDRAW_FEE = 0.03; //якщо віза/мастеркард, інакше в середньому там 0.02

}
