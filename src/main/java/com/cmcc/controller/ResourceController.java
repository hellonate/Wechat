package com.cmcc.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.cmcc.bean.User;
import com.cmcc.bean.UserNotFoundException;
import com.cmcc.service.impl.UserSvImpl;
@Controller
@RequestMapping(value="/resource")
public class ResourceController {
	@Autowired
	private UserSvImpl userSvImpl;
	
	/*@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
    @ResponseBody
	public User getUser(@PathVariable("id")  int id,Model model) {
		User user = userSvImpl.selectById(id);
		model =  model.addAttribute(user);
		HttpStatus httpStatus =  user != null ? HttpStatus.OK:HttpStatus.NOT_FOUND;
		
		return user;
	}*/
	
	
	//发生错误后，给出相对应的响应码
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		HttpStatus httpStatus =  user != null ? HttpStatus.OK:HttpStatus.NOT_FOUND;
		return new ResponseEntity<User>(user, httpStatus);
	}*/
	
	//给出相对应的错误信息
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		if (user == null) {
			Error error = new Error("user"+id+"not found");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
	
	
	//处理具体的错误
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		if (user == null) {
			Error error = new Error("user"+id+"not found");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
	
	
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
/*	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> UserNotFound(UserNotFoundException e){
		int id = e.getId();
		Error error = new Error("User"+id+"not found");
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}*/
	
	//继续进化，使用responseBody
	
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}*/
	
	//继续进化，不用ResponseEntity
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error  UserNotFound(UserNotFoundException e){
		int id = e.getId();
		Error error = new Error("Error,User"+id+"not found");
		return error;
	}
	
	//在响应中设置头部信息
	
/*	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id")  int id) {
		User user = userSvImpl.selectById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}*/
	
	//将创建的新资源放到响应的头部信息中,并返回给客户端。
	@RequestMapping(value="/saveUser/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> saveUser(@PathVariable int id ,UriComponentsBuilder ucb) {
		HttpHeaders headers = new HttpHeaders();
		User user = userSvImpl.selectById(id);
		URI locationUri = ucb.path("/users")
				.path(String.valueOf(user.getId()))
				.build()
				.toUri();
		headers.setLocation(locationUri);
		ResponseEntity<User> responseEntity = new ResponseEntity<User>(user,headers,HttpStatus.CREATED);
		
		return responseEntity;
	}	
}
