package com.ryg.chapter_3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class ListViewEx_Shj extends ListView {
    private static final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2_Shj horizontalScrollViewEx2Shj;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx_Shj(Context context) {
        super(context);
    }

    public ListViewEx_Shj(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx_Shj(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2_Shj horizontalScrollViewEx2_shj){
        horizontalScrollViewEx2Shj=horizontalScrollViewEx2_shj;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                horizontalScrollViewEx2Shj.requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
                if (Math.abs(deltaX)>Math.abs(deltaY)){
                    horizontalScrollViewEx2Shj.requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }
        mLastX=x;
        mLastY=y;
        return super.dispatchTouchEvent(event);
    }

}
