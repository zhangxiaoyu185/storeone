package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiServerType;
import com.xiaoyu.lingdian.service.BusiServerTypeService;
import com.xiaoyu.lingdian.vo.BusiServerTypeVO;

@Controller
@RequestMapping(value="/busiServerType")
@Api(value="busiServerType", description="服务类别相关操作")
public class BusiServerTypeController extends BaseController {

	/**
	* 服务类别表
	*/
	@Autowired
	private BusiServerTypeService busiServerTypeService;

	/**
	* 添加(默认启用)
	*
	* @param bssteName 类别名称
	* @param bssteIcon 图标
	* @param bssteIslist 是否有列表:1有0无
	* @param bssteDetails 详情URL(不是列表用)
	* @return
	*/
	@ApiOperation(value="添加(默认启用)", httpMethod = "POST", notes = "添加(默认启用)")
	@RequestMapping(value="/add/busiServerType", method=RequestMethod.POST)
	public void addBusiServerType(
			@ApiParam(value="类别名称", required = true) @RequestParam(value="bssteName", required = true) String bssteName, 
			@ApiParam(value="图标", required = true) @RequestParam(value="bssteIcon", required = true) String bssteIcon, 
			@ApiParam(value="是否有列表:1有0无", required = true) @RequestParam(value="bssteIslist", required = true) Integer bssteIslist, 
			@ApiParam(value="详情URL(不是列表用)", required = false) @RequestParam(value="bssteDetails", required = false) String bssteDetails, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin addBusiServerType");
		if (bssteIslist == 0) {
			if (StringUtil.isEmpty(bssteDetails)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无列表时详情URL必填!"), response);
				logger.info("[BusiServerTypeController]:end addBusiServerType");
				return;
			}
		}
		BusiServerType type = busiServerTypeService.getBusiServerTypeByName(bssteName);		if (null != type) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该类别已存在!"), response);
			logger.info("[BusiServerTypeController]:end addBusiServerType");
			return;
		}

		BusiServerType busiServerType = new BusiServerType();
		String uuid = RandomUtil.generateString(16);
		busiServerType.setBssteUuid(uuid);
		busiServerType.setBssteName(bssteName);
		busiServerType.setBssteIcon(bssteIcon);
		busiServerType.setBssteIslist(bssteIslist);
		busiServerType.setBssteDetails(bssteDetails);
		busiServerType.setBssteCdate(new Date());
		busiServerType.setBssteUdate(new Date());
		busiServerType.setBssteStatus(1);
		busiServerTypeService.insertBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiServerTypeController]:end addBusiServerType");
	}

	/**
	* 修改
	*
	* @param bssteUuid 标识UUID
	* @param bssteName 类别名称
	* @param bssteIcon 图标
	* @param bssteIslist 是否有列表:1有0无
	* @param bssteDetails 详情URL(不是列表用)
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiServerType", method=RequestMethod.POST)
	public void updateBusiServerType(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssteUuid", required = true) String bssteUuid, 
			@ApiParam(value="类别名称", required = true) @RequestParam(value="bssteName", required = true) String bssteName, 
			@ApiParam(value="图标", required = true) @RequestParam(value="bssteIcon", required = true) String bssteIcon, 
			@ApiParam(value="是否有列表:1有0无", required = true) @RequestParam(value="bssteIslist", required = true) Integer bssteIslist, 
			@ApiParam(value="详情URL(不是列表用)", required = false) @RequestParam(value="bssteDetails", required = false) String bssteDetails, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin updateBusiServerType");
		if (bssteIslist == 0) {
			if (StringUtil.isEmpty(bssteDetails)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无列表时详情URL必填!"), response);
				logger.info("[BusiServerTypeController]:end updateBusiServerType");
				return;
			}
		}
		BusiServerType type = busiServerTypeService.getBusiServerTypeByName(bssteName);
		if (null != type && !bssteUuid.equals(type.getBssteUuid())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该类别已存在!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}
				BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteName(bssteName);
		busiServerType.setBssteIcon(bssteIcon);
		busiServerType.setBssteIslist(bssteIslist);
		busiServerType.setBssteDetails(bssteDetails);
		busiServerType.setBssteUdate(new Date());

		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiServerTypeController]:end updateBusiServerType");
	}

	/**
	* 启用
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="启用", httpMethod = "POST", notes = "启用")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssteUuid", required = true) String bssteUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin enable");
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteStatus(1);
		busiServerType.setBssteUdate(new Date());
		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiServerTypeController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="禁用", httpMethod = "POST", notes = "禁用")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssteUuid", required = true) String bssteUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin disable");	
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteStatus(0);
		busiServerType.setBssteUdate(new Date());
		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiServerTypeController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiServerType(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssteUuid", required = true) String bssteUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin deleteBusiServerType");		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerTypeService.deleteBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiServerTypeController]:end deleteBusiServerType");
	}

	/**
	* 批量删除
	*
	* @param bssteUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiServerType(
			@ApiParam(value="服务类别标识集合(|拼接)", required = true) @RequestParam(value="bssteUuids", required = true) String bssteUuids, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin deleteBatchBusiServerType");		String[] uuids=bssteUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiServerTypeController]:end deleteBatchBusiServerType");
			return;
		}
		busiServerTypeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiServerTypeController]:end deleteBatchBusiServerType");
	}

	/**
	* 获取单个服务类别
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个服务类别", httpMethod = "GET", notes = "获取单个服务类别", response = BusiServerTypeVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiServerType(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssteUuid", required = true) String bssteUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin viewsBusiServerType");		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);

		BusiServerTypeVO busiServerTypeVO = new BusiServerTypeVO();
		busiServerTypeVO.convertPOToVO(busiServerType);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiServerTypeVO), response);
		logger.info("[BusiServerTypeController]:end viewsBusiServerType");
	}

	/**
	* 获取所有启用的服务类别list<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取所有启用的服务类别list", httpMethod = "GET", notes = "获取所有启用的服务类别list", response = BusiServerTypeVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findBusiServerTypeForListsAll(
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypeForListsAll");
		List<BusiServerType> lists = busiServerTypeService.findBusiServerTypeForListsAll();
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : lists) {
			vo = new BusiServerTypeVO();
			vo.convertPOToVO(busiServerType);
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypeForListsAll");
	}
	
	/**
	* 获取服务类别分页列表<Page>
	* 
	* @param bssteName 服务类别名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取服务类别分页列表", httpMethod = "GET", notes = "获取服务类别分页列表", response = BusiServerTypeVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiServerTypePage(
			@ApiParam(value="服务类别名称", required = false) @RequestParam(value="bssteName", required = false) String bssteName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypePage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerType> page = busiServerTypeService.findBusiServerTypePage(bssteName, pageNum, pageSize);
		Page<BusiServerTypeVO> pageVO = new Page<BusiServerTypeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : page.getResult()) {
			vo = new BusiServerTypeVO();
			vo.convertPOToVO(busiServerType);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypePage");
	}

}