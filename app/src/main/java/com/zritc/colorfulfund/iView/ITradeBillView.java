package com.zritc.colorfulfund.iView;

/**
 * 交易账单
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public interface ITradeBillView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
