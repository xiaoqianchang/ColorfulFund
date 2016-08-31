package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;

import com.zritc.colorfulfund.ui.ZREditText.ZRTextWatcher;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.util.ArrayList;

/**
 * Normal text input.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRItemTextInput extends ZRItemBase {
	protected static final int VALID_SMS_LEN = 6;
	protected static final int VALID_MOBILE_LEN = 13;
	protected static final int VALID_ID_LEN = 20;
	protected static final int VALID_MAX_PAN_LEN = 23;
	protected ZREditText mEditText;

	private ArrayList<ZRTextWatcher> mTextWatchers = new ArrayList<ZRTextWatcher>();

	protected ZRTextWatcher mTextWatcher = new ZRTextWatcher() {
		@Override
		public void onTextChanged(View view, CharSequence s, int start,
				int before, int count) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.onTextChanged(ZRItemTextInput.this, s, start, before,
						count);
			}
		}

		@Override
		public void beforeTextChanged(View view, CharSequence s, int start,
				int count, int after) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.beforeTextChanged(ZRItemTextInput.this, s, start, count,
						after);
			}
		}

		@Override
		public void afterTextChanged(View view, Editable s) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.afterTextChanged(ZRItemTextInput.this, s);
			}
		}
	};

	public ZRItemTextInput(Context context, String label, String hint,
			String name) {
		this(context, null, label, hint, name);
	}

	public ZRItemTextInput(Context context, String label, String hint,
			String name, boolean needBackgroudResource) {
		this(context, null, label, hint, name, needBackgroudResource,
				LinearLayout.HORIZONTAL);
	}

	public ZRItemTextInput(Context context, AttributeSet attrs) {
		this(context, attrs, null, null, null);
	}

	public ZRItemTextInput(Context context, AttributeSet attrs, String label,
			String hint, String name) {
		this(context, attrs, label, hint, name, true, LinearLayout.HORIZONTAL);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param label
	 * @param hint
	 * @param name
	 * @param needBackgroudResource
	 * @param orientation
	 */
	public ZRItemTextInput(Context context, AttributeSet attrs, String label,
			String hint, String name, boolean needBackgroudResource,
			int orientation) {
		super(context, attrs, label, name, orientation);

		if (needBackgroudResource) {
			setBackgroundResource(ZRResourceManager.getResourceID("bg_textbox",
					"drawable"));
		}
		setAddStatesFromChildren(true);

		mEditText = onCreateEditText(context);
		mEditText.setBackgroundDrawable(null);
		mEditText.setHint(hint);
		mEditText
				.setTextAppearance(context, ZRResourceManager.getResourceID(
						"TFEdit.Medium.Black", "style"));
		mEditText.setPadding(0, 0, 0, 0);
		LinearLayout.LayoutParams lp;
		// if (View.VISIBLE == mLabel.getVisibility()) {
		// lp = new LayoutParams(0,
		// android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		// lp.weight = WEIGHT_SUM - WEIGHT_LABEL;
		// } else {
		// lp = new LayoutParams(
		// android.view.ViewGroup.LayoutParams.FILL_PARENT,
		// android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		// }
		// *** place line93-101
		lp = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		// ***
		lp.gravity = Gravity.CENTER_VERTICAL;
		lp.setMargins(0, 0, 0, 0);
		addView(mEditText, lp);
		mEditText.setFocusable(true);
		mEditText.setFocusableInTouchMode(true);
		mEditText.addTextChangedListener(mTextWatcher);
	}

	public void addTextChangedListener(ZRTextWatcher listener) {
		mTextWatchers.add(listener);
	}

	public void addOnClickListener(View.OnClickListener l) {
		mEditText.addOnClickListener(l);
	}

	@Override
	public void setValue(String value) {
		mEditText.setText(value);
	}

	@Override
	public int getId() {
		return mEditText.getId();
	}

	@Override
	public Object getValue() {
		return mEditText.getContent();
	}

	@Override
	public void setHint(String hint) {
		mEditText.setHint(hint);
	}

	public CharSequence getHint() {
		return mEditText.getHint();
	}

	@Override
	public boolean isValidate() {
		return !mEditText.isEmpty();
	}

	public void setReadOnly() {
		mEditText.setReadOnly();
	}

	public boolean isEmpty() {
		return mEditText.isEmpty();
	}

	public void setImeOptions(int imeOptions) {
		mEditText.setImeOptions(imeOptions);
	}

	public void addOnEditorActionListener(OnEditorActionListener l) {
		mEditText.addOnEditorActionListener(l);
	}

	public void getFocus() {
		mEditText.getFocus();
	}

	public void setMinLines(int minlines) {
		mEditText.setMinLines(minlines);
	}

	public void setSingleLine(boolean singleLine) {
		mEditText.setSingleLine(singleLine);
	}

	public void setDelEnable(boolean enable) {
		mEditText.setDelEnable(enable);
	}

	public void setSelection(int index) {
		mEditText.setSelection(index);
	}

	public void setPassword() {
		mEditText.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	protected ZREditText onCreateEditText(Context context) {
		return new ZREditText(context);
	}

	public void setInputType(int type) {
		mEditText.setInputType(type);
	}

	public void setFilters(int length) {
		mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				length) });
	}

	public void setError(CharSequence error){
		mEditText.setError(error);
	}
}
