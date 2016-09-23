package com.zritc.colorfulfund.activity.fortunegroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityShareEntry;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityFortuneGroupCommentList;
import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.iView.IArticleDetailsView;
import com.zritc.colorfulfund.presenter.ArticleDetailsPresenter;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pulltozoomview.PullToZoomScrollViewEx;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.http.HEAD;

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

    @Bind(R.id.img_user)
    ZRCircleImageView imgUser;

    @Bind(R.id.tv_author)
    TextView tvAuthor; // 作者

    @Bind(R.id.tv_time)
    TextView tvTime; // 时间

    @Bind(R.id.ll_article)
    RelativeLayout llArticle;

    @Bind(R.id.wv_webView)
    WebView mWebView;

    @Bind(R.id.img_collect)
    ImageView imgCollect; // 收藏

    @Bind(R.id.img_collect_inner)
    ImageView imgInnerCollect; // 收藏

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
    private List<GetPostInfo4C.ReferList> referList = new ArrayList<>();
    private String postId = "";
    private boolean isThumb; // 该用户是否点过赞
    private boolean isCollection; // 该用户是否收藏过

    @Override
    protected int getContentViewId() {
        return R.layout.activity_article_details;
    }

    @Override
    protected void initPresenter() {
        presenter = new ArticleDetailsPresenter(this, this);
        presenter.init();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            postId = bundle.getString("postId");
        }
    }

    @Override
    public void initView() {
        setTitleText("文章详情");
        getExtraData();
        presenter.doGetPostInfo(postId);

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
        settings.setDefaultTextEncodingName("UTF-8");
//        mWebView.loadUrl(articleUrl);

        mGridView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            postId = referList.get(position).articleId;
            presenter.doGetPostInfo(postId);
        });
    }

    @OnClick({R.id.img_back, R.id.img_collect_inner, R.id.img_collect, R.id.img_praise, R.id.img_share, R.id.img_share_inner, R.id.img_comment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_back: // 返回
                finish();
                break;
            case R.id.img_collect_inner:
                presenter.doCollection(postId);
                break;
            case R.id.img_collect: // 收藏
                presenter.doCollection(postId);
                break;
            case R.id.img_praise: // 赞
                presenter.doThumb(postId);
                break;
            case R.id.img_share:
                // 分享弹出
                UPMediaMessage messageInner = new UPMediaMessage();
                messageInner.setShareFrom(UPMediaMessage.SHARE_FROM_APP);
                messageInner.setTitle("title");
                messageInner.setSMSDesc("desc");
                messageInner.setDescription("description");
                messageInner.setUrl("http://www.baidu.com");
                intent.setClass(this, ZRActivityShareEntry.class);
                intent.putExtra(ZRConstant.KEY_EXTRA_INTRO, messageInner);
                startActivity(intent);
            case R.id.img_share_inner: // 分享
                // 分享弹出
                UPMediaMessage message = new UPMediaMessage();
                message.setShareFrom(UPMediaMessage.SHARE_FROM_APP);
                message.setTitle("title");
                message.setSMSDesc("desc");
                message.setDescription("description");
                message.setUrl("http://www.baidu.com");
                intent.setClass(this, ZRActivityShareEntry.class);
                intent.putExtra(ZRConstant.KEY_EXTRA_INTRO, message);
                startActivity(intent);
                break;
            case R.id.img_comment: // 评论
                intent.setClass(this, ZRActivityFortuneGroupCommentList.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onToolbarClick() {
        super.onToolbarClick();
        hideOrShowButtomContainer();
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GetPostInfo4C) {
            // 帖子详情数据
            GetPostInfo4C getPostInfo4C = (GetPostInfo4C) object;
            GetPostInfo4C.Result result = getPostInfo4C.result;
            if (null != result) {
                refreshContentView(result);
            }
            collectionStatusChanged();
            praiseStatusChanged();
        }else if (object instanceof CreateCollection) {
            // 收藏返回
            CreateCollection createCollection = (CreateCollection) object;
            isCollection = createCollection.collectionStatus;
            collectionStatusChanged();
        } else if (object instanceof CreateThumb) {
            // 点赞
            CreateThumb createThumb = (CreateThumb) object;
            isThumb = createThumb.trhumStatus;
            praiseStatusChanged();
        }
    }

    private void refreshContentView(GetPostInfo4C.Result result) {
        GetPostInfo4C.PostInfo postInfo = result.postInfo;
        // 刷新帖子内容
        if (null != postInfo) {
            if (!TextUtils.isEmpty(postInfo.coverImgURL)) {
                Picasso.with(this).load(postInfo.coverImgURL).placeholder(R.mipmap.ic_img_profile_bg).into(imgHead);
            } else {
                Picasso.with(this).load(R.mipmap.ic_img_profile_bg).into(imgHead);
            }
//            ImageLoader.getInstance().displayImage(postInfo.coverImgURL, imgHead);
            // 标签列表 id、name、color
            tvtag.setText(postInfo.tagList.get(0).tagName);
            tvArticleTitle.setText(postInfo.title);
            setTitleText(postInfo.title);
            // 引文富文本
//            postInfo.quote;
            // 富文本内容
            mWebView.loadData(postInfo.content, "text/html", "UTF-8");
            tvTime.setText(ZRUtils.formatTime(postInfo.postTime, ZRUtils.TIME_FORMAT14));
            // 设置作者信息
            if (!TextUtils.isEmpty(postInfo.authorInfo.photoURL)) {
                Picasso.with(this).load(postInfo.authorInfo.photoURL).placeholder(R.mipmap.ic_img_profile_bg).into(imgUser);
            } else {
                Picasso.with(this).load(R.mipmap.icon_header).into(imgUser);
            }
            tvAuthor.setText(postInfo.authorInfo.nickName);
        }
        isThumb = result.thumbStatus;
        isCollection = result.collectionStatus;
        referList = result.referList;
        adapter = new HotAdapter(this, referList, R.layout.gv_hot_item);
        mGridView.setAdapter(adapter);
    }

    /**
     * 收藏状态变化
     */
    private void collectionStatusChanged() {
        imgCollect.setImageResource(isCollection ? R.mipmap.icon_collection_selected : R.mipmap.icon_collection_normal);
        imgInnerCollect.setImageResource(isCollection ? R.mipmap.icon_collection_selected : R.mipmap.icon_collection_normal);
    }

    /**
     * 点赞状态变化
     */
    private void praiseStatusChanged() {
        imgPraise.setImageResource(isThumb ? R.mipmap.icon_praise_selected : R.mipmap.icon_praise_normal);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    static class HotAdapter extends ZRCommonAdapter<GetPostInfo4C.ReferList> {

        public HotAdapter(Context context, List<GetPostInfo4C.ReferList> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, GetPostInfo4C.ReferList item) {
            ((ZRCircleImageView) helper.getView(R.id.img_hot_img)).setRectAdius(16);
            helper.setImageByUrl(R.id.img_hot_img, item.coverImgURL);
            helper.setText(R.id.tv_hot_title, item.title);
        }
    }

    // 手指上下滑动时的最小速度
    private static final int YSPEED_MIN = 150;

    // 手指上下滑动时的最小距离
    private static final int YDISTANCE_MIN = 150;

    // 记录手指按下时的横坐标
    private float xDown;

    // 记录手指按下时的纵坐标
    private float yDown;

    // 记录手指移动时的横坐标
    private float xMove;

    // 记录手指移动时的纵坐标
    private float yMove;

    // 记录手指移动的y轴距离
    private int distanceX;

    // 记录手指移动的x轴距离
    private int distanceY;

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
                distanceX = (int) (xMove - xDown);
                distanceY = (int) (yMove - yDown);
                // 获取顺时速度
                int ySpeed = getScrollVelocity();
                //                Log.d(TAG, "sepeed: " + ySpeed + ", distanceY: " + distanceY);
                // 底部容器显示与隐藏需满足以下条件：
                // 1.y轴速度>YSPEED_MIN
                // 2.向下滑工具栏消失
                // 3.向上滑、点击文章正文时出现
                if (ySpeed > YSPEED_MIN && distanceY > YDISTANCE_MIN) {
                    // 向下滑
                    if (!isButtomContainerHiding) {
                        hideOrShowButtomContainer();
                    }
                } else if (ySpeed > YSPEED_MIN && distanceY < -YDISTANCE_MIN) {
                    // 向上滑
                    if (isButtomContainerHiding) {
                        hideOrShowButtomContainer();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 是否点击正文
                if (distanceX >-50 && distanceX < 50 && distanceY > -50 && distanceY < 50) {
                    if (isButtomContainerHiding) {
                        hideOrShowButtomContainer();
                    }
                }
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
}
