package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;

import com.zritc.colorfulfund.ui.ZREditText.ZRTextWatcher;

/**
 * Mobile input.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRItemMobile extends ZRItemTextInput {

	private static final String REG_MOBILE_STR = "1[34578]\\d{9}";
	private static final String REG_PHONE_STR = "^\\d{3}-?\\d{8}|\\d{4}-?\\d{8}$";

	/*
	 * Add spaces between mobile.
	 */
	private final ZRTextWatcher mTextWatcher = new ZRTextWatcher() {
		private boolean needProcess = true;
		// cursor position
		private int caret;
		private boolean needRomoveOneDigit = false;

		private String format(CharSequence s, int oldCaret) {
			int len = s.length();
			StringBuffer buffer = new StringBuffer();
			int digitCount = 0;
			for (int i = 0; i < len; i++) {
				char c = s.charAt(i);
				if (c != ' ') {
					digitCount++;
					if (digitCount == 4 || digitCount == 8) {
						buffer.append(' ');
					}
				}
				if (i == oldCaret) {
					caret = buffer.length();
				}
				if (c != ' ') {
					buffer.append(c);
				}
			}

			if (oldCaret == len) {
				caret = buffer.length();
			}
			return buffer.toString();
		}

		@Override
		public void onTextChanged(View view, CharSequence s, int start,
				int before, int count) {
			if (!needProcess) {
				return;
			}

			CharSequence source = s;
			if (needRomoveOneDigit) {
				source = s.subSequence(0, start - 1);
				if (start < s.length()) {
					source = source.toString()
							+ s.subSequence(start, s.length());
				}
				start--;
				needRomoveOneDigit = false;
			}

			needProcess = false;
			String formatStr = format(source, start + count);
			mEditText.setText(formatStr);
			mEditText.setSelection(caret);
			needProcess = true;
		}

		@Override
		public void beforeTextChanged(View view, CharSequence s, int start,
				int count, int after) {
			if (count == 1 && after == 0 && s.charAt(start) == ' ') {
				needRomoveOneDigit = true;
			}
		}

		@Override
		public void afterTextChanged(View view, Editable s) {
		}
	};

	public ZRItemMobile(Context context, AttributeSet attrs) {
		this(context, attrs, null, null, null);
	}

	public ZRItemMobile(Context context, String label, String hint, String name) {
		this(context, null, label, hint, name);
	}

	public ZRItemMobile(Context context, AttributeSet attrs, String label,
			String hint, String name) {
		super(context, attrs, label, hint, name);

		mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
		mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				VALID_MOBILE_LEN) });
		mEditText.addTextChangedListener(mTextWatcher);
	}

	@Override
	public boolean isValidate() {
		if (!mEditText.isEmpty()) {
			return getValue().matches(REG_MOBILE_STR)
					|| getValue().matches(REG_PHONE_STR);
		}
		return false;
	}

	@Override
	public String getValue() {
		return mEditText.getContent().replaceAll(" ", "");
	}
}
