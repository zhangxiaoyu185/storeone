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
import com.xiaoyu.lingdian.entity.BusiExpressConfig;
import com.xiaoyu.lingdian.service.BusiExpressConfigService;
import com.xiaoyu.lingdian.vo.BusiExpressConfigVO;

@Controller
@RequestMapping(value="/busiExpressConfig")
@Api(value="busiExpressConfig", description="快递鸟配置相关操作")
public class BusiExpressConfigController extends BaseController {

	/**
	* 快递鸟配置表
	*/
	@Autowired
	private BusiExpressConfigService busiExpressConfigService;
	
	/**
	* 添加
	*
	* @param bsecgKdnCode 商户标识
	* @param bsecgApiKey API_KEY
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/busiExpressConfig", method=RequestMethod.POST)
	public void addBusiExpressConfig(
			@ApiParam(value="商户标识", required = true) @RequestParam(value="bsecgKdnCode", required = true) String bsecgKdnCode, 
			@ApiParam(value="API_KEY", required = true) @RequestParam(value="bsecgApiKey", required = true) String bsecgApiKey, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin addBusiExpressConfig");		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		String uuid = RandomUtil.generateString(16);
		busiExpressConfig.setBsecgUuid(uuid);
		busiExpressConfig.setBsecgKdnCode(bsecgKdnCode);
		busiExpressConfig.setBsecgApiKey(bsecgApiKey);
		busiExpressConfig.setBsecgCdate(new Date());
		busiExpressConfig.setBsecgUdate(new Date());
		busiExpressConfigService.insertBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiExpressConfigController]:end addBusiExpressConfig");
	}

	/**
	* 修改
	*
	* @param bsecgUuid 标识UUID
	* @param bsecgKdnCode 商户标识
	* @param bsecgApiKey API_KEY
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiExpressConfig", method=RequestMethod.POST)
	public void updateBusiExpressConfig(
			@ApiParam(value="快递鸟配置标识", required = true) @RequestParam(value="bsecgUuid", required = true) String bsecgUuid, 
			@ApiParam(value="商户标识", required = true) @RequestParam(value="bsecgKdnCode", required = true) String bsecgKdnCode, 
			@ApiParam(value="API_KEY", required = true) @RequestParam(value="bsecgApiKey", required = true) String bsecgApiKey, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin updateBusiExpressConfig");		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);
		busiExpressConfig.setBsecgKdnCode(bsecgKdnCode);
		busiExpressConfig.setBsecgApiKey(bsecgApiKey);
		busiExpressConfig.setBsecgUdate(new Date());
		busiExpressConfigService.updateBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiExpressConfigController]:end updateBusiExpressConfig");
	}

	/**
	* 删除
	*
	* @param bsecgUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiExpressConfig(
			@ApiParam(value="快递鸟配置标识", required = true) @RequestParam(value="bsecgUuid", required = true) String bsecgUuid, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin deleteBusiExpressConfig");		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);
		busiExpressConfigService.deleteBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiExpressConfigController]:end deleteBusiExpressConfig");
	}

	/**
	* 批量删除
	*
	* @param bsecgUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiExpressConfig(
			@ApiParam(value="快递鸟配置标识集合(|拼接)", required = true) @RequestParam(value="bsecgUuids", required = true) String bsecgUuids, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin deleteBatchBusiExpressConfig");		String[] uuids=bsecgUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiExpressConfigController]:end deleteBatchBusiExpressConfig");
			return;
		}
		busiExpressConfigService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiExpressConfigController]:end deleteBatchBusiExpressConfig");
	}

	/**
	* 获取单个
	*
	* @param bsecgUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个快递鸟配置", httpMethod = "GET", notes = "获取单个快递鸟配置", response = BusiExpressConfigVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiExpressConfig(
			@ApiParam(value="快递鸟配置标识", required = true) @RequestParam(value="bsecgUuid", required = true) String bsecgUuid, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin viewsBusiExpressConfig");		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);
		busiExpressConfig = busiExpressConfigService.getBusiExpressConfig(busiExpressConfig);
		BusiExpressConfigVO busiExpressConfigVO = new BusiExpressConfigVO();
		busiExpressConfigVO.convertPOToVO(busiExpressConfig);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiExpressConfigVO), response);
		logger.info("[BusiExpressConfigController]:end viewsBusiExpressConfig");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取列表", httpMethod = "GET", notes = "获取列表", response = BusiExpressConfigVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findBusiExpressConfigList(
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin findBusiExpressConfigList");		List<BusiExpressConfig> lists = busiExpressConfigService.findBusiExpressConfigList();
		List<BusiExpressConfigVO> vos = new ArrayList<BusiExpressConfigVO>();
		BusiExpressConfigVO vo;
		for (BusiExpressConfig busiExpressConfig : lists) {
			vo = new BusiExpressConfigVO();			vo.convertPOToVO(busiExpressConfig);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiExpressConfigController]:end findBusiExpressConfigList");
	}

	/**
	* 获取分页列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取分页列表", httpMethod = "GET", notes = "获取分页列表", response = BusiExpressConfigVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiExpressConfigPage(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin findBusiExpressConfigPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExpressConfig> page = busiExpressConfigService.findBusiExpressConfigPage(pageNum, pageSize);
		Page<BusiExpressConfigVO> pageVO = new Page<BusiExpressConfigVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiExpressConfigVO> vos = new ArrayList<BusiExpressConfigVO>();
		BusiExpressConfigVO vo;
		for (BusiExpressConfig busiExpressConfig : page.getResult()) {
			vo = new BusiExpressConfigVO();			vo.convertPOToVO(busiExpressConfig);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExpressConfigController]:end findBusiExpressConfigPage");
	}

}