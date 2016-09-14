package com.zritc.colorfulfund.activity.fortunegroup;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.circle.CreateComment;
import com.zritc.colorfulfund.data.response.circle.GetCommentList4C;
import com.zritc.colorfulfund.iView.IFortuneGroupCommentListView;
import com.zritc.colorfulfund.presenter.FortuneGroupCommentListPresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshListView;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.widget.FortuneGroupCommentDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityFortuneGroupCommentList 财富圈评论列表
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-23
 * @lastUpdate 2016-08-23
 */
public class ZRActivityFortuneGroupCommentList extends ZRActivityToolBar<FortuneGroupCommentListPresenter> implements IFortuneGroupCommentListView {

    @Bind(R.id.view_comment)
    View commentView;

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;

    private ZRListView listView;
    private Dialog dialog;

    private ZRCommonAdapter<GetCommentList4C.CommentList> adapter;

    private FortuneGroupCommentListPresenter fortuneGroupCommentListPresenter;
    private List<GetCommentList4C.CommentList> datas = new ArrayList<>();
    private int pageIndex = 0;
    private boolean hasMoreData = false;
    private String postId;

    @OnClick({R.id.view_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_comment:
                // 评论弹出
                dialog = new FortuneGroupCommentDialog(this, fortuneGroupCommentListPresenter, postId);
                dialog.show();
                break;
        }
    }

    /**
     * 执行上拉/下拉刷新操作
     */
    private ZRPullToRefreshBase.OnRefreshListener<ZRListView> onRefreshListener = new ZRPullToRefreshBase.OnRefreshListener<ZRListView>() {
        /**
         * 执行下拉刷新
         *
         * @param refreshView
         */
        @Override
        public void onPullDownToRefresh(
                ZRPullToRefreshBase<ZRListView> refreshView) {
            // 刷新的时候清空列表重新获取第一页数据
            pageIndex = 0;
            fortuneGroupCommentListPresenter.commentList4C(postId);
        }

        /**
         * 执行上拉刷新
         *
         * @param refreshView
         */
        @Override
        public void onPullUpToRefresh(
                ZRPullToRefreshBase<ZRListView> refreshView) {
            if (hasMoreData) {
                pageIndex--;
                fortuneGroupCommentListPresenter.commentList4C(postId);
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fortune_group_comment_list;
    }

    @Override
    protected void initPresenter() {
        fortuneGroupCommentListPresenter = new FortuneGroupCommentListPresenter(this, this);
        fortuneGroupCommentListPresenter.init();

    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        postId = bundle.getString("postId");
    }

    @Override
    public void initView() {

        setTitleText("评论");

        getExtraData();

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);

        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<GetCommentList4C.CommentList>(
                mContext, datas, R.layout.cell_fortune_group_comment_list_item) {
            @Override
            public void convert(int position, ZRViewHolder holder,
                                final GetCommentList4C.CommentList item) {
                holder.setText(R.id.text_user, item.commentInfo.authorInfo.nickName);
                holder.setText(R.id.text_time, ZRUtils.calTimePast(ZRUtils.formatTime(item.commentInfo.postTime, ZRUtils.TIME_FORMAT2)));
                holder.setText(R.id.text_comments, item.commentInfo.content);
                if (TextUtils.isEmpty(item.commentInfo.authorInfo.photoURL))
                    ((ImageView) holder.getView(R.id.img_user)).setImageResource(R.mipmap.icon_header);
                else
                    holder.setImageByUrl(R.id.img_user, item.commentInfo.authorInfo.photoURL, R.mipmap.icon_header);
                holder.getView(R.id.text_110).setOnClickListener(v -> {
                    fortuneGroupCommentListPresenter.report(item.commentInfo.commentId + "");
                });
            }

        });
        listView.setVerticalScrollBarEnabled(false);
        pullToRefreshListView.setOnRefreshListener(onRefreshListener);

        // 初次进界面给与初始刷新时间，并自动触发下拉刷新请求
        setLastUpdateTime();
        pullToRefreshListView.doPullRefreshing(true, 1000);
    }

    /**
     * 刷新控件
     */
    private void onLoadComplete() {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onPullUpRefreshComplete();
        pullToRefreshListView.onPullDownRefreshComplete();
        pullToRefreshListView.setHasMoreData(hasMoreData);
        setLastUpdateTime();
    }

    private void setLastUpdateTime() {
        pullToRefreshListView
                .setLastUpdatedLabel(ZRUtils.getCurrentTime("MM-dd HH:mm"));
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GetCommentList4C) {
            GetCommentList4C getCommentList4C = (GetCommentList4C) object;
            if (pageIndex == 0)
                datas.clear();
            if (datas.size() == 1) {
                if (datas.get(0).commentInfo.commentId == 0) {
                    datas.clear();
                }
            }
            datas.addAll(getCommentList4C.commentList);
            hasMoreData = datas.size() != adapter.getCount();
            onLoadComplete();
        } else if (object instanceof CreateComment) {
            dialog.cancel();
            showToast("评论成功！");
            pullToRefreshListView.doPullRefreshing(true, 1000);
        } else {
            dialog.cancel();
            showToast("举报成功！");
        }
    }

    @Override
    public void onError(String msg) {
        onLoadComplete();
    }

}
