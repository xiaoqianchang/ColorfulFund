package com.zritc.colorfulfund.iView;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/19.
 *
 * @version 1.0
 */
public interface IFundListView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
