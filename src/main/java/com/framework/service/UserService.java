package com.framework.service;

import com.framework.bean.User;

public interface UserService {
	
	public void insert(User user);
	
	public void delete(int id);
	
	public void update(User user);
	
	public User get(int id);

}
