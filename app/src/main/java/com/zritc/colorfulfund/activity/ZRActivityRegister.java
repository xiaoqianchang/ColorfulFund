package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IRegisterView;
import com.zritc.colorfulfund.presenter.RegisterPresenter;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshWebView;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册界面
 *
 * Created by Chang.Xiao on 2016/8/10.
 *
 * @version 1.0
 */
public class ZRActivityRegister extends ZRActivityBase<RegisterPresenter> implements IRegisterView {

    @Bind(R.id.img_back)
    ImageView imgBack; // 返回按钮
    @Bind(R.id.edt_user_name)
    EditText edtUserName; // 用户名
    @Bind(R.id.edt_user_pass)
    EditText edtUserPass; // 密码
    @Bind(R.id.edt_auth_code)
    EditText edtAuthCode; // 验证码
    @Bind(R.id.tv_send_auth_code)
    TextView tvSendAuthCode; // 发送验证码
    @Bind(R.id.btn_register)
    Button btnRegister; // 注册
    @Bind(R.id.tv_to_login)
    TextView tvToLogin; // 已有账号

    private RegisterPresenter registerPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {
        registerPresenter = new RegisterPresenter(this, this);
        registerPresenter.init();
    }

    @Override
    public void initView() {
        // 弹出多彩基金管家声明条款
//        Intent intent = new Intent(this, ZRActivityPullToRefreshWebView.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick({R.id.img_back, R.id.tv_send_auth_code, R.id.btn_register, R.id.tv_to_login})
    public void onClick(View view) {
        Intent intent = null;
        String phone = edtUserName.getText().toString();
        String password = edtUserPass.getText().toString();
        String authCode = edtAuthCode.getText().toString();
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_send_auth_code:
                if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    ZRToastFactory.getToast(this, "手机号不合法").show();
                    break;
                }
                registerPresenter.sendAuthCode(phone);
                startTimeCount();
                break;
            case R.id.btn_register:
                if (inputValidate()) {
                    registerPresenter.doRegister(phone, password, authCode);
                }
                break;
            case R.id.tv_to_login:
                intent = new Intent(this, ZRActivityLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    private boolean inputValidate() {
        String phone = edtUserName.getText().toString();
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            ZRToastFactory.getToast(this, "手机号不合法").show();
            return false;
        }
        String password = edtUserPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ZRToastFactory.getToast(this, "请输入密码").show();
            return false;
        }
        return true;
    }

    private void startTimeCount() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSendAuthCode.setEnabled(false);
                tvSendAuthCode.setTextColor(getResources().getColor(android.R.color.darker_gray));
                tvSendAuthCode.setText(millisUntilFinished / 1000 + "秒后可重发");
            }

            @Override
            public void onFinish() {
                tvSendAuthCode.setEnabled(true);
                tvSendAuthCode.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                tvSendAuthCode.setText("发送");
            }
        }.start();
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }
}
