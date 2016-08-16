package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Custom button view，has red tip.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRNumButton extends RelativeLayout {
	private ZRTextView mMainBtn;
	private ZRTextView mNumView;
	private Context mContext;
	private Drawable mNormalDrawable;
	private Drawable mSpecialDrawable;

	private OnClickListener mCustomOnClickListener = null;

	private OnClickListener mMainBtnOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (null != mCustomOnClickListener) {
				mCustomOnClickListener.onClick(ZRNumButton.this);
			}
		}
	};

	public ZRNumButton(Context context) {
		this(context, null);
	}

	public ZRNumButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZRNumButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initLayout();
	}

	public void setMainBackground(int resID) {
		this.setBackgroundResource(resID);
	}

	public void setMainBackground(Drawable drawable) {
		mMainBtn.setBackgroundDrawable(drawable);
	}

	public void setMainTextColor(ColorStateList colors) {
		mMainBtn.setTextColor(colors);
	}

	public void setMainTextColor(int color) {
		mMainBtn.setTextColor(color);
	}

	public void setMainTextSize(int textSize) {
		mMainBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
	}

	public void setMainMinEms(int minEms) {
		mMainBtn.setMinEms(minEms);
	}

	public LayoutParams getMainTextParams() {
		return (LayoutParams) mMainBtn.getLayoutParams();
	}

	public void setMainTextParams(LayoutParams params) {
		mMainBtn.setLayoutParams(params);
	}

	public void setNumBackground(Drawable drawable) {
		mNormalDrawable = drawable;
	}

	public void setSpecialNumBackground(Drawable drawable) {
		mSpecialDrawable = drawable;
	}

	@Override
	public void setOnClickListener(OnClickListener listener) {
		mCustomOnClickListener = listener;
	}

	@Override
	public void setClickable(boolean clickable) {
		mMainBtn.setClickable(clickable);
	}

	@Override
	public void setEnabled(boolean enable) {
		mMainBtn.setEnabled(enable);
	}

	public void setText(CharSequence text) {
		mMainBtn.setText(text);
	}

	/**
	 * 看具体业务决定是否使用这个功能
	 * 
	 * @param num
	 */
	public void setNum(int num) {
		// -1 当有新的消息提醒时，右上角按钮有红色提示
		if (-1 == num) {
			mNumView.setBackgroundDrawable(mSpecialDrawable);
			mNumView.setText("");
			mNumView.setVisibility(View.VISIBLE);
		} else if (0 == num) {
			mNumView.setVisibility(View.GONE);
		} else {
			// 显示消息的条数
			mNumView.setBackgroundDrawable(mNormalDrawable);
			if (99 < num) {
				mNumView.setText("99+");
				mNumView.setVisibility(View.VISIBLE);
			} else {
				mNumView.setText(String.valueOf(num));
				mNumView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setNumTextColor(int color) {
		mNumView.setTextColor(color);
	}

	public void setNumTextSize(int textSize) {
		mNumView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
	}

	private void initLayout() {
		mContext = getContext();
		mMainBtn = new ZRTextView(mContext);
		mMainBtn.setId(mMainBtn.hashCode());
		mMainBtn.setGravity(Gravity.CENTER);
		mMainBtn.setOnClickListener(mMainBtnOnClickListener);
		LayoutParams params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		addView(mMainBtn, params);

		mNumView = new ZRTextView(mContext);
		mNumView.setGravity(Gravity.CENTER);
		params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_RIGHT, mMainBtn.getId());
		addView(mNumView, params);
		mNumView.setVisibility(View.GONE);
	}
}
