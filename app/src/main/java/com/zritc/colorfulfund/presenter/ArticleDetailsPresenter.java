package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.circle.CreateCollection;
import com.zritc.colorfulfund.data.response.circle.CreateThumb;
import com.zritc.colorfulfund.data.response.circle.GetPostInfo4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IArticleDetailsView;

import retrofit2.Call;

/**
 * 文章详情presenter
 * <p>
 * Created by Chang.Xiao on 2016/8/23.
 *
 * @version 1.0
 */
public class ArticleDetailsPresenter extends BasePresenter<IArticleDetailsView> {

    public ArticleDetailsPresenter(Context context, IArticleDetailsView iView) {
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

    /**
     * 收藏
     *
     * @param postId
     */
    public void doCollection(String postId) {
        Call<CreateCollection> collectionCallbackByPost = ZRNetManager.getInstance().createCollectionCallbackByPost(postId);
        collectionCallbackByPost.enqueue(new ResponseCallBack<CreateCollection>(CreateCollection.class) {
            @Override
            public void onSuccess(CreateCollection createCollection) {
                iView.onSuccess(createCollection);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 点赞
     *
     * @param postId
     */
    public void doThumb(String postId) {
        Call<CreateThumb> thumbCallbackByPost = ZRNetManager.getInstance().createThumbCallbackByPost(postId);
        thumbCallbackByPost.enqueue(new ResponseCallBack<CreateThumb>(CreateThumb.class) {
            @Override
            public void onSuccess(CreateThumb createThumb) {
                iView.onSuccess(createThumb);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }
}
