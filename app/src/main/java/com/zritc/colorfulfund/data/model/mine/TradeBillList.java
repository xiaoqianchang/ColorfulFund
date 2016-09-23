package com.zritc.colorfulfund.data.model.mine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易账单列表模型
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class TradeBillList implements Serializable {

    public List<TradeBill> tradeBillList = new ArrayList<>(); // 交易账单列表

    public class TradeBill implements Serializable {
        public String poCode; // 组合代码
        public String fundName; // 基金名称
        public String fundProfit; // 基金收益
        public String fundAmount; // 基金金额

        public List<TradeBillDetail> tradeBillDetailList = new ArrayList<>(); // 当月内的账单
    }

    /**
     * 交易详情
     */
    public class TradeBillDetail implements Serializable {
        public String actionType; // 操作 - 申购，赎回，调仓等等 这里是文字，不是数字代码
        public String status; // 状态 - 未确认， 已确认等等 这里是文字，不是数字代码
        public String createDate; // 日期
        public String tradeMoney; // 交易额
    }
}
