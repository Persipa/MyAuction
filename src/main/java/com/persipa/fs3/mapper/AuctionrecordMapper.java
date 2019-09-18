package com.persipa.fs3.mapper;

import com.persipa.fs3.pojo.Auctionrecord;
import com.persipa.fs3.pojo.AuctionrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionrecordMapper {
    int countByExample(AuctionrecordExample example);

    int deleteByExample(AuctionrecordExample example);

    int insert(Auctionrecord record);

    int insertSelective(Auctionrecord record);

    List<Auctionrecord> selectByExample(AuctionrecordExample example);

    int updateByExampleSelective(@Param("record") Auctionrecord record, @Param("example") AuctionrecordExample example);

    int updateByExample(@Param("record") Auctionrecord record, @Param("example") AuctionrecordExample example);
}