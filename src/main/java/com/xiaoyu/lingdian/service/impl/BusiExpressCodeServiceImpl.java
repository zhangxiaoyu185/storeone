package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.BusiExpressCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiExpressCode;

/**
* 快递鸟公司编码配置表
*/
@Service("busiExpressCodeService")
public class BusiExpressCodeServiceImpl implements BusiExpressCodeService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiExpressCode(BusiExpressCode busiExpressCode) {
		myBatisDAO.insert(busiExpressCode);
		return true;
	}

	@Override
	public boolean updateBusiExpressCode(BusiExpressCode busiExpressCode) {
		myBatisDAO.update(busiExpressCode);
		return true;
	}

	@Override
	public boolean deleteBusiExpressCode(BusiExpressCode busiExpressCode) {
		myBatisDAO.delete(busiExpressCode);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiExpressCodeByIds",hashMap);
		return true;
	}

	@Override
	public BusiExpressCode getBusiExpressCode(BusiExpressCode busiExpressCode) {
		return (BusiExpressCode) myBatisDAO.findForObject(busiExpressCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiExpressCode> findBusiExpressCodeList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiExpressCodeForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiExpressCode> findBusiExpressCodePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiExpressCodeForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}