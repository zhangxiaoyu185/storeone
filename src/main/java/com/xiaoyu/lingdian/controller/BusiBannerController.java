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
import com.xiaoyu.lingdian.entity.BusiBanner;
import com.xiaoyu.lingdian.service.BusiBannerService;
import com.xiaoyu.lingdian.vo.BusiBannerVO;

@Controller
@RequestMapping(value="/busiBanner")
@Api(value="busiBanner", description="导航商品配置相关操作")
public class BusiBannerController extends BaseController {

	/**
	* 导航商品配置表
	*/
	@Autowired
	private BusiBannerService busiBannerService;
	
	/**
	* 添加(默认为启用)
	*
	* @param bsbarLink 链接
	* @param bsbarPic BANNER图
	* @param bsbarOrd 顺序
	* @param bsbarDesc 描述
	* @return
	*/
	@ApiOperation(value="添加(默认为启用)", httpMethod = "POST", notes = "添加(默认为启用)")
	@RequestMapping(value="/add/busiBanner", method=RequestMethod.POST)
	public void addBusiBanner(
			@ApiParam(value="链接", required = true) @RequestParam(value="bsbarLink", required = true) String bsbarLink, 
			@ApiParam(value="BANNER图", required = false) @RequestParam(value="bsbarPic", required = false) String bsbarPic, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bsbarOrd", required = true) Integer bsbarOrd, 
			@ApiParam(value="描述", required = false) @RequestParam(value="bsbarDesc", required = false) String bsbarDesc, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin addBusiBanner");		BusiBanner busiBanner = new BusiBanner();
		String uuid = RandomUtil.generateString(16);
		busiBanner.setBsbarUuid(uuid);
		busiBanner.setBsbarLink(bsbarLink);
		busiBanner.setBsbarPic(bsbarPic);
		busiBanner.setBsbarOrd(bsbarOrd);
		busiBanner.setBsbarStatus(1);
		busiBanner.setBsbarDesc(bsbarDesc);
		busiBanner.setBsbarCdate(new Date());
		busiBanner.setBsbarUdate(new Date());
		busiBannerService.insertBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiBannerController]:end addBusiBanner");
	}

	/**
	* 修改
	*
	* @param bsbarUuid 标识UUID
	* @param bsbarLink 链接
	* @param bsbarPic BANNER图
	* @param bsbarOrd 顺序
	* @param bsbarDesc 描述
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiBanner", method=RequestMethod.POST)
	public void updateBusiBanner(
			@ApiParam(value="导航商品配置标识UUID", required = true) @RequestParam(value="bsbarUuid", required = true) String bsbarUuid, 
			@ApiParam(value="链接", required = true) @RequestParam(value="bsbarLink", required = true) String bsbarLink, 
			@ApiParam(value="BANNER图", required = false) @RequestParam(value="bsbarPic", required = false) String bsbarPic, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bsbarOrd", required = true) Integer bsbarOrd, 
			@ApiParam(value="描述", required = false) @RequestParam(value="bsbarDesc", required = false) String bsbarDesc, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin updateBusiBanner");		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarLink(bsbarLink);
		busiBanner.setBsbarPic(bsbarPic);
		busiBanner.setBsbarOrd(bsbarOrd);
		busiBanner.setBsbarDesc(bsbarDesc);
		busiBanner.setBsbarUdate(new Date());
		busiBannerService.updateBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiBannerController]:end updateBusiBanner");
	}

	/**
	* 启用
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="启用", httpMethod = "POST", notes = "启用")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="导航商品配置标识UUID", required = true) @RequestParam(value="bsbarUuid", required = true) String bsbarUuid, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin enable");	
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarStatus(1);
		busiBanner.setBsbarUdate(new Date());
		busiBannerService.updateBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiBannerController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="禁用", httpMethod = "POST", notes = "禁用")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="导航商品配置标识UUID", required = true) @RequestParam(value="bsbarUuid", required = true) String bsbarUuid, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin disable");
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarStatus(0);
		busiBanner.setBsbarUdate(new Date());
		busiBannerService.updateBusiBanner(busiBanner);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiBannerController]:end disable");
	}

	/**
	* 删除
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiBanner(
			@ApiParam(value="导航商品配置标识UUID", required = true) @RequestParam(value="bsbarUuid", required = true) String bsbarUuid, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin deleteBusiBanner");		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBannerService.deleteBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiBannerController]:end deleteBusiBanner");
	}

	/**
	* 批量删除
	*
	* @param bsbarUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiBanner(
			@ApiParam(value="导航商品配置标识集合(|拼接)", required = true) @RequestParam(value="bsbarUuids", required = true) String bsbarUuids, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin deleteBatchBusiBanner");		String[] uuids=bsbarUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiBannerController]:end deleteBatchBusiBanner");
			return;
		}
		busiBannerService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiBannerController]:end deleteBatchBusiBanner");
	}

	/**
	* 获取单个banner
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个banner", httpMethod = "GET", notes = "获取单个banner", response = BusiBannerVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiBanner(
			@ApiParam(value="导航商品配置标识UUID", required = true) @RequestParam(value="bsbarUuid", required = true) String bsbarUuid, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin viewsBusiBanner");		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner = busiBannerService.getBusiBanner(busiBanner);
		BusiBannerVO busiBannerVO = new BusiBannerVO();
		busiBannerVO.convertPOToVO(busiBanner);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiBannerVO), response);
		logger.info("[BusiBannerController]:end viewsBusiBanner");
	}

	/**
	* 获取启用的banner列表
	* 
	* @return
	*/
	@ApiOperation(value="获取启用的banner列表", httpMethod = "GET", notes = "获取启用的banner列表", response = BusiBannerVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findBusiBannerForLists(
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin findBusiBannerForLists");		List<BusiBanner> lists = busiBannerService.findBusiBannerForLists();
		List<BusiBannerVO> vos = new ArrayList<BusiBannerVO>();
		BusiBannerVO vo;
		for (BusiBanner busiBanner : lists) {
			vo = new BusiBannerVO();			vo.convertPOToVO(busiBanner);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiBannerController]:end findBusiBannerForLists");
	}
	
	/**
	* 获取分页列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取分页列表", httpMethod = "GET", notes = "获取分页列表", response = BusiBannerVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiBannerPage(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin findBusiBannerPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiBanner> page = busiBannerService.findBusiBannerPage(pageNum, pageSize);
		Page<BusiBannerVO> pageVO = new Page<BusiBannerVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiBannerVO> vos = new ArrayList<BusiBannerVO>();
		BusiBannerVO vo;
		for (BusiBanner busiBanner : page.getResult()) {
			vo = new BusiBannerVO();			vo.convertPOToVO(busiBanner);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiBannerController]:end findBusiBannerPage");
	}

}