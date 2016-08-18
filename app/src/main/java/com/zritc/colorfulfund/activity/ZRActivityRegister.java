package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.iView.IRegisterView;
import com.zritc.colorfulfund.presenter.RegisterPresenter;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRNetUtils;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
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
    EditText mEdtUserName; // 用户名
    @Bind(R.id.edt_user_pass)
    EditText mEdtUserPass; // 密码
    @Bind(R.id.edt_auth_code)
    EditText mEdtAuthCode; // 验证码
    @Bind(R.id.tv_send_auth_code)
    TextView tvSendAuthCode; // 发送验证码
    @Bind(R.id.btn_register)
    Button btnRegister; // 注册
    @Bind(R.id.tv_to_login)
    TextView tvToLogin; // 已有账号

    private RegisterPresenter registerPresenter;
    private CountDownTimer countDownTimer;

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
        Intent intent = new Intent(this, ZRActivityPullToRefreshWebView.class);
        startActivity(intent);
    }

    @OnClick({R.id.img_back, R.id.tv_send_auth_code, R.id.btn_register, R.id.tv_to_login})
    public void onClick(View view) {
        Intent intent = null;
        String phone = mEdtUserName.getText().toString();
        String password = mEdtUserPass.getText().toString();
        String authCode = mEdtAuthCode.getText().toString();
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_send_auth_code:
                if (doPhoneValid()) {
                registerPresenter.sendAuthCode(phone);
                startTimeCount();
                }
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

    /**
     * 验证手机号
     * @return
     */
    private boolean doPhoneValid() {
        String account = mEdtUserName.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            showToast(getString(R.string.toast_mobile_empty));
            mEdtUserName.setFocusable(true);
            return false;
        } else {
            if (account.length() != 11) {
                showToast(getString(R.string.toast_mobile_error));
                mEdtUserName.setFocusable(true);
                return false;
            }
        }
        return true;
    }

    private boolean inputValidate() {
        if (!doPhoneValid()) {
            return false;
        }
        String password = mEdtUserPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ZRToastFactory.getToast(this, "请输入密码").show();
            return false;
        }
        return true;
    }

    private void startTimeCount() {
        countDownTimer = new CountDownTimer(60000, 1000) {
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

    @Override
    public void sendAuthCodeSucess() {
    }

    @Override
    public void sendAuthCodeFail(String msg) {
        showToast(msg);
        countDownTimer.onFinish();
        countDownTimer.cancel();
    }

    @Override
    public void registerSuccess() {
        // 注册成功，开始做登录
        registerPresenter.doLogin(mEdtUserName.getText().toString().trim(), mEdtUserPass.getText().toString().trim(), ZRNetUtils.getLocalIpAddress());
    }

    @Override
    public void registerFail(String msg) {
        showToast(msg);
    }

    @Override
    public void loginSuccess(Login login) {
        // 登录成功，保存状态
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_PHONE, mEdtUserName.getText().toString().trim());
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_PASSWORD, mEdtUserPass.getText().toString().trim());
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_SID, login.sid);
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_RID, login.rid);

        Intent intent = new Intent(mContext, ZRActivityMain.class);
        mContext.startActivity(intent);
    }

    @Override
    public void loginFail(String message) {
        showToast(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
