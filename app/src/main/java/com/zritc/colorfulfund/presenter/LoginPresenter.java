package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.zritc.colorfulfund.activity.ZRActivityLogin;
import com.zritc.colorfulfund.activity.ZRActivityMain;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.data.response.user.RegisterAcc;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ILoginView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginPresenter
 * <p>
 * Created by Chang.Xiao on 2016/8/11.
 *
 * @version 1.0
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(Context context, ILoginView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
    }

    public void doLogin(String loginName, String password, String ip) {
        iView.showProgress("开始登录...");
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
                iView.hideProgress();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                iView.hideProgress();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
