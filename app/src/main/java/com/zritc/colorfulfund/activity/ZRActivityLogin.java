package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.iView.ILoginView;
import com.zritc.colorfulfund.presenter.LoginPresenter;
import com.zritc.colorfulfund.utils.StringUtils;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRNetUtils;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
import com.zritc.colorfulfund.utils.ZRToastFactory;
import com.zritc.colorfulfund.utils.ZRUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A login screen that offers login via name/password.
 */
public class ZRActivityLogin extends ZRActivityBase<LoginPresenter> implements ILoginView {

    @Bind(R.id.img_back)
    ImageView imgBack; // 返回按钮
    @Bind(R.id.edt_user_name)
    EditText edtUserName; // 用户名
    @Bind(R.id.edt_user_pass)
    EditText edtUserPass; // 密码
    @Bind(R.id.tv_wechat_login)
    TextView tvWechatLogin; // 微信登录
    @Bind(R.id.btn_user_login)
    Button btnUserLogin; // 登录
    @Bind(R.id.tv_register)
    TextView tvRegister; // 注册

    private LoginPresenter loginPresenter;
    private String userNmae;
    private String userPass;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        loginPresenter = new LoginPresenter(this, this);
        loginPresenter.init();
    }

    @Override
    public void initView() {
        edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();
                btnUserLogin.setEnabled(!TextUtils.isEmpty(str));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获取上次登录时的账号
        String phone = ZRSharePreferenceKeeper.getStringValue(this, ZRConstant.KEY_PHONE);
        edtUserName.setText(phone);
        // 让光标在最后显示
        edtUserName.setSelection(phone.length());
    }

    @OnClick({R.id.img_back, R.id.tv_wechat_login, R.id.btn_user_login, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_wechat_login:
                ZRToastFactory.getToast(this, "请添加微信支持").show();
                break;
            case R.id.btn_user_login:
                if (inputValidate()) {
                    loginPresenter.doLogin(userNmae, userPass);
                }
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, ZRActivityRegister.class);
                startActivity(intent);
                break;
        }
    }

    private boolean inputValidate() {
        userNmae = edtUserName.getText().toString().trim();
        userPass = edtUserPass.getText().toString().trim();
        if (TextUtils.isEmpty(userNmae) || userNmae.length() != 11) {
            showToast("请您输入11位的手机号码");
            return false;
        }
        if (!StringUtils.isMobileNum2(userNmae)) {
            showToast("您输入的不是手机号码");
            return false;
        }
        if (TextUtils.isEmpty(userPass)) {
            showToast("请输入密码");
            return false;
        } else if (userPass.length() < 6) {
            showToast("密码至少为6位");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.release();
    }

    @Override
    public void loginSuccess(Login login) {
        // 登录成功，保存状态
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_PHONE, edtUserName.getText().toString().trim());
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_PASSWORD, edtUserPass.getText().toString().trim());
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_SID, login.sid);
        ZRSharePreferenceKeeper.keepStringValue(this, ZRConstant.KEY_RID, login.rid);
        ZRSharePreferenceKeeper.keepBooleanValue(this, ZRConstant.KEY_AUTO_LOGIN, true);

        Intent intent = new Intent(mContext, ZRActivityMain.class);
        mContext.startActivity(intent);
    }

    @Override
    public void loginFail(String message) {
        showToast(message);
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

