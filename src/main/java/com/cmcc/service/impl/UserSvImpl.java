package com.cmcc.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.cmcc.bean.Result;
import com.cmcc.bean.User;
import com.cmcc.config.Config;
import com.cmcc.mapper.UserMapper;
import com.cmcc.utils.WechatUtils;

import redis.clients.jedis.Jedis;


@Service
public class UserSvImpl implements UserMapper {
	
	int status = 0;
	String message = null;
	String nickname = null;
	String headimgurl = null;
	String openid = null;
	String telnum=null;
	Date subscribeTime = null;
	
	@Autowired
	private UserMapper userMapper;
	public void insert(User user) {
		 userMapper.insert(user);
		
	}

	public void update(User user) {
		userMapper.update(user);
		
	}

	public User selectById(int id) {
		return userMapper.selectById(id);
	}
	
	
	/**
	 * 获取用户信息包括手机号
	 * @param accountId
	 * @param code
	 * @return
	 */
	public Result getUserInfo(String accountId,String code) {
		Result result = new Result();
		String url = "http://221.176.66.251/operation/api/out/getFansInfoByOauth2?accountId="+Config.ACCOUNT_ID+"&code="+code+"&lang=zh_CN";
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		// 永远返回status and  message 
		int status = jsonObject.getIntValue("status");
		String message = jsonObject.getString("message");
		result.setStatus(status);
		result.setMessage(message);
		
		
		if (status == 3001) {
			String openid = jsonObject.getString("openid");
			result.setOpenid(openid);
		}
		if (status == 1000 || status == 1001) {
			String nickname = jsonObject.getString("nickname");
			String headimgurl = jsonObject.getString("headimgurl");
			String openid = jsonObject.getString("openid");
			String telnum = jsonObject.getString("telnum");
			Date subscribeTime = jsonObject.getDate("subscribeTime");
			
			result.setHeadimgurl(headimgurl);
			result.setNickname(nickname);
			result.setOpenid(openid);
			result.setTelnum(telnum);
			result.setSubscribeTime(subscribeTime);
		}
		return result;
	}
    
	
	/**
	 * 获取用户信息，包含手机号 归属地
	 * @param accountId
	 * @param code
	 * @return
	 */
	
	public Result getUserInfo2(String accountId,String code) {
		
		Result result = new Result();
		String url = "http://221.176.66.251/operation/api/out/v2/getFansInfoByOauth2?accountId="+Config.ACCOUNT_ID+"&code="+code+"&lang=zh_CN";
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		// 永远返回status and  message 
		int status = jsonObject.getIntValue("status");
		String message = jsonObject.getString("message");
		result.setStatus(status);
		result.setMessage(message);
		
		
		if (status == 3001) {
			String openid = jsonObject.getString("openid");
			result.setOpenid(openid);
		}
		if (status == 0000) {
			String nickname = jsonObject.getString("nickName");
			String headimgurl = jsonObject.getString("headImgurl");
			String openid = jsonObject.getString("openId");
			String telnum = jsonObject.getString("telNum");
			String provinceCode = jsonObject.getString("provinceCode");
			String cityCode = jsonObject.getString("cityCode");
			
			result.setHeadimgurl(headimgurl);
			result.setNickname(nickname);
			result.setOpenid(openid);
			result.setTelnum(telnum);
			result.setCityCode(cityCode);
			result.setProvinceCode(provinceCode);
		}
		return result;
	}
	
	
	/**
	 * 获取用户信息，不包含手机号
	 * @param accountId
	 * @param code
	 * @return
	 */
	
	public Result getUserInfo3(String accountId,String code) {
		
		Result result = new Result();
		String url = "http://221.176.66.251/operation/api/out/v3/getFansInfoByOauth2?accountId="+Config.ACCOUNT_ID+"&code="+code+"&lang=zh_CN";
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		// 永远返回status and  message 
		int status = jsonObject.getIntValue("status");
		String message = jsonObject.getString("message");
		result.setStatus(status);
		result.setMessage(message);
		
		
		if (status == 3001) {
			String openid = jsonObject.getString("openid");
			result.setOpenid(openid);
		}
		if (status == 0000) {
			String nickname = jsonObject.getString("nickName");
			String headimgurl = jsonObject.getString("headImgurl");
			String openid = jsonObject.getString("openId");
			String provinceCode = jsonObject.getString("provinceCode");
			String cityCode = jsonObject.getString("cityCode");
			int phoneStatus = jsonObject.getIntValue("phoneStatus");
			
			result.setHeadimgurl(headimgurl);
			result.setNickname(nickname);
			result.setOpenid(openid);
			result.setTelnum(telnum);
			result.setCityCode(cityCode);
			result.setProvinceCode(provinceCode);
			result.setPhoneStatus(phoneStatus);
		}
		return result;
	}
	
	/**
	 * 获取用户信息，包含上一次关注 和 绑定时间
	 * @param accountId
	 * @param code
	 * @return
	 */
	
	public Result getUserInfo4(String accountId,String code) {
		
		Result result = new Result();
		
		//TODO ,signature 并没有设置
		String url = "http://221.176.66.251/api/out/fans/?code="+code+"&appId=APPID&timestamp=1481166600023&nonce=572490&signature=SIGNATURE";
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		// 永远返回status and  message 
		String id = jsonObject.getString("id");
		int status = jsonObject.getIntValue("status");
		String message = jsonObject.getString("message");
		JSONObject data= jsonObject.getJSONObject("data");
		
		
	/*	String openid = data.getString("openid");
		String nickname = data.getString("nickname");
		String headimgurl = data.getString("headimgurl");
	    String sex = data.getString("sex");
	    String city_code = data.getString("city_code");
	    String city = data.getString("city");
	    String groupid= data.getString("groupid");
	    String phoneStatus= data.getString("phoneStatus");
	    String province_code= data.getString("province_code");
	    String province= data.getString("province");
	    String ranking= data.getString("ranking");
	    String subscribe= data.getString("subscribe");
	    String telephone= data.getString("telephone");*/
	    int bindPhoneTime= data.getIntValue("bindPhoneTime");
	    int subscribe_timeloc= data.getIntValue("subscribe_timeloc");
	    int unBindPhoneTime= data.getIntValue("unBindPhoneTime");
	    int unsubscribe_time= data.getIntValue("unsubscribe_time");
	   
	    
	    result.setId(id);
	    result.setStatus(status);
	    result.setMessage(message);
	    result.setBindPhoneTime(unBindPhoneTime);
	    result.setSubscribe_timeloc(subscribe_timeloc);
	    result.setUnBindPhoneTime(unBindPhoneTime);
	    result.setUnsubscribe_time(unsubscribe_time);

		return result;
	}
	
	public void saveResult() {
		   Jedis jedis = new Jedis("192.168.248.130",6379);
	       jedis.auth("123456");
	       jedis.set("runoobkey", "www.runoob.com");
		
		
		
	}

}
