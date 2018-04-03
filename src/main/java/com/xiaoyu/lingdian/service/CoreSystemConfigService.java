package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreSystemConfig;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 系统配置表
*/
public interface CoreSystemConfigService {

	/**
	* 添加
	* @param coreSystemConfig
	* @return
	*/
	public boolean insertCoreSystemConfig(CoreSystemConfig coreSystemConfig);

	/**
	* 修改
	* @param coreSystemConfig
	* @return
	*/
	public boolean updateCoreSystemConfig(CoreSystemConfig coreSystemConfig);

	/**
	* 删除
	* @param coreSystemConfig
	* @return
	*/
	public boolean deleteCoreSystemConfig(CoreSystemConfig coreSystemConfig);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreSystemConfig
	* @return
	*/
	public CoreSystemConfig getCoreSystemConfig(CoreSystemConfig coreSystemConfig);

	/**
	* 根据KEY查询
	* @param coreSystemConfig
	* @return
	*/
	public CoreSystemConfig getCoreSystemConfigByKey(CoreSystemConfig coreSystemConfig);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreSystemConfig> findCoreSystemConfigList();

	/**
	* 查询所有Page
	* @param crscgKey
	* @return Page
	*/
	public Page<CoreSystemConfig> findCoreSystemConfigPage(String crscgKey, int pageNum, int pageSize);

}