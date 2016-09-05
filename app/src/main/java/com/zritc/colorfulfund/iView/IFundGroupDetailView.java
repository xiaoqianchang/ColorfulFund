package com.zritc.colorfulfund.iView;

/**
 * IFundGroupDetailView 基金组合详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-30
 * @lastUpdate 2016-8-30
 */
public interface IFundGroupDetailView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
