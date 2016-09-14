package com.zritc.colorfulfund.data.model.wish;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/14.
 *
 * @version 1.0
 */

import java.io.Serializable;

/**
 * poBase
 */
public class WishPoBase implements Serializable {

    /**
     * 组合代码
     */
    public String poCode = "";

    /**
     * 组合列表
     */
    public String poName = "";

    /**
     * 年化收益率
     */
    public String expectedYearlyRoe = "";

    /**
     * 组合风险级别
     */
    public String riskLevel = "";

    @Override
    public String toString() {
        return "PoBase{" +
                "poCode='" + poCode + '\'' +
                ", poName='" + poName + '\'' +
                ", expectedYearlyRoe='" + expectedYearlyRoe + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                '}';
    }
}
