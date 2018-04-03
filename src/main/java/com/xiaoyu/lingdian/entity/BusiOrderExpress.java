package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 订单明细快递物流表
*/
public class BusiOrderExpress extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsoesUnid;

	/**
	*标识UUID
	*/
	private String bsoesUuid;

	/**
	*订单明细标识
	*/
	private String bsoesOrderItem;

	/**
	*物流内容
	*/
	private String bsoesContent;

	/**
	*添加时间
	*/
	private Date bsoesCdate;

	public void setBsoesUnid(Integer bsoesUnid) {
		this.bsoesUnid = bsoesUnid;
	}

	public Integer getBsoesUnid( ) {
		return bsoesUnid;
	}

	public void setBsoesUuid(String bsoesUuid) {
		this.bsoesUuid = bsoesUuid;
	}

	public String getBsoesUuid( ) {
		return bsoesUuid;
	}

	public void setBsoesOrderItem(String bsoesOrderItem) {
		this.bsoesOrderItem = bsoesOrderItem;
	}

	public String getBsoesOrderItem( ) {
		return bsoesOrderItem;
	}

	public void setBsoesContent(String bsoesContent) {
		this.bsoesContent = bsoesContent;
	}

	public String getBsoesContent( ) {
		return bsoesContent;
	}

	public void setBsoesCdate(Date bsoesCdate) {
		this.bsoesCdate = bsoesCdate;
	}

	public Date getBsoesCdate( ) {
		return bsoesCdate;
	}

	public BusiOrderExpress( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}