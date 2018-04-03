package com.xiaoyu.lingdian.job;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiOrderService;

public class AutomaticEvaluationJob extends QuartzJobBean {
	
	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
	 
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;
 
	/**
	* 商城订单表
	*/
	private BusiOrderService busiOrderService;

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}
	public void setBusiOrderService(BusiOrderService busiOrderService) {
		this.busiOrderService = busiOrderService;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutomaticReceiptJob start");
		//查询超过7天的待评价的订单
		List<BusiOrder> orderList = busiOrderService.findBusiOrderForListsByOverDayPJ();
		for (BusiOrder order : orderList) {
			try	{
				//不需要评价信息
				Date date = new Date();
				//根据下单订单号更新确认评价时间和成交状态,更新订单明细状态
				BusiOrder busiOrder = new BusiOrder();
				busiOrder.setBsorrUuid(order.getBsorrUuid());
				busiOrder.setBsorrStatus(4);
				busiOrder.setBsorrEvaluateTime(new Date());//评价时间
				busiOrder.setBsorrUdate(date);
				busiOrderService.updateBusiOrder(busiOrder);
				
				//修改订单数量
				coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, 0, 0, -1, 1, 0);
			}catch (Exception e) {
				logger.info("订单"+order.getBsorrUuid()+"超过7天自动评价失败");
			}
		}	
		logger.info("AutomaticEvaluationJob end");
	}

}
