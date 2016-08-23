package com.zritc.colorfulfund.iView;

/**
 * IFortuneGroupListView 圈子列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-23
 * @lastUpdate 2016-8-23
 */
public interface IFortuneGroupListView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
