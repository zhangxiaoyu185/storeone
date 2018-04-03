package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiOrder;

/**
* 订单表
*/
@Service("busiOrderService")
public class BusiOrderServiceImpl implements BusiOrderService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiOrder(BusiOrder busiOrder) {
		myBatisDAO.insert(busiOrder);
		return true;
	}

	@Override
	public boolean updateBusiOrder(BusiOrder busiOrder) {
		myBatisDAO.update(busiOrder);
		return true;
	}

	@Override
	public BusiOrder getBusiOrder(BusiOrder busiOrder) {
		return (BusiOrder) myBatisDAO.findForObject(busiOrder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrder> findBusiOrderForListsByOverDayDSH() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiOrderForListsByOverDayDSH", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrder> findBusiOrderForListsByOverDayPJ() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiOrderForListsByOverDayPJ", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiOrder> findBusiOrderForPagesByMyStatus(String bsorrUuid, String bsorrUser, Integer bsorrStatus, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsorrUuid", bsorrUuid);
		hashMap.put("bsorrUser", bsorrUser);
		hashMap.put("bsorrStatus", bsorrStatus);
		return myBatisDAO.findForPage("findBusiOrderForPagesByMyStatus", new PageRequest(pageNum, pageSize, hashMap));
	}
	
}