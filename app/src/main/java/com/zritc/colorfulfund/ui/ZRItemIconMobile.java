package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Midas
 * @version 1.0
 * @createDate 2015-12-17
 * @lastUpdate 2015-12-17
 */
public class ZRItemIconMobile extends ZRItemMobile {
	private ZRIconEditText mIconEditText;

	public ZRItemIconMobile(Context context, AttributeSet attrs) {
		this(context, attrs, null, null, null);
	}

	public ZRItemIconMobile(Context context, String label, String hint,
			String name) {
		this(context, null, label, hint, name);
	}

	public ZRItemIconMobile(Context context, AttributeSet attrs, String label,
			String hint, String name) {
		super(context, attrs, label, hint, name);
	}

	public void addOnIconClickListener(View.OnClickListener l) {
		mIconEditText.addOnIconClickListener(l);
	}

	public void setIcon(Drawable drawable) {
		mIconEditText.setIcon(drawable);
	}

	public void setIcon(int resId) {
		mIconEditText.setIcon(resId);
	}

	@Override
	protected ZREditText onCreateEditText(Context context) {
		mIconEditText = new ZRIconEditText(context);
		return mIconEditText;
	}

}
