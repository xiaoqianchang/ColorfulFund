package com.zritc.colorfulfund.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by nereo on 15/11/10.
 */
public class ZRSquareFrameLayout extends FrameLayout {
	public ZRSquareFrameLayout(Context context) {
		super(context);
	}

	public ZRSquareFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
	}
}
