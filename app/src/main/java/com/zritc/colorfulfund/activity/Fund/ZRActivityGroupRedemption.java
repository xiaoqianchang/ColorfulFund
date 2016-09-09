package com.zritc.colorfulfund.activity.fund;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.GetUserPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.RedeemPo;
import com.zritc.colorfulfund.iView.IGroupRedemptionView;
import com.zritc.colorfulfund.presenter.GroupRedemptionPresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.abslistview.CommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;
import com.zritc.colorfulfund.utils.StringUtils;
import com.zritc.colorfulfund.utils.ZRDateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private static final int REQUEST_CODE_GROUP_REDEMPTION_RESULT = 0x110;

    @Bind(R.id.lv_product_group)
    ZRListView productGroup;

    @Bind(R.id.tv_today_redeem)
    TextView tvTodayRedeem;

    @Bind(R.id.tv_redemption_money)
    TextView redemptionMoney; // 赎回费用

    @Bind(R.id.edt_money)
    EditText edtMoney;

    @Bind(R.id.tv_working_days)
    TextView tvWorkingDays; // 预计到账工作日

    @Bind(R.id.btn_redemption_group)
    Button btnRedemptionGroup;

    private GroupRedemptionPresenter groupRedemptionPresenter;
    private String poCode = ""; // 基金代码

    /**
     * 假初始化数据
     */
    private List<GetUserPoInfo4C.UserFundListPerBank> userFundListPerBank;
    private MyAdapter myAdapter;
    private String todayRedeem; // 今日可赎回
    private double totalMoney;
    private String redemptionCost; // 赎回费用
    private String moneyStr = ""; // 赎回金额
    private boolean isAllRedemption = true; // 是否全部赎回
    private int workingDays;
    private double maxRedeemAmount; // 今日可赎回最大金额
    private double minRedeemAmount; // 今日最小赎回金额

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
        Intent intent = getIntent();
        if (null != intent) {
            poCode = intent.getStringExtra("poCode");
        }
        userFundListPerBank = new ArrayList<>();
        productGroup.setDivider(null);
        productGroup.setListViewHeightBasedOnChildren(productGroup);
        tvTodayRedeem.setText("0");
        // 获取数据
        groupRedemptionPresenter.doGetUserPoInfo4C(poCode);

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
                // 判断输入的金额是否大于持有的份额
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    if (!isAllRedemption) {
                    if (Double.parseDouble(s.toString().trim()) > Double.parseDouble(tvTodayRedeem.getText().toString())) {
                        showToast("您输入的份额大于可赎回份额。");
                        // 不可点击
                        btnRedemptionGroup.setEnabled(false);
                    } else {
                        btnRedemptionGroup.setEnabled(true);
                    }
                    } else {
                        // 设置输入框不可编辑
                        edtMoney.setEnabled(false);
                        btnRedemptionGroup.setEnabled(true);
                    }
                    if (StringUtils.isZero(s.toString().trim())) {
                        btnRedemptionGroup.setEnabled(false);
                }
                edtMoney.setSelection(s.length());
                } else {
                    btnRedemptionGroup.setEnabled(false);
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
    }

    /**
     * 初始化假数据
     */
    private void initData(GetUserPoInfo4C getUserPoInfo4C) {
        if (null != getUserPoInfo4C) {
            List<GetUserPoInfo4C.UserPoInfoListPerBank> userPoInfoListPerBanks = getUserPoInfo4C.userPoInfo.userPoInfoListPerBank;
            if (userPoInfoListPerBanks.size() == 0) {
                showToast("用户没有组合信息");
                edtMoney.setEnabled(false);
                return;
            }
            GetUserPoInfo4C.UserPoInfoListPerBank userPoInfoListPerBank = getUserPoInfo4C.userPoInfo.userPoInfoListPerBank.get(0);
            if (null != userPoInfoListPerBank) {
                userFundListPerBank = userPoInfoListPerBank.userFundListPerBank;
            }
            myAdapter = new MyAdapter(this, R.layout.lv_group_redemption_item, userFundListPerBank);
            productGroup.setAdapter(myAdapter);
            // 允许赎回最大金额
            maxRedeemAmount = userPoInfoListPerBank.poRedeemableAsset.maxRedeemAmount;
            minRedeemAmount = userPoInfoListPerBank.poRedeemableAsset.minRedeemAmount;
            if (StringUtils.isZero(maxRedeemAmount) && StringUtils.isZero(minRedeemAmount)) {
                tvTodayRedeem.setText("只能允许全额赎回");
                isAllRedemption = true;
            } else {
                tvTodayRedeem.setText(String.valueOf(maxRedeemAmount));
                isAllRedemption = false;
            }

        // 算总金额
            for (int i = 0; i < userFundListPerBank.size(); i++) {
                totalMoney += userFundListPerBank.get(i).poFundAssetInfo.totalAmount;
        }

        // 算产品所占比例
            for (int j = 0; j < userFundListPerBank.size(); j++) {
                if (!StringUtils.isZero(totalMoney))
                    userFundListPerBank.get(j).fundInfo.poPercentage = userFundListPerBank.get(j).poFundAssetInfo.totalAmount / totalMoney;
            }
            // 数据赋值
//            myAdapter.notifyDataSetChanged();
            edtMoney.setText(StringUtils.getMoneyByFormat(String.valueOf(totalMoney)));
        }
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
        for (int i = 0; i < userFundListPerBank.size(); i++) {
            userFundListPerBank.get(i).poFundAssetInfo.totalAmount = Double.parseDouble(money) * userFundListPerBank.get(i).fundInfo.poPercentage;
        }
        myAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_redemption_group})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_redemption_group:
                groupRedemptionPresenter.doGroupRedemption(poCode, edtMoney.getText().toString().trim());
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
        if (object instanceof GetUserPoInfo4C) {
            // 获取我要赎回基金的详情
            initData((GetUserPoInfo4C) object);
            btnRedemptionGroup.setEnabled(true);
        }else if (object instanceof RedeemPo) {
            // 组合赎回
            // 计算预计到账日
            RedeemPo redeemPo = (RedeemPo) object;
            int diffDays = ZRDateUtils.getDiffDays(ZRDateUtils.getDiffTime(ZRDateUtils.getCurrentTimeMillis(), redeemPo.expectedTransferIntoDate));
            startActivityForResult(new Intent(this, ZRActivityRedemptionResult.class), REQUEST_CODE_GROUP_REDEMPTION_RESULT);
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_GROUP_REDEMPTION_RESULT:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class MyAdapter extends CommonAdapter<GetUserPoInfo4C.UserFundListPerBank> {

        public MyAdapter(Context context, int layoutId, List<GetUserPoInfo4C.UserFundListPerBank> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, GetUserPoInfo4C.UserFundListPerBank userFundListPerBank) {
            holder.setText(R.id.tv_name, userFundListPerBank.fundInfo.fundName);
            holder.setText(R.id.tv_value, String.format(mContext.getResources().getString(R.string.money), StringUtils.getMoneyByFormat(userFundListPerBank.poFundAssetInfo.totalAmount)));
        }
    }
}
