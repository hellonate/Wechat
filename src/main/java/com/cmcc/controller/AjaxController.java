package com.cmcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/ajax")
public class AjaxController {
	 
	@RequestMapping(value="getInfo",method=RequestMethod.GET)
	public void getInfo() {
		
		
		
	}
}
