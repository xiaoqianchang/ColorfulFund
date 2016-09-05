package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IFundGroupDetailView;

/**
 * FundGroupDetailPresenter 基金组合详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-30
 * @lastUpdate 2016-8-30
 */
public class FundGroupDetailPresenter extends BasePresenter<IFundGroupDetailView> {

    public FundGroupDetailPresenter(Context context, IFundGroupDetailView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void groupList (){
//        Call<GetFundPoList4C> fundPoList4CCallbackByPost = ZRNetManager.getInstance().getFundPoList4CCallbackByPost();
//        fundPoList4CCallbackByPost.enqueue(new ResponseCallBack<GetFundPoList4C>(GetFundPoList4C.class) {
//            @Override
//            public void onSuccess(GetFundPoList4C getFundPoList4C) {
//                iView.onSuccess(getFundPoList4C);
//            }
//
//            @Override
//            public void onError(String code, String msg) {
//                iView.onError(msg);
//            }
//        });
    }

}
