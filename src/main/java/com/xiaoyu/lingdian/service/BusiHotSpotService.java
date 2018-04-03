package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiHotSpot;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 热点配置表
*/
public interface BusiHotSpotService {

	/**
	* 添加
	* @param busiHotSpot
	* @return
	*/
	public boolean insertBusiHotSpot(BusiHotSpot busiHotSpot);

	/**
	* 修改
	* @param busiHotSpot
	* @return
	*/
	public boolean updateBusiHotSpot(BusiHotSpot busiHotSpot);

	/**
	* 删除
	* @param busiHotSpot
	* @return
	*/
	public boolean deleteBusiHotSpot(BusiHotSpot busiHotSpot);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiHotSpot
	* @return
	*/
	public BusiHotSpot getBusiHotSpot(BusiHotSpot busiHotSpot);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiHotSpot> findBusiHotSpotList();

	/**
	* 查询所有Page
	* @param bshstName 热点名称
	* @return Page
	*/
	public Page<BusiHotSpot> findBusiHotSpotPage(String bshstName, int pageNum, int pageSize);

}