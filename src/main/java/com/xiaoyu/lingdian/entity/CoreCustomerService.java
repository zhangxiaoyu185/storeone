package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 客服信息表
*/
public class CoreCustomerService extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crcseUnid;

	/**
	*标识UUID
	*/
	private String crcseUuid;

	/**
	*电话号码(|隔开)
	*/
	private String crcseTel;

	/**
	*QQ(|隔开)
	*/
	private String crcseQq;

	/**
	*创建日期
	*/
	private Date crcseCdate;

	/**
	*修改日期
	*/
	private Date crcseUdate;

	public void setCrcseUnid(Integer crcseUnid) {
		this.crcseUnid = crcseUnid;
	}

	public Integer getCrcseUnid( ) {
		return crcseUnid;
	}

	public void setCrcseUuid(String crcseUuid) {
		this.crcseUuid = crcseUuid;
	}

	public String getCrcseUuid( ) {
		return crcseUuid;
	}

	public void setCrcseTel(String crcseTel) {
		this.crcseTel = crcseTel;
	}

	public String getCrcseTel( ) {
		return crcseTel;
	}

	public void setCrcseQq(String crcseQq) {
		this.crcseQq = crcseQq;
	}

	public String getCrcseQq( ) {
		return crcseQq;
	}

	public void setCrcseCdate(Date crcseCdate) {
		this.crcseCdate = crcseCdate;
	}

	public Date getCrcseCdate( ) {
		return crcseCdate;
	}

	public void setCrcseUdate(Date crcseUdate) {
		this.crcseUdate = crcseUdate;
	}

	public Date getCrcseUdate( ) {
		return crcseUdate;
	}

	public CoreCustomerService( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}