package com.zritc.colorfulfund.activity.fortunegroup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityShareEntry;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.IFortuneGroupCommentListView;
import com.zritc.colorfulfund.presenter.FortuneGroupCommentListPresenter;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshListView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRPopupUtil;
import com.zritc.colorfulfund.utils.ZRUtils;

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

    private ZRCommonAdapter<FortuneGroupCommentList> adapter;

    private FortuneGroupCommentListPresenter fortuneGroupCommentListPresenter;
    private List<FortuneGroupCommentList> datas = new ArrayList<>();
    private int pageIndex = 0;
    private boolean hasMoreData = false;

    @OnClick({R.id.view_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_comment:
                // 分享弹出
                Intent intent = new Intent();
                UPMediaMessage message = new UPMediaMessage();
                message.setShareFrom(UPMediaMessage.SHARE_FROM_APP);
                message.setTitle("title");
                message.setSMSDesc("desc");
                message.setDescription("description");
                message.setUrl("http://www.baidu.com");
                intent.setClass(this, ZRActivityShareEntry.class);
                intent.putExtra(ZRConstant.KEY_EXTRA_INTRO, message);
                startActivity(intent);

                // 评论弹出
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.view_sendcomment_pop, null, false);
                dialog = ZRPopupUtil.makePopup(ZRActivityFortuneGroupCommentList.this, v);
                dialog.show();

                EditText edtComment = (EditText) v.findViewById(R.id.edt_comment);
                v.findViewById(R.id.btn_back).setOnClickListener(_v -> dialog.cancel());
                v.findViewById(R.id.btn_ok).setOnClickListener(_v -> {
                    if (!TextUtils.isEmpty(edtComment.getText().toString()))
                        fortuneGroupCommentListPresenter.sendCommentMessage(edtComment.getText().toString());
                });

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
        return R.layout.activity_fortune_group_comment_list;
    }

    @Override
    protected void initPresenter() {
        fortuneGroupCommentListPresenter = new FortuneGroupCommentListPresenter(this, this);
        fortuneGroupCommentListPresenter.init();
    }

    @Override
    public void initView() {

        setTitleText("评论");

        // TestDatas start
        datas.add(new FortuneGroupCommentList("老虎财经", "http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "潘基文：多投资少女，因为他们可能拥有改变整个国家的力量。", "35分钟前"));
        datas.add(new FortuneGroupCommentList("alko", "http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "Pokmon go的版权在哪个公司？", "35分钟前"));
        // TestDatas end

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);

        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<FortuneGroupCommentList>(
                mContext, datas, R.layout.cell_fortune_group_comment_list_item) {
            @Override
            public void convert(int position, ZRViewHolder holder,
                                final FortuneGroupCommentList item) {
                holder.setText(R.id.text_user, item.getAuthor());
                holder.setText(R.id.text_time, item.getPublishTime());
                holder.setText(R.id.text_comments, item.getContent());
                holder.setImageByUrl(R.id.img_user, item.getAuthorImage());
            }

        });
        listView.setVerticalScrollBarEnabled(false);
        pullToRefreshListView.setOnRefreshListener(onRefreshListener);

        // 初次进界面给与初始刷新时间，并自动触发下拉刷新请求
        setLastUpdateTime();

        // test line
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

    }

    @Override
    public void onError(String msg) {

    }

    public class FortuneGroupCommentList {
        private String author;
        private String publishTime;
        private String authorImage;
        private String content;

        private List<FortuneGroupCommentList> datas = new ArrayList<>();

        public FortuneGroupCommentList(String author, String authorImage, String content, String publishTime) {
            this.author = author;
            this.authorImage = authorImage;
            this.content = content;
            this.publishTime = publishTime;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorImage() {
            return authorImage;
        }

        public void setAuthorImage(String authorImage) {
            this.authorImage = authorImage;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<FortuneGroupCommentList> getDatas() {
            return datas;
        }

        public void setDatas(List<FortuneGroupCommentList> datas) {
            this.datas = datas;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }

}
