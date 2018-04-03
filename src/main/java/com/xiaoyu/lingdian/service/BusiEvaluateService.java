package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiEvaluate;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 评价表
*/
public interface BusiEvaluateService {

	/**
	* 添加
	* @param busiEvaluate
	* @return
	*/
	public boolean insertBusiEvaluate(BusiEvaluate busiEvaluate);

	/**
	* 修改
	* @param busiEvaluate
	* @return
	*/
	public boolean updateBusiEvaluate(BusiEvaluate busiEvaluate);

	/**
	* 删除
	* @param busiEvaluate
	* @return
	*/
	public boolean deleteBusiEvaluate(BusiEvaluate busiEvaluate);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 后台订单的评价
	* @param busiEvaluate
	* @return
	*/
	public BusiEvaluate getBusiEvaluate(BusiEvaluate busiEvaluate);

	/**
	* 前台商品的评价Page
	* @param bseveProduct
	* @return Page
	*/
	public Page<BusiEvaluate> findBusiEvaluateForPages(String bseveProduct, int pageNum, int pageSize);

}