package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IAddCardView;

import retrofit2.Call;

/**
 * AddCardPresenter 添加卡片
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public class AddCardPresenter extends BasePresenter<IAddCardView> {

    public AddCardPresenter(Context context, IAddCardView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void prepareBindPayment(String realName, String identityNo, String paymentType, String paymentNo, String phone) {
        Call<PrepareBindPayment> prepareBindPaymentCall = ZRNetManager.getInstance().prepareBindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone);
        prepareBindPaymentCall.enqueue(new ResponseCallBack<PrepareBindPayment>(PrepareBindPayment.class) {
            @Override
            public void onSuccess(PrepareBindPayment prepareBindPayment) {
                iView.hideProgress();
                iView.onSuccess(prepareBindPayment);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
                    }

    public void bindPayment(String realName, String identityNo, String paymentType, String paymentNo, String phone, String vCode) {
        iView.showProgress("处理中...");
        Call<BindPayment> bindPaymentCall = ZRNetManager.getInstance().bindPaymentCallbackByPost(realName, identityNo, paymentType, paymentNo, phone, vCode);
        bindPaymentCall.enqueue(new ResponseCallBack<BindPayment>(BindPayment.class) {
            @Override
            public void onSuccess(BindPayment bindPayment) {
                    iView.hideProgress();
                iView.onSuccess(bindPayment);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }
}
