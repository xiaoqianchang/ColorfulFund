package com.zritc.colorfulfund.activity.Fund;

import android.widget.Button;
import android.widget.EditText;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.ZRFund;
import com.zritc.colorfulfund.iView.IMultiFundApplyPurchaseView;
import com.zritc.colorfulfund.presenter.MultiFundApplyPurchasePresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ZRActivityMultiFundApplyPurchase 多只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class ZRActivityMultiFundApplyPurchase extends ZRActivityToolBar<MultiFundApplyPurchasePresenter> implements IMultiFundApplyPurchaseView {

    @Bind(R.id.id_listview)
    ZRListView listView;
    @Bind(R.id.id_edt_buy_money)
    EditText edtBuyMoney;
    @Bind(R.id.id_btn_next)
    Button btnNext;

    private ZRCommonAdapter<ZRFund> adapter;
    private MultiFundApplyPurchasePresenter multiFundApplyPurchasePresenter;

    private List<ZRFund> datas = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_multi_fund_apply_purchase;
    }

    @Override
    protected void initPresenter() {
        multiFundApplyPurchasePresenter = new MultiFundApplyPurchasePresenter(this, this);
        multiFundApplyPurchasePresenter.init();
    }

    @Override
    public void initView() {

        setTitleText("基金申购");

        listView.setAdapter(adapter = new ZRCommonAdapter<ZRFund>(this, datas, R.layout.cell_fund_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, ZRFund item) {
                helper.setText(R.id.id_txt_name, item.getName());
                helper.setText(R.id.id_txt_money, item.getMoney());
            }
        });

        for (int i = 0; i < 4; i++) {
            ZRFund fund = new ZRFund();
            fund.setId(i);
            fund.setName("大成现金增利货币");
            fund.setMoney("2499.00元");
            datas.add(fund);
        }
        adapter.notifyDataSetChanged();
        listView.setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }

}
