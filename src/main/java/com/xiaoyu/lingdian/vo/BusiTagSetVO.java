package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiTagSet;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 标签配置表
*/
@ApiModel(value = "标签配置")
public class BusiTagSetVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "标签配置标识")
	private String bststUuid;

	/**
	*标签名称
	*/
	@ApiModelProperty(value = "标签名称")
	private String bststName;

	/**
	*标签简介
	*/
	@ApiModelProperty(value = "标签简介")
	private String bststDesc;

	/**
	*标签图标字
	*/
	@ApiModelProperty(value = "标签图标字")
	private String bststIcon;

	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bststCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bststUdate;

	public void setBststUuid(String bststUuid) {
		this.bststUuid = bststUuid;
	}

	public String getBststUuid( ) {
		return bststUuid;
	}

	public void setBststName(String bststName) {
		this.bststName = bststName;
	}

	public String getBststName( ) {
		return bststName;
	}

	public void setBststDesc(String bststDesc) {
		this.bststDesc = bststDesc;
	}

	public String getBststDesc( ) {
		return bststDesc;
	}

	public void setBststIcon(String bststIcon) {
		this.bststIcon = bststIcon;
	}

	public String getBststIcon( ) {
		return bststIcon;
	}

	public void setBststCdate(String bststCdate) {
		this.bststCdate = bststCdate;
	}

	public String getBststCdate( ) {
		return bststCdate;
	}

	public void setBststUdate(String bststUdate) {
		this.bststUdate = bststUdate;
	}

	public String getBststUdate( ) {
		return bststUdate;
	}

	public BusiTagSetVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiTagSet po = (BusiTagSet) poObj;
		this.bststUuid = po.getBststUuid();
		this.bststName = po.getBststName();
		this.bststDesc = po.getBststDesc();
		this.bststIcon = po.getBststIcon();
		this.bststCdate = po.getBststCdate()!=null?DateUtil.formatDefaultDate(po.getBststCdate()):"";
		this.bststUdate = po.getBststUdate()!=null?DateUtil.formatDefaultDate(po.getBststUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}