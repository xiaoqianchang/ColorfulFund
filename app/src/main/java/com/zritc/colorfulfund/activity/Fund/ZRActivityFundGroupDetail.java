package com.zritc.colorfulfund.activity.fund;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.IFundGroupDetailView;
import com.zritc.colorfulfund.presenter.FundGroupDetailPresenter;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityFundGroupDetail 基金组合详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-30
 * @lastUpdate 2016-08-30
 */
public class ZRActivityFundGroupDetail extends ZRActivityToolBar<FundGroupDetailPresenter> implements IFundGroupDetailView {

    @Bind(R.id.btn_left_back)
    ImageButton btnBack;

    @Bind(R.id.list_view)
    ListView listView;

    private ZRCommonAdapter<Fund> adapter;

    private FundGroupDetailPresenter fundGroupDetailPresenter;

    @OnClick({R.id.btn_left_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left_back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fund_group_detail;
    }

    @Override
    protected void initPresenter() {
        fundGroupDetailPresenter = new FundGroupDetailPresenter(this, this);
        fundGroupDetailPresenter.init();
    }

    @Override
    public void initView() {
        //
        datas.add(new Fund("华夏股票型1", "50%", "5000.00"));
        datas.add(new Fund("华夏股票型2", "10%", "600.00"));
        datas.add(new Fund("华夏股票型3", "20%", "12000.00"));
        datas.add(new Fund("华夏股票型4", "20%", "2000.00"));
        //

        showToolBar(View.GONE);

        listView.setAdapter(adapter = new ZRCommonAdapter<Fund>(this, datas, R.layout.cell_fund_detail_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, Fund item) {
                helper.setText(R.id.text_name, item.getName());
                helper.setText(R.id.text_per, item.getPer());
                helper.setText(R.id.text_money, item.getMoney());
            }
        });

        listView.setDivider(null);
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

    private List<Fund> datas = new ArrayList<>();

    private class Fund {
        private String name;
        private String per;
        private String money;

        private List<Fund> datas = new ArrayList<>();

        public Fund(String name, String per, String money) {
            this.money = money;
            this.name = name;
            this.per = per;
        }

        public String getMoney() {
            return money;
        }

        public String getName() {
            return name;
        }

        public String getPer() {
            return per;
        }
    }

}
