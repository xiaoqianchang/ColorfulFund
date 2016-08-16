package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class ZRViewPager extends ViewPager {

	private int DIRECTION_IDLE = 0;
	private int DIRECTION_UP = 1;
	private int DIRECTION_DOWN = 2;
	private int DIRECTION_LEFT = 3;
	private int DIRECTION_RIGHT = 4;
	private int mDirection = DIRECTION_IDLE;

	private GestureDetector mGestureDetector;

	public ZRViewPager(Context context) {
		this(context, null);
	}

	public ZRViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new HScrollDetector());
		setFadingEdgeLength(0);
	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
	// getDefaultSize(0, heightMeasureSpec));
	// // Children are just made to fill our space.
	// int childWidthSize = getMeasuredWidth();
	// int childHeightSize = getMeasuredHeight();
	// int extHeight = (int) getResources().getDimension(
	// R.dimen.srl_header_scroll_max_distance);
	// // 高度加上一段高度
	// heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize
	// + extHeight, MeasureSpec.EXACTLY);
	// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	// }

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev != null) {
			int action = ev.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				mDirection = DIRECTION_IDLE;
				getParent().requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				getParent().requestDisallowInterceptTouchEvent(false);
				break;
			}
		}
		boolean re = mGestureDetector.onTouchEvent(ev);
		if (re) {
			getParent().requestDisallowInterceptTouchEvent(true);
		} else {
			getParent().requestDisallowInterceptTouchEvent(false);
		}

		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	class HScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			boolean reY = true;
			if (mDirection == DIRECTION_IDLE) {
				if (Math.abs(distanceY) < Math.abs(distanceX)) {
					if (distanceX < 0) {
						mDirection = DIRECTION_LEFT;
					} else {
						mDirection = DIRECTION_RIGHT;
					}
				} else {
					if (distanceY > 0) {
						mDirection = DIRECTION_UP;
					} else {
						mDirection = DIRECTION_DOWN;
					}
				}
			} else {
				if (mDirection == DIRECTION_UP || mDirection == DIRECTION_DOWN) {
					reY = false;
				} else if (mDirection == DIRECTION_LEFT
						|| mDirection == DIRECTION_RIGHT) {
					reY = true;
				}
			}
			return reY;
		}
	}
}