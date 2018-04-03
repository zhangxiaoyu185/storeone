package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiTagSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiTagSet;

/**
* 标签配置表
*/
@Service("busiTagSetService")
public class BusiTagSetServiceImpl implements BusiTagSetService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiTagSet(BusiTagSet busiTagSet) {
		myBatisDAO.insert(busiTagSet);
		return true;
	}

	@Override
	public boolean updateBusiTagSet(BusiTagSet busiTagSet) {
		myBatisDAO.update(busiTagSet);
		return true;
	}

	@Override
	public boolean deleteBusiTagSet(BusiTagSet busiTagSet) {
		myBatisDAO.delete(busiTagSet);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiTagSetByIds",hashMap);
		return true;
	}

	@Override
	public BusiTagSet getBusiTagSet(BusiTagSet busiTagSet) {
		return (BusiTagSet) myBatisDAO.findForObject(busiTagSet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiTagSet> findBusiTagSetList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiTagSetForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiTagSet> findBusiTagSetPage(String bststName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bststName", bststName);
		return myBatisDAO.findForPage("findBusiTagSetForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiTagSet> findBusiTagSetByUuids(List<String> list) {
		if (list.size() <= 0) {
			return new ArrayList<BusiTagSet>();
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForList("findBusiTagSetByUuids", hashMap);
	}

}