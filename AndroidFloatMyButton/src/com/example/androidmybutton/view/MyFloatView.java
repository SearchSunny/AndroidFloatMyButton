package com.example.androidmybutton.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;

import com.example.androidmybutton.MyApplication;
/**
 * 自定义浮动按钮2
 * @author miaowei
 *
 */
public class MyFloatView extends Button {

	private Context mContext;
	public MyFloatView(Context context) {
		super(context);
		mContext = context;
	}
	public MyFloatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    
    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService("window");
    
    //此wmParams为获取的全局变量，用以保存悬浮窗口的属性
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();
	
	 @Override
     public boolean onTouchEvent(MotionEvent event) {
         
         //getRawX()获取相对屏幕的坐标，即以屏幕左上角为原点         
         x = event.getRawX();   
         y = event.getRawY()-25;   //25是系统状态栏的高度
         Log.i("currP", "currX"+x+"====currY"+y);
         switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //getX()获取相对View的坐标，即以此View左上角为原点
                mTouchStartX =  event.getX(); 
                mTouchStartY =  event.getY();
                
                Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);
                
                break;
            case MotionEvent.ACTION_MOVE:                
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchStartX=mTouchStartY=0;
                break;
            }
            return true;
        }
     
     private void updateViewPosition(){
        //更新浮动窗口位置参数,x是鼠标在屏幕的位置，mTouchStartX是鼠标在图片的位置
        wmParams.x=(int)( x-mTouchStartX);
        System.out.println(mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        wm.updateViewLayout(this, wmParams);
        
     }
}
