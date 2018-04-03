package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 分类表
*/
@ApiModel(value = "分类")
public class BusiProductCategoryVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "分类标识")
	private String bspcyUuid;

	/**
	*分类名称
	*/
	@ApiModelProperty(value = "分类名称")
	private String bspcyName;

	/**
	*分类图标
	*/
	@ApiModelProperty(value = "分类图标")
	private String bspcyIcon;

	/**
	*顺序
	*/
	@ApiModelProperty(value = "顺序")
	private Integer bspcyOrd;

	/**
	*状态:1启动0禁用2删除
	*/
	@ApiModelProperty(value = "状态:1启动0禁用2删除")
	private Integer bspcyStatus;

	/**
	*上级分类
	*/
	@ApiModelProperty(value = "上级分类")
	private String bspcyTop;

	/**
	*上级分类名称
	*/
	@ApiModelProperty(value = "上级分类名称")
	private String bspcyTopName;
	
	/**
	*是否有子节点:1有0无
	*/
	@ApiModelProperty(value = "是否有子节点:1有0无")
	private Integer bspcyChildNode;

	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bspcyCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bspcyUdate;

	public void setBspcyUuid(String bspcyUuid) {
		this.bspcyUuid = bspcyUuid;
	}

	public String getBspcyUuid( ) {
		return bspcyUuid;
	}

	public void setBspcyName(String bspcyName) {
		this.bspcyName = bspcyName;
	}

	public String getBspcyName( ) {
		return bspcyName;
	}

	public void setBspcyIcon(String bspcyIcon) {
		this.bspcyIcon = bspcyIcon;
	}

	public String getBspcyIcon( ) {
		return bspcyIcon;
	}

	public void setBspcyOrd(Integer bspcyOrd) {
		this.bspcyOrd = bspcyOrd;
	}

	public Integer getBspcyOrd( ) {
		return bspcyOrd;
	}

	public void setBspcyStatus(Integer bspcyStatus) {
		this.bspcyStatus = bspcyStatus;
	}

	public Integer getBspcyStatus( ) {
		return bspcyStatus;
	}

	public void setBspcyTop(String bspcyTop) {
		this.bspcyTop = bspcyTop;
	}

	public String getBspcyTop( ) {
		return bspcyTop;
	}

	public void setBspcyChildNode(Integer bspcyChildNode) {
		this.bspcyChildNode = bspcyChildNode;
	}

	public Integer getBspcyChildNode( ) {
		return bspcyChildNode;
	}

	public void setBspcyCdate(String bspcyCdate) {
		this.bspcyCdate = bspcyCdate;
	}

	public String getBspcyCdate( ) {
		return bspcyCdate;
	}

	public void setBspcyUdate(String bspcyUdate) {
		this.bspcyUdate = bspcyUdate;
	}

	public String getBspcyUdate( ) {
		return bspcyUdate;
	}

	public String getBspcyTopName() {
		return bspcyTopName;
	}

	public void setBspcyTopName(String bspcyTopName) {
		this.bspcyTopName = bspcyTopName;
	}

	public BusiProductCategoryVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiProductCategory po = (BusiProductCategory) poObj;
		this.bspcyUuid = po.getBspcyUuid();
		this.bspcyName = po.getBspcyName();
		this.bspcyIcon = po.getBspcyIcon();
		this.bspcyOrd = po.getBspcyOrd();
		this.bspcyStatus = po.getBspcyStatus();
		this.bspcyTop = po.getBspcyTop();
		this.bspcyChildNode = po.getBspcyChildNode();
		this.bspcyCdate = po.getBspcyCdate()!=null?DateUtil.formatDefaultDate(po.getBspcyCdate()):"";
		this.bspcyUdate = po.getBspcyUdate()!=null?DateUtil.formatDefaultDate(po.getBspcyUdate()):"";
	}

}