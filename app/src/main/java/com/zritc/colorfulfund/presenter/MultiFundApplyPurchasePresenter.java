package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.BuyPo;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetFundPoInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IMultiFundApplyPurchaseView;

import retrofit2.Call;

/**
 * MultiFundApplyPurchasePresenter 多只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class MultiFundApplyPurchasePresenter extends BasePresenter<IMultiFundApplyPurchaseView> {

    public MultiFundApplyPurchasePresenter(Context context, IMultiFundApplyPurchaseView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    /**
     * 基金组合详情
     *
     * @param poCode
     */
    public void fundPoInfo4C(String poCode) {
        Call<GetFundPoInfo4C> fundPoInfo4CCallbackByPost = ZRNetManager.getInstance().getFundPoInfo4CCallbackByPost(poCode);
        fundPoInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetFundPoInfo4C>(GetFundPoInfo4C.class) {
            @Override
            public void onSuccess(GetFundPoInfo4C getFundPoInfo4C) {
                iView.onSuccess(getFundPoInfo4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
                    }

    /**
     * 购买基金
     *
     * @param paymentId
     * @param poCode
     * @param amount
     */
    public void buyPo(String poCode, String amount, String paymentId) {
        iView.showProgress("处理中...");
        Call<BuyPo> buyPoCall = ZRNetManager.getInstance().buyPoCallbackByPost(poCode, amount, paymentId);
        buyPoCall.enqueue(new ResponseCallBack<BuyPo>(BuyPo.class) {
            @Override
            public void onSuccess(BuyPo buyPo) {
                iView.hideProgress();
                iView.onSuccess(buyPo);
            }

            @Override
            public void onError(String code, String msg) {
                    iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    /**
     * 估算申购费用
     *
     * @param tradeType
     * @param fundCode
     * @param amount
     */
    public void doEstimateBuyFundFee(String tradeType, String fundCode, String amount) {
        Call<EstimateBuyFundFee> estimateBuyFundFeeCall = ZRNetManager.getInstance().estimateBuyFundFeeCallbackByPost(tradeType, fundCode, amount);
        estimateBuyFundFeeCall.enqueue(new ResponseCallBack<EstimateBuyFundFee>(EstimateBuyFundFee.class) {
            @Override
            public void onSuccess(EstimateBuyFundFee estimateBuyFundFee) {
                iView.onSuccess(estimateBuyFundFee);
            }

            @Override
            public void onError(String code, String msg) {
//                iView.onError(msg);
            }
        });
    }

}
