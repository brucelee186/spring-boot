package com.framework.test.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
public class CoTestMo {
	
	@Autowired
	private Sender sender;
	
	//@GetMapping("sendDirectQueue")
	public Object sendDirectQueue() {
		sender.sendFanout();
		return "ok";
	}

}
