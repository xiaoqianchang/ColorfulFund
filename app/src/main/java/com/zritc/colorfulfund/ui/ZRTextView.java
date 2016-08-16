package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;

/**
 * Custom TextView.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRTextView extends TextView {
	public ZRTextView(Context context) {
		this(context, null, 0);
	}

	public ZRTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZRTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

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
				setText(ZRStrings.get(context, textName));
			}
			setTextAppearance(a);
			a.recycle();
		}
	}

	@Override
	public void setTextAppearance(Context context, int resid) {
		super.setTextAppearance(context, resid);

		TypedArray a = getContext().obtainStyledAttributes(resid,
				ZRResourceManager.getStyleableArray("ZRText"));
		setTextAppearance(a);

		a.recycle();
	}

	void setTextAppearance(TypedArray a) {
		int n = a.getIndexCount();
		int textSize = 0;
		ColorStateList textColor = null;
		int textSizeIndex = ZRResourceManager.getStyleable("ZRText_textSize");
		int textColorIndex = ZRResourceManager.getStyleable("ZRText_textColor");
		for (int i = 0; i < n; ++i) {
			int attr = a.getIndex(i);
			if (attr == textColorIndex) {
				textColor = a.getColorStateList(attr);
			} else if (attr == textSizeIndex) {
				textSize = a.getDimensionPixelSize(attr, textSize);
			}
		}
		if (null != textColor) {
			setTextColor(textColor);
		}

		if (0 != textSize) {
			setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		}
	}

//	@Override
//	protected void onDraw(Canvas canvas) {
//		Drawable[] drawables = getCompoundDrawables();
//		if (drawables != null) {
//			Drawable drawableLeft = drawables[0];
//			if (drawableLeft != null) {
//				float textWidth = getPaint().measureText(getText().toString());
//				int drawablePadding = getCompoundDrawablePadding();
//				int drawableWidth = 0;
//				drawableWidth = drawableLeft.getIntrinsicWidth();
//				float bodyWidth = textWidth + drawableWidth + drawablePadding;
//				canvas.translate((getWidth() - bodyWidth) / 2, 0);
//			}
//		}
//		super.onDraw(canvas);
//	}
}
