package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 标签配置表
*/
public class BusiTagSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bststUnid;

	/**
	*标识UUID
	*/
	private String bststUuid;

	/**
	*标签名称
	*/
	private String bststName;

	/**
	*标签简介
	*/
	private String bststDesc;

	/**
	*标签图标字
	*/
	private String bststIcon;

	/**
	*创建时间
	*/
	private Date bststCdate;

	/**
	*更新时间
	*/
	private Date bststUdate;

	public void setBststUnid(Integer bststUnid) {
		this.bststUnid = bststUnid;
	}

	public Integer getBststUnid( ) {
		return bststUnid;
	}

	public void setBststUuid(String bststUuid) {
		this.bststUuid = bststUuid;
	}

	public String getBststUuid( ) {
		return bststUuid;
	}

	public void setBststName(String bststName) {
		this.bststName = bststName;
	}

	public String getBststName( ) {
		return bststName;
	}

	public void setBststDesc(String bststDesc) {
		this.bststDesc = bststDesc;
	}

	public String getBststDesc( ) {
		return bststDesc;
	}

	public void setBststIcon(String bststIcon) {
		this.bststIcon = bststIcon;
	}

	public String getBststIcon( ) {
		return bststIcon;
	}

	public void setBststCdate(Date bststCdate) {
		this.bststCdate = bststCdate;
	}

	public Date getBststCdate( ) {
		return bststCdate;
	}

	public void setBststUdate(Date bststUdate) {
		this.bststUdate = bststUdate;
	}

	public Date getBststUdate( ) {
		return bststUdate;
	}

	public BusiTagSet( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}