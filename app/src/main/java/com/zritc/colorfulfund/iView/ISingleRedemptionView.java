package com.zritc.colorfulfund.iView;

import com.zritc.colorfulfund.data.response.trade.RedeemPo;

/**
 * 单支基金赎回界面操控类
 * <p>
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public interface ISingleRedemptionView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void showDatas(RedeemPo redeemPo);
}
