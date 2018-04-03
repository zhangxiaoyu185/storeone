package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 快递鸟公司编码配置表
*/
public class BusiExpressCode extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bseceUnid;

	/**
	*标识UUID
	*/
	private String bseceUuid;

	/**
	*编码
	*/
	private String bseceCode;

	/**
	*名称
	*/
	private String bseceName;

	public void setBseceUnid(Integer bseceUnid) {
		this.bseceUnid = bseceUnid;
	}

	public Integer getBseceUnid( ) {
		return bseceUnid;
	}

	public void setBseceUuid(String bseceUuid) {
		this.bseceUuid = bseceUuid;
	}

	public String getBseceUuid( ) {
		return bseceUuid;
	}

	public void setBseceCode(String bseceCode) {
		this.bseceCode = bseceCode;
	}

	public String getBseceCode( ) {
		return bseceCode;
	}

	public void setBseceName(String bseceName) {
		this.bseceName = bseceName;
	}

	public String getBseceName( ) {
		return bseceName;
	}

	public BusiExpressCode( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}