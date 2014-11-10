package com.cooby.news.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.cooby.news.R;
import com.cooby.news.ui.login.LoginFragment.OnRegiterListener;

public class LoginActivity extends ActionBarActivity{
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login_activity);
		
		
		
		LoginFragment lo = new LoginFragment();
		lo.setOnRegiterListener(new OnRegiterListener(){
			public void OnClick() {
				changenFragment(new RegiterFragment());
			}
		});
		
		changenFragment(lo);
	}
	
	private void changenFragment(Fragment fragment){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.main,fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}

}






























