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
import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import com.xiaoyu.lingdian.service.CoreMessageCenterService;
import com.xiaoyu.lingdian.vo.CoreMessageCenterVO;

@Controller
@RequestMapping(value="/coreMessageCenter")
@Api(value="coreMessageCenter", description="系统消息中心相关操作")
public class CoreMessageCenterController extends BaseController {

	/**
	* 系统消息中心表
	*/
	@Autowired
	private CoreMessageCenterService coreMessageCenterService;
	
	/**
	* 添加(用户添加，默认为未处理消息)
	*
	* @param crmecContent 消息内容
	* @param crmecType 消息类型
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/coreMessageCenter", method=RequestMethod.POST)
	public void addCoreMessageCenter(
			@ApiParam(value="消息内容", required = true) @RequestParam(value="crmecContent", required = true) String crmecContent, 
			@ApiParam(value="消息类型", required = true) @RequestParam(value="crmecType", required = true) Integer crmecType, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin addCoreMessageCenter");        		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		String uuid = RandomUtil.generateString(16);
		coreMessageCenter.setCrmecUuid(uuid);
		coreMessageCenter.setCrmecContent(crmecContent);
		coreMessageCenter.setCrmecCdate(new Date());
		coreMessageCenter.setCrmecUdate(new Date());
		coreMessageCenter.setCrmecStatus(2);
		coreMessageCenter.setCrmecType(crmecType);

		coreMessageCenterService.insertCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreMessageCenterController]:end addCoreMessageCenter");
	}

	/**
	* 处理
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="处理", httpMethod = "POST", notes = "处理")
	@RequestMapping(value="/update/coreMessageCenter", method=RequestMethod.POST)
	public void updateCoreMessageCenter(
			@ApiParam(value="消息标识UUID", required = true) @RequestParam(value="crmecUuid", required = true) String crmecUuid, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin updateCoreMessageCenter");				CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);
		coreMessageCenter.setCrmecStatus(1);
		coreMessageCenter.setCrmecUdate(new Date());

		coreMessageCenterService.updateCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "处理成功!"),response);
		logger.info("[CoreMessageCenterController]:end updateCoreMessageCenter");
	}

	/**
	* 删除
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreMessageCenter(
			@ApiParam(value="消息标识UUID", required = true) @RequestParam(value="crmecUuid", required = true) String crmecUuid, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin deleteCoreMessageCenter");		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);
		coreMessageCenter.setCrmecStatus(3);
		coreMessageCenter.setCrmecUdate(new Date());

		coreMessageCenterService.updateCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreMessageCenterController]:end deleteCoreMessageCenter");
	}

	/**
	* 批量删除
	*
	* @param crmecUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreMessageCenter(
			@ApiParam(value="消息标识UUID集合(|拼接)", required = true) @RequestParam(value="crmecUuids", required = true) String crmecUuids, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin deleteBatchCoreMessageCenter");
		String[] uuids = crmecUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreMessageCenterController]:end deleteBatchCoreMessageCenter");
			return;
		}
		
		coreMessageCenterService.updateBatchCoreMessageCenterByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreMessageCenterController]:end deleteBatchCoreMessageCenter");
	}

	/**
	* 获取单个
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个消息", httpMethod = "GET", notes = "获取单个消息", response = CoreMessageCenterVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsCoreMessageCenter(
			@ApiParam(value="消息标识UUID", required = true) @RequestParam(value="crmecUuid", required = true) String crmecUuid, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin viewsCoreMessageCenter");		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);

		coreMessageCenter = coreMessageCenterService.getCoreMessageCenter(coreMessageCenter);

		CoreMessageCenterVO coreMessageCenterVO = new CoreMessageCenterVO();
		coreMessageCenterVO.convertPOToVO(coreMessageCenter);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreMessageCenterVO), response);
		logger.info("[CoreMessageCenterController]:end viewsCoreMessageCenter");
	}

	/**
	* 获取所有未读消息个数
	* 
	* @return
	*/
	@ApiOperation(value="获取所有未读消息个数", httpMethod = "GET", notes = "获取所有未读消息个数")
	@RequestMapping(value="/find/count", method=RequestMethod.GET)
	public void findCoreMessageCenterCount(
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin findCoreMessageCenterCount");		int count = coreMessageCenterService.findCoreMessageCenterCount();
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取所有未读消息个数成功!", count),response);
		logger.info("[CoreMessageCenterController]:end findCoreMessageCenterCount");
	}

	/**
	* 获取消息分页列表<Page>
	* 
	* @param crmecContent 消息内容
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取消息分页列表", httpMethod = "GET", notes = "获取消息分页列表", response = CoreMessageCenterVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findCoreMessageCenterPage(
			@ApiParam(value="消息内容", required = false) @RequestParam(value="crmecContent", required = false) String crmecContent, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin findCoreMessageCenterPage");      		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreMessageCenter> page = coreMessageCenterService.findCoreMessageCenterPage(crmecContent, pageNum, pageSize);
		Page<CoreMessageCenterVO> pageVO = new Page<CoreMessageCenterVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreMessageCenterVO> vos = new ArrayList<CoreMessageCenterVO>();
		CoreMessageCenterVO vo;
		for (CoreMessageCenter coreMessageCenter : page.getResult()) {
			vo = new CoreMessageCenterVO();
			vo.convertPOToVO(coreMessageCenter);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreMessageCenterController]:end findCoreMessageCenterPage");
	}

}