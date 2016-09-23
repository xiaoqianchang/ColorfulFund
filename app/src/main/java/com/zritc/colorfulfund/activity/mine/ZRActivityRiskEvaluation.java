package com.zritc.colorfulfund.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.data.model.mine.RiskEvaluationLevelInfo;
import com.zritc.colorfulfund.iView.IRiskEvaluationView;
import com.zritc.colorfulfund.presenter.RiskEvaluationPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 风险评估界面
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRActivityRiskEvaluation extends ZRActivityToolBar<RiskEvaluationPresenter> implements IRiskEvaluationView {

    private static final int REQUEST_CODE_RISK_EVALUATION = 0x21;

    @Bind(R.id.tv_risk_level)
    TextView tvRiskLevel; // 封信等级

    @Bind(R.id.tv_risk_level_description)
    TextView tvRiskLevelDescription; // 风险等级描述

    @Bind(R.id.tv_again_evaluation)
    TextView tvAgainEvaluation; // 重新评测

    private RiskEvaluationPresenter presenter;
    private RiskEvaluationLevelInfo riskEvaluationResult;
    private PersonalInfo personalInfo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_risk_evaluation;
    }

    @Override
    protected void initPresenter() {
        presenter = new RiskEvaluationPresenter(this, this);
        presenter.init();
        // 获取数据
        presenter.doGetData();
    }

    @Override
    public void initView() {
        setTitleText("风险评测");
        getExtraData();
        refreshView();
        RxView.clicks(tvAgainEvaluation).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivityForResult(new Intent(this, ZRActivityRiskEvaluationIssue.class), REQUEST_CODE_RISK_EVALUATION);
                });
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            personalInfo = (PersonalInfo) bundle.getSerializable("personalInfo");
        }
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == mActivity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_RISK_EVALUATION:
                    setResult(RESULT_OK, data);
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof RiskEvaluationLevelInfo) {
            riskEvaluationResult = (RiskEvaluationLevelInfo) object;
            refreshView();
        }
    }

    private void refreshView() {
        if (null != personalInfo) {
            tvRiskLevel.setText(personalInfo.riskEvaluationType);
            tvRiskLevelDescription.setText(personalInfo.riskLevelDescription);
        } else {
            tvRiskLevel.setText(riskEvaluationResult.riskLevelType);
            tvRiskLevelDescription.setText(riskEvaluationResult.riskLevelDescription);
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
