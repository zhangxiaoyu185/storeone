package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 首页商品表
*/
public interface BusiIndexProductService {

	/**
	* 添加
	* @param busiIndexProduct
	* @return
	*/
	public boolean insertBusiIndexProduct(BusiIndexProduct busiIndexProduct);

	/**
	* 修改
	* @param busiIndexProduct
	* @return
	*/
	public boolean updateBusiIndexProduct(BusiIndexProduct busiIndexProduct);

	/**
	* 删除
	* @param busiIndexProduct
	* @return
	*/
	public boolean deleteBusiIndexProduct(BusiIndexProduct busiIndexProduct);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiIndexProduct
	* @return
	*/
	public BusiIndexProduct getBusiIndexProduct(BusiIndexProduct busiIndexProduct);

	/**
	* 前台获取启动的所有首页商品page
	* @return Page
	*/
	public Page<BusiIndexProduct> findBusiIndexProductPageBefore(int pageNum, int pageSize);

	/**
	* 后台根据商品名称查询所有首页商品page
	* @param productName
	* @return Page
	*/
	public Page<BusiIndexProduct> findBusiIndexProductForPages(String productName, int pageNum, int pageSize);

}