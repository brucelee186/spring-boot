package com.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.framework.bean.common.Regular;

public class UtValid {

    public static boolean validate(String str, String type) {
    	//将给定的正则表达式编译到模式中。
        Pattern p = Pattern.compile(type);
        //创建匹配给定输入与此模式的匹配器。
        Matcher m = p.matcher(str);
        //尝试将整个区域与模式匹配。
        return m.matches();
    }
    
    public static boolean phone(String str) {
    	return validate(str, Regular.phone);
    }
    
    public static boolean cellphone(String str) {
    	return validate(str, Regular.cellphone);
    }
    
    public static boolean tel(String str) {
    	return validate(str, Regular.tel);
    }
    
    public static boolean fax(String str) {
    	return validate(str, Regular.tel);
    }
    
    public static boolean email(String str) {
        return validate(str, Regular.email);
    }
    
    public static boolean url(String str) {
    	return validate(str, Regular.url);
    }
    
    public static boolean blank(Object str) {
    	return validate(str.toString(), Regular.blank);
    }
    

}
