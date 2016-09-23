package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IFundCombinAnalysis;

/**
 * FundManagerPresenter 组合分析
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-9-22
 * @lastUpdate 2016-9-22
 */
public class FundCombinAnalysisPresenter extends BasePresenter<IFundCombinAnalysis> {

    public FundCombinAnalysisPresenter(Context context, IFundCombinAnalysis iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }


}
