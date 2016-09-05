package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IGenerateAlbumView;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/2.
 *
 * @version 1.0
 */
public class GenerateAlbumPresenter extends BasePresenter<IGenerateAlbumView> {

    public GenerateAlbumPresenter(Context context, IGenerateAlbumView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
