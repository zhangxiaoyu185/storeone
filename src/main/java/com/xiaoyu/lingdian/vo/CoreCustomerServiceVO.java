package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.CoreCustomerService;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 客服信息表
*/
@ApiModel(value = "客服信息")
public class CoreCustomerServiceVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "客服信息标识")
	private String crcseUuid;

	/**
	*电话号码(|隔开)
	*/
	@ApiModelProperty(value = "电话号码(|隔开)")
	private String crcseTel;

	/**
	*QQ(|隔开)
	*/
	@ApiModelProperty(value = "QQ(|隔开)")
	private String crcseQq;

	/**
	*创建日期
	*/
	@ApiModelProperty(value = "创建日期")
	private String crcseCdate;

	/**
	*修改日期
	*/
	@ApiModelProperty(value = "修改日期")
	private String crcseUdate;

	public void setCrcseUuid(String crcseUuid) {
		this.crcseUuid = crcseUuid;
	}

	public String getCrcseUuid( ) {
		return crcseUuid;
	}

	public void setCrcseTel(String crcseTel) {
		this.crcseTel = crcseTel;
	}

	public String getCrcseTel( ) {
		return crcseTel;
	}

	public void setCrcseQq(String crcseQq) {
		this.crcseQq = crcseQq;
	}

	public String getCrcseQq( ) {
		return crcseQq;
	}

	public void setCrcseCdate(String crcseCdate) {
		this.crcseCdate = crcseCdate;
	}

	public String getCrcseCdate( ) {
		return crcseCdate;
	}

	public void setCrcseUdate(String crcseUdate) {
		this.crcseUdate = crcseUdate;
	}

	public String getCrcseUdate( ) {
		return crcseUdate;
	}

	public CoreCustomerServiceVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreCustomerService po = (CoreCustomerService) poObj;
		this.crcseUuid = po.getCrcseUuid();
		this.crcseTel = po.getCrcseTel();
		this.crcseQq = po.getCrcseQq();
		this.crcseCdate = po.getCrcseCdate()!=null?DateUtil.formatDefaultDate(po.getCrcseCdate()):"";
		this.crcseUdate = po.getCrcseUdate()!=null?DateUtil.formatDefaultDate(po.getCrcseUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}