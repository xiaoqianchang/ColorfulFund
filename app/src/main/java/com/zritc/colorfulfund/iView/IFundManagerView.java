package com.zritc.colorfulfund.iView;

/**
 * IFundManagerView 基金管家
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-9-22
 * @lastUpdate 2016-9-22
 */
public interface IFundManagerView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
