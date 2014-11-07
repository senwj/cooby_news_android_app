package com.cooby.news.common;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class UIHelper {
	public static void getToast(Context context, int id) {
		Toast toast = Toast.makeText(context, id, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
