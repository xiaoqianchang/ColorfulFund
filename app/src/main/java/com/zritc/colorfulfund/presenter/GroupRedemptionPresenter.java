package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetUserPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IGroupRedemptionView;

import retrofit2.Call;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/16.
 *
 * @version 1.0
 */
public class GroupRedemptionPresenter extends BasePresenter<IGroupRedemptionView> {
    public GroupRedemptionPresenter(Context context, IGroupRedemptionView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 组合赎回
     * @param poCode
     * @param amount
     */
    public void doGroupRedemption(String poCode, String amount) {
        iView.showProgress("开始赎回");
        Call<RedeemPo> redeemPoCall = ZRNetManager.getInstance().redeemPoCallbackByPost(poCode, amount);
        redeemPoCall.enqueue(new ResponseCallBack<RedeemPo>(RedeemPo.class) {
            @Override
            public void onSuccess(RedeemPo redeemPo) {
                iView.hideProgress();
                iView.onSuccess(redeemPo);
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
                iView.onError(msg);
            }
        });
    }

    /**
     * 获取我要赎回基金的详情
     * @param poCode
     */
    public void doGetUserPoInfo4C(String poCode) {
        Call<GetUserPoInfo4C> userPoInfo4CCallbackByPost = ZRNetManager.getInstance().getUserPoInfo4CCallbackByPost(poCode);
        userPoInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetUserPoInfo4C>(GetUserPoInfo4C.class) {
            @Override
            public void onSuccess(GetUserPoInfo4C getUserPoInfo4C) {
                iView.onSuccess(getUserPoInfo4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }
}
