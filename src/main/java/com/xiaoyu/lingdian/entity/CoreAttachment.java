package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 业务附件表
*/
public class CoreAttachment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer cratmUnid;

	/**
	*标识UUID
	*/
	private String cratmUuid;

	/**
	*业务实体UUID
	*/
	private String cratmBusUuid;

	/**
	*名称
	*/
	private String cratmFileName;

	/**
	*目录,例a/b
	*/
	private String cratmDir;

	/**
	*文件扩展名
	*/
	private String cratmExtension;

	/**
	*创建日期
	*/
	private Date cratmCdate;

	/**
	*宽度
	*/
	private Integer cratmWidth;

	/**
	*高度
	*/
	private Integer cratmHeight;

	/**
	*类型:1头像(150X150)2商品主图(750X468)3商品封面图4评价图片5服务类别图(52X52)6服务动态封面图（702X320）7导航商品封面图(750X460)8商品类别图标（90X90）9logo10其他999非图片
	*/
	private Integer cratmType;

	public void setCratmUnid(Integer cratmUnid) {
		this.cratmUnid = cratmUnid;
	}

	public Integer getCratmUnid( ) {
		return cratmUnid;
	}

	public void setCratmUuid(String cratmUuid) {
		this.cratmUuid = cratmUuid;
	}

	public String getCratmUuid( ) {
		return cratmUuid;
	}

	public void setCratmBusUuid(String cratmBusUuid) {
		this.cratmBusUuid = cratmBusUuid;
	}

	public String getCratmBusUuid( ) {
		return cratmBusUuid;
	}

	public void setCratmFileName(String cratmFileName) {
		this.cratmFileName = cratmFileName;
	}

	public String getCratmFileName( ) {
		return cratmFileName;
	}

	public void setCratmDir(String cratmDir) {
		this.cratmDir = cratmDir;
	}

	public String getCratmDir( ) {
		return cratmDir;
	}

	public void setCratmExtension(String cratmExtension) {
		this.cratmExtension = cratmExtension;
	}

	public String getCratmExtension( ) {
		return cratmExtension;
	}

	public void setCratmCdate(Date cratmCdate) {
		this.cratmCdate = cratmCdate;
	}

	public Date getCratmCdate( ) {
		return cratmCdate;
	}

	public void setCratmWidth(Integer cratmWidth) {
		this.cratmWidth = cratmWidth;
	}

	public Integer getCratmWidth( ) {
		return cratmWidth;
	}

	public void setCratmHeight(Integer cratmHeight) {
		this.cratmHeight = cratmHeight;
	}

	public Integer getCratmHeight( ) {
		return cratmHeight;
	}

	public void setCratmType(Integer cratmType) {
		this.cratmType = cratmType;
	}

	public Integer getCratmType( ) {
		return cratmType;
	}

	public CoreAttachment( ) { 
	}

}