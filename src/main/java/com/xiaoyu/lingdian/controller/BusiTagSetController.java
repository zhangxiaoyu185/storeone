package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiTagSet;
import com.xiaoyu.lingdian.service.BusiTagSetService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.ExcelUtils;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.BusiTagSetVO;

@Controller
@RequestMapping(value="/busiTagSet")
@Api(value="busiTagSet", description="标签配置相关操作")
public class BusiTagSetController extends BaseController {

	/**
	* 标签配置表
	*/
	@Autowired
	private BusiTagSetService busiTagSetService;
	
	/**
	* 后台导入EXCEL
	*
	* @return
	*/
	@ApiOperation(value="后台导入EXCEL", httpMethod = "POST", notes = "后台导入EXCEL")
	@RequestMapping(value="/excel/input", method=RequestMethod.POST)
    public void inputExcel(
    		HttpServletRequest request, 
    		HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin inputExcel");
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
        MultipartFile file = mulRequest.getFile("excelFile");  
        String filename = file.getOriginalFilename();  
        if(filename == null || "".equals(filename)) {  
        	writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择导入的文件！"), response);
			logger.info("[BusiTagSetController]:end inputExcel");
			return;
        }  
        try {  
            InputStream input = file.getInputStream();
            List<String[]> list = ExcelUtils.importExcel(input);
            for (String[] strings : list) {
            	if(strings.length < 3) {
                	continue;
                }
                BusiTagSet busiTagSet = new BusiTagSet();
        		String uuid = RandomUtil.generateString(16);
        		busiTagSet.setBststUuid(uuid);
        		busiTagSet.setBststName(strings[0]);
        		busiTagSet.setBststDesc(strings[1]);
        		busiTagSet.setBststIcon(strings[2]);
        		busiTagSet.setBststCdate(new Date());
        		busiTagSet.setBststUdate(new Date());
        		busiTagSetService.insertBusiTagSet(busiTagSet);
			}  
        } catch (Exception e) {  
        	writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "导入失败！"), response);
			logger.info("[BusiTagSetController]:end inputExcel");
			return;
        }
        
        writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "导入成功!"),response);
		logger.info("[BusiTagSetController]:end inputExcel"); 
    } 
	
	/**
	* 添加
	*
	* @param bststName 标签名称
	* @param bststDesc 标签简介
	* @param bststIcon 标签图标字
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/busiTagSet", method=RequestMethod.POST)
	public void addBusiTagSet(
			@ApiParam(value="标签名称", required = true) @RequestParam(value="bststName", required = true) String bststName, 
			@ApiParam(value="标签简介", required = false) @RequestParam(value="bststDesc", required = false) String bststDesc, 
			@ApiParam(value="标签图标字", required = true) @RequestParam(value="bststIcon", required = true) String bststIcon, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin addBusiTagSet");		BusiTagSet busiTagSet = new BusiTagSet();
		String uuid = RandomUtil.generateString(16);
		busiTagSet.setBststUuid(uuid);
		busiTagSet.setBststName(bststName);
		busiTagSet.setBststDesc(bststDesc);
		busiTagSet.setBststIcon(bststIcon);
		busiTagSet.setBststCdate(new Date());
		busiTagSet.setBststUdate(new Date());
		busiTagSetService.insertBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiTagSetController]:end addBusiTagSet");
	}

	/**
	* 修改
	*
	* @param bststUuid 标识UUID
	* @param bststName 标签名称
	* @param bststDesc 标签简介
	* @param bststIcon 标签图标字
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiTagSet", method=RequestMethod.POST)
	public void updateBusiTagSet(
			@ApiParam(value="标签标识", required = true) @RequestParam(value="bststUuid", required = true) String bststUuid, 
			@ApiParam(value="标签名称", required = true) @RequestParam(value="bststName", required = true) String bststName, 
			@ApiParam(value="标签简介", required = false) @RequestParam(value="bststDesc", required = false) String bststDesc, 
			@ApiParam(value="标签图标字", required = true) @RequestParam(value="bststIcon", required = true) String bststIcon, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin updateBusiTagSet");		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);
		busiTagSet.setBststName(bststName);
		busiTagSet.setBststDesc(bststDesc);
		busiTagSet.setBststIcon(bststIcon);
		busiTagSet.setBststUdate(new Date());
		busiTagSetService.updateBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiTagSetController]:end updateBusiTagSet");
	}

	/**
	* 删除
	*
	* @param bststUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiTagSet(
			@ApiParam(value="标签标识", required = true) @RequestParam(value="bststUuid", required = true) String bststUuid, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin deleteBusiTagSet");		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);
		busiTagSetService.deleteBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiTagSetController]:end deleteBusiTagSet");
	}

	/**
	* 批量删除
	*
	* @param bststUuids UUID集合
	* @return
	*/
	@ApiOperation(value="批量删除", httpMethod = "POST", notes = "批量删除")
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiTagSet(
			@ApiParam(value="标签标识集合(|拼接)", required = true) @RequestParam(value="bststUuids", required = true) String bststUuids, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin deleteBatchBusiTagSet");		String[] uuids=bststUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiTagSetController]:end deleteBatchBusiTagSet");
			return;
		}
		busiTagSetService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiTagSetController]:end deleteBatchBusiTagSet");
	}

	/**
	* 获取单个标签
	*
	* @param bststUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个标签", httpMethod = "GET", notes = "获取单个标签", response = BusiTagSetVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiTagSet(
			@ApiParam(value="标签标识", required = true) @RequestParam(value="bststUuid", required = true) String bststUuid, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin viewsBusiTagSet");		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);
		busiTagSet = busiTagSetService.getBusiTagSet(busiTagSet);

		BusiTagSetVO busiTagSetVO = new BusiTagSetVO();
		busiTagSetVO.convertPOToVO(busiTagSet);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiTagSetVO), response);
		logger.info("[BusiTagSetController]:end viewsBusiTagSet");
	}

	/**
	* 根据标签UUID集合查询标签列表
	*
	* @param bststUuids UUID集合
	* @return
	*/
	@ApiOperation(value="根据标签UUID集合查询标签列表", httpMethod = "GET", notes = "根据标签UUID集合查询标签列表", response = BusiTagSetVO.class)
	@RequestMapping(value="/find/by/uuids", method=RequestMethod.GET)
	public void findBusiTagSetByUuids(
			@ApiParam(value="标签标识集合(|拼接)", required = true) @RequestParam(value="bststUuids", required = true) String bststUuids, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetByUuids");
		String[] uuids=bststUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择标签集合对象！"), response);
			logger.info("[BusiTagSetController]:end findBusiTagSetByUuids");
			return;
		}		
		List<BusiTagSet> tagList = busiTagSetService.findBusiTagSetByUuids(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取成功!", tagList),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetByUuids");
	}

	/**
	* 获取标签列表<List>
	* 
	* @return
	*/
	@ApiOperation(value="获取标签列表", httpMethod = "GET", notes = "获取标签列表", response = BusiTagSetVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findBusiTagSetList(
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetList");		List<BusiTagSet> lists = busiTagSetService.findBusiTagSetList();
		List<BusiTagSetVO> vos = new ArrayList<BusiTagSetVO>();
		BusiTagSetVO vo;
		for (BusiTagSet busiTagSet : lists) {
			vo = new BusiTagSetVO();			vo.convertPOToVO(busiTagSet);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetList");
	}

	/**
	* 获取标签分页列表<Page>
	* 
	* @param bststName 标签名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取标签分页列表", httpMethod = "GET", notes = "获取标签分页列表", response = BusiTagSetVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiTagSetPage(
			@ApiParam(value="标签名称", required = false) @RequestParam(value="bststName", required = false) String bststName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiTagSet> page = busiTagSetService.findBusiTagSetPage(bststName, pageNum, pageSize);
		Page<BusiTagSetVO> pageVO = new Page<BusiTagSetVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiTagSetVO> vos = new ArrayList<BusiTagSetVO>();
		BusiTagSetVO vo;
		for (BusiTagSet busiTagSet : page.getResult()) {
			vo = new BusiTagSetVO();			vo.convertPOToVO(busiTagSet);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetPage");
	}

	/**
     * 后台导出标签设置
     * 
     * @return
     */
	@ApiOperation(value="后台导出标签设置", httpMethod = "GET", notes = "后台导出标签设置")
    @RequestMapping(value = "/excel/export", method = RequestMethod.GET)
    public void exportExcel( 
    		HttpServletResponse response) throws IOException {
		logger.info("[BusiTagSetController]:begin exportExcel");
    	List<BusiTagSet> list = busiTagSetService.findBusiTagSetList();
        final Map<String, String> linkedHeader = new LinkedHashMap<>();
        linkedHeader.put("bststUnid", "标识UNID");
        linkedHeader.put("bststUuid", "标识UUID");
        linkedHeader.put("bststName", "标签名称");
        linkedHeader.put("bststDesc", "标签简介");
        linkedHeader.put("bststIcon", "标签图标字");
        linkedHeader.put("bststCdate", "创建时间");
        linkedHeader.put("bststUdate", "更新时间");

        List<String[]> contents = ExcelUtils.buildExcelList(list, new ExcelUtils.ExcelMapper<BusiTagSet>() {

            @Override
            public String[] mapHeader() {
                return toExcelHeader(linkedHeader);
            }

            @Override
            public String[] mapRowData(BusiTagSet obj) {
                return toExcelLine(obj, linkedHeader);
            }
        });
        ExcelUtils.writeXls(response, contents, "tag_set_list");
        logger.info("[BusiTagSetController]:end exportExcel");
    }

    private String[] toExcelLine(BusiTagSet obj, Map<String, String> linkedHeader) {
        List<String> list = new ArrayList<>();
        for (String key : linkedHeader.keySet()) {
        	if(key.equals("bststUnid")) {
        		 list.add(String.valueOf(obj.getBststUnid()));
        	}
        	if(key.equals("bststUuid")) {
	       		 list.add(obj.getBststUuid());
	       	}
        	if(key.equals("bststName")) {
	       		 list.add(obj.getBststName());
	       	}
        	if(key.equals("bststDesc")) {
	       		 list.add(obj.getBststDesc());
	       	}
        	if(key.equals("bststIcon")) {
	       		 list.add(obj.getBststIcon());
	       	}
        	if(key.equals("bststCdate")) {
	       		 list.add(DateUtil.formatDefaultDate(obj.getBststCdate()));
	       	}
        	if(key.equals("bststUdate")) {
	       		 list.add(DateUtil.formatDefaultDate(obj.getBststUdate()));
	       	}
        }
        return list.toArray(new String[list.size()]);
    }

    private String[] toExcelHeader(Map<String, String> linkedHeader) {
        Collection<String> titles = linkedHeader.values();
        return new ArrayList<>(titles).toArray(new String[titles.size()]);
    }

}