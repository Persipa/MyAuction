package com.persipa.fs3.mapper;

import com.persipa.fs3.pojo.Auction;
import com.persipa.fs3.pojo.AuctionCustom;

import java.util.List;

public interface AuctionCustomMapper {
    Auction findAuctionAndRecordListById(int auctionid);

    List<AuctionCustom> findAuctionEndtimeList();

    List<Auction> findAuctionNoEndtimeList();

}