package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiServerDynamic;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 服务动态表
*/
@ApiModel(value = "服务动态")
public class BusiServerDynamicVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "服务动态标识")
	private String bssdcUuid;

	/**
	*服务类别
	*/
	@ApiModelProperty(value = "服务类别")
	private String bssdcType;

	/**
	*服务类别名称
	*/
	@ApiModelProperty(value = "服务类别名称")
	private String bssdcTypeName;
	
	/**
	*标题
	*/
	@ApiModelProperty(value = "标题")
	private String bssdcTitle;

	/**
	*封面图
	*/
	@ApiModelProperty(value = "封面图")
	private String bssdcPic;

	/**
	*内容
	*/
	@ApiModelProperty(value = "内容")
	private String bssdcContent;

	/**
	*描述
	*/
	@ApiModelProperty(value = "描述")
	private String bssdcDesc;

	/**
	*作者
	*/
	@ApiModelProperty(value = "作者")
	private String bssdcAuthor;

	/**
	*点击数
	*/
	@ApiModelProperty(value = "点击数")
	private Integer bssdcCount;

	/**
	*是否转载:1是0否
	*/
	@ApiModelProperty(value = "是否转载:1是0否")
	private Integer bssdcIsReprint;

	/**
	*来源URL
	*/
	@ApiModelProperty(value = "来源URL")
	private String bssdcSource;

	/**
	*发布时间
	*/
	@ApiModelProperty(value = "发布时间")
	private String bssdcPdate;

	/**
	*创建日期
	*/
	@ApiModelProperty(value = "创建日期")
	private String bssdcCdate;

	/**
	*修改日期
	*/
	@ApiModelProperty(value = "修改日期")
	private String bssdcUdate;

	/**
	*状态:1启用,0禁用
	*/
	@ApiModelProperty(value = "状态:1启用,0禁用")
	private Integer bssdcStatus;

	public void setBssdcUuid(String bssdcUuid) {
		this.bssdcUuid = bssdcUuid;
	}

	public String getBssdcUuid( ) {
		return bssdcUuid;
	}

	public void setBssdcType(String bssdcType) {
		this.bssdcType = bssdcType;
	}

	public String getBssdcType( ) {
		return bssdcType;
	}

	public void setBssdcTitle(String bssdcTitle) {
		this.bssdcTitle = bssdcTitle;
	}

	public String getBssdcTitle( ) {
		return bssdcTitle;
	}

	public void setBssdcPic(String bssdcPic) {
		this.bssdcPic = bssdcPic;
	}

	public String getBssdcPic( ) {
		return bssdcPic;
	}

	public void setBssdcContent(String bssdcContent) {
		this.bssdcContent = bssdcContent;
	}

	public String getBssdcContent( ) {
		return bssdcContent;
	}

	public void setBssdcDesc(String bssdcDesc) {
		this.bssdcDesc = bssdcDesc;
	}

	public String getBssdcDesc( ) {
		return bssdcDesc;
	}

	public void setBssdcAuthor(String bssdcAuthor) {
		this.bssdcAuthor = bssdcAuthor;
	}

	public String getBssdcAuthor( ) {
		return bssdcAuthor;
	}

	public void setBssdcCount(Integer bssdcCount) {
		this.bssdcCount = bssdcCount;
	}

	public Integer getBssdcCount( ) {
		return bssdcCount;
	}

	public void setBssdcIsReprint(Integer bssdcIsReprint) {
		this.bssdcIsReprint = bssdcIsReprint;
	}

	public Integer getBssdcIsReprint( ) {
		return bssdcIsReprint;
	}

	public void setBssdcSource(String bssdcSource) {
		this.bssdcSource = bssdcSource;
	}

	public String getBssdcSource( ) {
		return bssdcSource;
	}

	public void setBssdcPdate(String bssdcPdate) {
		this.bssdcPdate = bssdcPdate;
	}

	public String getBssdcPdate( ) {
		return bssdcPdate;
	}

	public void setBssdcCdate(String bssdcCdate) {
		this.bssdcCdate = bssdcCdate;
	}

	public String getBssdcCdate( ) {
		return bssdcCdate;
	}

	public void setBssdcUdate(String bssdcUdate) {
		this.bssdcUdate = bssdcUdate;
	}

	public String getBssdcUdate( ) {
		return bssdcUdate;
	}

	public void setBssdcStatus(Integer bssdcStatus) {
		this.bssdcStatus = bssdcStatus;
	}

	public Integer getBssdcStatus( ) {
		return bssdcStatus;
	}

	public String getBssdcTypeName() {
		return bssdcTypeName;
	}

	public void setBssdcTypeName(String bssdcTypeName) {
		this.bssdcTypeName = bssdcTypeName;
	}

	public BusiServerDynamicVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiServerDynamic po = (BusiServerDynamic) poObj;
		this.bssdcUuid = po.getBssdcUuid();
		this.bssdcType = po.getBssdcType();
		this.bssdcTitle = po.getBssdcTitle();
		this.bssdcPic = po.getBssdcPic();
		this.bssdcContent = po.getBssdcContent();
		this.bssdcDesc = po.getBssdcDesc();
		this.bssdcAuthor = po.getBssdcAuthor();
		this.bssdcCount = po.getBssdcCount();
		this.bssdcIsReprint = po.getBssdcIsReprint();
		this.bssdcSource = po.getBssdcSource();
		this.bssdcPdate = po.getBssdcPdate()!=null?DateUtil.formatDefaultDate(po.getBssdcPdate()):"";
		this.bssdcCdate = po.getBssdcCdate()!=null?DateUtil.formatDefaultDate(po.getBssdcCdate()):"";
		this.bssdcUdate = po.getBssdcUdate()!=null?DateUtil.formatDefaultDate(po.getBssdcUdate()):"";
		this.bssdcStatus = po.getBssdcStatus();
	}

}