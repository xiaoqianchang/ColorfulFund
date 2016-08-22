package com.zritc.colorfulfund.activity.Fund;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.adapter.TimeLineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ZRActivityApplyPurchaseResult 申购结果
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class ZRActivityApplyPurchaseResult extends ZRActivityToolBar {

    @Bind(R.id.time_line_recycler)
    RecyclerView mRecycler;

    private TimeLineAdapter mAdapter;

    private String amount = "";//申购金额

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fund_apply_purchase_result;
    }

    @Override
    protected void initPresenter() {
        Bundle bundle = getIntent().getExtras();
        amount = bundle.getString("amount");
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
        datas.add(String.format("扣款成功，基金申购份额%s元", amount));
        datas.add("确认交易等待基金公司确认份额");
        datas.add("基金公司已确认份额");
        return datas;
    }
}
