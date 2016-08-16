package com.zritc.colorfulfund.iView;

/**
 * IMultiFundApplyPurchaseView 多只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public interface IMultiFundApplyPurchaseView extends IBaseView {

    void showProgress();
    void hideProgress();
    void showErrorView();
    void showNoMoreData();
}
