package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiOrderExpressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiOrderExpress;

/**
* 订单快递物流表
*/
@Service("busiOrderExpressService")
public class BusiOrderExpressServiceImpl implements BusiOrderExpressService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiOrderExpress(BusiOrderExpress busiOrderExpress) {
		myBatisDAO.insert(busiOrderExpress);
		return true;
	}

	@Override
	public boolean updateBusiOrderExpress(BusiOrderExpress busiOrderExpress) {
		myBatisDAO.update(busiOrderExpress);
		return true;
	}

	@Override
	public boolean deleteBusiOrderExpress(BusiOrderExpress busiOrderExpress) {
		myBatisDAO.delete(busiOrderExpress);
		return true;
	}

	private static final String DELETEBATCH_BUSIORDEREXPRESS_BY_IDS="deleteBatchBusiOrderExpressByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_BUSIORDEREXPRESS_BY_IDS,hashMap);
		return true;
	}

	@Override
	public BusiOrderExpress getBusiOrderExpress(BusiOrderExpress busiOrderExpress) {
		return (BusiOrderExpress) myBatisDAO.findForObject(busiOrderExpress);
	}

	private static final String FIND_BUSIORDEREXPRESS_FOR_LISTS="findBusiOrderExpressForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderExpress> findBusiOrderExpressList() {
		return myBatisDAO.findForList(FIND_BUSIORDEREXPRESS_FOR_LISTS, null);
	}

	private static final String FIND_BUSIORDEREXPRESS_FOR_PAGES="findBusiOrderExpressForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiOrderExpress> findBusiOrderExpressPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage(FIND_BUSIORDEREXPRESS_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	private static final String FIND_BUSIORDEREXPRESS_FOR_LISTS_BY_ORDERID="findBusiOrderExpressForListsbyOrderId";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderExpress> findBusiOrderExpressForListsbyOrderId(BusiOrderExpress busiOrderExpress) { 
		return myBatisDAO.findForList(FIND_BUSIORDEREXPRESS_FOR_LISTS_BY_ORDERID,busiOrderExpress);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}