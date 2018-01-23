package com.looploop.testint20h.utils;

import com.looploop.testint20h.model.Ask;
import com.looploop.testint20h.model.Bid;

import java.util.List;

/**
 * Created by Alyona on 23.01.2018.
 */

public class Exchanger {
      /*--------------------YOBIT----------------*/

    //input - asks, output - min price of 1 btc
    //приймає пропозиції продажу, повертає скільки грошей ми витратимо на покупку 1 біткойну на цьому сервісі
    //комісія на введення грошей тут не береться
    public static double getYobitExpenses(List<Ask> asks){
        double bestBuyOffer = asks.get(0).getPrice();

        for (Ask ask: asks) {
            bestBuyOffer = Math.min(bestBuyOffer, ask.getPrice());
        }
        return bestBuyOffer;
    }

    //повертає скільки біткойнів в нас буде в разі їх виводу з цього сервісу
    public static double getYobitCryptoWithdraw(double cryptoAmount){
        return cryptoAmount * (1 - Const.YOBIT_CRYPTO_WITHDRAW_FEE);
    }

    //input - bids, btc volume, output - final money(with fee)
    //приймає пропозиції купівлі і кількість наявної криптовалюти, повертає скільки грошей ми отримаємо внаслідок кращого продажу
    public static double getYobitProfit(List<Bid> bids, double crypto){
        double bestSellOffer = bids.get(0).getPrice();

        for (Bid bid: bids) {
            //Why not max?
            bestSellOffer = Math.max(bestSellOffer, bid.getPrice());
        }

        return bestSellOffer*crypto * (1 - Const.YOBIT_USD_WITHDRAW_FEE);
    }


    /*--------------------EXMO----------------*/

    //приймає пропозиції продажу, повертає скільки грошей ми витратимо на покупку 1 біткойну на цьому сервісі
    //береться комісія за операцію
    //і комісія за введення грошей
    public static double getExmoExpenses(List<Ask> asks){
        double bestBuyOffer = asks.get(0).getPrice();

        for (Ask ask: asks) {
            bestBuyOffer = Math.min(bestBuyOffer, ask.getPrice());
        }

        bestBuyOffer = bestBuyOffer * (1 + Const.EXMO_TRADING_FEE);

        return bestBuyOffer * (1 + Const.EXMO_MONEY_INPUT_FEE);
    }

    //повертає скільки біткойнів в нас буде в разі їх виводу з цього сервісу
    public static double getExmoCryptoWithdraw(double cryptoAmount){
        return cryptoAmount * (1 - Const.EXMO_CRYPTO_WITHDRAW_FEE);
    }

    //приймає пропозиції купівлі і кількість наявної криптовалюти, повертає скільки грошей ми отримаємо внаслідок кращого продажу
    //береться комісія за операцію
    //і за вивід комісія і ще - 7.5 баксів
    public static double getExmoProfit(List<Bid> bids, double crypto){
        double bestSellOffer = bids.get(0).getPrice();

        for (Bid bid: bids) {
            bestSellOffer = Math.max(bestSellOffer, bid.getPrice());
        }

        bestSellOffer = bestSellOffer * crypto * (1 - Const.EXMO_TRADING_FEE);

        return bestSellOffer * (1 - Const.EXMO_USD_WITHDRAW_FEE) - 7.5;
    }

    /*--------------------KRAKEN----------------*/

    //приймає пропозиції продажу, повертає скільки грошей ми витратимо на покупку 1 біткойну на цьому сервісі
    public static double getKrakenExpenses(List<Ask> asks){
        double bestBuyOffer = asks.get(0).getPrice();

        for (Ask ask: asks) {
            bestBuyOffer = Math.min(bestBuyOffer, ask.getPrice());
        }

        return bestBuyOffer + Const.KRAKEN_MONEY_INPUT_FEE;
    }

    //повертає скільки біткойнів в нас буде в разі їх виводу з цього сервісу
    public static double getKrakenCryptoWithdraw(double cryptoAmount){
        return cryptoAmount * (1 - Const.KRAKEN_CRYPTO_WITHDRAW_FEE);
    }

    //приймає пропозиції купівлі і кількість наявної криптовалюти, повертає скільки грошей ми отримаємо внаслідок кращого продажу
    public static double getKrakenProfit(List<Bid> bids, double crypto){
        double bestSellOffer = bids.get(0).getPrice();

        for (Bid bid: bids) {
            bestSellOffer = Math.max(bestSellOffer, bid.getPrice());
        }

        bestSellOffer = bestSellOffer * crypto * (1 - Const.KRAKEN_OPEN_FEE); //за відкриття позиції

        return bestSellOffer - Const.KRAKEN_USD_WITHDRAW_FEE;
    }

}
