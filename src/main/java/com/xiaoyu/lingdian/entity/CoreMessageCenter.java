package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 系统消息中心表
*/
public class CoreMessageCenter extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crmecUnid;

	/**
	*标识UUID
	*/
	private String crmecUuid;

	/**
	*消息内容
	*/
	private String crmecContent;

	/**
	*创建日期
	*/
	private Date crmecCdate;

	/**
	*更新日期
	*/
	private Date crmecUdate;
	
	/**
	*状态:1已处理,2未处理,3已删除
	*/
	private Integer crmecStatus;

	/**
	*消息类型
	*/
	private Integer crmecType;

	public void setCrmecUnid(Integer crmecUnid) {
		this.crmecUnid = crmecUnid;
	}

	public Integer getCrmecUnid( ) {
		return crmecUnid;
	}

	public void setCrmecUuid(String crmecUuid) {
		this.crmecUuid = crmecUuid;
	}

	public String getCrmecUuid( ) {
		return crmecUuid;
	}

	public void setCrmecContent(String crmecContent) {
		this.crmecContent = crmecContent;
	}

	public String getCrmecContent( ) {
		return crmecContent;
	}

	public void setCrmecCdate(Date crmecCdate) {
		this.crmecCdate = crmecCdate;
	}

	public Date getCrmecCdate( ) {
		return crmecCdate;
	}

	public Date getCrmecUdate() {
		return crmecUdate;
	}

	public void setCrmecUdate(Date crmecUdate) {
		this.crmecUdate = crmecUdate;
	}

	public void setCrmecStatus(Integer crmecStatus) {
		this.crmecStatus = crmecStatus;
	}

	public Integer getCrmecStatus( ) {
		return crmecStatus;
	}

	public void setCrmecType(Integer crmecType) {
		this.crmecType = crmecType;
	}

	public Integer getCrmecType( ) {
		return crmecType;
	}

	public CoreMessageCenter( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}