package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;

/**
 * Custom button view.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRButton extends ZRTextView {
	public static final int STYLE_BLUE = 0;
	public static final int STYLE_GRAY = STYLE_BLUE + 1;
	public static final int STYLE_ORANGE = STYLE_GRAY + 1;
	public static final int STYLE_TITLE_LEFT = STYLE_ORANGE + 1;
	public static final int STYLE_TITLE_RIGHT = STYLE_TITLE_LEFT + 1;
	public static final int STYLE_DIALOG_LEFT = STYLE_TITLE_RIGHT + 1;
	public static final int STYLE_DIALOG_RIGHT = STYLE_DIALOG_LEFT + 1;
	public static final int STYLE_DIALOG_SINGLE = STYLE_DIALOG_RIGHT + 1;
	public static final int STYLE_GRAY_TEXTBLUE = STYLE_DIALOG_SINGLE + 1;

	int mStyle = STYLE_GRAY_TEXTBLUE;

	public ZRButton(Context context) {
		this(context, null, 0);
	}

	public ZRButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZRButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setGravity(Gravity.CENTER);
		setFocusable(true);
		setClickable(true);

		if (null != attrs) {
			TypedArray typeArray = context.obtainStyledAttributes(attrs,
					ZRResourceManager.getStyleableArray("ZRButton"));
			setStyle(context, typeArray);
			typeArray.recycle();
			setStyle(context);
		}
	}

	/**
	 * @param context
	 * @param a
	 */
	private void setStyle(Context context, TypedArray a) {
		int textIndex = ZRResourceManager.getStyleable("ZRButton_text");
		int textSizeIndex = ZRResourceManager.getStyleable("ZRButton_textSize");
		int textColorIndex = ZRResourceManager
				.getStyleable("ZRButton_textColor");
		int backgroundIndex = ZRResourceManager
				.getStyleable("ZRButton_background_");
		int paddingLeftIndex = ZRResourceManager
				.getStyleable("ZRButton_paddingLeft");
		int paddingTopIndex = ZRResourceManager
				.getStyleable("ZRButton_paddingTop");
		int paddingRightIndex = ZRResourceManager
				.getStyleable("ZRButton_paddingRight");
		int paddingBottomIndex = ZRResourceManager
				.getStyleable("ZRButton_paddingBottom");
		int styleIndex = ZRResourceManager.getStyleable("ZRButton_style");

		String textName = "";
		int textSize = 0;
		ColorStateList textColor = null;
		Drawable background = null;
		int paddingLeft = 0;
		int paddingTop = 0;
		int paddingRight = 0;
		int paddingBottom = 0;

		int n = a.getIndexCount();
		for (int i = 0; i < n; ++i) {
			int attr = a.getIndex(i);
			if (attr == textIndex) {
				textName = a.getString(attr);
			} else if (attr == textSizeIndex) {
				textSize = a.getDimensionPixelSize(attr, textSize);
			} else if (attr == textColorIndex) {
				textColor = a.getColorStateList(attr);
			} else if (attr == backgroundIndex) {
				background = a.getDrawable(attr);
			} else if (attr == paddingLeftIndex) {
				paddingLeft = (int) a.getDimension(attr, paddingLeft);
			} else if (attr == paddingTopIndex) {
				paddingTop = (int) a.getDimension(attr, paddingTop);
			} else if (attr == paddingRightIndex) {
				paddingRight = (int) a.getDimension(attr, paddingRight);
			} else if (attr == paddingBottomIndex) {
				paddingBottom = (int) a.getDimension(attr, paddingBottom);
			} else if (attr == styleIndex) {
				mStyle = a.getInt(attr, mStyle);
			}
		}

		if (!TextUtils.isEmpty(textName)) {
			setText(ZRStrings.get(context, textName));
		}

		if (0 != textSize) {
			setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		}
		if (null != textColor) {
			setTextColor(textColor);
		}
		if (null != background) {
			setBackgroundDrawable(background);
		}

		setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}

	public void setStyle(Context context) {
		setStyle(context, mStyle);
	}

	/**
	 * Set button style.
	 * 
	 * @param style
	 *            one of {@link #STYLE_BLUE}, {@link #STYLE_GRAY} and
	 *            {@link #STYLE_GREEN}
	 */
	public void setStyle(Context context, int style) {
		int resID = ZRResourceManager.getResourceID("ZRButton.Blue", "style");
		switch (style) {
		case STYLE_GRAY:
			resID = ZRResourceManager.getResourceID("ZRButton.Gray", "style");
			break;
		case STYLE_ORANGE:
			resID = ZRResourceManager.getResourceID(
					"ZRButton.Dialog.TextOrange", "style");
			break;
		case STYLE_TITLE_LEFT:
			resID = ZRResourceManager.getResourceID("ZRButton.Title.Left",
					"style");
			break;
		case STYLE_TITLE_RIGHT:
			resID = ZRResourceManager.getResourceID("ZRButton.Title.Right",
					"style");
			break;
		case STYLE_DIALOG_SINGLE:
			resID = ZRResourceManager.getResourceID("ZRButton.Dialog.Single",
					"style");
			break;
		case STYLE_GRAY_TEXTBLUE:
			resID = ZRResourceManager.getResourceID("ZRButton.Gray.TextBlue",
					"style");
			break;
		case STYLE_DIALOG_LEFT:
			resID = ZRResourceManager.getResourceID("ZRButton.Dialog.Left",
					"style");
			break;
		case STYLE_DIALOG_RIGHT:
			resID = ZRResourceManager.getResourceID("ZRButton.Dialog.Right",
					"style");
			break;
		}
		setStyleResID(context, resID);
	}

	/**
	 * Set button style.
	 * 
	 * @param resID
	 *            of a style.
	 */
	public void setStyleResID(Context context, int resID) {
		TypedArray a = getContext().obtainStyledAttributes(resID,
				ZRResourceManager.getStyleableArray("ZRButton"));
		setStyle(context, a);
		a.recycle();
	}
}
