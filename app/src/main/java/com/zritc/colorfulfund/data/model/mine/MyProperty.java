package com.zritc.colorfulfund.data.model.mine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的资产
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class MyProperty implements Serializable {

    public String totalIncome; // 总收益
    public String propertyTotalAmount; // 资产总额
    public List<Property> propertyList = new ArrayList<>(); // 资产列表

    /**
     * 单个资产
     */
    public class Property implements Serializable {
        // poBase
        public String poCode; // 组合代码
        public String poName; // 组合名
        public String expectedYearlyRoe; // 年化收益率
        public String riskLevel; // 组合风险级别

        // userPoAsset
        public String balance; // 余额
        public String income; // 收益
    }
}
