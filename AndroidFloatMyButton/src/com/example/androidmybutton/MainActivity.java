package com.example.androidmybutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;

import com.example.androidmybutton.view.MyButtonBackMap;
/**
 * 自定义浮动按钮1，随意拖动
 * @author miaowei
 *
 */
public class MainActivity extends Activity{

	private MyButtonBackMap myButtonBackMap;
	WindowManager mWindowManager;  
    WindowManager.LayoutParams wmParams; 
    LinearLayout mFloatLayout;  
    private static final String TAG = "MV";
    LinearLayout view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//设置LayoutParams(全局变量）相关参数
		wmParams = ((MyApplication)getApplication()).getMywmParams();
		mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		myButtonBackMap = new MyButtonBackMap(MainActivity.this);
		myButtonBackMap.setText("浮动按钮");
		wmParams.type = LayoutParams.TYPE_PHONE;  
        wmParams.format = PixelFormat.RGBA_8888;;  
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE; 
        //调整悬浮窗口至左上角
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;  
        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 50;  
        wmParams.y = 50;  
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  
        mWindowManager.addView(myButtonBackMap, wmParams);
		
		//createFloatView();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	/**
	 * 此方法同样适用
	 */
	private void createFloatView()  
    {  
        //获取LayoutParams对象  
        wmParams = new WindowManager.LayoutParams();  
          
        //获取的是LocalWindowManager对象  
        //mWindowManager = this.getWindowManager();  
        //Log.i(TAG, "mWindowManager1--->" + this.getWindowManager());  
        //mWindowManager = getWindow().getWindowManager();  
        //Log.i(TAG, "mWindowManager2--->" + getWindow().getWindowManager());  
       
        //获取的是CompatModeWrapper对象  
        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);  
        Log.i(TAG, "mWindowManager3--->" + mWindowManager);  
        wmParams.type = LayoutParams.TYPE_PHONE;  
        wmParams.format = PixelFormat.RGBA_8888;;  
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE; 
        //调整悬浮窗口至左上角
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;  
        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 50;  
        wmParams.y = 50;  
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  
          
        LayoutInflater inflater = getLayoutInflater();//LayoutInflater.from(getApplication());  
          
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);  
        mWindowManager.addView(mFloatLayout, wmParams); 
        
        //setContentView(R.layout.main);  
        myButtonBackMap = (MyButtonBackMap)mFloatLayout.findViewById(R.id.backMap);  
          
        //Log.i(TAG, "mFloatView" + myButtonBackMap);  
        //Log.i(TAG, "mFloatView--parent-->" + myButtonBackMap.getParent());  
        //Log.i(TAG, "mFloatView--parent--parent-->" + myButtonBackMap.getParent().getParent());  
        //绑定触摸移动监听  
        myButtonBackMap.setOnTouchListener(new OnTouchListener()   
        {  
              
            @Override  
            public boolean onTouch(View v, MotionEvent event)   
            {  
            	int eventAction = event.getAction();
            	switch (eventAction) {
				case MotionEvent.ACTION_MOVE:
	                wmParams.x = (int)event.getRawX() - mFloatLayout.getWidth()/2;  
	                //25为状态栏高度  
	                wmParams.y = (int)event.getRawY() - mFloatLayout.getHeight()/2 - 40;  
	                mWindowManager.updateViewLayout(mFloatLayout, wmParams);  
					break;
				case MotionEvent.ACTION_DOWN:
					
					Log.i(TAG, "点击///ACTION_DOWN");
					
					break;
				default:
					break;
				}
                
                return true;  
            }  
        });  
          
        //绑定点击监听  
        myButtonBackMap.setOnClickListener(new View.OnClickListener()  
        {  
              
            @Override  
            public void onClick(View v)   
            {  
            	Log.i(TAG, "点击///onClick");
            }  
        });  
          
    } 
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mFloatLayout != null)  
        {  
            //移除悬浮窗口  
            mWindowManager.removeView(mFloatLayout);  
        } 
	}

}
