package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiMessageCenterService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户消息中心表
*/
@Service("busiMessageCenterService")
public class BusiMessageCenterServiceImpl implements BusiMessageCenterService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiMessageCenter(BusiMessageCenter busiMessageCenter) {
		myBatisDAO.insert(busiMessageCenter);
		return true;
	}

	@Override
	public boolean updateBusiMessageCenter(BusiMessageCenter busiMessageCenter) {
		myBatisDAO.update(busiMessageCenter);
		return true;
	}

	@Override
	public boolean updateBatchBusiMessageCenterByIds(List<String> list) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchBusiMessageCenterByIds", hashMap);
		return true;
	}

	@Override
	public BusiMessageCenter getBusiMessageCenter(BusiMessageCenter busiMessageCenter) {
		return (BusiMessageCenter) myBatisDAO.findForObject(busiMessageCenter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiMessageCenter> findBusiMessageCenterPage(String bsmecUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsmecUser", bsmecUser);
		return myBatisDAO.findForPage("findBusiMessageCenterPage", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiMessageCenter> findBusiMessageCenterForPages(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiMessageCenter>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		return myBatisDAO.findForPage("findBusiMessageCenterForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}