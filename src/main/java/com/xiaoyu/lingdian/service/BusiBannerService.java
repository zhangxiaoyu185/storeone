package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiBanner;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 导航商品配置表
*/
public interface BusiBannerService {

	/**
	* 添加
	* @param busiBanner
	* @return
	*/
	public boolean insertBusiBanner(BusiBanner busiBanner);

	/**
	* 修改
	* @param busiBanner
	* @return
	*/
	public boolean updateBusiBanner(BusiBanner busiBanner);

	/**
	* 删除
	* @param busiBanner
	* @return
	*/
	public boolean deleteBusiBanner(BusiBanner busiBanner);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiBanner
	* @return
	*/
	public BusiBanner getBusiBanner(BusiBanner busiBanner);

	/**
	* 获取启用的banner图list
	* @return List
	*/
	public List<BusiBanner> findBusiBannerForLists();
	
	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiBanner> findBusiBannerPage(int pageNum, int pageSize);

}