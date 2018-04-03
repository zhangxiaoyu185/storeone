package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreSystemSet;

/**
* 系统设置表
*/
@Service("coreSystemSetService")
public class CoreSystemSetServiceImpl implements CoreSystemSetService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreSystemSet(CoreSystemSet coreSystemSet) {
		myBatisDAO.insert(coreSystemSet);
		return true;
	}

	@Override
	public boolean updateCoreSystemSet(CoreSystemSet coreSystemSet) {
		myBatisDAO.update(coreSystemSet);
		return true;
	}

	@Override
	public boolean deleteCoreSystemSet(CoreSystemSet coreSystemSet) {
		myBatisDAO.delete(coreSystemSet);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreSystemSetByIds",hashMap);
		return true;
	}

	@Override
	public CoreSystemSet getCoreSystemSet(CoreSystemSet coreSystemSet) {
		return (CoreSystemSet) myBatisDAO.findForObject(coreSystemSet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreSystemSet> findCoreSystemSetList() {
		return myBatisDAO.findForList("findCoreSystemSetForLists", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreSystemSet> findCoreSystemSetPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findCoreSystemSetForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}