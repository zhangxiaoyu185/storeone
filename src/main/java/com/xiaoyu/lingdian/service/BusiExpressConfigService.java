package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiExpressConfig;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 快递鸟配置表
*/
public interface BusiExpressConfigService {

	/**
	* 添加
	* @param busiExpressConfig
	* @return
	*/
	public boolean insertBusiExpressConfig(BusiExpressConfig busiExpressConfig);

	/**
	* 修改
	* @param busiExpressConfig
	* @return
	*/
	public boolean updateBusiExpressConfig(BusiExpressConfig busiExpressConfig);

	/**
	* 删除
	* @param busiExpressConfig
	* @return
	*/
	public boolean deleteBusiExpressConfig(BusiExpressConfig busiExpressConfig);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiExpressConfig
	* @return
	*/
	public BusiExpressConfig getBusiExpressConfig(BusiExpressConfig busiExpressConfig);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiExpressConfig> findBusiExpressConfigList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiExpressConfig> findBusiExpressConfigPage(int pageNum, int pageSize);

}