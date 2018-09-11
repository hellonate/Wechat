package com.cmcc.mapper;


import com.cmcc.bean.Detail;


public interface DetailMapper {
	 void insert(Detail detail);
	 
	 void update(Detail detail);
	 
	 Detail selectById(int id);

}
