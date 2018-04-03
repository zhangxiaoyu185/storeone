package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户消息中心表
*/
public class BusiMessageCenter extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsmecUnid;

	/**
	*标识UUID
	*/
	private String bsmecUuid;

	/**
	*所属用户
	*/
	private String bsmecUser;

	/**
	*创建日期
	*/
	private Date bsmecCdate;

	/**
	*更新日期
	*/
	private Date bsmecUdate;
	
	/**
	*状态:1未读,2已读,3删除
	*/
	private Integer bsmecStatus;

	/**
	*消息类型:1金额2订单
	*/
	private Integer bsmecType;

	/**
	*封面图
	*/
	private String bsmecProductPic;
	
	/**
	*内容
	*/
	private String bsmecContent;

	public void setBsmecUnid(Integer bsmecUnid) {
		this.bsmecUnid = bsmecUnid;
	}

	public Integer getBsmecUnid( ) {
		return bsmecUnid;
	}

	public void setBsmecUuid(String bsmecUuid) {
		this.bsmecUuid = bsmecUuid;
	}

	public String getBsmecUuid( ) {
		return bsmecUuid;
	}

	public void setBsmecUser(String bsmecUser) {
		this.bsmecUser = bsmecUser;
	}

	public String getBsmecUser( ) {
		return bsmecUser;
	}

	public void setBsmecCdate(Date bsmecCdate) {
		this.bsmecCdate = bsmecCdate;
	}

	public Date getBsmecCdate( ) {
		return bsmecCdate;
	}

	public Date getBsmecUdate() {
		return bsmecUdate;
	}

	public void setBsmecUdate(Date bsmecUdate) {
		this.bsmecUdate = bsmecUdate;
	}

	public void setBsmecStatus(Integer bsmecStatus) {
		this.bsmecStatus = bsmecStatus;
	}

	public Integer getBsmecStatus( ) {
		return bsmecStatus;
	}

	public void setBsmecType(Integer bsmecType) {
		this.bsmecType = bsmecType;
	}

	public Integer getBsmecType( ) {
		return bsmecType;
	}

	public String getBsmecProductPic() {
		return bsmecProductPic;
	}

	public void setBsmecProductPic(String bsmecProductPic) {
		this.bsmecProductPic = bsmecProductPic;
	}

	public void setBsmecContent(String bsmecContent) {
		this.bsmecContent = bsmecContent;
	}

	public String getBsmecContent( ) {
		return bsmecContent;
	}

	public BusiMessageCenter( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}