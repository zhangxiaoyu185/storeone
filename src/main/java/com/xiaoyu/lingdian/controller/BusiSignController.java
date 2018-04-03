package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.DateUtil;
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
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.entity.BusiSignRule;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.BusiSignRecordService;
import com.xiaoyu.lingdian.service.BusiSignRuleService;
import com.xiaoyu.lingdian.vo.BusiSignRecordVO;
import com.xiaoyu.lingdian.vo.BusiSignRuleVO;

@Controller
@RequestMapping(value="/busiSign")
@Api(value="busiSign", description="签到相关操作")
public class BusiSignController extends BaseController {

	/**
	* 用户当前签到记录表
	*/
	@Autowired
	private BusiSignRecordService busiSignRecordService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 签到设置规则表
	*/
	@Autowired
	private BusiSignRuleService busiSignRuleService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 用户签到
	*
	* @param bssrdUser 签到用户
	* @return
	*/
	@ApiOperation(value="用户签到", httpMethod = "POST", notes = "用户签到")
	@RequestMapping(value="/sign", method=RequestMethod.POST)
	public void sign(
			@ApiParam(value="签到用户标识", required = true) @RequestParam(value="bssrdUser", required = true) String bssrdUser,
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin sign");
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bssrdUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiSignController]:end sign");
			return;
		}
		
		//获得金額
		int fee = 0;
		//根据用户查询签到记录
		BusiSignRecord signRecord = busiSignRecordService.getBusiSignRecordByUser(bssrdUser);
		if (null == signRecord) { //还未有签到记录，第一次签到
		    //连续签到天数
	        int count = 1;  
			BusiSignRule busiSignRule = busiSignRuleService.getBusiSignRuleAfterCount(count);
			if (null == busiSignRule) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "没有签到规则,请联系在线客服!"), response);
				logger.info("[BusiSignController]:end sign");
				return;
			}
			
			fee = busiSignRule.getBssreFee();
			
			//添加签到记录
			BusiSignRecord busiSignRecord = new BusiSignRecord();
			busiSignRecord.setBssrdUuid(RandomUtil.generateString(16));
			busiSignRecord.setBssrdUser(bssrdUser);
			busiSignRecord.setBssrdFee(fee);
			busiSignRecord.setBssrdLastDate(new Date());
			busiSignRecord.setBssrdSignDays(count);
			busiSignRecordService.insertBusiSignRecord(busiSignRecord);
			
			//更新用户金額
			coreUserService.updateCoreUserByFee(bssrdUser, fee);
			
			//添加到用户消息中心
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(bssrdUser);
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(1);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("连续"+count+"天签到获得"+fee+"元!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		} else { //有签到记录
			//判断当天是否已签到
			Date date = new Date();		
			String dateNow = DateUtil.formatDate("yyyy-MM-dd", date);
			String dateLastSign = DateUtil.formatDate("yyyy-MM-dd", signRecord.getBssrdLastDate());
			if (dateNow.equals(dateLastSign)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "您今日已签到!"), response);
				logger.info("[BusiSignController]:end sign");
				return;
			} 
			//获取连续签到天数
            int count = signRecord.getBssrdSignDays(); 
	        Date dateSign = DateUtil.dateFormatToDate("yyyy-MM-dd",dateLastSign);
	        Date dateToday = DateUtil.dateFormatToDate("yyyy-MM-dd",dateNow);
	        int allDays = DateUtil.daysDiff(dateSign, dateToday); 
			if (1 == allDays) { //连续签到
				count++;			
			}else{
			    count = 1;
			}
			BusiSignRule busiSignRule = busiSignRuleService.getBusiSignRuleAfterCount(count);
			if (null == busiSignRule) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "没有签到规则,请联系在线客服!"), response);
				logger.info("[BusiSignController]:end sign");
				return;
			}
				
			fee = busiSignRule.getBssreFee();
				
			//更新签到记录
			BusiSignRecord busiSignRecord = new BusiSignRecord();
			busiSignRecord.setBssrdUser(bssrdUser);
			busiSignRecord.setBssrdFee(fee);
			busiSignRecord.setBssrdLastDate(date);
			busiSignRecord.setBssrdSignDays(count);
			busiSignRecordService.updateBusiSignRecord(busiSignRecord);
				
			//更新用户金額
			coreUserService.updateCoreUserByFee(bssrdUser, fee);
			
			//添加到用户消息中心
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(bssrdUser);
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(1);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("连续"+count+"天签到获得"+fee+"元!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		}

		coreUser = coreUserService.getCoreUser(coreUser);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addFee", "+" + fee);
		map.put("userFee", coreUser.getCrusrFee());

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "签到成功!", map),response);
		logger.info("[BusiSignController]:end sign");
	}

	/**
	* 获取单个签到记录
	*
	* @param bssrdUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个签到记录", httpMethod = "GET", notes = "获取单个签到记录", response = BusiSignRecordVO.class)
	@RequestMapping(value="/record/views", method=RequestMethod.GET)
	public void viewsBusiSignRecord(
			@ApiParam(value="签到记录标识", required = true) @RequestParam(value="bssrdUuid", required = true) String bssrdUuid,
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin viewsBusiSignRecord");		BusiSignRecord busiSignRecord = new BusiSignRecord();
		busiSignRecord.setBssrdUuid(bssrdUuid);
		busiSignRecord = busiSignRecordService.getBusiSignRecord(busiSignRecord);
		if(null == busiSignRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "单个签到记录不存在!"), response);
			logger.info("[BusiSignController]:end viewsBusiSignRecord");
			return;
		}

		BusiSignRecordVO busiSignRecordVO = new BusiSignRecordVO();
		busiSignRecordVO.convertPOToVO(busiSignRecord);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiSignRecord.getBssrdUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiSignController]:end viewsBusiSignRecord");
			return;
		}
		busiSignRecordVO.setBssrdUserName(coreUser.getCrusrName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个签到记录成功", busiSignRecordVO), response);
		logger.info("[BusiSignController]:end viewsBusiSignRecord");
	}

	/**
	* 后台获取签到记录<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台获取签到分页记录", httpMethod = "GET", notes = "后台获取签到分页记录", response = BusiSignRecordVO.class)
	@RequestMapping(value="/record/find/by/cnd", method=RequestMethod.GET)
	public void findBusiSignRecordPage(
			@ApiParam(value="签到用户昵称", required = true) @RequestParam(value="userName", required = true) String userName, 
			@ApiParam(value="第几页", required = true) @RequestParam(value="pageNum", required = true) Integer pageNum, 
			@ApiParam(value="每页数量", required = true) @RequestParam(value="pageSize", required = true) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin findBusiSignRecordPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiSignRecord> page = busiSignRecordService.findBusiSignRecordPage(userName, pageNum, pageSize);
		Page<BusiSignRecordVO> pageVO = new Page<BusiSignRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiSignRecord busiSignRecord : page.getResult()) {
			hashUserUuids.add(busiSignRecord.getBssrdUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiSignRecordVO> vos = new ArrayList<BusiSignRecordVO>();
		BusiSignRecordVO vo;
		for (BusiSignRecord busiSignRecord : page.getResult()) {
			vo = new BusiSignRecordVO();			vo.convertPOToVO(busiSignRecord);
			vo.setBssrdUserName(userMap.get(busiSignRecord.getBssrdUser())==null?null:userMap.get(busiSignRecord.getBssrdUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiSignController]:end findBusiSignRecordPage");
	}

	/**
	* 添加签到规则
	*
	* @param bssreCount 连续天数
	* @param bssreFee 可领取金額
	* @param bssreOrd 顺序
	* @return
	*/
	@ApiOperation(value="添加签到规则", httpMethod = "POST", notes = "添加签到规则")
	@RequestMapping(value="/rule/add/busiSignRule", method=RequestMethod.POST)
	public void addBusiSignRule(
			@ApiParam(value="连续天数", required = true) @RequestParam(value="bssreCount", required = true) Integer bssreCount,
			@ApiParam(value="可领取金额", required = true) @RequestParam(value="bssreFee", required = true) Integer bssreFee,
			@ApiParam(value="顺序", required = true) @RequestParam(value="bssreOrd", required = true) Integer bssreOrd,
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin addBusiSignRule");
		BusiSignRule busiSignRule = new BusiSignRule();
		String uuid = RandomUtil.generateString(16);
		busiSignRule.setBssreUuid(uuid);
		busiSignRule.setBssreCount(bssreCount);
		busiSignRule.setBssreFee(bssreFee);
		busiSignRule.setBssreOrd(bssreOrd);

		busiSignRuleService.insertBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiSignController]:end addBusiSignRule");
	}

	/**
	* 修改签到规则
	*
	* @param bssreUuid 标识UUID
	* @param bssreCount 连续天数
	* @param bssreFee 可领取金額
	* @param bssreOrd 顺序
	* @return
	*/
	@ApiOperation(value="修改签到规则", httpMethod = "POST", notes = "修改签到规则")
	@RequestMapping(value="/rule/update/busiSignRule", method=RequestMethod.POST)
	public void updateBusiSignRule(
			@ApiParam(value="签到规格标识UUID", required = true) @RequestParam(value="bssreUuid", required = true) String bssreUuid, 
			@ApiParam(value="连续天数", required = true) @RequestParam(value="bssreCount", required = true) Integer bssreCount, 
			@ApiParam(value="可领取金额", required = true) @RequestParam(value="bssreFee", required = true) Integer bssreFee, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bssreOrd", required = true) Integer bssreOrd, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin updateBusiSignRule");
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);
		busiSignRule.setBssreCount(bssreCount);
		busiSignRule.setBssreFee(bssreFee);
		busiSignRule.setBssreOrd(bssreOrd);
		busiSignRuleService.updateBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiSignController]:end updateBusiSignRule");
	}

	/**
	* 删除签到规则
	*
	* @param bssreUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除签到规则", httpMethod = "POST", notes = "删除签到规则")
	@RequestMapping(value="/rule/delete/one", method=RequestMethod.POST)
	public void deleteBusiSignRule(
			@ApiParam(value="签到规格标识UUID", required = true) @RequestParam(value="bssreUuid", required = true) String bssreUuid, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin deleteBusiSignRule");
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);

		busiSignRuleService.deleteBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiSignController]:end deleteBusiSignRule");
	}

	/**
	* 批量删除签到规则
	*
	* @param bssreUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除签到规则", httpMethod = "POST", notes = "批量删除签到规则")
	@RequestMapping(value="/rule/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiSignRule(
			@ApiParam(value="签到规格标识UUID集合字符串(|隔开)", required = true) @RequestParam(value="bssreUuids", required = true) String bssreUuids, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin deleteBatchBusiSignRule");
		String[] uuids=bssreUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiSignController]:end deleteBatchBusiSignRule");
			return;
		}
		busiSignRuleService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiSignController]:end deleteBatchBusiSignRule");
	}

	/**
	* 获取单个签到规则
	*
	* @param bssreUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个签到规则", httpMethod = "GET", notes = "获取单个签到规则", response = BusiSignRuleVO.class)
	@RequestMapping(value="/rule/views", method=RequestMethod.GET)
	public void viewsBusiSignRule(
			@ApiParam(value="签到规格标识UUID", required = true) @RequestParam(value="bssreUuid", required = true) String bssreUuid, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin viewsBusiSignRule");
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);

		busiSignRule = busiSignRuleService.getBusiSignRule(busiSignRule);

		BusiSignRuleVO busiSignRuleVO = new BusiSignRuleVO();
		busiSignRuleVO.convertPOToVO(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个签到设置信息成功", busiSignRuleVO), response);
		logger.info("[BusiSignController]:end viewsBusiSignRule");
	}

	/**
	* 获取所有的签到规则<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取所有的签到规则", httpMethod = "GET", notes = "获取所有的签到规则", response = BusiSignRuleVO.class)
	@RequestMapping(value="/rule/find/all", method=RequestMethod.GET)
	public void findBusiSignRuleList(
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin findBusiSignRuleList");
		List<BusiSignRule> lists = busiSignRuleService.findBusiSignRuleList();
		List<BusiSignRuleVO> vos = new ArrayList<BusiSignRuleVO>();
		BusiSignRuleVO vo;
		for (BusiSignRule busiSignRule : lists) {
			vo = new BusiSignRuleVO();
			vo.convertPOToVO(busiSignRule);
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiSignController]:end findBusiSignRuleList");
	}

	/**
	* 获取签到规则分页列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取签到规则分页列表", httpMethod = "GET", notes = "获取签到规则分页列表", response = BusiSignRuleVO.class)
	@RequestMapping(value="/rule/find/by/cnd", method=RequestMethod.GET)
	public void findBusiSignRulePage(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiSignController]:begin findBusiSignRulePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiSignRule> page = busiSignRuleService.findBusiSignRulePage(pageNum, pageSize);
		Page<BusiSignRuleVO> pageVO = new Page<BusiSignRuleVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiSignRuleVO> vos = new ArrayList<BusiSignRuleVO>();
		BusiSignRuleVO vo;
		for (BusiSignRule busiSignRule : page.getResult()) {
			vo = new BusiSignRuleVO();
			vo.convertPOToVO(busiSignRule);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiSignController]:end findBusiSignRulePage");
	}

}