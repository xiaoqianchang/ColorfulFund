package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.ITradePasswordSetView;

/**
 * TradePasswordSetPresenter 交易密码设置
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public class TradePasswordSetPresenter extends BasePresenter<ITradePasswordSetView> {

    public TradePasswordSetPresenter(Context context, ITradePasswordSetView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void requestTradePasswordSet(String tradePassword) {
        /*iView.showProgress();
        mSubscription = ZRRetrofit.getNetApiInstance().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziData -> {
                    if (meiziData.results.size() == 0){
                        iView.showNoMoreData();
                    }else {
                        iView.showMeiziList(meiziData.results);
                    }
                    iView.hideProgress();
                }, throwable -> {
                    iView.showErrorView();
                    iView.hideProgress();
                });*/
    }
}
