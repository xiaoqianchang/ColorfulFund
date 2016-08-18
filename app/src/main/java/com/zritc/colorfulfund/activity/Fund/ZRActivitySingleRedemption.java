package com.zritc.colorfulfund.activity.Fund;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.iView.ISingleRedemptionView;
import com.zritc.colorfulfund.presenter.SingleRedemptionPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 单支基金赎回
 * 
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public class ZRActivitySingleRedemption extends ZRActivityToolBar<SingleRedemptionPresenter> implements ISingleRedemptionView {

    @Bind(R.id.tv_cash)
    TextView tvCash; // 大成现金增利货币

    @Bind(R.id.tv_redemption_fee)
    TextView tvRedemptionFee; // 赎回费用

    @Bind(R.id.edt_redemption_money)
    EditText edtRedemptionMoney; // 赎回份额

    @Bind(R.id.tv_working_days)
    TextView tvWorkingDays;

    @Bind(R.id.btn_redemption)
    Button btnRedemption;

    private SingleRedemptionPresenter singleRedemptionPresenter;

    //
    private String cash;
    private String redemptionFee;
    private String redemptionMoney;
    private String workingDays;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_single_redemption;
    }

    @Override
    protected void initPresenter() {
        singleRedemptionPresenter = new SingleRedemptionPresenter(this, this);
        singleRedemptionPresenter.init();
    }

    @Override
    public void initView() {
        setTitleText("单支基金赎回");
        initData();

        tvCash.setText(cash);
        tvRedemptionFee.setText(redemptionFee);
        tvWorkingDays.setText(String.format(getResources().getString(R.string.working_days), workingDays));

        edtRedemptionMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             * 赎回份额的输入不可以大于持有的份额，若输入超过，则提示，“您输入的份额大于可赎回份额。”
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                // 判断输入的金额是否大于持有的份额
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    if (Double.parseDouble(s.toString().trim()) > Double.parseDouble(tvCash.getText().toString())) {
                    showToast("您输入的份额大于可赎回份额。");
                }
            }
            }
        });
    }

    /**
     * 构造数据
     */
    private void initData() {
        cash = "4600.00";
        redemptionFee = "123.00";
        workingDays = "2";
    }

    @OnClick(R.id.btn_redemption)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_redemption:
//                singleRedemptionPresenter.doRedemption("ZH000484", "25.00");
                startActivity(new Intent(this, ZRActivityRedemptionResult.class));
                break;
        }
    }

    @Override
    public void showProgress(CharSequence message) {
        showToast(message.toString());
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDatas(RedeemPo redeemPo) {

    }
}
