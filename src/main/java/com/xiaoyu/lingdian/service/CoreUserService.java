package com.xiaoyu.lingdian.service;

import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户表
*/
public interface CoreUserService {

	/**
	* 添加
	* @param coreUser
	* @return
	*/
	public boolean insertCoreUser(CoreUser coreUser);

	/**
	* 修改
	* @param coreUser
	* @return
	*/
	public boolean updateCoreUser(CoreUser coreUser);

	/**
	* 修改金额
	* @param crusrUuid
	* @param crusrFee
	* @return
	*/
	public boolean updateCoreUserByFee(String crusrUuid, double crusrFee);
	
	/**
	* 修改订单数量
	* @param crusrUuid
	* @param crusrOrder
	* @param crusrPendingShip
	* @param crusrPendingRecv
	* @param crusrPendingEval
	* @param crusrFinished
	* @param crusrCancelled
	* @return
	*/
	public boolean updateCoreUserByOrderCount(String crusrUuid, int crusrOrder, int crusrPendingShip, int crusrPendingRecv, 
			int crusrPendingEval, int crusrFinished, int crusrCancelled);

	/**
	* 根据手机号修改
	* @param coreUser
	* @return
	*/
	public boolean updateCoreUserByMobile(CoreUser coreUser);
	
	/**
	* 批量软删除
	* @param list
	* @return boolean
	*/
	public boolean updateBatchCoreUserByIds(List<String> list);

	/**
	* 根据UUID查询
	* @param coreUser
	* @return
	*/
	public CoreUser getCoreUser(CoreUser coreUser);
	
	/**
	* 根据手机号查询
	* @param crusrMobile
	* @return
	*/
	public CoreUser getCoreUserByMobile(String crusrMobile);
	
	/**
	* 根据账户名称查询
	* @param crusrName
	* @return
	*/
	public CoreUser getCoreUserByName(String crusrName);

	/**
	* 查询用户Mapper
	* @param list
	* @return List
	*/
	public Map<String, CoreUser> findCoreUserMapByUuidList(List<String> list);

	/**
	* 查询所有List
	* @param crusrName
	* @param list
	* @return List
	*/
	public List<CoreUser> findCoreUserList(String crusrName, List<String> list);

	/**
	* 查询所有Page
	* @param crusrName
	* @param crusrMobile
	* @param pageNum
	* @param pageSize
	* @return Page
	*/
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrMobile, int pageNum, int pageSize);  

}