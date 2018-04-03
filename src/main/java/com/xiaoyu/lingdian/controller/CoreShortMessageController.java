package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.xiaoyu.lingdian.tool.BaseConstant;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.SendSMSUtil;
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
import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreShortMessageService;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.vo.CoreShortMessageVO;

@Controller
@RequestMapping(value="/coreShortMessage")
@Api(value="coreShortMessage", description="短信相关操作")
public class CoreShortMessageController extends BaseController {

	/**
	* 短信日志记录表
	*/
	@Autowired
	private CoreShortMessageService coreShortMessageService;
	
	/**
	* 系统设置表
	*/
	@Autowired
	private CoreSystemSetService coreSystemSetService;

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	 * 注册发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@ApiOperation(value="注册发送短信", httpMethod = "POST", notes = "注册发送短信")
	@RequestMapping(value="/register/send", method=RequestMethod.POST)
	public void storeRegisterSendSms(
			@ApiParam(value="手机号", required = true) @RequestParam(value="crmceMobile", required = true) String crmceMobile, 
			HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin storeRegisterSendSms");	
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}

		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null != coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 500) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制500条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		//boolean flag = false;
		//try {
		//	flag = SendSMSUtil.sendStoreRegister(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		//} catch (Exception e) {
		//	logger.info("注册发送短信失败！");
		//}
		//if (!flag) {
		//	CoreShortMessage coreShortMessage = new CoreShortMessage();		
		//	coreShortMessage.setCrmceUuid(uuid);
		//	coreShortMessage.setCrmceMobile(crmceMobile);
		//	coreShortMessage.setCrmceContent(code);
		//	coreShortMessage.setCrmceTime(date);
		//	coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		//	coreShortMessage.setCrmceStatus(0);
		//	coreShortMessage.setCrmceType(1);
		//
		//	coreShortMessageService.insertCoreShortMessage(coreShortMessage);
		//
		//	writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "注册发送验证码失败!"), response);
		//	logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
		//	return;
		//}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "注册发送验证码成功!", code),response);
		logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
	}
	
	/**
	 * 忘记密码发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@ApiOperation(value="忘记密码发送短信", httpMethod = "POST", notes = "忘记密码发送短信")
	@RequestMapping(value="/forget/send", method=RequestMethod.POST)
	public void storeForgetSendSms(
			@ApiParam(value="手机号", required = true) @RequestParam(value="crmceMobile", required = true) String crmceMobile, 
			HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin storeForgetSendSms");	
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}

		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号不存在，请先注册！"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 500) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制500条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
//		boolean flag = false;
//		try {
//			flag = SendSMSUtil.sendShareForgetPwd(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
//		} catch (Exception e) {
//			logger.info("发送忘记密码短信失败！");
//		}
//		if (!flag) {
//			CoreShortMessage coreShortMessage = new CoreShortMessage();		
//			coreShortMessage.setCrmceUuid(uuid);
//			coreShortMessage.setCrmceMobile(crmceMobile);
//			coreShortMessage.setCrmceContent(code);
//			coreShortMessage.setCrmceTime(date);
//			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
//			coreShortMessage.setCrmceStatus(0);
//			coreShortMessage.setCrmceType(1);
//
//			coreShortMessageService.insertCoreShortMessage(coreShortMessage);
//
//			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "发送忘记密码验证码失败!"), response);
//			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
//			return;
//		}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发送忘记密码验证码成功!", code),response);
		logger.info("[CoreShortMessageController]:end storeForgetSendSms");
	}

	/**
	* 获取单个短信验证码
	*
	* @param crmceUuid 标识UUID
	* @return
	*/
	@ApiOperation(value="获取单个短信验证码", httpMethod = "GET", notes = "获取单个短信验证码", response = CoreShortMessageVO.class)
	@RequestMapping(value="/views", method=RequestMethod.GET)
	public void viewsCoreShortMessage(
			@ApiParam(value="短信标识UUID", required = true) @RequestParam(value="crmceUuid", required = true) String crmceUuid,
			HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin viewsCoreShortMessage");		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(crmceUuid);
		coreShortMessage = coreShortMessageService.getCoreShortMessage(coreShortMessage);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShortMessageController]:end viewsCoreShortMessage");
			return;
		}

		CoreShortMessageVO coreShortMessageVO = new CoreShortMessageVO();
		coreShortMessageVO.convertPOToVO(coreShortMessage);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreShortMessageVO), response);
		logger.info("[CoreShortMessageController]:end viewsCoreShortMessage");
	}

	/**
	* 获取短信验证码分页列表<Page>
	* 
	* @param crmceMobile 手机号
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@ApiOperation(value="获取短信验证码分页列表", httpMethod = "GET", notes = "获取短信验证码分页列表", response = CoreShortMessageVO.class)
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.GET)
	public void findCoreShortMessagePage(
			@ApiParam(value="手机号", required = false) @RequestParam(value="crmceMobile", required = false) String crmceMobile, 
			@ApiParam(value="第几页", required = false) @RequestParam(value="pageNum", required = false) Integer pageNum, 
			@ApiParam(value="每页数量", required = false) @RequestParam(value="pageSize", required = false) Integer pageSize,
			HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin findCoreShortMessagePage");		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreShortMessage> page = coreShortMessageService.findCoreShortMessagePage(crmceMobile, pageNum, pageSize);
		Page<CoreShortMessageVO> pageVO = new Page<CoreShortMessageVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreShortMessageVO> vos = new ArrayList<CoreShortMessageVO>();

		CoreShortMessageVO vo;
		for (CoreShortMessage coreShortMessage : page.getResult()) {
			vo = new CoreShortMessageVO();			vo.convertPOToVO(coreShortMessage);			
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreShortMessageController]:end findCoreShortMessagePage");
	}

}