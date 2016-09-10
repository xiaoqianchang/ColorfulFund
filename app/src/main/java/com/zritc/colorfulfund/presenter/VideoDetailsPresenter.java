package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IVideoDetailsView;

import retrofit2.Call;

/**
 * 视频详情presenter
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class VideoDetailsPresenter extends BasePresenter<IVideoDetailsView> {

    public VideoDetailsPresenter(Context context, IVideoDetailsView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取帖子详情
     *
     * @param postId
     */
    public void doGetPostInfo(String postId) {
        Call<GetPostInfo4C> postInfo4CCallbackByPost = ZRNetManager.getInstance().getPostInfo4CCallbackByPost(postId);
        postInfo4CCallbackByPost.enqueue(new ResponseCallBack<GetPostInfo4C>(GetPostInfo4C.class) {
            @Override
            public void onSuccess(GetPostInfo4C getPostInfo4C) {
                iView.onSuccess(getPostInfo4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }
}
