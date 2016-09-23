package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.data.model.wish.WishCategory;
import com.zritc.colorfulfund.data.response.wish.GetWishListTypes4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ICreateWishView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 建立心愿
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class CreateWishPresenter extends BasePresenter<ICreateWishView> {

    private List<Integer> imgUrls = new ArrayList<>();
    public List<Integer> imgToSettingUrls = new ArrayList<>();
    public List<WishCategory> categoryList;

    public CreateWishPresenter(Context context, ICreateWishView iView) {
        super(context, iView);
        imgUrls.add(R.mipmap.packet_white);
        imgUrls.add(R.mipmap.car_white);
        imgUrls.add(R.mipmap.watch_white);
        imgUrls.add(R.mipmap.phone_white);
        imgUrls.add(R.mipmap.travel_white);
        imgUrls.add(R.mipmap.custom_white);
        categoryList = new ArrayList<>();

        imgToSettingUrls.add(R.mipmap.packet);
        imgToSettingUrls.add(R.mipmap.car);
        imgToSettingUrls.add(R.mipmap.watch);
        imgToSettingUrls.add(R.mipmap.phone);
        imgToSettingUrls.add(R.mipmap.travel);
        imgToSettingUrls.add(R.mipmap.custom);
    }

    @Override
    public void release() {

    }

    /**
     * 获取心愿类型
     */
    public void doGetWishListTypes() {
        Call<GetWishListTypes4C> wishListTypes4CCallbackByPost = ZRNetManager.getInstance().getWishListTypes4CCallbackByPost();
        wishListTypes4CCallbackByPost.enqueue(new ResponseCallBack<GetWishListTypes4C>(GetWishListTypes4C.class) {
            @Override
            public void onSuccess(GetWishListTypes4C getWishListTypes4C) {
                dataConverter(getWishListTypes4C);
                iView.onSuccess(this);
            }

            @Override
            public void onError(String code, String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 网络模型数据转换器
     */
    private void dataConverter(GetWishListTypes4C getWishListTypes4C) {
        categoryList.clear();
        List<GetWishListTypes4C.WishListTypes> wishListTypes = getWishListTypes4C.wishListTypes;
        for (int i = 0; i < wishListTypes.size(); i++) {
            WishCategory wishCategory = new WishCategory();
            wishCategory.wishTypeId = wishListTypes.get(i).wishTypeId;
            wishCategory.imgUrl = imgUrls.get(i);
            wishCategory.name = wishListTypes.get(i).wishTypeName;
            categoryList.add(wishCategory);
        }
//        categoryList.add(new WishCategory(R.mipmap.packet, "包包"));
//        categoryList.add(new WishCategory(R.mipmap.car, "汽车"));
//        categoryList.add(new WishCategory(R.mipmap.watch, "手表"));
//        categoryList.add(new WishCategory(R.mipmap.phone, "手机"));
//        categoryList.add(new WishCategory(R.mipmap.travel, "旅游"));
//        categoryList.add(new WishCategory(R.mipmap.custom, "自定义"));
    }
}
