package com.zritc.colorfulfund.utils;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * <p>
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public class StringUtils {

    /**
     * 格式化 money 四舍五入
     *
     * @param money
     * @return 2.00
     */
    public static String getMoneyByFormat(String money) {
        try {
            if (TextUtils.isEmpty(money)) {
                money = "0.00";
            }
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
            money = numberFormat.format(new Double(money));
            return money.substring(1);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 格式化 money 四舍五入
     *
     * @param money
     * @return 2.00
     */
    public static String getMoneyByFormat(double money) {
        try {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
            return numberFormat.format(money).substring(1);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 金额是否为零
     *
     * @param money
     * @return
     */
    public static boolean isZero(String money) {
        if (TextUtils.isEmpty(money)) {
            return true;
        }
        if ("0".equals(money) || "0.0".equals(money) || "0.00".equals(money)) {
            return true;
        }
        return false;
    }

    /**
     * 金额是否为零
     *
     * @param money
     * @return
     */
    public static boolean isZero(double money) {
        if (money == 0 || money == 0.0 || money == 0.00) {
            return true;
        }
        return false;
    }

    public static boolean isMobileNum2(String num) {
        String str = "^((13[0-9])|(15[0-9])|(18[0-9])|(145)|(147))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(num);
        return m.matches();
    }
}
