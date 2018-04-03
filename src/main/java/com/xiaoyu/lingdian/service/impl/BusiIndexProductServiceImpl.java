package com.xiaoyu.lingdian.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiIndexProductService;
import com.xiaoyu.lingdian.service.BusiProductService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import com.xiaoyu.lingdian.entity.BusiProduct;

/**
* 首页商品表
*/
@Service("busiIndexProductService")
public class BusiIndexProductServiceImpl implements BusiIndexProductService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	@Override
	public boolean insertBusiIndexProduct(BusiIndexProduct busiIndexProduct) {
		myBatisDAO.insert(busiIndexProduct);
		return true;
	}

	@Override
	public boolean updateBusiIndexProduct(BusiIndexProduct busiIndexProduct) {
		myBatisDAO.update(busiIndexProduct);
		return true;
	}

	@Override
	public boolean deleteBusiIndexProduct(BusiIndexProduct busiIndexProduct) {
		myBatisDAO.delete(busiIndexProduct);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiIndexProductByIds",hashMap);
		return true;
	}

	@Override
	public BusiIndexProduct getBusiIndexProduct(BusiIndexProduct busiIndexProduct) {
		return (BusiIndexProduct) myBatisDAO.findForObject(busiIndexProduct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiIndexProduct> findBusiIndexProductPageBefore(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiIndexProductPageBefore", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiIndexProduct> findBusiIndexProductForPages(String productName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();	
		if (!StringUtil.isEmpty(productName)) {
			List<BusiProduct> productList = busiProductService.findBusiProductForPagesByProductName(productName);
			List<String> list = new ArrayList<>();
			for (BusiProduct busiProduct : productList) {
				list.add(busiProduct.getBsprtUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiIndexProduct>(1, 10, 0);
			}
			hashMap.put("list", list);
		}

		return myBatisDAO.findForPage("findBusiIndexProductForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}