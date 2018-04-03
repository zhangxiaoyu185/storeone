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
import com.xiaoyu.lingdian.entity.BusiServerDynamic;
import com.xiaoyu.lingdian.entity.BusiServerType;
import com.xiaoyu.lingdian.service.BusiServerDynamicService;
import com.xiaoyu.lingdian.service.BusiServerTypeService;
import com.xiaoyu.lingdian.vo.BusiServerDynamicVO;

@Controller
@RequestMapping(value="/busiServerDynamic")
@Api(value="busiServerDynamic", description="服务动态相关操作")
public class BusiServerDynamicController extends BaseController {

	/**
	* 服务动态表
	*/
	@Autowired
	private BusiServerDynamicService busiServerDynamicService;
	
	/**
	* 服务类型表
	*/
	@Autowired
	private BusiServerTypeService busiServerTypeService;
	
	/**
	* 添加(刚添加的动态默认都是草稿)
	*
	* @param bssdcType 服务类别
	* @param bssdcTitle 标题
	* @param bssdcPic 封面图
	* @param bssdcContent 内容
	* @param bssdcDesc 描述
	* @return
	*/
	@ApiOperation(value="添加(默认为草稿)", httpMethod = "POST", notes = "添加(默认为草稿)")
	@RequestMapping(value="/add/busiServerDynamic", method=RequestMethod.POST)
	public void addBusiServerDynamic(
			@ApiParam(value="服务类别", required = true) @RequestParam(value="bssdcType", required = true) String bssdcType, 
			@ApiParam(value="标题", required = true) @RequestParam(value="bssdcTitle", required = true) String bssdcTitle, 
			@ApiParam(value="封面图", required = true) @RequestParam(value="bssdcPic", required = true) String bssdcPic, 
			@ApiParam(value="内容", required = true) @RequestParam(value="bssdcContent", required = true) String bssdcContent, 
			@ApiParam(value="描述", required = false) @RequestParam(value="bssdcDesc", required = false) String bssdcDesc, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin addBusiServerDynamic");
		//判断标题是否重复
		BusiServerDynamic namic = busiServerDynamicService.getBusiServerDynamicByTitle(bssdcType, bssdcTitle);
		if(null != namic){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标题已存在!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}
				
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		String uuid = RandomUtil.generateString(16);
		busiServerDynamic.setBssdcUuid(uuid);
		busiServerDynamic.setBssdcType(bssdcType);
		busiServerDynamic.setBssdcTitle(bssdcTitle);
		busiServerDynamic.setBssdcPic(bssdcPic);
		busiServerDynamic.setBssdcContent(bssdcContent);
		busiServerDynamic.setBssdcDesc(bssdcDesc);
		busiServerDynamic.setBssdcCdate(new Date());
		busiServerDynamic.setBssdcUdate(new Date());
		busiServerDynamic.setBssdcStatus(0);
		busiServerDynamicService.insertBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
	}

	/**
	* 修改
	*
	* @param bssdcUuid 标识UUID
	* @param bssdcType 服务类别
	* @param bssdcTitle 标题
	* @param bssdcPic 封面图
	* @param bssdcContent 内容
	* @param bssdcDesc 描述
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiServerDynamic", method=RequestMethod.POST)
	public void updateBusiServerDynamic(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid, 
			@ApiParam(value="服务类别", required = true) @RequestParam(value="bssdcType", required = true) String bssdcType, 
			@ApiParam(value="标题", required = true) @RequestParam(value="bssdcTitle", required = true) String bssdcTitle, 
			@ApiParam(value="封面图", required = true) @RequestParam(value="bssdcPic", required = true) String bssdcPic, 
			@ApiParam(value="内容", required = true) @RequestParam(value="bssdcContent", required = true) String bssdcContent, 
			@ApiParam(value="描述", required = false) @RequestParam(value="bssdcDesc", required = false) String bssdcDesc, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin updateBusiServerDynamic");
		//判断标题是否重复
		BusiServerDynamic namic = busiServerDynamicService.getBusiServerDynamicByTitle(bssdcType, bssdcTitle);
		if(null != namic && !namic.getBssdcUuid().equals(bssdcUuid)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标题已存在!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
		//判断是否已发布
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}	
		if (busiServerDynamic.getBssdcStatus() == 1) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "已发布状态的服务动态不能修改!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
				
		busiServerDynamic.setBssdcType(bssdcType);
		busiServerDynamic.setBssdcTitle(bssdcTitle);
		busiServerDynamic.setBssdcPic(bssdcPic);
		busiServerDynamic.setBssdcContent(bssdcContent);
		busiServerDynamic.setBssdcDesc(bssdcDesc);
		busiServerDynamic.setBssdcUdate(new Date());
		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
	}

	/**
	 * 发布
	 *
	 * @param bssdcUuid 标识UUID
	 * @return
	 */
	@ApiOperation(value="发布", httpMethod = "POST", notes = "发布")
	@RequestMapping(value="/publish", method=RequestMethod.POST)
	public void publish(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin publish");
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic.setBssdcStatus(1);
		busiServerDynamic.setBssdcPdate(new Date());
		busiServerDynamic.setBssdcUdate(new Date());
		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发布成功!"),response);
		logger.info("[BusiServerDynamicController]:end publish");
	}

	/**
	 * 草稿
	 *
	 * @param bssdcUuid 标识UUID
	 * @return
	 */
	@ApiOperation(value="草稿", httpMethod = "POST", notes = "草稿")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid,
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin disable");
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic.setBssdcStatus(0);
		busiServerDynamic.setBssdcUdate(new Date());
		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改成草稿成功!"),response);
		logger.info("[BusiServerDynamicController]:end disable");
	}

	/**
	* 删除
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiServerDynamic(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin deleteBusiServerDynamic");
		//判断已发布的不能删除
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null != busiServerDynamic && 0 == busiServerDynamic.getBssdcStatus()) {
			busiServerDynamicService.deleteBusiServerDynamic(busiServerDynamic);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiServerDynamicController]:end deleteBusiServerDynamic");
	}

	/**
	* 批量删除
	*
	* @param bssdcUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiServerDynamic(
			@ApiParam(value="服务动态标识集合(|拼接)", required = true) @RequestParam(value="bssdcUuids", required = true) String bssdcUuids, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin deleteBatchBusiServerDynamic");
		String[] uuids=bssdcUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		BusiServerDynamic busiServerDynamic;
		for (int i = 0; i < uuids.length; i++) {
			//判断已发布的不能删除
			busiServerDynamic = new BusiServerDynamic();
			busiServerDynamic.setBssdcUuid(uuids[i]);
			busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
			if (null != busiServerDynamic && 0 == busiServerDynamic.getBssdcStatus()) {
				list.add(uuids[i]);
			}
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiServerDynamicController]:end deleteBatchBusiServerDynamic");
			return;
		}

		busiServerDynamicService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiServerDynamicController]:end deleteBatchBusiServerDynamic");
	}

	/**
	* 后台获取单个服务动态
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="后台获取单个服务动态", httpMethod = "GET", notes = "后台获取单个服务动态", response = BusiServerDynamicVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiServerDynamic(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin viewsBusiServerDynamic");
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end viewsBusiServerDynamic");
			return;
		}

		BusiServerDynamicVO busiServerDynamicVO = new BusiServerDynamicVO();
		busiServerDynamicVO.convertPOToVO(busiServerDynamic);		
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(busiServerDynamic.getBssdcType());
		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);
		if (null != busiServerType) {
			busiServerDynamicVO.setBssdcTypeName(busiServerType.getBssteName());
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiServerDynamicVO), response);
		logger.info("[BusiServerDynamicController]:end viewsBusiServerDynamic");
	}

	/**
	* 前台获取单个服务动态(查看数+1)
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="前台获取单个服务动态", httpMethod = "GET", notes = "前台获取单个服务动态", response = BusiServerDynamicVO.class)
	@RequestMapping(value="/index/views", method=RequestMethod.GET)
	public void viewsIndex(
			@ApiParam(value="服务动态标识", required = true) @RequestParam(value="bssdcUuid", required = true) String bssdcUuid, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin viewsIndex");
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic || busiServerDynamic.getBssdcStatus() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end viewsIndex");
			return;
		}

		//更新数+1
		busiServerDynamicService.updateBusiServerDynamicCount(bssdcUuid);
		
		BusiServerDynamicVO busiServerDynamicVO = new BusiServerDynamicVO();
		busiServerDynamicVO.convertPOToVO(busiServerDynamic);	
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(busiServerDynamic.getBssdcType());
		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);
		if (null != busiServerType) {
			busiServerDynamicVO.setBssdcTypeName(busiServerType.getBssteName());
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "前台获取单个信息成功", busiServerDynamicVO), response);
		logger.info("[BusiServerDynamicController]:end viewsIndex");
	}

	/**
	* 后台按标题获取列表<Page>
	* 
	* @param bssdcTitle 标题
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台按标题获取分页列表", httpMethod = "GET", notes = "后台按标题获取分页列表", response = BusiServerDynamicVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiServerDynamicPage(
			@ApiParam(value="标题", required = false) @RequestParam(value="bssdcTitle", required = false) String bssdcTitle, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin findBusiServerDynamicPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerDynamic> page = busiServerDynamicService.findBusiServerDynamicForPages(bssdcTitle, pageNum, pageSize);
		Page<BusiServerDynamicVO> pageVO = new Page<BusiServerDynamicVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerDynamicVO> vos = new ArrayList<BusiServerDynamicVO>();
		
		HashSet<String> hashSet = new HashSet<String>();
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			hashSet.add(busiServerDynamic.getBssdcType());
		}
		List<String> typeUuids = new ArrayList<String>(hashSet);
		Map<String, BusiServerType> map = busiServerTypeService.findBusiServerTypeMapByUuidList(typeUuids);		
		BusiServerDynamicVO vo;
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			vo = new BusiServerDynamicVO();
			vo.convertPOToVO(busiServerDynamic);
			vo.setBssdcTypeName(map.get(busiServerDynamic.getBssdcType()) == null ? null : map.get(busiServerDynamic.getBssdcType()).getBssteName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerDynamicController]:end findBusiServerDynamicPage");
	}

	/**
	* 前台分页列表
	* 
	* @param bssdcType 类别
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="前台分页列表", httpMethod = "GET", notes = "前台分页列表", response = BusiServerDynamicVO.class)
	@RequestMapping(value="/find/by/cnd/before", method=RequestMethod.GET)
	public void findBusiDynamicForPagesByBefore(
			@ApiParam(value="服务类别标识", required = true) @RequestParam(value="bssdcType", required = true) String bssdcType, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin findBusiDynamicForPagesByBefore");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerDynamic> page = busiServerDynamicService.findBusiServerDynamicForPagesByBefore(bssdcType, pageNum, pageSize);
		Page<BusiServerDynamicVO> pageVO = new Page<BusiServerDynamicVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerDynamicVO> vos = new ArrayList<BusiServerDynamicVO>();
		BusiServerDynamicVO vo;
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			vo = new BusiServerDynamicVO();
			vo.convertPOToVO(busiServerDynamic);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerDynamicController]:end findBusiDynamicForPagesByBefore");
	}

}