package com.cooby.news.ui.login;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cooby.news.AppContext;
import com.cooby.news.AppException;
import com.cooby.news.R;
import com.cooby.news.bean.User;
import com.cooby.news.common.StringUtils;

public class RegiterFragment extends Fragment implements View.OnClickListener{
	private AppContext ac;
	private Activity mActivity;
	
	private TextView txt_account;
	private TextView txt_password;
	private TextView txt_affirm_password;
	
	private EditText account;
	private EditText password;
	private EditText affirm_password;
	
	private Button btn_register;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mActivity =  getActivity();
		ac = (AppContext) mActivity.getApplication();
		View view = inflater.inflate(R.layout.login_register_fragment,null);
		iniFreamView(view);
		return view;
	}

	public void iniFreamView(View view ) {
		txt_account = (TextView) view.findViewById(R.id.txt_account);
		txt_password = (TextView) view.findViewById(R.id.txt_password);
		txt_affirm_password = (TextView) view.findViewById(R.id.txt_affirm_password);
		
		account = (EditText) view.findViewById(R.id.account);
		password = (EditText) view.findViewById(R.id.password);
		affirm_password = (EditText) view.findViewById(R.id.affirm_password);
		
		btn_register = (Button) view.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		
		setRedText(txt_account);
		setRedText(txt_password);
		setRedText(txt_affirm_password);
	}
	
	private void setRedText(TextView textView) {
		SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
		ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
		builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(builder);
	}

	public void onClick(View view) {
		int viewId=view.getId();
		switch(viewId){
		case R.id.btn_register:
			register();
		break;
		}
	}
	
	private void register(){
		final User user = new User();
		user.name=account.getText().toString();
		user.password=password.getText().toString();
		String apassword = affirm_password.getText().toString();
		if(StringUtils.isEmpty(user.name)){
			account.setError("用户名不能为空");
			return;
		}
		if(StringUtils.isEmpty(user.password)){
			password.setError("密码不能为空");
			return;
		}
		if(StringUtils.isEmpty(apassword)){
			account.setError("确认密码不能为空");
			return;
		}
		if(apassword.equals(user.password)){
			account.setError("密码和确认密码不相同");
			return;
		}
		
		Handler mHandler = new Handler(){
			public void handleMessage(Message msg) {
				
			}
		};
		
		new Thread(){
			public void run() {
				try {
					ac.register(ac, user);
				} catch (AppException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}







































