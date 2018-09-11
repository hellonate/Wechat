package com.cmcc.mapper;


import com.cmcc.bean.User;

public interface UserMapper {
	
	 void insert(User user);
	 
	 void update(User user);
	 
	 User selectById(int id);


}
