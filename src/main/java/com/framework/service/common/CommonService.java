package com.framework.service.common;

import com.framework.exception.CoException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface CommonService {
	
	/**
	 * 以JDBC的方式插入对象
	 * @param object
	 * @return
	 * @throws PmException
	 */
	Object insertByQuery(Object object) throws CoException;
	
	/**
	 * 以JDBC的方式删除对象
	 * @param object
	 * @return
	 * @throws PmException
	 */
	Object deleteByQuery(Object object) throws CoException;
	
	/**
	 * 以JDBC的方式更新对象
	 * @param object
	 * @return
	 * @throws PmException
	 */
	Object updateByQuery(Object object) throws CoException;
	
	/**
	 * 以JDBC的方式查询对象
	 * @param object
	 * @return
	 * @throws PmException
	 */
	Object selectByQuery(Object object) throws CoException;
	
	/**
	 * 以JDBC的方式查询单个对象
	 * @param object
	 * @return
	 * @throws PmException
	 */
	Object getByQuery(Object object) throws CoException;
	
	/**
	 * 以JDBC的方式查询JSON对象
	 * @param sql
	 * @return
	 * @throws PmException
	 */
	JSONArray select(String sql) throws CoException;
	
	
	/**
	 * 以JDBC的方式查询JSON单个对象
	 * @param sql
	 * @return
	 * @throws PmException
	 */
	JSONObject get(String sql) throws CoException;
	

	
	/**
	 * 取得用户编号
	 * @return
	 */
	public String getUserId();
	
	public String getUserName();
	
	/**
	 * 取得员工卡号
	 * @return
	 */
	public String getCardNo();
	
	public String getDivisionId();
	
	public String getDivisionName();
	
	public String getIp();
	public String getLastMonth();
	
	/**
	 * 取得本月
	 * @return
	 */
	public String getCurrentMonth();
	
	/**
	 * 取得用户等级
	 * @return
	 */
	public Integer getUserLevel();
}
