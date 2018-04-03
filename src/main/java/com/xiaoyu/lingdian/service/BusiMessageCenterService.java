package com.xiaoyu.lingdian.service;

import java.util.List;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户消息中心表
*/
public interface BusiMessageCenterService {

	/**
	* 添加
	* @param busiMessageCenter
	* @return
	*/
	public boolean insertBusiMessageCenter(BusiMessageCenter busiMessageCenter);

	/**
	* 修改
	* @param busiMessageCenter
	* @return
	*/
	public boolean updateBusiMessageCenter(BusiMessageCenter busiMessageCenter);

	/**
	* 批量已读
	* @param list
	* @return
	*/
	public boolean updateBatchBusiMessageCenterByIds(List<String> list);
	
	/**
	* 查询
	* @param busiMessageCenter
	* @return
	*/
	public BusiMessageCenter getBusiMessageCenter(BusiMessageCenter busiMessageCenter);
	
	/**
	* 我的消息page
	* @param bsmecUser
	* @return Page
	*/
	public Page<BusiMessageCenter> findBusiMessageCenterPage(String bsmecUser, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiMessageCenter> findBusiMessageCenterForPages(String userName, int pageNum, int pageSize);

}