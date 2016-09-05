package com.zritc.colorfulfund.iView;

/**
 * ITargetSettingView 目标设置
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-31
 * @lastUpdate 2016-8-31
 */
public interface ITargetSettingView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
