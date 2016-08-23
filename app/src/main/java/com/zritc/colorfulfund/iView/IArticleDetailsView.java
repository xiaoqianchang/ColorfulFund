package com.zritc.colorfulfund.iView;

/**
 * 文章详情
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public interface IArticleDetailsView extends IBaseView {

    void showProgress(CharSequence message);
    void hideProgress();
    void onSuccess(Object object);
    void onError(String msg);
}
