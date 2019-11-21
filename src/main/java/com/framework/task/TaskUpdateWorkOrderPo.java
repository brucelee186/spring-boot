package com.framework.task;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.framework.bean.impl.OpDeviceImpl;
import com.framework.bean.impl.OpWorkOrderImpl;
import com.framework.bean.impl.SyNotificationImpl;
import com.framework.service.OpDeviceService;
import com.framework.service.OpWorkOrderService;
import com.framework.service.SyNotificationService;

/**
 * 批量更新已停电工单
 * @author Administrator
 *
 */
@Component("taskUpdateWorkOrderPo")
public class TaskUpdateWorkOrderPo {

	@Autowired
	private OpWorkOrderService opWorkOrderService;

	public void run() {
		try {
			OpWorkOrderImpl opWorkOrder = new OpWorkOrderImpl();
			opWorkOrderService.updateWorkOrderPo(opWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}