package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiExpressConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiExpressConfig;

/**
* 快递鸟配置表
*/
@Service("busiExpressConfigService")
public class BusiExpressConfigServiceImpl implements BusiExpressConfigService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiExpressConfig(BusiExpressConfig busiExpressConfig) {
		myBatisDAO.insert(busiExpressConfig);
		return true;
	}

	@Override
	public boolean updateBusiExpressConfig(BusiExpressConfig busiExpressConfig) {
		myBatisDAO.update(busiExpressConfig);
		return true;
	}

	@Override
	public boolean deleteBusiExpressConfig(BusiExpressConfig busiExpressConfig) {
		myBatisDAO.delete(busiExpressConfig);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiExpressConfigByIds",hashMap);
		return true;
	}

	@Override
	public BusiExpressConfig getBusiExpressConfig(BusiExpressConfig busiExpressConfig) {
		return (BusiExpressConfig) myBatisDAO.findForObject(busiExpressConfig);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiExpressConfig> findBusiExpressConfigList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiExpressConfigForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiExpressConfig> findBusiExpressConfigPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiExpressConfigForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}