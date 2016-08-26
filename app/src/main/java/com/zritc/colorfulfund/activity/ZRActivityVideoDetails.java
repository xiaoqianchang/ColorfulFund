package com.zritc.colorfulfund.activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IVideoDetailsView;
import com.zritc.colorfulfund.presenter.VideoDetailsPresenter;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pulltozoomview.PullToZoomScrollViewEx;

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
 * 
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

    @Bind(R.id.tv_author)
    TextView tvAuthor; // 作者

    @Bind(R.id.tv_time)
    TextView tvTime; // 时间

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
    private List<HotVideo> datas;
    private String uriString = "https://test-media-cmcaifu-com.oss-cn-hangzhou.aliyuncs.com/upload/1781fd560180400b971bd9c08afa0e0f.mp4";
    String mVideoUrl = "http://v.youku.com/v_show/id_XMTUzNzM1MjUwMA==_ev_5.html?from=y1.3-idx-uhome-1519-20887.205805-205902.8-1";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void initPresenter() {
        presenter = new VideoDetailsPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        imgVideoBg.setBackgroundResource(R.mipmap.bg_red);
        datas = new ArrayList<>();
        initData();
        adapter = new HotVideoAdapter(this, datas, R.layout.lv_video_detail_item);
        mHotVideo.setAdapter(adapter);
        mHotVideo.setOnItemClickListener((parent, view, position, id) -> {
            mVideoUrl = datas.get(position).getVideoUrl();
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
        mVideoView.setOnJjOpenFailedListener(new OnJjOnOpenFailedListener(){

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
        for (int i = 0; i < 4; i++) {
            datas.add(new HotVideo("哈哈哈", 1355, "http://v.youku.com/v_show/id_XMTY5NjE3NTc4OA==.html?from=y1.2-2-96.3.3-1.1-3-1-2-0", "http://img4.imgtn.bdimg.com/it/u=98923187,3761999633&fm=11&gp=0.jpg", "05:33"));
        }
    }

    @OnClick({R.id.img_play, R.id.img_back, R.id.img_collect, R.id.img_praise, R.id.img_share, R.id.img_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_play:
                play();
                break;
            case R.id.img_back: // 返回
                finish();
                break;
            case R.id.img_collect: // 收藏
                showToast("攻城狮正在Coding...");
                break;
            case R.id.img_praise: // 赞
                showToast("攻城狮正在Coding...");
                break;
            case R.id.img_share: // 分享
                showToast("攻城狮正在Coding...");
                break;
            case R.id.img_comment: // 评论
                showToast("攻城狮正在Coding...");
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

    static class HotVideoAdapter extends ZRCommonAdapter<HotVideo> {

        public HotVideoAdapter(Context context, List<HotVideo> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, HotVideo item) {
            helper.setText(R.id.tv_video_title, item.getTitle());
            helper.setText(R.id.tv_play_count, String.format("%s 次播放", item.getPlayCOunt()));
            ((ZRCircleImageView) helper.getView(R.id.img_hot_video)).setRectAdius(16);
            helper.setImageByUrl(R.id.img_hot_video, item.getImgUrl());
            helper.setText(R.id.tv_time, item.getTime());
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
