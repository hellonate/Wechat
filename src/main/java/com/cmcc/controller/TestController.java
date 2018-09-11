package com.cmcc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cmcc.bean.Share;


@Controller
@RequestMapping(value="/test1")
public class TestController {
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(HttpServletRequest req,HttpServletResponse response) throws IOException {
		String username = req.getParameter("username");
		System.out.println("hello:"+username);
		return "redirect:/share.jsp";
	}
	
	@RequestMapping(value="/back",method=RequestMethod.GET)
	@ResponseBody
	public Share  back() {
		Share share = new Share();
		share.setAppId("appId");
		share.setNonceStr("nonceStr");
		share.setSignature("signature");
		share.setTimestamp("timestamp");
		return share ;
	}
	
	
	
}
