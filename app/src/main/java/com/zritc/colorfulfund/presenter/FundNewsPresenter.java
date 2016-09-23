package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IFundNews;

/**
 * FundManagerPresenter 基金动态
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-9-22
 * @lastUpdate 2016-9-22
 */
public class FundNewsPresenter extends BasePresenter<IFundNews> {

    public FundNewsPresenter(Context context, IFundNews iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }


}
