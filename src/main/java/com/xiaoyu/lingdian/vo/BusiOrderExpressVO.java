package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiOrderExpress;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 订单明细快递物流表
*/
@ApiModel(value = "订单明细快递物流")
public class BusiOrderExpressVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "订单明细快递物流标识")
	private String bsoesUuid;

	/**
	*订单明细标识
	*/
	@ApiModelProperty(value = "订单明细标识")
	private String bsoesOrderItem;

	/**
	*物流内容
	*/
	@ApiModelProperty(value = "物流内容")
	private String bsoesContent;

	/**
	*添加时间
	*/
	@ApiModelProperty(value = "添加时间")
	private String bsoesCdate;

	public void setBsoesUuid(String bsoesUuid) {
		this.bsoesUuid = bsoesUuid;
	}

	public String getBsoesUuid( ) {
		return bsoesUuid;
	}

	public void setBsoesOrderItem(String bsoesOrderItem) {
		this.bsoesOrderItem = bsoesOrderItem;
	}

	public String getBsoesOrderItem( ) {
		return bsoesOrderItem;
	}

	public void setBsoesContent(String bsoesContent) {
		this.bsoesContent = bsoesContent;
	}

	public String getBsoesContent( ) {
		return bsoesContent;
	}

	public void setBsoesCdate(String bsoesCdate) {
		this.bsoesCdate = bsoesCdate;
	}

	public String getBsoesCdate( ) {
		return bsoesCdate;
	}

	public BusiOrderExpressVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiOrderExpress po = (BusiOrderExpress) poObj;
		this.bsoesUuid = po.getBsoesUuid();
		this.bsoesOrderItem = po.getBsoesOrderItem();
		this.bsoesContent = po.getBsoesContent();
		this.bsoesCdate = po.getBsoesCdate()!=null?DateUtil.formatDefaultDate(po.getBsoesCdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}