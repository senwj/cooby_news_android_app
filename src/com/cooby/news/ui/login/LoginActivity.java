package com.cooby.news.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.cooby.news.R;
import com.cooby.news.ui.login.LoginFragment.OnRegiterListener;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{
    private View mCustomView;
    private ActionBar.LayoutParams lp;
    private ImageView action_bar_back;
    
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mCustomView = (View) getLayoutInflater().inflate(R.layout.base_action_bar_layout, null);
     	lp = new ActionBar.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
     	final ActionBar bar = getSupportActionBar();
        bar.setCustomView(mCustomView, lp);
        action_bar_back = (ImageView) mCustomView.findViewById(R.id.action_bar_back);
        action_bar_back.setOnClickListener(this);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		this.setContentView(R.layout.login_activity);
		LoginFragment lo = new LoginFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.main,lo);
		ft.commit();
		lo.setOnRegiterListener(new OnRegiterListener(){
			public void OnClick() {
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.main,new RegiterFragment());
				ft.addToBackStack("");
				ft.commit();
			}
		});
	}

	public void onClick(View v) {
		int viewId = v.getId();
		switch(viewId){
		case R.id.action_bar_back:
			if(getSupportFragmentManager().getBackStackEntryCount()>0){
				getSupportFragmentManager().popBackStack();
			}else{
				finish();
			}
		break;
		}
	}
	
	
}






























