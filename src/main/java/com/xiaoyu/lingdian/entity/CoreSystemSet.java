package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 系统设置表
*/
public class CoreSystemSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crsstUnid;

	/**
	*标识UUID
	*/
	private String crsstUuid;

	/**
	*短信接口路径
	*/
	private String crsstMessagePath;

	/**
	*短信账户名
	*/
	private String crsstMessageLoginname;

	/**
	*短信密码
	*/
	private String crsstMessagePwd;

	/**
	*短信key
	*/
	private String crsstMessageKey;
	
	/**
	*项目域名
	*/
	private String crsstMessageDomain;

	/**
	*附件存放目录
	*/
	private String crsstAttachmentDir;

	public void setCrsstUnid(Integer crsstUnid) {
		this.crsstUnid = crsstUnid;
	}

	public Integer getCrsstUnid( ) {
		return crsstUnid;
	}

	public void setCrsstUuid(String crsstUuid) {
		this.crsstUuid = crsstUuid;
	}

	public String getCrsstUuid( ) {
		return crsstUuid;
	}

	public void setCrsstMessagePath(String crsstMessagePath) {
		this.crsstMessagePath = crsstMessagePath;
	}

	public String getCrsstMessagePath( ) {
		return crsstMessagePath;
	}

	public void setCrsstMessageLoginname(String crsstMessageLoginname) {
		this.crsstMessageLoginname = crsstMessageLoginname;
	}

	public String getCrsstMessageLoginname( ) {
		return crsstMessageLoginname;
	}

	public void setCrsstMessagePwd(String crsstMessagePwd) {
		this.crsstMessagePwd = crsstMessagePwd;
	}

	public String getCrsstMessagePwd( ) {
		return crsstMessagePwd;
	}

	public String getCrsstMessageKey() {
		return crsstMessageKey;
	}

	public void setCrsstMessageKey(String crsstMessageKey) {
		this.crsstMessageKey = crsstMessageKey;
	}

	public void setCrsstMessageDomain(String crsstMessageDomain) {
		this.crsstMessageDomain = crsstMessageDomain;
	}

	public String getCrsstMessageDomain( ) {
		return crsstMessageDomain;
	}

	public void setCrsstAttachmentDir(String crsstAttachmentDir) {
		this.crsstAttachmentDir = crsstAttachmentDir;
	}

	public String getCrsstAttachmentDir( ) {
		return crsstAttachmentDir;
	}

	public CoreSystemSet( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}