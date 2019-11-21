package com.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class CoUtil {
	
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	// MD5Encode
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	//

	public static String Md5(String plainText, String salt) {
		return Md5(plainText + salt);
	}

	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
            byte[] b = md.digest();

			int i;

			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static JSONObject getUserNotExistErrorResp() {
		return getErrorResp(500, "用户不存在", "use does not exist");
	}
	
	public static JSONObject getErrorResp(String errorMsg) {
		return getErrorResp(500, errorMsg, errorMsg);
	}
	
	public static JSONObject getErrorResp(int code, String errorMsg, String errorMsgEs) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("isSuccess", "0");
		json.put("errorMessage", errorMsg);
		json.put("msg", errorMsg);
		json.put("msg_es", errorMsgEs);
		return json;
	}
	
	public static String assemblyWhereV1(JSONObject requestJson, String table) {
		String sqlWhere = "";
		JSONObject filterDataObj = requestJson.getJSONObject("filterDataRow");
		if (filterDataObj != null) {

			JSONObject filterDataUserdata = requestJson.getJSONObject("filterUserdata");
			String relations = filterDataUserdata.getString("relations");

			if (StringUtils.isNotBlank(relations)) {
				String[] relationsAry = relations.split(",");

				for (int i = 0; i < relationsAry.length; i++) {
					String relation = relationsAry[i];

					if (filterDataObj.containsKey(relation)) {
						String filterDataValue = filterDataObj.getJSONObject(relation).getString("value");

						if (StringUtils.isNotBlank(filterDataValue)) {

							String columnName = CoUtil.stringChange(relation);

							if (columnName.endsWith("_filter_begin")) {

								columnName = columnName.replace("_filter_begin", "");
								sqlWhere += (" and " + table + "." + columnName + " > '" + filterDataValue + "' ");

							} else if (columnName.endsWith("_filter_end")) {

								columnName = columnName.replace("_filter_end", "");
								sqlWhere += (" and " + table + "." + columnName + " < '" + filterDataValue + "' ");

							} else {
								sqlWhere += (" and " + table + "." + columnName + " like '%" + filterDataValue + "%' ");
							}
						}
					}
				}
				if (sqlWhere != "") {
					sqlWhere = sqlWhere.replaceFirst(" and", "");
				}
			}
		}

		return sqlWhere;
	}
	
	/**
	 * 计算 where条件
	 * @param requestJson
	 * @param table
	 * @return
	 */
	public static String assemblyWhereV2(JSONObject requestJson, String table) {
		String sqlWhere = "";
		JSONObject filterDataObj = requestJson.getJSONObject("filterDataRow");
		if (filterDataObj != null) {
			
			JSONObject filterDataUserdata = requestJson.getJSONObject("filterUserdata");
			String relations = filterDataUserdata.getString("relations");
			
			
			if (StringUtils.isNotBlank(relations)) {
				String[] relationsAry = relations.split(",");
				
				for (int i = 0; i < relationsAry.length; i++) {
					String relation = relationsAry[i];
					
					if (filterDataObj.containsKey(relation)) {
						String filterDataValue = filterDataObj.getJSONObject(relation).getString("value");
						
						if (StringUtils.isNotBlank(filterDataValue)) {
							
							String prefix = "a.";
							/*
							 * if(relation.length() >=3 && relation.startsWith("id")) { String
							 * thirdCharacter = relation.substring(2, 3); char charThird =
							 * thirdCharacter.charAt(0); // 如果第三个字母大写那么替换id为name方便查询
							 * if(Character.isUpperCase(charThird)) { relation = "name" +
							 * relation.substring(2, relation.length()); prefix = ""; } }
							 */
							String columnName = relation;
							
							if (columnName.endsWith("_filter_begin")) {
								
								columnName = columnName.replace("_filter_begin", "");
								sqlWhere += (" and " + prefix + columnName + " > '" + filterDataValue + "' ");
								
							} else if (columnName.endsWith("_filter_end")) {
								
								columnName = columnName.replace("_filter_end", "");
								sqlWhere += (" and " +  prefix + columnName + " < '" + filterDataValue + "' ");
								
							} else {
								sqlWhere += (" and " +  prefix + columnName + " like '%" + filterDataValue + "%' ");
							}
						}
					}
				}
				if (sqlWhere != "") {
					sqlWhere = sqlWhere.replaceFirst(" and", "");
				}
			}
		}
		return sqlWhere;
	}
	
	/**
	 * 计算 where条件
	 * @param requestJson
	 * @param table
	 * @return
	 */
	public static String assemblyWhere(JSONObject requestJson, String table) {
		String sqlWhere = "";
		JSONObject filterDataObj = requestJson.getJSONObject("filterDataRow");
		if (filterDataObj != null) {
			
			JSONObject filterDataUserdata = requestJson.getJSONObject("filterUserdata");
			String relations = filterDataUserdata.getString("relations");
			
			if (StringUtils.isNotBlank(relations)) {
				String[] relationsAry = relations.split(",");
				
				for (int i = 0; i < relationsAry.length; i++) {
					String relation = relationsAry[i];
					
					if (filterDataObj.containsKey(relation)) {
						String filterDataValue = filterDataObj.getJSONObject(relation).getString("value");
						
						if (StringUtils.isNotBlank(filterDataValue)) {
							
							String columnName = relation;
							
							if (columnName.endsWith("_filter_begin")) {
								
								columnName = columnName.replace("_filter_begin", "");
								sqlWhere += (" and " + "a." + columnName + " > '" + filterDataValue + "' ");
								
							} else if (columnName.endsWith("_filter_end")) {
								
								columnName = columnName.replace("_filter_end", "");
								sqlWhere += (" and " +  "a." + columnName + " < '" + filterDataValue + "' ");
								
							} else {
								sqlWhere += (" and " +  "a." + columnName + " like '%" + filterDataValue + "%' ");
							}
						}
					}
				}
				if (sqlWhere != "") {
					sqlWhere = sqlWhere.replaceFirst(" and", "");
				}
			}
		}
		return sqlWhere;
	}
	
	/**
	 * 计算 where条件
	 * @param requestJson
	 * @param table
	 * @return
	 */
	public static String assemblyWhereFilter(JSONObject requestJson, String sqlWhere, String relations) {
		String sqlWhereFilter = "";
		JSONObject filterDataObj = requestJson.getJSONObject("userdata");
		if (filterDataObj != null) {
			
			JSONObject filterDataUserdata = requestJson.getJSONObject("userdata");
			
			if (StringUtils.isNotBlank(relations)) {
				String[] relationsAry = relations.split(",");
				
				for (int i = 0; i < relationsAry.length; i++) {
					String relation = relationsAry[i];
					
					if (filterDataObj.containsKey(relation)) {
						String filterDataValue = filterDataObj.getJSONObject(relation).getString("value");
						
						if (StringUtils.isNotBlank(filterDataValue)) {
							
							String columnName = relation;
							
							 {
								 sqlWhereFilter += (" and " +  "a." + columnName + " ='" + filterDataValue + "' ");
							}
						}
					}
				}
				if ("".equals(sqlWhere)) {
					sqlWhereFilter = sqlWhereFilter.replaceFirst(" and", "");
				} 
				else {
					sqlWhereFilter = sqlWhere + sqlWhereFilter;
				}
			}
		}
		return sqlWhereFilter;
	}
	
	public static String stringChange(String s) {
		if (StringUtils.isBlank(s))
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				String temp = String.valueOf(s.charAt(i)).toLowerCase();
				sb.append("_" + temp);
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
	
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "LocalHost";
		}
		return ip;
	}
	
	 /**
     * SpringMvc下获取request
     * 
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;

    }
    /**
     * SpringMvc下获取session
     * 
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;

    }

}
