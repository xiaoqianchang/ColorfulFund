package com.zritc.colorfulfund.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.adapter.ZRFundManagerAdapter;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.model.fund.FundManager;
import com.zritc.colorfulfund.iView.IFundNews;
import com.zritc.colorfulfund.presenter.FundNewsPresenter;
import com.zritc.colorfulfund.ui.ZRSyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ZRFragmentFundNews 基金动态
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public class ZRFragmentFundNews extends ZRFragmentBase<FundNewsPresenter> implements IFundNews {
    private static final String ARG_PARAM = "param";

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.time_line_recycler)
    RecyclerView recycler;

    private FundNewsPresenter fundNewsPresenter;
    private ZRFundManagerAdapter adapter;
    private List<FundManager> datas = new ArrayList<>();
    private boolean hasMoreData;
    private int pageIndex = 0;

    public static ZRFragmentFundNews newInstance(String param) {
        ZRFragmentFundNews fragment = new ZRFragmentFundNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_fund_news;
    }

    @Override
    protected void initPresenter() {
        fundNewsPresenter = new FundNewsPresenter(getActivity(), this);
        fundNewsPresenter.init();
    }

    @Override
    public void initView() {
        // 刷新组件
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
//                if (listView.getFirstVisiblePosition() == 0 && hasMoreData) {
//                    this.pageIndex++;
//                } else {
//                    showToast("没有更多啦");
//                }
                swipeRefreshLayout.setRefreshing(false);
            }, 2000);
        });

        ZRSyLinearLayoutManager layoutManager = new ZRSyLinearLayoutManager(getActivity());
//        layoutManager.setAutoMeasureEnabled(true);
        recycler.setLayoutManager(layoutManager);
        adapter = new ZRFundManagerAdapter(getActivity(), getDatas());
        recycler.setAdapter(adapter);
    }

    private List<FundManager> getDatas() {
        FundManager x = new FundManager();
        x.setDate(1474621388367l);
        x.setCategory(0);
        x.setCategoryName("调仓提醒");
        x.setAdjustTitle("由于市场风格变化，您的组合需要进行调整");
        x.setFundName("鹏华军工A级");
        x.setAdjustUpdate("40%>>60%");
        datas.add(x);

        FundManager x1 = new FundManager();
        x1.setDate(1474621388367l);
        x1.setCategory(1);
        x1.setCategoryName("投资简报");
        x1.setCurrentMonthProft("100");
        x1.setCurrentMonthInvestment("5,800");
        x1.setCurrentTrend("稳定增长");
        datas.add(x1);

        FundManager x2 = new FundManager();
        x2.setDate(1474621388367l);
        x2.setCategory(2);
        x2.setCategoryName("专家建议");
        x2.setExpertOpinionTitle("机构行为观察：一般来讲，机构");
        datas.add(x2);

        FundManager x3 = new FundManager();
        x3.setDate(1474620047367l);
        x3.setCategory(3);
        x3.setCategoryName("教育基金完成");
        x3.setEduTitle("孩子钢琴课（100%）");
        x3.setEduDate(1474621388367l);
        datas.add(x3);

        FundManager x4 = new FundManager();
        x4.setDate(1474620047367l);
        x4.setCategory(4);
        x4.setCategoryName("定投提醒");
        x4.setFundReminderTitle("孩子提琴课");
        x4.setFundReminderPer("（100%）");
        x4.setFundReminderDate(1474621388367l);
        datas.add(x4);

        FundManager x5 = new FundManager();
        x5.setDate(1474620047367l);
        x5.setCategory(5);
        x5.setCategoryName("心愿基金完成");
        x5.setWishTitle("孩子二胡课（100%）");
        x5.setWishDate(1474621388367l);
        datas.add(x5);
        return datas;
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


}
