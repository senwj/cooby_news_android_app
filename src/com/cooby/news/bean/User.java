package com.cooby.news.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class User implements Serializable {
	public String memberId; // 会员ID
	public String memberAccountNum;// 会员账号
	public String memberPassword; // 密码
	public String memberName;// 会员姓名
	public String memberBirthday; // 会员生日
	public String memberSex;// 会员性别
	public String memberIndustry;// 会员从事行业
	public String memberHobby;// 会员爱好
	public String memberEmail;// 会员邮箱
	public String memberHandicaps; // 会员差点
	public String currentState;// 当前状态
	public String memberImg;// 会员图片
	public String sessionCode;
	public String memberBigImg;// 会员大图片
	public String memberSmallImg;// 会员小图片
	public String memberPetName;// 会员昵称
	public String encryptionMemberAccountNum;// 加密的账号
	
	private Result validate;
	
	public Result getValidate() {
		return validate;
	}
	public void setValidate(Result validate) {
		this.validate = validate;
	}

	public static User parse(String result) {
		User user = new User();
		Result res = JSON.parseObject(result, Result.class);
		if(res.OK()){
			user = JSON.parseObject(res.msg, User.class);
		}
		user.setValidate(res);
		return user;
	}
}
