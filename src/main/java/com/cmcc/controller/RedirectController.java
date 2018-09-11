package com.cmcc.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cmcc.bean.Detail;
import com.cmcc.bean.User;
import com.cmcc.config.Config;
import com.cmcc.utils.WechatUtils;

@Controller
@RequestMapping(value="reController")
public class RedirectController {
		
/*	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public String getCode(HttpServletRequest req) {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.REDIRECT_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		return "redirect:/reController/getUserInfo2";
		
	}
	@RequestMapping(value="/getUserInfo2",method=RequestMethod.GET)
	public ModelAndView getUserInfo2(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		// get accessToken
		Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		// get UserInfo
		User user =  WechatUtils.getUserInfo(detail);
		mav.addObject(user);
		mav.setViewName("result");
		return mav;
	}*/
	//*********************
	
/*	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public String getCode(HttpServletRequest req) {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.REDIRECT_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		return "redirect:getUserInfo2";
		
	}
	@RequestMapping(value="/getUserInfo2",method=RequestMethod.GET)
	public ModelAndView getUserInfo2(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		// get accessToken
		Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		// get UserInfo
		User user =  WechatUtils.getUserInfo(detail);
		mav.addObject(user);
		mav.setViewName("result");
		return mav;
		
	}*/
	
	
/*	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public String getCode(HttpServletRequest req) {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.REDIRECT_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		return "redirect:getUserInfo2?code=123";
		
	}
	@RequestMapping(value="/getUserInfo2",method=RequestMethod.GET)
	public ModelAndView getUserInfo2(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		// get accessToken
		//Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		//// get UserInfo
		//User user =  WechatUtils.getUserInfo(detail);
		//mav.addObject(user);
		System.out.println("code=>"+code);
		mav.setViewName("result");
		return mav;
		
	}*/
	
	
	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public String getCode(HttpServletRequest req) {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.REDIRECT_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		return "redirect:result?getUserInfo2";
		
	}
	@RequestMapping(value="/getUserInfo2",method=RequestMethod.GET)
	public ModelAndView getUserInfo2(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		// get accessToken
		//Detail detail = WechatUtils.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		//// get UserInfo
		//User user =  WechatUtils.getUserInfo(detail);
		//mav.addObject(user);
		System.out.println("code=>"+code);
		mav.setViewName("result");
		return mav;
	}
	
	
}
