package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
 * 用户表
 */
public class CoreUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 标识UNID
	 */
	private Integer crusrUnid;

	/**
	 * 标识UUID
	 */
	private String crusrUuid;

	/**
	 * 帐户名称
	 */
	private String crusrName;

	/**
	 * 真实姓名
	 */
	private String crusrCode;

	/**
	 * 登录密码
	 */
	private String crusrPassword;

	/**
	 * 头像附件
	 */
	private String crusrHead;

	/**
	 * 年龄
	 */
	private Integer crusrAge;

	/**
	 * 居住省份
	 */
	private String crusrProvince;

	/**
	 * 居住省份名称
	 */
	private String crusrProvinceName;

	/**
	 * 居住城市
	 */
	private String crusrCity;

	/**
	 * 居住城市名称
	 */
	private String crusrCityName;

	/**
	 * 居住区域
	 */
	private String crusrDistrict;

	/**
	 * 居住区域名称
	 */
	private String crusrDistrictName;

	/**
	 * 详细地址
	 */
	private String crusrAddress;

	/**
	 * 电子邮箱
	 */
	private String crusrEmail;

	/**
	 * 邮箱验证:1验证0未验证
	 */
	private Integer crusrEmailVerify;

	/**
	 * 手机号码
	 */
	private String crusrMobile;

	/**
	 * 手机验证:1验证0未验证
	 */
	private Integer crusrMobileVerify;

	/**
	 * 状态:1启用,0禁用
	 */
	private Integer crusrStatus;

	/**
	 * 创建日期
	 */
	private Date crusrCdate;

	/**
	 * 修改日期
	 */
	private Date crusrUdate;

	/**
	 * 生日
	 */
	private String crusrBirthday;

	/**
	 * 性别:1男,2女,0其它
	 */
	private Integer crusrGender;

	/**
	 * 备注
	 */
	private String crusrRemarks;

	/**
	 * 金额
	 */
	private Double crusrFee;

	/**
	 * 订单数量
	 */
	private Integer crusrOrder;

	/**
	 * 待发货订单数量
	 */
	private Integer crusrPendingShip;

	/**
	 * 待收货订单数量
	 */
	private Integer crusrPendingRecv;

	/**
	 * 待评价订单数量
	 */
	private Integer crusrPendingEval;

	/**
	 * 已完成订单数量
	 */
	private Integer crusrFinished;

	/**
	 * 取消订单数量
	 */
	private Integer crusrCancelled;

	public Integer getCrusrUnid() {
		return crusrUnid;
	}

	public void setCrusrUnid(Integer crusrUnid) {
		this.crusrUnid = crusrUnid;
	}

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

	public String getCrusrPassword() {
		return crusrPassword;
	}

	public void setCrusrPassword(String crusrPassword) {
		this.crusrPassword = crusrPassword;
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

	public Date getCrusrCdate() {
		return crusrCdate;
	}

	public void setCrusrCdate(Date crusrCdate) {
		this.crusrCdate = crusrCdate;
	}

	public Date getCrusrUdate() {
		return crusrUdate;
	}

	public void setCrusrUdate(Date crusrUdate) {
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

	public CoreUser() {
	}

	// <=================定制内容开始==============
	// ==================定制内容结束==============>

}