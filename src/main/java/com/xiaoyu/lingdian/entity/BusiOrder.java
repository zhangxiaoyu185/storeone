package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 订单表
*/
public class BusiOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsorrUnid;

	/**
	*标识UUID
	*/
	private String bsorrUuid;

	/**
	*所属用户
	*/
	private String bsorrUser;

	/**
	*订单总价
	*/
	private Double bsorrTotalValue;

	/**
	*商品标识
	*/
	private String bsorrProduct;

	/**
	*商品名称
	*/
	private String bsorrProductName;

	/**
	*封面图
	*/
	private String bsorrPic;

	/**
	*标签集合（七|赠|正）
	*/
	private String bsorrTags;

	/**
	*购买数量
	*/
	private Integer bsorrQuantity;

	/**
	*快递费用
	*/
	private Double bsorrExpress;

	/**
	*商品单价
	*/
	private Double bsorrPrice;

	/**
	*快递单号
	*/
	private String bsorrExpno;

	/**
	*快递公司编号
	*/
	private String bsorrExpressCode;

	/**
	*快递公司名称
	*/
	private String bsorrExpressName;

	/**
	*上一次查询时间
	*/
	private Date bsorrLastDate;

	/**
	*买家留言（下单时使用）
	*/
	private String bsorrBuyerMemo;

	/**
	*支付状态:1待发货2待收货3待评价4已完成5已删除
	*/
	private Integer bsorrStatus;

	/**
	*创建时间(下单时间)
	*/
	private Date bsorrCdate;

	/**
	*更新时间
	*/
	private Date bsorrUdate;

	/**
	*发货时间
	*/
	private Date bsorrShippingTime;

	/**
	*确认收货时间
	*/
	private Date bsorrRecvTime;

	/**
	*评价时间
	*/
	private Date bsorrEvaluateTime;

	/**
	*交易完成时间
	*/
	private Date bsorrFinishTime;

	/**
	*删除订单时间
	*/
	private Date bsorrDelTime;

	/**
	*收货人姓名
	*/
	private String bsorrName;

	/**
	*收货人身份证号
	*/
	private String bsorrIdCard;

	/**
	*收货人联系方式
	*/
	private String bsorrMobile;

	/**
	*邮政编码
	*/
	private String bsorrZipCode;

	/**
	*所在省
	*/
	private String bsorrProvince;

	/**
	*所在省名称
	*/
	private String bsorrProvinceName;

	/**
	*所在市
	*/
	private String bsorrCity;

	/**
	*所在市名称
	*/
	private String bsorrCityName;

	/**
	*所在区
	*/
	private String bsorrCounty;

	/**
	*所在区名称
	*/
	private String bsorrCountyName;

	/**
	*详细地址
	*/
	private String bsorrAddress;

	public void setBsorrUnid(Integer bsorrUnid) {
		this.bsorrUnid = bsorrUnid;
	}

	public Integer getBsorrUnid( ) {
		return bsorrUnid;
	}

	public void setBsorrUuid(String bsorrUuid) {
		this.bsorrUuid = bsorrUuid;
	}

	public String getBsorrUuid( ) {
		return bsorrUuid;
	}

	public void setBsorrUser(String bsorrUser) {
		this.bsorrUser = bsorrUser;
	}

	public String getBsorrUser( ) {
		return bsorrUser;
	}

	public void setBsorrTotalValue(Double bsorrTotalValue) {
		this.bsorrTotalValue = bsorrTotalValue;
	}

	public Double getBsorrTotalValue( ) {
		return bsorrTotalValue;
	}

	public void setBsorrProduct(String bsorrProduct) {
		this.bsorrProduct = bsorrProduct;
	}

	public String getBsorrProduct( ) {
		return bsorrProduct;
	}

	public void setBsorrProductName(String bsorrProductName) {
		this.bsorrProductName = bsorrProductName;
	}

	public String getBsorrProductName( ) {
		return bsorrProductName;
	}

	public void setBsorrPic(String bsorrPic) {
		this.bsorrPic = bsorrPic;
	}

	public String getBsorrPic( ) {
		return bsorrPic;
	}

	public void setBsorrTags(String bsorrTags) {
		this.bsorrTags = bsorrTags;
	}

	public String getBsorrTags( ) {
		return bsorrTags;
	}

	public void setBsorrQuantity(Integer bsorrQuantity) {
		this.bsorrQuantity = bsorrQuantity;
	}

	public Integer getBsorrQuantity( ) {
		return bsorrQuantity;
	}

	public void setBsorrExpress(Double bsorrExpress) {
		this.bsorrExpress = bsorrExpress;
	}

	public Double getBsorrExpress( ) {
		return bsorrExpress;
	}

	public void setBsorrPrice(Double bsorrPrice) {
		this.bsorrPrice = bsorrPrice;
	}

	public Double getBsorrPrice( ) {
		return bsorrPrice;
	}

	public void setBsorrExpno(String bsorrExpno) {
		this.bsorrExpno = bsorrExpno;
	}

	public String getBsorrExpno( ) {
		return bsorrExpno;
	}

	public void setBsorrExpressCode(String bsorrExpressCode) {
		this.bsorrExpressCode = bsorrExpressCode;
	}

	public String getBsorrExpressCode( ) {
		return bsorrExpressCode;
	}

	public void setBsorrExpressName(String bsorrExpressName) {
		this.bsorrExpressName = bsorrExpressName;
	}

	public String getBsorrExpressName( ) {
		return bsorrExpressName;
	}

	public void setBsorrLastDate(Date bsorrLastDate) {
		this.bsorrLastDate = bsorrLastDate;
	}

	public Date getBsorrLastDate( ) {
		return bsorrLastDate;
	}

	public void setBsorrBuyerMemo(String bsorrBuyerMemo) {
		this.bsorrBuyerMemo = bsorrBuyerMemo;
	}

	public String getBsorrBuyerMemo( ) {
		return bsorrBuyerMemo;
	}

	public void setBsorrStatus(Integer bsorrStatus) {
		this.bsorrStatus = bsorrStatus;
	}

	public Integer getBsorrStatus( ) {
		return bsorrStatus;
	}

	public void setBsorrCdate(Date bsorrCdate) {
		this.bsorrCdate = bsorrCdate;
	}

	public Date getBsorrCdate( ) {
		return bsorrCdate;
	}

	public void setBsorrUdate(Date bsorrUdate) {
		this.bsorrUdate = bsorrUdate;
	}

	public Date getBsorrUdate( ) {
		return bsorrUdate;
	}

	public void setBsorrShippingTime(Date bsorrShippingTime) {
		this.bsorrShippingTime = bsorrShippingTime;
	}

	public Date getBsorrShippingTime( ) {
		return bsorrShippingTime;
	}

	public void setBsorrRecvTime(Date bsorrRecvTime) {
		this.bsorrRecvTime = bsorrRecvTime;
	}

	public Date getBsorrRecvTime( ) {
		return bsorrRecvTime;
	}

	public void setBsorrEvaluateTime(Date bsorrEvaluateTime) {
		this.bsorrEvaluateTime = bsorrEvaluateTime;
	}

	public Date getBsorrEvaluateTime( ) {
		return bsorrEvaluateTime;
	}

	public void setBsorrFinishTime(Date bsorrFinishTime) {
		this.bsorrFinishTime = bsorrFinishTime;
	}

	public Date getBsorrFinishTime( ) {
		return bsorrFinishTime;
	}

	public void setBsorrDelTime(Date bsorrDelTime) {
		this.bsorrDelTime = bsorrDelTime;
	}

	public Date getBsorrDelTime( ) {
		return bsorrDelTime;
	}

	public void setBsorrName(String bsorrName) {
		this.bsorrName = bsorrName;
	}

	public String getBsorrName( ) {
		return bsorrName;
	}

	public void setBsorrIdCard(String bsorrIdCard) {
		this.bsorrIdCard = bsorrIdCard;
	}

	public String getBsorrIdCard( ) {
		return bsorrIdCard;
	}

	public void setBsorrMobile(String bsorrMobile) {
		this.bsorrMobile = bsorrMobile;
	}

	public String getBsorrMobile( ) {
		return bsorrMobile;
	}

	public void setBsorrZipCode(String bsorrZipCode) {
		this.bsorrZipCode = bsorrZipCode;
	}

	public String getBsorrZipCode( ) {
		return bsorrZipCode;
	}

	public void setBsorrProvince(String bsorrProvince) {
		this.bsorrProvince = bsorrProvince;
	}

	public String getBsorrProvince( ) {
		return bsorrProvince;
	}

	public void setBsorrProvinceName(String bsorrProvinceName) {
		this.bsorrProvinceName = bsorrProvinceName;
	}

	public String getBsorrProvinceName( ) {
		return bsorrProvinceName;
	}

	public void setBsorrCity(String bsorrCity) {
		this.bsorrCity = bsorrCity;
	}

	public String getBsorrCity( ) {
		return bsorrCity;
	}

	public void setBsorrCityName(String bsorrCityName) {
		this.bsorrCityName = bsorrCityName;
	}

	public String getBsorrCityName( ) {
		return bsorrCityName;
	}

	public void setBsorrCounty(String bsorrCounty) {
		this.bsorrCounty = bsorrCounty;
	}

	public String getBsorrCounty( ) {
		return bsorrCounty;
	}

	public void setBsorrCountyName(String bsorrCountyName) {
		this.bsorrCountyName = bsorrCountyName;
	}

	public String getBsorrCountyName( ) {
		return bsorrCountyName;
	}

	public void setBsorrAddress(String bsorrAddress) {
		this.bsorrAddress = bsorrAddress;
	}

	public String getBsorrAddress( ) {
		return bsorrAddress;
	}

	public BusiOrder( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}