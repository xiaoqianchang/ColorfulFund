package com.zritc.colorfulfund.utils;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 字符串处理工具类
 * <p>
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public class StringUtils {

    /**
     * 校准精度
     * @param value 具体的值
     * @param scale 精度（小数几位）
     * @return
     */
    public static String adjustPrecision(double value, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        // 可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        // 模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * 格式化 money
     *
     * @param money
     * @return 2.00
     */
    public static String getMoneyByFormat(String money) {
        if (TextUtils.isEmpty(money)) {
            money = "0";
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        money = numberFormat.format(new Double(money));
        return money.substring(1);
    }

    /**
     *
     *
     * @param money
     * @return
     */
    public static double getMoneyByString(String money) {
        getMoneyByFormat(money);
        return Double.parseDouble(money);
    }

    public static boolean isZero(String money) {
        if (TextUtils.isEmpty(money)) {
            return true;
        }
        if ("0".equals(money)) {
            return true;
        }
        return false;
    }

    public static boolean isZero(double money) {
        if (money == 0 || money == 0.0 || money == 0.00) {
            return true;
        }
        return false;
    }

    public static String adjust(String value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(new Double(value));
    }
}
