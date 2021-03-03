package com.ryg.chapter_3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class VScrollViewExTest extends ViewGroup {
    private static final String TAG = "VScrollViewExTest";

    private int mChildrenSize;
    private int mChildHeight;
    private int mChildIndex;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public VScrollViewExTest(Context context) {
        super(context);
        init();
    }

    public VScrollViewExTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VScrollViewExTest(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mLastX = x;
            mLastY = y;
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action:" + event.getAction());
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
            break;
        }
        case MotionEvent.ACTION_MOVE: {
            int deltaX = x - mLastX;
            int deltaY = y - mLastY;
            Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
            scrollBy(0, -deltaY);
            break;
        }
        case MotionEvent.ACTION_UP: {
            int scrollY = getScrollY();
            int scrollToChildIndex = scrollY / mChildHeight;
            Log.d(TAG, "current index:" + scrollToChildIndex);
            //滑动速率
            mVelocityTracker.computeCurrentVelocity(1000);
            float yVelocity = mVelocityTracker.getYVelocity();
            if (Math.abs(yVelocity) >= 50) {
                mChildIndex = yVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
            } else {
                mChildIndex = (scrollY + mChildHeight / 2) / mChildHeight;
            }
            mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
            int dy = mChildIndex * mChildHeight - scrollY;
            smoothScrollBy(0, dy);
            mVelocityTracker.clear();
            Log.d(TAG, "index:" + scrollToChildIndex + " dy:" + dy);
            break;
        }
        default:
            break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            //大概是wrap_content时候的自己计算
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, measuredHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        } else {//此时width height  应该都是EXACTLY
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "width:" + getWidth());
        int childH = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                final int childHeight = childView.getMeasuredHeight();
                mChildHeight = childHeight;
                childView.layout(childH, 0, childH + childHeight,
                        childView.getMeasuredHeight());
                childH += childHeight;
            }
        }
    }
//=============================以下  mScroller 固定写法=============================================

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(0, getScrollY(), 0, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
//=============================以上  mScroller 固定写法=============================================

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
