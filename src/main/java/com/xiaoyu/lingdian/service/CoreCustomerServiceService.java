package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreCustomerService;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 客服信息表
*/
public interface CoreCustomerServiceService {

	/**
	* 添加
	* @param coreCustomerService
	* @return
	*/
	public boolean insertCoreCustomerService(CoreCustomerService coreCustomerService);

	/**
	* 修改
	* @param coreCustomerService
	* @return
	*/
	public boolean updateCoreCustomerService(CoreCustomerService coreCustomerService);

	/**
	* 删除
	* @param coreCustomerService
	* @return
	*/
	public boolean deleteCoreCustomerService(CoreCustomerService coreCustomerService);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreCustomerService
	* @return
	*/
	public CoreCustomerService getCoreCustomerService(CoreCustomerService coreCustomerService);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreCustomerService> findCoreCustomerServiceList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreCustomerService> findCoreCustomerServicePage(int pageNum, int pageSize);

}