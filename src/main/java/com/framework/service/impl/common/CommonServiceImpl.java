package com.framework.service.impl.common;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.framework.bean.common.BaseBean;
import com.framework.exception.CoException;
import com.framework.service.common.CommonService;
import com.framework.util.UtSetCommonField;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Override
	public Object insertByQuery(Object object) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteByQuery(Object object) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateByQuery(Object object) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectByQuery(Object object) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByQuery(Object object) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray select(String sql) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject get(String sql) throws CoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCardNo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDivisionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDivisionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUserLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCommonField(Object object) {
		UtSetCommonField.setCommonField(object);
	}

}
