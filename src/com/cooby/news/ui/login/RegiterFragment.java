package com.cooby.news.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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
import com.cooby.news.bean.Result;
import com.cooby.news.bean.User;
import com.cooby.news.common.StringUtils;
import com.cooby.news.common.UIHelper;
import com.cooby.news.widget.LoadingDialog;

public class RegiterFragment extends Fragment implements View.OnClickListener{
	private AppContext ac;
	private ActionBarActivity mActivity;
	
	private TextView txt_account;
	private TextView txt_password;
	private TextView txt_affirm_password;
	private TextView txt_nickname;
	
	private EditText account;
	private EditText password;
	private EditText affirm_password;
	private EditText nickname;
	
	private Button btn_register;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		mActivity =  (ActionBarActivity) getActivity();
		ac = (AppContext) mActivity.getApplication();
		ActionBar actionBar =mActivity.getSupportActionBar();
		TextView head_title=(TextView)actionBar.getCustomView().findViewById(R.id.head_title);
		head_title.setText("注册");

		View view = inflater.inflate(R.layout.login_register_fragment,null);
		iniFreamView(view);
		return view;
	}

	public void iniFreamView(View view ) {
		txt_account = (TextView) view.findViewById(R.id.txt_account);
		txt_password = (TextView) view.findViewById(R.id.txt_password);
		txt_affirm_password = (TextView) view.findViewById(R.id.txt_affirm_password);
		txt_nickname = (TextView) view.findViewById(R.id.txt_nickname);
		
		account = (EditText) view.findViewById(R.id.account);
		password = (EditText) view.findViewById(R.id.password);
		affirm_password = (EditText) view.findViewById(R.id.affirm_password);
		nickname = (EditText) view.findViewById(R.id.nickname);
		
		btn_register = (Button) view.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		
		setRedText(txt_account);
		setRedText(txt_password);
		setRedText(txt_affirm_password);
		setRedText(txt_nickname);
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
		user.memberAccountNum=account.getText().toString();
		user.memberPassword=password.getText().toString();
		user.memberPetName=nickname.getText().toString();
		String apassword = affirm_password.getText().toString();
		if(StringUtils.isEmpty(user.memberAccountNum)){
			account.setError("用户名不能为空");
			return;
		}
		if(StringUtils.isEmpty(user.memberPassword)){
			password.setError("密码不能为空");
			return;
		}
		if(StringUtils.isEmpty(apassword)){
			affirm_password.setError("确认密码不能为空");
			return;
		}
		if(!apassword.equals(user.memberPassword)){
			account.setError("密码和确认密码不相同");
			return;
		}
		
		if(StringUtils.isEmpty(user.memberPetName)){
			nickname.setError("昵称不能为空");
			return;
		}
		
		final LoadingDialog  l  = new LoadingDialog(mActivity); 
		l.setTitle("正在注册...");
		l.show();
		final Handler mHandler = new Handler(){
			public void handleMessage(Message msg) {
				l.dismiss();
				switch(msg.what){
				case 1:
					mActivity.getSupportFragmentManager().popBackStack();
					break;
				case 0:
					UIHelper.ToastMessage(mActivity, msg.obj.toString());
					break;
				case -1:
					((AppException)msg.obj).makeToast(mActivity);
					break;
				}
			}
		};
		
		new Thread(){
			public void run() {
				Message msg = Message.obtain();
				try {
					Result res = ac.register(ac, user);
					if(res.OK()){
						msg.what=1;
					}else{
						msg.what=0;
						msg.obj = res.msg;
					}
				} catch (AppException e) {
					msg.what = -1;
			    	msg.obj = e;
					e.printStackTrace();
				}
				mHandler.sendMessage(msg);
			}
		}.start();
	}
}







































