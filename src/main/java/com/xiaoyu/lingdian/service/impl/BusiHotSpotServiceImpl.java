package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiHotSpotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiHotSpot;

/**
* 热点配置表
*/
@Service("busiHotSpotService")
public class BusiHotSpotServiceImpl implements BusiHotSpotService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiHotSpot(BusiHotSpot busiHotSpot) {
		myBatisDAO.insert(busiHotSpot);
		return true;
	}

	@Override
	public boolean updateBusiHotSpot(BusiHotSpot busiHotSpot) {
		myBatisDAO.update(busiHotSpot);
		return true;
	}

	@Override
	public boolean deleteBusiHotSpot(BusiHotSpot busiHotSpot) {
		myBatisDAO.delete(busiHotSpot);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiHotSpotByIds",hashMap);
		return true;
	}

	@Override
	public BusiHotSpot getBusiHotSpot(BusiHotSpot busiHotSpot) {
		return (BusiHotSpot) myBatisDAO.findForObject(busiHotSpot);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiHotSpot> findBusiHotSpotList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiHotSpotForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiHotSpot> findBusiHotSpotPage(String bshstName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bshstName", bshstName);
		return myBatisDAO.findForPage("findBusiHotSpotForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}