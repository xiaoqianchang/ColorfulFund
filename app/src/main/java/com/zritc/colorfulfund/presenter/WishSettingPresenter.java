package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IWishSettingView;

/**
 * 心愿设置
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class WishSettingPresenter extends BasePresenter<IWishSettingView> {

    public WishSettingPresenter(Context context, IWishSettingView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
