package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户当前签到记录表
*/
public class BusiSignRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssrdUnid;

	/**
	*标识UUID
	*/
	private String bssrdUuid;

	/**
	*签到用户
	*/
	private String bssrdUser;

	/**
	*所得金額
	*/
	private Integer bssrdFee;

	/**
	*最后签到时间
	*/
	private Date bssrdLastDate;

	/**
	*连续签到天数
	*/
	private Integer bssrdSignDays;

	public void setBssrdUnid(Integer bssrdUnid) {
		this.bssrdUnid = bssrdUnid;
	}

	public Integer getBssrdUnid( ) {
		return bssrdUnid;
	}

	public void setBssrdUuid(String bssrdUuid) {
		this.bssrdUuid = bssrdUuid;
	}

	public String getBssrdUuid( ) {
		return bssrdUuid;
	}

	public void setBssrdUser(String bssrdUser) {
		this.bssrdUser = bssrdUser;
	}

	public String getBssrdUser( ) {
		return bssrdUser;
	}

	public Integer getBssrdFee() {
        return bssrdFee;
    }

    public void setBssrdFee(Integer bssrdFee) {
        this.bssrdFee = bssrdFee;
    }

    public void setBssrdLastDate(Date bssrdLastDate) {
		this.bssrdLastDate = bssrdLastDate;
	}

	public Date getBssrdLastDate( ) {
		return bssrdLastDate;
	}

	public void setBssrdSignDays(Integer bssrdSignDays) {
		this.bssrdSignDays = bssrdSignDays;
	}

	public Integer getBssrdSignDays( ) {
		return bssrdSignDays;
	}

	public BusiSignRecord( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}