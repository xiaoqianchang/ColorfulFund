package com.zritc.colorfulfund.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.zritc.colorfulfund.data.model.circle.PostList4C;
import com.zritc.colorfulfund.data.model.mine.Collection;
import com.zritc.colorfulfund.data.response.circle.GetPostList4C;
import com.zritc.colorfulfund.data.response.user.GetUserCollectionList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.ICollectionView;

import java.util.List;

import retrofit2.Call;

/**
 * 收藏
 * <p>
 * Created by Chang.Xiao on 2016/9/23.
 *
 * @version 1.0
 */
public class CollectionPresenter extends BasePresenter<ICollectionView> {

    public CollectionPresenter(Context context, ICollectionView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    /**
     * 获取收藏列表
     *
     * @param pageIndex
     */
    public void doGetUserCollectionList(int pageIndex) {
        Call<GetUserCollectionList4C> getUserCollectionList4CCall = ZRNetManager.getInstance().getUserCollectionList4CCallbackByPost(String.valueOf(pageIndex));
        getUserCollectionList4CCall.enqueue(new ResponseCallBack<GetUserCollectionList4C>(GetUserCollectionList4C.class) {
            @Override
            public void onSuccess(GetUserCollectionList4C getUserCollectionList4C) {
                Collection collection = collectionConverter(getUserCollectionList4C);
                iView.onSuccess(collection);
            }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    /**
     * 收藏列表转换器
     *
     * @param getUserCollectionList4C
     * @return
     */
    private Collection collectionConverter(GetUserCollectionList4C getUserCollectionList4C) {
        Collection collection = new Collection();
        GetUserCollectionList4C.PageInfo pageInfo = getUserCollectionList4C.pageInfo;
        if (TextUtils.isEmpty(pageInfo.pageIndex)) {
            collection.pageIndex = 0;
        } else {
            collection.pageIndex = Integer.parseInt(pageInfo.pageIndex);
        }
        List<GetUserCollectionList4C.UserCollectionList> userCollectionList =  pageInfo.userCollectionList;
        if (null != userCollectionList) {
            for (GetUserCollectionList4C.UserCollectionList userCollection : userCollectionList) {
                GetUserCollectionList4C.PostInfo postInfo = userCollection.postInfo;
                if (null != postInfo) {
                    Collection.Post post = new Collection().new Post();
                    post.articleId = postInfo.articleId;
                    post.coverImgURL = postInfo.coverImgURL;
                    post.title = postInfo.title;
                    post.articleType = postInfo.articleType;
                    post.content = postInfo.content;
                    post.quote = postInfo.quote;
                    post.postTime = postInfo.postTime;
                    post.thumbNumber = postInfo.thumbNumber;
                    post.commentNumber = postInfo.commentNumber;
                    post.visitNumber = postInfo.visitNumber;
                    GetUserCollectionList4C.AuthorInfo authorInfo = postInfo.authorInfo;
                    if (null != authorInfo) {
                        post.userId = authorInfo.userId;
                        post.nickName = authorInfo.nickName;
                        post.photoURL = authorInfo.photoURL;
                    }
                    List<GetUserCollectionList4C.TagList> tagList = postInfo.tagList;
                    if (null != tagList) {
                        for (GetUserCollectionList4C.TagList tags : tagList) {
                            if (null != tags) {
                                Collection.Tag tag = new Collection().new Tag();
                                tag.tagId = tags.tagId;
                                tag.tagName = tags.tagName;
                                tag.color = tags.color;
                                post.tagList.add(tag);
                            }
                        }
                    }
                    collection.postList.add(post);
                }
            }
        }
        return collection;
    }
}
