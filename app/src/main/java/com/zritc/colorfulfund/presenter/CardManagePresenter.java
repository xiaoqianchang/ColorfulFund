package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.ICardManageView;

/**
 * CardManagePresenter 卡片管理
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-12
 * @lastUpdate 2016-8-12
 */
public class CardManagePresenter extends BasePresenter<ICardManageView> {

    public CardManagePresenter(Context context, ICardManageView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void requestCardList(int page) {
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
