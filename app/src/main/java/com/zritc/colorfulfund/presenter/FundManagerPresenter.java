package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IFundManagerView;

/**
 * FundManagerPresenter 基金管家
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-9-22
 * @lastUpdate 2016-9-22
 */
public class FundManagerPresenter extends BasePresenter<IFundManagerView> {

    public FundManagerPresenter(Context context, IFundManagerView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }


}
