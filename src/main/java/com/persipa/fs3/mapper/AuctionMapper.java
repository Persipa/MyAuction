package com.persipa.fs3.mapper;

import com.persipa.fs3.pojo.Auction;
import com.persipa.fs3.pojo.AuctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionMapper {
    int countByExample(AuctionExample example);

    int deleteByExample(AuctionExample example);

    int insert(Auction record);

    int insertSelective(Auction record);

    List<Auction> selectByExample(AuctionExample example);

    int updateByExampleSelective(@Param("record") Auction record, @Param("example") AuctionExample example);

    int updateByExample(@Param("record") Auction record, @Param("example") AuctionExample example);
}