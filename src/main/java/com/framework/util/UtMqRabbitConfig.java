package com.framework.util;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.framework.test.mq.User;


//@Configuration
public class UtMqRabbitConfig {

	public static final String QUEUE_DIRECT = "directQueue";
	
	//@Bean
	public Queue directQueue() {
		return new Queue(QUEUE_DIRECT, true);
	}
}