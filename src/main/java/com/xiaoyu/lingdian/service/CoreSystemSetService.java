package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreSystemSet;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 系统设置表
*/
public interface CoreSystemSetService {

	/**
	* 添加
	* @param coreSystemSet
	* @return
	*/
	public boolean insertCoreSystemSet(CoreSystemSet coreSystemSet);

	/**
	* 修改
	* @param coreSystemSet
	* @return
	*/
	public boolean updateCoreSystemSet(CoreSystemSet coreSystemSet);

	/**
	* 删除
	* @param coreSystemSet
	* @return
	*/
	public boolean deleteCoreSystemSet(CoreSystemSet coreSystemSet);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreSystemSet
	* @return
	*/
	public CoreSystemSet getCoreSystemSet(CoreSystemSet coreSystemSet);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreSystemSet> findCoreSystemSetList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreSystemSet> findCoreSystemSetPage(int pageNum, int pageSize);

}