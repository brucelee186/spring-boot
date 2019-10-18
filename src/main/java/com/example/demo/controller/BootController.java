package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

	@RequestMapping("/")
	String home() {
		return "Home Sweet Home";
	}
}
