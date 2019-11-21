
package com.framework.bean.common;


public interface Cons {

	// JPush
    public static String ENCRYPT_SMS2_TYPE = "SM2";
    public static String DEFAULT_SM2_ENCRYPT_KEY = "BPj6Mj/T444gxPaHc6CDCizMRp4pEl14WI2lvIbdEK2c+5XiSqmQt2TQc8hMMZqfxcDqUNQW95puAfQx1asv3rU=";

	public static final String ID_SYS = "00000000-0000-0000-0000-000000000000";
	public static final String IP_SYS = "192.168.0.0";
	public static final String SESSION_INFO = "sessionInfo";
	public static final String LOG_TEMPLATE = "[NAS] %s";
	
	// 编辑状态
	public static final String INSERT_STATE = "i";
	public static final String DELETE_STATE = "d";
	public static final String UPDATE_STATE = "u";
	public static final String SEARCH_STATE = "s";
}
