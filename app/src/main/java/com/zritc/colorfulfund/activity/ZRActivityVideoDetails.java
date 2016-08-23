package com.zritc.colorfulfund.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.iView.IVideoDetailsView;
import com.zritc.colorfulfund.presenter.VideoDetailsPresenter;

/**
 * 视频详情界面
 * 
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ZRActivityVideoDetails extends ZRActivityToolBar<VideoDetailsPresenter> implements IVideoDetailsView {

    private VideoDetailsPresenter presenter;

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
