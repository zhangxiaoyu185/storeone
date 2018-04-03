package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户当前签到记录表
*/
public interface BusiSignRecordService {

	/**
	* 添加
	* @param busiSignRecord
	* @return
	*/
	public boolean insertBusiSignRecord(BusiSignRecord busiSignRecord);

	/**
	* 根据用户标识修改
	* @param busiSignRecord
	* @return
	*/
	public boolean updateBusiSignRecord(BusiSignRecord busiSignRecord);

	/**
	* 查询
	* @param busiSignRecord
	* @return
	*/
	public BusiSignRecord getBusiSignRecord(BusiSignRecord busiSignRecord);

	/**
	* 根据用户标识查询签到记录
	* @param bssrdUser
	* @return
	*/
	public BusiSignRecord getBusiSignRecordByUser(String bssrdUser);

	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiSignRecord> findBusiSignRecordPage(String userName, int pageNum, int pageSize);

}