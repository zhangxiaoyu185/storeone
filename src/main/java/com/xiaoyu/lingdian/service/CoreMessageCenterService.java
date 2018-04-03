package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 系统消息中心表
*/
public interface CoreMessageCenterService {

	/**
	* 添加
	* @param coreMessageCenter
	* @return
	*/
	public boolean insertCoreMessageCenter(CoreMessageCenter coreMessageCenter);

	/**
	* 修改
	* @param coreMessageCenter
	* @return
	*/
	public boolean updateCoreMessageCenter(CoreMessageCenter coreMessageCenter);

	/**
	* 批量软删除
	* @param list
	* @return boolean
	*/
	public boolean updateBatchCoreMessageCenterByIds(List<String> list);

	/**
	* 查询
	* @param coreMessageCenter
	* @return
	*/
	public CoreMessageCenter getCoreMessageCenter(CoreMessageCenter coreMessageCenter);

	/**
	* 获取所有未读消息个数
	* @return List
	*/
	public int findCoreMessageCenterCount();

	/**
	* 查询所有Page
	* @param crmecContent 消息内容 
	* @return Page
	*/
	public Page<CoreMessageCenter> findCoreMessageCenterPage(String crmecContent, int pageNum, int pageSize);

}