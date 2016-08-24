package com.zritc.colorfulfund.activity;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.iView.IVideoDetailsView;
import com.zritc.colorfulfund.presenter.VideoDetailsPresenter;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pulltozoomview.PullToZoomScrollViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 视频详情界面
 * 
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ZRActivityVideoDetails extends ZRActivityToolBar<VideoDetailsPresenter> implements IVideoDetailsView {

    @Bind(R.id.vv_video_view)
    VideoView mVideoView;

    @Bind(R.id.sc_scrollView)
    PullToZoomScrollViewEx mScrollView;

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
        datas = new ArrayList<>();
        initData();
        adapter = new HotVideoAdapter(this, datas, R.layout.lv_video_detail_item);
        mHotVideo.setAdapter(adapter);

        Uri uri = Uri.parse(uriString);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            datas.add(new HotVideo("哈哈哈", 1355, "http://img4.imgtn.bdimg.com/it/u=98923187,3761999633&fm=11&gp=0.jpg", "05:33"));
        }
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

    static class HotVideoAdapter extends ZRCommonAdapter<HotVideo> {

        public HotVideoAdapter(Context context, List<HotVideo> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, HotVideo item) {
            helper.setText(R.id.tv_video_title, item.getTitle());
            helper.setText(R.id.tv_play_count, String.format("%s 次播放", item.getPlayCOunt()));
            helper.setImageByUrl(R.id.img_hot_video, item.getVideoUrl());
            helper.setText(R.id.tv_time, item.getTime());
        }
    }

    static class HotVideo {
        private String title;
        private int playCOunt;
        private String videoUrl;
        private String time;

        public HotVideo(String title, int playCOunt, String videoUrl, String time) {
            this.title = title;
            this.playCOunt = playCOunt;
            this.videoUrl = videoUrl;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
