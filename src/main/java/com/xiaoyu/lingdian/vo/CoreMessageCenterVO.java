package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 系统消息中心表
*/
@ApiModel(value = "系统消息")
public class CoreMessageCenterVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "系统消息标识")
	private String crmecUuid;

	/**
	*消息内容
	*/
	@ApiModelProperty(value = "消息内容")
	private String crmecContent;

	/**
	*创建日期
	*/
	@ApiModelProperty(value = "创建日期")
	private String crmecCdate;

	/**
	*更新日期
	*/
	@ApiModelProperty(value = "更新日期")
	private String crmecUdate;

	/**
	*状态:1已处理,2未处理,3已删除
	*/
	@ApiModelProperty(value = "状态:1已处理,2未处理,3已删除")
	private Integer crmecStatus;

	/**
	*消息类型
	*/
	@ApiModelProperty(value = "消息类型")
	private Integer crmecType;

	public void setCrmecUuid(String crmecUuid) {
		this.crmecUuid = crmecUuid;
	}

	public String getCrmecUuid( ) {
		return crmecUuid;
	}

	public void setCrmecContent(String crmecContent) {
		this.crmecContent = crmecContent;
	}

	public String getCrmecContent( ) {
		return crmecContent;
	}

	public void setCrmecCdate(String crmecCdate) {
		this.crmecCdate = crmecCdate;
	}

	public String getCrmecCdate( ) {
		return crmecCdate;
	}

	public String getCrmecUdate() {
		return crmecUdate;
	}

	public void setCrmecUdate(String crmecUdate) {
		this.crmecUdate = crmecUdate;
	}

	public void setCrmecStatus(Integer crmecStatus) {
		this.crmecStatus = crmecStatus;
	}

	public Integer getCrmecStatus( ) {
		return crmecStatus;
	}

	public void setCrmecType(Integer crmecType) {
		this.crmecType = crmecType;
	}

	public Integer getCrmecType( ) {
		return crmecType;
	}

	public CoreMessageCenterVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreMessageCenter po = (CoreMessageCenter) poObj;
		this.crmecUuid = po.getCrmecUuid();
		this.crmecContent = po.getCrmecContent();
		this.crmecCdate = po.getCrmecCdate()!=null?DateUtil.formatDefaultDate(po.getCrmecCdate()):"";
		this.crmecUdate = po.getCrmecUdate()!=null?DateUtil.formatDefaultDate(po.getCrmecUdate()):"";
		this.crmecStatus = po.getCrmecStatus();
		this.crmecType = po.getCrmecType();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}