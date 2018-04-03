package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.CoreSystemConfig;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 系统配置表
*/
@ApiModel(value = "系统配置")
public class CoreSystemConfigVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "系统配置")
	private String crscgUuid;
	
	/**
	*配置KEY
	*/
	@ApiModelProperty(value = "配置KEY")
	private String crscgKey;

	/**
	*配置值
	*/
	@ApiModelProperty(value = "配置值")
	private String crscgValue;

	/**
	*配置说明
	*/
	@ApiModelProperty(value = "配置说明")
	private String crscgDesc;

	public void setCrscgUuid(String crscgUuid) {
		this.crscgUuid = crscgUuid;
	}

	public String getCrscgUuid( ) {
		return crscgUuid;
	}

	public void setCrscgKey(String crscgKey) {
		this.crscgKey = crscgKey;
	}

	public String getCrscgKey( ) {
		return crscgKey;
	}

	public void setCrscgValue(String crscgValue) {
		this.crscgValue = crscgValue;
	}

	public String getCrscgValue( ) {
		return crscgValue;
	}

	public void setCrscgDesc(String crscgDesc) {
		this.crscgDesc = crscgDesc;
	}

	public String getCrscgDesc( ) {
		return crscgDesc;
	}

	public CoreSystemConfigVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreSystemConfig po = (CoreSystemConfig) poObj;
		this.crscgUuid = po.getCrscgUuid();
		this.crscgKey = po.getCrscgKey();
		this.crscgValue = po.getCrscgValue();
		this.crscgDesc = po.getCrscgDesc();
	}

}