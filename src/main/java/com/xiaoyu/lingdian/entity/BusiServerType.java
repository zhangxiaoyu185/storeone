package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 服务类别表
*/
public class BusiServerType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssteUnid;

	/**
	*标识UUID
	*/
	private String bssteUuid;

	/**
	*类别名称
	*/
	private String bssteName;

	/**
	*图标
	*/
	private String bssteIcon;

	/**
	*是否有列表:1有0无
	*/
	private Integer bssteIslist;

	/**
	*详情URL(不是列表用)
	*/
	private String bssteDetails;

	/**
	*创建日期
	*/
	private Date bssteCdate;

	/**
	*修改日期
	*/
	private Date bssteUdate;

	/**
	*状态:1启用,0禁用
	*/
	private Integer bssteStatus;

	public void setBssteUnid(Integer bssteUnid) {
		this.bssteUnid = bssteUnid;
	}

	public Integer getBssteUnid( ) {
		return bssteUnid;
	}

	public void setBssteUuid(String bssteUuid) {
		this.bssteUuid = bssteUuid;
	}

	public String getBssteUuid( ) {
		return bssteUuid;
	}

	public void setBssteName(String bssteName) {
		this.bssteName = bssteName;
	}

	public String getBssteName( ) {
		return bssteName;
	}

	public void setBssteIcon(String bssteIcon) {
		this.bssteIcon = bssteIcon;
	}

	public String getBssteIcon( ) {
		return bssteIcon;
	}

	public void setBssteIslist(Integer bssteIslist) {
		this.bssteIslist = bssteIslist;
	}

	public Integer getBssteIslist( ) {
		return bssteIslist;
	}

	public void setBssteDetails(String bssteDetails) {
		this.bssteDetails = bssteDetails;
	}

	public String getBssteDetails( ) {
		return bssteDetails;
	}

	public void setBssteCdate(Date bssteCdate) {
		this.bssteCdate = bssteCdate;
	}

	public Date getBssteCdate( ) {
		return bssteCdate;
	}

	public void setBssteUdate(Date bssteUdate) {
		this.bssteUdate = bssteUdate;
	}

	public Date getBssteUdate( ) {
		return bssteUdate;
	}

	public void setBssteStatus(Integer bssteStatus) {
		this.bssteStatus = bssteStatus;
	}

	public Integer getBssteStatus( ) {
		return bssteStatus;
	}

	public BusiServerType( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}