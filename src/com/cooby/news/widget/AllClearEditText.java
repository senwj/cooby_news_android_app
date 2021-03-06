package com.cooby.news.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.cooby.news.R;

public class AllClearEditText extends EditText implements OnFocusChangeListener,TextWatcher{
	private Drawable mClearDrawable;
	private boolean hasFoucs;  
	
	/** 
	* @param context
	* @param attrs
	* @param defStyle
	*/ 
	public AllClearEditText(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* @param context
	* @param attrs
	* @param defStyle
	*/ 
	public AllClearEditText(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* @param context
	* @param attrs
	* @param defStyle
	*/ 
	public AllClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

    private void init(){
    	mClearDrawable=getCompoundDrawables()[2];  
    	if(mClearDrawable==null){
    		mClearDrawable=getResources().getDrawable(R.drawable.common_input_box_clear);
    	}
    	
    	mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());   
        setClearIconVisible(false);   
        setOnFocusChangeListener(this);   
        addTextChangedListener(this);  
    }
    
 
    @Override   
    public boolean onTouchEvent(MotionEvent event) {  
        if (event.getAction() == MotionEvent.ACTION_UP) {  
            if (getCompoundDrawables()[2] != null) {  
  
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())  
                        && (event.getX() < ((getWidth() - getPaddingRight())));  
                  
                if (touchable) {  
                    this.setText("");  
                }  
            }  
        }  
  
        return super.onTouchEvent(event);  
    }  
    
 
    protected void setClearIconVisible(boolean visible) {   
        Drawable right = visible ? mClearDrawable : null;   
        setCompoundDrawables(getCompoundDrawables()[0],   
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);   
    }   
    

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		this.hasFoucs = hasFocus;  
        if (hasFocus) {   
            setClearIconVisible(getText().length() > 0);   
        } else {   
            setClearIconVisible(false);   
        }   
	}   
	

    @Override   
    public void onTextChanged(CharSequence s, int start, int count,   
            int after) {   
                if(hasFoucs){  
                    setClearIconVisible(s.length() > 0);  
                }  
    }   

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
 
	
	
 
}
