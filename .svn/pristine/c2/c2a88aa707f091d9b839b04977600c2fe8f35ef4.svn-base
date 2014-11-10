package com.cooby.news.bean;

public class URLs {
	public final static String HOST = "www.18kszx.com";
	public final static String HTTP = "http://";
	public final static String	NAME_SPACE			= "http://service.golf.cooby.com";
	
	public final static String memberService = "MemberService"; // 会员信息接口
	public final static String login = "login"; // MemberService接口
	
	
	public static String getServiceURL(String method) {
		StringBuilder url = new StringBuilder();
		url.append(HTTP).append(HOST).append("/").append("/services/").append(method);
		return url.toString();
	}
}
