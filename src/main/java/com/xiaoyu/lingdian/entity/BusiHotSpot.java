package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 热点配置表
*/
public class BusiHotSpot extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bshstUnid;

	/**
	*标识UUID
	*/
	private String bshstUuid;

	/**
	*热点名称
	*/
	private String bshstName;

	/**
	*热点图片
	*/
	private String bshstIcon;

	/**
	*创建时间
	*/
	private Date bshstCdate;

	/**
	*更新时间
	*/
	private Date bshstUdate;

	public void setBshstUnid(Integer bshstUnid) {
		this.bshstUnid = bshstUnid;
	}

	public Integer getBshstUnid( ) {
		return bshstUnid;
	}

	public void setBshstUuid(String bshstUuid) {
		this.bshstUuid = bshstUuid;
	}

	public String getBshstUuid( ) {
		return bshstUuid;
	}

	public void setBshstName(String bshstName) {
		this.bshstName = bshstName;
	}

	public String getBshstName( ) {
		return bshstName;
	}

	public void setBshstIcon(String bshstIcon) {
		this.bshstIcon = bshstIcon;
	}

	public String getBshstIcon( ) {
		return bshstIcon;
	}

	public void setBshstCdate(Date bshstCdate) {
		this.bshstCdate = bshstCdate;
	}

	public Date getBshstCdate( ) {
		return bshstCdate;
	}

	public void setBshstUdate(Date bshstUdate) {
		this.bshstUdate = bshstUdate;
	}

	public Date getBshstUdate( ) {
		return bshstUdate;
	}

	public BusiHotSpot( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}