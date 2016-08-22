package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ICardManageView;

import retrofit2.Call;

/**
 * CardManagePresenter 卡片管理
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public class CardManagePresenter extends BasePresenter<ICardManageView> {

    public CardManagePresenter(Context context, ICardManageView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void userBankCards4C() {
        Call<GetUserBankCards4C> userBankCards4CCallbackByPost = ZRNetManager.getInstance().getUserBankCards4CCallbackByPost();
        userBankCards4CCallbackByPost.enqueue(new ResponseCallBack<GetUserBankCards4C>(GetUserBankCards4C.class) {
            @Override
            public void onSuccess(GetUserBankCards4C getUserBankCards4C) {
                iView.onSuccess(getUserBankCards4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
                    }

    public void unbindPayment(String paymentNo) {
        iView.showProgress("处理中...");
        Call<UnbindPayment> unbindPaymentCall = ZRNetManager.getInstance().unbindPaymentCallbackByPost(paymentNo);
        unbindPaymentCall.enqueue(new ResponseCallBack<UnbindPayment>(UnbindPayment.class) {
            @Override
            public void onSuccess(UnbindPayment unbindPayment) {
                    iView.hideProgress();
                iView.onSuccess(unbindPayment);
            }

            @Override
            public void onError(String code, String msg) {
                    iView.hideProgress();
                iView.onError(msg);
            }
        });
    }
}
