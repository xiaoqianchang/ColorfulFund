package com.zritc.colorfulfund.activity.fund;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.adapter.TimeLineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 赎回结果
 *
 * Created by Chang.Xiao on 2016/8/17.
 *
 * @version 1.0
 */
public class ZRActivityRedemptionResult extends ZRActivityToolBar {

    @Bind(R.id.time_line_recycler)
    RecyclerView mRecycler;

    @Bind(R.id.btn_redemption_group)
    Button btnRedemptionGroup;

    private TimeLineAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_redemption_result;
    }

    @Override
    protected void initPresenter() {
        initView();
    }

    private void initView() {
        setTitleText("交易结果");
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TimeLineAdapter(this, getDatas());
        mRecycler.setAdapter(mAdapter);
    }

    private List<String> getDatas() {
        List<String> datas = new ArrayList<String>();
//        datas.add("扣款成功，基金申购份额4000.00元");
//        datas.add("确认交易等待基金公司确认份额");
//        datas.add("基金公司已确认份额");
        datas.add("基金赎回提交成功，赎回份额：");
        datas.add("确认交易，等待基金公司登记赎回");
        datas.add("基金公司已确认赎回，等待付款");
        datas.add("已付款");
        return datas;
    }

    @OnClick(R.id.btn_redemption_group)
    public void onClick(View view) {
        finish();
    }
}
