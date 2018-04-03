package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户消息中心表
*/
@ApiModel(value = "用户消息中心")
public class BusiMessageCenterVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "用户消息中心标识")
	private String bsmecUuid;

	/**
	*所属用户
	*/
	@ApiModelProperty(value = "所属用户")
	private String bsmecUser;

	/**
	*所属用户名称
	*/
	@ApiModelProperty(value = "所属用户名称")
	private String bsmecUserName;
	
	/**
	*创建日期
	*/
	@ApiModelProperty(value = "创建日期")
	private String bsmecCdate;

	/**
	*更新日期
	*/
	@ApiModelProperty(value = "更新日期")
	private String bsmecUdate;

	/**
	*状态:1未读,2已读,3删除
	*/
	@ApiModelProperty(value = "状态:1未读,2已读,3删除")
	private Integer bsmecStatus;

	/**
	*消息类型:1金额2订单
	*/
	@ApiModelProperty(value = "消息类型:1金额2订单")
	private Integer bsmecType;

	/**
	*商品封面图
	*/
	@ApiModelProperty(value = "商品封面图")
	private String bsmecProductPic;

	/**
	*内容
	*/
	@ApiModelProperty(value = "内容")
	private String bsmecContent;

	public void setBsmecUuid(String bsmecUuid) {
		this.bsmecUuid = bsmecUuid;
	}

	public String getBsmecUuid( ) {
		return bsmecUuid;
	}

	public void setBsmecUser(String bsmecUser) {
		this.bsmecUser = bsmecUser;
	}

	public String getBsmecUser( ) {
		return bsmecUser;
	}

	public void setBsmecCdate(String bsmecCdate) {
		this.bsmecCdate = bsmecCdate;
	}

	public String getBsmecCdate( ) {
		return bsmecCdate;
	}

	public void setBsmecStatus(Integer bsmecStatus) {
		this.bsmecStatus = bsmecStatus;
	}

	public Integer getBsmecStatus( ) {
		return bsmecStatus;
	}

	public void setBsmecType(Integer bsmecType) {
		this.bsmecType = bsmecType;
	}

	public Integer getBsmecType( ) {
		return bsmecType;
	}

	public void setBsmecContent(String bsmecContent) {
		this.bsmecContent = bsmecContent;
	}

	public String getBsmecContent( ) {
		return bsmecContent;
	}

	public String getBsmecUdate() {
		return bsmecUdate;
	}

	public void setBsmecUdate(String bsmecUdate) {
		this.bsmecUdate = bsmecUdate;
	}

	public String getBsmecProductPic() {
		return bsmecProductPic;
	}

	public void setBsmecProductPic(String bsmecProductPic) {
		this.bsmecProductPic = bsmecProductPic;
	}

	public String getBsmecUserName() {
		return bsmecUserName;
	}

	public void setBsmecUserName(String bsmecUserName) {
		this.bsmecUserName = bsmecUserName;
	}

	public BusiMessageCenterVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiMessageCenter po = (BusiMessageCenter) poObj;
		this.bsmecUuid = po.getBsmecUuid();
		this.bsmecUser = po.getBsmecUser();
		this.bsmecCdate = po.getBsmecCdate()!=null?DateUtil.formatDefaultDate(po.getBsmecCdate()):"";
		this.bsmecUdate = po.getBsmecUdate()!=null?DateUtil.formatDefaultDate(po.getBsmecUdate()):"";
		this.bsmecStatus = po.getBsmecStatus();
		this.bsmecType = po.getBsmecType();
		this.bsmecProductPic = po.getBsmecProductPic();
		this.bsmecContent = po.getBsmecContent();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}