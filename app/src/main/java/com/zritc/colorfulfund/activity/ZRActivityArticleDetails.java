package com.zritc.colorfulfund.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.iView.IArticleDetailsView;
import com.zritc.colorfulfund.presenter.ArticleDetailsPresenter;

/**
 * 文章详情界面
 * 
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ZRActivityArticleDetails extends ZRActivityToolBar<ArticleDetailsPresenter> implements IArticleDetailsView {

    private ArticleDetailsPresenter presenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_article_details;
    }

    @Override
    protected void initPresenter() {
        presenter = new ArticleDetailsPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {

    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(String msg) {

    }
}
