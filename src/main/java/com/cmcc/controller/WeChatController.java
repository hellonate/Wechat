package com.cmcc.controller;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cmcc.bean.Detail;
import com.cmcc.bean.User;
import com.cmcc.config.Config;
import com.cmcc.service.impl.DetailSvImpl;
import com.cmcc.service.impl.UserSvImpl;
import com.cmcc.utils.WechatUtils;

@Controller
@RequestMapping(value="/wechat")
public class WeChatController {
	@Autowired
	UserSvImpl UserSvImpl;
	
	@Autowired
	DetailSvImpl detailSvImpl;
	
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public void Login(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
		//进入授权页面，然后跳转到回调页面
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.Test_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		resp.sendRedirect(url);
	}
	
	
	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public ModelAndView getCode(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		
		Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		//WechatUtils.saveToProperty(detail.getAccessToken());
		// get UserInfo
		User user =  WechatUtils.getUserInfo(detail);
		//save user
		//UserSvImpl.insert(user);
		mav.addObject(user);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value="/getCode2",method=RequestMethod.GET)
	public ModelAndView getCode2(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		//第一次获取
		if (WechatUtils.getProperty("access_token").equals("0")) {
			// get accessToken and OpenId
			Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
			//save detail
			WechatUtils.saveToProperty(detail.getAccessToken());
			detailSvImpl.insert(detail);
			// get UserInfo
			User user =  WechatUtils.getUserInfo(detail);
			//save user 
			UserSvImpl.insert(user);
			mav.addObject(user);
			mav.setViewName("result");
			return mav;
		}
		
		//过期
		if (!WechatUtils.validateAccessToken()) {
			// get refreshToken
			String refreshToken = detailSvImpl.selectById(1).getRefresh_token();
			Detail detail = WechatUtils.reObtainAccessToken(refreshToken);
			WechatUtils.saveToProperty(detail.getAccessToken());
			//update  detail
			detailSvImpl.update(detail);
			// get UserInfo
			User user =  WechatUtils.getUserInfo(detail);
			mav.addObject(user);
			mav.setViewName("result");
			return mav;
		}
		// 没有过期
		Detail detail = detailSvImpl.selectById(1);
		// get UserInfo
		User user =  WechatUtils.getUserInfo(detail);
		mav.addObject(user);
		mav.setViewName("result");
		return mav;
	}
}

