package com.zritc.colorfulfund.activity.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityArticleDetails;
import com.zritc.colorfulfund.activity.fortunegroup.ZRActivityVideoDetails;
import com.zritc.colorfulfund.data.model.mine.Collection;
import com.zritc.colorfulfund.iView.ICollectionView;
import com.zritc.colorfulfund.presenter.CollectionPresenter;
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
 * 收藏界面
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRActivityCollection extends ZRActivityToolBar<CollectionPresenter> implements ICollectionView {

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;

    private CollectionPresenter presenter;
    private ZRListView listView;

    private ZRCommonAdapter<Collection.Post> adapter;

    private List<Collection.Post> datas = new ArrayList<>();
    private int pageIndex = 0;
    private boolean hasMoreData = false;

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
            presenter.doGetUserCollectionList(pageIndex);
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
                presenter.doGetUserCollectionList(pageIndex);
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initPresenter() {
        presenter = new CollectionPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        setTitleText("收藏");
        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);
        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<Collection.Post>(
                mContext, datas, R.layout.cell_fortune_group_list_item) {
            @Override
            public void convert(int position, ZRViewHolder holder,
                                final Collection.Post item) {
                holder.setText(R.id.text_title, item.title);
                holder.setText(R.id.text_channel, item.tagList.get(0).tagName);
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
                intent.setClass(this, ZRActivityArticleDetails.class);
                intent.putExtra("postId", datas.get(position).articleId);
            } else {
                intent.setClass(this, ZRActivityVideoDetails.class);
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
        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (pageIndex == 0)
                datas.clear();
            datas.addAll(collection.postList);
            hasMoreData = datas.size() != adapter.getCount();
            onLoadComplete();
        }
    }

    @Override
    public void onError(String msg) {
        onLoadComplete();
    }
}
