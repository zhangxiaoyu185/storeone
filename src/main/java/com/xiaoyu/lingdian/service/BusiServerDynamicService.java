package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiServerDynamic;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 服务动态表
*/
public interface BusiServerDynamicService {

	/**
	* 添加
	* @param busiServerDynamic
	* @return
	*/
	public boolean insertBusiServerDynamic(BusiServerDynamic busiServerDynamic);

	/**
	* 修改
	* @param busiServerDynamic
	* @return
	*/
	public boolean updateBusiServerDynamic(BusiServerDynamic busiServerDynamic);

	/**
	* 更新查看数
	* @param bssdcUuid
	* @return
	*/
	public boolean updateBusiServerDynamicCount(String bssdcUuid);

	/**
	* 删除
	* @param busiServerDynamic
	* @return
	*/
	public boolean deleteBusiServerDynamic(BusiServerDynamic busiServerDynamic);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	 * 根据标题查询
	 * @param bssdcType
	 * @param bssdcTitle
	 * @return
	 */
	public BusiServerDynamic getBusiServerDynamicByTitle(String bssdcType, String bssdcTitle);

	/**
	* 查询
	* @param busiServerDynamic
	* @return
	*/
	public BusiServerDynamic getBusiServerDynamic(BusiServerDynamic busiServerDynamic);

	/**
	* 前台获取所有page
	* @param bssdcType
	* @return Page
	*/
	public Page<BusiServerDynamic> findBusiServerDynamicForPagesByBefore(String bssdcType, int pageNum, int pageSize);
	
	/**
	* 后台获取所有page
	* @param bssdcTitle
	* @return Page
	*/
	public Page<BusiServerDynamic> findBusiServerDynamicForPages(String bssdcTitle, int pageNum, int pageSize);

}