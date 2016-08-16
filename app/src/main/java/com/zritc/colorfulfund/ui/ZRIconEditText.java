package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.util.ArrayList;

/**
 * Custom EditText with a icon on the left side.
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2015-12-17
 * @lastUpdate 2015-12-17
 */
public class ZRIconEditText extends ZREditText {
	private ImageView mIcon;
	private ArrayList<View.OnClickListener> mIconClickListeners = new ArrayList<View.OnClickListener>();

	private View.OnClickListener mIconClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			for (View.OnClickListener l : mIconClickListeners) {
				l.onClick(v);
			}
		}
	};

	public ZRIconEditText(Context context) {
		this(context, null, 0);
	}

	public ZRIconEditText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZRIconEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initLayout();
	}

	public void setIcon(int resId) {
		mIcon.setImageResource(resId);
	}

	public void setIcon(Drawable drawable) {
		mIcon.setImageDrawable(drawable);
	}

	private void initLayout() {
		setPadding(0, 0, 0, 0);

		mIcon = new ImageView(mContext);
		mIcon.setId(mIcon.hashCode());
		// mIcon.setImageResource(UPResourceManager.getResourceID("btn_contacts",
		// "drawable"));
		mIcon.setOnClickListener(mIconClickListener);
		mIcon.setPadding(0, 0, 0, 0);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		addView(mIcon, lp);

		lp = new RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.LEFT_OF, mIcon.getId());
		lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		lp.rightMargin = mContext.getResources().getDimensionPixelSize(
				ZRResourceManager.getResourceID("padding_12", "dimen"));
		mDelBtn.setLayoutParams(lp);

	}

	public void addOnIconClickListener(View.OnClickListener l) {
		mIconClickListeners.add(l);
	}
}
