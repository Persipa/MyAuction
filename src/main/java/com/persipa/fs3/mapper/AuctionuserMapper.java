package com.persipa.fs3.mapper;

import com.persipa.fs3.pojo.Auctionuser;
import com.persipa.fs3.pojo.AuctionuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionuserMapper {
    int countByExample(AuctionuserExample example);

    int deleteByExample(AuctionuserExample example);

    int insert(Auctionuser record);

    int insertSelective(Auctionuser record);

    List<Auctionuser> selectByExample(AuctionuserExample example);

    int updateByExampleSelective(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);

    int updateByExample(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);
}