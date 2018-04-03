package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.BusiOrderExpress;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.BusiOrderExpressService;
import com.xiaoyu.lingdian.service.BusiOrderService;
import com.xiaoyu.lingdian.service.BusiProductService;
import com.xiaoyu.lingdian.service.CoreMessageCenterService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.KdniaoTrackQueryAPI;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.BusiOrderExpressVO;
import com.xiaoyu.lingdian.vo.BusiOrderVO;

@Controller
@RequestMapping(value="/busiOrder")
@Api(value="busiOrder", description="商城订单相关操作")
public class BusiOrderController extends BaseController {

	/**
	* 商城订单表
	*/
	@Autowired
	private BusiOrderService busiOrderService;
	
	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 系统消息中心表
	*/
	@Autowired
	private CoreMessageCenterService coreMessageCenterService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 订单明细快递物流表
	*/
	@Autowired
	private BusiOrderExpressService busiOrderExpressService;

	/**
	* 支付生成订单
	*
	* @param bsorrUser 所属用户
	* @param bsorrTotalValue 订单总价
	* @param bsorrProduct 商品标识
	* @param bsorrProductName 商品名称
	* @param bsorrPic 封面图
	* @param bsorrTags 标签集合（七|赠|正）
	* @param bsorrQuantity 购买数量
	* @param bsorrExpress 快递费用
	* @param bsorrPrice 商品单价
	* @param bsorrBuyerMemo 买家留言（下单时使用）
	* @param bsorrName 收货人姓名
	* @param bsorrIdCard 收货人身份证号
	* @param bsorrMobile 收货人联系方式
	* @param bsorrZipCode 邮政编码
	* @param bsorrProvince 所在省
	* @param bsorrProvinceName 所在省名称
	* @param bsorrCity 所在市
	* @param bsorrCityName 所在市名称
	* @param bsorrCounty 所在区
	* @param bsorrCountyName 所在区名称
	* @param bsorrAddress 详细地址
	* @return
	*/
	@ApiOperation(value="支付生成订单", httpMethod = "POST", notes = "支付生成订单")
	@RequestMapping(value="/add/busiOrder", method=RequestMethod.POST)
	public void addBusiOrder(
			@ApiParam(value="所属用户", required = true) @RequestParam(value="bsorrUser", required = true) String bsorrUser, 
			@ApiParam(value="订单总价", required = true) @RequestParam(value="bsorrTotalValue", required = true) Double bsorrTotalValue, 
			@ApiParam(value="商品标识", required = true) @RequestParam(value="bsorrProduct", required = true) String bsorrProduct, 
			@ApiParam(value="商品名称", required = true) @RequestParam(value="bsorrProductName", required = true) String bsorrProductName, 
			@ApiParam(value="封面图", required = false) @RequestParam(value="bsorrPic", required = false) String bsorrPic, 
			@ApiParam(value="标签集合（七|赠|正）", required = false) @RequestParam(value="bsorrTags", required = false) String bsorrTags, 
			@ApiParam(value="购买数量", required = true) @RequestParam(value="bsorrQuantity", required = true) Integer bsorrQuantity, 
			@ApiParam(value="快递费用", required = true) @RequestParam(value="bsorrExpress", required = true) Double bsorrExpress, 
			@ApiParam(value="商品单价", required = true) @RequestParam(value="bsorrPrice", required = true) Double bsorrPrice, 
			@ApiParam(value="买家留言（下单时使用）", required = false) @RequestParam(value="bsorrBuyerMemo", required = false) String bsorrBuyerMemo, 
			@ApiParam(value="收货人姓名", required = true) @RequestParam(value="bsorrName", required = true) String bsorrName, 
			@ApiParam(value="收货人身份证号", required = false) @RequestParam(value="bsorrIdCard", required = false) String bsorrIdCard, 
			@ApiParam(value="收货人联系方式", required = true) @RequestParam(value="bsorrMobile", required = true) String bsorrMobile, 
			@ApiParam(value="邮政编码", required = false) @RequestParam(value="bsorrZipCode", required = false) String bsorrZipCode, 
			@ApiParam(value="所在省", required = true) @RequestParam(value="bsorrProvince", required = true) String bsorrProvince, 
			@ApiParam(value="所在省名称", required = true) @RequestParam(value="bsorrProvinceName", required = true) String bsorrProvinceName, 
			@ApiParam(value="所在市", required = true) @RequestParam(value="bsorrCity", required = true) String bsorrCity, 
			@ApiParam(value="所在市名称", required = true) @RequestParam(value="bsorrCityName", required = true) String bsorrCityName, 
			@ApiParam(value="所在区", required = true) @RequestParam(value="bsorrCounty", required = true) String bsorrCounty,
			@ApiParam(value="所在区名称", required = true) @RequestParam(value="bsorrCountyName", required = true) String bsorrCountyName, 
			@ApiParam(value="详细地址", required = true) @RequestParam(value="bsorrAddress", required = true) String bsorrAddress, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin addBusiOrder");
		if (bsorrQuantity * bsorrPrice != bsorrPrice) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品总价计算错误！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}
		if (bsorrPrice + bsorrExpress != bsorrTotalValue) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单总价计算错误！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}	
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bsorrUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}		
		if (coreUser.getCrusrFee() < bsorrTotalValue) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "余额不足，请先充值！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}
		//判断库存
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsorrProduct);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}		
		if (busiProduct.getBsprtStock() < bsorrQuantity) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品库存不足！"), response);
			logger.info("[BusiOrderController]:end addBusiOrder.");
			return;
		}		
		BusiOrder busiOrder = new BusiOrder();
		String uuid = RandomUtil.generateString(16);
		busiOrder.setBsorrUuid(uuid);
		busiOrder.setBsorrUser(bsorrUser);
		busiOrder.setBsorrTotalValue(bsorrTotalValue);
		busiOrder.setBsorrProduct(bsorrProduct);
		busiOrder.setBsorrProductName(bsorrProductName);
		busiOrder.setBsorrPic(bsorrPic);
		busiOrder.setBsorrTags(bsorrTags);
		busiOrder.setBsorrQuantity(bsorrQuantity);
		busiOrder.setBsorrExpress(bsorrExpress);
		busiOrder.setBsorrPrice(bsorrPrice);
		busiOrder.setBsorrBuyerMemo(bsorrBuyerMemo);
		busiOrder.setBsorrStatus(1);
		busiOrder.setBsorrCdate(new Date());
		busiOrder.setBsorrUdate(new Date());
		busiOrder.setBsorrName(bsorrName);
		busiOrder.setBsorrIdCard(bsorrIdCard);
		busiOrder.setBsorrMobile(bsorrMobile);
		busiOrder.setBsorrZipCode(bsorrZipCode);
		busiOrder.setBsorrProvince(bsorrProvince);
		busiOrder.setBsorrProvinceName(bsorrProvinceName);
		busiOrder.setBsorrCity(bsorrCity);
		busiOrder.setBsorrCityName(bsorrCityName);
		busiOrder.setBsorrCounty(bsorrCounty);
		busiOrder.setBsorrCountyName(bsorrCountyName);
		busiOrder.setBsorrAddress(bsorrAddress);
		busiOrderService.insertBusiOrder(busiOrder);
		busiProductService.updateBusiProductStock(bsorrProduct, bsorrQuantity);		
		coreUserService.updateCoreUserByFee(bsorrUser, -bsorrTotalValue);		
		coreUserService.updateCoreUserByOrderCount(bsorrUser, 1, 1, 0, 0, 0, 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "下单成功!"),response);
		logger.info("[BusiOrderController]:end addBusiOrder");
	}
	
	/**
	 * 提醒发货
	 * 
	 * @param bsorrUuid 订单号
	 */
	@ApiOperation(value="提醒发货", httpMethod = "POST", notes = "提醒发货")
	@RequestMapping(value="/prompt/shipment", method=RequestMethod.POST)
	public void promptShipment(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin promptShipment");
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end promptShipment");
			return;
		}
		if (1 != order.getBsorrStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能提醒发货!"), response);
			logger.info("[BusiOrderController]:end promptShipment");
			return;
		}
		//发送提醒消息
		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		String uuid = RandomUtil.generateString(16);
		coreMessageCenter.setCrmecUuid(uuid);
		coreMessageCenter.setCrmecContent("订单号"+order.getBsorrUuid()+order.getBsorrProductName()+"提醒发货");
		coreMessageCenter.setCrmecCdate(new Date());
		coreMessageCenter.setCrmecUdate(new Date());
		coreMessageCenter.setCrmecStatus(2);
		coreMessageCenter.setCrmecType(1);
		coreMessageCenterService.insertCoreMessageCenter(coreMessageCenter);		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(order.getBsorrUuid());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(2);
		busiMessageCenter.setBsmecProductPic(order.getBsorrPic());
		busiMessageCenter.setBsmecContent("您购买的"+order.getBsorrProductName()+"商品提醒发货成功!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发送消息提醒成功!"),response);
		logger.info("[BusiOrderController]:end promptShipment");
	}

	/**
	 * 查看物流信息列表<List>
	 * 
	 * @param bsorrUuid 订单号
	 * @param bsorrExpressCode 物流单号
	 * @param bsorrExpno 物流公司编码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="查看物流信息列表", httpMethod = "GET", notes = "查看物流信息列表", response = BusiOrderExpressVO.class)
	@RequestMapping(value = "/find/logistics/list", method = RequestMethod.GET)
	public void findLogisticsList(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			@ApiParam(value="物流单号(快递单号)", required = true) @RequestParam(value="bsorrExpressCode", required = true) String bsorrExpressCode,
			@ApiParam(value="物流公司编码", required = true) @RequestParam(value="bsorrExpno", required = true) String bsorrExpno, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findLogisticsList");
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end promptShipment");
			return;
		}
		if (order.getBsorrStatus() < 2 || StringUtil.isEmpty(order.getBsorrExpno()) || StringUtil.isEmpty(order.getBsorrExpressCode())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单发货信息不存在!"),response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}		 
		List<BusiOrderExpressVO> listExpressVO = new ArrayList<BusiOrderExpressVO>();
		BusiOrderExpressVO expressVO;		
		// 初始化物流信息对象并插入订单ID
		BusiOrderExpress busiOrderExpress = new BusiOrderExpress();
		busiOrderExpress.setBsoesOrderItem(bsorrUuid);			
		if (order.getBsorrStatus() > 2) { // 如果状态为确认收货后，直接查询数据库
			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);// 查询单个id的物流
			if (null == listsbyOrderId || listsbyOrderId.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "物流信息已过期!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			for (BusiOrderExpress express : listsbyOrderId) {
				expressVO = new BusiOrderExpressVO();
				expressVO.convertPOToVO(express);
				listExpressVO.add(expressVO);
			}
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "物流信息查看成功!", listExpressVO), response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}	  
		String result = "";
		KdniaoTrackQueryAPI kiaoApi = new KdniaoTrackQueryAPI();		
		if (null == order.getBsorrLastDate()) { // 第一次查询
			// 更新商品订单物流上次查询时间为现在
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(bsorrUuid);
			busiOrder.setBsorrLastDate(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
			// 去物流公司查询
			JSONArray jsonArray = null;
			try {
				result = kiaoApi.getOrderTracesByJson(bsorrExpressCode, bsorrExpno);
				if (null != result) {
					JSONObject parseObject = JSON.parseObject(result);
					if (parseObject.getBoolean("Success")) {
						jsonArray = parseObject.getJSONArray("Traces");
					} else {
						writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "不支持该快递公司查询，或者快递公司编码错误!"), response);
						logger.info("[BusiOrderController]:end findLogisticsList");
						return;
					}
				} else {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
					logger.info("[BusiOrderController]:end findLogisticsList");
					return;
				}
			} catch (Exception e) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList" + e.getMessage());
				return;
			}
			// 查询到数据解析存储到物流表，返回数据
			if (null == jsonArray || jsonArray.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			// 1、插入数据库
			for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {// 循环访问数据库待改
				JSONObject job = (JSONObject) iterator.next();
				if (null != job && job.getString("AcceptStation") != null && job.getDate("AcceptTime") != null) {
					BusiOrderExpress busiOrderExpressL = new BusiOrderExpress();
					busiOrderExpressL.setBsoesUuid(RandomUtil.generateString(16));
					busiOrderExpressL.setBsoesOrderItem(bsorrUuid);
					busiOrderExpressL.setBsoesContent(job.getString("AcceptStation"));
					busiOrderExpressL.setBsoesCdate(job.getDate("AcceptTime"));
					busiOrderExpressService.insertBusiOrderExpress(busiOrderExpressL);
				}
			}
			// 2、查插入的数据库数据
			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);
			if (null == listsbyOrderId || listsbyOrderId.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			for (BusiOrderExpress express : listsbyOrderId) {
				expressVO = new BusiOrderExpressVO();
				expressVO.convertPOToVO(express);
				listExpressVO.add(expressVO);
			}
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "查看物流信息成功!", listExpressVO), response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
		// 30分钟内直接查询数据库
		Date data = new Date();
		long minutes = DateUtil.minuteDiff(order.getBsorrLastDate(), data);
		if (minutes <= 30) {
			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);// 查询单个id的物流
			if (null == listsbyOrderId || listsbyOrderId.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			for (BusiOrderExpress express : listsbyOrderId) {
				expressVO = new BusiOrderExpressVO();
				expressVO.convertPOToVO(express);
				listExpressVO.add(expressVO);
			}
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "查看物流信息成功!", listExpressVO), response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
		// 超过30分钟，重新去物流公司查
		if (minutes > 30) {
			// 更新商品订单物流上次查询时间为现在
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(bsorrUuid);
			busiOrder.setBsorrLastDate(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
			// 去物流公司查询
			JSONArray jsonArray = null;
			try {
				result = kiaoApi.getOrderTracesByJson(bsorrExpressCode, bsorrExpno);
				if (null != result) {
					JSONObject parseObject = JSON.parseObject(result);
					if (parseObject.getBoolean("Success")) {
						jsonArray = parseObject.getJSONArray("Traces");
					} else {
						writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "不支持该快递公司查询，或者快递公司编码错误!"), response);
						logger.info("[BusiOrderController]:end findLogisticsList");
						return;
					}
				} else {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
					logger.info("[BusiOrderController]:end findLogisticsList");
					return;
				}
			} catch (Exception e) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList" + e.getMessage());
				return;
			}
			// 查询到数据就del原表中数据，解析insert查询到的数据返回数据
			if (null == jsonArray || jsonArray.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			// 1、 删除原有信息
			busiOrderExpressService.deleteBusiOrderExpress(busiOrderExpress);
			// 2、插入数据库
			for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {// 循环访问数据库待改
				JSONObject job = (JSONObject) iterator.next();
				if (null != job && job.getString("AcceptStation") != null && job.getDate("AcceptTime") != null) {
					BusiOrderExpress busiOrderExpressL = new BusiOrderExpress();
					busiOrderExpressL.setBsoesUuid(RandomUtil.generateString(16));
					busiOrderExpressL.setBsoesOrderItem(bsorrUuid);
					busiOrderExpressL.setBsoesContent(job.getString("AcceptStation"));
					busiOrderExpressL.setBsoesCdate(job.getDate("AcceptTime"));
					busiOrderExpressService.insertBusiOrderExpress(busiOrderExpressL);
				}
			}
			// 3、查询数据库返回的信息
			List<BusiOrderExpress> listExpressById = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);
			if (null == listExpressById || listExpressById.size() == 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"), response);
				logger.info("[BusiOrderController]:end findLogisticsList");
				return;
			}
			for (BusiOrderExpress express : listExpressById) {
				expressVO = new BusiOrderExpressVO();
				expressVO.convertPOToVO(express);
				listExpressVO.add(expressVO);
			}
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "查看物流信息成功!", listExpressVO), response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
	}	

	/**
	 * 前端查询我的各个状态的分页订单
	 * 
	 * @param bsorrUser 用户标识
	 * @param bsorrStatus 订单状态号
	 * @param pageNum
	 * @param pageSize 
	 */
	@ApiOperation(value="前端查询我的各个状态的分页订单", httpMethod = "GET", notes = "前端查询我的各个状态的分页订单", response = BusiOrderVO.class)
	@RequestMapping(value="/find/by/cnd/my/status", method=RequestMethod.GET)
	public void findBusiOrderForPagesByMyStatus(
			@ApiParam(value="用户标识", required = true) @RequestParam(value="bsorrUser", required = true) String bsorrUser, 
			@ApiParam(value="订单状态", required = true) @RequestParam(value="bsorrStatus", required = true) Integer bsorrStatus, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderForPagesByMyStatus");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrder> page = busiOrderService.findBusiOrderForPagesByMyStatus(null, bsorrUser, bsorrStatus, pageNum, pageSize);
		Page<BusiOrderVO> pageVO = new Page<BusiOrderVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiOrderVO> vos = new ArrayList<BusiOrderVO>();
		BusiOrderVO vo;
		for (BusiOrder busiOrder : page.getResult()) {
			vo = new BusiOrderVO();
			vo.convertPOToVO(busiOrder);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderForPagesByMyStatus");
	}

	/**
	 * 后台根据订单号查询各个状态的分页订单
	 * 
	 * @param bsorrUuid 订单号
	 * @param bsorrStatus 订单状态
	 * @param pageNum
	 * @param pageSize 
	 */
	@ApiOperation(value="后台根据订单号查询各个状态的分页订单", httpMethod = "GET", notes = "后台根据订单号查询各个状态的分页订单", response = BusiOrderVO.class)
	@RequestMapping(value="/find/by/cnd/my/status/back", method=RequestMethod.GET)
	public void findBusiOrderForPagesByMyStatusBack(
			@ApiParam(value="订单号标识", required = false) @RequestParam(value="bsorrUuid", required = false) String bsorrUuid, 
			@ApiParam(value="订单状态", required = true) @RequestParam(value="bsorrStatus", required = true) Integer bsorrStatus, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderForPagesByMyStatusBack");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrder> page = busiOrderService.findBusiOrderForPagesByMyStatus(bsorrUuid, null, bsorrStatus, pageNum, pageSize);
		Page<BusiOrderVO> pageVO = new Page<BusiOrderVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiOrder busiOrder : page.getResult()) {
			hashUserUuids.add(busiOrder.getBsorrUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);		
		List<BusiOrderVO> vos = new ArrayList<BusiOrderVO>();
		BusiOrderVO vo;
		for (BusiOrder busiOrder : page.getResult()) {
			vo = new BusiOrderVO();
			vo.convertPOToVO(busiOrder);
			vo.setBsorrUserName(userMap.get(busiOrder.getBsorrUser())==null?null:userMap.get(busiOrder.getBsorrUser()).getCrusrName());		
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderForPagesByMyStatusBack");
	}

	/**
	 * 后台发货，必填快递单号
	 * 
	 * @param bsorrUuid 订单号
	 * @param bsorrExpno 快递单号
	 * @param bsorrExpressCode 快递公司编号
	 * @param bsorrExpressName 快递公司名称
	 */
	@RequestMapping(value="/back/shipment", method=RequestMethod.POST)
	public void backShipment(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			@ApiParam(value="快递单号", required = true) @RequestParam(value="bsorrExpno", required = true) String bsorrExpno, 
			@ApiParam(value="快递公司编号", required = true) @RequestParam(value="bsorrExpressCode", required = true) String bsorrExpressCode, 
			@ApiParam(value="快递公司名称", required = false) @RequestParam(value="bsorrExpressName", required = false) String bsorrExpressName, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin backShipment");
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end backShipment");
			return;
		}
		if (1 != order.getBsorrStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能发货!"), response);
			logger.info("[BusiOrderController]:end backShipment");
			return;
		}
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(order.getBsorrUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(2);
		busiMessageCenter.setBsmecProductPic(order.getBsorrPic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+order.getBsorrProductName()+"商品发货了，请关注商品的物流信息");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);		
		//更新订单明细状态
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);
		busiOrder.setBsorrStatus(2);
		busiOrder.setBsorrShippingTime(new Date());
		busiOrder.setBsorrUdate(new Date());
		busiOrder.setBsorrExpno(bsorrExpno);
		busiOrder.setBsorrExpressCode(bsorrExpressCode);
		busiOrder.setBsorrExpressName(bsorrExpressName);		
		busiOrderService.updateBusiOrder(busiOrder);	
		coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, -1, 1, 0, 0, 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台发货成功!"),response);
		logger.info("[BusiOrderController]:end backShipment");
	}
	
	/**
	 * 用户确认收货
	 * 
	 * @param bsorrUuid 订单号
	 */
	@ApiOperation(value="用户确认收货", httpMethod = "POST", notes = "用户确认收货")
	@RequestMapping(value="/recv/receipt", method=RequestMethod.POST)
	public void recvReceipt(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin recvReceipt");
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end recvReceipt");
			return;
		}		
		if (order.getBsorrStatus() != 2) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态不能确认收货!"),response);
			logger.info("[BusiOrderController]:end recvReceipt");
			return;
		}		
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
		busiOrder.setBsorrUuid(bsorrUuid);
		busiOrder.setBsorrStatus(3);
		busiOrder.setBsorrRecvTime(new Date());
		busiOrder.setBsorrUdate(new Date());
		busiOrderService.updateBusiOrder(busiOrder);		
		coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, 0, -1, 1, 0, 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "确认收货成功!"),response);
		logger.info("[BusiOrderController]:end recvReceipt");
	}
	
	/**
	 * 删除订单
	 * 
	 * @param bsorrUuid 订单号
	 */
	@ApiOperation(value="删除订单", httpMethod = "POST", notes = "删除订单")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin delete");
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end delete");
			return;
		}
		if (4 != order.getBsorrStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能删除订单!"), response);
			logger.info("[BusiOrderController]:end delete");
			return;
		}		
		Date date = new Date();		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(order.getBsorrUser());
		busiMessageCenter.setBsmecCdate(date);
		busiMessageCenter.setBsmecUdate(date);
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(2);
		busiMessageCenter.setBsmecProductPic(order.getBsorrPic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+order.getBsorrProductName()+"商品订单已删除!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);	
		//更新订单状态
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);
		busiOrder.setBsorrStatus(5);
		busiOrder.setBsorrDelTime(date);
		busiOrder.setBsorrUdate(date);
		busiOrderService.updateBusiOrder(busiOrder);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除订单成功!"),response);
		logger.info("[BusiOrderController]:end delete");
	}

	/**
	* 获取单个订单
	* 
	* @param bsorrUuid 订单号
	*/
	@ApiOperation(value="获取单个订单", httpMethod = "GET", notes = "获取单个订单", response = BusiOrderVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiOrder(
			@ApiParam(value="订单号标识", required = true) @RequestParam(value="bsorrUuid", required = true) String bsorrUuid, 
			HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin viewsBusiOrder");
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);
		busiOrder = busiOrderService.getBusiOrder(busiOrder);
		if (busiOrder == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该订单不存在!"), response);
			logger.info("[BusiOrderController]:end viewsBusiOrder");
			return;
		}		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiOrder.getBsorrUser());
		coreUser = coreUserService.getCoreUser(coreUser);		
		BusiOrderVO busiOrderVO = new BusiOrderVO();
		busiOrderVO.convertPOToVO(busiOrder);
		if (coreUser != null) {
			busiOrderVO.setBsorrUserName(coreUser==null?null:coreUser.getCrusrName());
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiOrderVO), response);
		logger.info("[BusiOrderController]:end viewsBusiOrder");
	}

}