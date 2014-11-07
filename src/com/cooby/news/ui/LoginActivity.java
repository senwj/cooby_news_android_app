package com.cooby.news.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cooby.news.AppContext;
import com.cooby.news.R;
import com.cooby.news.widget.AllClearEditText;

public class LoginActivity extends Activity{
	private AllClearEditText edit_account;
	private AllClearEditText edit_passwd;
	private Button btn_login;
	private Button btn_register;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login_activity);
		initView();
	}

	private void initView(){
		edit_account = (AllClearEditText) this.findViewById(R.id.edit_account);
		edit_passwd = (AllClearEditText) this.findViewById(R.id.edit_passwd);
		btn_login = (Button) this.findViewById(R.id.btn_login);
		btn_register = (Button) this.findViewById(R.id.btn_register);
		
		btn_login.setOnClickListener(setOnClickId(btn_login.getId()));
	}
	
	private void login(){
		final String name = edit_account.getText().toString().trim();
		final String passwd = edit_passwd.getText().toString().trim();
		
		new Thread(){
			public void run() {
				AppContext ac = (AppContext) getApplication();
			}
		}.start();
	}
	
	private View.OnClickListener setOnClickId(final int ViewId){
		return new View.OnClickListener() {
			public void onClick(View arg0) {
				switch(ViewId){
				case R.id.btn_login:
					login();
					break;
				}
			}
		};
	}
}





























