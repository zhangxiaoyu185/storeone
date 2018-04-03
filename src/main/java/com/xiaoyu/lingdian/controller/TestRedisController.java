package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletResponse;

import com.xiaoyu.lingdian.core.cache.model.RedisKeyAndValue;
import com.xiaoyu.lingdian.core.cache.service.DataRedisCacheService;
import com.xiaoyu.lingdian.core.cache.service.RedisCacheService;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;

@Controller
@RequestMapping(value="/redisTest")
@Api(value="redisTest", description="redis测试相关操作")
public class TestRedisController extends BaseController {

	@Autowired
	private RedisCacheService redisCacheService;
	
	@Autowired
	private DataRedisCacheService dataRedisCacheService;
	
	/**
	* 添加
	* @param key
	* @param value
	* @return
	*/
	@ApiOperation(value="redis设置键值对1", httpMethod = "POST", notes = "redis设置键值对1")
	@RequestMapping(value="/set", method=RequestMethod.POST)
	public void set(
			@ApiParam(value="redis的key", required = true) @RequestParam(value="key", required = true) String key, 
			@ApiParam(value="redis的value", required = true) @RequestParam(value="value", required = true) String value,
			HttpServletResponse response) {
		logger.info("[TestRedisController]:begin set");		
		RedisKeyAndValue redisKeyAndValue = new RedisKeyAndValue(key, value, 1000l);
		redisCacheService.set(redisKeyAndValue);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "设置成功!"),response);
		logger.info("[TestRedisController]:end set");
	}	

	/**
	* 获取
	* @return
	*/
	@ApiOperation(value="redis获取值1", httpMethod = "GET", notes = "redis获取值1")
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public void get(
			@ApiParam(value="redis的key", required = true) @RequestParam(value="key", required = true) String key,
			HttpServletResponse response) {
		logger.info("[TestRedisController]:begin get");
		RedisKeyAndValue redisKeyAndValue = redisCacheService.get(key);		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取成功", redisKeyAndValue), response);
		logger.info("[TestRedisController]:end get");
	}

	/**
	* 添加1
	* @param key
	* @param value
	* @return
	*/
	@ApiOperation(value="redis设置键值对2", httpMethod = "POST", notes = "redis设置键值对2")
	@RequestMapping(value="/set1", method=RequestMethod.POST)
	public void set1(
			@ApiParam(value="redis的key", required = true) @RequestParam(value="key", required = true) String key, 
			@ApiParam(value="redis的value", required = true) @RequestParam(value="value", required = true) String value,
			HttpServletResponse response) {
		logger.info("[TestRedisController]:begin set1");		
		dataRedisCacheService.setSingleData(key, value);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "设置成功!"),response);
		logger.info("[TestRedisController]:end set1");
	}	

	/**
	* 获取1
	* @return
	*/
	@ApiOperation(value="redis获取值2", httpMethod = "GET", notes = "redis获取值2")
	@RequestMapping(value="/get1", method=RequestMethod.GET)
	public void get1(
			@ApiParam(value="redis的key", required = true) @RequestParam(value="key", required = true) String key,
			HttpServletResponse response) {
		logger.info("[TestRedisController]:begin get1");
		String value = (String) dataRedisCacheService.getSingleValueByKey(key);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取成功", value), response);
		logger.info("[TestRedisController]:end get1");
	}
	
}