package com.xiaoyu.lingdian.job;

import java.util.Date;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.BusiOrderService;
import com.xiaoyu.lingdian.tool.RandomUtil;

/**
 * 自动确认收货
 *
 */
public class AutomaticReceiptJob extends QuartzJobBean {

	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");

	/**
	* 用户消息中心表
	*/
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;

	/**
	* 商品订单表
	*/
	private BusiOrderService busiOrderService;

	public void setBusiMessageCenterService(BusiMessageCenterService busiMessageCenterService) {
		this.busiMessageCenterService = busiMessageCenterService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public void setBusiOrderService(BusiOrderService busiOrderService) {
		this.busiOrderService = busiOrderService;
	}

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutomaticReceiptJob start");
		//查询超过10天的待收货订单
		List<BusiOrder> orderList = busiOrderService.findBusiOrderForListsByOverDayDSH();
		for (BusiOrder order : orderList) {
			try	{
				//添加到用户消息中心
				BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
				busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
				busiMessageCenter.setBsmecUser(order.getBsorrUser());
				busiMessageCenter.setBsmecCdate(new Date());
				busiMessageCenter.setBsmecUdate(new Date());
				busiMessageCenter.setBsmecStatus(1);
				busiMessageCenter.setBsmecType(2);
				busiMessageCenter.setBsmecProductPic(order.getBsorrPic());
				busiMessageCenter.setBsmecContent("亲,您购买的"+order.getBsorrProductName()+"商品已确认收货!");
				busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
				
				//根据下单订单号更新确认收货时间和成交状态,更新订单状态
				BusiOrder busiOrder = new BusiOrder();
				busiOrder.setBsorrUuid(order.getBsorrUuid());
				busiOrder.setBsorrStatus(3);
				busiOrder.setBsorrRecvTime(new Date());
				busiOrder.setBsorrUdate(new Date());
				busiOrderService.updateBusiOrder(busiOrder);
				
				coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, 0, -1, 1, 0, 0);
			}catch (Exception e) {
				logger.info("订单"+order.getBsorrUuid()+"超过10天自动确认收货失败");
			}
		}	
		logger.info("AutomaticReceiptJob end");
	}
	
}