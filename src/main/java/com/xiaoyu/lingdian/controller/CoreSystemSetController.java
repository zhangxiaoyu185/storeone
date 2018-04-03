package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import com.xiaoyu.lingdian.vo.CoreSystemSetVO;

@Controller
@RequestMapping(value="/coreSystemSet")
@Api(value="coreSystemSet", description="系统设置相关操作")
public class CoreSystemSetController extends BaseController {

	/**
	* 系统设置表
	*/
	@Autowired
	private CoreSystemSetService coreSystemSetService;
	
	/**
	* 添加
	*
	* @param crsstMessagePath 短信接口路径
	* @param crsstMessageLoginname 短信账户名
	* @param crsstMessagePwd 短信密码
	* @param crsstMessageKey 短信key
	* @param crsstMessageDomain 项目域名
	* @param crsstAttachmentDir 附件存放目录
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/coreSystemSet", method=RequestMethod.POST)
	public void addCoreSystemSet(
			@ApiParam(value="短信接口路径", required = true) @RequestParam(value="crsstMessagePath", required = true) String crsstMessagePath, 
			@ApiParam(value="短信账户名", required = true) @RequestParam(value="crsstMessageLoginname", required = true) String crsstMessageLoginname, 
			@ApiParam(value="短信密码", required = true) @RequestParam(value="crsstMessagePwd", required = true) String crsstMessagePwd, 
			@ApiParam(value="短信key", required = true) @RequestParam(value="crsstMessageKey", required = true) String crsstMessageKey, 
			@ApiParam(value="项目域名", required = true) @RequestParam(value="crsstMessageDomain", required = true) String crsstMessageDomain, 
			@ApiParam(value="附件存放目录", required = true) @RequestParam(value="crsstAttachmentDir", required = true) String crsstAttachmentDir, 
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin addCoreSystemSet");		CoreSystemSet coreSystemSet = new CoreSystemSet();
		String uuid = RandomUtil.generateString(16);
		coreSystemSet.setCrsstUuid(uuid);
		coreSystemSet.setCrsstMessagePath(crsstMessagePath);
		coreSystemSet.setCrsstMessageLoginname(crsstMessageLoginname);
		coreSystemSet.setCrsstMessagePwd(crsstMessagePwd);
		coreSystemSet.setCrsstMessageKey(crsstMessageKey);
		coreSystemSet.setCrsstMessageDomain(crsstMessageDomain);
		coreSystemSet.setCrsstAttachmentDir(crsstAttachmentDir);

		coreSystemSetService.insertCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreSystemSetController]:end addCoreSystemSet");
	}

	/**
	* 修改
	*
	* @param crsstUuid 标识UUID
	* @param crsstMessagePath 短信接口路径
	* @param crsstMessageLoginname 短信账户名
	* @param crsstMessagePwd 短信密码
	* @param crsstMessageKey 短信key
	* @param crsstMessageDomain 项目域名
	* @param crsstAttachmentDir 附件存放目录
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/coreSystemSet", method=RequestMethod.POST)
	public void updateCoreSystemSet(
			@ApiParam(value="系统设置标识", required = true) @RequestParam(value="crsstMessagePath", required = true) String crsstUuid, 
			@ApiParam(value="短信接口路径", required = true) @RequestParam(value="crsstMessagePath", required = true) String crsstMessagePath, 
			@ApiParam(value="短信账户名", required = true) @RequestParam(value="crsstMessageLoginname", required = true) String crsstMessageLoginname, 
			@ApiParam(value="短信密码", required = true) @RequestParam(value="crsstMessagePwd", required = true) String crsstMessagePwd, 
			@ApiParam(value="短信key", required = true) @RequestParam(value="crsstMessageKey", required = true) String crsstMessageKey, 
			@ApiParam(value="项目域名", required = true) @RequestParam(value="crsstMessageDomain", required = true) String crsstMessageDomain, 
			@ApiParam(value="附件存放目录", required = true) @RequestParam(value="crsstAttachmentDir", required = true) String crsstAttachmentDir, 
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin updateCoreSystemSet");		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);
		coreSystemSet.setCrsstMessagePath(crsstMessagePath);
		coreSystemSet.setCrsstMessageLoginname(crsstMessageLoginname);
		coreSystemSet.setCrsstMessagePwd(crsstMessagePwd);
		coreSystemSet.setCrsstMessageKey(crsstMessageKey);
		coreSystemSet.setCrsstMessageDomain(crsstMessageDomain);
		coreSystemSet.setCrsstAttachmentDir(crsstAttachmentDir);

		coreSystemSetService.updateCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
	}

	/**
	* 删除
	*
	* @param crsstUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreSystemSet(
			@ApiParam(value="系统设置标识", required = true) @RequestParam(value="crsstMessagePath", required = true) String crsstUuid, 
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin deleteCoreSystemSet");		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);

		coreSystemSetService.deleteCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreSystemSetController]:end deleteCoreSystemSet");
	}

	/**
	* 批量删除
	*
	* @param crsstUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreSystemSet(
			@ApiParam(value="系统设置标识集合(|拼接)", required = true) @RequestParam(value="crsstUuids", required = true) String crsstUuids,
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin deleteBatchCoreSystemSet");		String[] uuids=crsstUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreSystemSetController]:end deleteBatchCoreSystemSet");
			return;
		}
		coreSystemSetService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreSystemSetController]:end deleteBatchCoreSystemSet");
	}

	/**
	* 获取单个系统设置
	*
	* @param crsstUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个系统设置", httpMethod = "GET", notes = "获取单个系统设置", response = CoreSystemSetVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsCoreSystemSet(
			@ApiParam(value="系统设置标识", required = true) @RequestParam(value="crsstUuid", required = true) String crsstUuid,
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin viewsCoreSystemSet");		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);

		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);

		CoreSystemSetVO coreSystemSetVO = new CoreSystemSetVO();
		coreSystemSetVO.convertPOToVO(coreSystemSet);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreSystemSetVO), response);
		logger.info("[CoreSystemSetController]:end viewsCoreSystemSet");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取系统设置列表", httpMethod = "GET", notes = "获取系统设置列表", response = CoreSystemSetVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findCoreSystemSetList(
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin findCoreSystemSetList");		List<CoreSystemSet> lists = coreSystemSetService.findCoreSystemSetList();
		List<CoreSystemSetVO> vos = new ArrayList<CoreSystemSetVO>();
		CoreSystemSetVO vo;
		for (CoreSystemSet coreSystemSet : lists) {
			vo = new CoreSystemSetVO();			vo.convertPOToVO(coreSystemSet);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreSystemSetController]:end findCoreSystemSetList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取系统设置分页列表", httpMethod = "GET", notes = "获取系统设置分页列表", response = CoreSystemSetVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findCoreSystemSetPage(
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = true) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = true) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin findCoreSystemSetPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreSystemSet> page = coreSystemSetService.findCoreSystemSetPage(pageNum, pageSize);
		Page<CoreSystemSetVO> pageVO = new Page<CoreSystemSetVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreSystemSetVO> vos = new ArrayList<CoreSystemSetVO>();
		CoreSystemSetVO vo;
		for (CoreSystemSet coreSystemSet : page.getResult()) {
			vo = new CoreSystemSetVO();			vo.convertPOToVO(coreSystemSet);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreSystemSetController]:end findCoreSystemSetPage");
	}

}