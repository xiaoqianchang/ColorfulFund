package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zritc.colorfulfund.activity.ZRActivityMain;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.PrepareRegisterAcc;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
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
        prepareRegisterAccCall.enqueue(new Callback<PrepareRegisterAcc>() {
            @Override
            public void onResponse(Call<PrepareRegisterAcc> call, Response<PrepareRegisterAcc> response) {
                try {
                    PrepareRegisterAcc body = response.body();
                    if (null != body) {
                        Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iView.hideProgress();
            }

            @Override
            public void onFailure(Call<PrepareRegisterAcc> call, Throwable t) {
                iView.hideProgress();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void doRegister(String loginName, String password, String vCode) {
        iView.showProgress("注册...");
        Call<RegisterAcc> registerAccCall = ZRNetManager.getInstance().registerAccCallbackByPost(loginName, password, vCode);
        registerAccCall.enqueue(new Callback<RegisterAcc>() {
            @Override
            public void onResponse(Call<RegisterAcc> call, Response<RegisterAcc> response) {
                try {
                    RegisterAcc body = response.body();
                    if (null != body) {
                        Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                    }
                    doLogin(loginName, password, ZRNetUtils.getLocalIpAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iView.hideProgress();
            }

            @Override
            public void onFailure(Call<RegisterAcc> call, Throwable t) {
                iView.hideProgress();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void doLogin(String loginName, String password, String ip) {
        iView.showProgress("登录...");
        Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(loginName, password, ip);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    Login body = response.body();
                    if (null != body) {
                        Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                        // 登录成功，保存状态
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PHONE, loginName);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_PASSWORD, password);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_SID, body.sid);
                        ZRSharePreferenceKeeper.keepStringValue(mContext, ZRConstant.KEY_RID, body.rid);
                    }
                    Intent intent = new Intent(mContext, ZRActivityMain.class);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
