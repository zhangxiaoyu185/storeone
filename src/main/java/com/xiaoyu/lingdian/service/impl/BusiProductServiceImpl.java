package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiProduct;

/**
* 商品表
*/
@Service("busiProductService")
public class BusiProductServiceImpl implements BusiProductService {

	private static final SimpleMapMapper<String, BusiProduct> PRODUCT_UUID_MAPPER = new SimpleMapMapper<String, BusiProduct>() {
		@Override
		public String mapKey(BusiProduct object, int rowNum) {
			return object.getBsprtUuid();
		}
	};

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiProduct(BusiProduct busiProduct) {
		myBatisDAO.insert(busiProduct);
		return true;
	}

	@Override
	public boolean updateBusiProduct(BusiProduct busiProduct) {
		myBatisDAO.update(busiProduct);
		return true;
	}

	@Override
	public boolean updateBusiProductStock(String bsprtUuid, Integer bsprtStock) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsprtUuid", bsprtUuid);
		hashMap.put("bsprtStock", bsprtStock);
		myBatisDAO.update("updateBusiProductStock", hashMap);
		return true;
	}

	@Override
	public BusiProduct getBusiProduct(BusiProduct busiProduct) {
		return (BusiProduct) myBatisDAO.findForObject(busiProduct);
	}

	@Override
	public boolean updateBatchBusiProductDeteleByIds(List<String> list) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchBusiProductDeteleByIds", hashMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProduct> findBusiProductForListsBySale() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiProductForListsBySale", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProduct> findBusiProductForLists() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiProductForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BusiProduct> findBusiProductListByUuids(List<String> list) {
		if(list.size() <= 0) {
			return new HashMap<String, BusiProduct>();
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findBusiProductListByUuids", hashMap, PRODUCT_UUID_MAPPER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiProduct> findBusiProductForPagesByCategory(String bsprtCategory, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsprtCategory", bsprtCategory);
		return myBatisDAO.findForPage("findBusiProductForPagesByCategory", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiProduct> findBusiProductPage(String bsprtCategory, String bsprtName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();	
		hashMap.put("bsprtCategory", bsprtCategory);
		hashMap.put("bsprtName", bsprtName);
		return myBatisDAO.findForPage("findBusiProductForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiProduct> findBusiProductForPagesByProductName(String bsprtName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsprtName", bsprtName);
		return myBatisDAO.findForList("findBusiProductForPagesByProductName", hashMap);
	}

}