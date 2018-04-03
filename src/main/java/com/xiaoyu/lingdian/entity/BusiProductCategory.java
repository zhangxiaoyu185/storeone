package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 分类表
*/
public class BusiProductCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bspcyUnid;

	/**
	*标识UUID
	*/
	private String bspcyUuid;

	/**
	*分类名称
	*/
	private String bspcyName;

	/**
	*分类图标
	*/
	private String bspcyIcon;

	/**
	*顺序
	*/
	private Integer bspcyOrd;

	/**
	*状态:1启动0禁用2删除
	*/
	private Integer bspcyStatus;

	/**
	*上级分类
	*/
	private String bspcyTop;

	/**
	*是否有子节点:1有0无
	*/
	private Integer bspcyChildNode;

	/**
	*创建时间
	*/
	private Date bspcyCdate;

	/**
	*更新时间
	*/
	private Date bspcyUdate;

	public void setBspcyUnid(Integer bspcyUnid) {
		this.bspcyUnid = bspcyUnid;
	}

	public Integer getBspcyUnid( ) {
		return bspcyUnid;
	}

	public void setBspcyUuid(String bspcyUuid) {
		this.bspcyUuid = bspcyUuid;
	}

	public String getBspcyUuid( ) {
		return bspcyUuid;
	}

	public void setBspcyName(String bspcyName) {
		this.bspcyName = bspcyName;
	}

	public String getBspcyName( ) {
		return bspcyName;
	}

	public void setBspcyIcon(String bspcyIcon) {
		this.bspcyIcon = bspcyIcon;
	}

	public String getBspcyIcon( ) {
		return bspcyIcon;
	}

	public void setBspcyOrd(Integer bspcyOrd) {
		this.bspcyOrd = bspcyOrd;
	}

	public Integer getBspcyOrd( ) {
		return bspcyOrd;
	}

	public void setBspcyStatus(Integer bspcyStatus) {
		this.bspcyStatus = bspcyStatus;
	}

	public Integer getBspcyStatus( ) {
		return bspcyStatus;
	}

	public void setBspcyTop(String bspcyTop) {
		this.bspcyTop = bspcyTop;
	}

	public String getBspcyTop( ) {
		return bspcyTop;
	}

	public void setBspcyChildNode(Integer bspcyChildNode) {
		this.bspcyChildNode = bspcyChildNode;
	}

	public Integer getBspcyChildNode( ) {
		return bspcyChildNode;
	}

	public void setBspcyCdate(Date bspcyCdate) {
		this.bspcyCdate = bspcyCdate;
	}

	public Date getBspcyCdate( ) {
		return bspcyCdate;
	}

	public void setBspcyUdate(Date bspcyUdate) {
		this.bspcyUdate = bspcyUdate;
	}

	public Date getBspcyUdate( ) {
		return bspcyUdate;
	}

	public BusiProductCategory( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}