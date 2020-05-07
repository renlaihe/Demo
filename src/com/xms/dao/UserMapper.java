package com.xms.dao;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.User;

@MyAnnotation
public interface UserMapper {
	
//	根据账号查询用户
	User findUserByEmail(String email);
	
//	保存用户注册信息
	void save(User user);
	
}
