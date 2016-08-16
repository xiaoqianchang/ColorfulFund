package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;

/**
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRCheckBox extends LinearLayout {
	private static final int[] STATE_SET_CHECKED = { android.R.attr.state_checked };
	private static final int[] STATE_SET_UNCHECKED = { -android.R.attr.state_checked };

	private boolean isChecked;
	private ZRTextView mText;
	private ImageView mImage;
	private Drawable mDrawable;

	private ZROnCheckChangedListener mOnCheckedChangeListener;

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			setChecked(!isChecked);
		}
	};

	public interface ZROnCheckChangedListener {
		public void onCheckChanged(ZRCheckBox v, boolean checked);
	}

	public ZRCheckBox(Context context) {
		this(context, null);
	}

	public ZRCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);

		if (null != attrs) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					ZRResourceManager.getStyleableArray("ZRText"));

			int textIndex = ZRResourceManager.getStyleable("ZRText_text");
			String textName = "";

			int n = a.getIndexCount();
			for (int i = 0; i < n; ++i) {
				int attr = a.getIndex(i);
				if (attr == textIndex) {
					textName = a.getString(attr);
				}
			}
			if (!TextUtils.isEmpty(textName)) {
				mText.setText(ZRStrings.get(context, textName));
			}
			mText.setTextAppearance(a);
			a.recycle();
		}
	}

	public void setChecked(boolean checked) {
		if (isChecked != checked) {
			isChecked = checked;
			updateDrawable();
			if (null != mOnCheckedChangeListener) {
				mOnCheckedChangeListener.onCheckChanged(this, isChecked);
			}
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setOnCheckChangedListener(ZROnCheckChangedListener l) {
		mOnCheckedChangeListener = l;
	}

	public void setText(String s) {
		mText.setText(s);
	}

	public void setCheckDrawable(Drawable d) {
		mDrawable = d;
		updateDrawable();
	}

	public void setCheckDrawable(int resID) {
		setCheckDrawable(getResources().getDrawable(resID));
	}

	public void setTextPaddingLeft(int padding) {
		LayoutParams params = (LayoutParams) mText.getLayoutParams();
		params.leftMargin = padding;
		mText.setLayoutParams(params);
	}

	public void setTextStyle(Context context, int styleID) {
		mText.setTextAppearance(context, styleID);
	}

	@Deprecated
	@Override
	public void setOnClickListener(OnClickListener l) {
	}

	private void init(Context context) {
		super.setOnClickListener(mOnClickListener);
		isChecked = false;
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		mImage = new ImageView(context);
		mDrawable = getResources().getDrawable(
				ZRResourceManager.getResourceID("btn_checkbox", "drawable"));
		addView(mImage);

		mText = new ZRTextView(context);
		mText.setSingleLine(true);
		mText.setEllipsize(TruncateAt.END);
		LayoutParams params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.leftMargin = context.getResources().getDimensionPixelSize(
				ZRResourceManager.getResourceID("padding_14", "dimen"));
		addView(mText, params);

		updateDrawable();
	}

	private void updateDrawable() {
		if (null != mDrawable && mDrawable.isStateful()) {
			if (isChecked) {
				mDrawable.setState(STATE_SET_CHECKED);
			} else {
				mDrawable.setState(STATE_SET_UNCHECKED);
			}
			mImage.setImageDrawable(mDrawable.getCurrent());
		} else {
			mImage.setImageDrawable(mDrawable);
		}
	}
}
