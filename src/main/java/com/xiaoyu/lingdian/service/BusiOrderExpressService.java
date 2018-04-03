package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.BusiOrderExpress;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 订单明细快递物流表
*/
public interface BusiOrderExpressService {

	/**
	* 添加
	* @param busiOrderExpress
	* @return
	*/
	public boolean insertBusiOrderExpress(BusiOrderExpress busiOrderExpress);

	/**
	* 修改
	* @param busiOrderExpress
	* @return
	*/
	public boolean updateBusiOrderExpress(BusiOrderExpress busiOrderExpress);

	/**
	* 删除
	* @param busiOrderExpress
	* @return
	*/
	public boolean deleteBusiOrderExpress(BusiOrderExpress busiOrderExpress);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiOrderExpress
	* @return
	*/
	public BusiOrderExpress getBusiOrderExpress(BusiOrderExpress busiOrderExpress);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiOrderExpress> findBusiOrderExpressList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiOrderExpress> findBusiOrderExpressPage(int pageNum, int pageSize);
	
	/**
	 * 根据子订单ID查询物流信息
	 * @param busiOrderExpress
	 * @return
	 */
	public List<BusiOrderExpress> findBusiOrderExpressForListsbyOrderId(BusiOrderExpress busiOrderExpress);

//<=================定制内容开始==============
//==================定制内容结束==============>

}