package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.model.wish.WishCategory;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.data.response.wish.CreateUserWishList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IWishSettingView;
import com.zritc.colorfulfund.utils.ZRUtils;

import retrofit2.Call;

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

    public void getUserPoAssetInfo4C(String poCode) {
        Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCall = ZRNetManager.getInstance().getUserPoAssetInfo4CCallbackByPost(poCode);
        getUserPoAssetInfo4CCall.enqueue(new ResponseCallBack<GetUserPoAssetInfo4C>(GetUserPoAssetInfo4C.class) {
            @Override
            public void onSuccess(GetUserPoAssetInfo4C getUserPoAssetInfo4C) {
                UserPoAssetInfo userPoAssetInfo = new UserPoAssetInfo();
                userPoAssetInfo.expectedYearlyRoe = getUserPoAssetInfo4C.userPoInvestInfo.fundPoInfo.poBase.expectedYearlyRoe;
                iView.onSuccess(userPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 创建用户心愿
     */
    public void doCreateUserWish(long typeId, String name, String targetAmount, String targetDate) {
        iView.showProgress("处理中...");
        Call<CreateUserWishList4C> createUserWishList = ZRNetManager.getInstance().createUserWishList4CCallbackByPost(typeId, name, targetAmount, targetDate);
        createUserWishList.enqueue(new ResponseCallBack<CreateUserWishList4C>(CreateUserWishList4C.class) {
            @Override
            public void onSuccess(CreateUserWishList4C createUserWishList4C) {
                iView.hideProgress();
                iView.onSuccess(createUserWishList4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     */
    private void dataConverter(CreateUserWishList4C createUserWishList4C) {
    }
}
