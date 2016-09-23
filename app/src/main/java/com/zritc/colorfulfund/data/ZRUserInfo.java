package com.zritc.colorfulfund.data;

import android.text.TextUtils;

import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.response.edu.GetUserPoAssetInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;

import java.io.Serializable;

import retrofit2.Call;

/**
 * ZRUserInfo.
 *
 * @author eric
 * @version 1.0
 * @createDate 2016-09-19
 * @lastUpdate 2016-09-19
 */
public class ZRUserInfo implements Serializable {

    private static final long serialVersionUID = -1994975156104278036L;

    private static ZRUserInfo sInstance = null;

    // 资产配置信息
    private UserPoAssetInfo eduUserPoAssetInfo = null;
    private UserPoAssetInfo wishUserPoAssetInfo = null;

    public static final synchronized ZRUserInfo getInstance() {
        if (null == sInstance) {
            sInstance = new ZRUserInfo();
        }
        return sInstance;
    }

    public static final synchronized void clear() {
        sInstance = null;
    }

    /**
     * 获取教育场景中，用户的个人资产配置信息
     *
     * @param callBack
     */
    public void getEduUserPoAssetInfo(UserInfoCallBack callBack) {
        String poCode = "ZH000484";//暂时写死
        Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCall = ZRNetManager.getInstance().getUserPoAssetInfo4CCallbackByPost(poCode);
        getUserPoAssetInfo4CCall.enqueue(new ResponseCallBack<GetUserPoAssetInfo4C>(GetUserPoAssetInfo4C.class) {
            @Override
            public void onSuccess(GetUserPoAssetInfo4C getUserPoAssetInfo4C) {
                eduUserPoAssetInfo = new UserPoAssetInfo();
                eduUserPoAssetInfo.targetAmount = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.targetAmount;
                eduUserPoAssetInfo.totalAmount = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalAmount;
                eduUserPoAssetInfo.totalProfit = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalProfit;
                callBack.onUserInfo(eduUserPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                callBack.onUserInfoError(msg);
            }
        });
    }

    /**
     * 是否是初次购买教育基金
     *
     * @return
     */
    public boolean isEduFirstBuy() {
        if (null != eduUserPoAssetInfo && !TextUtils.isEmpty(eduUserPoAssetInfo.targetAmount))
            return false;
        else
            return true;
    }

    /**
     * 获取心愿场景中，用户的个人资产配置信息
     *
     * @param callBack
     */
    public void getWishUserPoAssetInfo(UserInfoCallBack callBack) {
        String poCode = "ZH000487";//暂时写死
        Call<GetUserPoAssetInfo4C> getUserPoAssetInfo4CCall = ZRNetManager.getInstance().getUserPoAssetInfo4CCallbackByPost(poCode);
        getUserPoAssetInfo4CCall.enqueue(new ResponseCallBack<GetUserPoAssetInfo4C>(GetUserPoAssetInfo4C.class) {
            @Override
            public void onSuccess(GetUserPoAssetInfo4C getUserPoAssetInfo4C) {
                wishUserPoAssetInfo = new UserPoAssetInfo();
                wishUserPoAssetInfo.targetAmount = getUserPoAssetInfo4C.userPoInvestInfo.userInitPoAssetInfo.targetAmount;
                wishUserPoAssetInfo.totalAmount = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalAmount;
                wishUserPoAssetInfo.totalProfit = getUserPoAssetInfo4C.userPoInvestInfo.userPoAsset.totalProfit;
                callBack.onUserInfo(eduUserPoAssetInfo);
            }

            @Override
            public void onError(String code, String msg) {
                callBack.onUserInfoError(msg);
            }
        });
    }

    /**
     * 是否是初次购买心愿基金
     *
     * @return
     */
    public boolean isWishFirstBuy() {
        if (null != wishUserPoAssetInfo && !TextUtils.isEmpty(wishUserPoAssetInfo.targetAmount))
            return false;
        else
            return true;
    }

    public interface UserInfoCallBack {
        void onUserInfo(Object object);

        void onUserInfoError(String message);
    }

}
