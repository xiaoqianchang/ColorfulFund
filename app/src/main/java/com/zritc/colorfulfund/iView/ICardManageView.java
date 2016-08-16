package com.zritc.colorfulfund.iView;

/**
 * ICardManageView 卡片管理
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public interface ICardManageView extends IBaseView {

    void showProgress();
    void hideProgress();
    void showErrorView();
    void showNoMoreData();
}
