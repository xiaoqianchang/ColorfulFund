package com.zritc.colorfulfund.activity.CardManage;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.iView.ITradePasswordSetView;
import com.zritc.colorfulfund.presenter.TradePasswordSetPresenter;
import com.zritc.colorfulfund.ui.ZREditText;
import com.zritc.colorfulfund.ui.ZRItemTextInput;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

public class ZRActivityTradePasswordSet extends ZRActivityToolBar<TradePasswordSetPresenter> implements ITradePasswordSetView {

    @Bind(R.id.id_edt_card_trade_password)
    ZRItemTextInput edtCardTradePassword;
    @Bind(R.id.id_btn_next)
    Button btnNext;

    private String password = "";
    private String repassword = "";

    private TradePasswordSetPresenter tradePasswordSetPresenter;

    ZREditText.ZRTextWatcher textWatcher = new ZREditText.ZRTextWatcher() {
        @Override
        public void onTextChanged(View view, CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(View view, CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(View view, Editable s) {
            String _password = edtCardTradePassword.getValue().toString();
            if (isFirstPage()) {
                password = _password;
            } else {
                repassword = _password;
            }
            boolean enable = _password.isEmpty();
            btnNext.setEnabled(!enable);
        }
    };

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
        edtCardTradePassword.addTextChangedListener(textWatcher);

        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    if (isFirstPage()) {
                        btnNext.setText("完成");
                        btnNext.setEnabled(false);
                        edtCardTradePassword.setValue("");
                        edtCardTradePassword.setHint("请再次输入您的支付密码");
                    } else {
                        if (password.equals(repassword))
                            tradePasswordSetPresenter.setTransPwd(password);
                        else {
                            AlertDialog.Builder
                                    builder = new AlertDialog.Builder(
                                    this);
                            builder.setMessage("密码输入不一致");
                            builder.setCancelable(true);
                            builder.setPositiveButton("重新输入", (DialogInterface dialog, int which) -> {
                                dialog.cancel();
                            });
                            builder.create().show();
                        }
                    }
                });
    }

    private boolean isFirstPage() {
        return btnNext.getText().toString().equals("下一步");
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
        if (object instanceof SetTransPwd) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onError(String msg) {
        hideProgress();
        showToast(msg);
    }
}
