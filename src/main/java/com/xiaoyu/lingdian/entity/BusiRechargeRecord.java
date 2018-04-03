package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户充值记录表
*/
public class BusiRechargeRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsrrdUnid;

	/**
	*标识UUID
	*/
	private String bsrrdUuid;

	/**
	*充值金额
	*/
	private String bsrrdAmount;

	/**
	*充值人
	*/
	private String bsrrdUser;

	/**
	*创建时间
	*/
	private Date bsrrdCdate;

	/**
	*更新时间
	*/
	private Date bsrrdUdate;

	public void setBsrrdUnid(Integer bsrrdUnid) {
		this.bsrrdUnid = bsrrdUnid;
	}

	public Integer getBsrrdUnid( ) {
		return bsrrdUnid;
	}

	public void setBsrrdUuid(String bsrrdUuid) {
		this.bsrrdUuid = bsrrdUuid;
	}

	public String getBsrrdUuid( ) {
		return bsrrdUuid;
	}

	public void setBsrrdAmount(String bsrrdAmount) {
		this.bsrrdAmount = bsrrdAmount;
	}

	public String getBsrrdAmount( ) {
		return bsrrdAmount;
	}

	public void setBsrrdUser(String bsrrdUser) {
		this.bsrrdUser = bsrrdUser;
	}

	public String getBsrrdUser( ) {
		return bsrrdUser;
	}

	public void setBsrrdCdate(Date bsrrdCdate) {
		this.bsrrdCdate = bsrrdCdate;
	}

	public Date getBsrrdCdate( ) {
		return bsrrdCdate;
	}

	public void setBsrrdUdate(Date bsrrdUdate) {
		this.bsrrdUdate = bsrrdUdate;
	}

	public Date getBsrrdUdate( ) {
		return bsrrdUdate;
	}

	public BusiRechargeRecord( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}