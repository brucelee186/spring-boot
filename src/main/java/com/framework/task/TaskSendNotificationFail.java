package com.framework.task;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.bean.impl.SyNotificationImpl;
import com.framework.service.SyNotificationService;


@Component("taskSendNotificationFail")
public class TaskSendNotificationFail {

	@Autowired
	private SyNotificationService syNotificationService;

	public void run() {
		try {
			SyNotificationImpl syNotification = new SyNotificationImpl();
			// 状态(已经发送 s:sended,未发送 u:unsend,失败 f:failed,失效i:invalid)
			syNotification.setStatus("f");
			syNotification = syNotificationService.get(syNotification);
			if(null != syNotification) {
				syNotificationService.sendNotification(syNotification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}