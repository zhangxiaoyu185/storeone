package com.xiaoyu.lingdian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;

/**
* 用户表
*/
@Service("coreUserService")
public class CoreUserServiceImpl implements CoreUserService {

	private static final SimpleMapMapper<String, CoreUser> USER_UUID_MAPPER = new SimpleMapMapper<String, CoreUser>() {
		@Override
		public String mapKey(CoreUser object, int rowNum) {
			return object.getCrusrUuid();
		}
	};

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreUser(CoreUser coreUser) {
		myBatisDAO.insert(coreUser);
		return true;
	}

	@Override
	public boolean updateCoreUser(CoreUser coreUser) {
		myBatisDAO.update(coreUser);
		return true;
	}

	@Override
	public boolean updateCoreUserByFee(String crusrUuid, double crusrFee) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrFee", crusrFee);
		myBatisDAO.update("updateCoreUserByFee", hashMap);
		return true;
	}

	@Override
	public boolean updateBatchCoreUserByIds(List<String> list) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchCoreUserByIds",hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByMobile(CoreUser coreUser) {
		myBatisDAO.update("updateCoreUserByMobile", coreUser);
		return true;
	}

	@Override
	public CoreUser getCoreUser(CoreUser coreUser) {
		return (CoreUser) myBatisDAO.findForObject(coreUser);
	}
	
	@Override
	public CoreUser getCoreUserByMobile(String crusrMobile) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrMobile", crusrMobile);
		return (CoreUser) myBatisDAO.findForObject("getCoreUserByMobile", hashMap);
	}
	
	@Override
	public CoreUser getCoreUserByName(String crusrName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		return (CoreUser) myBatisDAO.findForObject("getCoreUserByName", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, CoreUser> findCoreUserMapByUuidList(List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findCoreUserForLists", hashMap, USER_UUID_MAPPER);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> findCoreUserList(String crusrName, List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		hashMap.put("list", list);
		return myBatisDAO.findForList("findCoreUserForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrMobile, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		hashMap.put("crusrMobile", crusrMobile);
		return myBatisDAO.findForPage("findCoreUserForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public boolean updateCoreUserByOrderCount(String crusrUuid, int crusrOrder, int crusrPendingShip, int crusrPendingRecv, 
			int crusrPendingEval, int crusrFinished, int crusrCancelled) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrOrder", crusrOrder);
		hashMap.put("crusrPendingShip", crusrPendingShip);
		hashMap.put("crusrPendingRecv", crusrPendingRecv);
		hashMap.put("crusrPendingEval", crusrPendingEval);
		hashMap.put("crusrFinished", crusrFinished);
		hashMap.put("crusrCancelled", crusrCancelled);
		myBatisDAO.update("updateCoreUserByOrderCount", hashMap);
		return true;
	}

}