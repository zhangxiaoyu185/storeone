package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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
import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.service.BusiIndexProductService;
import com.xiaoyu.lingdian.service.BusiProductService;
import com.xiaoyu.lingdian.vo.BusiIndexProductVO;

@Controller
@RequestMapping(value="/busiIndexProduct")
@Api(value="busiIndexProduct", description="首页商品配置相关操作")
public class BusiIndexProductController extends BaseController {

	/**
	* 首页商品表
	*/
	@Autowired
	private BusiIndexProductService busiIndexProductService;

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 添加(默认0禁用)
	*
	* @param bsiptProduct 商品标识
	* @param bsiptOrd 顺序
	* @return
	*/
	@ApiOperation(value="添加(默认0禁用)", httpMethod = "POST", notes = "添加(默认0禁用)")
	@RequestMapping(value="/add/busiIndexProduct", method=RequestMethod.POST)
	public void addBusiIndexProduct(
			@ApiParam(value="商品标识", required = true) @RequestParam(value="bsiptProduct", required = true) String bsiptProduct, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bsiptOrd", required = true) Integer bsiptOrd, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin addBusiIndexProduct");				BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		String uuid = RandomUtil.generateString(16);
		busiIndexProduct.setBsiptUuid(uuid);
		busiIndexProduct.setBsiptProduct(bsiptProduct);
		busiIndexProduct.setBsiptOrd(bsiptOrd);
		busiIndexProduct.setBsiptStatus(0);
		busiIndexProduct.setBsiptCdate(new Date());
		busiIndexProduct.setBsiptUdate(new Date());
		busiIndexProductService.insertBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiIndexProductController]:end addBusiIndexProduct");
	}

	/**
	* 修改
	*
	* @param bsiptUuid 标识UUID
	* @param bsiptProduct 商品标识
	* @param bsiptOrd 顺序
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiIndexProduct", method=RequestMethod.POST)
	public void updateBusiIndexProduct(
			@ApiParam(value="首页商品配置标识", required = true) @RequestParam(value="bsiptUuid", required = true) String bsiptUuid, 
			@ApiParam(value="商品标识", required = true) @RequestParam(value="bsiptProduct", required = true) String bsiptProduct, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bsiptOrd", required = true) Integer bsiptOrd, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin updateBusiIndexProduct");		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptProduct(bsiptProduct);
		busiIndexProduct.setBsiptOrd(bsiptOrd);
		busiIndexProduct.setBsiptUdate(new Date());
		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiIndexProductController]:end updateBusiIndexProduct");
	}

	/**
	* 启用
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="启用", httpMethod = "POST", notes = "启用")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="首页商品配置标识", required = true) @RequestParam(value="bsiptUuid", required = true) String bsiptUuid, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin enable");	
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptStatus(1);
		busiIndexProduct.setBsiptUdate(new Date());
		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiIndexProductController]:end enable");
	}

	/**
	* 禁用
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="禁用", httpMethod = "POST", notes = "禁用")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="首页商品配置标识", required = true) @RequestParam(value="bsiptUuid", required = true) String bsiptUuid, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin disable");		
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptStatus(0);
		busiIndexProduct.setBsiptUdate(new Date());
		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiIndexProductController]:end disable");
	}

	/**
	* 删除
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiIndexProduct(
			@ApiParam(value="首页商品配置标识", required = true) @RequestParam(value="bsiptUuid", required = true) String bsiptUuid, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin deleteBusiIndexProduct");		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProductService.deleteBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiIndexProductController]:end deleteBusiIndexProduct");
	}

	/**
	* 批量删除
	*
	* @param bsiptUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiIndexProduct(
			@ApiParam(value="首页商品配置标识集合(|拼接)", required = true) @RequestParam(value="bsiptUuids", required = true) String bsiptUuids, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin deleteBatchBusiIndexProduct");		String[] uuids=bsiptUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiIndexProductController]:end deleteBatchBusiIndexProduct");
			return;
		}
		busiIndexProductService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiIndexProductController]:end deleteBatchBusiIndexProduct");
	}

	/**
	* 前台获取启动的分页热榜page
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="前台获取启动的分页热榜", httpMethod = "GET", notes = "前台获取启动的分页热榜", response = BusiIndexProductVO.class)
	@RequestMapping(value="/find/by/cnd/before", method=RequestMethod.GET)
	public void findBusiIndexProductPageBefore(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin findBusiIndexProductPageBefore");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIndexProduct> page = busiIndexProductService.findBusiIndexProductPageBefore(pageNum, pageSize);
		Page<BusiIndexProductVO> pageVO = new Page<BusiIndexProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			hashProductUuids.add(busiIndexProduct.getBsiptProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);	
		List<BusiIndexProductVO> vos = new ArrayList<BusiIndexProductVO>();
		BusiIndexProductVO vo;
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			vo = new BusiIndexProductVO();			vo.convertPOToVO(busiIndexProduct);
			if(null != productMap.get(busiIndexProduct.getBsiptProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiIndexProduct.getBsiptProduct()));
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIndexProductController]:end findBusiIndexProductPageBefore");
	}

	/**
	* 后台根据商品名称查询分页热榜page
	* 
	* @param productName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台根据商品名称查询分页热榜", httpMethod = "GET", notes = "后台根据商品名称查询分页热榜", response = BusiIndexProductVO.class)
	@RequestMapping(value="/find/by/cnd/after", method=RequestMethod.GET)
	public void findBusiIndexProductPage(
			@ApiParam(value="商品名称", required = false) @RequestParam(value="productName", required = false) String productName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin findBusiIndexProductPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIndexProduct> page = busiIndexProductService.findBusiIndexProductForPages(productName, pageNum, pageSize);
		Page<BusiIndexProductVO> pageVO = new Page<BusiIndexProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			hashProductUuids.add(busiIndexProduct.getBsiptProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);		
		List<BusiIndexProductVO> vos = new ArrayList<BusiIndexProductVO>();
		BusiIndexProductVO vo;
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			vo = new BusiIndexProductVO();
			vo.convertPOToVO(busiIndexProduct);
			if(null != productMap.get(busiIndexProduct.getBsiptProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiIndexProduct.getBsiptProduct()));
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIndexProductController]:end findBusiIndexProductPage");
	}

}