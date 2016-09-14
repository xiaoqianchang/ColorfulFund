package com.zritc.colorfulfund.activity.fund;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.edu.PoFund;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.iView.IFundGroupDetailView;
import com.zritc.colorfulfund.presenter.FundGroupDetailPresenter;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityFundGroupDetail 我的历史基金投资详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-30
 * @lastUpdate 2016-08-30
 */
public class ZRActivityMyFundGroupDetail extends ZRActivityBase<FundGroupDetailPresenter> implements IFundGroupDetailView {

    @Bind(R.id.btn_left_back)
    ImageButton btnBack;

    @Bind(R.id.text_fund_name)
    TextView textFundName;

    @Bind(R.id.text_fund_type)
    TextView textFundType;

    @Bind(R.id.text_fund_first)
    TextView textFundFirst;

    @Bind(R.id.text_fund_month)
    TextView textFundMonth;

    @Bind(R.id.text_fund_year)
    TextView textFundYear;

    @Bind(R.id.text_fund_date)
    TextView textFundDate;

    @Bind(R.id.text_fund_year_rate_return)
    TextView textFundYearRateReturn;

    @Bind(R.id.text_fund_rate_return)
    TextView textFundRateReturn;

    @Bind(R.id.list_view)
    ListView listView;

    private ZRCommonAdapter<PoFund> adapter;

    private FundGroupDetailPresenter fundGroupDetailPresenter;

    private List<PoFund> datas = new ArrayList<>();

    private String poCode;
    private String initialtAmount;//初始投资额

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
        return R.layout.activity_my_fund_group_detail;
    }

    @Override
    protected void initPresenter() {
        fundGroupDetailPresenter = new FundGroupDetailPresenter(this, this);
        fundGroupDetailPresenter.init();
        fundGroupDetailPresenter.getUserPoAssetInfo4C(poCode);
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            poCode = bundle.getString("poCode");
        }
    }

    @Override
    public void initView() {

        getExtraData();

        listView.setAdapter(adapter = new ZRCommonAdapter<PoFund>(this, datas, R.layout.cell_fund_detail_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, PoFund item) {
                helper.setText(R.id.text_name, item.fundName);
                helper.setText(R.id.text_per, item.poPercentage);
                double money = 0;
                if (!TextUtils.isEmpty(item.poPercentage) && !TextUtils.isEmpty(initialtAmount)) {
                    money = Double.parseDouble(item.poPercentage) * Double.parseDouble(initialtAmount);
                }
                helper.setText(R.id.text_money, String.valueOf(money));
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
        if (object instanceof UserPoAssetInfo) {
            UserPoAssetInfo userPoAssetInfo = ((UserPoAssetInfo) object);
            initialtAmount = userPoAssetInfo.initialtAmount;//初始投资
            String profitbythismonth = userPoAssetInfo.profitbythismonth;//每月定投
            String level = userPoAssetInfo.riskLevel;
            if (level.equals("0"))
                level = "未评估过";
            else if (level.equals("1"))
                level = "保守型";
            else if (level.equals("2"))
                level = "稳健型";
            else if (level.equals("3"))
                level = "进取型";
            textFundName.setText("宝宝基金");
            textFundType.setText(level);
            textFundFirst.setText("初始投资：¥" + initialtAmount);
            textFundMonth.setText("每月追加：¥" + profitbythismonth);

            textFundYear.setText(ZRUtils.getDateLength(ZRUtils.getCurrentTime("yyyyMMdd"), ZRUtils.formatTime(userPoAssetInfo.targetDate, "yyyyMMdd"))[0] + "年");
            textFundDate.setText(ZRUtils.formatTime(userPoAssetInfo.targetDate, "yy/MM/dd"));
            textFundYearRateReturn.setText(userPoAssetInfo.expectedYearlyRoe + "%");
            textFundRateReturn.setText(userPoAssetInfo.expectedYearlyRoe + "%");

            datas.addAll(userPoAssetInfo.poFundList);
        }
    }

    @Override
    public void onError(String msg) {

    }
}
