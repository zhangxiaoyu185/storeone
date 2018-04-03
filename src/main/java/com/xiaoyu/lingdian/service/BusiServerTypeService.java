package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiServerType;

import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 服务类别表
*/
public interface BusiServerTypeService {

	/**
	* 添加
	* @param busiServerType
	* @return
	*/
	public boolean insertBusiServerType(BusiServerType busiServerType);

	/**
	* 修改
	* @param busiServerType
	* @return
	*/
	public boolean updateBusiServerType(BusiServerType busiServerType);

	/**
	* 删除
	* @param busiServerType
	* @return
	*/
	public boolean deleteBusiServerType(BusiServerType busiServerType);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiServerType
	* @return
	*/
	public BusiServerType getBusiServerType(BusiServerType busiServerType);

	/**
	* 根据名称查询
	* @param bssteName
	* @return
	*/
	public BusiServerType getBusiServerTypeByName(String bssteName);
	
	/**
	* 查询类别Mapper
	* @param list
	* @return List
	*/
	public Map<String, BusiServerType> findBusiServerTypeMapByUuidList(List<String> list);
	
	/**
	* 获取所有启用的类别list
	* @return List
	*/
	public List<BusiServerType> findBusiServerTypeForLists();
	
	/**
	* 获取所有启用的类别list
	* @return List
	*/
	public List<BusiServerType> findBusiServerTypeForListsAll();
	
	/**
	* 查询所有Page
	* @param bssteName
	* @return Page
	*/
	public Page<BusiServerType> findBusiServerTypePage(String bssteName, int pageNum, int pageSize);

}