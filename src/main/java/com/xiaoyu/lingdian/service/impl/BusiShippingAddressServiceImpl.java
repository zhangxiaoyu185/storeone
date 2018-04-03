package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiShippingAddressService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.entity.BusiShippingAddress;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 收货地址表
*/
@Service("busiShippingAddressService")
public class BusiShippingAddressServiceImpl implements BusiShippingAddressService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiShippingAddress(BusiShippingAddress busiShippingAddress) {
		myBatisDAO.insert(busiShippingAddress);
		return true;
	}

	@Override
	public boolean updateBusiShippingAddress(BusiShippingAddress busiShippingAddress) {
		myBatisDAO.update(busiShippingAddress);
		return true;
	}

	@Override
	public boolean deleteBusiShippingAddress(BusiShippingAddress busiShippingAddress) {
		myBatisDAO.delete(busiShippingAddress);
		return true;
	}

	@Override
	public boolean updateBatchBusiShippingAddressByIds(String bssasUser, List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssasUser", bssasUser);
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchBusiShippingAddressByIds",hashMap);
		return true;
	}

	@Override
	public BusiShippingAddress getBusiShippingAddress(BusiShippingAddress busiShippingAddress) {
		return (BusiShippingAddress) myBatisDAO.findForObject(busiShippingAddress);
	}

	@Override
	public BusiShippingAddress getBusiShippingAddressDefault(String bssasUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssasUser", bssasUser);
		return (BusiShippingAddress) myBatisDAO.findForObject("getBusiShippingAddressDefault", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiShippingAddress> findBusiShippingAddressForListsByMy(String bssasUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssasUser", bssasUser);
		return myBatisDAO.findForList("findBusiShippingAddressForListsByMy", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiShippingAddress> findBusiShippingAddressForPages(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiShippingAddress>(1, 10, 0);
			}
			hashMap.put("list", list);
		}		
		return myBatisDAO.findForPage("findBusiShippingAddressForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}