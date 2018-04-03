package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiServerTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiServerType;

/**
* 服务类别表
*/
@Service("busiServerTypeService")
public class BusiServerTypeServiceImpl implements BusiServerTypeService {

	private static final SimpleMapMapper<String, BusiServerType> SERVER_TYPE_UUID_MAPPER = new SimpleMapMapper<String, BusiServerType>() {
		@Override
		public String mapKey(BusiServerType object, int rowNum) {
			return object.getBssteUuid();
		}
	};
	
	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiServerType(BusiServerType busiServerType) {
		myBatisDAO.insert(busiServerType);
		return true;
	}

	@Override
	public boolean updateBusiServerType(BusiServerType busiServerType) {
		myBatisDAO.update(busiServerType);
		return true;
	}

	@Override
	public boolean deleteBusiServerType(BusiServerType busiServerType) {
		myBatisDAO.delete(busiServerType);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiServerTypeByIds",hashMap);
		return true;
	}

	@Override
	public BusiServerType getBusiServerType(BusiServerType busiServerType) {
		return (BusiServerType) myBatisDAO.findForObject(busiServerType);
	}

	@Override
	public BusiServerType getBusiServerTypeByName(String bssteName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssteName", bssteName);
		return (BusiServerType) myBatisDAO.findForObject("getBusiServerTypeByName", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BusiServerType> findBusiServerTypeMapByUuidList(List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findBusiServerTypeForListsAll", hashMap, SERVER_TYPE_UUID_MAPPER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiServerType> findBusiServerTypeForLists() {
		return myBatisDAO.findForList("findBusiServerTypeForLists");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiServerType> findBusiServerTypePage(String bssteName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssteName", bssteName);
		return myBatisDAO.findForPage("findBusiServerTypeForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiServerType> findBusiServerTypeForListsAll() {
		return myBatisDAO.findForList("findBusiServerTypeForListsAll");
	}

}