package com.cooby.news.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cooby.news.AppContext;
import com.cooby.news.AppException;
import com.cooby.news.R;
import com.cooby.news.bean.Result;
import com.cooby.news.bean.User;
import com.cooby.news.common.UIHelper;
import com.cooby.news.widget.AllClearEditText;

public class LoginFragment extends Fragment implements View.OnClickListener{
	private AllClearEditText edit_account;
	private AllClearEditText edit_passwd;
	private Button btn_login;
	private Button btn_register;
	private Button btn_seiip;

	private ActionBarActivity mActivity;
	
	public OnRegiterListener mOnRegiterListener;
	public interface OnRegiterListener{
		public void OnClick();
	}
	public void setOnRegiterListener(OnRegiterListener liste){
		mOnRegiterListener =liste;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		mActivity =  (ActionBarActivity) getActivity();
		ActionBar actionBar =mActivity.getSupportActionBar();
		TextView head_title=(TextView)actionBar.getCustomView().findViewById(R.id.head_title);
		head_title.setText("登录");
		
		View view = inflater.inflate(R.layout.login_fragment, null);
		initView(view);
		return view;
	}
	
	private void initView(View view){
		edit_account = (AllClearEditText) view.findViewById(R.id.edit_account);
		edit_passwd = (AllClearEditText) view.findViewById(R.id.edit_passwd);
		btn_login = (Button) view.findViewById(R.id.btn_login);
		btn_register = (Button) view.findViewById(R.id.btn_register);
		btn_seiip = (Button) view.findViewById(R.id.btn_seiip);
		
		btn_login.setOnClickListener(this);
		btn_seiip.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}
	
	private void login(){
		final String username = edit_account.getText().toString().trim();
		final String passwd = edit_passwd.getText().toString().trim();
		final Handler mhandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == 1){
					mActivity.finish();
				}else if(msg.what==0){
					UIHelper.ToastMessage(mActivity, msg.obj.toString());
				}else if(msg.what == -1){
					((AppException)msg.obj).makeToast(mActivity);
				}
			}
		};
		new Thread(){
			public void run() {
				Message msg = Message.obtain();
				try {
					AppContext ac = (AppContext) mActivity.getApplication();
					User user = ac.login(ac, username, passwd);
					Result res = user.getValidate();
					if(res.OK()){
	                	ac.saveLoginInfo(user);//保存登录信息
	                	msg.what = 1;//成功
	                	msg.obj = user;
	                }else{
	                	msg.what = 0;//失败
	                	msg.obj = res.msg;
	                }
				} catch (AppException e) {
					msg.what = -1;
			    	msg.obj = e;
					e.printStackTrace();
				}
				mhandler.sendMessage(msg);
			}
		}.start();
	}
	
	public void onClick(View view) {
		int viewId = view.getId();
		switch(viewId){
		case R.id.btn_login:
			login();
			break;
		case R.id.btn_register:
			mOnRegiterListener.OnClick();
			break;
		case R.id.btn_seiip:
			UIHelper.showSetIp(mActivity);
			break;
		}
	}
}
