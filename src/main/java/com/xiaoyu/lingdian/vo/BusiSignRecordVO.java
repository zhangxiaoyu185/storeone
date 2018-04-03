package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户当前签到记录表
*/
@ApiModel(value = "用户当前签到记录")
public class BusiSignRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "用户当前签到记录标识")
	private String bssrdUuid;

	/**
	*签到用户
	*/
	@ApiModelProperty(value = "签到用户")
	private String bssrdUser;

	/**
	*签到用户账户
	*/
	@ApiModelProperty(value = "签到用户账户")
	private String bssrdUserName;

	/**
	*所得金額
	*/
	@ApiModelProperty(value = "所得金額")
	private Integer bssrdFee;

	/**
	*最后签到时间
	*/
	@ApiModelProperty(value = "最后签到时间")
	private String bssrdLastDate;

	/**
	*连续签到天数
	*/
	@ApiModelProperty(value = "连续签到天数")
	private Integer bssrdSignDays;

	public void setBssrdUuid(String bssrdUuid) {
		this.bssrdUuid = bssrdUuid;
	}

	public String getBssrdUuid( ) {
		return bssrdUuid;
	}

	public void setBssrdUser(String bssrdUser) {
		this.bssrdUser = bssrdUser;
	}

	public String getBssrdUser( ) {
		return bssrdUser;
	}

	public Integer getBssrdFee() {
        return bssrdFee;
    }

    public void setBssrdFee(Integer bssrdFee) {
        this.bssrdFee = bssrdFee;
    }

    public void setBssrdLastDate(String bssrdLastDate) {
		this.bssrdLastDate = bssrdLastDate;
	}

	public String getBssrdLastDate( ) {
		return bssrdLastDate;
	}

	public void setBssrdSignDays(Integer bssrdSignDays) {
		this.bssrdSignDays = bssrdSignDays;
	}

	public Integer getBssrdSignDays( ) {
		return bssrdSignDays;
	}

	public String getBssrdUserName() {
		return bssrdUserName;
	}

	public void setBssrdUserName(String bssrdUserName) {
		this.bssrdUserName = bssrdUserName;
	}

	public BusiSignRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiSignRecord po = (BusiSignRecord) poObj;
		this.bssrdUuid = po.getBssrdUuid();
		this.bssrdUser = po.getBssrdUser();
		this.bssrdFee = po.getBssrdFee();
		this.bssrdLastDate = po.getBssrdLastDate()!=null?DateUtil.formatDefaultDate(po.getBssrdLastDate()):"";
		this.bssrdSignDays = po.getBssrdSignDays();
	}

}