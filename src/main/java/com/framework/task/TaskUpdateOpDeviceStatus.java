package com.framework.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.bean.impl.OpDeviceImpl;
import com.framework.service.OpRoomOperationService;

@Component("taskUpdateOpDeviceStatus")
public class TaskUpdateOpDeviceStatus {

	@Autowired
	private OpRoomOperationService operationService;
	
	public void run() {
		try {
			OpDeviceImpl opDevice = new OpDeviceImpl();
			operationService.updateOpDeviceStatus(opDevice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
