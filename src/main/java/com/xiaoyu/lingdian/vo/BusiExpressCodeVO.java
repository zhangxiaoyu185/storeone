package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiExpressCode;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 快递鸟公司编码配置表
*/
@ApiModel(value = "快递鸟公司编码配置")
public class BusiExpressCodeVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "快递鸟公司编码配置标识")
	private String bseceUuid;

	/**
	*编码
	*/
	@ApiModelProperty(value = "编码")
	private String bseceCode;

	/**
	*名称
	*/
	@ApiModelProperty(value = "名称")
	private String bseceName;

	public void setBseceUuid(String bseceUuid) {
		this.bseceUuid = bseceUuid;
	}

	public String getBseceUuid( ) {
		return bseceUuid;
	}

	public void setBseceCode(String bseceCode) {
		this.bseceCode = bseceCode;
	}

	public String getBseceCode( ) {
		return bseceCode;
	}

	public void setBseceName(String bseceName) {
		this.bseceName = bseceName;
	}

	public String getBseceName( ) {
		return bseceName;
	}

	public BusiExpressCodeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiExpressCode po = (BusiExpressCode) poObj;
		this.bseceUuid = po.getBseceUuid();
		this.bseceCode = po.getBseceCode();
		this.bseceName = po.getBseceName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}