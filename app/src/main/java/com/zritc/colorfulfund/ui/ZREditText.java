package com.zritc.colorfulfund.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.util.ArrayList;

/**
 * Custom EditText. It contains a delete button, which will cause all text clear
 * up when clicked.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZREditText extends RelativeLayout {
	protected EditText mEditText;
	protected ImageView mDelBtn;
	protected Context mContext;
	private InputMethodManager mImm;

	private boolean isDelEnable = true;

	private ArrayList<OnFocusChangeListener> mFocusListeners = new ArrayList<OnFocusChangeListener>();
	private ArrayList<ZRTextWatcher> mTextWatchers = new ArrayList<ZRTextWatcher>();
	private ArrayList<OnClickListener> mClickListeners = new ArrayList<OnClickListener>();
	private ArrayList<OnClearTextListener> mOnClearTextListeners = new ArrayList<OnClearTextListener>();
	private ArrayList<OnEditorActionListener> mOnEditorActionListener = new ArrayList<OnEditorActionListener>();

	public interface ZRTextWatcher {
		public void onTextChanged(View view, CharSequence s, int start,
								  int before, int count);

		public void beforeTextChanged(View view, CharSequence s, int start,
									  int count, int after);

		public void afterTextChanged(View view, Editable s);
	}

	private OnEditorActionListener mEditorActionListener = new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			for (OnEditorActionListener l : mOnEditorActionListener) {
				l.onEditorAction(v, actionId, event);
				return true;
			}
			return false;
		}
	};

	private OnFocusChangeListener mFocusListener = new OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			for (OnFocusChangeListener listener : mFocusListeners) {
				listener.onFocusChange(v, hasFocus);
			}
			if (hasFocus && getText().length() > 0 && isDelEnable) {
				mDelBtn.setVisibility(View.VISIBLE);
			} else if (isDelEnable) {
				mDelBtn.setVisibility(View.INVISIBLE);
			} else {
				mDelBtn.setVisibility(View.GONE);
			}
			invalidate();
		}
	};

	private TextWatcher mTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.onTextChanged(ZREditText.this, s, start, before, count);
			}
			if (s.length() > 0 && isDelEnable) {
				mDelBtn.setVisibility(View.VISIBLE);
			} else if (isDelEnable) {
				mDelBtn.setVisibility(View.INVISIBLE);
			} else {
				mDelBtn.setVisibility(View.GONE);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.beforeTextChanged(ZREditText.this, s, start, count, after);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			for (ZRTextWatcher watch : mTextWatchers) {
				watch.afterTextChanged(ZREditText.this, s);
			}
		}
	};

	private OnClickListener mEditTextClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			for (OnClickListener l : mClickListeners) {
				if (null != l) {
					l.onClick(v);
				}
			}
		}
	};

	private OnClickListener mDelClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mEditText.setText("");
			for (OnClearTextListener l : mOnClearTextListeners) {
				l.onClearText();
			}
		}
	};

	public interface OnClearTextListener {
		public void onClearText();
	}

	public ZREditText(Context context) {
		this(context, null, 0);
	}

	public ZREditText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZREditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initLayout();
	}

	public void setError(CharSequence text) {
		mEditText.setError(text);
	}

	public void setHint(int resId) {
		mEditText.setHint(resId);
	}

	public void setHint(CharSequence text) {
		mEditText.setHint(text);
	}

	public void setHintColor(int color) {
		mEditText.setHintTextColor(color);
	}

	public CharSequence getHint() {
		return mEditText.getHint();
	}

	public Editable getText() {
		return mEditText.getText();
	}

	public String getContent() {
		return getText().toString().trim();
	}

	public boolean isEmpty() {
		return 0 == getContent().length();
	}

	public void addOnFocusChangeListener(OnFocusChangeListener l) {
		mFocusListeners.add(l);
	}

	public boolean removeOnFocusChangeListener(OnFocusChangeListener l) {
		return mFocusListeners.remove(l);
	}

	public void addOnClearTextListener(OnClearTextListener l) {
		mOnClearTextListeners.add(l);
	}

	public void addOnEditorActionListener(OnEditorActionListener l) {
		mOnEditorActionListener.add(l);
	}

	public boolean removeOnClearTextListener(OnClearTextListener l) {
		return mOnClearTextListeners.remove(l);
	}

	public boolean isEditTextFocused() {
		return mEditText.hasFocus();
	}

	public void setText(int resId) {
		mEditText.setText(resId);
	}

	public void setText(CharSequence text) {
		mEditText.setText(text);
	}

	public void setPassword() {
		mEditText.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	public void setSelection(int index) {
		mEditText.setSelection(index);
	}

	public void setFilters(InputFilter[] filters) {
		mEditText.setFilters(filters);
	}

	public void setInputType(int type) {
		mEditText.setInputType(type);
	}

	public void setEditTextTag(Object tag) {
		mEditText.setTag(tag);
	}

	public void setEditGravity(int gravity) {
		mEditText.setGravity(gravity);
	}

	public void setTextAppearance(Context context, int resid) {
		mEditText.setTextAppearance(context, resid);

		TypedArray a = getContext().obtainStyledAttributes(resid,
				ZRResourceManager.getStyleableArray("ZRText"));
		int n = a.getIndexCount();
		int textSize = 0;
		ColorStateList textColor = null;
		ColorStateList hintColor = null;
		int textSizeIndex = ZRResourceManager.getStyleable("ZRText_textSize");
		int textColorIndex = ZRResourceManager.getStyleable("ZRText_textColor");
		int hintColorIndex = ZRResourceManager.getStyleable("ZRText_hintColor");
		for (int i = 0; i < n; ++i) {
			int attr = a.getIndex(i);
			if (attr == textColorIndex) {
				textColor = a.getColorStateList(attr);
			} else if (attr == textSizeIndex) {
				textSize = a.getDimensionPixelSize(attr, textSize);
			} else if (attr == hintColorIndex) {
				hintColor = a.getColorStateList(attr);
			}
		}
		a.recycle();

		if (null != textColor) {
			mEditText.setTextColor(textColor);
		}

		if (0 != textSize) {
			mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		}

		if (null != hintColor) {
			mEditText.setHintTextColor(hintColor);
		}
	}

	public void addOnClickListener(OnClickListener l) {
		mClickListeners.add(l);
	}

	public boolean removeOnClickListener(OnClickListener l) {
		return mClickListeners.remove(l);
	}

	public void addTextChangedListener(ZRTextWatcher watcher) {
		mTextWatchers.add(watcher);
	}

	public boolean removeTextChangedListener(ZRTextWatcher watcher) {
		return mTextWatchers.remove(watcher);
	}

	public void setMinLines(int minlines) {
		mEditText.setMinLines(minlines);
	}

	public void setTextSize(float size) {
		mEditText.setTextSize(size);
	}

	public void setTextSize(int unit, float size) {
		mEditText.setTextSize(unit, size);
	}

	public void setSingleLine(boolean singleLine) {
		mEditText.setSingleLine(singleLine);
		if (singleLine) {
			mEditText.setEllipsize(TruncateAt.END);
		} else {
			mEditText.setGravity(Gravity.TOP);
		}
	}

	public void setReadOnly() {
		mEditText.setKeyListener(null);
		mEditText.setFocusable(false);
		setBackgroundResource(ZRResourceManager.getResourceID(
				"bg_edittext_gray", "drawable"));
	}

	@SuppressLint("NewApi")
	public void setReadOnly2() {
		mEditText.setKeyListener(null);
		mEditText.setFocusable(false);
		setBackgroundColor(ZRResourceManager.getResourceID("gray", "color"));
	}

	public void setDelEnable(boolean enable) {
		isDelEnable = enable;
		if (isDelEnable) {
			mDelBtn.setVisibility(View.INVISIBLE);
		} else {
			mDelBtn.setVisibility(View.GONE);
		}
	}

	public void setEditTextPadding(int left, int top, int right, int bottom) {
		mEditText.setPadding(left, top, right, bottom);
		mDelBtn.setPadding(0, 0, right, 0);
	}

	public void setCompoundDrawables(Drawable left, Drawable top,
			Drawable right, Drawable bottom) {
		mEditText.setCompoundDrawables(left, top, right, bottom);
	}

	public void setCompoundDrawablePadding(int padding) {
		mEditText.setCompoundDrawablePadding(padding);
	}

	public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
			Drawable top, Drawable right, Drawable bottom) {
		mEditText.setCompoundDrawablesWithIntrinsicBounds(left, top, right,
				bottom);
	}

	public void setCompoundDrawablesWithIntrinsicBounds(int left, int top,
			int right, int bottom) {
		mEditText.setCompoundDrawablesWithIntrinsicBounds(left, top, right,
				bottom);
	}

	public void setImeOptions(int imeOptions) {
		switch (imeOptions) {
		case EditorInfo.IME_ACTION_NEXT:
			mEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			break;
		case EditorInfo.IME_ACTION_DONE:
			mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
			break;
		case EditorInfo.IME_ACTION_SEND:
			mEditText.setImeOptions(EditorInfo.IME_ACTION_SEND);
			break;
		case EditorInfo.IME_ACTION_SEARCH:
			mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			break;
		default:
			mEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			break;
		}

	}

	@Override
	public int getId() {
		return mEditText.hashCode();
	}

	public int getParentId() {
		return getId();
	}

	public void getFocus() {
		mEditText.setFocusable(true);
		mEditText.setFocusableInTouchMode(true);
		mEditText.requestFocus();
	}

	public void hideSoftInput() {
		mImm.hideSoftInputFromWindow(mEditText.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mEditText.setOnFocusChangeListener(mFocusListener);
		mEditText.addTextChangedListener(mTextWatcher);
		mEditText.setOnEditorActionListener(mEditorActionListener);
	}

	@Override
	protected void onDetachedFromWindow() {
		mEditText.setOnFocusChangeListener(null);
		mEditText.removeTextChangedListener(mTextWatcher);
		mEditText.setOnEditorActionListener(null);
		super.onDetachedFromWindow();
	}

	private void initLayout() {
		mContext = getContext();

		mImm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		setGravity(Gravity.CENTER_VERTICAL);
		setFocusable(false);

		setAddStatesFromChildren(true);

		setBackgroundResource(0);

		mDelBtn = new ImageView(mContext);
		mDelBtn.setId(mDelBtn.hashCode());
		mDelBtn.setImageResource(ZRResourceManager.getResourceID("btn_del",
				"drawable"));
		mDelBtn.setVisibility(View.INVISIBLE);
		mDelBtn.setOnClickListener(mDelClickListener);
		LayoutParams rLp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		rLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		rLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		rLp.rightMargin = mContext.getResources().getDimensionPixelSize(
				ZRResourceManager.getResourceID("padding_15", "dimen"));
		addView(mDelBtn, rLp);

		mEditText = new EditText(mContext);
		mEditText.setId(mEditText.hashCode());
		mEditText.setSingleLine(true);
		mEditText.setEllipsize(TruncateAt.END);
		mEditText.setBackgroundResource(0);
		mEditText.setClickable(true);
		mEditText.setOnClickListener(mEditTextClickListener);
		setTextAppearance(mContext, ZRResourceManager.getResourceID(
				"ZREdit.Medium.LTBlack", "style"));
		int padding = mContext.getResources().getDimensionPixelSize(
				ZRResourceManager.getResourceID("padding_14", "dimen"));
		mEditText.setPadding(0, padding, padding, padding);
		rLp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		rLp.addRule(RelativeLayout.LEFT_OF, mDelBtn.getId());
		rLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		rLp.setMargins(0, 0, 0, 0);
		addView(mEditText, rLp);
		setPadding(0, 0, 0, 0);
	}
}
