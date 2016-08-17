package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.zritc.colorfulfund.iView.IBaseView;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import rx.Subscription;

/**
 * 基础Presenter
 * <p>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected Subscription mSubscription;
    protected Context mContext;
    protected T iView;

    public BasePresenter(Context context, T iView) {
        this.mContext = context;
        this.iView = iView;
    }

    public void init() {
        iView.initView();
    }

    /**
     * Presenter释放资源
     */
    public abstract void release();

    protected void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            ZRToastFactory.getToast(mContext, msg).show();
    }

    protected void cancelToast() {
        ZRToastFactory.cancelToast();
    }
}
