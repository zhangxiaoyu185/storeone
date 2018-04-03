package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiExpressConfig;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 快递鸟配置表
*/
@ApiModel(value = "快递鸟配置")
public class BusiExpressConfigVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "快递鸟配置标识")
	private String bsecgUuid;

	/**
	*商户标识
	*/
	@ApiModelProperty(value = "商户标识")
	private String bsecgKdnCode;

	/**
	*API_KEY
	*/
	@ApiModelProperty(value = "API_KEY")
	private String bsecgApiKey;

	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bsecgCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bsecgUdate;

	public void setBsecgUuid(String bsecgUuid) {
		this.bsecgUuid = bsecgUuid;
	}

	public String getBsecgUuid( ) {
		return bsecgUuid;
	}

	public void setBsecgKdnCode(String bsecgKdnCode) {
		this.bsecgKdnCode = bsecgKdnCode;
	}

	public String getBsecgKdnCode( ) {
		return bsecgKdnCode;
	}

	public void setBsecgApiKey(String bsecgApiKey) {
		this.bsecgApiKey = bsecgApiKey;
	}

	public String getBsecgApiKey( ) {
		return bsecgApiKey;
	}

	public void setBsecgCdate(String bsecgCdate) {
		this.bsecgCdate = bsecgCdate;
	}

	public String getBsecgCdate( ) {
		return bsecgCdate;
	}

	public void setBsecgUdate(String bsecgUdate) {
		this.bsecgUdate = bsecgUdate;
	}

	public String getBsecgUdate( ) {
		return bsecgUdate;
	}

	public BusiExpressConfigVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiExpressConfig po = (BusiExpressConfig) poObj;
		this.bsecgUuid = po.getBsecgUuid();
		this.bsecgKdnCode = po.getBsecgKdnCode();
		this.bsecgApiKey = po.getBsecgApiKey();
		this.bsecgCdate = po.getBsecgCdate()!=null?DateUtil.formatDefaultDate(po.getBsecgCdate()):"";
		this.bsecgUdate = po.getBsecgUdate()!=null?DateUtil.formatDefaultDate(po.getBsecgUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}