package com.example.androidmybutton.view;

import com.example.androidmybutton.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
/**
 * 自定义浮动按钮1
 * @author miaowei
 *
 */
public class MyButtonBackMap extends Button{
	
	private Context mContext;
	private static final String TAG = "MV"; 
	WindowManager mWindowManager;  
    WindowManager.LayoutParams wmParams;  
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    private boolean mode;
	public MyButtonBackMap(Context context) {
		super(context);
		mContext = context;
		createFloatView();
	}

	public MyButtonBackMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	private void createFloatView()  
    {  
        //获取LayoutParams对象  
        //wmParams = new WindowManager.LayoutParams();  
        wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();  
        //获取的是CompatModeWrapper对象  
       mWindowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);  
        Log.i(TAG, "mWindowManager3--->" + mWindowManager);  
        //绑定触摸移动监听  
        MyButtonBackMap.this.setOnTouchListener(new OnTouchListener()   
        {  
              
            @Override  
            public boolean onTouch(View v, MotionEvent event)   
            {  
            	//getRawX()获取相对屏幕的坐标，即以屏幕左上角为原点         
                x = event.getRawX();   
                y = event.getRawY()-25;   //25是系统状态栏的高度
            	int eventAction = event.getAction();
            	switch (eventAction) {
				case MotionEvent.ACTION_MOVE:
					Log.i(TAG, "ACTION_MOVE");
				
					
				    updateViewPosition();
					
					
					break;
				case MotionEvent.ACTION_DOWN:
					//getX()获取相对View的坐标，即以此View左上角为原点
	                mTouchStartX =  event.getX(); 
	                mTouchStartY =  event.getY();
					Log.i(TAG, "点击///ACTION_DOWN");
					break;
				case MotionEvent.ACTION_UP:
					Log.i(TAG, "ACTION_UP");
					break;
				default:
					break;
				}
                
                return false;  
            }  
        });  
          
        //绑定点击监听  
        MyButtonBackMap.this.setOnClickListener(new View.OnClickListener()  
        {  
              
            @Override  
            public void onClick(View v)   
            {  
            	Log.i(TAG, "点击///onClick");
            }  
        });  
          
    } 
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

	}

	private void updateViewPosition(){
        //更新浮动窗口位置参数,x是鼠标在屏幕的位置，mTouchStartX是鼠标在图片的位置
        wmParams.x=(int)( x-mTouchStartX);
        System.out.println(mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        mWindowManager.updateViewLayout(this, wmParams);
        
     }
}
