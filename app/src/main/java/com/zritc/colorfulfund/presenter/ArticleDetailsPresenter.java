package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IArticleDetailsView;

/**
 * 文章详情presenter
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ArticleDetailsPresenter extends BasePresenter<IArticleDetailsView> {

    public ArticleDetailsPresenter(Context context, IArticleDetailsView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
