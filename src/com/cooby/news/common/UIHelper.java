package com.cooby.news.common;

import com.cooby.news.AppConfig;
import com.cooby.news.bean.URLs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class UIHelper {
	/**
	 * 弹出Toast消息
	 * 
	 * @param msg
	 */
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 设置IP地址
	 * @param cont
	 */
	public static void showSetIp(Context cont) {
		final AppConfig appConfig = AppConfig.getAppConfig(cont);
		String ip= appConfig.get(AppConfig.IP_ADDRESS);
		int checkItem=0;
		if(ip.equals(URLs.IP[1])){
			checkItem=1;
		}else if(ip.equals(URLs.IP[2])){
			checkItem=2;
		}else if(ip.equals(URLs.IP[3])){
			checkItem=3;
		}
		new AlertDialog.Builder(cont)
				.setTitle("请选择所要连接的地址")
				.setSingleChoiceItems(
						new String[] { "正式外网" ,"陈鹏内网", "曾哥内网", "测试外网"},
						checkItem,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								if (which == 0) {
									appConfig.set(AppConfig.IP_ADDRESS, URLs.IP[which]);
								} else if (which == 1) {
									appConfig.set(AppConfig.IP_ADDRESS, URLs.IP[which]);
								} else if (which == 2) {
									appConfig.set(AppConfig.IP_ADDRESS,URLs.IP[which]);
								} else {
									appConfig.set(AppConfig.IP_ADDRESS,URLs.IP[which]);
								}
							}
						}).setPositiveButton("确定", null).show();
	}
}
