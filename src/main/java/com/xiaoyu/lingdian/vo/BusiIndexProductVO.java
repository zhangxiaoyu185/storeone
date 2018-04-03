package com.xiaoyu.lingdian.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 首页商品表
*/
@ApiModel(value = "首页商品")
public class BusiIndexProductVO implements BaseVO {

	/**
	*标识UUID
	*/
	@ApiModelProperty(value = "首页商品标识")
	private String bsiptUuid;

	/**
	*商品标识
	*/
	@ApiModelProperty(value = "商品标识")
	private String bsiptProduct;

	/**
	*顺序
	*/
	@ApiModelProperty(value = "顺序")
	private Integer bsiptOrd;

	/**
	*状态:1启动0禁用
	*/
	@ApiModelProperty(value = "状态:1启动0禁用")
	private Integer bsiptStatus;

	/**
	*创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private String bsiptCdate;

	/**
	*更新时间
	*/
	@ApiModelProperty(value = "更新时间")
	private String bsiptUdate;

	/**
	*商品名称
	*/
	@ApiModelProperty(value = "商品名称")
	private String bsprtName;

	/**
	*分类
	*/
	@ApiModelProperty(value = "分类")
	private String bsprtCategory;

	/**
	*分类名称
	*/
	@ApiModelProperty(value = "分类名称")
	private String bsprtCategoryName;
	
	/**
	*商品描述
	*/
	@ApiModelProperty(value = "商品描述")
	private String bsprtDesc;
	
	/**
	*售价范围
	*/
	@ApiModelProperty(value = "售价范围")
	private String bsprtSalePrice;

	/**
	*原价范围
	*/
	@ApiModelProperty(value = "原价范围")
	private String bsprtOrignalPrice;

	/**
	*销售数量
	*/
	@ApiModelProperty(value = "销售数量")
	private Integer bsprtSaleCount;

	/**
	*地区
	*/
	@ApiModelProperty(value = "地区")
	private String bsprtAddress;

	/**
	*状态:1销售中2已下架3已删除
	*/
	@ApiModelProperty(value = "状态:1销售中2已下架3已删除")
	private Integer bsprtSaleStatus;

	/**
	*快递费用
	*/
	@ApiModelProperty(value = "快递费用")
	private Integer bsprtExpress;

	/**
	*封面图
	*/
	@ApiModelProperty(value = "封面图")
	private String bsprtPic;

	/**
	*商品热点标记
	*/
	@ApiModelProperty(value = "商品热点标记")
	private String bsprtHotSpot;
	
	/**
	*商品热点名称
	*/
	@ApiModelProperty(value = "商品热点名称")
	private String bsprtHotspotName;
	
	/**
	*商品热点图标
	*/
	@ApiModelProperty(value = "商品热点图标")
	private String bsprtHotspotIcon;

	public void setBsiptUuid(String bsiptUuid) {
		this.bsiptUuid = bsiptUuid;
	}

	public String getBsiptUuid( ) {
		return bsiptUuid;
	}

	public void setBsiptProduct(String bsiptProduct) {
		this.bsiptProduct = bsiptProduct;
	}

	public String getBsiptProduct( ) {
		return bsiptProduct;
	}

	public void setBsiptOrd(Integer bsiptOrd) {
		this.bsiptOrd = bsiptOrd;
	}

	public Integer getBsiptOrd( ) {
		return bsiptOrd;
	}

	public void setBsiptStatus(Integer bsiptStatus) {
		this.bsiptStatus = bsiptStatus;
	}

	public Integer getBsiptStatus( ) {
		return bsiptStatus;
	}

	public void setBsiptCdate(String bsiptCdate) {
		this.bsiptCdate = bsiptCdate;
	}

	public String getBsiptCdate( ) {
		return bsiptCdate;
	}

	public void setBsiptUdate(String bsiptUdate) {
		this.bsiptUdate = bsiptUdate;
	}

	public String getBsiptUdate( ) {
		return bsiptUdate;
	}

	public String getBsprtName() {
		return bsprtName;
	}

	public void setBsprtName(String bsprtName) {
		this.bsprtName = bsprtName;
	}

	public String getBsprtCategory() {
		return bsprtCategory;
	}

	public void setBsprtCategory(String bsprtCategory) {
		this.bsprtCategory = bsprtCategory;
	}

	public String getBsprtCategoryName() {
		return bsprtCategoryName;
	}

	public void setBsprtCategoryName(String bsprtCategoryName) {
		this.bsprtCategoryName = bsprtCategoryName;
	}

	public String getBsprtDesc() {
		return bsprtDesc;
	}

	public void setBsprtDesc(String bsprtDesc) {
		this.bsprtDesc = bsprtDesc;
	}

	public String getBsprtSalePrice() {
		return bsprtSalePrice;
	}

	public void setBsprtSalePrice(String bsprtSalePrice) {
		this.bsprtSalePrice = bsprtSalePrice;
	}

	public String getBsprtOrignalPrice() {
		return bsprtOrignalPrice;
	}

	public void setBsprtOrignalPrice(String bsprtOrignalPrice) {
		this.bsprtOrignalPrice = bsprtOrignalPrice;
	}

	public Integer getBsprtSaleCount() {
		return bsprtSaleCount;
	}

	public void setBsprtSaleCount(Integer bsprtSaleCount) {
		this.bsprtSaleCount = bsprtSaleCount;
	}

	public String getBsprtAddress() {
		return bsprtAddress;
	}

	public void setBsprtAddress(String bsprtAddress) {
		this.bsprtAddress = bsprtAddress;
	}

	public Integer getBsprtSaleStatus() {
		return bsprtSaleStatus;
	}

	public void setBsprtSaleStatus(Integer bsprtSaleStatus) {
		this.bsprtSaleStatus = bsprtSaleStatus;
	}

	public Integer getBsprtExpress() {
		return bsprtExpress;
	}

	public void setBsprtExpress(Integer bsprtExpress) {
		this.bsprtExpress = bsprtExpress;
	}

	public String getBsprtHotSpot() {
		return bsprtHotSpot;
	}

	public void setBsprtHotSpot(String bsprtHotSpot) {
		this.bsprtHotSpot = bsprtHotSpot;
	}

	public String getBsprtHotspotName() {
		return bsprtHotspotName;
	}

	public void setBsprtHotspotName(String bsprtHotspotName) {
		this.bsprtHotspotName = bsprtHotspotName;
	}

	public String getBsprtHotspotIcon() {
		return bsprtHotspotIcon;
	}

	public void setBsprtHotspotIcon(String bsprtHotspotIcon) {
		this.bsprtHotspotIcon = bsprtHotspotIcon;
	}

	public String getBsprtPic() {
		return bsprtPic;
	}

	public void setBsprtPic(String bsprtPic) {
		this.bsprtPic = bsprtPic;
	}

	public BusiIndexProductVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiIndexProduct po = (BusiIndexProduct) poObj;
		this.bsiptUuid = po.getBsiptUuid();
		this.bsiptProduct = po.getBsiptProduct();
		this.bsiptOrd = po.getBsiptOrd();
		this.bsiptStatus = po.getBsiptStatus();
		this.bsiptCdate = po.getBsiptCdate()!=null?DateUtil.formatDefaultDate(po.getBsiptCdate()):"";
		this.bsiptUdate = po.getBsiptUdate()!=null?DateUtil.formatDefaultDate(po.getBsiptUdate()):"";
	}

	public void convertPOToVOByProduct(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiProduct po = (BusiProduct) poObj;
		this.bsprtName = po.getBsprtName();
		this.bsprtCategory = po.getBsprtCategory();
		this.bsprtDesc = po.getBsprtDesc();
		this.bsprtSalePrice = po.getBsprtSalePrice();
		this.bsprtOrignalPrice = po.getBsprtOrignalPrice();
		this.bsprtSaleCount = po.getBsprtSaleCount();
		this.bsprtAddress = po.getBsprtAddress();
		this.bsprtSaleStatus = po.getBsprtSaleStatus();
		this.bsprtExpress = po.getBsprtExpress();
		this.bsprtPic = po.getBsprtPic();
		this.bsprtHotSpot = po.getBsprtHotSpot();
		this.bsprtHotspotName = po.getBsprtHotspotName();
		this.bsprtHotspotIcon = po.getBsprtHotspotIcon();
	}

}