package com.zritc.colorfulfund.activity.mine;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 风险评测引导页
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class ZRActivityRiskEvaluationGuide extends ZRActivityBase {

    private static final int REQUEST_CODE_RISK_EVALUATION_GUIDE = 0x211;

    @Bind(R.id.imgBtn_close)
    ImageButton imgBtnClose;

    @Bind(R.id.btn_start_evaluation)
    Button btnStartEvaluation;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_risk_evaluation_guide;
    }

    @Override
    protected void initPresenter() {
        RxView.clicks(imgBtnClose).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        RxView.clicks(btnStartEvaluation).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivityForResult(new Intent(this, ZRActivityRiskEvaluationIssue.class), REQUEST_CODE_RISK_EVALUATION_GUIDE);
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == mActivity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_RISK_EVALUATION_GUIDE:
                    setResult(RESULT_OK, data);
                    finish();
                    break;
            }
        }
    }
}
