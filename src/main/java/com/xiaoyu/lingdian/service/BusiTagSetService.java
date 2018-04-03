package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiTagSet;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 标签配置表
*/
public interface BusiTagSetService {

	/**
	* 添加
	* @param busiTagSet
	* @return
	*/
	public boolean insertBusiTagSet(BusiTagSet busiTagSet);

	/**
	* 修改
	* @param busiTagSet
	* @return
	*/
	public boolean updateBusiTagSet(BusiTagSet busiTagSet);

	/**
	* 删除
	* @param busiTagSet
	* @return
	*/
	public boolean deleteBusiTagSet(BusiTagSet busiTagSet);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiTagSet
	* @return
	*/
	public BusiTagSet getBusiTagSet(BusiTagSet busiTagSet);

	/**
	* 根据标签UUID集合查询标签列表
	* @param list
	* @return List
	*/
	public List<BusiTagSet> findBusiTagSetByUuids(List<String> list);
	
	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiTagSet> findBusiTagSetList();

	/**
	* 查询所有Page
	* @param bststName
	* @return Page
	*/
	public Page<BusiTagSet> findBusiTagSetPage(String bststName, int pageNum, int pageSize);

}