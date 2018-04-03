package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiSignRule;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 签到设置规则表
*/
public interface BusiSignRuleService {

	/**
	* 添加
	* @param busiSignRule
	* @return
	*/
	public boolean insertBusiSignRule(BusiSignRule busiSignRule);

	/**
	* 修改
	* @param busiSignRule
	* @return
	*/
	public boolean updateBusiSignRule(BusiSignRule busiSignRule);

	/**
	* 删除
	* @param busiSignRule
	* @return
	*/
	public boolean deleteBusiSignRule(BusiSignRule busiSignRule);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiSignRule
	* @return
	*/
	public BusiSignRule getBusiSignRule(BusiSignRule busiSignRule);

	/**
	* 获取最匹配的签到规则
	* @param count
	* @return
	*/
	public BusiSignRule getBusiSignRuleAfterCount(int count);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiSignRule> findBusiSignRuleList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiSignRule> findBusiSignRulePage(int pageNum, int pageSize);

}