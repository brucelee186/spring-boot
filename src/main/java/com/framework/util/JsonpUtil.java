package com.framework.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.framework.bean.ResultValue;


public class JsonpUtil {
	public static void  createJsonp(HttpServletResponse response,ResultValue resultValue) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		// String value= JsonUtils.obj2json(resultValue);
		String value= resultValue.toString();
		System.out.println(resultValue.getCallBackId());
		try {
			if(value!=null) {
				response.getWriter().write(resultValue.getCallBackId() +"(" + value + ")");
				response.getWriter().flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
