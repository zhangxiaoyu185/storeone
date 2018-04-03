package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiProductCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiProductCategory;

/**
* 分类表
*/
@Service("busiProductCategoryService")
public class BusiProductCategoryServiceImpl implements BusiProductCategoryService {

	private static final SimpleMapMapper<String, BusiProductCategory> CATEGORY_UUID_MAPPER = new SimpleMapMapper<String, BusiProductCategory>() {
		@Override
		public String mapKey(BusiProductCategory object, int rowNum) {
			return object.getBspcyUuid();
		}
	};

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiProductCategory(BusiProductCategory busiProductCategory) {
		myBatisDAO.insert(busiProductCategory);
		return true;
	}

	@Override
	public boolean updateBusiProductCategory(BusiProductCategory busiProductCategory) {
		myBatisDAO.update(busiProductCategory);
		return true;
	}

	@Override
	public boolean updateBusiProductCategoryDisableByTop(String bspcyTop) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspcyTop", bspcyTop);
		myBatisDAO.update("updateBusiProductCategoryDisableByTop", hashMap);
		return true;
	}

	@Override
	public boolean updatBatchBusiProductCategoryDeteleByTop(String bspcyTop) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspcyTop", bspcyTop);
		myBatisDAO.update("updatBatchBusiProductCategoryDeteleByTop",hashMap);
		return true;
	}

	@Override
	public boolean updatBatchBusiProductCategoryDeteleByIds(List<String> list) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updatBatchBusiProductCategoryDeteleByIds",hashMap);
		return true;
	}

	@Override
	public BusiProductCategory getBusiProductCategory(BusiProductCategory busiProductCategory) {
		return (BusiProductCategory) myBatisDAO.findForObject(busiProductCategory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProductCategory> findBusiProductCategoryForListsHaveChild() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiProductCategoryForListsHaveChild", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProductCategory> findBusiProductCategoryForListsByTop(String bspcyTop) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspcyTop", bspcyTop);
		return myBatisDAO.findForList("findBusiProductCategoryForListsByTop", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProductCategory> findBusiProductCategoryForListsByChild() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiProductCategoryForListsByChild", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProductCategory> findBusiProductCategoryForListsAll() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiProductCategoryForListsAll", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiProductCategory> findBusiProductCategoryPage(String bspcyTop, String bspcyName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspcyTop", bspcyTop);
		hashMap.put("bspcyName", bspcyName);
		return myBatisDAO.findForPage("findBusiProductCategoryForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProductCategory> findBusiProductCategoryList(String bspcyName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspcyName", bspcyName);
		return myBatisDAO.findForList("findBusiProductCategoryForPages", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BusiProductCategory> findBusiProductCategoryListByUuids(List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findBusiProductCategoryListByUuids", hashMap, CATEGORY_UUID_MAPPER);
	}

}