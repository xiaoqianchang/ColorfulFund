package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.user.SetTransPwd;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ITradePasswordSetView;

import retrofit2.Call;

/**
 * TradePasswordSetPresenter 交易密码设置
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public class TradePasswordSetPresenter extends BasePresenter<ITradePasswordSetView> {

    public TradePasswordSetPresenter(Context context, ITradePasswordSetView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void setTransPwd(String password) {
        iView.showProgress("处理中...");
        Call<SetTransPwd> setTransPwdCall = ZRNetManager.getInstance().setTransPwdCallbackByPost(password);
        setTransPwdCall.enqueue(new ResponseCallBack<SetTransPwd>(SetTransPwd.class) {
            @Override
            public void onSuccess(SetTransPwd setTransPwd) {
                iView.hideProgress();
                iView.onSuccess(setTransPwd);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
                    }
        });
    }
}
