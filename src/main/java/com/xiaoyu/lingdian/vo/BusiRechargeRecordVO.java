package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiRechargeRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户充值记录表
*/
@ApiModel(value = "用户充值记录")
public class BusiRechargeRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "用户充值记录标识")
	private String bsrrdUuid;

	/**
	*充值金额
	*/
	@ApiModelProperty(value = "充值金额")
	private String bsrrdAmount;

	/**
	*充值人
	*/
	@ApiModelProperty(value = "充值人")
	private String bsrrdUser;

	/**
	*充值人姓名
	*/
	@ApiModelProperty(value = "充值人姓名")
	private String bsrrdUserName;
	
	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bsrrdCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bsrrdUdate;

	public void setBsrrdUuid(String bsrrdUuid) {
		this.bsrrdUuid = bsrrdUuid;
	}

	public String getBsrrdUuid( ) {
		return bsrrdUuid;
	}

	public void setBsrrdAmount(String bsrrdAmount) {
		this.bsrrdAmount = bsrrdAmount;
	}

	public String getBsrrdAmount( ) {
		return bsrrdAmount;
	}

	public void setBsrrdUser(String bsrrdUser) {
		this.bsrrdUser = bsrrdUser;
	}

	public String getBsrrdUser( ) {
		return bsrrdUser;
	}

	public String getBsrrdUserName() {
		return bsrrdUserName;
	}

	public void setBsrrdUserName(String bsrrdUserName) {
		this.bsrrdUserName = bsrrdUserName;
	}

	public void setBsrrdCdate(String bsrrdCdate) {
		this.bsrrdCdate = bsrrdCdate;
	}

	public String getBsrrdCdate( ) {
		return bsrrdCdate;
	}

	public void setBsrrdUdate(String bsrrdUdate) {
		this.bsrrdUdate = bsrrdUdate;
	}

	public String getBsrrdUdate( ) {
		return bsrrdUdate;
	}

	public BusiRechargeRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiRechargeRecord po = (BusiRechargeRecord) poObj;
		this.bsrrdUuid = po.getBsrrdUuid();
		this.bsrrdAmount = po.getBsrrdAmount();
		this.bsrrdUser = po.getBsrrdUser();
		this.bsrrdCdate = po.getBsrrdCdate()!=null?DateUtil.formatDefaultDate(po.getBsrrdCdate()):"";
		this.bsrrdUdate = po.getBsrrdUdate()!=null?DateUtil.formatDefaultDate(po.getBsrrdUdate()):"";
	}

}