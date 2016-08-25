package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.trade.GetUserPoList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IFundListView;

import retrofit2.Call;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/8/19.
 *
 * @version 1.0
 */
public class FundListPresenter extends BasePresenter<IFundListView> {

    public FundListPresenter(Context context, IFundListView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取我的基金列表
     */
    public void doGetUserPoList4C() {
        iView.showProgress("获取基金列表...");
        Call<GetUserPoList4C> userPoList4CCallbackByPost = ZRNetManager.getInstance().getUserPoList4CCallbackByPost();
        userPoList4CCallbackByPost.enqueue(new ResponseCallBack<GetUserPoList4C>(GetUserPoList4C.class) {
            @Override
            public void onSuccess(GetUserPoList4C getUserPoList4C) {
                iView.hideProgress();
                iView.onSuccess(getUserPoList4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }
}
