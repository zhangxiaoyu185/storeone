package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.RandomUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiShippingAddress;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.BusiShippingAddressService;
import com.xiaoyu.lingdian.vo.BusiShippingAddressVO;

@Controller
@RequestMapping(value="/busiShippingAddress")
@Api(value="busiShippingAddress", description="收货地址相关操作")
public class BusiShippingAddressController extends BaseController {

	/**
	* 收货地址表
	*/
	@Autowired
	private BusiShippingAddressService busiShippingAddressService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 添加
	*
	* @param bssasUser 所属用户
	* @param bssasName 收货人姓名
	* @param bssasMobile 收货人联系方式
	* @param bsarsZipCode 邮政编码
	* @param bssasProvince 所在省
	* @param bssasProvinceName 所在省名称
	* @param bssasCity 所在市
	* @param bssasCityName 所在市名称
	* @param bssasCounty 所在区
	* @param bssasCountyName 所在区名称
	* @param bssasAddress 详细地址
	* @return
	*/
	@ApiOperation(value="添加", httpMethod = "POST", notes = "添加")
	@RequestMapping(value="/add/busiShippingAddress", method=RequestMethod.POST)
	public void addBusiShippingAddress(
			@ApiParam(value="所属用户", required = true) @RequestParam(value="bssasUser", required = true) String bssasUser, 
			@ApiParam(value="收货人姓名", required = true) @RequestParam(value="bssasName", required = true) String bssasName, 
			@ApiParam(value="收货人联系方式", required = true) @RequestParam(value="bssasMobile", required = true) String bssasMobile, 
			@ApiParam(value="邮政编码", required = false) @RequestParam(value="bsarsZipCode", required = false) String bsarsZipCode, 
			@ApiParam(value="所在省", required = true) @RequestParam(value="bssasProvince", required = true) String bssasProvince, 
			@ApiParam(value="所在省名称", required = true) @RequestParam(value="bssasProvinceName", required = true) String bssasProvinceName, 
			@ApiParam(value="所在市", required = true) @RequestParam(value="bssasCity", required = true) String bssasCity, 
			@ApiParam(value="所在市名称", required = true) @RequestParam(value="bssasCityName", required = true) String bssasCityName, 
			@ApiParam(value="所在区", required = true) @RequestParam(value="bssasCounty", required = true) String bssasCounty, 
			@ApiParam(value="所在区名称", required = true) @RequestParam(value="bssasCountyName", required = true) String bssasCountyName, 
			@ApiParam(value="详细地址", required = true) @RequestParam(value="bssasAddress", required = true) String bssasAddress, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin addBusiShippingAddress");		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		String uuid = RandomUtil.generateString(16);
		busiShippingAddress.setBssasUuid(uuid);
		busiShippingAddress.setBssasUser(bssasUser);
		busiShippingAddress.setBssasName(bssasName);
		busiShippingAddress.setBssasMobile(bssasMobile);
		busiShippingAddress.setBsarsZipCode(bsarsZipCode);
		busiShippingAddress.setBssasProvince(bssasProvince);
		busiShippingAddress.setBssasProvinceName(bssasProvinceName);
		busiShippingAddress.setBssasCity(bssasCity);
		busiShippingAddress.setBssasCityName(bssasCityName);
		busiShippingAddress.setBssasCounty(bssasCounty);
		busiShippingAddress.setBssasCountyName(bssasCountyName);
		busiShippingAddress.setBssasAddress(bssasAddress);
		busiShippingAddress.setBsarsDefault(2);

		busiShippingAddressService.insertBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
	}

	/**
	* 修改
	*
	* @param bssasUuid 标识UUID
	* @param bssasName 收货人姓名
	* @param bssasMobile 收货人联系方式
	* @param bsarsZipCode 邮政编码
	* @param bssasProvince 所在省
	* @param bssasProvinceName 所在省名称
	* @param bssasCity 所在市
	* @param bssasCityName 所在市名称
	* @param bssasCounty 所在区
	* @param bssasCountyName 所在区名称
	* @param bssasAddress 详细地址
	* @return
	*/
	@ApiOperation(value="修改", httpMethod = "POST", notes = "修改")
	@RequestMapping(value="/update/busiShippingAddress", method=RequestMethod.POST)
	public void updateBusiShippingAddress( 
			@ApiParam(value="收货地址UUID标识", required = true) @RequestParam(value="bssasUuid", required = true) String bssasUuid, 
			@ApiParam(value="收货人姓名", required = true) @RequestParam(value="bssasName", required = true) String bssasName, 
			@ApiParam(value="收货人联系方式", required = true) @RequestParam(value="bssasMobile", required = true) String bssasMobile, 
			@ApiParam(value="邮政编码", required = false) @RequestParam(value="bsarsZipCode", required = false) String bsarsZipCode, 
			@ApiParam(value="所在省", required = true) @RequestParam(value="bssasProvince", required = true) String bssasProvince, 
			@ApiParam(value="所在省名称", required = true) @RequestParam(value="bssasProvinceName", required = true) String bssasProvinceName, 
			@ApiParam(value="所在市", required = true) @RequestParam(value="bssasCity", required = true) String bssasCity, 
			@ApiParam(value="所在市名称", required = true) @RequestParam(value="bssasCityName", required = true) String bssasCityName, 
			@ApiParam(value="所在区", required = true) @RequestParam(value="bssasCounty", required = true) String bssasCounty, 
			@ApiParam(value="所在区名称", required = true) @RequestParam(value="bssasCountyName", required = true) String bssasCountyName, 
			@ApiParam(value="详细地址", required = true) @RequestParam(value="bssasAddress", required = true) String bssasAddress, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateBusiShippingAddress");		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBssasName(bssasName);
		busiShippingAddress.setBssasMobile(bssasMobile);
		busiShippingAddress.setBsarsZipCode(bsarsZipCode);
		busiShippingAddress.setBssasProvince(bssasProvince);
		busiShippingAddress.setBssasProvinceName(bssasProvinceName);
		busiShippingAddress.setBssasCity(bssasCity);
		busiShippingAddress.setBssasCityName(bssasCityName);
		busiShippingAddress.setBssasCounty(bssasCounty);
		busiShippingAddress.setBssasCountyName(bssasCountyName);
		busiShippingAddress.setBssasAddress(bssasAddress);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
	}

	/**
	 * 点击取消默认
	 * @param bssasUuid
	 * @return
	 */
	@ApiOperation(value="点击取消默认", httpMethod = "POST", notes = "点击取消默认")
	@RequestMapping(value="/update/cancel/default", method=RequestMethod.POST)
	public void updateCancelDefault(
			@ApiParam(value="点击的收货地址UUID标识", required = true) @RequestParam(value="bssasUuid", required = true) String bssasUuid, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateCancelDefault");
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBsarsDefault(2);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "取消默认成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateCancelDefault");
	}
	
	/**
	 * 点击默认
	 * @param bssasUuid
	 * @param bssasUser
	 * @return
	 */
	@ApiOperation(value="点击默认", httpMethod = "POST", notes = "点击默认")
	@RequestMapping(value="/update/default", method=RequestMethod.POST)
	public void updateDefault(
			@ApiParam(value="点击的收货地址UUID标识", required = true) @RequestParam(value="bssasUuid", required = true) String bssasUuid, 
			@ApiParam(value="收货地址所属用户", required = true) @RequestParam(value="bssasUser", required = true) String bssasUser, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateDefault");
		//先全部更新为不默认
		busiShippingAddressService.updateBatchBusiShippingAddressByIds(bssasUser, null);
		
		//再更新当前为默认
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBsarsDefault(1);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "更新成默认成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateDefault");
	}
	
	/**
	* 删除
	*
	* @param bssasUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="删除", httpMethod = "POST", notes = "删除")
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiShippingAddress(
			@ApiParam(value="收货地址UUID标识", required = true) @RequestParam(value="bssasUuid", required = true) String bssasUuid,
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin deleteBusiShippingAddress");		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);

		busiShippingAddressService.deleteBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiShippingAddressController]:end deleteBusiShippingAddress");
	}

	/**
	* 获取单个
	*
	* @param bssasUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个收货地址", httpMethod = "GET", notes = "获取单个收货地址", response = BusiShippingAddressVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsBusiShippingAddress(
			@ApiParam(value="收货地址UUID标识", required = true) @RequestParam(value="bssasUuid", required = true) String bssasUuid,
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin viewsBusiShippingAddress");		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);

		busiShippingAddress = busiShippingAddressService.getBusiShippingAddress(busiShippingAddress);

		BusiShippingAddressVO busiShippingAddressVO = new BusiShippingAddressVO();
		busiShippingAddressVO.convertPOToVO(busiShippingAddress);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiShippingAddressVO), response);
		logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddress");
	}

	/**
	* 查询我的默认收货地址
	*
	* @param bssasUser 用户标识
	* @return
	*/
	@ApiOperation(value="查询我的默认收货地址", httpMethod = "GET", notes = "查询我的默认收货地址", response = BusiShippingAddressVO.class)
	@RequestMapping(value="/views/my/default", method=RequestMethod.GET)
	public void viewsBusiShippingAddressMyDefault(
			@ApiParam(value="所属用户", required = true) @RequestParam(value="bssasUser", required = true) String bssasUser, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin viewsBusiShippingAddressMyDefault");
		BusiShippingAddress busiShippingAddress = busiShippingAddressService.getBusiShippingAddressDefault(bssasUser);
		if (null == busiShippingAddress) {
			List<BusiShippingAddress> lists = busiShippingAddressService.findBusiShippingAddressForListsByMy(bssasUser);
			if (null == lists || lists.size() <= 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 2, "没有收货地址"), response);
				logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddressMyDefault");
				return;
			} else {
				busiShippingAddress = lists.get(0);
			}
		}
		BusiShippingAddressVO busiShippingAddressVO = new BusiShippingAddressVO();
		busiShippingAddressVO.convertPOToVO(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取我的默认收货地址成功", busiShippingAddressVO), response);
		logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddressMyDefault");
	}

	/**
	 * 我的收货地址
	 * 
	 * @param bssasUser 用户标识
	 * @param response
	 */
	@ApiOperation(value="我的收货地址列表", httpMethod = "GET", notes = "我的收货地址列表", response = BusiShippingAddressVO.class)
	@RequestMapping(value = "/find/my/shipping/address", method = RequestMethod.GET)
	public void findMyShippingAddress(
			@ApiParam(value="所属用户", required = true) @RequestParam(value="bssasUser", required = true) String bssasUser, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController.findMyShippingAddress]:begin findMyShippingAddress.");
		List<BusiShippingAddress> lists = busiShippingAddressService.findBusiShippingAddressForListsByMy(bssasUser);
		List<BusiShippingAddressVO> vos = new ArrayList<BusiShippingAddressVO>();
		BusiShippingAddressVO vo;
		for (BusiShippingAddress busiShippingAddress : lists) {
			vo = new BusiShippingAddressVO();
			vo.convertPOToVO(busiShippingAddress);
			vos.add(vo);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "查询我的收货地址成功", vos), response);
		logger.info("[BusiShippingAddressController.findMyShippingAddress]:end findMyShippingAddress.");
	}

	/**
	* 后台获取所有收货地址列表<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="后台获取收货地址分页列表", httpMethod = "GET", notes = "后台获取收货地址分页列表", response = BusiShippingAddressVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findBusiShippingAddressPage(
			@ApiParam(value="用户昵称", required = false) @RequestParam(value="userName", required = true) String userName, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = true) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = true) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin findBusiShippingAddressPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiShippingAddress> page = busiShippingAddressService.findBusiShippingAddressForPages(userName, pageNum, pageSize);
		Page<BusiShippingAddressVO> pageVO = new Page<BusiShippingAddressVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiShippingAddress busiShippingAddress : page.getResult()) {
			hashUserUuids.add(busiShippingAddress.getBssasUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiShippingAddressVO> vos = new ArrayList<BusiShippingAddressVO>();
		BusiShippingAddressVO vo;
		for (BusiShippingAddress busiShippingAddress : page.getResult()) {
			vo = new BusiShippingAddressVO();
			vo.convertPOToVO(busiShippingAddress);
			vo.setBssasUserName(userMap.get(busiShippingAddress.getBssasUser())==null?null:userMap.get(busiShippingAddress.getBssasUser()).getCrusrName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiShippingAddressController]:end findBusiShippingAddressPage");
	}

}