package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * ID input in payment form.
 * 
 * @author eric
 * @version 1.0
 * @createDate 2013-05-15
 * @lastUpdate 2013-05-15
 */
public class ZRItemID extends ZRItemTextInput {

    // 身份证
    public static final int TYPE_ID_CARD = 0;
    // 非身份证
    public static final int TYPE_OTHER_CARD = 1;

    private int mType;

    public ZRItemID(Context context, AttributeSet attrs) {
        this(context, attrs, "", "", "");
    }

//    public ZRItemID(Context context, String label, String hint, String name) {
//        this(context, null, label, hint, name);
//    }

    public ZRItemID(Context context, AttributeSet attrs, String label,
            String hint, String name) {
        super(context, attrs, label, hint, name);
        mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
                VALID_ID_LEN) });
    }

    public void setIDType(int type) {
        mType = type;
    }

    @Override
    public boolean isValidate() {
        if (!mEditText.isEmpty()) {
            if (mType == TYPE_ID_CARD) {
                String idNum = mEditText.getContent();
                int length = idNum.length();

                if (15 == length) {
                    return validateDate(idNum.substring(6, 12))
                            && idNum.matches("^[0-9]*$");
                } else if (18 == length) {
                    return validateDate(idNum.substring(6, 14))
                            && checkSum(idNum);
                }
            } else if (mType == TYPE_OTHER_CARD) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateDate(String date) {
        try {
            if (6 == date.length()) {
                int month = Integer.parseInt(date.substring(2, 4));
                int day = Integer.parseInt(date.substring(4));
                if (month > 0 && month < 13 && day > 0 && day < 31) {
                    return true;
                }
            } else {
                Date birthday = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                        .parse(date);
                if (new Date().before(birthday)) {
                    return false;
                }

                GregorianCalendar calendar = new GregorianCalendar();
                int month = Integer.parseInt(date.substring(4, 6));
                int day = Integer.parseInt(date.substring(6));
                if (month < 1 || month > 12) {
                    return false;
                }
                calendar.setTime(birthday);
                boolean isValidate = false;
                switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    isValidate = (day > 0 && day < 32);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    isValidate = (day > 0 && day < 31);
                    break;
                case 2:
                    if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
                        isValidate = (day > 0 && day < 30);
                    } else {
                        isValidate = (day > 0 && day < 29);
                    }
                    break;
                }
                return isValidate;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static  boolean checkSum(String id) {
        String idPre = id.substring(0, 17);
        String sumCode = id.substring(17);
        if (!idPre.matches("^[0-9]*$")) {
            return false;
        }

        int[] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        String[] verifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2" };

        int sum = 0;

        try {
            for (int i = 0; i < idPre.length(); ++i) {
                int ai = Integer.parseInt(String.valueOf(idPre.charAt(i)));
                sum += ai * power[i];
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (sumCode.equalsIgnoreCase(verifyCode[sum % 11])) {
            return true;
        } else {
            return false;
        }
    }
}
