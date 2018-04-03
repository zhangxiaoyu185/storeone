package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiExpressCode;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 快递鸟公司编码配置表
*/
public interface BusiExpressCodeService {

	/**
	* 添加
	* @param busiExpressCode
	* @return
	*/
	public boolean insertBusiExpressCode(BusiExpressCode busiExpressCode);

	/**
	* 修改
	* @param busiExpressCode
	* @return
	*/
	public boolean updateBusiExpressCode(BusiExpressCode busiExpressCode);

	/**
	* 删除
	* @param busiExpressCode
	* @return
	*/
	public boolean deleteBusiExpressCode(BusiExpressCode busiExpressCode);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiExpressCode
	* @return
	*/
	public BusiExpressCode getBusiExpressCode(BusiExpressCode busiExpressCode);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiExpressCode> findBusiExpressCodeList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiExpressCode> findBusiExpressCodePage(int pageNum, int pageSize);

}