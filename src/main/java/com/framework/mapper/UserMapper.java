package com.framework.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.framework.bean.User;

@Mapper
public interface UserMapper {

	public void insert(User user);
	
	public void delete(int id);
	
	public void update(User user);
	
	public User get(int id);
}
