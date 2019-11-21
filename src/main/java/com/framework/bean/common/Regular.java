package com.framework.bean.common;

public class Regular {

	public static final String phone = "^1\\d{10}$|^(0\\d{2,3}-?|\\(0\\d{2,3}\\))?[1-9]\\d{4,7}(-\\d{1,8})?$";
	
//	public static final String cellphone  = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\\\d{8}$";
	public static final String cellphone  = "^1[3|4|5|7|8]\\d{9}$";
	
	public static final String tel  = "^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$";
	
	public static final String email  = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	public static final String url  = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
	// 不允许为空
	public static final String blank = ".*[^ ].*";

}
