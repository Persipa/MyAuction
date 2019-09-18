package com.persipa.fs3.pojo;

import java.util.Date;

public class AuctionCustom extends Auction{

    private Double auctionprice;

    private String username;

    public Double getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(Double auctionprice) {
        this.auctionprice = auctionprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
