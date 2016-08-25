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
import android.view.VelocityTracker;
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

    // 手指上下滑动时的最小速度
    private static final int YSPEED_MIN = 150;

    // 记录手指按下时的横坐标
    private float xDown;

    // 记录手指按下时的纵坐标
    private float yDown;

    // 记录手指移动时的横坐标
    private float xMove;

    // 记录手指移动时的纵坐标
    private float yMove;

    // 用于计算手指滑动的速度
    private VelocityTracker mVelocityTracker;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = ev.getRawX();
                yMove = ev.getRawY();
                // 滑动的距离
                int distanceX = (int) (xMove - xDown);
                int distanceY = (int) (yMove - yDown);
                // 获取顺时速度
                int ySpeed = getScrollVelocity();
                Log.d("xc", "sepeed: " + ySpeed);
                // 底部容器显示与隐藏需满足以下条件：
                // 1.y轴速度>YSPEED_MIN
                // 2.
                if (ySpeed > YSPEED_MIN) {
                    hideOrShowButtomContainer();
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker当中
     *
     * @param ev
     */
    private void createVelocityTracker(MotionEvent ev) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
    }

    /**
     * 回收VelocityTracker对象
     */
    private void recycleVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     *
     * @return  滑动速度，以每秒钟移动了多少像素值为单位
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getYVelocity();
        return Math.abs(velocity);
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
