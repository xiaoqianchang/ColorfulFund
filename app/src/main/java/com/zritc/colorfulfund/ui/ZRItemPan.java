package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;

import com.zritc.colorfulfund.ui.ZREditText.ZRTextWatcher;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pan input in payment form.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2013-05-15
 * @lastUpdate 2013-05-15
 */
public class ZRItemPan extends ZRItemTextInput {

    /*
     * Add spaces between pan.
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
                    if (i != 0 && (digitCount & 3) == 1) {
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
        public void onTextChanged(View v, CharSequence s, int start,
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
        public void beforeTextChanged(View v, CharSequence s, int start,
                                      int count, int after) {
            if (count == 1 && after == 0 && s.charAt(start) == ' ') {
                needRomoveOneDigit = true;
            }
        }

        @Override
        public void afterTextChanged(View v, Editable s) {
        }
    };

    public ZRItemPan(Context context, AttributeSet attrs) {
        this(context, attrs, null, null, null);
    }

    public ZRItemPan(Context context, String label, String hint, String name) {
        this(context, null, label, hint, name);
    }

    public ZRItemPan(Context context, AttributeSet attrs, String label,
                     String hint, String name) {
        super(context, attrs, label, hint, name);
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(
                VALID_MAX_PAN_LEN)});
    }

    @Override
    public boolean isValidate() {
        if (!mEditText.isEmpty()) {
            return verifyPan(getValue());
        }
        return false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mEditText.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onDetachedFromWindow() {
        mEditText.removeTextChangedListener(mTextWatcher);
        super.onDetachedFromWindow();
    }

    @Override
    public String getValue() {
        return mEditText.getContent().replaceAll(" ", "");
    }

    private boolean verifyPan(String pan) {
        Pattern cardPattern = Pattern.compile("\\\\d{13,19}");
        String tempCard = pan;
        Matcher cardMatcher = cardPattern.matcher(tempCard.replace(" ", ""));

        if (!cardMatcher.matches()) {
            return false;
        }
        int len = pan.length();
        int luhmSum = 0;
        for (int i = len - 2, j = 0; i >= 0; i--, j++) {
            int k = pan.charAt(i) - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        char checkCode = (luhmSum % 10 == 0) ? '0'
                : (char) ((10 - luhmSum % 10) + '0');
        return (checkCode == pan.charAt(len - 1));
    }

    @Override
    public void setReadOnly() {
        mEditText.setReadOnly();
        setBackgroundResource(ZRResourceManager.getResourceID(
                "bg_textbox_disable", "drawable"));
    }
}
