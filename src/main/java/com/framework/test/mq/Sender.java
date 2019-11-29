package com.framework.test.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.util.UtMqRabbitConfig;

@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void sendFanout() {
		User user = new User();
		user.setId(1);
		user.setName("neo");
		System.err.println(" Send UserInfo");
		amqpTemplate.convertAndSend(UtMqRabbitConfig.QUEUE_DIRECT, "", user);
	}

}
