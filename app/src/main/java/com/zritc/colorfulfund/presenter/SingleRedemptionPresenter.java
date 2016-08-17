package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ISingleRedemptionView;

import retrofit2.Call;

/**
 * 单支基金赎回presenter
 * <p>
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public class SingleRedemptionPresenter extends BasePresenter<ISingleRedemptionView> {

    public SingleRedemptionPresenter(Context context, ISingleRedemptionView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public void doRedemption(String poCode, String amount) {
        iView.showProgress("开始赎回...");
        Call<RedeemPo> redeemPoCall = ZRNetManager.getInstance().redeemPoCallbackByPost(poCode, amount);
        redeemPoCall.enqueue(new ResponseCallBack<RedeemPo>(RedeemPo.class) {
            @Override
            public void onSuccess(RedeemPo redeemPo) {
                iView.hideProgress();
                iView.showDatas(redeemPo);
            }

            @Override
            public void onError(String code, String msg) {
                showToast(msg);
                iView.hideProgress();
            }
        });
    }
}
