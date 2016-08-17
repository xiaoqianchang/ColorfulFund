package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zritc.colorfulfund.activity.ZRActivityMain;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IRegisterView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRNetUtils;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        iView.showProgress("正在发送验证码...");
        Call<PrepareRegisterAcc> prepareRegisterAccCall = ZRNetManager.getInstance().prepareRegisterAccCallbackByPost(loginName);
        prepareRegisterAccCall.enqueue(new ResponseCallBack<PrepareRegisterAcc>(PrepareRegisterAcc.class) {
            @Override
            public void onSuccess(PrepareRegisterAcc prepareRegisterAcc) {
                showToast(prepareRegisterAcc.msg);
                iView.hideProgress();
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                showToast(msg);
            }
        });
    }

    public void doRegister(String loginName, String password, String vCode) {
        iView.showProgress("注册...");
        Call<RegisterAcc> registerAccCall = ZRNetManager.getInstance().registerAccCallbackByPost(loginName, password, vCode);
        registerAccCall.enqueue(new ResponseCallBack<RegisterAcc>(RegisterAcc.class) {
            @Override
            public void onSuccess(RegisterAcc registerAcc) {
                showToast(registerAcc.msg);
                iView.hideProgress();
                    doLogin(loginName, password, ZRNetUtils.getLocalIpAddress());
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                showToast(msg);
            }
        });
    }

    public void doLogin(String loginName, String password, String ip) {
        iView.showProgress("登录...");
        Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(loginName, password, ip);
        loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
            @Override
            public void onSuccess(Login login) {
                showToast(login.msg);
                        // 登录成功，保存状态
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PHONE, loginName);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PASSWORD, password);
                ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_SID, login.sid);
                ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_RID, login.rid);
                    Intent intent = new Intent(mContext, ZRActivityMain.class);
                    mContext.startActivity(intent);
                iView.hideProgress();
            }

            @Override
            public void onError(String code, String msg) {
                showToast(msg);
                iView.hideProgress();
            }
        });
    }
}
