package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiRechargeRecord;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.BusiRechargeRecordService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.BusiRechargeRecordVO;

@Controller
@RequestMapping(value="/busiRechargeRecord")
@Api(value="busiRechargeRecord", description="用户充值记录相关操作")
public class BusiRechargeRecordController extends BaseController {

	/**
	* 用户充值记录表
	*/
	@Autowired
	private BusiRechargeRecordService busiRechargeRecordService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 添加
	*
	* @param bsrrdAmount 充值金额
	* @param bsrrdUser 充值人
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/busiRechargeRecord", method=RequestMethod.POST)
	public void addBusiRechargeRecord(
			@ApiParam(value="充值金额", required = true) @RequestParam(value="bsrrdAmount", required = true) String bsrrdAmount, 
			@ApiParam(value="充值人", required = true) @RequestParam(value="bsrrdUser", required = true) String bsrrdUser, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin addBusiRechargeRecord");
		try {
			coreUserService.updateCoreUserByFee(bsrrdUser, Double.parseDouble(bsrrdAmount));
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "充值失败,充值金额不正确!"),response);
			logger.info("[BusiRechargeRecordController]:end addBusiRechargeRecord");
			return;
		}
				BusiRechargeRecord busiRechargeRecord = new BusiRechargeRecord();
		String uuid = RandomUtil.generateString(16);
		busiRechargeRecord.setBsrrdUuid(uuid);
		busiRechargeRecord.setBsrrdAmount(bsrrdAmount);
		busiRechargeRecord.setBsrrdUser(bsrrdUser);
		busiRechargeRecord.setBsrrdCdate(new Date());
		busiRechargeRecord.setBsrrdUdate(new Date());
		busiRechargeRecordService.insertBusiRechargeRecord(busiRechargeRecord);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "充值成功!"),response);
		logger.info("[BusiRechargeRecordController]:end addBusiRechargeRecord");
	}

	/**
	* 删除
	*
	* @param bsrrdUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiRechargeRecord(
			@ApiParam(value="充值标识UUID", required = true) @RequestParam(value="bsrrdUuid", required = true) String bsrrdUuid, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin deleteBusiRechargeRecord");		BusiRechargeRecord busiRechargeRecord = new BusiRechargeRecord();
		busiRechargeRecord.setBsrrdUuid(bsrrdUuid);
		busiRechargeRecordService.deleteBusiRechargeRecord(busiRechargeRecord);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiRechargeRecordController]:end deleteBusiRechargeRecord");
	}

	/**
	* 批量删除
	*
	* @param bsrrdUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiRechargeRecord(
			@ApiParam(value="充值标识集合(|拼接)", required = true) @RequestParam(value="bsrrdUuids", required = true) String bsrrdUuids, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin deleteBatchBusiRechargeRecord");		String[] uuids=bsrrdUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiRechargeRecordService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiRechargeRecordController]:end deleteBatchBusiRechargeRecord");
	}

	/**
	* 获取单个充值记录
	*
	* @param bsrrdUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个充值记录", httpMethod = "GET", notes = "获取单个充值记录", response = BusiRechargeRecordVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiRechargeRecord(
			@ApiParam(value="充值标识UUID", required = true) @RequestParam(value="bsrrdUuid", required = true) String bsrrdUuid, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin viewsBusiRechargeRecord");		BusiRechargeRecord busiRechargeRecord = new BusiRechargeRecord();
		busiRechargeRecord.setBsrrdUuid(bsrrdUuid);
		busiRechargeRecord = busiRechargeRecordService.getBusiRechargeRecord(busiRechargeRecord);
		BusiRechargeRecordVO busiRechargeRecordVO = new BusiRechargeRecordVO();
		busiRechargeRecordVO.convertPOToVO(busiRechargeRecord);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiRechargeRecordVO), response);
		logger.info("[BusiRechargeRecordController]:end viewsBusiRechargeRecord");
	}

	/**
	* 前台-我的充值记录分页列表<Page>
	* 
	* @param bsrrdUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="前台-我的充值记录分页列表", httpMethod = "GET", notes = "前台-我的充值记录分页列表", response = BusiRechargeRecordVO.class)
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.GET)
	public void findBusiRechargeRecordPageMy(
			@ApiParam(value="所属用户标识", required = true) @RequestParam(value="bsrrdUser", required = true) String bsrrdUser, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin findBusiRechargeRecordPageMy");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiRechargeRecord> page = busiRechargeRecordService.findBusiRechargeRecordPage(bsrrdUser, pageNum, pageSize);
		Page<BusiRechargeRecordVO> pageVO = new Page<BusiRechargeRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiRechargeRecordVO> vos = new ArrayList<BusiRechargeRecordVO>();
		BusiRechargeRecordVO vo;
		for (BusiRechargeRecord busiRechargeRecord : page.getResult()) {
			vo = new BusiRechargeRecordVO();
			vo.convertPOToVO(busiRechargeRecord);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "我的充值记录获取成功!", pageVO),response);
		logger.info("[BusiRechargeRecordController]:end findBusiRechargeRecordPageMy");
	}

	/**
	* 后台-获取充值记录分页列表<Page>
	* 
	* @param userName 用户名
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台-获取充值记录分页列表", httpMethod = "GET", notes = "后台-获取充值记录分页列表", response = BusiRechargeRecordVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiRechargeRecordPage(
			@ApiParam(value="用户名", required = false) @RequestParam(value="userName", required = false) String userName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiRechargeRecordController]:begin findBusiRechargeRecordPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiRechargeRecord> page = busiRechargeRecordService.findBusiRechargeRecordForPages(userName, pageNum, pageSize);
		Page<BusiRechargeRecordVO> pageVO = new Page<BusiRechargeRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiRechargeRecord busiRechargeRecord : page.getResult()) {
			hashUserUuids.add(busiRechargeRecord.getBsrrdUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);		
		List<BusiRechargeRecordVO> vos = new ArrayList<BusiRechargeRecordVO>();
		BusiRechargeRecordVO vo;
		for (BusiRechargeRecord busiRechargeRecord : page.getResult()) {
			vo = new BusiRechargeRecordVO();			vo.convertPOToVO(busiRechargeRecord);
			vo.setBsrrdUserName(userMap.get(busiRechargeRecord.getBsrrdUser())==null?null:userMap.get(busiRechargeRecord.getBsrrdUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiRechargeRecordController]:end findBusiRechargeRecordPage");
	}

}