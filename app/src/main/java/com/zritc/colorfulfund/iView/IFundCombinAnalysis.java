package com.zritc.colorfulfund.iView;

/**
 * IFundCombinAnalysis 组合分析
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public interface IFundCombinAnalysis extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
