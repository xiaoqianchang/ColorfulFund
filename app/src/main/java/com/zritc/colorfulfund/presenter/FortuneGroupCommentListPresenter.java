package com.zritc.colorfulfund.presenter;

import android.content.Context;

import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.CreateReport;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.http.ResponseCallBack;
import com.zritc.colorfulfund.http.ZRNetManager;
import com.zritc.colorfulfund.iView.IFortuneGroupCommentListView;

import retrofit2.Call;

/**
 * FortuneGroupCommentListPresenter 圈子评论列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-23
 * @lastUpdate 2016-8-23
 */
public class FortuneGroupCommentListPresenter extends BasePresenter<IFortuneGroupCommentListView> {

    public FortuneGroupCommentListPresenter(Context context, IFortuneGroupCommentListView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        mSubscription.unsubscribe();
    }

    public void commentList4C(String postId) {
        Call<GetCommentList4C> commentList4CCallbackByPost = ZRNetManager.getInstance().getCommentList4CCallbackByPost(postId);
        commentList4CCallbackByPost.enqueue(new ResponseCallBack<GetCommentList4C>(GetCommentList4C.class) {
            @Override
            public void onSuccess(GetCommentList4C getCommentList4C) {
                iView.onSuccess(getCommentList4C);
    }

            @Override
            public void onError(String code, String msg) {
                iView.onError(msg);
            }
        });
    }

    public void sendComment(String postId, String comment) {
        iView.showProgress("正在提交...");
        Call<CreateComment> commentCallbackByPost = ZRNetManager.getInstance().createCommentCallbackByPost(postId, comment);
        commentCallbackByPost.enqueue(new ResponseCallBack<CreateComment>(CreateComment.class) {
            @Override
            public void onSuccess(CreateComment createComment) {
                iView.hideProgress();
                iView.onSuccess(createComment);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

    public void report(String commentid) {
        iView.showProgress("正在提交...");
        Call<CreateReport> reportCallbackByPost = ZRNetManager.getInstance().createReportCallbackByPost(commentid);
        reportCallbackByPost.enqueue(new ResponseCallBack<CreateReport>(CreateReport.class) {
            @Override
            public void onSuccess(CreateReport createReport) {
                iView.hideProgress();
                iView.onSuccess(createReport);
            }

            @Override
            public void onError(String code, String msg) {
                iView.hideProgress();
                iView.onError(msg);
            }
        });
    }

}
