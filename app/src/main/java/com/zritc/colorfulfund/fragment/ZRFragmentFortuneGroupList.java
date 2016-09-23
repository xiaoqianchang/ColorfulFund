package com.zritc.colorfulfund.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityArticleDetails;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityVideoDetails;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.model.circle.PostList4C;
import com.zritc.colorfulfund.iView.IFortuneGroupListView;
import com.zritc.colorfulfund.presenter.FortuneGroupListPresenter;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshListView;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ZRFragmentFortuneGroupList 财富圈
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-23
 * @lastUpdate 2016-08-23
 */
public class ZRFragmentFortuneGroupList extends ZRFragmentBase<FortuneGroupListPresenter> implements IFortuneGroupListView {
    private static final String ARG_PARAM = "param";

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;

    private ZRListView listView;

    private ZRCommonAdapter<PostList4C> adapter;

    private FortuneGroupListPresenter groupListPresenter;

    private List<PostList4C> datas = new ArrayList<>();
    private int boardId = 1;
    private int pageIndex = 0;
    private boolean hasMoreData = false;
    private String param;

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
            groupListPresenter.getPostList4C(boardId, pageIndex);
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
                groupListPresenter.getPostList4C(boardId, pageIndex);
            }
        }
    };

    public static ZRFragmentFortuneGroupList newInstance(String param) {
        ZRFragmentFortuneGroupList fragment = new ZRFragmentFortuneGroupList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getExtraArguments() {
        param = getArguments().getString(ARG_PARAM);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fortune_group_list;
    }

    @Override
    protected void initPresenter() {
        groupListPresenter = new FortuneGroupListPresenter(getActivity(), this);
        groupListPresenter.init();
    }

    @Override
    public void initView() {

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);
        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<PostList4C>(
                mContext, datas, R.layout.cell_fortune_group_list_item) {
            @Override
            public void convert(int position, ZRViewHolder holder,
                                final PostList4C item) {
                holder.setText(R.id.text_title, item.title);
                holder.setText(R.id.text_channel, item.tagName);
                holder.setText(R.id.text_publisher, item.nickName);
                holder.setText(R.id.text_date, ZRUtils.calTimePast(ZRUtils.formatTime(item.postTime, ZRUtils.TIME_FORMAT2)));
                holder.setText(R.id.text_collection, item.thumbNumber + "");
                holder.setText(R.id.text_during, "");
                ((ZRCircleImageView) holder.getView(R.id.img_album)).setRectAdius(16);
                holder.setImageByUrl(R.id.img_album, item.coverImgURL);
                if (TextUtils.isEmpty(item.photoURL))
                    ((ImageView) holder.getView(R.id.img_user)).setImageResource(R.mipmap.icon_header);
                else
                    holder.setImageByUrl(R.id.img_user, item.photoURL, R.mipmap.icon_header);
                holder.getView(R.id.img_play).setVisibility(item.articleType.equals("1") ? View.GONE : View.VISIBLE);
            }

        });
        listView.setVerticalScrollBarEnabled(false);
//        listView.setDivider(null);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent();
            if (datas.get(position).articleType.equals("1")) {
                intent.setClass(getActivity(), ZRActivityArticleDetails.class);
                intent.putExtra("postId", datas.get(position).articleId);
            } else {
                intent.setClass(getActivity(), ZRActivityVideoDetails.class);
                intent.putExtra("postId", datas.get(position).articleId);
            }
            intent.putExtra("postId",datas.get(position).articleId);
            startActivity(intent);
        });
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof PostList4C) {
            PostList4C postList4C = (PostList4C) object;
            if (pageIndex == 0)
                datas.clear();
            datas.addAll(postList4C.postList);
            hasMoreData = datas.size() != adapter.getCount();
            onLoadComplete();
        }
    }

    @Override
    public void onError(String msg) {
        onLoadComplete();
    }
}
