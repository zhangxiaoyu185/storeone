package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 导航商品配置表
*/
public class BusiBanner extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsbarUnid;

	/**
	*标识UUID
	*/
	private String bsbarUuid;

	/**
	*链接
	*/
	private String bsbarLink;

	/**
	*BANNER图
	*/
	private String bsbarPic;

	/**
	*顺序
	*/
	private Integer bsbarOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsbarStatus;

	/**
	*描述
	*/
	private String bsbarDesc;
	
	/**
	*创建时间
	*/
	private Date bsbarCdate;

	/**
	*更新时间
	*/
	private Date bsbarUdate;

	public void setBsbarUnid(Integer bsbarUnid) {
		this.bsbarUnid = bsbarUnid;
	}

	public Integer getBsbarUnid( ) {
		return bsbarUnid;
	}

	public void setBsbarUuid(String bsbarUuid) {
		this.bsbarUuid = bsbarUuid;
	}

	public String getBsbarUuid( ) {
		return bsbarUuid;
	}

	public void setBsbarLink(String bsbarLink) {
		this.bsbarLink = bsbarLink;
	}

	public String getBsbarLink( ) {
		return bsbarLink;
	}

	public void setBsbarPic(String bsbarPic) {
		this.bsbarPic = bsbarPic;
	}

	public String getBsbarPic( ) {
		return bsbarPic;
	}

	public void setBsbarOrd(Integer bsbarOrd) {
		this.bsbarOrd = bsbarOrd;
	}

	public Integer getBsbarOrd( ) {
		return bsbarOrd;
	}

	public void setBsbarStatus(Integer bsbarStatus) {
		this.bsbarStatus = bsbarStatus;
	}

	public Integer getBsbarStatus( ) {
		return bsbarStatus;
	}

	public void setBsbarDesc(String bsbarDesc) {
		this.bsbarDesc = bsbarDesc;
	}

	public String getBsbarDesc( ) {
		return bsbarDesc;
	}

	public void setBsbarCdate(Date bsbarCdate) {
		this.bsbarCdate = bsbarCdate;
	}

	public Date getBsbarCdate( ) {
		return bsbarCdate;
	}

	public void setBsbarUdate(Date bsbarUdate) {
		this.bsbarUdate = bsbarUdate;
	}

	public Date getBsbarUdate( ) {
		return bsbarUdate;
	}

	public BusiBanner( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}