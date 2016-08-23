package com.zritc.colorfulfund.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.iView.IArticleDetailsView;
import com.zritc.colorfulfund.presenter.ArticleDetailsPresenter;
import com.zritc.colorfulfund.ui.pulltozoomview.PullToZoomScrollViewEx;

import butterknife.Bind;

/**
 * 文章详情界面
 * 
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ZRActivityArticleDetails extends ZRActivityToolBar<ArticleDetailsPresenter> implements IArticleDetailsView {

    @Bind(R.id.sc_scrollView)
    PullToZoomScrollViewEx scrollView;

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
//        View headView = LayoutInflater.from(context).inflate(R.layout.member_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.article_details_zoom_view, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.article_details_content_view, null, false);
//        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
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
