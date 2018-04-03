package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 订单表
*/
@ApiModel(value = "订单")
public class BusiOrderVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "订单标识")
	private String bsorrUuid;

	/**
	*所属用户
	*/
	@ApiModelProperty(value = "所属用户")
	private String bsorrUser;

	/**
	*所属用户名称
	*/
	@ApiModelProperty(value = "所属用户名称")
	private String bsorrUserName;
	
	/**
	*订单总价
	*/
	@ApiModelProperty(value = "订单总价")
	private Double bsorrTotalValue;

	/**
	*商品标识
	*/
	@ApiModelProperty(value = "商品标识")
	private String bsorrProduct;

	/**
	*商品名称
	*/
	@ApiModelProperty(value = "商品名称")
	private String bsorrProductName;

	/**
	*封面图
	*/
	@ApiModelProperty(value = "封面图")
	private String bsorrPic;

	/**
	*标签集合（七|赠|正）
	*/
	@ApiModelProperty(value = "标签集合（七|赠|正）")
	private String bsorrTags;

	/**
	*购买数量
	*/
	@ApiModelProperty(value = "购买数量")
	private Integer bsorrQuantity;

	/**
	*快递费用
	*/
	@ApiModelProperty(value = "快递费用")
	private Double bsorrExpress;

	/**
	*商品单价
	*/
	@ApiModelProperty(value = "商品单价")
	private Double bsorrPrice;

	/**
	*快递单号
	*/
	@ApiModelProperty(value = "快递单号")
	private String bsorrExpno;

	/**
	*快递公司编号
	*/
	@ApiModelProperty(value = "快递公司编号")
	private String bsorrExpressCode;

	/**
	*快递公司名称
	*/
	@ApiModelProperty(value = "快递公司名称")
	private String bsorrExpressName;

	/**
	*上一次查询时间
	*/
	@ApiModelProperty(value = "上一次查询时间")
	private String bsorrLastDate;

	/**
	*买家留言（下单时使用）
	*/
	@ApiModelProperty(value = "买家留言（下单时使用）")
	private String bsorrBuyerMemo;

	/**
	*支付状态:1待发货2待收货3待评价4已完成5已删除
	*/
	@ApiModelProperty(value = "支付状态:1待发货2待收货3待评价4已完成5已删除")
	private Integer bsorrStatus;

	/**
	*创建时间(下单时间)
	*/
	@ApiModelProperty(value = "创建时间(下单时间)")
	private String bsorrCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bsorrUdate;

	/**
	*发货时间
	*/	
	@ApiModelProperty(value = "发货时间")
	private String bsorrShippingTime;

	/**
	*确认收货时间
	*/
	@ApiModelProperty(value = "确认收货时间")
	private String bsorrRecvTime;

	/**
	*评价时间
	*/
	@ApiModelProperty(value = "评价时间")
	private String bsorrEvaluateTime;

	/**
	*交易完成时间
	*/
	@ApiModelProperty(value = "交易完成时间")
	private String bsorrFinishTime;

	/**
	*删除订单时间
	*/
	@ApiModelProperty(value = "删除订单时间")
	private String bsorrDelTime;

	/**
	*收货人姓名
	*/
	@ApiModelProperty(value = "收货人姓名")
	private String bsorrName;

	/**
	*收货人身份证号
	*/
	@ApiModelProperty(value = "收货人身份证号")
	private String bsorrIdCard;

	/**
	*收货人联系方式
	*/
	@ApiModelProperty(value = "收货人联系方式")
	private String bsorrMobile;

	/**
	*邮政编码
	*/
	@ApiModelProperty(value = "邮政编码")
	private String bsorrZipCode;

	/**
	*所在省
	*/
	@ApiModelProperty(value = "所在省")
	private String bsorrProvince;

	/**
	*所在省名称
	*/
	@ApiModelProperty(value = "所在省名称")
	private String bsorrProvinceName;

	/**
	*所在市
	*/
	@ApiModelProperty(value = "所在市")
	private String bsorrCity;

	/**
	*所在市名称
	*/
	@ApiModelProperty(value = "所在市名称")
	private String bsorrCityName;

	/**
	*所在区
	*/
	@ApiModelProperty(value = "所在区")
	private String bsorrCounty;

	/**
	*所在区名称
	*/
	@ApiModelProperty(value = "所在区名称")
	private String bsorrCountyName;

	/**
	*详细地址
	*/
	@ApiModelProperty(value = "详细地址")
	private String bsorrAddress;

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

	public String getBsorrUserName() {
		return bsorrUserName;
	}

	public void setBsorrUserName(String bsorrUserName) {
		this.bsorrUserName = bsorrUserName;
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

	public void setBsorrLastDate(String bsorrLastDate) {
		this.bsorrLastDate = bsorrLastDate;
	}

	public String getBsorrLastDate( ) {
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

	public void setBsorrCdate(String bsorrCdate) {
		this.bsorrCdate = bsorrCdate;
	}

	public String getBsorrCdate( ) {
		return bsorrCdate;
	}

	public void setBsorrUdate(String bsorrUdate) {
		this.bsorrUdate = bsorrUdate;
	}

	public String getBsorrUdate( ) {
		return bsorrUdate;
	}

	public void setBsorrShippingTime(String bsorrShippingTime) {
		this.bsorrShippingTime = bsorrShippingTime;
	}

	public String getBsorrShippingTime( ) {
		return bsorrShippingTime;
	}

	public void setBsorrRecvTime(String bsorrRecvTime) {
		this.bsorrRecvTime = bsorrRecvTime;
	}

	public String getBsorrRecvTime( ) {
		return bsorrRecvTime;
	}

	public void setBsorrEvaluateTime(String bsorrEvaluateTime) {
		this.bsorrEvaluateTime = bsorrEvaluateTime;
	}

	public String getBsorrEvaluateTime( ) {
		return bsorrEvaluateTime;
	}

	public void setBsorrFinishTime(String bsorrFinishTime) {
		this.bsorrFinishTime = bsorrFinishTime;
	}

	public String getBsorrFinishTime( ) {
		return bsorrFinishTime;
	}

	public void setBsorrDelTime(String bsorrDelTime) {
		this.bsorrDelTime = bsorrDelTime;
	}

	public String getBsorrDelTime( ) {
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

	public BusiOrderVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiOrder po = (BusiOrder) poObj;
		this.bsorrUuid = po.getBsorrUuid();
		this.bsorrUser = po.getBsorrUser();
		this.bsorrTotalValue = po.getBsorrTotalValue();
		this.bsorrProduct = po.getBsorrProduct();
		this.bsorrProductName = po.getBsorrProductName();
		this.bsorrPic = po.getBsorrPic();
		this.bsorrTags = po.getBsorrTags();
		this.bsorrQuantity = po.getBsorrQuantity();
		this.bsorrExpress = po.getBsorrExpress();
		this.bsorrPrice = po.getBsorrPrice();
		this.bsorrExpno = po.getBsorrExpno();
		this.bsorrExpressCode = po.getBsorrExpressCode();
		this.bsorrExpressName = po.getBsorrExpressName();
		this.bsorrLastDate = po.getBsorrLastDate()!=null?DateUtil.formatDefaultDate(po.getBsorrLastDate()):"";
		this.bsorrBuyerMemo = po.getBsorrBuyerMemo();
		this.bsorrStatus = po.getBsorrStatus();
		this.bsorrCdate = po.getBsorrCdate()!=null?DateUtil.formatDefaultDate(po.getBsorrCdate()):"";
		this.bsorrUdate = po.getBsorrUdate()!=null?DateUtil.formatDefaultDate(po.getBsorrUdate()):"";
		this.bsorrShippingTime = po.getBsorrShippingTime()!=null?DateUtil.formatDefaultDate(po.getBsorrShippingTime()):"";
		this.bsorrRecvTime = po.getBsorrRecvTime()!=null?DateUtil.formatDefaultDate(po.getBsorrRecvTime()):"";
		this.bsorrEvaluateTime = po.getBsorrEvaluateTime()!=null?DateUtil.formatDefaultDate(po.getBsorrEvaluateTime()):"";
		this.bsorrFinishTime = po.getBsorrFinishTime()!=null?DateUtil.formatDefaultDate(po.getBsorrFinishTime()):"";
		this.bsorrDelTime = po.getBsorrDelTime()!=null?DateUtil.formatDefaultDate(po.getBsorrDelTime()):"";
		this.bsorrName = po.getBsorrName();
		this.bsorrIdCard = po.getBsorrIdCard();
		this.bsorrMobile = po.getBsorrMobile();
		this.bsorrZipCode = po.getBsorrZipCode();
		this.bsorrProvince = po.getBsorrProvince();
		this.bsorrProvinceName = po.getBsorrProvinceName();
		this.bsorrCity = po.getBsorrCity();
		this.bsorrCityName = po.getBsorrCityName();
		this.bsorrCounty = po.getBsorrCounty();
		this.bsorrCountyName = po.getBsorrCountyName();
		this.bsorrAddress = po.getBsorrAddress();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}