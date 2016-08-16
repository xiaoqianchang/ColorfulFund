package com.zritc.colorfulfund.iView;

/**
 * ITradePasswordSetView 交易密码设置
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public interface ITradePasswordSetView extends IBaseView {

    void showProgress();
    void hideProgress();
    void showErrorView();
    void showNoMoreData();
}
