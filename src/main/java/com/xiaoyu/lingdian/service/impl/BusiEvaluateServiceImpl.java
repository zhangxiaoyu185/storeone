package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiEvaluateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiEvaluate;

/**
* 评价表
*/
@Service("busiEvaluateService")
public class BusiEvaluateServiceImpl implements BusiEvaluateService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiEvaluate(BusiEvaluate busiEvaluate) {
		myBatisDAO.insert(busiEvaluate);
		return true;
	}

	@Override
	public boolean updateBusiEvaluate(BusiEvaluate busiEvaluate) {
		myBatisDAO.update(busiEvaluate);
		return true;
	}

	@Override
	public boolean deleteBusiEvaluate(BusiEvaluate busiEvaluate) {
		myBatisDAO.delete(busiEvaluate);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiEvaluateByIds",hashMap);
		return true;
	}

	@Override
	public BusiEvaluate getBusiEvaluate(BusiEvaluate busiEvaluate) {
		return (BusiEvaluate) myBatisDAO.findForObject(busiEvaluate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiEvaluate> findBusiEvaluateForPages(String bseveProduct, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bseveProduct", bseveProduct);
		return myBatisDAO.findForPage("findBusiEvaluateForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}