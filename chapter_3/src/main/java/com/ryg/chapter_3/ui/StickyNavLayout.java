package com.ryg.chapter_3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ryg.chapter_3.R;

public class StickyNavLayout extends LinearLayout
{

	private View mTop;
	private View mNav;
	private ViewPager mViewPager;
	
	private int mTopViewHeight;
	private ViewGroup mInnerScrollView;
	private boolean isTopHidden = false;

	private OverScroller mScroller;
	private VelocityTracker mVelocityTracker;
	private int mTouchSlop;
	private int mMaximumVelocity, mMinimumVelocity;
	
	private float mLastY;
	private boolean mDragging;
	
	
	

	public StickyNavLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);

		
		mScroller = new OverScroller(context);
		mVelocityTracker = VelocityTracker.obtain();
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mMaximumVelocity = ViewConfiguration.get(context)
				.getScaledMaximumFlingVelocity();
		mMinimumVelocity = ViewConfiguration.get(context)
				.getScaledMinimumFlingVelocity();

	}

	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		mTop = findViewById(R.id.id_stickynavlayout_topview);
		mNav = findViewById(R.id.id_stickynavlayout_indicator);
		View view = findViewById(R.id.id_stickynavlayout_viewpager);
		if (!(view instanceof ViewPager))
		{
			throw new RuntimeException(
					"id_stickynavlayout_viewpager show used by ViewPager !");
		}
		mViewPager = (ViewPager) view;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
		params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mTopViewHeight = mTop.getMeasuredHeight();
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		float y = ev.getY();

		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			mLastY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			float dy = y - mLastY;

			getCurrentScrollView();

			if (Math.abs(dy) > mTouchSlop)
			{
				mDragging = true;
				
				
				if(mInnerScrollView instanceof ScrollView)
				{
					// 如果topView没有隐藏
					// 或sc的scrollY = 0 && topView隐藏 && 下拉，则拦截
					if (!isTopHidden
							|| (mInnerScrollView.getScrollY() == 0 && isTopHidden && dy > 0))
					{
						return true;
					}
				}else if(mInnerScrollView instanceof ListView)
				{
					ListView lv = (ListView) mInnerScrollView;
					View c = lv.getChildAt(lv.getFirstVisiblePosition());
					//TODO 此处因为listview要刷新，所以topview没有隐藏且向下拉的时候不拦截
					// 如果topView没有隐藏
					// 或sc的listView在顶部 && topView隐藏 && 下拉，则拦截
					if (!isTopHidden
							|| ( c!= null && c.getTop() == 0 && isTopHidden && dy > 0))
					{
						return true;
					}
				}
				
			}
			break;
			default:
				break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	private void getCurrentScrollView()
	{

		int currentItem = mViewPager.getCurrentItem();
		PagerAdapter a = mViewPager.getAdapter();
		if (a instanceof FragmentPagerAdapter)
		{
			FragmentPagerAdapter fadapter = (FragmentPagerAdapter) a;
			Fragment item = fadapter.getItem(currentItem);
			mInnerScrollView = (ViewGroup) (item.getView()
					.findViewById(R.id.id_stickynavlayout_innerscrollview));
		} else if (a instanceof FragmentStatePagerAdapter)
		{
			FragmentStatePagerAdapter fsAdapter = (FragmentStatePagerAdapter) a;
			Fragment item = fsAdapter.getItem(currentItem);
			mInnerScrollView = (ViewGroup) (item.getView()
					.findViewById(R.id.id_stickynavlayout_innerscrollview));
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		mVelocityTracker.addMovement(event);
		int action = event.getAction();
		float y = event.getY();

		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) mScroller.abortAnimation();
			mVelocityTracker.clear();
			mVelocityTracker.addMovement(event);
			mLastY = y;
			return true;
		case MotionEvent.ACTION_MOVE:
			float dy = y - mLastY;

			if (!mDragging && Math.abs(dy) > mTouchSlop)
			{
				mDragging = true;
			}
			if (mDragging)
			{
				//一般都是 -dy  或者  -dx
				scrollBy(0, (int) -dy);
				mLastY = y;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			mDragging = false;
			if (!mScroller.isFinished())
			{
				mScroller.abortAnimation();
			}
			break;
		case MotionEvent.ACTION_UP:
			mDragging = false;
			mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
			int velocityY = (int) mVelocityTracker.getYVelocity();
			if (Math.abs(velocityY) > mMinimumVelocity)
			{
				fling(-velocityY);
			}
			mVelocityTracker.clear();
			break;
			default:
				break;
		}

		return super.onTouchEvent(event);
	}

	public void fling(int velocityY)
	{
		mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
		invalidate();
	}

	@Override
	public void scrollTo(int x, int y)
	{
		if (y < 0)
		{
			y = 0;
		}
		if (y > mTopViewHeight)
		{
			y = mTopViewHeight;
		}
		if (y != getScrollY())
		{
			super.scrollTo(x, y);
		}

		isTopHidden = getScrollY() == mTopViewHeight;

	}

	@Override
	public void computeScroll()
	{
		if (mScroller.computeScrollOffset())
		{
			scrollTo(0, mScroller.getCurrY());
			invalidate();
		}
	}

}
