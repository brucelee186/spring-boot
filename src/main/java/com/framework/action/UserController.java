package com.framework.action;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framework.bean.User;
import com.framework.service.UserService;

@RestController
@ComponentScan({"com.framework.service"})
@MapperScan("com.framework.mapper")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/service")
	public String service() {
		return "service= " + userService; 
	}

	@RequestMapping("/get")
	public String get() {
		User user = userService.get(1);
		return "username: " + user.getUsername() + " age: " + user.getAge();
	}
}
