package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.data.response.mine.EvaluateUserRiskLevel;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IRiskEvaluationIssueCompleteView;

import retrofit2.Call;

/**
 * 风险评测结果
 * <p>
 * Created by Chang.Xiao on 2016/9/22.
 *
 * @version 1.0
 */
public class RiskEvaluationIssueCompletePresenter extends BasePresenter<IRiskEvaluationIssueCompleteView> {

    private PersonalInfo personalInfo; // 个人资料模型-风险评估类型

    public RiskEvaluationIssueCompletePresenter(Context context, IRiskEvaluationIssueCompleteView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 评估用户风险等级
     *
     * @param answerId
     */
    public void doEvaluateUserRiskLevel(String answerId) {
        Call<EvaluateUserRiskLevel> evaluateUserRiskLevelCall = ZRNetManager.getInstance().evaluateUserRiskLevelCallbackByPost(answerId);
        evaluateUserRiskLevelCall.enqueue(new ResponseCallBack<EvaluateUserRiskLevel>(EvaluateUserRiskLevel.class) {
            @Override
            public void onSuccess(EvaluateUserRiskLevel evaluateUserRiskLevel) {
                evaluateUserRiskLevelConverter(evaluateUserRiskLevel);
                iView.onSuccess(personalInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     */
    private void evaluateUserRiskLevelConverter(EvaluateUserRiskLevel evaluateUserRiskLevel) {
        personalInfo = new PersonalInfo();
        personalInfo.riskLevel = evaluateUserRiskLevel.riskLevel;
        personalInfo.riskEvaluationType = getRiskLevel(evaluateUserRiskLevel.riskLevel);
    }

    private String getRiskLevel(String riskLevel) {
        if (riskLevel.equals("0"))
            return "未评估过";
        else if (riskLevel.equals("1"))
            return "保守型";
        else if (riskLevel.equals("2"))
            return "稳健型";
        else
            return "进取型";
    }
}
