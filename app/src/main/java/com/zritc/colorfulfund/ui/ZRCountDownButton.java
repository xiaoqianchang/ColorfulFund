package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.Button;

import com.zritc.colorfulfund.utils.ZRStrings;

public class ZRCountDownButton extends CountDownTimer {
	public static final int TIME_COUNT_FUTURE = 60000;
	public static final int TIME_COUNT_INTERVAL = 1000;

	private Context mContext;
	private Button mButton;
	private String mOriginalText;
	private Drawable mOriginalBackground;
	private Drawable mTickBackground;
	private int mOriginalTextColor;

	public ZRCountDownButton() {
		super(TIME_COUNT_FUTURE, TIME_COUNT_INTERVAL);
	}

	public ZRCountDownButton(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public void init(Context context, Button button) {
		mContext = context;
		mButton = button;
		mOriginalText = mButton.getText().toString();
		mOriginalBackground = mButton.getBackground();
		mTickBackground = mOriginalBackground;
		mOriginalTextColor = mButton.getCurrentTextColor();
	}

	public void setTickDrawable(Drawable tickDrawable) {
		mTickBackground = tickDrawable;
	}

	@Override
	public void onFinish() {
		if (mContext != null && mButton != null) {
			mButton.setText(mOriginalText);
			mButton.setTextColor(mOriginalTextColor);
			mButton.setBackgroundDrawable(mOriginalBackground);
			mButton.setClickable(true);
		}
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if (mContext != null && mButton != null) {
			mButton.setClickable(false);
			mButton.setBackgroundDrawable(mTickBackground);
			mButton.setText(String.format(
					ZRStrings.get(mContext, "lable_resend"),
					millisUntilFinished / 1000));
		}
	}
}
