package com.cmcc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmcc.bean.Detail;
import com.cmcc.service.impl.DetailSvImpl;

@Controller
@RequestMapping(value="/dec")
public class DetailControlelr {
	private static Logger log = Logger.getLogger(DetailControlelr.class);
    @Autowired
	DetailSvImpl detailSvImpl;
    
    @RequestMapping(value="/save",method=RequestMethod.GET)
    public String save(Detail detail) {
    	log.info("detail.toString()");
    	detailSvImpl.insert(detail);
    	return "result";
    }
    
    
    @RequestMapping(value="/selectById",method=RequestMethod.GET)
    public String selectById() {
    	Detail detail = detailSvImpl.selectById(1);
    	log.info("detail.toString()");
    	return "result";
    }
    
    @RequestMapping(value="/update",method=RequestMethod.GET)
    public String update(Detail detail) {
    	int id = 1;
    	detail.setId(id);
    	detail.setAccessToken("hello");
    	detailSvImpl.update(detail);
    	log.info(detail.toString());
    	return "result";
    }
    
}
