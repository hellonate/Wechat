package com.cmcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cmcc.bean.Result;

@Controller
@RequestMapping(value="/redis")
public class RedisController {
	
	@Autowired
	private RedisTemplate<String, Result> redisTemplate;
	
	
	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;
	

	@RequestMapping(value="/insertInfo",method=RequestMethod.GET)
	public ModelAndView insertInfo() {
		Result result = new Result();
		result.setCityCode("123");
		result.setMessage("lets go");
		result.setNickname("hahah");
		
		redisTemplate.opsForValue().set("result", result);
		

		
		stringRedisTemplate.opsForValue().set("result2", "result2");
		System.out.println("saved");
		return null;
	}
	
	@RequestMapping(value="/searchInfo",method=RequestMethod.GET)
	public ModelAndView searchInfo() {
	    ModelAndView mav = new ModelAndView();
		Result result2 =redisTemplate.opsForValue().get("result");
		System.out.println("value=>"+result2.toString());
		stringRedisTemplate.opsForValue().set("mlf", "mlf");
		System.out.println("seted");
		mav.setViewName("redis");
		mav.addObject(result2);
	
		return mav;
	}
	
	
	@RequestMapping(value="/updateInfo",method=RequestMethod.GET)
	public ModelAndView updateInfo() {
		Result result = new Result();
		result.setCityCode("123567");
		result.setMessage("lets go");
		result.setNickname("hahah");
		redisTemplate.opsForValue().set("result", result);
		//mav.setViewName("result");
		return null;
	}
	
	@RequestMapping(value="/deleteInfo",method=RequestMethod.GET)
	public ModelAndView deleteInfo() {
		redisTemplate.delete("result");
		return null;
	}
	
	@RequestMapping(value="/redis",method=RequestMethod.GET)
	public String redis() {
		
		return "/redis";
	}
	
	

}
