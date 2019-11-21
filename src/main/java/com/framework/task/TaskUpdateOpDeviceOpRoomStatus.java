package com.framework.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.bean.impl.OpDeviceImpl;
import com.framework.service.OpDeviceService;
import com.framework.service.OpRoomOperationService;

@Component("taskUpdateOpDeviceOpRoomStatus")
public class TaskUpdateOpDeviceOpRoomStatus {

	@Autowired
	private OpDeviceService opDeviceService;
	
	public void run() {
		try {
			OpDeviceImpl opDevice = new OpDeviceImpl();
			opDeviceService.updateOpDeviceOpRoomStatus(opDevice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
