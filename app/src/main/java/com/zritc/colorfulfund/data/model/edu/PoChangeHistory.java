package com.zritc.colorfulfund.data.model.edu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 基金持仓历史
 * Created by gufei on 16/9/14.
 */
public class PoChangeHistory implements Serializable {

    /**
     * 组合代码
     */
    public String poCode = "";

    /**
     * 组合列表
     */
    public String poName = "";

    /**
     * 激进型等等
     */
    public String riskType = "";

    /**
     * 年化收益率
     */
    public String expectedYearlyRoe = "";

    /**
     * 变更时间
     */
    public long updateTime;

    /**
     *
     */
    public List<PoFund> poFundList = new ArrayList<>();
    public List<PoChangeHistory> poChangeHistory = new ArrayList<>();

    @Override
    public String toString() {
        return "PoChangeHistory{" +
                "poCode='" + poCode + '\'' +
                ", poName='" + poName + '\'' +
                ", riskType='" + riskType + '\'' +
                ", expectedYearlyRoe='" + expectedYearlyRoe + '\'' +
                ", updateTime=" + updateTime +
                ", poFundList=" + poFundList +
                '}';
    }
}