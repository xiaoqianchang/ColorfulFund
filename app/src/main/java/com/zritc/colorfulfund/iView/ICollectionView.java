package com.zritc.colorfulfund.iView;

/**
 * 收藏
 * <p>
 * Created by Chang.Xiao on 2016/9/23.
 *
 * @version 1.0
 */
public interface ICollectionView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
