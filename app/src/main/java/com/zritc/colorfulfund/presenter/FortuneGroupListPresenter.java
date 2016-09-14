package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.model.circle.PostList4C;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IFortuneGroupListView;

import retrofit2.Call;

/**
 * FortuneGroupListPresenter 圈子列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-23
 * @lastUpdate 2016-8-23
 */
public class FortuneGroupListPresenter extends BasePresenter<IFortuneGroupListView> {

    public FortuneGroupListPresenter(Context context, IFortuneGroupListView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void getPostList4C(int boardId, int pageIndex) {
        Call<GetPostList4C> postList4CCallbackByPost = ZRNetManager.getInstance().getPostList4CCallbackByPost(String.valueOf(boardId), String.valueOf(pageIndex));
        postList4CCallbackByPost.enqueue(new ResponseCallBack<GetPostList4C>(GetPostList4C.class) {
            @Override
            public void onSuccess(GetPostList4C getPostList4C) {
                PostList4C postList4C = new PostList4C();
                for (GetPostList4C.PostList x : getPostList4C.postListPerPage.postList) {
                    PostList4C y = new PostList4C();
                    y.pageindex = getPostList4C.postListPerPage.pageindex;
                    y.articleId = x.articleId;
                    y.articleType = x.articleType;
                    y.title = x.title;
                    y.thumbNumber = x.thumbNumber;
                    y.commentNumber = x.commentNumber;
                    y.content = x.content;
                    y.coverImgURL = x.coverImgURL;
                    y.postTime = x.postTime;
                    y.quote = x.quote;
                    y.visitNumber = x.visitNumber;
                    y.nickName = x.authorInfo.nickName;
                    if (x.tagList.size()>0) {
                        y.tagId = x.tagList.get(0).tagId;
                        y.tagName = x.tagList.get(0).tagName;
                        y.color = x.tagList.get(0).color;
                    }
                    postList4C.postList.add(y);
                }
                iView.onSuccess(postList4C);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

}
