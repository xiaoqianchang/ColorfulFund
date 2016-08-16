package com.zritc.colorfulfund.activity.CardManage;

import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.ITradePasswordSetView;
import com.zritc.colorfulfund.presenter.TradePasswordSetPresenter;
import com.zritc.colorfulfund.ui.ZRItemTextInput;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

public class ZRActivityTradePasswordSet extends ZRActivityToolBar<TradePasswordSetPresenter> implements ITradePasswordSetView {

    @Bind(R.id.id_edt_card_trade_password)
    ZRItemTextInput edtCardTradePassword;
    @Bind(R.id.id_btn_next)
    Button btnNext;

    private TradePasswordSetPresenter tradePasswordSetPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_trade_password_set;
    }

    @Override
    protected void initPresenter() {
        tradePasswordSetPresenter = new TradePasswordSetPresenter(this, this);
        tradePasswordSetPresenter.init();
    }

    @Override
    public void initView() {

        setTitleText("交易密码设置");

        edtCardTradePassword.setBackgroundDrawable(null);
        edtCardTradePassword.setHint("请设置您的支付密码");

        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    if (btnNext.getText().toString().equals("下一步")) {
                        btnNext.setText("完成");
                        edtCardTradePassword.setHint("请再次输入您的支付密码");
                    } else {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }
}
