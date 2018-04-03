package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiProduct;

import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 商品表
*/
public interface BusiProductService {

	/**
	* 添加
	* @param busiProduct
	* @return
	*/
	public boolean insertBusiProduct(BusiProduct busiProduct);

	/**
	* 修改
	* @param busiProduct
	* @return
	*/
	public boolean updateBusiProduct(BusiProduct busiProduct);

	/**
	* 修改商品库存
	* @param bsprtStock
	* @return
	*/
	public boolean updateBusiProductStock(String bsprtUuid, Integer bsprtStock);
	
	/**
	* 批量软删除
	* @param list
	* @return boolean
	*/
	public boolean updateBatchBusiProductDeteleByIds(List<String> list);

	/**
	* 查询
	* @param busiProduct
	* @return
	*/
	public BusiProduct getBusiProduct(BusiProduct busiProduct);

	/**
	* 获取所有销售中的商品list
	* @return List
	*/
	public List<BusiProduct> findBusiProductForListsBySale();
	
	/**
	* 获取所有商品list
	* @return List
	*/
	public List<BusiProduct> findBusiProductForLists();
	
	/**
	* 根据商品标识集合查询对应商品Map
	* @param list
	* @return Map
	*/
	public Map<String, BusiProduct> findBusiProductListByUuids(List<String> list);
	
	/**
	* 获取商品分类下的所有销售中的商品Page
	* @param bsprtCategory
	* @return Page
	*/
	public Page<BusiProduct> findBusiProductForPagesByCategory(String bsprtCategory, int pageNum, int pageSize);
	
	/**
	* 根据商品分类标识或商品名称获取所有未删除的page
	* @param bsprtCategory
	* @param bsprtName
	* @return Page
	*/
	public Page<BusiProduct> findBusiProductPage(String bsprtCategory, String bsprtName, int pageNum, int pageSize);

	/**
	* 根据商品名称获取所有商品List
	* @param bsprtName
	* @return List
	*/
	public List<BusiProduct> findBusiProductForPagesByProductName(String bsprtName);
	
}