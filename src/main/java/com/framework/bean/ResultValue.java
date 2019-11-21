package com.framework.bean;

import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName: ResultValue 
 * @Description: 返回结果信息
 * @author: renkai
 * @date: 2018�?1�?26�? 下午2:05:43
 */
public class ResultValue {
	private boolean success=false;//返回结果是否成功
	private String message;//提示信息
	
	//回调Id
	private String callBackId;
	
	private Map<String, Object> map=new HashMap<String, Object>();
	public ResultValue() {

	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public String getCallBackId() {
		return callBackId;
	}

	public void setCallBackId(String callBackId) {
		this.callBackId = callBackId;
	}
	
}
