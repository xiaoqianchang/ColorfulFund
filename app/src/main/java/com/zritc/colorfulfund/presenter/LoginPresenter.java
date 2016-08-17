package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zritc.colorfulfund.activity.ZRActivityMain;
import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ILoginView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import retrofit2.Call;

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
        loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
            @Override
            public void onSuccess(Login login) {
                Toast.makeText(mContext, login.msg, Toast.LENGTH_SHORT).show();
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
                iView.hideProgress();
                ZRToastFactory.getToast(mContext, msg).show();
            }
        });
    }
}
