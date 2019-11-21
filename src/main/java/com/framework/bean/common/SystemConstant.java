package com.framework.bean.common;

public class SystemConstant {

////		 正式环境
	public static boolean isSandBox = false;
	public static String domain = "https://xcx.nvsoft.com.cn";
	public static String uploadAccessUrl = domain + "/pfjc_img/";
	public static String PUBLIC_APP_ID = "wx42b2e3e54abe7cf4";
	public static String PUBLIC_APP_SECRET = "0d19f33f9d4d04e2e83660542bc93392";
	public static String PUBLIC_APP_Token = "no7";
	public static String BAND_NTF = "cR1KT7f7XSzAKqZbg_-SgEju2ch6xFLDHhXuIe4QbDI";
	public static String BAND_FIAL_NTF = "A1VUwSqlx21lLxSb-zevKGnb-0CI5CtwT7k5oqZMbss";

	public static String PAY_SUCCESS_ID = "zyeP5Vf_cLqW7f3Ob7W6gJPpbzgEkgcfr116IRp3-6I";
	public static String TEST_FINISH_ID = "jHKq5Zxm34836b4TF-C7Ub4QJD3F2iuaeiFih8rAwZU";
	public static String FOLLOW_SUCCESS_ID = "8ok5BjhgxIf6mF5ujAswLz2Rj_-mpwdqsas1T-hH0Hc";

	public static String DRAWCASH_NOTIFY = "V0fSZWUdNc95xSoSbGBzmOGVmxSQ0YHyQRTnFUHJwpk";

	public static String GOODS_ON_DELIVERY_ID = "b0XU5wPsRtGUNUYS-i4GIO072GweLDkH3qUPrFf_b44";
	public static String RECEIVE_GOODS_ID = "O21dPYl4n_oLeNQUSfOHFuXWOuxD2YrDppwhZBFrAzk";
	public static String LEAVE_MESSAGE = "jGQnRuE5GifgHqhzPaHpBXYsHFkiUDMGxQnhxJpGglU";
	public static String DELIVERY_GOODS_NOTIFY = "7nvbxIIoakBWbPQm2VTAo-RjJM0qWhSsuMXngM1xGV0";
	public static String BUYER_RECEIVE_NOTIFY = "vA_R5M08Y_QdfX0NxeB2-5zU2eo1dfnLnqVH5znEjtA";

//	 测试环境 start
//
//	public static boolean isSandBox = true;
//	public static String domain = "https://cmtest.nvsoft.com.cn";
//	public static String uploadAccessUrl = domain + "/pfjc_img/";
//	public static String PUBLIC_APP_ID = "wx8d87332c50f65a06";
//	public static String PUBLIC_APP_SECRET = "a427bb07af1850113ad52f52c515d66b";
//	public static String PUBLIC_APP_Token = "no7";
//
//	public static String BAND_NTF = "D7yBz48LtngPmsuwNwHYGodVhHNUkkjKVWTvHD7zAq0";
//	public static String BAND_FIAL_NTF = "CUkSimXCDfNCdIgcbXlL9LcM13V8KTGfAzoNr-v4plE";
//
//	public static String PAY_SUCCESS_ID = "MeOpgVBXkfnCYnUN7xJeoPYb5uj6UoafJ2qJS5AgUm4";
//	public static String TEST_FINISH_ID = "-b4Ydo_1f17sBwpCtL73YHv7aeGjWTA2pmoTUsw5G10";
//	public static String FOLLOW_SUCCESS_ID = "4EMjjoDDX8dlkEb-uBc2do6J6jxsGBrehKqtzA2M5yE";
//
//	public static String DRAWCASH_NOTIFY = "CY6P_1y80ugABwe3LNlVLblzhkaNFF7Ku8v9GvkZgmE";
//
//	public static String GOODS_ON_DELIVERY_ID = "b0XU5wPsRtGUNUYS-i4GIO072GweLDkH3qUPrFf_b44";
//	public static String RECEIVE_GOODS_ID = "O21dPYl4n_oLeNQUSfOHFuXWOuxD2YrDppwhZBFrAzk";
//	public static String LEAVE_MESSAGE = "jGQnRuE5GifgHqhzPaHpBXYsHFkiUDMGxQnhxJpGglU";
//	public static String DELIVERY_GOODS_NOTIFY = "7nvbxIIoakBWbPQm2VTAo-RjJM0qWhSsuMXngM1xGV0";
//	public static String BUYER_RECEIVE_NOTIFY = "vA_R5M08Y_QdfX0NxeB2-5zU2eo1dfnLnqVH5znEjtA";

	// 测试环境 end
	public static String apiUrl = "/pfjc";

	public static float sandBoxMoney = 1.01f;
	public static String brand = "No7快闪科美";
	public static String uploadPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\pfjc_img";
	public static String download = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\download";

	public static String prefix = "skindetector_";

	public static String MINI_APP_ID = "wxc4e4532503cadc8d";
	public static String MINI_APP_SECRET = "a3fe7d66e53619c5afcce6d45b89523c";

	public static String MINI_MCH_ID = "1522409391";
	public static String MINI_APP_KEY = "Zaq12wsxZaq12wsxZaq12wsxZaq12wsx";

	public static String PUBLIC_APP_EncodingAESKey = "6UKVhiQXItyoKc2DIg7DrhizoJ7TYDimSKY1utcveFM";
	public static String CRET_PATH = "";

	public static String wxPayNotifyUrl = domain + apiUrl + "/pfjc_order/u/notify";
	public static String wxPayNotifyUrl2 = domain + apiUrl + "/pfjc_order/u/notify2";
	public static String wxPayNotifyMember = domain + apiUrl + "/pfjc_member_level/u/notify";
	public static String wxPayNotifyPacket = domain + apiUrl + "/pfjc_packet/u/notify";

	// 快递100

	public static String kuidi100_customer = "066D6A5669FB87DD84548149CC30B26D";
	public static String kuidi100_key = "uLWMVqxo325";

// 以下貌似沒用到
	public static String domainValue = "app.ganzheapp.com";
//	public static String httpUrlPrefix = "http://app.ganzheapp.com/";
//	public static String kuaid100_key = "SRxpddhW6592";
//	public static String kuaid100_customer = "9BE4284D2FC59AEC3965B940280429BB";
//	public static String sms_key = "8e560601bb52e594c78184ece52c54fe";// 短信key
//	public static String sms_model_id = "71837";// 短信模板ID
//	public static String id_card_key = "45bd0acda9960be21a1d4c3a1783cffb";// 身份证实名查询key
//
//	public static String kuidi100_customer = "066D6A5669FB87DD84548149CC30B26D";
//	public static String kuidi100_key = "uLWMVqxo325";
//
//	// 极光推送参数
//	public static String jpush_app_key = "9003e6f0de62a992731d1471";
//	public static String jpush_master_secret = "13a7025c1341877217acfbeb";
//
//	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "N1141614";
	public static String pswd = "b3vFxJ4Hlqffb6";
	public static String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
//
//	// 阿里云短信发送参数
	public static String accessKeyId = "LTAICytEQ6OC6RSX";
	public static String accessKeySecret = "1FSmO7UnOlYIlyhPIHU2PuTWY8rLKH";
//
	public static String confirm_refund_ntf = null;
	public static String confirm_pass_ntf = null;

}
