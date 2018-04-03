package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 系统配置表
*/
public class CoreSystemConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crscgUnid;

	/**
	*标识UUID
	*/
	private String crscgUuid;

	/**
	*配置KEY
	*/
	private String crscgKey;

	/**
	*配置值
	*/
	private String crscgValue;

	/**
	*配置说明
	*/
	private String crscgDesc;

	public void setCrscgUnid(Integer crscgUnid) {
		this.crscgUnid = crscgUnid;
	}

	public Integer getCrscgUnid( ) {
		return crscgUnid;
	}

	public void setCrscgUuid(String crscgUuid) {
		this.crscgUuid = crscgUuid;
	}

	public String getCrscgUuid( ) {
		return crscgUuid;
	}

	public void setCrscgKey(String crscgKey) {
		this.crscgKey = crscgKey;
	}

	public String getCrscgKey( ) {
		return crscgKey;
	}

	public void setCrscgValue(String crscgValue) {
		this.crscgValue = crscgValue;
	}

	public String getCrscgValue( ) {
		return crscgValue;
	}

	public void setCrscgDesc(String crscgDesc) {
		this.crscgDesc = crscgDesc;
	}

	public String getCrscgDesc( ) {
		return crscgDesc;
	}

	public CoreSystemConfig( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}