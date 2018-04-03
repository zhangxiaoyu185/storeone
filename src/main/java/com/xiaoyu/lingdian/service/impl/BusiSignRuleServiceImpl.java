package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiSignRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiSignRule;

/**
* 签到设置规则表
*/
@Service("busiSignRuleService")
public class BusiSignRuleServiceImpl implements BusiSignRuleService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiSignRule(BusiSignRule busiSignRule) {
		myBatisDAO.insert(busiSignRule);
		return true;
	}

	@Override
	public boolean updateBusiSignRule(BusiSignRule busiSignRule) {
		myBatisDAO.update(busiSignRule);
		return true;
	}

	@Override
	public boolean deleteBusiSignRule(BusiSignRule busiSignRule) {
		myBatisDAO.delete(busiSignRule);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiSignRuleByIds", hashMap);
		return true;
	}

	@Override
	public BusiSignRule getBusiSignRule(BusiSignRule busiSignRule) {
		return (BusiSignRule) myBatisDAO.findForObject(busiSignRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiSignRule> findBusiSignRuleList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiSignRuleForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiSignRule> findBusiSignRulePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiSignRuleForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public BusiSignRule getBusiSignRuleAfterCount(int count) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("count", count);
		return (BusiSignRule) myBatisDAO.findForObject("getBusiSignRuleAfterCount", hashMap);
	}

}