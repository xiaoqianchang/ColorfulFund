package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.wish.Wish;
import com.zritc.colorfulfund.data.model.wish.WishPoBase;
import com.zritc.colorfulfund.data.response.wish.DeleteUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.GetUserWishLists4C;
import com.zritc.colorfulfund.data.response.wish.WithdrawAssetFromWishlist4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IWishHomePageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 心愿presenter
 * <p>
 * Created by Chang.Xiao on 2016/9/6.
 *
 * @version 1.0
 */
public class WishHomePagePresenter extends BasePresenter<IWishHomePageView> {

    public String investmentMoney = ""; // 加仓定投金额
    public WishPoBase wishPoBase;
    public List<Wish> wishList;

    public WishHomePagePresenter(Context context, IWishHomePageView iView) {
        super(context, iView);
        wishList = new ArrayList<>();
    }

    @Override
    public void release() {

    }

    /**
     * 获取心愿列表
     */
    public void doGetUserWishLists() {
        Call<GetUserWishLists4C> userWishLists4CCallbackByPost = ZRNetManager.getInstance().getUserWishLists4CCallbackByPost();
        userWishLists4CCallbackByPost.enqueue(new ResponseCallBack<GetUserWishLists4C>(GetUserWishLists4C.class) {
            @Override
            public void onSuccess(GetUserWishLists4C getUserWishLists4C) {
                dataConverter(getUserWishLists4C);
                iView.onSuccess(getUserWishLists4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 删除心愿
     *
     * @param wishListId
     */
    public void doDeleteUserWish(long wishListId) {
        iView.showProgress("处理中...");
        Call<DeleteUserWishList4C> deleteUserWishList4CCallbackByPost = ZRNetManager.getInstance().deleteUserWishList4CCallbackByPost(wishListId);
        deleteUserWishList4CCallbackByPost.enqueue(new ResponseCallBack<DeleteUserWishList4C>(DeleteUserWishList4C.class) {
            @Override
            public void onSuccess(DeleteUserWishList4C deleteUserWishList4C) {
                iView.hideProgress();
                iView.onSuccess(deleteUserWishList4C);
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
    private void dataConverter(GetUserWishLists4C getUserWishLists4C) {
        investmentMoney = getUserWishLists4C.userWishlistInfo.investmentInfo.periodicalAmount;
        GetUserWishLists4C.PoBase poBase = getUserWishLists4C.userWishlistInfo.poBase;
        wishPoBase = new WishPoBase();
        wishPoBase.poCode = poBase.poCode;
        wishPoBase.poName = poBase.poName;
        wishPoBase.expectedYearlyRoe = poBase.expectedYearlyRoe;
        wishPoBase.riskLevel = poBase.riskLevel;
        wishList.clear();
        List<GetUserWishLists4C.UserWishLists> userWishLists = getUserWishLists4C.userWishlistInfo.userWishLists;
        for (GetUserWishLists4C.UserWishLists userWish : userWishLists) {
            Wish wish = new Wish();
            wish.wishId = userWish.wishId;
            wish.wishStatus = userWish.wishStatus;
            wish.wishName = userWish.wishName;
            wish.targetMoney = userWish.targetAmount;
            wish.currentProgress = (int) (Double.parseDouble(userWish.currentInvestmentAmount) / Double.parseDouble(userWish.targetAmount));
            wishList.add(wish);
        }
//        wishList.add(new Wish("存钱中", "IWC表", "10万", 55));
//        wishList.add(new Wish("未开始", "IWC表", "10万", 55));
//        wishList.add(new Wish("已完成", "IWC表", "10万", 55));
//        wishList.add(new Wish("已完成", "IWC表", "10万", 55));
    }

}
