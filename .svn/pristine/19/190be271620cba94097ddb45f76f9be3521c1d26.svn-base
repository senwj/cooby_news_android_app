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
/** 
 * @Description:带有全部清空按钮的edittext
 * @author      zdd
 * @date        2014-4-28 下午5:20:10
 * @remark      http://blog.csdn.net/xiaanming/article/details/11066685
 */
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
    	mClearDrawable=getCompoundDrawables()[2];  //获取android:drawableRight的设置，没有则附默认图片
    	if(mClearDrawable==null){
    		mClearDrawable=getResources().getDrawable(R.drawable.common_input_box_clear);
    	}
    	
    	mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());   
    	//默认设置隐藏图标  
        setClearIconVisible(false);   
        //设置焦点改变的监听  
        setOnFocusChangeListener(this);   
        //设置输入框里面内容发生改变的监听  
        addTextChangedListener(this);  
    }
    
    /** 
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和 
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑 
     */  
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
    
    /** 
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去 
     * @param visible 
     */  
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
	
	/** 
     * 当输入框里面内容发生变化的时候回调的方法 
     */  
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
