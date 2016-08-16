package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zritc.colorfulfund.utils.ZRResourceManager;

/**
 * Custom ImageView, show pictures by checked.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-05-05
 * @lastUpdate 2014-05-05
 */
public class ZRSwitchButton extends ImageView implements OnClickListener {

	public interface OnCheckListener {
		public void onCheck(ZRSwitchButton v, boolean checked);
	}

	private boolean isChecked = true;
	private OnCheckListener mOnCheckListener = null;

	public ZRSwitchButton(Context context) {
		this(context, null);
		initListner();
	}

	public ZRSwitchButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		initListner();
	}

	public ZRSwitchButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initListner();
	}

	private void initListner() {
		setChecked(isChecked());
		setOnClickListener(this);
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
		if (checked) {
			setImageResource(ZRResourceManager.getResourceID("btn_switch_on",
					"drawable"));
		} else {
			setImageResource(ZRResourceManager.getResourceID("btn_switch_off",
					"drawable"));
		}
	}

	@Override
	public void onClick(View v) {
		setChecked(!isChecked());
		if (mOnCheckListener != null) {
			mOnCheckListener.onCheck(this, isChecked());
		}
	}

	public void setOnCheckListener(OnCheckListener listener) {
		initListner();
		mOnCheckListener = listener;
	}

	public OnCheckListener getOnCheckListener() {
		return mOnCheckListener;
	}

}
