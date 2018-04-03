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
import com.xiaoyu.lingdian.entity.BusiHotSpot;
import com.xiaoyu.lingdian.service.BusiHotSpotService;
import com.xiaoyu.lingdian.vo.BusiHotSpotVO;

@Controller
@RequestMapping(value="/busiHotSpot")
@Api(value="busiBanner", description="热点配置相关操作")
public class BusiHotSpotController extends BaseController {

	/**
	* 热点配置表
	*/
	@Autowired
	private BusiHotSpotService busiHotSpotService;
	
	/**
	* 添加
	*
	* @param bshstName 热点名称
	* @param bshstIcon 热点图片
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/busiHotSpot", method=RequestMethod.POST)
	public void addBusiHotSpot(
			@ApiParam(value="热点名称", required = true) @RequestParam(value="bshstName", required = true) String bshstName, 
			@ApiParam(value="热点图片", required = true) @RequestParam(value="bshstIcon", required = true) String bshstIcon, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin addBusiHotSpot");			BusiHotSpot busiHotSpot = new BusiHotSpot();
		String uuid = RandomUtil.generateString(16);
		busiHotSpot.setBshstUuid(uuid);
		busiHotSpot.setBshstName(bshstName);
		busiHotSpot.setBshstIcon(bshstIcon);
		busiHotSpot.setBshstCdate(new Date());
		busiHotSpot.setBshstUdate(new Date());
		busiHotSpotService.insertBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiHotSpotController]:end addBusiHotSpot");
	}

	/**
	* 修改
	*
	* @param bshstUuid 标识UUID
	* @param bshstName 热点名称
	* @param bshstIcon 热点图片
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiHotSpot", method=RequestMethod.POST)
	public void updateBusiHotSpot(
			@ApiParam(value="热点配置标识UUID", required = true) @RequestParam(value="bshstUuid", required = true) String bshstUuid, 
			@ApiParam(value="热点名称", required = true) @RequestParam(value="bshstName", required = true) String bshstName, 
			@ApiParam(value="热点图片", required = true) @RequestParam(value="bshstIcon", required = true) String bshstIcon, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin updateBusiHotSpot");		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);
		busiHotSpot.setBshstName(bshstName);
		busiHotSpot.setBshstIcon(bshstIcon);
		busiHotSpot.setBshstUdate(new Date());
		busiHotSpotService.updateBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiHotSpotController]:end updateBusiHotSpot");
	}

	/**
	* 删除
	*
	* @param bshstUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiHotSpot(
			@ApiParam(value="热点配置标识UUID", required = true) @RequestParam(value="bshstUuid", required = true) String bshstUuid, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin deleteBusiHotSpot");		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);
		busiHotSpotService.deleteBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiHotSpotController]:end deleteBusiHotSpot");
	}

	/**
	* 批量删除
	*
	* @param bshstUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiHotSpot(
			@ApiParam(value="热点配置标识集合(|拼接)", required = true) @RequestParam(value="bshstUuids", required = true) String bshstUuids, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin deleteBatchBusiHotSpot");		String[] uuids=bshstUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiHotSpotController]:end deleteBatchBusiHotSpot");
			return;
		}
		busiHotSpotService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiHotSpotController]:end deleteBatchBusiHotSpot");
	}

	/**
	* 获取单个
	*
	* @param bshstUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个热点配置", httpMethod = "GET", notes = "获取单个热点配置", response = BusiHotSpotVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiHotSpot(
			@ApiParam(value="热点配置标识UUID", required = true) @RequestParam(value="bshstUuid", required = true) String bshstUuid, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin viewsBusiHotSpot");		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);
		busiHotSpot = busiHotSpotService.getBusiHotSpot(busiHotSpot);
		BusiHotSpotVO busiHotSpotVO = new BusiHotSpotVO();
		busiHotSpotVO.convertPOToVO(busiHotSpot);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiHotSpotVO), response);
		logger.info("[BusiHotSpotController]:end viewsBusiHotSpot");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取列表", httpMethod = "GET", notes = "获取列表", response = BusiHotSpotVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findBusiHotSpotList(
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin findBusiHotSpotList");		List<BusiHotSpot> lists = busiHotSpotService.findBusiHotSpotList();
		List<BusiHotSpotVO> vos = new ArrayList<BusiHotSpotVO>();
		BusiHotSpotVO vo;
		for (BusiHotSpot busiHotSpot : lists) {
			vo = new BusiHotSpotVO();			vo.convertPOToVO(busiHotSpot);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiHotSpotController]:end findBusiHotSpotList");
	}

	/**
	* 获取分页列表<Page>
	* 
	* @param bshstName 热点名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取分页列表", httpMethod = "GET", notes = "获取分页列表", response = BusiHotSpotVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiHotSpotPage(
			@ApiParam(value="热点名称", required = false) @RequestParam(value="bshstName", required = false) String bshstName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin findBusiHotSpotPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiHotSpot> page = busiHotSpotService.findBusiHotSpotPage(bshstName, pageNum, pageSize);
		Page<BusiHotSpotVO> pageVO = new Page<BusiHotSpotVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiHotSpotVO> vos = new ArrayList<BusiHotSpotVO>();
		BusiHotSpotVO vo;
		for (BusiHotSpot busiHotSpot : page.getResult()) {
			vo = new BusiHotSpotVO();			vo.convertPOToVO(busiHotSpot);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiHotSpotController]:end findBusiHotSpotPage");
	}

}