package com.cmcc.controller;




import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cmcc.bean.User;



@Controller
@RequestMapping(value="/es")
public class ESController {
	private Logger log = LoggerFactory.getLogger(ESController.class);

	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	@RequestMapping(value="/save",method=RequestMethod.GET)
	public String save() {
		String id = "123456";
		User user = new User("123", "苗乐飞", "男", "河南省", "许昌市", "china");
		IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(user).build();
		esTemplate.index(indexQuery);	
		System.out.println("save done");
		return "redirect:search";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView  searchById(Model model) {
		ModelAndView mav = new ModelAndView();
		List<String> list = new ArrayList<String>();
		list.add("123456");
		List<User> users =new ArrayList<User>();
		//构建查询语句
		SearchQuery searchQuery =new NativeSearchQueryBuilder().withIds(list).build();
		users = esTemplate.queryForList(searchQuery, User.class);
		System.out.println(users);
		mav.addObject("users", users);
		mav.setViewName("es");	
		return mav;
	}
	

	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public void delete() {
		esTemplate.deleteIndex("es_search");
	}
	
	
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public void update() {
		String id = "123456";
		User user = new User("123", "栾延旭", "男", "河南省", "许昌市", "china");
	    IndexQuery indexQuery = new IndexQueryBuilder().withType("es_person").withObject(user).build();
	    esTemplate.index(indexQuery);
     }
}
