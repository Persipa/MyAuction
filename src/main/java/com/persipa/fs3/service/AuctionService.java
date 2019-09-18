package com.persipa.fs3.service;

import java.util.List;

import com.persipa.fs3.pojo.Auction;
import com.persipa.fs3.pojo.AuctionCustom;
import com.persipa.fs3.pojo.Auctionrecord;

public interface AuctionService {
    public List<Auction> findAuctions(Auction query);

    public void addAuction(Auction query);

    public void removeAuction(int auctionId);

    public Auction findAuctionByAuctionId(int auctionid);

    public void updateAuction(Auction auction);

    public Auction findAuctionAndRecordList(int auctionid);

    public void addAuctionRecord(Auctionrecord record) throws Exception;

    public List<AuctionCustom> findAuctionEndTimeList();

    public List<Auction> findAuctionNoEndtimeList();

}
