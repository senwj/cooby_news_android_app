package com.cooby.news.bean;

import com.cooby.news.AppConfig;
import com.cooby.news.AppContext;

public class URLs {
	public final static String HOST = "www.18kszx.com";
	public final static String HTTP = "http://";
	/**
	 * 0-正式外网     1-陈鹏内网      2-曾哥内网     3-测试外网
	 */
	public final static String[] IP = new String[]{"www.18kszx.com", "192.168.0.188:8888","192.168.0.180:8883", "61.191.55.11:8080"};

	/**
	 * 登录
	 */
	public final static String login ="/member_login.do";
	/**
	 * 注册
	 */
	public final static String register = "/member_addMember.do";

	public static String getServiceURL(String method) {
		StringBuilder url = new StringBuilder();
		url.append(HTTP).append(HOST).append("/").append("/services/")
				.append(method);
		return url.toString();
	}
	
	public static String getURL(AppContext appContext,String method) {
		final AppConfig appConfig = AppConfig.getAppConfig(appContext);
		String ip= appConfig.get(AppConfig.IP_ADDRESS);
		StringBuilder url = new StringBuilder();
		url.append(HTTP).append(ip).append("/Information").append("/mobile").append(method);
		return url.toString();
	}
}
