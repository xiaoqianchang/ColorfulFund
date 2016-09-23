package com.zritc.colorfulfund.iView;

/**
 * 我的资产
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public interface IMyPropertyView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
