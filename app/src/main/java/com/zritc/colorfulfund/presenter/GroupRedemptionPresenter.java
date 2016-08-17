package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.widget.Toast;

import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IGroupRedemptionView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        redeemPoCall.enqueue(new Callback<RedeemPo>() {
            @Override
            public void onResponse(Call<RedeemPo> call, Response<RedeemPo> response) {
                try {
                    RedeemPo body = response.body();
                    if (null != body) {
                        Toast.makeText(mContext, body.msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iView.hideProgress();
            }

            @Override
            public void onFailure(Call<RedeemPo> call, Throwable t) {
                iView.hideProgress();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 估算申购费用
     * @param tradeType
     * @param fundCode
     * @param amount
     */
    public void doEstimateBuyFundFee(String tradeType, String fundCode, double amount) {
        Call<EstimateBuyFundFee> estimateBuyFundFeeCall = ZRNetManager.getInstance().estimateBuyFundFeeCallbackByPost(tradeType, fundCode, amount);
        estimateBuyFundFeeCall.enqueue(new ResponseCallBack<EstimateBuyFundFee>(EstimateBuyFundFee.class) {
            @Override
            public void onSuccess(EstimateBuyFundFee estimateBuyFundFee) {
                showToast(estimateBuyFundFee.msg);
            }

            @Override
            public void onError(String code, String msg) {
                showToast(msg);
            }
        });
    }
}
