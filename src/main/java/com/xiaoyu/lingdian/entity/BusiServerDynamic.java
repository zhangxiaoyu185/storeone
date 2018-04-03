package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 服务动态表
*/
public class BusiServerDynamic extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssdcUnid;

	/**
	*标识UUID
	*/
	private String bssdcUuid;

	/**
	*服务类别
	*/
	private String bssdcType;

	/**
	*标题
	*/
	private String bssdcTitle;

	/**
	*封面图
	*/
	private String bssdcPic;

	/**
	*内容
	*/
	private String bssdcContent;

	/**
	*描述
	*/
	private String bssdcDesc;

	/**
	*作者
	*/
	private String bssdcAuthor;

	/**
	*点击数
	*/
	private Integer bssdcCount;

	/**
	*是否转载:1是0否
	*/
	private Integer bssdcIsReprint;

	/**
	*来源URL
	*/
	private String bssdcSource;

	/**
	*发布时间
	*/
	private Date bssdcPdate;

	/**
	*创建日期
	*/
	private Date bssdcCdate;

	/**
	*修改日期
	*/
	private Date bssdcUdate;

	/**
	*状态:1启用,0禁用
	*/
	private Integer bssdcStatus;

	public void setBssdcUnid(Integer bssdcUnid) {
		this.bssdcUnid = bssdcUnid;
	}

	public Integer getBssdcUnid( ) {
		return bssdcUnid;
	}

	public void setBssdcUuid(String bssdcUuid) {
		this.bssdcUuid = bssdcUuid;
	}

	public String getBssdcUuid( ) {
		return bssdcUuid;
	}

	public void setBssdcType(String bssdcType) {
		this.bssdcType = bssdcType;
	}

	public String getBssdcType( ) {
		return bssdcType;
	}

	public void setBssdcTitle(String bssdcTitle) {
		this.bssdcTitle = bssdcTitle;
	}

	public String getBssdcTitle( ) {
		return bssdcTitle;
	}

	public void setBssdcPic(String bssdcPic) {
		this.bssdcPic = bssdcPic;
	}

	public String getBssdcPic( ) {
		return bssdcPic;
	}

	public void setBssdcContent(String bssdcContent) {
		this.bssdcContent = bssdcContent;
	}

	public String getBssdcContent( ) {
		return bssdcContent;
	}

	public void setBssdcDesc(String bssdcDesc) {
		this.bssdcDesc = bssdcDesc;
	}

	public String getBssdcDesc( ) {
		return bssdcDesc;
	}

	public void setBssdcAuthor(String bssdcAuthor) {
		this.bssdcAuthor = bssdcAuthor;
	}

	public String getBssdcAuthor( ) {
		return bssdcAuthor;
	}

	public void setBssdcCount(Integer bssdcCount) {
		this.bssdcCount = bssdcCount;
	}

	public Integer getBssdcCount( ) {
		return bssdcCount;
	}

	public void setBssdcIsReprint(Integer bssdcIsReprint) {
		this.bssdcIsReprint = bssdcIsReprint;
	}

	public Integer getBssdcIsReprint( ) {
		return bssdcIsReprint;
	}

	public void setBssdcSource(String bssdcSource) {
		this.bssdcSource = bssdcSource;
	}

	public String getBssdcSource( ) {
		return bssdcSource;
	}

	public void setBssdcPdate(Date bssdcPdate) {
		this.bssdcPdate = bssdcPdate;
	}

	public Date getBssdcPdate( ) {
		return bssdcPdate;
	}

	public void setBssdcCdate(Date bssdcCdate) {
		this.bssdcCdate = bssdcCdate;
	}

	public Date getBssdcCdate( ) {
		return bssdcCdate;
	}

	public void setBssdcUdate(Date bssdcUdate) {
		this.bssdcUdate = bssdcUdate;
	}

	public Date getBssdcUdate( ) {
		return bssdcUdate;
	}

	public void setBssdcStatus(Integer bssdcStatus) {
		this.bssdcStatus = bssdcStatus;
	}

	public Integer getBssdcStatus( ) {
		return bssdcStatus;
	}

	public BusiServerDynamic( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}