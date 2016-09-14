package com.zritc.colorfulfund.activity.fortunegroup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityShareEntry;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityFortuneGroupCommentList;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.iView.IVideoDetailsView;
import com.zritc.colorfulfund.presenter.VideoDetailsPresenter;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.video.venvy.param.JjVideoView;
import cn.com.video.venvy.param.OnJjBufferCompleteListener;
import cn.com.video.venvy.param.OnJjBufferStartListener;
import cn.com.video.venvy.param.OnJjBufferingUpdateListener;
import cn.com.video.venvy.param.OnJjOnOpenFailedListener;
import cn.com.video.venvy.param.OnJjOpenStartListener;
import cn.com.video.venvy.param.OnJjOpenSuccessListener;
import cn.com.video.venvy.param.OnJjOutsideLinkClickListener;
import cn.com.video.venvy.param.VideoJjMediaContoller;

/**
 * 视频详情界面
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ZRActivityVideoDetails extends ZRActivityBase<VideoDetailsPresenter> implements IVideoDetailsView {

    @Bind(R.id.jj_video_view)
    JjVideoView mVideoView;

    @Bind(R.id.sdk_ijk_progress_bar_layout)
    View mLoadView;

    @Bind(R.id.sdk_load_layout)
    View mLoadBufferView;

    @Bind(R.id.sdk_ijk_progress_bar_text)
    TextView mLoadText;

    @Bind(R.id.sdk_sdk_ijk_load_buffer_text)
    TextView mLoadBufferTextView;

    @Bind(R.id.img_video_bg)
    ImageView imgVideoBg;

    @Bind(R.id.img_play)
    ImageView imgPlay;

    @Bind(R.id.sc_scrollView)
    LinearLayout mScrollView;

    @Bind(R.id.tv_tag)
    TextView tvtag; // 文章tag

    @Bind(R.id.tv_article_title)
    TextView tvArticleTitle; // 文章标题

    @Bind(R.id.img_user)
    ZRCircleImageView imgUser; // 作者头像

    @Bind(R.id.tv_author)
    TextView tvAuthor; // 作者

    @Bind(R.id.tv_time)
    TextView tvTime; // 时间

    @Bind(R.id.tv_play_count)
    TextView tvPlayCount; // 播放次数

    @Bind(R.id.img_back)
    ImageView imgBack; // 返回

    @Bind(R.id.img_collect)
    ImageView imgCollect; // 收藏

    @Bind(R.id.img_praise)
    ImageView imgPraise; // 赞

    @Bind(R.id.img_share)
    ImageView imgShare; // 分享

    @Bind(R.id.img_comment)
    ImageView imgComment;// 评论

    @Bind(R.id.lv_hot_video)
    ListView mHotVideo;

    private VideoDetailsPresenter presenter;
    private HotVideoAdapter adapter;
    private List<GetPostInfo4C.ReferList> referList;
    private String mVideoUrl = "";
    private String postId = "";
    private boolean isThumb; // 该用户是否点过赞
    private boolean isCollection; // 该用户是否收藏过

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void initPresenter() {
        presenter = new VideoDetailsPresenter(this, this);
        presenter.init();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        postId = bundle.getString("postId");
    }

    @Override
    public void initView() {
        getExtraData();

        imgVideoBg.setBackgroundResource(R.mipmap.bg_red);
        referList = new ArrayList<>();
        initData();
        presenter.doGetPostInfo(postId);
        mHotVideo.setOnItemClickListener((parent, view, position, id) -> {
            mVideoUrl = referList.get(position).content;
            if (!ZRUtils.isUrl(mVideoUrl)) {
                showToast("不是正确的播放地址");
                return;
            }
            play();
        });

        mVideoView.setMediaController(new VideoJjMediaContoller(this, true));
        mLoadBufferTextView.setTextColor(Color.RED);
        /***
         * 用户自定义的外链 可 获取外链点击时间
         */
        mVideoView
                .setOnJjOutsideLinkClickListener(new OnJjOutsideLinkClickListener() {

                    @Override
                    public void onJjOutsideLinkClick(String arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onJjOutsideLinkClose() {
                        // TODO Auto-generated method stub

                    }
                });
        /***
         * 设置缓冲
         */
        mVideoView.setMediaBufferingView(mLoadBufferView);
        /***
         * 视频开始加载数据
         */
        mVideoView.setOnJjOpenStart(new OnJjOpenStartListener() {

            @Override
            public void onJjOpenStart(String arg0) {
                mLoadText.setText(arg0);
            }
        });
        /***
         * 视频开始播放
         */
        mVideoView.setOnJjOpenSuccess(new OnJjOpenSuccessListener() {

            @Override
            public void onJjOpenSuccess() {
                mLoadView.setVisibility(View.GONE);
                imgVideoBg.setVisibility(View.GONE);
            }
        });
        // 缓冲开始
        mVideoView.setOnJjBufferStart(new OnJjBufferStartListener() {

            @Override
            public void onJjBufferStartListener(int arg0) {
                Log.e("Video++", "====================缓冲值=====" + arg0);
            }
        });
        mVideoView
                .setOnJjBufferingUpdateListener(new OnJjBufferingUpdateListener() {

                    @Override
                    public void onJjBufferingUpdate(int arg1) {
                        //						mLoadView.setVisibility(mVideoView
                        //								.getBufferPercentage() > 5 ? View.GONE
                        //								: View.VISIBLE);
                        if (mLoadBufferView.getVisibility() == View.VISIBLE) {
                            mLoadBufferTextView.setText(String
                                    .valueOf(mVideoView.getBufferPercentage())
                                    + "%");
                            Log.e("Video++", "====================缓冲值2====="
                                    + arg1);
                        }
                    }
                });
        // 缓冲完成
        mVideoView.setOnJjBufferComplete(new OnJjBufferCompleteListener() {

            @Override
            public void onJjBufferCompleteListener(int arg0) {
                // TODO Auto-generated method stub
                mLoadView.setVisibility(View.GONE);
            }
        });
        mVideoView.setOnJjOpenFailedListener(new OnJjOnOpenFailedListener() {

            @Override
            public boolean onJjOpenFailed(int arg0, int arg1) {
                return false;
            }

        });
        /***
         * 注意VideoView 要调用下面方法 配置你用户信息
         */
        mVideoView.setVideoJjAppKey("Nke_z5Dq-");
        mVideoView.setVideoJjPageName("com.zritc.colorfulfund");
        // mVideoView.setMediaCodecEnabled(true);// 是否开启 硬解 硬解对一些手机有限制
    }

    private void initData() {
    }

    @OnClick({R.id.img_play, R.id.img_back, R.id.img_collect, R.id.img_praise, R.id.img_share, R.id.img_comment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_play:
                if (!ZRUtils.isUrl(mVideoUrl)) {
                    showToast("不是正确的播放地址");
                    return;
                }
                play();
                break;
            case R.id.img_back: // 返回
                finish();
                break;
            case R.id.img_collect: // 收藏
                presenter.doCollection(postId);
                break;
            case R.id.img_praise: // 赞
                presenter.doThumb(postId);
                break;
            case R.id.img_share: // 分享
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

    private void play() {
        imgPlay.setVisibility(View.GONE);
        mLoadView.setVisibility(View.VISIBLE);
        /***
         * 视频标签显示的时间 默认显示5000毫秒 可设置 传入值 long类型 毫秒
         */
        // 参数代表是否记录视频播放位置 默认false不记录 true代表第二次或多次进入，直接跳转到上次退出的时间点开始播放
        // mVideoView.setVideoJjSaveExitTime(false);
        /***
         * 指定时间开始播放 毫秒
         */
        // mVideoView.setVideoJjSeekToTime(Long.valueOf(20000));
        mVideoView.setVideoJjResetState();
        // 判断是否源
        // 0：代表 8大视频网站url
        // 3：代表自己服务器的视频源
        // 2：代表直播地址
        // 1：代表本地视频(手机上的视频源)
        // 4：特殊需求
        mVideoView.setVideoJjType(0);
        mVideoView.setResourceVideo(mVideoUrl);
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
                Picasso.with(this).load(postInfo.coverImgURL).placeholder(R.mipmap.ic_img_profile_bg).into(imgVideoBg);
            } else {
                Picasso.with(this).load(R.mipmap.bg_red).into(imgVideoBg);
            }
            // 标签列表 id、name、color
            tvtag.setText(postInfo.tagList.get(0).tagName);
            tvArticleTitle.setText(postInfo.title);
            mVideoUrl = postInfo.content; // 视频url
            tvTime.setText(ZRUtils.formatTime(postInfo.postTime, ZRUtils.TIME_FORMAT14));
            tvPlayCount.setText(String.format("%s 次播放", postInfo.visitNumber));
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
        adapter = new HotVideoAdapter(this, referList, R.layout.lv_video_detail_item);
        mHotVideo.setAdapter(adapter);
    }

    /**
     * 收藏状态变化
     */
    private void collectionStatusChanged() {
        imgCollect.setImageResource(isCollection ? R.mipmap.icon_collection_selected : R.mipmap.icon_collection_normal);
    }

    /**
     * 点赞状态变化
     */
    private void praiseStatusChanged() {
        imgPraise.setImageResource(isThumb ? R.mipmap.icon_praise_selected : R.mipmap.icon_praise_normal);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须调用 要不直播有问题
        if (mVideoView != null)
            mVideoView.onDestroy();
    }

    static class HotVideoAdapter extends ZRCommonAdapter<GetPostInfo4C.ReferList> {

        public HotVideoAdapter(Context context, List<GetPostInfo4C.ReferList> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, GetPostInfo4C.ReferList item) {
            helper.setText(R.id.tv_video_title, item.title);
            helper.setText(R.id.tv_play_count, String.format("%s 次播放", item.visitNumber));
            ((ZRCircleImageView) helper.getView(R.id.img_hot_video)).setRectAdius(16);
            helper.setImageByUrl(R.id.img_hot_video, item.coverImgURL);
//            helper.setText(R.id.tv_time, ZRUtils.formatTime(item));
        }
    }

    static class HotVideo {
        private String title;
        private int playCOunt;
        private String videoUrl;
        private String imgUrl;
        private String time;

        public HotVideo(String title, int playCOunt, String videoUrl, String imgUrl, String time) {
            this.title = title;
            this.playCOunt = playCOunt;
            this.videoUrl = videoUrl;
            this.imgUrl = imgUrl;
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlayCOunt() {
            return playCOunt;
        }

        public void setPlayCOunt(int playCOunt) {
            this.playCOunt = playCOunt;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
