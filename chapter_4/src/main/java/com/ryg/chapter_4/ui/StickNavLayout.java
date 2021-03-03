package com.ryg.chapter_4.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;

import androidx.viewpager.widget.ViewPager;

import com.ryg.chapter_4.R;

/**
 * @author SHJ
 * @date 2021/2/5
 * email：1214495199@qq.com
 * Description:
 */
public class StickNavLayout extends LinearLayout {
    private View mTop;
    private View mHideTitleView;
    private ViewPager mViewPager;

    private int mTopViewHeight;
    private ViewGroup mInnerScrollView;

    public boolean isTopHidden() {
        return isTopHidden;
    }

    private boolean isTopHidden = false;

    private SitckyScollerListener scollerListener;
    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private float mDownY;
    private boolean mDragging;


    private boolean isInControl = false;

    public StickNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);

        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        scollerListener = (SitckyScollerListener) context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        mHideTitleView = findViewById(R.id.id_stickynavlayout_topview_hide_height);
        View view = findViewById(R.id.id_stickynavlayout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException("id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mViewPager = (ViewPager) view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(sizeHeight+mTop.getMeasuredHeight(),heightMode);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight=mTop.getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
//                getCurrentScrollView();

                if (mInnerScrollView instanceof ScrollView) {
                    if (mInnerScrollView.getScrollY() == 0 && isTopHidden && dy > 0 && !isInControl) {
                        isInControl = true;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        //拷贝新的事件
                        MotionEvent ev2 = MotionEvent.obtain(ev);
                        dispatchTouchEvent(ev);
                        ev2.setAction(MotionEvent.ACTION_DOWN);
                        return dispatchTouchEvent(ev2);
                    }
                } else if (mInnerScrollView instanceof ListView) {

                    ListView lv = (ListView) mInnerScrollView;
                    View c = lv.getChildAt(lv.getFirstVisiblePosition());

                    if (!isInControl && c != null && c.getTop() == 0 && isTopHidden && dy > 0) {
                        isInControl = true;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        //拷贝新的事件
                        MotionEvent ev2 = MotionEvent.obtain(ev);
                        dispatchTouchEvent(ev);
                        ev2.setAction(MotionEvent.ACTION_DOWN);
                        return dispatchTouchEvent(ev2);
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
//                getCurrentScrollView();

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
interface SitckyScollerListener {
    public void scoller(float scrollY, float topViewHeight);
}