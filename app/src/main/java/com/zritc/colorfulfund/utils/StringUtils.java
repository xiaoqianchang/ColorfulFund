package com.zritc.colorfulfund.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
}
