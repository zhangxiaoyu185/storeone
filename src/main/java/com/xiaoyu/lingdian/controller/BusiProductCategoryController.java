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
import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.service.BusiProductCategoryService;
import com.xiaoyu.lingdian.vo.BusiProductCategoryVO;

@Controller
@RequestMapping(value="/busiProductCategory")
@Api(value="busiProductCategory", description="商品分类相关操作")
public class BusiProductCategoryController extends BaseController {

	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;
	
	/**
	* 添加(默认启动)，暂时只做两级
	*
	* @param bspcyName 分类名称
	* @param bspcyIcon 分类图标
	* @param bspcyOrd 顺序
	* @param bspcyTop 上级分类
	* @param bspcyChildNode 是否有子节点:1有0无
	* @return
	*/
	@ApiOperation(value="添加(默认启动)，暂时只做两级", httpMethod = "POST", notes = "添加(默认启动)，暂时只做两级")
	@RequestMapping(value="/add/busiProductCategory", method=RequestMethod.POST)
	public void addBusiProductCategory(
			@ApiParam(value="分类名称", required = true) @RequestParam(value="bspcyName", required = true) String bspcyName, 
			@ApiParam(value="分类图标", required = false) @RequestParam(value="bspcyIcon", required = false) String bspcyIcon, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bspcyOrd", required = true) Integer bspcyOrd, 
			@ApiParam(value="上级分类", required = false) @RequestParam(value="bspcyTop", required = false) String bspcyTop, 
			@ApiParam(value="是否有子节点:1有0无", required = true) @RequestParam(value="bspcyChildNode", required = true) Integer bspcyChildNode, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin addBusiProductCategory");		BusiProductCategory busiProductCategory = new BusiProductCategory();
		String uuid = RandomUtil.generateString(16);
		busiProductCategory.setBspcyUuid(uuid);
		busiProductCategory.setBspcyName(bspcyName);
		busiProductCategory.setBspcyIcon(bspcyIcon);
		busiProductCategory.setBspcyOrd(bspcyOrd);
		busiProductCategory.setBspcyStatus(1);
		busiProductCategory.setBspcyTop(bspcyTop);
		busiProductCategory.setBspcyChildNode(bspcyChildNode);
		busiProductCategory.setBspcyCdate(new Date());
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.insertBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiProductCategoryController]:end addBusiProductCategory");
	}

	/**
	* 修改
	*
	* @param bspcyUuid 标识UUID
	* @param bspcyName 分类名称
	* @param bspcyIcon 分类图标
	* @param bspcyOrd 顺序
	* @param bspcyTop 上级分类
	* @param bspcyChildNode 是否有子节点:1有0无
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiProductCategory", method=RequestMethod.POST)
	public void updateBusiProductCategory(
			@ApiParam(value="分类标识", required = true) @RequestParam(value="bspcyUuid", required = true) String bspcyUuid, 
			@ApiParam(value="分类名称", required = true) @RequestParam(value="bspcyName", required = true) String bspcyName, 
			@ApiParam(value="分类图标", required = false) @RequestParam(value="bspcyIcon", required = false) String bspcyIcon, 
			@ApiParam(value="顺序", required = true) @RequestParam(value="bspcyOrd", required = true) Integer bspcyOrd, 
			@ApiParam(value="上级分类", required = false) @RequestParam(value="bspcyTop", required = false) String bspcyTop, 
			@ApiParam(value="是否有子节点:1有0无", required = true) @RequestParam(value="bspcyChildNode", required = true) Integer bspcyChildNode, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin updateBusiProductCategory");		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyName(bspcyName);
		busiProductCategory.setBspcyIcon(bspcyIcon);
		busiProductCategory.setBspcyOrd(bspcyOrd);
		busiProductCategory.setBspcyTop(bspcyTop);
		busiProductCategory.setBspcyChildNode(bspcyChildNode);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
	}

	/**
	* 启用
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="启用", httpMethod = "POST", notes = "启用")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="分类标识", required = true) @RequestParam(value="bspcyUuid", required = true) String bspcyUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin enable");
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(1);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiProductCategoryController]:end enable");
	}
	
	/**
	* 禁用,父结点禁用,子节点也禁用
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="禁用,父结点禁用,子节点也禁用", httpMethod = "POST", notes = "禁用,父结点禁用,子节点也禁用")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="分类标识", required = true) @RequestParam(value="bspcyUuid", required = true) String bspcyUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin disable");
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(0);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);
		//子节点也禁用
		busiProductCategoryService.updateBusiProductCategoryDisableByTop(bspcyUuid);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiProductCategoryController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiProductCategory(
			@ApiParam(value="分类标识", required = true) @RequestParam(value="bspcyUuid", required = true) String bspcyUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin deleteBusiProductCategory");		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(2);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);
		//子节点也删除
		busiProductCategoryService.updatBatchBusiProductCategoryDeteleByTop(bspcyUuid);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiProductCategoryController]:end deleteBusiProductCategory");
	}

	/**
	* 批量删除
	*
	* @param bspcyUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiProductCategory(
			@ApiParam(value="分类标识集合(|拼接)", required = true) @RequestParam(value="bspcyUuids", required = true) String bspcyUuids, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin deleteBatchBusiProductCategory");		String[] uuids=bspcyUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			busiProductCategoryService.updateBusiProductCategoryDisableByTop(uuids[i]);
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiProductCategoryController]:end deleteBatchBusiProductCategory");
			return;
		}
		busiProductCategoryService.updatBatchBusiProductCategoryDeteleByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiProductCategoryController]:end deleteBatchBusiProductCategory");
	}

	/**
	* 获取单个商品分类
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个商品分类", httpMethod = "GET", notes = "获取单个商品分类", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiProductCategory(
			@ApiParam(value="分类标识", required = true) @RequestParam(value="bspcyUuid", required = true) String bspcyUuid, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin viewsBusiProductCategory");		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory = busiProductCategoryService.getBusiProductCategory(busiProductCategory);
		if (null == busiProductCategory) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品分类不存在!"), response);
			logger.info("[BusiProductCategoryController]:end viewsBusiProductCategory");
			return;
		}
		BusiProductCategoryVO busiProductCategoryVO = new BusiProductCategoryVO();
		busiProductCategoryVO.convertPOToVO(busiProductCategory);		
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
			hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
		}	
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);		
		if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
			busiProductCategoryVO.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiProductCategoryVO), response);
		logger.info("[BusiProductCategoryController]:end viewsBusiProductCategory");
	}

	/**
	* 获取所有有子节点的启用分类list
	* 
	* @return
	*/
	@ApiOperation(value="获取所有有子节点的启用分类列表", httpMethod = "GET", notes = "获取所有有子节点的启用分类列表", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/find/have/child", method=RequestMethod.GET)
	public void findBusiProductCategoryForListsHaveChild(
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsHaveChild");
		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsHaveChild();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsHaveChild");
	}
	
	/**
	* 根据父结点获取启用的分类子节点列表list
	* 
	* @param bspcyTop
	* @return
	*/
	@ApiOperation(value="根据父结点获取启用的分类子节点列表list", httpMethod = "GET", notes = "根据父结点获取启用的分类子节点列表list", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/find/all/child/by/top", method=RequestMethod.GET)
	public void findBusiProductCategoryForListsByTop(
			@ApiParam(value="上级分类", required = true) @RequestParam(value="bspcyTop", required = true) String bspcyTop, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsByTop");
		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsByTop(bspcyTop);
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsByTop");
	}

	/**
	* 商品用-获取所有启用的子分类列表list
	* 
	* @return
	*/
	@ApiOperation(value="商品用-获取所有启用的子分类列表list", httpMethod = "GET", notes = "商品用-获取所有启用的子分类列表list", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/find/all/child/by/product", method=RequestMethod.GET)
	public void findBusiProductCategoryForListsByChild(
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsByChild");
		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsByChild();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsByChild");
	}

	/**
	* 商品用-获取所有启用的分类列表list,返回变成树
	* 
	* @return
	*/
	@ApiOperation(value="商品用-获取所有启用的分类列表list,返回变成树", httpMethod = "GET", notes = "商品用-获取所有启用的分类列表list,返回变成树", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/find/all/by/product", method=RequestMethod.GET)
	public void findBusiProductCategoryForListsAll(
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsAll");		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsAll();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsAll");
	}

	/**
	* 后台根据分类名称获取分页分类page
	* 
	* @param bspcyTop
	* @param bspcyName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台根据分类名称获取分页分类", httpMethod = "GET", notes = "后台根据分类名称获取分页分类", response = BusiProductCategoryVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiProductCategoryPage(
			@ApiParam(value="上级分类", required = false) @RequestParam(value="bspcyTop", required = false) String bspcyTop, 
			@ApiParam(value="分类名称", required = false) @RequestParam(value="bspcyName", required = false) String bspcyName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProductCategory> page = busiProductCategoryService.findBusiProductCategoryPage(bspcyTop, bspcyName, pageNum, pageSize);
		Page<BusiProductCategoryVO> pageVO = new Page<BusiProductCategoryVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());		
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : page.getResult()) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : page.getResult()) {
			vo = new BusiProductCategoryVO();			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryPage");
	}

}