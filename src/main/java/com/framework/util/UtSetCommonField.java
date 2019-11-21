package com.framework.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.framework.bean.common.BaseBean;
import com.framework.bean.common.Cons;

public class UtSetCommonField {

	/**
	 * 设置修改共同信息
	 * @param object
	 */
	public static void setCommonField(Object object) {
		Class<?> clazz = object.getClass();
		Class<?> clazzSuper = clazz.getSuperclass();
		Class<?> clazzGrand = clazzSuper.getSuperclass();
		// 取得用户id和ip
		String userIp = Cons.IP_SYS;
		String userId = Cons.ID_SYS;
		BaseBean bean = (BaseBean) object;
		String editState = bean.getEditState();
		try {
			Field objectField = clazzGrand.getDeclaredField("object");
			objectField.setAccessible(true);
			
			Field modifyIpfField = clazzSuper.getDeclaredField("modifiedIp");
			modifyIpfField.setAccessible(true);
			
			Field modifiedDateField = clazzSuper.getDeclaredField("modifiedDate");
			modifiedDateField.setAccessible(true);
			
			Field createUserField = clazzSuper.getDeclaredField("createUser");
			createUserField.setAccessible(true);
			
			Field modifiedUserField = clazzSuper.getDeclaredField("modifiedUser");
			modifiedUserField.setAccessible(true);
			
			HttpServletRequest request = bean.getRequest();
			if(null != request) {
				String ipRequset = UtIpAddress.getIpAddr(request);
				if(!"".equals(ipRequset)) {
					userIp = ipRequset;
				}
			}
			
			Object obj_createUser = createUserField.get(object);
			
			Object obj_modifiedUser = modifiedUserField.get(object);
			
			// 如果创建时,没有赋值修改人,那么修改人也创建人相同
			if(null != obj_createUser && null == obj_modifiedUser) {
				String createUser = obj_createUser.toString();
				modifiedUserField.set(object, createUser);
			}
			
			Date date = new Date();
			
			modifiedDateField.set(object, date);
			modifyIpfField.set(object, userIp);
			
			if(null != editState && "i".equals(editState)) {
				
				String uuid = UUID.randomUUID().toString();
				
				Field uidField = clazzSuper.getDeclaredField("uid");
				uidField.setAccessible(true);
				
				Field createDateField = clazzSuper.getDeclaredField("createDate");
				createDateField.setAccessible(true);
				
				
				Field createIpField = clazzSuper.getDeclaredField("createIp");
				createIpField.setAccessible(true);
				
				uidField.set(object, uuid);
				createDateField.set(object, date);
				createIpField.set(object,userIp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
