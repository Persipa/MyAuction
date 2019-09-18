package com.persipa.fs3.service.impl;

import java.util.Date;
import java.util.List;

import com.persipa.fs3.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persipa.fs3.mapper.AuctionCustomMapper;
import com.persipa.fs3.mapper.AuctionMapper;
import com.persipa.fs3.mapper.AuctionrecordMapper;
import com.persipa.fs3.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private AuctionrecordMapper auctionrecordMapper;

    @Autowired
    private AuctionCustomMapper auctionCustomerMapper;

    @Override
    public List<Auction> findAuctions(Auction query) {

        AuctionExample example = new AuctionExample();

        AuctionExample.Criteria criteria = example.createCriteria();

        if (query != null) {
            // 商品名称模糊查询
            if (query.getAuctionname() != null && !"".equals(query.getAuctionname())) {
                criteria.andAuctionnameLike("%" + query.getAuctionname() + "%");
            }
            // 商品描述模糊查询
            if (query.getAuctiondesc() != null && !"".equals(query.getAuctiondesc())) {
                criteria.andAuctiondescLike("%" + query.getAuctiondesc() + "%");
            }
            // 大于开始时间
            if (query.getAuctionstarttime() != null) {
                criteria.andAuctionstarttimeGreaterThan(query.getAuctionstarttime());
            }
            // 小于结束时间
            if (query.getAuctionendtime() != null) {
                criteria.andAuctionendtimeLessThan(query.getAuctionendtime());
            }

            // 大于起拍价
            if (query.getAuctionstartprice() != null) {
                criteria.andAuctionstartpriceGreaterThan(query.getAuctionstartprice());
            }

        }
        example.setOrderByClause("auctionstarttime desc");

        List<Auction> list = auctionMapper.selectByExample(example);
        return list;
    }

    @Override
    public void addAuction(Auction query) {
        auctionMapper.insert(query);

    }

    @Override
    public void removeAuction(int auctionId) {
        AuctionrecordExample auctionrecordExample = new AuctionrecordExample();

        AuctionrecordExample.Criteria auctionrecordCriteria = auctionrecordExample.createCriteria();
        auctionrecordCriteria.andAuctionidEqualTo(auctionId);
        //如果有竞拍记录则删除
        if (auctionrecordMapper.countByExample(auctionrecordExample) > 0) {
            auctionrecordMapper.deleteByExample(auctionrecordExample);

        }

        AuctionExample auctionExample = new AuctionExample();
        AuctionExample.Criteria aucitonCriteria = auctionExample.createCriteria();
        aucitonCriteria.andAuctionidEqualTo(auctionId);

        auctionMapper.deleteByExample(auctionExample);
    }

    @Override
    public Auction findAuctionByAuctionId(int auctionid) {
        AuctionExample example = new AuctionExample();
        AuctionExample.Criteria criteria = example.createCriteria();

        criteria.andAuctionidEqualTo(auctionid);

        List<Auction> list = auctionMapper.selectByExample(example);

        return list.get(0);

    }

    @Override
    public void updateAuction(Auction auction) {
        AuctionExample example = new AuctionExample();

        AuctionExample.Criteria criteria = example.createCriteria();
        criteria.andAuctionidEqualTo(auction.getAuctionid());

        auctionMapper.updateByExample(auction, example);

    }

    @Override
    public Auction findAuctionAndRecordList(int auctionid) {
        return auctionCustomerMapper.findAuctionAndRecordListById(auctionid);

    }

    @Override
    public void addAuctionRecord(Auctionrecord record) throws Exception {
        Auction auction = auctionCustomerMapper.findAuctionAndRecordListById(record.getAuctionid());

        // 过期
        if (auction.getAuctionendtime().after(new Date()) == false) {
            throw new Exception("拍卖已结束");
        } else {

            // 非首次竞价
            if (auction.getAuctionrecordList() != null && auction.getAuctionrecordList().size() > 0) {
                Auctionrecord maxRecord = auction.getAuctionrecordList().get(0);
                if (record.getAuctionprice() < maxRecord.getAuctionprice()) {
                    throw new Exception("竞价必须高于所有的竞拍价");
                }
            } else {
                // 首次竞价
                if (record.getAuctionprice() < auction.getAuctionstartprice()) {
                    throw new Exception("竞价必须高于起拍价");
                }

            }
        }

        auctionrecordMapper.insert(record);
    }

    @Override
    public List<AuctionCustom> findAuctionEndTimeList() {
        return auctionCustomerMapper.findAuctionEndtimeList();

    }

    @Override
    public List<Auction> findAuctionNoEndtimeList() {
        return auctionCustomerMapper.findAuctionNoEndtimeList();

    }

}
