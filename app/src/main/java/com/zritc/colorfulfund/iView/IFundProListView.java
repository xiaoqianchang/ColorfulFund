package com.zritc.colorfulfund.iView;

/**
 * IFundProListView 基金列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-19
 * @lastUpdate 2016-8-19
 */
public interface IFundProListView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
