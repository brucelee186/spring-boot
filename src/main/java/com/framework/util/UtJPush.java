package com.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.framework.bean.impl.SyNotificationImpl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class UtJPush {
	
	// 设置好账号的app_key和masterSecret是必须的
	private static String APP_KEY = "abe24224a5b5967a0b6aacb2";
	private static String MASTER_SECRET = "3bb00c24aba59ffed7c19462";

	// 极光推送>>Android
	// Map<String, String> parm是我自己传过来的参数,可以自定义参数
	public static boolean jpushAndroid(SyNotificationImpl syNotification) {
		boolean res = false;
		try {
			// 创建JPushClient(极光推送的实例)
			JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
			// 推送的关键,构造一个payload
			// 标题
			String title = syNotification.getTitle();
			// 提示信息
			String alert = syNotification.getAlert();
			// 提示内容
			String content = syNotification.getContent();
			// 别名
			String alias = syNotification.getAlias();
			// 这里可以自定义推送参数了
			Map<String, String> mapNotification = new HashMap<String, String>();
			// 设置提示信息,内容是文章标题
			mapNotification.put("title", title);
			mapNotification.put("alert", alert);
			mapNotification.put("alias", alias);
			mapNotification.put("content", content);
			if (null != title && !"".equals(title)) {
				PushPayload payload = PushPayload.newBuilder()
						// 指定android平台的用户
						.setPlatform(Platform.android())
						// 你项目中的所有用户
						.setAudience(Audience.all())
						// 设置别名发送,单发，点对点方式
						.setAudience(Audience.alias(alias))
						// 设置按标签发送，相当于群发
						// .setAudience(Audience.tag("tag1"))
						// registrationId指定用户
						// .setAudience(Audience.registrationId(parm.get("id")))
						// 发送内容
						.setNotification(Notification.android(alert, title, mapNotification))
						.setOptions(Options.newBuilder()
								// apnProduction指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式) 可以不设置
								.setApnsProduction(true)
								// TimeToLive 两个小时的缓存时间
								.setTimeToLive(7200).build())
						// 自定义信息
						.setMessage(Message.content(content)).build();

				PushResult pushResult = jpushClient.sendPush(payload);
				if (pushResult.getResponseCode() == 200) {
					res = true;
				}
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
			return res;
		} catch (APIRequestException e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}
}
