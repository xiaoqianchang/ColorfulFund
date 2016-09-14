package com.zritc.colorfulfund.activity.scene;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundGroupDetail;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.response.edu.CreateUserInvestmentPlan4Edu;
import com.zritc.colorfulfund.iView.ITargetSettingView;
import com.zritc.colorfulfund.presenter.TargetSettingPresenter;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.widget.DraggableCircleView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * ZRActivityTargetSetting 目标设定
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-31
 * @lastUpdate 2016-08-31
 */
public class ZRActivityTargetSetting extends ZRActivityBase<TargetSettingPresenter> implements ITargetSettingView {

    private static final int REQUEST_CODE_TARGET_SET = 0x111;

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.circle_target_money)
    DraggableCircleView circleTargetMoney;

    @Bind(R.id.circle_target_date)
    DraggableCircleView circleTargetDate;

    @Bind(R.id.edit_money)
    EditText editText;

    @Bind(R.id.text_each_money)
    TextView textEachMoney;

    @Bind(R.id.btn_next)
    Button btnNext;

    private TargetSettingPresenter targetSettingPresenter;

    private String poCode = "";
    private int targetDate = 1; // 定投期数年(公式中为n次方)
    private double targetAmount = 10000; // 预期收益元
    private double initialAmount = 0;//首付款元
    private double eachMoney = 0;//每年定投金额元
    private double x = 0;// 收益率

    @Override
    protected int getContentViewId() {
        return R.layout.activity_target_setting;
    }

    @Override
    protected void initPresenter() {
        targetSettingPresenter = new TargetSettingPresenter(this, this);
        targetSettingPresenter.init();
        targetSettingPresenter.getUserPoAssetInfo4C(poCode);
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            poCode = bundle.getString("poCode");
        }
    }

    @Override
    public void initView() {

        getExtraData();

        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });

        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    targetSettingPresenter.createUserInvestmentPlan4Edu(poCode, String.valueOf(targetDate), String.valueOf(targetAmount), String.valueOf(initialAmount));
                });

        circleTargetMoney.newBuilder()
                .setSmallTickMarkNum(10)
                .setEachCircleTotal(new double[]{10 * 10000, 10 * 20000, 10 * 50000, 10 * 100000})
                .setCurrentValue(10000)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setCircleColor(0xFF57DDD0)
                .setCircleRingColor(0xFF57DDD0)
                .setInnerCircleColor(0xFF57DDD0)
                .setGapBetweenCircleAndLine(getResources().getDimension(R.dimen.padding_2))
                .setLineLength(getResources().getDimension(R.dimen.padding_8))
                .setCircleRingStrokeWidth((int) getResources().getDimension(R.dimen.padding_20))
                .setMiddleText("目标金额")
                .setMiddleValue("10000")
                .setMiddleTextColor(0xFF57DDD0)
                .setMiddleValueColor(0xFF57DDD0)
                .build();

        circleTargetDate.newBuilder()
                .setSmallTickMarkNum(10)
                .setEachCircleTotal(new double[]{120}) // 12个月、6年、12年
                .setCurrentValue(12)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setCircleColor(0xFFFCD742)
                .setCircleRingColor(0xFFFCD742)
                .setInnerCircleColor(0xFFFCD742)
                .setGapBetweenCircleAndLine(getResources().getDimension(R.dimen.padding_2))
                .setLineLength(getResources().getDimension(R.dimen.padding_8))
                .setCircleRingStrokeWidth((int) getResources().getDimension(R.dimen.padding_20))
                .setMiddleText("达成日期")
                .setMiddleValue(String.valueOf(ZRUtils.getCurrentYear() + 1) + "." + (ZRUtils.getCurrentMonth() < 10 ? "0" + ZRUtils.getCurrentMonth() : ZRUtils.getCurrentMonth()))
                .setMiddleTextColor(0xFF57DDD0)
                .setMiddleValueColor(0xFF57DDD0)
                .build();

        cal();

        circleTargetMoney.setDraggableCircleListener(new DraggableCircleView.OnDraggableCircleListener() {
            @Override
            public void onDraggableCircleValueChange(double currentValue) {
                Log.d(TAG, "---" + currentValue);
            }

            @Override
            public void onDraggableCircleValueChanged(double currentValue) {
                targetAmount = currentValue;
                cal();
            }
        });

        circleTargetDate.setDraggableCircleListener(new DraggableCircleView.OnDraggableCircleListener() {
            @Override
            public void onDraggableCircleValueChange(double currentValue) {
                Log.d(TAG, "---" + currentValue);
            }

            @Override
            public void onDraggableCircleValueChanged(double currentValue) {
                targetDate = (int) currentValue;
                if (0 == targetDate)
                    targetDate = 1;
                cal();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()))
                    initialAmount = Double.parseDouble(s.toString());
                cal();
            }
        });
    }

    private void cal() {
        if (initialAmount > targetAmount) {
            showToast("首笔投资金额需小于目标金额");
            return;
        }
        if (initialAmount == 0) {
            eachMoney = targetAmount / ((1 + x) * (-1 + Math.pow((1 + x), targetDate) / x));
        } else {
            eachMoney = (targetAmount - initialAmount * Math.pow((1 + x), targetDate)) / ((1 + x) * (-1 + Math.pow((1 + x), targetDate)) / x);
        }
        textEachMoney.setText(String.format("建议每月定投%s元", ZRUtils.getDecimalFormat(eachMoney / 12)));
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof UserPoAssetInfo) {
            UserPoAssetInfo userPoAssetInfo = ((UserPoAssetInfo) object);
            if (TextUtils.isEmpty(userPoAssetInfo.expectedYearlyRoe))
                x = 0;
            else
                x = Double.valueOf(userPoAssetInfo.expectedYearlyRoe);
        } else if (object instanceof CreateUserInvestmentPlan4Edu) {
            Intent intent = new Intent();
            intent.setClass(this, ZRActivityFundGroupDetail.class);
            startActivityForResult(intent, REQUEST_CODE_TARGET_SET);
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TARGET_SET:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
