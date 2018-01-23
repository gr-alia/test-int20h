package com.looploop.testint20h.model;


import java.util.List;

public class YobitOrders {
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
