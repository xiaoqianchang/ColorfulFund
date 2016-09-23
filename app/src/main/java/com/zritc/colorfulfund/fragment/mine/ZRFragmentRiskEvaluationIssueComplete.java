package com.zritc.colorfulfund.fragment.mine;

import android.util.Log;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.mine.ZRActivityRiskEvaluationIssue;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.iView.IRiskEvaluationIssueCompleteView;
import com.zritc.colorfulfund.presenter.RiskEvaluationIssueCompletePresenter;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 风险评测结果
 * <p>
 * Created by Chang.Xiao on 2016/9/21.
 *
 * @version 1.0
 */
public class ZRFragmentRiskEvaluationIssueComplete extends ZRFragmentBase<RiskEvaluationIssueCompletePresenter> implements IRiskEvaluationIssueCompleteView {

    private static final String TAG = ZRFragmentRiskEvaluationIssueComplete.class.getSimpleName();

    @Bind(R.id.tv_risk_level)
    TextView tvRiskLevel; // 风险等级

    @Bind(R.id.tv_complete_evaluation)
    TextView tvCompleteEvaluation;

    private RiskEvaluationIssueCompletePresenter presenter;
    private String answerId; // 答案Id, 传递的是一个字符串,每个answerId使用逗号进行分隔
    public String riskEvaluationType; // 风险评估类型
    private ZRActivityRiskEvaluationIssue parentActivity; // 所属activity

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_risk_evaluation_complete;
    }

    @Override
    protected void initPresenter() {
        presenter = new RiskEvaluationIssueCompletePresenter(mContext, this);
        presenter.init();

        parentActivity = (ZRActivityRiskEvaluationIssue) getActivity();
        RxView.clicks(tvCompleteEvaluation).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    // 完成，返回
                    parentActivity.completeRiskEvaluation();
                });
    }

    @Override
    public void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            answerId = parentActivity.getAnswerId();
            presenter.doEvaluateUserRiskLevel(answerId);
            Log.d(TAG, "answerId:" + answerId);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof PersonalInfo) {
            PersonalInfo personalInfo = (PersonalInfo) object;
            riskEvaluationType = personalInfo.riskEvaluationType;
            refreshView();
        }
    }

    private void refreshView() {
        tvRiskLevel.setText(riskEvaluationType);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    public String getRiskEvaluationType() {
        return riskEvaluationType;
    }
}
