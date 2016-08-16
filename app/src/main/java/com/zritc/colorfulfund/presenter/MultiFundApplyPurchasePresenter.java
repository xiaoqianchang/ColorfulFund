package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IMultiFundApplyPurchaseView;

/**
 * MultiFundApplyPurchasePresenter 多只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class MultiFundApplyPurchasePresenter extends BasePresenter<IMultiFundApplyPurchaseView> {

    public MultiFundApplyPurchasePresenter(Context context, IMultiFundApplyPurchaseView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void requestFundApplyPurchase(String pid, String money, String userid) {
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
