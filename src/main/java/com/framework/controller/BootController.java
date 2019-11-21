package com.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

// 声明该类为参与控制反转的类(相当于@ResponseBody 与  @Controller两个注解的集合)
@RestController
public class BootController {

	// 声明触发路径
	@RequestMapping("/")
	String home() {
		return "Home Sweet Home";
	}
	
	@RequestMapping("/boot")
	String boot() {
		return "booting...";
	}
	
	@RequestMapping("/login")
	String login(@RequestParam String username, @RequestParam String password) {
		String res = "用户名: " + username + " 密码: " + password;
		return res;
	}
	
	@RequestMapping("/index")
	ModelAndView index() {
		return new ModelAndView("index");
	}
}
