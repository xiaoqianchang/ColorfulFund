package com.zritc.colorfulfund.activity.mine;

import android.content.Context;
import android.os.Bundle;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.model.mine.TradeBillList;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class ZRActivityTradeBillDetail extends ZRActivityToolBar {

    @Bind(R.id.lv_trade_bill_detail)
    ZRListView lvTradeBillDetail;

    private TradeBillDetailAdapter adapter;
    private List<TradeBillList.TradeBillDetail> datas;
    private TradeBillList.TradeBill tradeBill;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_trade_bill_detail;
    }

    @Override
    protected void initPresenter() {
        datas = new ArrayList<>();
        getExtraData();
        setTitleText(tradeBill.fundName);
        adapter = new TradeBillDetailAdapter(this, datas, R.layout.lv_my_property_item);
        lvTradeBillDetail.setAdapter(adapter);
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            tradeBill = (TradeBillList.TradeBill) bundle.getSerializable("tradeBill");
            if (null != tradeBill) {
                datas.clear();
                datas.addAll(tradeBill.tradeBillDetailList);
            }
        }
    }

    static class TradeBillDetailAdapter extends ZRCommonAdapter<TradeBillList.TradeBillDetail> {

        public TradeBillDetailAdapter(Context context, List<TradeBillList.TradeBillDetail> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, TradeBillList.TradeBillDetail item) {
            helper.setText(R.id.tv_group_name, item.actionType);
            helper.setText(R.id.tv_balance, item.status);
            helper.setText(R.id.tv_income, item.createDate);
            helper.setText(R.id.tv_purchase, String.format("￥%s", item.tradeMoney));
        }
    }
}
