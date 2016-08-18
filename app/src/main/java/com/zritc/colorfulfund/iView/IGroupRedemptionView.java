package com.zritc.colorfulfund.iView;

/**
 * 组合赎回界面操作
 * <p>
 * Created by Chang.Xiao on 2016/8/16.
 *
 * @version 1.0
 */
public interface IGroupRedemptionView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
