package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiBanner;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 导航商品配置表
*/
@ApiModel(value = "导航商品配置")
public class BusiBannerVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "banner标识")
	private String bsbarUuid;

	/**
	*链接
	*/
	@ApiModelProperty(value = "链接")
	private String bsbarLink;

	/**
	*BANNER图
	*/
	@ApiModelProperty(value = "BANNER图")
	private String bsbarPic;

	/**
	*顺序
	*/
	@ApiModelProperty(value = "顺序")
	private Integer bsbarOrd;

	/**
	*状态:1启动0禁用
	*/
	@ApiModelProperty(value = "状态:1启动0禁用")
	private Integer bsbarStatus;

	/**
	*描述
	*/
	@ApiModelProperty(value = "描述")
	private String bsbarDesc;

	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bsbarCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bsbarUdate;

	public void setBsbarUuid(String bsbarUuid) {
		this.bsbarUuid = bsbarUuid;
	}

	public String getBsbarUuid( ) {
		return bsbarUuid;
	}

	public void setBsbarLink(String bsbarLink) {
		this.bsbarLink = bsbarLink;
	}

	public String getBsbarLink( ) {
		return bsbarLink;
	}

	public void setBsbarPic(String bsbarPic) {
		this.bsbarPic = bsbarPic;
	}

	public String getBsbarPic( ) {
		return bsbarPic;
	}

	public void setBsbarOrd(Integer bsbarOrd) {
		this.bsbarOrd = bsbarOrd;
	}

	public Integer getBsbarOrd( ) {
		return bsbarOrd;
	}

	public void setBsbarStatus(Integer bsbarStatus) {
		this.bsbarStatus = bsbarStatus;
	}

	public Integer getBsbarStatus( ) {
		return bsbarStatus;
	}

	public void setBsbarDesc(String bsbarDesc) {
		this.bsbarDesc = bsbarDesc;
	}

	public String getBsbarDesc( ) {
		return bsbarDesc;
	}

	public void setBsbarCdate(String bsbarCdate) {
		this.bsbarCdate = bsbarCdate;
	}

	public String getBsbarCdate( ) {
		return bsbarCdate;
	}

	public void setBsbarUdate(String bsbarUdate) {
		this.bsbarUdate = bsbarUdate;
	}

	public String getBsbarUdate( ) {
		return bsbarUdate;
	}

	public BusiBannerVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiBanner po = (BusiBanner) poObj;
		this.bsbarUuid = po.getBsbarUuid();
		this.bsbarLink = po.getBsbarLink();
		this.bsbarPic = po.getBsbarPic();
		this.bsbarOrd = po.getBsbarOrd();
		this.bsbarStatus = po.getBsbarStatus();
		this.bsbarDesc = po.getBsbarDesc();
		this.bsbarCdate = po.getBsbarCdate()!=null?DateUtil.formatDefaultDate(po.getBsbarCdate()):"";
		this.bsbarUdate = po.getBsbarUdate()!=null?DateUtil.formatDefaultDate(po.getBsbarUdate()):"";
	}

}