package com.xms.dao;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.User;

@MyAnnotation
public interface UserMapper {
	
//	�����˺Ų�ѯ�û�
	User findUserByEmail(String email);
	
//	�����û�ע����Ϣ
	void save(User user);
	
}
