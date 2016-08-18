package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IRegisterView;

import retrofit2.Call;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/11.
 *
 * @version 1.0
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public RegisterPresenter(Context context, IRegisterView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public void sendAuthCode(String loginName) {
        iView.showProgress(mContext.getString(R.string.tip_sendsmscode_processing));
        Call<PrepareRegisterAcc> prepareRegisterAccCall = ZRNetManager.getInstance().prepareRegisterAccCallbackByPost(loginName);
        prepareRegisterAccCall.enqueue(new ResponseCallBack<PrepareRegisterAcc>(PrepareRegisterAcc.class) {
            @Override
            public void onSuccess(PrepareRegisterAcc prepareRegisterAcc) {
                iView.hideProgress();
                iView.sendAuthCodeSucess();
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.sendAuthCodeFail(msg);
            }
        });
    }

    public void doRegister(String loginName, String password, String vCode) {
        iView.showProgress(mContext.getString(R.string.tip_register_processing));
        Call<RegisterAcc> registerAccCall = ZRNetManager.getInstance().registerAccCallbackByPost(loginName, password, vCode);
        registerAccCall.enqueue(new ResponseCallBack<RegisterAcc>(RegisterAcc.class) {
            @Override
            public void onSuccess(RegisterAcc registerAcc) {
                iView.hideProgress();
                iView.registerSuccess();
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.registerFail(msg);
            }
        });
    }

    public void doLogin(String loginName, String password, String ip) {
        iView.showProgress(mContext.getString(R.string.tip_login_processing));
        Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(loginName, password, ip);
        loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
            @Override
            public void onSuccess(Login login) {
                iView.hideProgress();
                iView.loginSuccess(login);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.loginFail(msg);
            }
        });
    }
}
