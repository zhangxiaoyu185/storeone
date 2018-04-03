package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiProductCategory;
import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 分类表
*/
public interface BusiProductCategoryService {

	/**
	* 添加
	* @param busiProductCategory
	* @return
	*/
	public boolean insertBusiProductCategory(BusiProductCategory busiProductCategory);

	/**
	* 修改
	* @param busiProductCategory
	* @return
	*/
	public boolean updateBusiProductCategory(BusiProductCategory busiProductCategory);

	/**
	* 根据父结点禁用子节点
	* @param bspcyTop
	* @return
	*/
	public boolean updateBusiProductCategoryDisableByTop(String bspcyTop);

	/**
	* 根据父结点删除子节点
	* @param bspcyTop
	* @return boolean
	*/
	public boolean updatBatchBusiProductCategoryDeteleByTop(String bspcyTop);

	/**
	* 批量软删除
	* @param list
	* @return boolean
	*/
	public boolean updatBatchBusiProductCategoryDeteleByIds(List<String> list);
	
	/**
	* 查询
	* @param busiProductCategory
	* @return
	*/
	public BusiProductCategory getBusiProductCategory(BusiProductCategory busiProductCategory);

	/**
	* 获取所有有子节点的启用分类list
	* @return List
	*/
	public List<BusiProductCategory> findBusiProductCategoryForListsHaveChild();

	/**
	* 根据父结点获取启用的分类子节点列表list
	* @param bspcyTop
	* @return List
	*/
	public List<BusiProductCategory> findBusiProductCategoryForListsByTop(String bspcyTop);

	/**
	* 商品用获取所有启用的子分类列表list
	* @return List
	*/
	public List<BusiProductCategory> findBusiProductCategoryForListsByChild();

	/**
	* 商品用获取所有启用的分类列表list
	* @return List
	*/
	public List<BusiProductCategory> findBusiProductCategoryForListsAll();

	/**
	* 后台根据分类名称或上级分类获取所有分类page
	* @param bspcyTop
	* @param bspcyName
	* @return Page
	*/
	public Page<BusiProductCategory> findBusiProductCategoryPage(String bspcyTop, String bspcyName, int pageNum, int pageSize);

	/**
	* 根据分类名称获取所有分类List
	* @param bspcyName
	* @return Page
	*/
	public List<BusiProductCategory> findBusiProductCategoryList(String bspcyName);
	
	/**
	* 根据分类 标识集合查询对应分类Map
	* @param list
	* @return Map
	*/
	public Map<String, BusiProductCategory> findBusiProductCategoryListByUuids(List<String> list);

}