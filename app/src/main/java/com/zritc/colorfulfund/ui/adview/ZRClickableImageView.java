package com.zritc.colorfulfund.ui.adview;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 有按压效果的ImageView
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2016-01-13
 * @lastUpdate 2016-01-13
 */
public class ZRClickableImageView extends ImageView {
	public ZRClickableImageView(Context context) {
		super(context);
		this.setOnTouchListener(VIEW_TOUCH_DARK);
	}

	public ZRClickableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnTouchListener(VIEW_TOUCH_DARK);
	}

	public ZRClickableImageView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.setOnTouchListener(VIEW_TOUCH_DARK);
	}

	public OnTouchListener VIEW_TOUCH_DARK = new OnTouchListener() {
		// 变暗(三个-50，值越大则效果越深)
		public final float[] BT_SELECTED_DARK = new float[] { 1, 0, 0, 0, -50,
				0, 1, 0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				ImageView iv = (ImageView) v;
				iv.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED_DARK));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				ImageView iv = (ImageView) v;
				iv.clearColorFilter();
				mPerformClick();
			} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
				ImageView iv = (ImageView) v;
				iv.clearColorFilter();
			}
			return true; // 如为false，执行ACTION_DOWN后不再往下执行
		}
	};

	private void mPerformClick() {
		ZRClickableImageView.this.performClick();
	}

}
