package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

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
}
