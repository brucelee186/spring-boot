package com.framework.test.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.framework.util.UtMqRabbitConfig;

@Component
public class Receiver {

	@RabbitListener(queues = UtMqRabbitConfig.QUEUE_DIRECT)
	private void receiverDirectQueue(User user) {
		System.err.println(user.toString());
	}
}
