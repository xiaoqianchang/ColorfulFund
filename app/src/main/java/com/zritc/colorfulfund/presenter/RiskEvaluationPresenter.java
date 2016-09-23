package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.mine.RiskEvaluationLevelInfo;
import com.zritc.colorfulfund.iView.IRiskEvaluationView;

/**
 * 风险评估
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class RiskEvaluationPresenter extends BasePresenter<IRiskEvaluationView> {

    private RiskEvaluationLevelInfo riskEvaluationResult;

    public RiskEvaluationPresenter(Context context, IRiskEvaluationView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取数据
     */
    public void doGetData() {
        dataConverter();
        iView.onSuccess(riskEvaluationResult);
    }

    /**
     * 网络模型数据转换器
     */
    private void dataConverter() {
        riskEvaluationResult = new RiskEvaluationLevelInfo();
        riskEvaluationResult.riskLevel = "0";
        riskEvaluationResult.riskLevelType = "平衡型";
        riskEvaluationResult.riskLevelDescription = "可以购买保守型、平衡型的基金";
    }
}
