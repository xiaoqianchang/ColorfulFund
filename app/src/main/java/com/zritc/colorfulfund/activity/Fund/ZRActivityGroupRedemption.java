package com.zritc.colorfulfund.activity.Fund;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.iView.IGroupRedemptionView;
import com.zritc.colorfulfund.presenter.GroupRedemptionPresenter;
import com.zritc.colorfulfund.ui.adapter.abslistview.CommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;
import com.zritc.colorfulfund.utils.ZRDateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 组合赎回界面
 * 
 * Created by Chang.Xiao on 2016/8/15.
 *
 * @version 1.0
 */
public class ZRActivityGroupRedemption extends ZRActivityToolBar<GroupRedemptionPresenter> implements IGroupRedemptionView {

    @Bind(R.id.lv_product_group)
    ListView productGroup;

    @Bind(R.id.tv_today_redeem)
    TextView tvTodayRedeem;

    @Bind(R.id.tv_redemption_money)
    TextView redemptionMoney; // 赎回费用

    @Bind(R.id.edt_money)
    EditText edtMoney;

    @Bind(R.id.tv_working_days)
    TextView tvWorkingDays; // 预计到账工作日

    @Bind(R.id.cb_all_redemption)
    CheckBox allRedemption;

    @Bind(R.id.btn_redemption_group)
    Button btnRedemptionGroup;

    private GroupRedemptionPresenter groupRedemptionPresenter;

    /**
     * 假初始化数据
     */
    private List<ProductGroup> datas;
    private MyAdapter myAdapter;
    private String todayRedeem; // 今日可赎回
    private double totalMoney;
    private String redemptionCost; // 赎回费用
    private String moneyStr = ""; // 赎回金额
    private boolean isAllRedemption; // 是否全部赎回
    private int workingDays;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_group_redemption;
    }

    @Override
    protected void initPresenter() {
        groupRedemptionPresenter = new GroupRedemptionPresenter(this, this);
        groupRedemptionPresenter.init();
    }

    @Override
    public void initView() {
        setTitleText("基金赎回");
        initData();

        myAdapter = new MyAdapter(this, R.layout.lv_group_redemption_item, datas);
        productGroup.setAdapter(myAdapter);
        edtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*
				 * 小数精确到后两位
				 */
                checkStr(s, start, before);
            }

            /**
             * 赎回份额的输入不可以大于持有的份额，若输入超过，则提示，“您输入的份额大于可赎回份额。”
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                // 开始搜索计算、显示
                calculate(s.toString().trim());
//                groupRedemptionPresenter.doEstimateBuyFundFee("2", "ZH000484", Double.parseDouble(s.toString().trim()));
                // 判断输入的金额是否大于持有的份额
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    if (Double.parseDouble(s.toString().trim()) > Double.parseDouble(tvTodayRedeem.getText().toString())) {
                        showToast("您输入的份额大于可赎回份额。");
                        // 不可点击
                        btnRedemptionGroup.setEnabled(false);
                        btnRedemptionGroup.setBackgroundResource(R.drawable.btn_disable);
                    } else {
                        btnRedemptionGroup.setEnabled(true);
                        btnRedemptionGroup.setBackgroundResource(R.drawable.btn_group_redemption);
                    }
                }
            }

            private void checkStr(CharSequence s, int start, int before) {
                // 整数限五位
                if (!s.toString().contains(".")) {
                    if (s.toString().length() > 5) {
                        s = s.toString().subSequence(0, 5).toString();
                        edtMoney.setText(s);
                    }
                }
                // 小数精确到后两位
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        edtMoney.setText(s);
                        edtMoney.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    edtMoney.setText(s);
                    edtMoney.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        edtMoney.setText(s.subSequence(0, 1));
                        edtMoney.setSelection(1);
                        return;
                    }
                }

                moneyStr = s.toString();

            }
        });

        tvTodayRedeem.setText(todayRedeem);
        redemptionMoney.setText(String.format(getString(R.string.money), redemptionCost));
        edtMoney.setText(moneyStr);
        allRedemption.setChecked(true);
        tvWorkingDays.setText(String.format(getResources().getString(R.string.working_days), workingDays));
    }

    /**
     * 初始化假数据
     */
    private void initData() {
        datas = new ArrayList<>();
        datas.add(new ProductGroup("大成现金增利货币", "4600.00"));
        datas.add(new ProductGroup("鹏华股票型价值基金", "4600.00"));
        datas.add(new ProductGroup("光大股票型价值基金", "3200.00"));

        // 算总金额
        for (int i = 0; i < datas.size(); i++) {
            totalMoney += Double.parseDouble(datas.get(i).getValue());
        }

        // 算产品所占比例
        for (int j = 0; j < datas.size(); j++) {
            datas.get(j).setRatio(Double.parseDouble(datas.get(j).getValue()) / totalMoney);
        }

        todayRedeem = "8000.00";
        redemptionCost = "123.00";
        moneyStr = String.format("%.2f", totalMoney);
        workingDays = 2;
        isAllRedemption = true;
    }

    /**
     * 动态计算金额并显示
     *
     * @param money
     */
    private void calculate(String money) {
        if (TextUtils.isEmpty(money)) {
            edtMoney.setText("0");
            money = "0";
        }
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setValue(String.format("%.2f", Double.parseDouble(money) * datas.get(i).getRatio()));
        }
        myAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_redemption_group, R.id.ll_all_redemption})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_redemption_group:
                groupRedemptionPresenter.doGroupRedemption("ZH000484", edtMoney.getText().toString().trim());
                break;
            case R.id.ll_all_redemption:
                if (allRedemption.isChecked()) {
                    allRedemption.setChecked(false);
                } else {
                    allRedemption.setChecked(true);
                }
                break;
        }
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof RedeemPo) {
            // 组合赎回
            // 计算预计到账日
            RedeemPo redeemPo = (RedeemPo) object;
            int diffDays = ZRDateUtils.getDiffDays(ZRDateUtils.getDiffTime(ZRDateUtils.getCurrentTimeMillis(), redeemPo.expectedTransferIntoDate));
            startActivity(new Intent(this, ZRActivityRedemptionResult.class));
        } else if (object instanceof EstimateBuyFundFee) {
            // 估算申购费用
            redemptionMoney.setText(String.valueOf(((EstimateBuyFundFee) object).buyFee));
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
        startActivity(new Intent(this, ZRActivityRedemptionResult.class));
    }

    class MyAdapter extends CommonAdapter<ProductGroup> {

        public MyAdapter(Context context, int layoutId, List<ProductGroup> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ProductGroup productGroup) {
            holder.setText(R.id.tv_name, productGroup.getName());
            holder.setText(R.id.tv_value, String.format(mContext.getResources().getString(R.string.money), productGroup.getValue()));
        }
    }

    class ProductGroup {
        public String name;
        public String value;
        private double ratio;

        public ProductGroup() {
        }

        public ProductGroup(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public double getRatio() {
            return ratio;
        }

        public void setRatio(double ratio) {
            this.ratio = ratio;
        }
    }
}
