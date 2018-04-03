package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiSignRule;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 签到设置规则表
*/
@ApiModel(value = "签到设置规则")
public class BusiSignRuleVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "签到设置规则标识")
	private String bssreUuid;

	/**
	*连续天数
	*/
	@ApiModelProperty(value = "连续天数")
	private Integer bssreCount;

	/**
	*可领取金額
	*/
	@ApiModelProperty(value = "可领取金額")
	private Integer bssreFee;

	/**
	*顺序
	*/
	@ApiModelProperty(value = "顺序")
	private Integer bssreOrd;

	public void setBssreUuid(String bssreUuid) {
		this.bssreUuid = bssreUuid;
	}

	public String getBssreUuid( ) {
		return bssreUuid;
	}

	public void setBssreCount(Integer bssreCount) {
		this.bssreCount = bssreCount;
	}

	public Integer getBssreCount( ) {
		return bssreCount;
	}

	public Integer getBssreFee() {
        return bssreFee;
    }

    public void setBssreFee(Integer bssreFee) {
        this.bssreFee = bssreFee;
    }

    public void setBssreOrd(Integer bssreOrd) {
		this.bssreOrd = bssreOrd;
	}

	public Integer getBssreOrd( ) {
		return bssreOrd;
	}

	public BusiSignRuleVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiSignRule po = (BusiSignRule) poObj;
		this.bssreUuid = po.getBssreUuid();
		this.bssreCount = po.getBssreCount();
		this.bssreFee = po.getBssreFee();
		this.bssreOrd = po.getBssreOrd();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}