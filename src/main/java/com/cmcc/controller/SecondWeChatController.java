package com.cmcc.controller;



import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.cmcc.bean.Detail;
import com.cmcc.bean.Result;
import com.cmcc.bean.Share;
import com.cmcc.config.Config;
import com.cmcc.service.impl.DetailSvImpl;
import com.cmcc.service.impl.UserSvImpl;
import com.cmcc.utils.JacksonUtil;
import com.cmcc.utils.WechatUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import sun.util.logging.resources.logging;


@Controller
@RequestMapping(value="/secwechat")
public class SecondWeChatController {
	Logger log  = Logger.getLogger(SecondWeChatController.class);
	
	
	@Autowired
	UserSvImpl UserSvImpl;
	
	@Autowired
	DetailSvImpl detailSvImpl;
	
	//TODO ,这里有可能有错误
	@Autowired
	RedisTemplate<String, Result> redisTemplate;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate2;
	
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public void Login(HttpServletRequest req,HttpServletResponse resp ) throws IOException {
		//进入授权页面，然后跳转到回调页面
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Config.APPTest_ID+"&redirect_uri="+URLEncoder.encode(Config.REDIRECT_URL)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		resp.sendRedirect(url); //跳转到getCode方法中
	}
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2e7288fdd5f458b7&redirect_uri=http://wx.10085.cn/WechatDemo/secwechat/getCode&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirec
	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public ModelAndView getCode(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		
		redisTemplate2.opsForValue().set("code", code);
		
		Result result = UserSvImpl.getUserInfo(Config.ACCOUNT_ID, code);
		//save to redis
		redisTemplate.opsForValue().set("result", result);
		mav.addObject(result);
		mav.setViewName("result");
		return mav;
	}
	 
	
	
	@RequestMapping(value="/getCode2",method=RequestMethod.GET)
	public ModelAndView getCode2(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		Result result = UserSvImpl.getUserInfo2(Config.ACCOUNT_ID, code);
		mav.addObject(result);
		mav.setViewName("result");
		return mav;
	}
	
	
	@RequestMapping(value="/getCode3",method=RequestMethod.GET)
	public ModelAndView getCode3(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		Result result = UserSvImpl.getUserInfo3(Config.ACCOUNT_ID, code);
		mav.addObject(result);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value="/getCode4",method=RequestMethod.GET)
	public ModelAndView getCode4(HttpServletRequest req) throws IOException {
		ModelAndView mav = new ModelAndView();
		String code = req.getParameter("code");
		Result result = UserSvImpl.getUserInfo3(Config.ACCOUNT_ID, code);
		mav.addObject(result);
		mav.setViewName("result");
		return mav;
	}
	
	/**
	 * 跳转到share.jsp
	 * @param req
	 * @return
	 */
	
	@RequestMapping(value="/redirect2share",method=RequestMethod.GET)
	public String redirect2share(HttpServletRequest req) {
		return "redirect:/share.jsp";
	}
	
	
	@RequestMapping(value="/share2WX",method=RequestMethod.GET)
	@ResponseBody
	public Share share2WX(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		//通过提供的接口获得
		String localUrl = "http://wx.10085.cn/WechatDemo/share.jsp";
		String url = "http://221.176.66.251/operation/api/out/getJsSignature?accountId="+Config.ACCOUNT_ID+"&url="+localUrl;
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		
		String jsSignature  =null;
		String  timeStamp  =null;
		String  nonceStr  =null;
		int  status=jsonObject.getIntValue("status");
		
		if (status == 1 || status == 1004) {
			
			log.info(jsonObject.get("message"));
			return null;
		}
			jsSignature  =jsonObject.getString("jsSignature");
		    timeStamp =jsonObject.getString("timeStamp");
			nonceStr =jsonObject.getString("nonceStr");

			Share share = new Share();
			share.setAppId(Config.APPTest_ID);
			share.setNonceStr(nonceStr);
			share.setSignature(jsSignature);
			share.setTimestamp(timeStamp);
			return share;
		/*	String jsapi_ticket = getTicket();
			//save ticket to redis
			redisTemplate2.opsForValue().set("jsapi_ticket", jsapi_ticket);
			//todo
			Share share = saveInfo(jsapi_ticket);
			return share;*/
	}

	private String getTicket() {
		String code = redisTemplate2.opsForValue().get("code");
		Detail detail = detailSvImpl.getAccessTokenDetail(Config.APPTest_ID, Config.APPTest_SECRET, code);
		String accessToken = detail.getAccessToken();
		
		//save accessToken to redis
		//redisTemplate2.opsForValue().set("accessToken", accessToken);
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		JSONObject jsonObj = WechatUtils.getJsonObjFromHttp(url);
		String jsapi_ticket = jsonObj.getString("ticket");
		return jsapi_ticket;
	}
	
	
	public static Share saveInfo(String jsapi_ticket) {
		
		        //生成签名，并把相关字段传递到前台
			
				String timestamp = "1536115157586"; //时间戳
				String nonceStr = "abcdefghijk";  //随机字符串
				//这里的url 必须是调用 js接口页面的完整url
				//////TODO
				////这里绝对是错误的
				
				//分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				//
				String url = "http://wx.10085.cn/WechatDemo/share.jsp";
				System.out.println("url=>"+url);

				// get jointString 
				String jointStr = jointString(jsapi_ticket, nonceStr, timestamp, url);
				System.out.println("jointStr=>"+jointStr);
				//get signature
				String signature = createSignature(jointStr);
				System.out.println("signature=>"+signature);
				//get AppId
				String appId = Config.APPTest_ID;
				System.out.println("appId=>"+appId);

				//
				Share share = new Share();
				share.setAppId(appId);
				share.setNonceStr(nonceStr);
				share.setSignature(signature);
				share.setTimestamp(timestamp);
		
		return share;
	}
	
	
	
	public static String jointString(String jsapi_ticket,String noncestr,String timestamp,String url) {
		String str = new String();
		str+= "jsapi_ticket="+jsapi_ticket+"&";
		str+= "noncestr="+noncestr+"&";
		str+= "timestamp="+timestamp+"&";
		str+= "url="+url;
		return str;
	}	

	
	public static String createSignature(String str) {
		//生成签名
		String signature = DigestUtils.shaHex(str);
		return signature;
	}
	
	@RequestMapping(value="getJsSignature",method=RequestMethod.GET)
	
	public ModelAndView getJsSignature() throws JsonParseException, JsonMappingException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		String url = "http://wx.10086.cn/operation/api/out/getJsSignature?accountId=5109afce-6b8f-4e9f-b706-36e7bf3e2f39";
		JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		System.out.println(jsonObject.toString());
		String timestamp= jsonObject.getString("timeStamp");
		String nonceStr= jsonObject.getString("nonceStr");
		String appId= Config.APPTest_ID;
		String jsSignature= jsonObject.getString("jsSignature");
		Share share = new Share();
		
		share.setNonceStr(nonceStr);
		share.setSignature(jsSignature);
		share.setTimestamp(timestamp);
		share.setAppId(appId);
		modelAndView.addObject(share);
		modelAndView.setViewName("share");
		return modelAndView;
	}
}
