package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiShippingAddress;
import java.util.List;

/**
* 收货地址表
*/
public interface BusiShippingAddressService {

	/**
	* 添加
	* @param busiShippingAddress
	* @return
	*/
	public boolean insertBusiShippingAddress(BusiShippingAddress busiShippingAddress);

	/**
	* 修改
	* @param busiShippingAddress
	* @return
	*/
	public boolean updateBusiShippingAddress(BusiShippingAddress busiShippingAddress);

	/**
	* 删除
	* @param busiShippingAddress
	* @return
	*/
	public boolean deleteBusiShippingAddress(BusiShippingAddress busiShippingAddress);

	/**
	* 批量更新收货地址为不默认
	* @param bssasUser
	* @param list
	* @return boolean
	*/
	public boolean updateBatchBusiShippingAddressByIds(String bssasUser, List<String> list);

	/**
	* 查询
	* @param busiShippingAddress
	* @return
	*/
	public BusiShippingAddress getBusiShippingAddress(BusiShippingAddress busiShippingAddress);

	/**
	* 查询我的默认收货地址
	* @param bssasUser
	* @return
	*/
	public BusiShippingAddress getBusiShippingAddressDefault(String bssasUser);
	
	/**
	* 我的收货地址List
	* @param bssasUser
	* @return List
	*/
	public List<BusiShippingAddress> findBusiShippingAddressForListsByMy(String bssasUser);
	
	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiShippingAddress> findBusiShippingAddressForPages(String userName, int pageNum, int pageSize);

}