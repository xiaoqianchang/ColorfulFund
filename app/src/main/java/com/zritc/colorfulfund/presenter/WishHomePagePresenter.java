package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.iView.IWishHomePageView;

/**
 * 心愿presenter
 * <p>
 * Created by Chang.Xiao on 2016/9/6.
 *
 * @version 1.0
 */
public class WishHomePagePresenter extends BasePresenter<IWishHomePageView> {

    public WishHomePagePresenter(Context context, IWishHomePageView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
