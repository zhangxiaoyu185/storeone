package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;
/**
* 用户表
*/
@ApiModel(value = "用户信息")
public class CoreUserVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "用户标识")
	private String crusrUuid;

	/**
	*帐户名称
	*/
	@ApiModelProperty(value = "帐户名称")
	private String crusrName;

	/**
	*真实姓名
	*/
	@ApiModelProperty(value = "真实姓名")
	private String crusrCode;

	/**
	*头像附件
	*/
	@ApiModelProperty(value = "头像附件")
	private String crusrHead;

	/**
	*年龄
	*/
	@ApiModelProperty(value = "年龄")
	private Integer crusrAge;

	/**
	*居住省份
	*/
	@ApiModelProperty(value = "居住省份")
	private String crusrProvince;

	/**
	*居住省份名称
	*/
	@ApiModelProperty(value = "居住省份名称")
	private String crusrProvinceName;

	/**
	*居住城市
	*/
	@ApiModelProperty(value = "居住城市")
	private String crusrCity;

	/**
	*居住城市名称
	*/
	@ApiModelProperty(value = "居住城市名称")
	private String crusrCityName;

	/**
	*居住区域
	*/
	@ApiModelProperty(value = "居住区域")
	private String crusrDistrict;

	/**
	*居住区域名称
	*/
	@ApiModelProperty(value = "居住区域名称")
	private String crusrDistrictName;

	/**
	*详细地址
	*/
	@ApiModelProperty(value = "详细地址")
	private String crusrAddress;

	/**
	*电子邮箱
	*/
	@ApiModelProperty(value = "电子邮箱")
	private String crusrEmail;

	/**
	*邮箱验证:1验证0未验证
	*/
	@ApiModelProperty(value = "邮箱验证:1验证0未验证")
	private Integer crusrEmailVerify;

	/**
	*手机号码
	*/
	@ApiModelProperty(value = "手机号码")
	private String crusrMobile;

	/**
	*手机验证:1验证0未验证
	*/
	@ApiModelProperty(value = "手机验证:1验证0未验证")
	private Integer crusrMobileVerify;

	/**
	*状态:1启用,0禁用
	*/
	@ApiModelProperty(value = "状态:1启用,0禁用")
	private Integer crusrStatus;

	/**
	*创建日期
	*/
	@ApiModelProperty(value = "创建日期")
	private String crusrCdate;

	/**
	*修改日期
	*/
	@ApiModelProperty(value = "修改日期")
	private String crusrUdate;

	/**
	*生日
	*/
	@ApiModelProperty(value = "生日")
	private String crusrBirthday;

	/**
	*性别:1男,2女,0其它
	*/
	@ApiModelProperty(value = "性别:1男,2女,0其它")
	private Integer crusrGender;

	/**
	*备注
	*/
	@ApiModelProperty(value = "备注")
	private String crusrRemarks;

	/**
	*金额
	*/
	@ApiModelProperty(value = "金额")
	private Double crusrFee;

	/**
	*订单数量
	*/
	@ApiModelProperty(value = "订单数量")
	private Integer crusrOrder;

	/**
	*待发货订单数量
	*/
	@ApiModelProperty(value = "待发货订单数量")
	private Integer crusrPendingShip;

	/**
	*待收货订单数量
	*/
	@ApiModelProperty(value = "待收货订单数量")
	private Integer crusrPendingRecv;

	/**
	*待评价订单数量
	*/
	@ApiModelProperty(value = "待评价订单数量")
	private Integer crusrPendingEval;

	/**
	*已完成订单数量
	*/
	@ApiModelProperty(value = "已完成订单数量")
	private Integer crusrFinished;

	/**
	*取消订单数量
	*/
	@ApiModelProperty(value = "取消订单数量")
	private Integer crusrCancelled;

	public String getCrusrUuid() {
		return crusrUuid;
	}

	public void setCrusrUuid(String crusrUuid) {
		this.crusrUuid = crusrUuid;
	}

	public String getCrusrName() {
		return crusrName;
	}

	public void setCrusrName(String crusrName) {
		this.crusrName = crusrName;
	}

	public String getCrusrCode() {
		return crusrCode;
	}

	public void setCrusrCode(String crusrCode) {
		this.crusrCode = crusrCode;
	}

	public String getCrusrHead() {
		return crusrHead;
	}

	public void setCrusrHead(String crusrHead) {
		this.crusrHead = crusrHead;
	}

	public Integer getCrusrAge() {
		return crusrAge;
	}

	public void setCrusrAge(Integer crusrAge) {
		this.crusrAge = crusrAge;
	}

	public String getCrusrProvince() {
		return crusrProvince;
	}

	public void setCrusrProvince(String crusrProvince) {
		this.crusrProvince = crusrProvince;
	}

	public String getCrusrProvinceName() {
		return crusrProvinceName;
	}

	public void setCrusrProvinceName(String crusrProvinceName) {
		this.crusrProvinceName = crusrProvinceName;
	}

	public String getCrusrCity() {
		return crusrCity;
	}

	public void setCrusrCity(String crusrCity) {
		this.crusrCity = crusrCity;
	}

	public String getCrusrCityName() {
		return crusrCityName;
	}

	public void setCrusrCityName(String crusrCityName) {
		this.crusrCityName = crusrCityName;
	}

	public String getCrusrDistrict() {
		return crusrDistrict;
	}

	public void setCrusrDistrict(String crusrDistrict) {
		this.crusrDistrict = crusrDistrict;
	}

	public String getCrusrDistrictName() {
		return crusrDistrictName;
	}

	public void setCrusrDistrictName(String crusrDistrictName) {
		this.crusrDistrictName = crusrDistrictName;
	}

	public String getCrusrAddress() {
		return crusrAddress;
	}

	public void setCrusrAddress(String crusrAddress) {
		this.crusrAddress = crusrAddress;
	}

	public String getCrusrEmail() {
		return crusrEmail;
	}

	public void setCrusrEmail(String crusrEmail) {
		this.crusrEmail = crusrEmail;
	}

	public Integer getCrusrEmailVerify() {
		return crusrEmailVerify;
	}

	public void setCrusrEmailVerify(Integer crusrEmailVerify) {
		this.crusrEmailVerify = crusrEmailVerify;
	}

	public String getCrusrMobile() {
		return crusrMobile;
	}

	public void setCrusrMobile(String crusrMobile) {
		this.crusrMobile = crusrMobile;
	}

	public Integer getCrusrMobileVerify() {
		return crusrMobileVerify;
	}

	public void setCrusrMobileVerify(Integer crusrMobileVerify) {
		this.crusrMobileVerify = crusrMobileVerify;
	}

	public Integer getCrusrStatus() {
		return crusrStatus;
	}

	public void setCrusrStatus(Integer crusrStatus) {
		this.crusrStatus = crusrStatus;
	}

	public String getCrusrCdate() {
		return crusrCdate;
	}

	public void setCrusrCdate(String crusrCdate) {
		this.crusrCdate = crusrCdate;
	}

	public String getCrusrUdate() {
		return crusrUdate;
	}

	public void setCrusrUdate(String crusrUdate) {
		this.crusrUdate = crusrUdate;
	}

	public String getCrusrBirthday() {
		return crusrBirthday;
	}

	public void setCrusrBirthday(String crusrBirthday) {
		this.crusrBirthday = crusrBirthday;
	}

	public Integer getCrusrGender() {
		return crusrGender;
	}

	public void setCrusrGender(Integer crusrGender) {
		this.crusrGender = crusrGender;
	}

	public String getCrusrRemarks() {
		return crusrRemarks;
	}

	public void setCrusrRemarks(String crusrRemarks) {
		this.crusrRemarks = crusrRemarks;
	}

	public Double getCrusrFee() {
		return crusrFee;
	}

	public void setCrusrFee(Double crusrFee) {
		this.crusrFee = crusrFee;
	}

	public Integer getCrusrOrder() {
		return crusrOrder;
	}

	public void setCrusrOrder(Integer crusrOrder) {
		this.crusrOrder = crusrOrder;
	}

	public Integer getCrusrPendingShip() {
		return crusrPendingShip;
	}

	public void setCrusrPendingShip(Integer crusrPendingShip) {
		this.crusrPendingShip = crusrPendingShip;
	}

	public Integer getCrusrPendingRecv() {
		return crusrPendingRecv;
	}

	public void setCrusrPendingRecv(Integer crusrPendingRecv) {
		this.crusrPendingRecv = crusrPendingRecv;
	}

	public Integer getCrusrPendingEval() {
		return crusrPendingEval;
	}

	public void setCrusrPendingEval(Integer crusrPendingEval) {
		this.crusrPendingEval = crusrPendingEval;
	}

	public Integer getCrusrFinished() {
		return crusrFinished;
	}

	public void setCrusrFinished(Integer crusrFinished) {
		this.crusrFinished = crusrFinished;
	}

	public Integer getCrusrCancelled() {
		return crusrCancelled;
	}

	public void setCrusrCancelled(Integer crusrCancelled) {
		this.crusrCancelled = crusrCancelled;
	}

	public CoreUserVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreUser po = (CoreUser) poObj;
		this.crusrUuid = po.getCrusrUuid();
		this.crusrName = po.getCrusrName();
		this.crusrCode = po.getCrusrCode();
		this.crusrHead = po.getCrusrHead();
		this.crusrAge = po.getCrusrAge();
		this.crusrProvince = po.getCrusrProvince();
		this.crusrProvinceName = po.getCrusrProvinceName();
		this.crusrCity = po.getCrusrCity();
		this.crusrCityName = po.getCrusrCityName();
		this.crusrDistrict = po.getCrusrDistrict();
		this.crusrDistrictName = po.getCrusrDistrictName();
		this.crusrAddress = po.getCrusrAddress();
		this.crusrEmail = po.getCrusrEmail();
		this.crusrEmailVerify = po.getCrusrEmailVerify();
		this.crusrMobile = po.getCrusrMobile();
		this.crusrMobileVerify = po.getCrusrMobileVerify();
		this.crusrFee = po.getCrusrFee();
		this.crusrStatus = po.getCrusrStatus();
		this.crusrCdate = po.getCrusrCdate()!=null?DateUtil.formatDefaultDate(po.getCrusrCdate()):"";
		this.crusrUdate = po.getCrusrUdate()!=null?DateUtil.formatDefaultDate(po.getCrusrUdate()):"";
		this.crusrBirthday = po.getCrusrBirthday();
		this.crusrGender = po.getCrusrGender();
		this.crusrRemarks = po.getCrusrRemarks();	
		this.crusrOrder = po.getCrusrOrder();
		this.crusrPendingShip = po.getCrusrPendingShip();
		this.crusrPendingRecv = po.getCrusrPendingRecv();
		this.crusrPendingEval = po.getCrusrPendingEval();
		this.crusrFinished = po.getCrusrFinished();
		this.crusrCancelled = po.getCrusrCancelled();
	}

}