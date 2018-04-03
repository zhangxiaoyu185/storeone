package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreMessageCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreMessageCenter;

/**
* 系统消息中心表
*/
@Service("coreMessageCenterService")
public class CoreMessageCenterServiceImpl implements CoreMessageCenterService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreMessageCenter(CoreMessageCenter coreMessageCenter) {
		myBatisDAO.insert(coreMessageCenter);
		return true;
	}

	@Override
	public boolean updateCoreMessageCenter(CoreMessageCenter coreMessageCenter) {
		myBatisDAO.update(coreMessageCenter);
		return true;
	}

	@Override
	public boolean updateBatchCoreMessageCenterByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchCoreMessageCenterByIds",hashMap);
		return true;
	}

	@Override
	public CoreMessageCenter getCoreMessageCenter(CoreMessageCenter coreMessageCenter) {
		return (CoreMessageCenter) myBatisDAO.findForObject(coreMessageCenter);
	}

	@Override
	public int findCoreMessageCenterCount() {
		Integer count = (Integer)myBatisDAO.findForObject("findCoreMessageCenterCount", null);
		return count == null ? 0 : count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreMessageCenter> findCoreMessageCenterPage(String crmecContent, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crmecContent",crmecContent);
		return myBatisDAO.findForPage("findCoreMessageCenterForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}