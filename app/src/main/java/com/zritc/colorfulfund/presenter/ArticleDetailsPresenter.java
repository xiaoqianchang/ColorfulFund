package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.circle.CreateCollection;
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
}
