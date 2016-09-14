package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.circle.CreatePost;
import com.zritc.colorfulfund.data.response.edu.GetGrowingPicList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IGenerateAlbumView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/2.
 *
 * @version 1.0
 */
public class GenerateAlbumPresenter extends BasePresenter<IGenerateAlbumView> {

    /**
     * 成长相册
     */
    public List<String> photoUrls;

    public GenerateAlbumPresenter(Context context, IGenerateAlbumView iView) {
        super(context, iView);
        photoUrls = new ArrayList<>();
    }

    @Override
    public void release() {

    }

    /**
     * 获取成长记录图片列表
     */
    public void doGrowingPicList() {
        Call<GetGrowingPicList4C> getGrowingPicList4CCallbackByPost = ZRNetManager.getInstance().getGrowingPicList4CCallbackByPost();
        getGrowingPicList4CCallbackByPost.enqueue(new ResponseCallBack<GetGrowingPicList4C>(GetGrowingPicList4C.class) {
            @Override
            public void onSuccess(GetGrowingPicList4C getGrowingPicList4C) {
                List<GetGrowingPicList4C.GrowingPicList> growingPicList = getGrowingPicList4C.growingPicList;
                if (null != growingPicList && growingPicList.size() > 0) {
                    for (GetGrowingPicList4C.GrowingPicList list : growingPicList) {
                        photoUrls.add(list.photoUrl);
                    }
                }
                iView.onSuccess(this);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }
}
