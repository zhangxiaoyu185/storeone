package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 评价表
*/
public class BusiEvaluate extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bseveUnid;

	/**
	*标识UUID
	*/
	private String bseveUuid;

	/**
	*订单明细标识
	*/
	private String bseveOrderItem;

	/**
	*商品标识
	*/
	private String bseveProduct;

	/**
	*选择商品属性组合名称（颜色：绿色尺寸：L）
	*/
	private String bseveAttrName;

	/**
	*评价人
	*/
	private String bseveUser;

	/**
	*评价人姓名
	*/
	private String bseveName;

	/**
	*评价人头像
	*/
	private String bseveHead;

	/**
	*评价内容
	*/
	private String bseveContent;

	/**
	*评价等级:1好评2中评3差评
	*/
	private Integer bseveGrade;

	/**
	*状态:1启动0禁用
	*/
	private Integer bseveStatus;

	/**
	*评价图1
	*/
	private String bsevePic1;

	/**
	*评价图2
	*/
	private String bsevePic2;

	/**
	*评价图3
	*/
	private String bsevePic3;

	/**
	*评价图4
	*/
	private String bsevePic4;

	/**
	*评价图5
	*/
	private String bsevePic5;

	/**
	*创建时间
	*/
	private Date bseveCdate;

	public void setBseveUnid(Integer bseveUnid) {
		this.bseveUnid = bseveUnid;
	}

	public Integer getBseveUnid( ) {
		return bseveUnid;
	}

	public void setBseveUuid(String bseveUuid) {
		this.bseveUuid = bseveUuid;
	}

	public String getBseveUuid( ) {
		return bseveUuid;
	}

	public void setBseveOrderItem(String bseveOrderItem) {
		this.bseveOrderItem = bseveOrderItem;
	}

	public String getBseveOrderItem( ) {
		return bseveOrderItem;
	}

	public void setBseveProduct(String bseveProduct) {
		this.bseveProduct = bseveProduct;
	}

	public String getBseveProduct( ) {
		return bseveProduct;
	}

	public void setBseveAttrName(String bseveAttrName) {
		this.bseveAttrName = bseveAttrName;
	}

	public String getBseveAttrName( ) {
		return bseveAttrName;
	}

	public void setBseveUser(String bseveUser) {
		this.bseveUser = bseveUser;
	}

	public String getBseveUser( ) {
		return bseveUser;
	}

	public void setBseveName(String bseveName) {
		this.bseveName = bseveName;
	}

	public String getBseveName( ) {
		return bseveName;
	}

	public void setBseveHead(String bseveHead) {
		this.bseveHead = bseveHead;
	}

	public String getBseveHead( ) {
		return bseveHead;
	}

	public void setBseveContent(String bseveContent) {
		this.bseveContent = bseveContent;
	}

	public String getBseveContent( ) {
		return bseveContent;
	}

	public void setBseveGrade(Integer bseveGrade) {
		this.bseveGrade = bseveGrade;
	}

	public Integer getBseveGrade( ) {
		return bseveGrade;
	}

	public void setBseveStatus(Integer bseveStatus) {
		this.bseveStatus = bseveStatus;
	}

	public Integer getBseveStatus( ) {
		return bseveStatus;
	}

	public void setBsevePic1(String bsevePic1) {
		this.bsevePic1 = bsevePic1;
	}

	public String getBsevePic1( ) {
		return bsevePic1;
	}

	public void setBsevePic2(String bsevePic2) {
		this.bsevePic2 = bsevePic2;
	}

	public String getBsevePic2( ) {
		return bsevePic2;
	}

	public void setBsevePic3(String bsevePic3) {
		this.bsevePic3 = bsevePic3;
	}

	public String getBsevePic3( ) {
		return bsevePic3;
	}

	public void setBsevePic4(String bsevePic4) {
		this.bsevePic4 = bsevePic4;
	}

	public String getBsevePic4( ) {
		return bsevePic4;
	}

	public void setBsevePic5(String bsevePic5) {
		this.bsevePic5 = bsevePic5;
	}

	public String getBsevePic5( ) {
		return bsevePic5;
	}

	public void setBseveCdate(Date bseveCdate) {
		this.bseveCdate = bseveCdate;
	}

	public Date getBseveCdate( ) {
		return bseveCdate;
	}

	public BusiEvaluate( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}