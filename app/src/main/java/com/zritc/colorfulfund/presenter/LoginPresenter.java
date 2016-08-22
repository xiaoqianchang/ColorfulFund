package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.user.Login;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ILoginView;
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

    public void doLogin(String loginName, String password) {
        iView.showProgress("开始登录...");
        Call<Login> loginCall = ZRNetManager.getInstance().loginCallbackByPost(loginName, password);
        loginCall.enqueue(new ResponseCallBack<Login>(Login.class) {
            @Override
            public void onSuccess(Login login) {
                iView.loginSuccess(login);
                iView.hideProgress();
            }

            @Override
            public void onError(String code, String msg) {
                ZRToastFactory.getToast(mContext, msg).show();
                iView.hideProgress();
            }
        });
    }
}
