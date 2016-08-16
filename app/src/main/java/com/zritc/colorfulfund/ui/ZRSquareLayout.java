package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ZRSquareLayout extends FrameLayout {
    public ZRSquareLayout(Context context) {
        super(context);
    }

    public ZRSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
