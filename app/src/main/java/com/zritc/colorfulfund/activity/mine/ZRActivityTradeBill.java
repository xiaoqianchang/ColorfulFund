package com.zritc.colorfulfund.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.model.mine.TradeBillList;
import com.zritc.colorfulfund.iView.ITradeBillView;
import com.zritc.colorfulfund.presenter.TradeBillPresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 交易账单界面
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRActivityTradeBill extends ZRActivityToolBar<TradeBillPresenter> implements ITradeBillView {

    @Bind(R.id.lv_bill)
    ZRListView lvBill;

    private TradeBillPresenter presenter;
    private BillAdapter adapter;
    private List<TradeBillList.TradeBill> datas;
    private TradeBillList tradeBillList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_trade_bill;
    }

    @Override
    protected void initPresenter() {
        presenter = new TradeBillPresenter(this, this);
        presenter.init();
        // 获取数据
        presenter.doGetUserTradeHistory();
    }

    @Override
    public void initView() {
        setTitleText("我的组合");
        datas = new ArrayList<>();
        adapter = new BillAdapter(this, datas, R.layout.lv_bill_item);
        lvBill.setAdapter(adapter);
        lvBill.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent(this, ZRActivityTradeBillDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("tradeBill", tradeBillList.tradeBillList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof TradeBillList) {
            tradeBillList = (TradeBillList) object;
            refreshView();
        }
    }

    private void refreshView() {
        datas.clear();
        datas.addAll(tradeBillList.tradeBillList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }

    static class BillAdapter extends ZRCommonAdapter<TradeBillList.TradeBill> {

        public BillAdapter(Context context, List<TradeBillList.TradeBill> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, TradeBillList.TradeBill item) {
            helper.setText(R.id.tv_group_name, item.fundName);
            helper.setText(R.id.tv_balance, String.format("￥%s", item.fundAmount));
        }
    }
}
