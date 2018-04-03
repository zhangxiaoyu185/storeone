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
import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.service.BusiIndexProductService;
import com.xiaoyu.lingdian.service.BusiProductCategoryService;
import com.xiaoyu.lingdian.service.BusiProductService;
import com.xiaoyu.lingdian.vo.BusiProductVO;

@Controller
@RequestMapping(value="/busiProduct")
@Api(value="busiProduct", description="商品相关操作")
public class BusiProductController extends BaseController {

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;
	
   /**
    * 首页商品表
    */
    @Autowired
    private BusiIndexProductService busiIndexProductService;

	/**
	 * 附件默认文件夹
	 */
	public static String ATTACHMENT_DIR_PATH = "C:\\file\\storeone\\";

	/**
	* 添加(默认为2已下架)
	*
	* @param bsprtName 商品名称
	* @param bsprtCategory 分类
	* @param bsprtDesc 商品描述
	* @param bsprtTel 客服电话
	* @param bsprtView 详情
	* @param bsprtSalePrice 售价
	* @param bsprtOrignalPrice 原价范围
	* @param bsprtSaleCount 销售数量
	* @param bsprtAddress 地区
	* @param bsprtStock 库存
	* @param bsprtExpress 快递费用
	* @param bsprtPic 封面图
	* @param bsprtImageUrl1 主图1
	* @param bsprtImageUrl2 主图2
	* @param bsprtImageUrl3 主图3
	* @param bsprtImageUrl4 主图4
	* @param bsprtImageUrl5 主图5
	* @param bsprtProductTag 标签JSON串
	* @param bsprtRuleParam 规格参数JSON串
	* @param bsprtHotSpot 商品热点标记
	* @param bsprtHotspotName 商品热点名称
	* @param bsprtHotspotIcon 商品热点图标
	* @return
	*/
	@ApiOperation(value="添加(默认为2已下架)", httpMethod = "POST", notes = "添加(默认为2已下架)")
	@RequestMapping(value="/add/busiProduct", method=RequestMethod.POST)
	public void addBusiProduct(
			@ApiParam(value="商品名称", required = true) @RequestParam(value="bsprtName", required = true) String bsprtName, 
			@ApiParam(value="分类", required = true) @RequestParam(value="bsprtCategory", required = true) String bsprtCategory, 
			@ApiParam(value="商品描述", required = false) @RequestParam(value="bsprtDesc", required = false) String bsprtDesc, 
			@ApiParam(value="客服电话", required = false) @RequestParam(value="bsprtTel", required = false) String bsprtTel, 
			@ApiParam(value="详情", required = true) @RequestParam(value="bsprtView", required = true) String bsprtView, 
			@ApiParam(value="售价", required = true) @RequestParam(value="bsprtSalePrice", required = true) String bsprtSalePrice, 
			@ApiParam(value="原价范围", required = false) @RequestParam(value="bsprtOrignalPrice", required = false) String bsprtOrignalPrice, 
			@ApiParam(value="销售数量", required = true) @RequestParam(value="bsprtSaleCount", required = true) Integer bsprtSaleCount, 
			@ApiParam(value="地区", required = false) @RequestParam(value="bsprtAddress", required = false) String bsprtAddress, 
			@ApiParam(value="库存", required = true) @RequestParam(value="bsprtStock", required = true) Integer bsprtStock, 
			@ApiParam(value="快递费用", required = true) @RequestParam(value="bsprtExpress", required = true) Integer bsprtExpress, 
			@ApiParam(value="封面图", required = true) @RequestParam(value="bsprtPic", required = true) String bsprtPic, 
			@ApiParam(value="主图1", required = true) @RequestParam(value="bsprtImageUrl1", required = true) String bsprtImageUrl1, 
			@ApiParam(value="主图2", required = false) @RequestParam(value="bsprtImageUrl2", required = false) String bsprtImageUrl2, 
			@ApiParam(value="主图3", required = false) @RequestParam(value="bsprtImageUrl3", required = false) String bsprtImageUrl3, 
			@ApiParam(value="主图4", required = false) @RequestParam(value="bsprtImageUrl4", required = false) String bsprtImageUrl4, 
			@ApiParam(value="主图5", required = false) @RequestParam(value="bsprtImageUrl5", required = false) String bsprtImageUrl5, 
			@ApiParam(value="标签JSON串", required = false) @RequestParam(value="bsprtProductTag", required = false) String bsprtProductTag, 
			@ApiParam(value="规格参数JSON串额", required = false) @RequestParam(value="bsprtRuleParam", required = false) String bsprtRuleParam, 
			@ApiParam(value="商品热点标记", required = false) @RequestParam(value="bsprtHotSpot", required = false) String bsprtHotSpot, 
			@ApiParam(value="商品热点名称", required = false) @RequestParam(value="bsprtHotspotName", required = false) String bsprtHotspotName, 
			@ApiParam(value="商品热点图标", required = false) @RequestParam(value="bsprtHotspotIcon", required = false) String bsprtHotspotIcon, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin addBusiProduct");		BusiProduct busiProduct = new BusiProduct();
		String uuid = RandomUtil.generateString(16);
		busiProduct.setBsprtUuid(uuid);
		busiProduct.setBsprtName(bsprtName);
		busiProduct.setBsprtCategory(bsprtCategory);
		busiProduct.setBsprtDesc(bsprtDesc);
		busiProduct.setBsprtTel(bsprtTel);
		busiProduct.setBsprtView(bsprtView);
		busiProduct.setBsprtSalePrice(bsprtSalePrice);
		busiProduct.setBsprtOrignalPrice(bsprtOrignalPrice);
		busiProduct.setBsprtSaleCount(bsprtSaleCount);
		busiProduct.setBsprtAddress(bsprtAddress);
		busiProduct.setBsprtStock(bsprtStock);
		busiProduct.setBsprtSaleStatus(2);
		busiProduct.setBsprtExpress(bsprtExpress);
		busiProduct.setBsprtPic(bsprtPic);
		busiProduct.setBsprtImageUrl1(bsprtImageUrl1);
		busiProduct.setBsprtImageUrl2(bsprtImageUrl2);
		busiProduct.setBsprtImageUrl3(bsprtImageUrl3);
		busiProduct.setBsprtImageUrl4(bsprtImageUrl4);
		busiProduct.setBsprtImageUrl5(bsprtImageUrl5);
		busiProduct.setBsprtProductTag(bsprtProductTag);
		busiProduct.setBsprtRuleParam(bsprtRuleParam);
		busiProduct.setBsprtHotSpot(bsprtHotSpot);
		busiProduct.setBsprtHotspotIcon(bsprtHotspotIcon);
		busiProduct.setBsprtHotspotName(bsprtHotspotName);
		busiProduct.setBsprtCdate(new Date());
		busiProduct.setBsprtUdate(new Date());		
		busiProductService.insertBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiProductController]:end addBusiProduct");
	}
	
	/**
	* 修改
	*
	* @param bsprtUuid 标识UUID
	* @param bsprtName 商品名称
	* @param bsprtDesc 商品描述
	* @param bsprtTel 客服电话
	* @param bsprtView 详情
	* @param bsprtSalePrice 售价
	* @param bsprtOrignalPrice 原价范围
	* @param bsprtSaleCount 销售数量
	* @param bsprtAddress 地区
	* @param bsprtStock 库存
	* @param bsprtExpress 快递费用
	* @param bsprtPic 封面图
	* @param bsprtImageUrl1 主图1
	* @param bsprtImageUrl2 主图2
	* @param bsprtImageUrl3 主图3
	* @param bsprtImageUrl4 主图4
	* @param bsprtImageUrl5 主图5
	* @param bsprtProductTag 标签JSON串
	* @param bsprtRuleParam 规格参数JSON串
	* @param bsprtHotSpot 商品热点标记
	* @param bsprtHotspotName 商品热点名称
	* @param bsprtHotspotIcon 商品热点图标
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiProduct", method=RequestMethod.POST)
	public void updateBusiProduct(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			@ApiParam(value="商品名称", required = true) @RequestParam(value="bsprtName", required = true) String bsprtName, 
			@ApiParam(value="商品描述", required = false) @RequestParam(value="bsprtDesc", required = false) String bsprtDesc, 
			@ApiParam(value="客服电话", required = false) @RequestParam(value="bsprtTel", required = false) String bsprtTel, 
			@ApiParam(value="详情", required = true) @RequestParam(value="bsprtView", required = true) String bsprtView, 
			@ApiParam(value="售价", required = true) @RequestParam(value="bsprtSalePrice", required = true) String bsprtSalePrice, 
			@ApiParam(value="原价范围", required = false) @RequestParam(value="bsprtOrignalPrice", required = false) String bsprtOrignalPrice, 
			@ApiParam(value="销售数量", required = true) @RequestParam(value="bsprtSaleCount", required = true) Integer bsprtSaleCount, 
			@ApiParam(value="地区", required = false) @RequestParam(value="bsprtAddress", required = false) String bsprtAddress, 
			@ApiParam(value="库存", required = true) @RequestParam(value="bsprtStock", required = true) Integer bsprtStock, 
			@ApiParam(value="快递费用", required = true) @RequestParam(value="bsprtExpress", required = true) Integer bsprtExpress, 
			@ApiParam(value="封面图", required = true) @RequestParam(value="bsprtPic", required = true) String bsprtPic, 
			@ApiParam(value="主图1", required = true) @RequestParam(value="bsprtImageUrl1", required = true) String bsprtImageUrl1, 
			@ApiParam(value="主图2", required = false) @RequestParam(value="bsprtImageUrl2", required = false) String bsprtImageUrl2, 
			@ApiParam(value="主图3", required = false) @RequestParam(value="bsprtImageUrl3", required = false) String bsprtImageUrl3, 
			@ApiParam(value="主图4", required = false) @RequestParam(value="bsprtImageUrl4", required = false) String bsprtImageUrl4, 
			@ApiParam(value="主图5", required = false) @RequestParam(value="bsprtImageUrl5", required = false) String bsprtImageUrl5, 
			@ApiParam(value="标签JSON串", required = false) @RequestParam(value="bsprtProductTag", required = false) String bsprtProductTag, 
			@ApiParam(value="规格参数JSON串额", required = false) @RequestParam(value="bsprtRuleParam", required = false) String bsprtRuleParam, 
			@ApiParam(value="商品热点标记", required = false) @RequestParam(value="bsprtHotSpot", required = false) String bsprtHotSpot, 
			@ApiParam(value="商品热点名称", required = false) @RequestParam(value="bsprtHotspotName", required = false) String bsprtHotspotName, 
			@ApiParam(value="商品热点图标", required = false) @RequestParam(value="bsprtHotspotIcon", required = false) String bsprtHotspotIcon, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin updateBusiProduct");		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtName(bsprtName);
		busiProduct.setBsprtDesc(bsprtDesc);
		busiProduct.setBsprtTel(bsprtTel);
		busiProduct.setBsprtView(bsprtView);
		busiProduct.setBsprtSalePrice(bsprtSalePrice);
		busiProduct.setBsprtOrignalPrice(bsprtOrignalPrice);
		busiProduct.setBsprtSaleCount(bsprtSaleCount);
		busiProduct.setBsprtAddress(bsprtAddress);
		busiProduct.setBsprtStock(bsprtStock);
		busiProduct.setBsprtExpress(bsprtExpress);
		busiProduct.setBsprtPic(bsprtPic);
		busiProduct.setBsprtImageUrl1(bsprtImageUrl1);
		busiProduct.setBsprtImageUrl2(bsprtImageUrl2);
		busiProduct.setBsprtImageUrl3(bsprtImageUrl3);
		busiProduct.setBsprtImageUrl4(bsprtImageUrl4);
		busiProduct.setBsprtImageUrl5(bsprtImageUrl5);
		busiProduct.setBsprtProductTag(bsprtProductTag);
		busiProduct.setBsprtRuleParam(bsprtRuleParam);
		busiProduct.setBsprtHotSpot(bsprtHotSpot);
		busiProduct.setBsprtHotspotIcon(bsprtHotspotIcon);
		busiProduct.setBsprtHotspotName(bsprtHotspotName);
		busiProduct.setBsprtUdate(new Date());
		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiProductController]:end updateBusiProduct");
	}

	/**
	* 改为销售
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="改为销售", httpMethod = "POST", notes = "改为销售")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin enable");
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(1);
		busiProduct.setBsprtUdate(new Date());
		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为销售成功!"),response);
		logger.info("[BusiProductController]:end enable");
	}
	
	/**
	* 改为下架
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="改为下架", httpMethod = "POST", notes = "改为下架")
	@RequestMapping(value="/shelf", method=RequestMethod.POST)
	public void shelf(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin shelf");
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(2);
		busiProduct.setBsprtUdate(new Date());
		busiProductService.updateBusiProduct(busiProduct);

        BusiIndexProduct busiIndexProduct = new BusiIndexProduct(); 
        busiIndexProduct.setBsiptProduct(bsprtUuid);
        busiIndexProductService.deleteBusiIndexProduct(busiIndexProduct);
        
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为下架成功!"),response);
		logger.info("[BusiProductController]:end shelf");
	}
	
	/**
	* 改为删除
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="改为删除", httpMethod = "POST", notes = "改为删除")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin delete");
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(3);
		busiProduct.setBsprtUdate(new Date());
		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为删除成功!"),response);
		logger.info("[BusiProductController]:end delete");
	}
	
	/**
	* 批量删除
	*
	* @param bsprtUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiProduct(
			@ApiParam(value="商品标识集合(|拼接)", required = true) @RequestParam(value="bsprtUuids", required = true) String bsprtUuids, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin deleteBatchBusiProduct");		String[] uuids=bsprtUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiProductController]:end deleteBatchBusiProduct");
			return;
		}
		busiProductService.updateBatchBusiProductDeteleByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiProductController]:end deleteBatchBusiProduct");
	}

	/**
	* 前台获取单个商品
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="前台获取单个商品", httpMethod = "GET", notes = "前台获取单个商品", response = BusiProductVO.class)
	@RequestMapping(value="/views/before", method=RequestMethod.GET)
	public void viewsBusiProductBefore(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin viewsBusiProductBefore");
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct  || 1 != busiProduct.getBsprtSaleStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在或已下架!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductBefore");
			return;
		}
		BusiProductVO busiProductVO = new BusiProductVO();
		busiProductVO.convertPOToVO(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "前台获取单个信息成功", busiProductVO), response);
		logger.info("[BusiProductController]:end viewsBusiProductBefore");
	}
	
	/**
	* 后台获取单个商品
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="后台获取单个商品", httpMethod = "GET", notes = "后台获取单个商品", response = BusiProductVO.class)
	@RequestMapping(value="/views/back", method=RequestMethod.GET)
	public void viewsBusiProductByback(
			@ApiParam(value="商品标识UUID", required = true) @RequestParam(value="bsprtUuid", required = true) String bsprtUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin viewsBusiProductByback");		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductByback");
			return;
		}
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		hashCategoryUuids.add(busiProduct.getBsprtCategory());
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		BusiProductVO busiProductVO = new BusiProductVO();
		busiProductVO.convertPOToVO(busiProduct);
		busiProductVO.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台获取单个信息成功", busiProductVO), response);
		logger.info("[BusiProductController]:end viewsBusiProductByback");
	}

	/**
	* 获取所有销售中的商品list
	* 
	* @return
	*/
	@ApiOperation(value="获取所有销售中的商品list", httpMethod = "GET", notes = "获取所有销售中的商品list", response = BusiProductVO.class)
	@RequestMapping(value="/find/all/by/sale", method=RequestMethod.GET)
	public void findBusiProductForListsBySale(
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductForListsBySale");		List<BusiProduct> lists = busiProductService.findBusiProductForListsBySale();
		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : lists) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		BusiProductVO vo;
		for (BusiProduct busiProduct : lists) {
			vo = new BusiProductVO();			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductController]:end findBusiProductForListsBySale");
	}

	/**
	* 获取商品分类下的所有销售中的分页商品
	* 
	* @param bsprtCategory
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取商品分类下的所有销售中的分页商品", httpMethod = "GET", notes = "获取商品分类下的所有销售中的分页商品", response = BusiProductVO.class)
	@RequestMapping(value="/find/by/category", method=RequestMethod.GET)
	public void findBusiProductForPagesByCategory(
			@ApiParam(value="商品分类标识", required = true) @RequestParam(value="bsprtCategory", required = true) String bsprtCategory, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductForPagesByCategory");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProduct> page = busiProductService.findBusiProductForPagesByCategory(bsprtCategory, pageNum, pageSize);
		Page<BusiProductVO> pageVO = new Page<BusiProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());	
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : page.getResult()) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();
		BusiProductVO vo;
		for (BusiProduct busiProduct : page.getResult()) {
			vo = new BusiProductVO();
			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductController]:end findBusiProductForPagesByCategory");
	}

	/**
	* 根据商品分类标识和商品名称获取所有未删除的page
	* 
	* @param bsprtCategory
	* @param bsprtName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="根据商品分类标识和商品名称获取所有未删除的分页商品", httpMethod = "GET", notes = "根据商品分类标识和商品名称获取所有未删除的分页商品", response = BusiProductVO.class)
	@RequestMapping(value="/back/find/by/cnd", method=RequestMethod.GET)
	public void findBusiProductPageByBack(
			@ApiParam(value="商品分类标识", required = true) @RequestParam(value="bsprtCategory", required = true) String bsprtCategory, 
			@ApiParam(value="商品名称", required = false) @RequestParam(value="bsprtName", required = false) String bsprtName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductPageByBack");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProduct> page = busiProductService.findBusiProductPage(bsprtCategory, bsprtName, pageNum, pageSize);
		Page<BusiProductVO> pageVO = new Page<BusiProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : page.getResult()) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();
		BusiProductVO vo;
		for (BusiProduct busiProduct : page.getResult()) {
			vo = new BusiProductVO();			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductController]:end findBusiProductPageByBack");
	}

}