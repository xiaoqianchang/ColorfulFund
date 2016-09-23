package com.zritc.colorfulfund.data.model.mine;

import java.io.Serializable;

/**
 * 个人资料模型-收藏
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class PersonalInfo implements Serializable {

    public String userId = ""; // 用户Id
    public String phone = ""; // 电话
    public String avatar = ""; // 头像
    public String nickName = ""; // 昵称

    public String myPropertyAmount = ""; // 我的资产
    public String bankCardNum = ""; // 绑定银行卡数量
    /**
     * 组合风险级别
     */
    public String riskLevel = "";
    public String riskEvaluationType = ""; // 风险评估类型
    public String riskLevelDescription; // 风险描述

    public MyProperty myProperty; // 我的资产
}
