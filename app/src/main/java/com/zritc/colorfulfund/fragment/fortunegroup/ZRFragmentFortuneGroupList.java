package com.zritc.colorfulfund.fragment.fortunegroup;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityArticleDetails;
import com.zritc.colorfulfund.activity.ZRActivityVideoDetails;
import com.zritc.colorfulfund.base.ZRFragmentBase;
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

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;

    private ZRListView listView;

    private ZRCommonAdapter<FortuneGroupList> adapter;

    private FortuneGroupListPresenter groupListPresenter;

    private List<FortuneGroupList> datas = new ArrayList<>();
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

            }
        }
    };

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
        // TestDatas start
        datas.add(new FortuneGroupList("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "老虎财经", "Businesss", "30", "05:03", "35分钟前", "http://img0.pcgames.com.cn/pcgames/1607/14/6239789_1.jpg", "潘基文：多投资少女，因为他们可能拥有改变整个国家的力量。", "0"));
        datas.add(new FortuneGroupList("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "alko", "投资学院", "134", "", "35分钟前", "http://img0.pcgames.com.cn/pcgames/1607/14/6239789_1.jpg", "Pokmon go的版权在哪个公司？", "1"));
        // TestDatas end

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);

        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<FortuneGroupList>(
                mContext, datas, R.layout.cell_fortune_group_list_item) {
            @Override
            public void convert(int position, ZRViewHolder holder,
                                final FortuneGroupList item) {
                holder.setText(R.id.text_title, item.getTitle());
                holder.setText(R.id.text_channel, item.getChannel());
                holder.setText(R.id.text_publisher, item.getAuthorName());
                holder.setText(R.id.text_date, item.getPublishTime());
                holder.setText(R.id.text_collection, item.getCollection());
                holder.setText(R.id.text_during, item.getDuring());
                ((ZRCircleImageView)holder.getView(R.id.img_album)).setRectAdius(16);
                holder.setImageByUrl(R.id.img_album, item.getImage());
                holder.setImageByUrl(R.id.img_user, item.getAuthorImage());
                holder.getView(R.id.img_play).setVisibility(TextUtils.isEmpty(item.getDuring())?View.GONE:View.VISIBLE);
            }

        });
        listView.setVerticalScrollBarEnabled(false);
//        listView.setDivider(null);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(datas.get(position).getDuring())) {
                intent.setClass(getActivity(), ZRActivityArticleDetails.class);
            } else {
                intent.setClass(getActivity(), ZRActivityVideoDetails.class);
            }
            startActivity(intent);
        });
        pullToRefreshListView.setOnRefreshListener(onRefreshListener);

        // 初次进界面给与初始刷新时间，并自动触发下拉刷新请求
        setLastUpdateTime();

        // Test code
        onLoadComplete();
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
        hasMoreData = false;
        onLoadComplete();
    }

    @Override
    public void onError(String msg) {
        onLoadComplete();
    }

    public class FortuneGroupList {

        private String id;
        private String title;
        private String image;
        private String authorImage;
        private String authorName;
        private String publishTime;
        private String collection;
        private String type;
        private String channel;
        private String during;

        public FortuneGroupList(String authorImage, String authorName, String channel, String collection, String during, String publishTime, String image, String title, String type) {
            this.authorImage = authorImage;
            this.authorName = authorName;
            this.channel = channel;
            this.collection = collection;
            this.during = during;
            this.publishTime = publishTime;
            this.image = image;
            this.title = title;
            this.type = type;
        }

        private List<FortuneGroupList> datas = new ArrayList<>();

        public List<FortuneGroupList> getDatas() {
            return datas;
        }

        public void setDatas(List<FortuneGroupList> datas) {
            this.datas = datas;
        }

        public String getAuthorImage() {
            return authorImage;
        }

        public void setAuthorImage(String authorImage) {
            this.authorImage = authorImage;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getDuring() {
            return during;
        }

        public void setDuring(String during) {
            this.during = during;
        }
    }
}
