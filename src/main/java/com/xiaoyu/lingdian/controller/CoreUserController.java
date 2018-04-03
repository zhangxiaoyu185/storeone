package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.service.CoreShortMessageService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.encrypt.MD5Util;
import com.xiaoyu.lingdian.tool.http.HttpUrlConnectionUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.CoreUserVO;

@Controller
@RequestMapping(value="/coreUser")
@Api(value="coreUser", description="用户相关操作")
public class CoreUserController extends BaseController {

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 短信日志记录表
	*/
	@Autowired
	private CoreShortMessageService coreShortMessageService;
	
	/**
	* 获取号码归属地
	*
	* @param mobileTel 手机号码
	* @return
	*/
	@ApiOperation(value="获取号码归属地", httpMethod = "GET", notes = "获取号码归属地")
	@RequestMapping(value="/mobile/attach", method=RequestMethod.GET)
	public void getMobileAttach(
			@ApiParam(value="手机号", required = true) @RequestParam(value="mobileTel", required = true) String mobileTel, 
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin getMobileAttach");
		if (!StringUtil.isMobile(mobileTel)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码不符合规范!"), response);
			logger.info("[CoreUserController]:end getMobileAttach");
			return;
		}
		
		String requestUrl = "https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=" + mobileTel;
		String message = "号段不存在";
		String strReturn = HttpUrlConnectionUtil.sendGet(requestUrl, null);
		if (!StringUtil.isEmpty(strReturn)) {
			strReturn = strReturn.replace("/*fgg_again*/phone(", "").replace(")", "");
			JSONObject jsonObject = (JSONObject) JSONObject.parse(strReturn);
			JSONObject addressObject = (JSONObject) jsonObject.get("data");
			message = addressObject.getString("area_operator");
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取号码归属地成功", message),response);
		logger.info("[CoreUserController]:end getMobileAttach");
	}
	
	/**
	 * 忘记密码
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param response
	 */
	@ApiOperation(value="忘记密码", httpMethod = "POST", notes = "忘记密码")
	@RequestMapping(value="/forget", method=RequestMethod.POST)
	public void forget(
			@ApiParam(value="手机号", required = true) @RequestParam(value="mobile", required = true) String mobile, 
			@ApiParam(value="密码", required = true) @RequestParam(value="password", required = true) String password, 
			@ApiParam(value="确认密码", required = true) @RequestParam(value="confirmPassword", required = true) String confirmPassword, 
			@ApiParam(value="验证码", required = true) @RequestParam(value="code", required = true) String code, 
			HttpServletResponse response) {
		logger.info("[CoreUserController.forget]:begin forget");
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		
		if(coreUserService.getCoreUserByMobile(mobile) == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号未注册,请直接注册！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.forget]:end forget");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrMobile(mobile);	
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrUdate(date);
		coreUserService.updateCoreUserByMobile(coreUser);		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "忘记密码修改成功！"), response);
		logger.info("[CoreUserController.forget]:end forget");
	}	

	/**
	 * 注册
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param response
	 */
	@ApiOperation(value="注册", httpMethod = "POST", notes = "注册", response = CoreUserVO.class)
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public void register(
			@ApiParam(value="手机号", required = true) @RequestParam(value="mobile", required = true) String mobile,
			@ApiParam(value="密码", required = true) @RequestParam(value="password", required = true) String password,
			@ApiParam(value="确认密码", required = true) @RequestParam(value="confirmPassword", required = true) String confirmPassword,
			@ApiParam(value="短信验证码", required = true) @RequestParam(value="code", required = true) String code,
			HttpServletResponse response) {
		logger.info("[CoreUserController.register]:begin register");
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}			
		if(coreUserService.getCoreUserByMobile(mobile) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.register]:end register");
			return;
		}

		CoreUser coreUser = new CoreUser();
		String uuid = RandomUtil.generateString(16);
		coreUser.setCrusrUuid(uuid);
		coreUser.setCrusrCdate(date);
		coreUser.setCrusrUdate(date);
		coreUser.setCrusrName(mobile);
		coreUser.setCrusrMobile(mobile);
		coreUser.setCrusrMobileVerify(1);
		coreUser.setCrusrCode(mobile);
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrGender(1);
		coreUser.setCrusrFee(0.0);
		coreUserService.insertCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "注册成功！", coreUser), response);
		logger.info("[CoreUserController.register]:end register");
	}
	
	/**
	 * 登录
	 * 
	 * @param mobile
	 * @param password
	 * @param response
	 */
	@ApiOperation(value="登录", httpMethod = "POST", notes = "登录", response = CoreUserVO.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(
			@ApiParam(value="手机号", required = true) @RequestParam(value="mobile", required = true) String mobile,
			@ApiParam(value="密码", required = true) @RequestParam(value="password", required = true) String password,
			HttpServletResponse response) {
		logger.info("[CoreUserController.login]:begin login.");
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.login]:end login");
			return;
		}
		
		CoreUser coreUser = coreUserService.getCoreUserByMobile(mobile);

		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		if(coreUser == null || !md5PWD.equalsIgnoreCase(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户名或密码错误！"), response);
			logger.info("[CoreUserController.login]:end login");
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "登录成功", coreUser), response);
		logger.info("[CoreUserController.login]:end login.");
	}
	
	/**
	* 启用
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="启用", httpMethod = "POST", notes = "启用")
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid,
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin enable");		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrUdate(new Date());

		coreUserService.updateCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[CoreUserController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="禁用", httpMethod = "POST", notes = "禁用")
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid,
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin disable");
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrStatus(0);
		coreUser.setCrusrUdate(new Date());

		coreUserService.updateCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[CoreUserController]:end disable");
	}

	/**
	 * 重置密码
	 * 
	 * @param crusrUuid
	 * @param response
	 */
	@ApiOperation(value="重置密码", httpMethod = "POST", notes = "重置密码")
	@RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
	public void resetPwd(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid,
			HttpServletResponse response) {
		logger.info("[CoreUserController.resetPwd]:begin resetPwd.");	
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		String newMd5PWD = MD5Util.encode("123456", Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(newMd5PWD);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "重置密码成功！"), response);
		logger.info("[CoreUserController.resetPwd]:end resetPwd.");
	}
	
	/**
	 * 修改密码
	 * 
	 * @param crusrUuid
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @param response
	 */
	@ApiOperation(value="修改密码", httpMethod = "POST", notes = "修改密码")
	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
	public void updatePwd(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid,
			@ApiParam(value="旧密码", required = true) @RequestParam(value="oldPwd", required = true) String oldPwd,
			@ApiParam(value="新密码", required = true) @RequestParam(value="newPwd", required = true) String newPwd,
			@ApiParam(value="确认新密码", required = true) @RequestParam(value="confirmPwd", required = true) String confirmPwd,
			HttpServletResponse response) {
		logger.info("[CoreUserController.updatePwd]:begin updatePwd.");
		if (!newPwd.equals(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码输入不一致！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);		
		String oldMd5PWD = MD5Util.encode(oldPwd, Constant.DEFAULT_CHARSET);	
		if(coreUser == null || !oldMd5PWD.equals(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "原密码错误！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}
		String newMd5PWD = MD5Util.encode(newPwd, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(newMd5PWD);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改密码成功！"), response);
		logger.info("[CoreUserController.updatePwd]:end updatePwd.");
	}

	/**
	* 修改个人信息
	*
	* @param crusrUuid 标识UUID
	* @param crusrName 帐户名称
	* @param crusrCode 真实姓名
	* @param crusrHead 头像附件
	* @param crusrProvince 居住省份
	* @param crusrProvinceName 居住省份名称
	* @param crusrCity 居住城市
	* @param crusrCityName 居住城市名称
	* @param crusrDistrict 居住区域
	* @param crusrDistrictName 居住区域名称
	* @param crusrAddress 详细地址
	* @param crusrEmail 电子邮箱
	* @param crusrMobile 手机号码
	* @param crusrBirthday 生日
	* @param crusrGender 性别:1男,2女,0其它
	* @param crusrRemarks 备注
	* @return
	*/
	@ApiOperation(value="修改个人信息", httpMethod = "POST", notes = "修改个人信息", response = CoreUserVO.class)
	@RequestMapping(value="/update/coreUser", method=RequestMethod.POST)
	public void updateCoreUser(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid,
			@ApiParam(value="帐户名称", required = true) @RequestParam(value="crusrName", required = true) String crusrName,
			@ApiParam(value="真实姓名", required = false) @RequestParam(value="crusrCode", required = false) String crusrCode,
			@ApiParam(value="头像附件", required = false) @RequestParam(value="crusrHead", required = false) String crusrHead,
			@ApiParam(value="居住省份", required = false) @RequestParam(value="crusrProvince", required = false) String crusrProvince,
			@ApiParam(value="居住省份名称", required = false) @RequestParam(value="crusrProvinceName", required = false) String crusrProvinceName,
			@ApiParam(value="居住城市", required = false) @RequestParam(value="crusrCity", required = false) String crusrCity,
			@ApiParam(value="居住城市名称", required = false) @RequestParam(value="crusrCityName", required = false) String crusrCityName,
			@ApiParam(value="居住区域", required = false) @RequestParam(value="crusrDistrict", required = false) String crusrDistrict,
			@ApiParam(value="居住区域名称", required = false) @RequestParam(value="crusrDistrictName", required = false) String crusrDistrictName,
			@ApiParam(value="详细地址", required = false) @RequestParam(value="crusrAddress", required = false) String crusrAddress,
			@ApiParam(value="电子邮箱", required = false) @RequestParam(value="crusrEmail", required = false) String crusrEmail,
			@ApiParam(value="手机号码", required = true) @RequestParam(value="crusrMobile", required = true) String crusrMobile,
			@ApiParam(value="生日(yyyy-MM-dd)", required = false) @RequestParam(value="crusrBirthday", required = false) String crusrBirthday,
			@ApiParam(value="性别:1男,2女,0其它", required = true) @RequestParam(value="crusrGender", required = true) Integer crusrGender,
			@ApiParam(value="备注", required = false) @RequestParam(value="crusrRemarks", required = false) String crusrRemarks,
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin updateCoreUser");
		if (!StringUtil.isPhone(crusrMobile)) {
			if (!StringUtil.isMobile(crusrMobile)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码格式不对!"), response);
				logger.info("[CoreUserController]:end updateCoreUser");
				return;
			}
		}
		if (!StringUtil.isEmpty(crusrEmail)) {
			if (!StringUtil.isEmail(crusrEmail)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "电子邮箱格式不对!"), response);
				logger.info("[CoreUserController]:end updateCoreUser");
				return;
			}
		}
		CoreUser user = coreUserService.getCoreUserByName(crusrName);
		if (null != user && !user.getCrusrName().equals(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该账户名称已存在!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrName(crusrName);
		coreUser.setCrusrCode(crusrCode);
		coreUser.setCrusrHead(crusrHead);
		coreUser.setCrusrProvince(crusrProvince);
		coreUser.setCrusrProvinceName(crusrProvinceName);
		coreUser.setCrusrCity(crusrCity);
		coreUser.setCrusrCityName(crusrCityName);
		coreUser.setCrusrDistrict(crusrDistrict);
		coreUser.setCrusrDistrictName(crusrDistrictName);
		coreUser.setCrusrAddress(crusrAddress);
		coreUser.setCrusrEmail(crusrEmail);
		coreUser.setCrusrMobile(crusrMobile);
		coreUser.setCrusrCdate(new Date());
		coreUser.setCrusrUdate(new Date());
		coreUser.setCrusrBirthday(crusrBirthday);
		coreUser.setCrusrGender(crusrGender);
		coreUser.setCrusrRemarks(crusrRemarks);

		coreUserService.updateCoreUser(coreUser);

		coreUser = coreUserService.getCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改个人信息成功!", coreUser),response);
		logger.info("[CoreUserController]:end updateCoreUser");
	}

	/**
	* 获取个人信息
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取个人信息", httpMethod = "GET", notes = "获取个人信息", response = CoreUserVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsCoreUser(
			@ApiParam(value="用户标识UUID", required = true) @RequestParam(value="crusrUuid", required = true) String crusrUuid, 
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin viewsCoreUser");		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[CoreUserController]:end viewsCoreUser");
			return;
		}
		CoreUserVO coreUserVO = new CoreUserVO();
		coreUserVO.convertPOToVO(coreUser);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取个人信息成功", coreUserVO), response);
		logger.info("[CoreUserController]:end viewsCoreUser");
	}

	/**
	* 获取列表<List>
	* @param crusrName
	* @return
	*/
	@ApiOperation(value="获取用户列表", httpMethod = "GET", notes = "获取用户列表", response = CoreUserVO.class)
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public void findCoreUserList(
			@ApiParam(value="用户昵称", required = false) @RequestParam(value="crusrName", required = false) String crusrName, 
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserList");		List<CoreUser> lists = coreUserService.findCoreUserList(crusrName, null);
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : lists) {
			vo = new CoreUserVO();			vo.convertPOToVO(coreUser);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreUserController]:end findCoreUserList");
	}

	/**
	* 获取分页列表<Page>
	* 
	* @param crusrName
	* @param crusrMobile
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取用户分页列表", httpMethod = "GET", notes = "获取用户分页列表", response = CoreUserVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findCoreUserPage(
			@ApiParam(value="用户昵称", required = false) @RequestParam(value="crusrName", required = false) String crusrName, 
			@ApiParam(value="手机号", required = false) @RequestParam(value="crusrMobile", required = false) String crusrMobile, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize, 
			HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserPage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreUser> page = coreUserService.findCoreUserPage(crusrName, crusrMobile, pageNum, pageSize);
		Page<CoreUserVO> pageVO = new Page<CoreUserVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : page.getResult()) {
			vo = new CoreUserVO();
			vo.convertPOToVO(coreUser);
			vos.add(vo);
		}
		pageVO.setResult(vos);
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreUserController]:end findCoreUserPage");
	}

}