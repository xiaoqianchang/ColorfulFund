package com.zritc.colorfulfund.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.iView.IArticleDetailsView;
import com.zritc.colorfulfund.presenter.ArticleDetailsPresenter;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.adapter.abslistview.CommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;
import com.zritc.colorfulfund.ui.pulltozoomview.PullToZoomScrollViewEx;
import com.zritc.colorfulfund.utils.ZRConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.OPTIONS;

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

    @Bind(R.id.img_head)
    ImageView imgHead; // 头部背景

    @Bind(R.id.tv_tag)
    TextView tvtag; // 文章tag

    @Bind(R.id.tv_article_title)
    TextView tvArticleTitle; // 文章标题

    @Bind(R.id.tv_author)
    TextView tvAuthor; // 作者

    @Bind(R.id.tv_time)
    TextView tvTime; // 时间

    @Bind(R.id.wv_webView)
    WebView mWebView;

    @Bind(R.id.img_collect)
    ImageView imgCollect; // 收藏

    @Bind(R.id.img_share)
    ImageView imgShare; // 分享

    @Bind(R.id.gv_grid_view)
    GridView mGridView; // 热门推荐

    @Bind(R.id.rl_buttom_container)
    RelativeLayout mButtomContainer;

    @Bind(R.id.img_back)
    ImageView imgBack; // 返回

    @Bind(R.id.img_comment)
    ImageView imgComment;// 评论

    @Bind(R.id.img_praise)
    ImageView imgPraise; // 赞

    private ArticleDetailsPresenter presenter;
    protected boolean isButtomContainerHiding = false;
    private String articleUrl = "https://www.baidu.com/";
    private HotAdapter adapter;
    private List<Hot> datas;

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
        setTitleText("文章详情");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hit = view.getHitTestResult();
                // 视频点击：midasapp://goVideoDetail?m?videoPK
                // 图片放大：midasapp://showImageNative?m?ImgTag
                // PDF阅读：midasapp://loadPDFDocument?m?pdfURL
                if (hit != null) {
                    return true;
                } else {
                    return true;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true); // 必须设置，不然网页加载不出来
        settings.setDefaultTextEncodingName("utf-8");
        mWebView.loadUrl(articleUrl);

        datas = new ArrayList<>();
        initData();
        adapter = new HotAdapter(this, datas, R.layout.gv_hot_item);
        mGridView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            datas.add(new Hot("http://img4.imgtn.bdimg.com/it/u=98923187,3761999633&fm=11&gp=0.jpg", "哈哈哈哈哈哈哈哈"));
        }
    }

    @OnClick({R.id.img_back, R.id.img_collect, R.id.img_praise, R.id.img_share, R.id.img_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back: // 返回
                finish();
                break;
            case R.id.img_collect: // 收藏
                showToast("攻城狮正在Coading...");
                break;
            case R.id.img_praise: // 赞
                showToast("攻城狮正在Coading...");
                break;
            case R.id.img_share: // 分享
                showToast("攻城狮正在Coading...");
                break;
            case R.id.img_comment: // 评论
                showToast("攻城狮正在Coading...");
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xc", "ssssssssssssssssssss");
        float startY = 0;
        float endY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                Log.d(TAG, "" + startY);
                break;
            case MotionEvent.ACTION_MOVE:
                float v = endY - startY;
                if (v < 0) {
                    v = -v;
                }
                Log.d(TAG, "" + v);
                if (v > 10)
                    hideOrShowButtomContainer();
                break;
            case MotionEvent.ACTION_UP:
                endY = event.getY();
                Log.d(TAG, "" + endY);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onToolbarClick() {
        super.onToolbarClick();
        hideOrShowButtomContainer();
    }

    /**
     * Control buttomContainer show or hiden
     */
    private void hideOrShowButtomContainer() {
        mButtomContainer.animate()
                .translationY(isButtomContainerHiding ? 0 : mButtomContainer.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isButtomContainerHiding = !isButtomContainerHiding;
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

    static class HotAdapter extends ZRCommonAdapter<Hot> {

        public HotAdapter(Context context, List<Hot> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, Hot item) {
            helper.setImageByUrl(R.id.img_hot_img, item.getImgUrl());
            helper.setText(R.id.tv_hot_title, item.getTitle());
        }
    }

    static class Hot {
        private String imgUrl;
        private String title;

        public Hot(String imgUrl, String title) {
            this.imgUrl = imgUrl;
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
