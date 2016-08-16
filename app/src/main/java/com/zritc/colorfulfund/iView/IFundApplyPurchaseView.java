package com.zritc.colorfulfund.iView;

/**
 * IFundApplyPurchaseView 单只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public interface IFundApplyPurchaseView extends IBaseView {

    void showProgress();
    void hideProgress();
    void showErrorView();
    void showNoMoreData();
}
