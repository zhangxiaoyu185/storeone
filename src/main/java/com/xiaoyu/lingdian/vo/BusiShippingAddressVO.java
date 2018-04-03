package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiShippingAddress;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 收货地址表
*/
@ApiModel(value = "收货地址")
public class BusiShippingAddressVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "收货地址标识")
	private String bssasUuid;

	/**
	*所属用户
	*/
	@ApiModelProperty(value = "所属用户")
	private String bssasUser;

	/**
	*所属用户名称
	*/
	@ApiModelProperty(value = "用户标识")
	private String bssasUserName;

	/**
	*收货人姓名
	*/
	@ApiModelProperty(value = "收货人姓名")
	private String bssasName;

	/**
	*收货人身份证号
	*/
	@ApiModelProperty(value = "收货人身份证号")
	private String bssasIdCard;

	/**
	*收货人联系方式
	*/
	@ApiModelProperty(value = "收货人联系方式")
	private String bssasMobile;

	/**
	*邮政编码
	*/
	@ApiModelProperty(value = "邮政编码")
	private String bsarsZipCode;

	/**
	*所在省
	*/
	@ApiModelProperty(value = "所在省")
	private String bssasProvince;

	/**
	*所在省名称
	*/
	@ApiModelProperty(value = "所在省名称")
	private String bssasProvinceName;

	/**
	*所在市
	*/
	@ApiModelProperty(value = "所在市")
	private String bssasCity;

	/**
	*所在市名称
	*/
	@ApiModelProperty(value = "所在市名称")
	private String bssasCityName;

	/**
	*所在区
	*/
	@ApiModelProperty(value = "所在区")
	private String bssasCounty;

	/**
	*所在区名称
	*/
	@ApiModelProperty(value = "所在区名称")
	private String bssasCountyName;

	/**
	*详细地址
	*/
	@ApiModelProperty(value = "详细地址")
	private String bssasAddress;

	/**
	*是否默认:1默认2不默认
	*/
	@ApiModelProperty(value = "是否默认:1默认2不默认")
	private Integer bsarsDefault;

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

	public String getBssasUserName() {
		return bssasUserName;
	}

	public void setBssasUserName(String bssasUserName) {
		this.bssasUserName = bssasUserName;
	}

	public BusiShippingAddressVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiShippingAddress po = (BusiShippingAddress) poObj;
		this.bssasUuid = po.getBssasUuid();
		this.bssasUser = po.getBssasUser();
		this.bssasName = po.getBssasName();
		this.bssasIdCard = po.getBssasIdCard();
		this.bssasMobile = po.getBssasMobile();
		this.bsarsZipCode = po.getBsarsZipCode();
		this.bssasProvince = po.getBssasProvince();
		this.bssasProvinceName = po.getBssasProvinceName();
		this.bssasCity = po.getBssasCity();
		this.bssasCityName = po.getBssasCityName();
		this.bssasCounty = po.getBssasCounty();
		this.bssasCountyName = po.getBssasCountyName();
		this.bssasAddress = po.getBssasAddress();
		this.bsarsDefault = po.getBsarsDefault();
	}

}