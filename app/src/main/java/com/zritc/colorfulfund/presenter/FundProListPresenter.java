package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IFundProListView;

import retrofit2.Call;

/**
 * FundProListPresenter 基金列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-19
 * @lastUpdate 2016-8-19
 */
public class FundProListPresenter extends BasePresenter<IFundProListView> {

    public FundProListPresenter(Context context, IFundProListView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void fundPoList4C (){
        Call<GetFundPoList4C> fundPoList4CCallbackByPost = ZRNetManager.getInstance().getFundPoList4CCallbackByPost();
        fundPoList4CCallbackByPost.enqueue(new ResponseCallBack<GetFundPoList4C>(GetFundPoList4C.class) {
            @Override
            public void onSuccess(GetFundPoList4C getFundPoList4C) {
                iView.onSuccess(getFundPoList4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

}
