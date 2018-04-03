package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 首页商品表
*/
public class BusiIndexProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsiptUnid;

	/**
	*标识UUID
	*/
	private String bsiptUuid;

	/**
	*商品标识
	*/
	private String bsiptProduct;

	/**
	*顺序
	*/
	private Integer bsiptOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsiptStatus;

	/**
	*创建时间
	*/
	private Date bsiptCdate;

	/**
	*更新时间
	*/
	private Date bsiptUdate;

	public void setBsiptUnid(Integer bsiptUnid) {
		this.bsiptUnid = bsiptUnid;
	}

	public Integer getBsiptUnid( ) {
		return bsiptUnid;
	}

	public void setBsiptUuid(String bsiptUuid) {
		this.bsiptUuid = bsiptUuid;
	}

	public String getBsiptUuid( ) {
		return bsiptUuid;
	}

	public void setBsiptProduct(String bsiptProduct) {
		this.bsiptProduct = bsiptProduct;
	}

	public String getBsiptProduct( ) {
		return bsiptProduct;
	}

	public void setBsiptOrd(Integer bsiptOrd) {
		this.bsiptOrd = bsiptOrd;
	}

	public Integer getBsiptOrd( ) {
		return bsiptOrd;
	}

	public void setBsiptStatus(Integer bsiptStatus) {
		this.bsiptStatus = bsiptStatus;
	}

	public Integer getBsiptStatus( ) {
		return bsiptStatus;
	}

	public void setBsiptCdate(Date bsiptCdate) {
		this.bsiptCdate = bsiptCdate;
	}

	public Date getBsiptCdate( ) {
		return bsiptCdate;
	}

	public void setBsiptUdate(Date bsiptUdate) {
		this.bsiptUdate = bsiptUdate;
	}

	public Date getBsiptUdate( ) {
		return bsiptUdate;
	}

	public BusiIndexProduct( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}