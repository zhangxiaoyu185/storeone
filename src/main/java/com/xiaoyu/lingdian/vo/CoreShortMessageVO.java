package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 短信日志记录表
*/
@ApiModel(value = "短信日志记录")
public class CoreShortMessageVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "短信日志记录标识")
	private String crmceUuid;
	
	/**
	*发送手机号
	*/
	@ApiModelProperty(value = "发送手机号")
	private String crmceMobile;

	/**
	*短信内容(包括验证码)
	*/
	@ApiModelProperty(value = "短信内容(包括验证码)")
	private String crmceContent;

	/**
	*发送时间
	*/
	@ApiModelProperty(value = "发送时间")
	private String crmceTime;

	/**
	*过期时间
	*/
	@ApiModelProperty(value = "过期时间")
	private String crmceTimeout;

	/**
	*发送状态:1成功,0失败
	*/
	@ApiModelProperty(value = "发送状态:1成功,0失败")
	private Integer crmceStatus;

	/**
	*短信类型:1验证码2其它
	*/
	@ApiModelProperty(value = "短信类型:1验证码2其它")
	private Integer crmceType;

	public void setCrmceUuid(String crmceUuid) {
		this.crmceUuid = crmceUuid;
	}

	public String getCrmceUuid( ) {
		return crmceUuid;
	}

	public void setCrmceMobile(String crmceMobile) {
		this.crmceMobile = crmceMobile;
	}

	public String getCrmceMobile( ) {
		return crmceMobile;
	}

	public void setCrmceContent(String crmceContent) {
		this.crmceContent = crmceContent;
	}

	public String getCrmceContent( ) {
		return crmceContent;
	}

	public void setCrmceTime(String crmceTime) {
		this.crmceTime = crmceTime;
	}

	public String getCrmceTime( ) {
		return crmceTime;
	}

	public void setCrmceTimeout(String crmceTimeout) {
		this.crmceTimeout = crmceTimeout;
	}

	public String getCrmceTimeout( ) {
		return crmceTimeout;
	}

	public void setCrmceStatus(Integer crmceStatus) {
		this.crmceStatus = crmceStatus;
	}

	public Integer getCrmceStatus( ) {
		return crmceStatus;
	}

	public void setCrmceType(Integer crmceType) {
		this.crmceType = crmceType;
	}

	public Integer getCrmceType( ) {
		return crmceType;
	}

	public CoreShortMessageVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreShortMessage po = (CoreShortMessage) poObj;
		this.crmceUuid = po.getCrmceUuid();
		this.crmceMobile = po.getCrmceMobile();
		this.crmceContent = po.getCrmceContent();
		this.crmceTime = po.getCrmceTime()!=null?DateUtil.formatDefaultDate(po.getCrmceTime()):"";
		this.crmceTimeout = po.getCrmceTimeout()!=null?DateUtil.formatDefaultDate(po.getCrmceTimeout()):"";
		this.crmceStatus = po.getCrmceStatus();
		this.crmceType = po.getCrmceType();
	}

}