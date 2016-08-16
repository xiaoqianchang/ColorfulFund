package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.zritc.colorfulfund.utils.ZRResourceManager;

/**
 * Base item. It contains a label only.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public abstract class ZRItemBase extends LinearLayout {
	protected static final float WEIGHT_SUM = 10f;
	protected static final float WEIGHT_LABEL = 3f;
	protected ZRTextView mLabel;
	protected String mName;

	public ZRItemBase(Context context) {
		this(context, null);
	}

	public ZRItemBase(Context context, AttributeSet attrs) {
		this(context, attrs, null, null, LinearLayout.HORIZONTAL);
	}

	public ZRItemBase(Context context, String label, String name) {
		this(context, null, label, name, LinearLayout.HORIZONTAL);
	}

	public ZRItemBase(Context context, AttributeSet attrs, String label,
			String name, int orientation) {
		super(context, attrs);

		setWeightSum(WEIGHT_SUM);
		mName = name;
		mLabel = new ZRTextView(context);
		mLabel.setTextAppearance(context,
				ZRResourceManager.getResourceID("ZRText.Medium.Gray", "style"));
		mLabel.setId(mLabel.hashCode());
		mLabel.setText(label);
		int padding = getResources().getDimensionPixelSize(
				ZRResourceManager.getResourceID("padding_14", "dimen"));
		LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		// lp.weight = WEIGHT_LABEL;
		// lp.rightMargin = padding;
		lp.leftMargin = padding;
		lp.gravity = Gravity.CENTER_VERTICAL;
		setOrientation(orientation);
		addView(mLabel, lp);
		if (TextUtils.isEmpty(label)) {
			mLabel.setVisibility(View.GONE);
		}
		setPadding(0, 0, 0, 0);
	}

	public void setName(String name) {
		mName = name;
	}

	public String getName() {
		return mName;
	}

	public void setLabel(String label) {
		mLabel.setText(label);
	}

	public String getLabel() {
		return mLabel.getText().toString();
	}

	/**
	 * Set value of this form item
	 * 
	 * @see {@link #getValue()}
	 * @param value
	 */
	public abstract void setValue(String value);

	/**
	 * Return value of this form item. The returned value will be concatenated
	 * in QString and send to server.
	 * 
	 * @return
	 */
	public abstract Object getValue();

	/**
	 * This will be called when the form is ready to submit.
	 * 
	 * @return true if input in this form item is validate.
	 */
	public abstract boolean isValidate();

	public abstract void setHint(String hint);

}
