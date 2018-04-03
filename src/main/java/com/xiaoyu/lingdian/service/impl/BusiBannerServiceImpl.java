package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiBannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiBanner;

/**
* 导航商品配置表
*/
@Service("busiBannerService")
public class BusiBannerServiceImpl implements BusiBannerService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiBanner(BusiBanner busiBanner) {
		myBatisDAO.insert(busiBanner);
		return true;
	}

	@Override
	public boolean updateBusiBanner(BusiBanner busiBanner) {
		myBatisDAO.update(busiBanner);
		return true;
	}

	@Override
	public boolean deleteBusiBanner(BusiBanner busiBanner) {
		myBatisDAO.delete(busiBanner);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiBannerByIds",hashMap);
		return true;
	}

	@Override
	public BusiBanner getBusiBanner(BusiBanner busiBanner) {
		return (BusiBanner) myBatisDAO.findForObject(busiBanner);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiBanner> findBusiBannerPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiBannerForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiBanner> findBusiBannerForLists() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiBannerForLists", hashMap);
	}

}