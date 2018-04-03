package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.RandomUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiEvaluate;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.BusiEvaluateService;
import com.xiaoyu.lingdian.service.BusiOrderService;
import com.xiaoyu.lingdian.vo.BusiEvaluateVO;

@Controller
@RequestMapping(value="/busiEvaluate")
@Api(value="busiEvaluate", description="评价相关操作")
public class BusiEvaluateController extends BaseController {

	/**
	* 评价表
	*/
	@Autowired
	private BusiEvaluateService busiEvaluateService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 商城订单表
	*/
	@Autowired
	private BusiOrderService busiOrderService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 评价（默认启用）
	*
	* @param bseveOrderItem 订单明细标识
	* @param bseveProduct 商品标识
	* @param bseveAttrName 商品名称
	* @param bsorrPic 封面图
	* @param bseveUser 评价人(通过用户表去获取姓名和头像)
	* @param bseveContent 评价内容
	* @param bseveGrade 评价等级:1好评2中评3差评
	* @param bsevePic1 评价图1
	* @param bsevePic2 评价图2
	* @param bsevePic3 评价图3
	* @param bsevePic4 评价图4
	* @param bsevePic5 评价图5
	* @return
	*/
	@ApiOperation(value="添加(默认为启用)", httpMethod = "POST", notes = "添加(默认为启用)")
	@RequestMapping(value="/add/busiEvaluate", method=RequestMethod.POST)
	public void addBusiEvaluate(
			@ApiParam(value="订单标识", required = true) @RequestParam(value="bseveOrderItem", required = true) String bseveOrderItem, 
			@ApiParam(value="商品标识", required = true) @RequestParam(value="bseveProduct", required = true) String bseveProduct, 
			@ApiParam(value="商品名称", required = true) @RequestParam(value="bseveAttrName", required = true) String bseveAttrName, 
			@ApiParam(value="封面图", required = false) @RequestParam(value="bsorrPic", required = false) String bsorrPic, 
			@ApiParam(value="评价人标识", required = true) @RequestParam(value="bseveUser", required = true) String bseveUser, 
			@ApiParam(value="评价内容", required = true) @RequestParam(value="bseveContent", required = true) String bseveContent, 
			@ApiParam(value="评价等级:1好评2中评3差评", required = true) @RequestParam(value="bseveGrade", required = true) Integer bseveGrade, 
			@ApiParam(value="评价图1", required = false) @RequestParam(value="bsevePic1", required = false) String bsevePic1, 
			@ApiParam(value="评价图2", required = false) @RequestParam(value="bsevePic2", required = false) String bsevePic2, 
			@ApiParam(value="评价图3", required = false) @RequestParam(value="bsevePic3", required = false) String bsevePic3, 
			@ApiParam(value="评价图4", required = false) @RequestParam(value="bsevePic4", required = false) String bsevePic4, 
			@ApiParam(value="评价图5", required = false) @RequestParam(value="bsevePic5", required = false) String bsevePic5, 
			HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin addBusiEvaluate");
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bseveUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "评价人不存在!"), response);
			logger.info("[BusiEvaluateController]:end addBusiEvaluate");
			return;
		}
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bseveOrderItem);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiEvaluateController]:end addBusiEvaluate");
			return;
		}
		if (order.getBsorrStatus() != 3) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单当前状态不能评价!"), response);
			logger.info("[BusiEvaluateController]:end addBusiEvaluate");
			return;
		}
		if (!order.getBsorrUser().equals(bseveUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "购买人和评价人不一致!"), response);
			logger.info("[BusiEvaluateController]:end addBusiEvaluate");
			return;
		}
		Date date = new Date();		BusiEvaluate busiEvaluate = new BusiEvaluate();
		String uuid = RandomUtil.generateString(16);
		busiEvaluate.setBseveUuid(uuid);
		busiEvaluate.setBseveOrderItem(bseveOrderItem);
		busiEvaluate.setBseveProduct(bseveProduct);
		busiEvaluate.setBseveAttrName(bseveAttrName);
		busiEvaluate.setBseveUser(bseveUser);
		busiEvaluate.setBseveName(coreUser.getCrusrName());
		busiEvaluate.setBseveHead(coreUser.getCrusrHead());
		busiEvaluate.setBseveContent(bseveContent);
		busiEvaluate.setBseveGrade(bseveGrade);
		busiEvaluate.setBseveStatus(1);
		busiEvaluate.setBsevePic1(bsevePic1);
		busiEvaluate.setBsevePic2(bsevePic2);
		busiEvaluate.setBsevePic3(bsevePic3);
		busiEvaluate.setBsevePic4(bsevePic4);
		busiEvaluate.setBsevePic5(bsevePic5);
		busiEvaluate.setBseveCdate(date);
		busiEvaluateService.insertBusiEvaluate(busiEvaluate);

		//修改用户的待评价数量等
		coreUserService.updateCoreUserByOrderCount(bseveUser, 0, 0, 0, -1, 1, 0);
		
		//修改订单的状态为已完成
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bseveOrderItem);
		busiOrder.setBsorrStatus(4);
		busiOrder.setBsorrEvaluateTime(date);
		busiOrder.setBsorrFinishTime(date);
		busiOrder.setBsorrUdate(date);
		busiOrderService.updateBusiOrder(busiOrder);

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(bseveUser);
		busiMessageCenter.setBsmecCdate(date);
		busiMessageCenter.setBsmecUdate(date);
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(2);
		busiMessageCenter.setBsmecProductPic(bsorrPic);
		busiMessageCenter.setBsmecContent("感谢亲对"+bseveAttrName+"商品的评价!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "评价成功!"),response);
		logger.info("[BusiEvaluateController]:end addBusiEvaluate");
	}

	/**
	* 后台订单的评价查看
	* 
	* @return
	*/
	@ApiOperation(value="后台订单的评价查看", httpMethod = "GET", notes = "后台订单的评价查看", response = BusiEvaluateVO.class)
	@RequestMapping(value="/find/view/by/order", method=RequestMethod.GET)
	public void findBusiEvaluateByOrder(
			@ApiParam(value="订单标识", required = true) @RequestParam(value="bseveOrderItem", required = true) String bseveOrderItem, 
			HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin findBusiEvaluateByOrder");	
		BusiEvaluate busiEvaluate = new BusiEvaluate();
		busiEvaluate.setBseveOrderItem(bseveOrderItem);		busiEvaluate = busiEvaluateService.getBusiEvaluate(busiEvaluate);		
		BusiEvaluateVO vo = new BusiEvaluateVO();		vo.convertPOToVO(busiEvaluate);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取后台订单的评价成功!", vo),response);
		logger.info("[BusiEvaluateController]:end findBusiEvaluateByOrder");
	}

	/**
	* 前台商品的评价查看<Page>
	* 
	* @param bseveProduct 商品
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="前台商品的评价查看", httpMethod = "GET", notes = "前台商品的评价查看", response = BusiEvaluateVO.class)
	@RequestMapping(value="/find/by/cnd/by/product", method=RequestMethod.GET)
	public void findBusiEvaluatePage(
			@ApiParam(value="商品标识", required = true) @RequestParam(value="bseveProduct", required = true) String bseveProduct, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin findBusiEvaluatePage");				if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiEvaluate> page = busiEvaluateService.findBusiEvaluateForPages(bseveProduct, pageNum, pageSize);
		Page<BusiEvaluateVO> pageVO = new Page<BusiEvaluateVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiEvaluateVO> vos = new ArrayList<BusiEvaluateVO>();
		BusiEvaluateVO vo;
		for (BusiEvaluate busiEvaluate : page.getResult()) {
			vo = new BusiEvaluateVO();			vo.convertPOToVO(busiEvaluate);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiEvaluateController]:end findBusiEvaluatePage");
	}

}