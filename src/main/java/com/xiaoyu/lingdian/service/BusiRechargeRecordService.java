package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiRechargeRecord;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户充值记录表
*/
public interface BusiRechargeRecordService {

	/**
	* 添加
	* @param busiRechargeRecord
	* @return
	*/
	public boolean insertBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord);

	/**
	* 修改
	* @param busiRechargeRecord
	* @return
	*/
	public boolean updateBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord);

	/**
	* 删除
	* @param busiRechargeRecord
	* @return
	*/
	public boolean deleteBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiRechargeRecord
	* @return
	*/
	public BusiRechargeRecord getBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord);

	/**
	* 我的充值记录page
	* @param bsrrdUser
	* @return Page
	*/
	public Page<BusiRechargeRecord> findBusiRechargeRecordPage(String bsrrdUser, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiRechargeRecord> findBusiRechargeRecordForPages(String userName, int pageNum, int pageSize);

}