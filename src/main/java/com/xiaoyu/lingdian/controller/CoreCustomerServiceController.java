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
import com.xiaoyu.lingdian.entity.CoreCustomerService;
import com.xiaoyu.lingdian.service.CoreCustomerServiceService;
import com.xiaoyu.lingdian.vo.CoreCustomerServiceVO;

@Controller
@RequestMapping(value="/coreCustomerService")
@Api(value="coreCustomerService", description="客服信息相关操作")
public class CoreCustomerServiceController extends BaseController {

	/**
	* 客服信息表
	*/
	@Autowired
	private CoreCustomerServiceService coreCustomerServiceService;
	
	/**
	* 添加
	*
	* @param crcseTel 电话号码(|隔开)
	* @param crcseQq QQ(|隔开)
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/coreCustomerService", method=RequestMethod.POST)
	public void addCoreCustomerService(
			@ApiParam(value="电话号码(|隔开)", required = true) @RequestParam(value="crcseTel", required = true) String crcseTel, 
			@ApiParam(value="QQ(|隔开)", required = true) @RequestParam(value="crcseQq", required = true) String crcseQq, 
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin addCoreCustomerService");		CoreCustomerService coreCustomerService = new CoreCustomerService();
		String uuid = RandomUtil.generateString(16);
		coreCustomerService.setCrcseUuid(uuid);
		coreCustomerService.setCrcseTel(crcseTel);
		coreCustomerService.setCrcseQq(crcseQq);
		coreCustomerService.setCrcseCdate(new Date());
		coreCustomerService.setCrcseUdate(new Date());
		coreCustomerServiceService.insertCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreCustomerServiceController]:end addCoreCustomerService");
	}

	/**
	* 修改
	*
	* @param crcseUuid 标识UUID
	* @param crcseTel 电话号码(|隔开)
	* @param crcseQq QQ(|隔开)
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/coreCustomerService", method=RequestMethod.POST)
	public void updateCoreCustomerService(
			@ApiParam(value="客服信息标识UUID", required = true) @RequestParam(value="crcseUuid", required = true) String crcseUuid, 
			@ApiParam(value="电话号码(|隔开)", required = true) @RequestParam(value="crcseTel", required = true) String crcseTel, 
			@ApiParam(value="QQ(|隔开)", required = true) @RequestParam(value="crcseQq", required = true) String crcseQq, 
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin updateCoreCustomerService");		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);
		coreCustomerService.setCrcseTel(crcseTel);
		coreCustomerService.setCrcseQq(crcseQq);
		coreCustomerService.setCrcseUdate(new Date());
		coreCustomerServiceService.updateCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreCustomerServiceController]:end updateCoreCustomerService");
	}

	/**
	* 删除
	*
	* @param crcseUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreCustomerService(
			@ApiParam(value="客服信息标识UUID", required = true) @RequestParam(value="crcseUuid", required = true) String crcseUuid, 
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin deleteCoreCustomerService");		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);
		coreCustomerServiceService.deleteCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreCustomerServiceController]:end deleteCoreCustomerService");
	}

	/**
	* 批量删除
	*
	* @param crcseUuids UUID集合
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreCustomerService(
			@ApiParam(value="客服信息标识UUID集合(|拼接)", required = true) @RequestParam(value="crcseUuids", required = true) String crcseUuids, 
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin deleteBatchCoreCustomerService");		String[] uuids=crcseUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreCustomerServiceController]:end deleteBatchCoreCustomerService");
			return;
		}
		coreCustomerServiceService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreCustomerServiceController]:end deleteBatchCoreCustomerService");
	}

	/**
	* 获取单个客服信息
	*
	* @param crcseUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个客服信息", httpMethod = "GET", notes = "获取单个客服信息", response = CoreCustomerServiceVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsCoreCustomerService(
			@ApiParam(value="客服信息标识UUID", required = true) @RequestParam(value="crcseUuid", required = true) String crcseUuid, 
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin viewsCoreCustomerService");		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);
		coreCustomerService = coreCustomerServiceService.getCoreCustomerService(coreCustomerService);
		CoreCustomerServiceVO coreCustomerServiceVO = new CoreCustomerServiceVO();
		coreCustomerServiceVO.convertPOToVO(coreCustomerService);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreCustomerServiceVO), response);
		logger.info("[CoreCustomerServiceController]:end viewsCoreCustomerService");
	}

	/**
	* 获取客服信息列表<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取客服信息列表", httpMethod = "GET", notes = "获取客服信息列表", response = CoreCustomerServiceVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findCoreCustomerServiceList(
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin findCoreCustomerServiceList");		List<CoreCustomerService> lists = coreCustomerServiceService.findCoreCustomerServiceList();
		List<CoreCustomerServiceVO> vos = new ArrayList<CoreCustomerServiceVO>();
		CoreCustomerServiceVO vo;
		for (CoreCustomerService coreCustomerService : lists) {
			vo = new CoreCustomerServiceVO();			vo.convertPOToVO(coreCustomerService);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreCustomerServiceController]:end findCoreCustomerServiceList");
	}

	/**
	* 获取客服信息分页列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取客服信息分页列表", httpMethod = "GET", notes = "获取客服信息分页列表", response = CoreCustomerServiceVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findCoreCustomerServicePage(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize,
			HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin findCoreCustomerServicePage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreCustomerService> page = coreCustomerServiceService.findCoreCustomerServicePage(pageNum, pageSize);
		Page<CoreCustomerServiceVO> pageVO = new Page<CoreCustomerServiceVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreCustomerServiceVO> vos = new ArrayList<CoreCustomerServiceVO>();
		CoreCustomerServiceVO vo;
		for (CoreCustomerService coreCustomerService : page.getResult()) {
			vo = new CoreCustomerServiceVO();			vo.convertPOToVO(coreCustomerService);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreCustomerServiceController]:end findCoreCustomerServicePage");
	}

}