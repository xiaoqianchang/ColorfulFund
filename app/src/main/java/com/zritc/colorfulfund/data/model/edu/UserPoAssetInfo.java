package com.zritc.colorfulfund.data.model.edu;

import java.io.Serializable;
import java.util.List;

/**
 * 资产配置
 * Created by gufei on 16/9/13.
 */
public class UserPoAssetInfo implements Serializable {
    /**
     * 年化收益率
     */
    public String expectedYearlyRoe = "";

    /**
     * 组合风险级别
     */
    public String riskLevel = "";

    public String getRiskLevel() {
        if (riskLevel.equals("0"))
            return "未评估过";
        else if (riskLevel.equals("1"))
            return "保守型";
        else if (riskLevel.equals("2"))
            return "稳健型";
        else
            return "进取型";
    }

    /**
     * 初始投资
     */
    public String initialtAmount = "";

    /**
     * 每月定投
     */
    public String profitbythismonth = "";

    /**
     * 目标完成预期时间
     */
    public String targetDate;

    /**
     * 目标资产
     */
    public String targetAmount = "";

    /**
     * 当前收益
     */
    public String totalProfit = "";

    /**
     * 当前资产
     */
    public String totalAmount = "";

    /**
     * 场景类型
     */
    public String sceneType = "";

    /**
     * 场景ID
     */
    public long sceneId;

    @Override
    public String toString() {
        return "UserPoAssetInfo{" +
                "expectedYearlyRoe='" + expectedYearlyRoe + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                ", initialtAmount='" + initialtAmount + '\'' +
                ", profitbythismonth='" + profitbythismonth + '\'' +
                ", targetDate=" + targetDate +
                ", targetAmount='" + targetAmount + '\'' +
                ", totalProfit='" + totalProfit + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", sceneType='" + sceneType + '\'' +
                ", sceneId=" + sceneId +
                ", poFundList=" + poFundList +
                '}';
    }

    /**
     *
     */
    public List<PoFund> poFundList;

}
