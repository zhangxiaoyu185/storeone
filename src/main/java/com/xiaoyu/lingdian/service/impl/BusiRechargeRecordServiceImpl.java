package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiRechargeRecordService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiRechargeRecord;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户充值记录表
*/
@Service("busiRechargeRecordService")
public class BusiRechargeRecordServiceImpl implements BusiRechargeRecordService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord) {
		myBatisDAO.insert(busiRechargeRecord);
		return true;
	}

	@Override
	public boolean updateBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord) {
		myBatisDAO.update(busiRechargeRecord);
		return true;
	}

	@Override
	public boolean deleteBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord) {
		myBatisDAO.delete(busiRechargeRecord);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiRechargeRecordByIds", hashMap);
		return true;
	}

	@Override
	public BusiRechargeRecord getBusiRechargeRecord(BusiRechargeRecord busiRechargeRecord) {
		return (BusiRechargeRecord) myBatisDAO.findForObject(busiRechargeRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiRechargeRecord> findBusiRechargeRecordPage(String bsrrdUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsrrdUser", bsrrdUser);
		return myBatisDAO.findForPage("findBusiRechargeRecordPage", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiRechargeRecord> findBusiRechargeRecordForPages(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiRechargeRecord>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		return myBatisDAO.findForPage("findBusiRechargeRecordForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}