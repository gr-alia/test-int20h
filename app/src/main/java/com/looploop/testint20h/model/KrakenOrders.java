package com.looploop.testint20h.model;

import java.util.List;

/**
 * Created by Alyona on 23.01.2018.
 */

public class KrakenOrders {
    List<Ask> asks = null;
    List<Bid> bids = null;

    public List<Ask> getAsks() {
        return asks;
    }

    public void setAsks(List<Ask> asks) {
        this.asks = asks;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
