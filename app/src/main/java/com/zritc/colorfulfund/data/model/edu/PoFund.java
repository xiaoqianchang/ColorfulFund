package com.zritc.colorfulfund.data.model.edu;

/**
 * Created by gufei on 16/9/13.
 */

import java.io.Serializable;

/**
 * poFundList
 */
public class PoFund implements Serializable {

    /**
     * 基金代码
     */
    public String fundCode = "";

    /**
     * 基金名称
     */
    public String fundName = "";

    /**
     * 基金百分比
     */
    public String poPercentage = "";

    @Override
    public String toString() {
        return "PoFund{" +
                "fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                ", poPercentage='" + poPercentage + '\'' +
                '}';
    }
}
