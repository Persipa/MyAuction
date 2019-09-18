package com.persipa.fs3.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Auctionrecord {

    @Override
    public String toString() {
        return "\nAuctionrecord{" +
                "auctionuser=" + auctionuser +
                ", id=" + id +
                ", userid=" + userid +
                ", auctionid=" + auctionid +
                ", auctiontime=" + auctiontime +
                ", auctionprice=" + auctionprice +
                '}';
    }

    public Auctionuser getAuctionuser() {
		return auctionuser;
	}

	public void setAuctionuser(Auctionuser auctionuser) {
		this.auctionuser = auctionuser;
	}

	private Auctionuser auctionuser;
	
    private Integer id;

    private Integer userid;

    private Integer auctionid;

    private Date auctiontime;

    private Double auctionprice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getAuctiontime() {
        return auctiontime;
    }

    public void setAuctiontime(Date auctiontime) {
        this.auctiontime = auctiontime;
    }

    public Double getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(Double auctionprice) {
        this.auctionprice = auctionprice;
    }
}