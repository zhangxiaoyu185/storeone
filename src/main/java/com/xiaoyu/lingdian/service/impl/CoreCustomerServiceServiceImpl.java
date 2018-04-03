package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreCustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreCustomerService;

/**
* 客服信息表
*/
@Service("coreCustomerServiceService")
public class CoreCustomerServiceServiceImpl implements CoreCustomerServiceService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreCustomerService(CoreCustomerService coreCustomerService) {
		myBatisDAO.insert(coreCustomerService);
		return true;
	}

	@Override
	public boolean updateCoreCustomerService(CoreCustomerService coreCustomerService) {
		myBatisDAO.update(coreCustomerService);
		return true;
	}

	@Override
	public boolean deleteCoreCustomerService(CoreCustomerService coreCustomerService) {
		myBatisDAO.delete(coreCustomerService);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreCustomerServiceByIds",hashMap);
		return true;
	}

	@Override
	public CoreCustomerService getCoreCustomerService(CoreCustomerService coreCustomerService) {
		return (CoreCustomerService) myBatisDAO.findForObject(coreCustomerService);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreCustomerService> findCoreCustomerServiceList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findCoreCustomerServiceForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreCustomerService> findCoreCustomerServicePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findCoreCustomerServiceForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}