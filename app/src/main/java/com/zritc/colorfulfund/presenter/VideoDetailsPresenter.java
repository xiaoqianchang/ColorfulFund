package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IVideoDetailsView;

/**
 * 视频详情presenter
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class VideoDetailsPresenter extends BasePresenter<IVideoDetailsView> {

    public VideoDetailsPresenter(Context context, IVideoDetailsView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
