package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 收货地址表
*/
public class BusiShippingAddress extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssasUnid;

	/**
	*标识UUID
	*/
	private String bssasUuid;

	/**
	*所属用户
	*/
	private String bssasUser;

	/**
	*收货人姓名
	*/
	private String bssasName;

	/**
	*收货人身份证号
	*/
	private String bssasIdCard;

	/**
	*收货人联系方式
	*/
	private String bssasMobile;

	/**
	*邮政编码
	*/
	private String bsarsZipCode;

	/**
	*所在省
	*/
	private String bssasProvince;

	/**
	*所在省名称
	*/
	private String bssasProvinceName;

	/**
	*所在市
	*/
	private String bssasCity;

	/**
	*所在市名称
	*/
	private String bssasCityName;

	/**
	*所在区
	*/
	private String bssasCounty;

	/**
	*所在区名称
	*/
	private String bssasCountyName;

	/**
	*详细地址
	*/
	private String bssasAddress;

	/**
	*是否默认:1默认2不默认
	*/
	private Integer bsarsDefault;

	public void setBssasUnid(Integer bssasUnid) {
		this.bssasUnid = bssasUnid;
	}

	public Integer getBssasUnid( ) {
		return bssasUnid;
	}

	public void setBssasUuid(String bssasUuid) {
		this.bssasUuid = bssasUuid;
	}

	public String getBssasUuid( ) {
		return bssasUuid;
	}

	public void setBssasUser(String bssasUser) {
		this.bssasUser = bssasUser;
	}

	public String getBssasUser( ) {
		return bssasUser;
	}

	public void setBssasName(String bssasName) {
		this.bssasName = bssasName;
	}

	public String getBssasName( ) {
		return bssasName;
	}

	public void setBssasIdCard(String bssasIdCard) {
		this.bssasIdCard = bssasIdCard;
	}

	public String getBssasIdCard( ) {
		return bssasIdCard;
	}

	public void setBssasMobile(String bssasMobile) {
		this.bssasMobile = bssasMobile;
	}

	public String getBssasMobile( ) {
		return bssasMobile;
	}

	public void setBsarsZipCode(String bsarsZipCode) {
		this.bsarsZipCode = bsarsZipCode;
	}

	public String getBsarsZipCode( ) {
		return bsarsZipCode;
	}

	public void setBssasProvince(String bssasProvince) {
		this.bssasProvince = bssasProvince;
	}

	public String getBssasProvince( ) {
		return bssasProvince;
	}

	public void setBssasProvinceName(String bssasProvinceName) {
		this.bssasProvinceName = bssasProvinceName;
	}

	public String getBssasProvinceName( ) {
		return bssasProvinceName;
	}

	public void setBssasCity(String bssasCity) {
		this.bssasCity = bssasCity;
	}

	public String getBssasCity( ) {
		return bssasCity;
	}

	public void setBssasCityName(String bssasCityName) {
		this.bssasCityName = bssasCityName;
	}

	public String getBssasCityName( ) {
		return bssasCityName;
	}

	public void setBssasCounty(String bssasCounty) {
		this.bssasCounty = bssasCounty;
	}

	public String getBssasCounty( ) {
		return bssasCounty;
	}

	public void setBssasCountyName(String bssasCountyName) {
		this.bssasCountyName = bssasCountyName;
	}

	public String getBssasCountyName( ) {
		return bssasCountyName;
	}

	public void setBssasAddress(String bssasAddress) {
		this.bssasAddress = bssasAddress;
	}

	public String getBssasAddress( ) {
		return bssasAddress;
	}

	public void setBsarsDefault(Integer bsarsDefault) {
		this.bsarsDefault = bsarsDefault;
	}

	public Integer getBsarsDefault( ) {
		return bsarsDefault;
	}

	public BusiShippingAddress( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}