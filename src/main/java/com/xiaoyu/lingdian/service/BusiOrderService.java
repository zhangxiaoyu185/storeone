package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiOrder;

import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 订单表
*/
public interface BusiOrderService {

	/**
	* 添加
	* @param busiOrder
	* @return
	*/
	public boolean insertBusiOrder(BusiOrder busiOrder);

	/**
	* 修改
	* @param busiOrder
	* @return
	*/
	public boolean updateBusiOrder(BusiOrder busiOrder);

	/**
	* 查询
	* @param busiOrder
	* @return
	*/
	public BusiOrder getBusiOrder(BusiOrder busiOrder);

	/**
	* 获取超过10天的待收货订单list
	* @return List
	*/
	public List<BusiOrder> findBusiOrderForListsByOverDayDSH();
	
	/**
	* 获取超过7天的待评价订单list
	* @return List
	*/
	public List<BusiOrder> findBusiOrderForListsByOverDayPJ();
	
	/**
	* 获取各个状态下的订单page
	* @param bsorrUuid
	* @param bsorrUser
	* @param bsorrStatus
	* @return Page
	*/
	public Page<BusiOrder> findBusiOrderForPagesByMyStatus(String bsorrUuid, String bsorrUser, Integer bsorrStatus, int pageNum, int pageSize);

}