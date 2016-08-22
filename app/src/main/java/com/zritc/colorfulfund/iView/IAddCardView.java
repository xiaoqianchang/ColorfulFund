package com.zritc.colorfulfund.iView;

/**
 * IAddCardView 添加卡片
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public interface IAddCardView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
