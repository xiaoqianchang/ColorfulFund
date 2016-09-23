package com.zritc.colorfulfund.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.fund.ZRActivityMyFundGroupDetail;
import com.zritc.colorfulfund.data.model.mine.MyProperty;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.iView.IMyPropertyView;
import com.zritc.colorfulfund.presenter.MyPropertyPresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 我的资产界面
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class ZRActivityMyProperty extends ZRActivityToolBar<MyPropertyPresenter> implements IMyPropertyView {

    @Bind(R.id.tv_total_income)
    TextView tvTotalIncome; // 总收益

    @Bind(R.id.tv_property_total_amount)
    TextView tvPropertyTotalAmount; // 资产总额

    @Bind(R.id.lv_group_property)
    ZRListView lvGroupProperty;

    private MyPropertyPresenter presenter;
    private PropertyAdapter adapter;
    private List<MyProperty.Property> datas;
    private MyProperty myProperty;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_property;
    }

    @Override
    protected void initPresenter() {
        presenter = new MyPropertyPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        setTitleText("我的资产");
        getExtraData();
        datas = new ArrayList<>();
        adapter = new PropertyAdapter(this, datas, R.layout.lv_my_property_item);
        lvGroupProperty.setAdapter(adapter);
        lvGroupProperty.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent();
            intent.setClass(this, ZRActivityMyFundGroupDetail.class);
            intent.putExtra("poCode", myProperty.propertyList.get(position).poCode); // "ZH000484"
            startActivity(intent);
        });
        refreshView();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            myProperty = (MyProperty) bundle.getSerializable("myProperty");
        }
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

    private void refreshView() {
        tvTotalIncome.setText(myProperty.totalIncome);
        tvPropertyTotalAmount.setText(myProperty.propertyTotalAmount);
        datas.clear();
        datas.addAll(myProperty.propertyList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }

    class PropertyAdapter extends ZRCommonAdapter<MyProperty.Property> {

        public PropertyAdapter(Context context, List<MyProperty.Property> datas, int itemLayoutId) {
            super(context, datas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, MyProperty.Property item) {
            helper.setText(R.id.tv_group_name, item.poName);
            helper.setText(R.id.tv_balance, String.format("￥%s", item.balance));
            helper.setText(R.id.tv_income, String.format("￥%s", item.income));
            helper.getView(R.id.tv_purchase).setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setClass(mContext, ZRActivityMultiFundApplyPurchase.class);
                Bundle bundle = new Bundle();
                bundle.putString("poCode", item.poCode); // "ZH000484"
                bundle.putString("money", item.balance); // "100"
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            });
        }
    }
}
