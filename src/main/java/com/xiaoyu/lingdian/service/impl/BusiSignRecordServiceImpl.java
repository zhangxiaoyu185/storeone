package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiSignRecordService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户当前签到记录表
*/
@Service("busiSignRecordService")
public class BusiSignRecordServiceImpl implements BusiSignRecordService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiSignRecord(BusiSignRecord busiSignRecord) {
		myBatisDAO.insert(busiSignRecord);
		return true;
	}

	@Override
	public boolean updateBusiSignRecord(BusiSignRecord busiSignRecord) {
		myBatisDAO.update(busiSignRecord);
		return true;
	}

	@Override
	public BusiSignRecord getBusiSignRecord(BusiSignRecord busiSignRecord) {
		return (BusiSignRecord) myBatisDAO.findForObject(busiSignRecord);
	}

	@Override
	public BusiSignRecord getBusiSignRecordByUser(String bssrdUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssrdUser", bssrdUser);
		return (BusiSignRecord) myBatisDAO.findForObject("getBusiSignRecordByUser", bssrdUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiSignRecord> findBusiSignRecordPage(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiSignRecord>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		return myBatisDAO.findForPage("findBusiSignRecordForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}