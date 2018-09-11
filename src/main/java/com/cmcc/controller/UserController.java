package com.cmcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cmcc.bean.User;
import com.cmcc.mapper.UserMapper;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	   @Autowired
		UserMapper userMapper;
	   
	   @Autowired
	   private RedisTemplate< String, String> redisTemplate;
	    
	    @RequestMapping(value="/save",method=RequestMethod.GET)
	    public String save(User user) {
	    	System.out.println(user.toString());
	    	userMapper.insert(user);
	    	String str = redisTemplate.opsForValue().get("");
	    	
	    	return "result";
	    }
	    
	    
	    @RequestMapping(value="/selectById",method=RequestMethod.GET)
	    public String selectById() {
	    	User user = userMapper.selectById(1);
	    	System.out.println(user.toString());
	    	return "result";
	    }
	    
	    @RequestMapping(value="/update",method=RequestMethod.GET)
	    public String update(User user) {
	    	user.setId(1);
	        user.setCity("zhengzhou");
	    	userMapper.update(user);
	    	System.out.println(user.toString());
	    	return "result";
	    }

}
