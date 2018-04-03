package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 快递鸟配置表
*/
public class BusiExpressConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsecgUnid;

	/**
	*标识UUID
	*/
	private String bsecgUuid;

	/**
	*商户标识
	*/
	private String bsecgKdnCode;

	/**
	*API_KEY
	*/
	private String bsecgApiKey;

	/**
	*创建时间
	*/
	private Date bsecgCdate;

	/**
	*更新时间
	*/
	private Date bsecgUdate;

	public void setBsecgUnid(Integer bsecgUnid) {
		this.bsecgUnid = bsecgUnid;
	}

	public Integer getBsecgUnid( ) {
		return bsecgUnid;
	}

	public void setBsecgUuid(String bsecgUuid) {
		this.bsecgUuid = bsecgUuid;
	}

	public String getBsecgUuid( ) {
		return bsecgUuid;
	}

	public void setBsecgKdnCode(String bsecgKdnCode) {
		this.bsecgKdnCode = bsecgKdnCode;
	}

	public String getBsecgKdnCode( ) {
		return bsecgKdnCode;
	}

	public void setBsecgApiKey(String bsecgApiKey) {
		this.bsecgApiKey = bsecgApiKey;
	}

	public String getBsecgApiKey( ) {
		return bsecgApiKey;
	}

	public void setBsecgCdate(Date bsecgCdate) {
		this.bsecgCdate = bsecgCdate;
	}

	public Date getBsecgCdate( ) {
		return bsecgCdate;
	}

	public void setBsecgUdate(Date bsecgUdate) {
		this.bsecgUdate = bsecgUdate;
	}

	public Date getBsecgUdate( ) {
		return bsecgUdate;
	}

	public BusiExpressConfig( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}