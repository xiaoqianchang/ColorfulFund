package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.ICreateWishView;

/**
 * 建立心愿
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class CreateWishPresenter extends BasePresenter<ICreateWishView> {

    public CreateWishPresenter(Context context, ICreateWishView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
