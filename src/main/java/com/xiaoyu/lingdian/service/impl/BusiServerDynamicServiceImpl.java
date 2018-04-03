package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiServerDynamicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiServerDynamic;

/**
* 服务动态表
*/
@Service("busiServerDynamicService")
public class BusiServerDynamicServiceImpl implements BusiServerDynamicService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiServerDynamic(BusiServerDynamic busiServerDynamic) {
		myBatisDAO.insert(busiServerDynamic);
		return true;
	}

	@Override
	public boolean updateBusiServerDynamic(BusiServerDynamic busiServerDynamic) {
		myBatisDAO.update(busiServerDynamic);
		return true;
	}

	@Override
	public boolean deleteBusiServerDynamic(BusiServerDynamic busiServerDynamic) {
		myBatisDAO.delete(busiServerDynamic);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiServerDynamicByIds",hashMap);
		return true;
	}

	@Override
	public BusiServerDynamic getBusiServerDynamic(BusiServerDynamic busiServerDynamic) {
		return (BusiServerDynamic) myBatisDAO.findForObject(busiServerDynamic);
	}

	@Override
	public boolean updateBusiServerDynamicCount(String bssdcUuid) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssdcUuid", bssdcUuid);
		myBatisDAO.update("updateBusiServerDynamicCount", hashMap);
		return true;
	}

	@Override
	public BusiServerDynamic getBusiServerDynamicByTitle(String bssdcType, String bssdcTitle) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssdcType", bssdcType);
		hashMap.put("bssdcTitle", bssdcTitle);
		return (BusiServerDynamic) myBatisDAO.findForObject("getBusiServerDynamicByTitle", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiServerDynamic> findBusiServerDynamicForPagesByBefore(String bssdcType, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssdcType", bssdcType);
		return myBatisDAO.findForPage("findBusiServerDynamicForPagesByBefore", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiServerDynamic> findBusiServerDynamicForPages(String bssdcTitle, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssdcTitle", bssdcTitle);
		return myBatisDAO.findForPage("findBusiServerDynamicForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}