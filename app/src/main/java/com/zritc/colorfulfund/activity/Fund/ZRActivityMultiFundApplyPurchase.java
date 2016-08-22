package com.zritc.colorfulfund.activity.Fund;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.CardManage.ZRActivityCardManage;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.BuyPo;
import com.zritc.colorfulfund.data.response.trade.EstimateBuyFundFee;
import com.zritc.colorfulfund.data.response.trade.GetFundPoInfo4C;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.iView.IMultiFundApplyPurchaseView;
import com.zritc.colorfulfund.presenter.MultiFundApplyPurchasePresenter;
import com.zritc.colorfulfund.ui.ZRCircleImageView;
import com.zritc.colorfulfund.ui.ZREditText;
import com.zritc.colorfulfund.ui.ZRItemTextInput;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRImageLoaderHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityMultiFundApplyPurchase 多只基金申购
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class ZRActivityMultiFundApplyPurchase extends ZRActivityToolBar<MultiFundApplyPurchasePresenter> implements IMultiFundApplyPurchaseView {

    private static final int REQUEST_CODE_ADD_BANK_CARD = 0x110;
    private static final int REQUEST_CODE_APPLY_PURCHASE_RESULT = 0x111;

    @Bind(R.id.id_listview)
    ZRListView listView;

    @Bind(R.id.id_rl_add_bank)
    View rlAddBank;

    @Bind(R.id.id_rl_bank_of_card)
    View rlBankCard;

    @Bind(R.id.id_img_bank)
    ZRCircleImageView imgBank;

    @Bind(R.id.id_txt_bank_name)
    TextView textBankName;

    @Bind(R.id.id_txt_card_info)
    TextView textCardInfo;

    @Bind(R.id.id_edt_buy_money)
    ZRItemTextInput edtBuyMoney;

    @Bind(R.id.id_txt_buy_fee)
    TextView textBuyFee;

    @Bind(R.id.id_btn_next)
    Button btnNext;

    private ZRCommonAdapter<GetFundPoInfo4C.PoFundList> adapter;
    private MultiFundApplyPurchasePresenter multiFundApplyPurchasePresenter;

    private GetUserBankCards4C.UserBankCardList bankCardList;

    private List<GetFundPoInfo4C.PoFundList> datas = new ArrayList<>();
    private GetFundPoList4C.FundPoList fundPoList;
    private GetFundPoInfo4C.UserPaymentInfo userPaymentInfo;
    private String amount;

    @OnClick({R.id.id_rl_add_bank, R.id.id_rl_bank_of_card, R.id.id_btn_next})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.id_rl_bank_of_card:
            case R.id.id_rl_add_bank:
                intent.setClass(this, ZRActivityCardManage.class);
                intent.putExtra(ZRConstant.KEY_FROM_WHICH_ACTIVITY, ZRActivityMultiFundApplyPurchase.class.getName());
                startActivityForResult(intent, REQUEST_CODE_ADD_BANK_CARD);
                break;
            case R.id.id_btn_next:
                if (null != userPaymentInfo)
                    multiFundApplyPurchasePresenter.buyPo(userPaymentInfo.userPaymentId, fundPoList.poCode, amount);
                break;
        }
    }

    ZREditText.ZRTextWatcher watcher = new ZREditText.ZRTextWatcher() {
        @Override
        public void onTextChanged(View view, CharSequence s, int start, int before, int count) {
            /**
             * 小数精确到后两位
             */
            checkStr(s, start, before);
        }

        @Override
        public void beforeTextChanged(View view, CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(View view, Editable s) {
            amount = s.toString().trim();
            // 开始搜索计算、显示
            calculate(amount);
            // 计算申购费
            doEstimateBuyFundFee();
            boolean enable = TextUtils.isEmpty(amount) && TextUtils.isEmpty(textBankName.getText().toString());
            btnNext.setEnabled(!enable);
        }

        private void checkStr(CharSequence s, int start, int before) {
            // 整数限五位
//            if (!s.toString().contains(".")) {
//                if (s.toString().length() > 5) {
//                    s = s.toString().subSequence(0, 5).toString();
//                    edtBuyMoney.setText(s);
//                }
//            }
            // 小数精确到后两位
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    edtBuyMoney.setValue(s.toString());
                    edtBuyMoney.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                edtBuyMoney.setValue(s.toString());
                edtBuyMoney.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    edtBuyMoney.setValue(s.subSequence(0, 1).toString());
                    edtBuyMoney.setSelection(1);
                    return;
                }
            }

            amount = s.toString();
        }

    };

    private void doEstimateBuyFundFee() {
        if (!TextUtils.isEmpty(amount))
            multiFundApplyPurchasePresenter.doEstimateBuyFundFee("2", fundPoList.poCode, amount);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_multi_fund_apply_purchase;
    }

    @Override
    protected void initPresenter() {
        getExtraData();
        multiFundApplyPurchasePresenter = new MultiFundApplyPurchasePresenter(this, this);
        multiFundApplyPurchasePresenter.init();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        userPaymentInfo = (GetFundPoInfo4C.UserPaymentInfo) bundle.getSerializable("GetFundPoInfo4C");
        fundPoList = (GetFundPoList4C.FundPoList) bundle.getSerializable("GetFundPoList4C.FundPoList");
    }

    @Override
    public void initView() {

        setTitleText("基金申购");

        adapter = new ZRCommonAdapter<GetFundPoInfo4C.PoFundList>(this, datas, R.layout.cell_fund_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, GetFundPoInfo4C.PoFundList item) {
                helper.setText(R.id.id_txt_name, item.fundName);
                helper.setText(R.id.id_txt_money, item.totalAmount);
            }
        };
        listView.setDivider(null);
        edtBuyMoney.setBackgroundDrawable(null);
        edtBuyMoney.addTextChangedListener(watcher);
        listView.setListViewHeightBasedOnChildren(listView);

        multiFundApplyPurchasePresenter.fundPoInfo4C(fundPoList.poCode);
    }

    /**
     * 动态计算金额并显示
     *
     * @param money
     */
    private void calculate(String money) {
        if (TextUtils.isEmpty(money)) {
            edtBuyMoney.setValue("");
            money = "0";
            textBuyFee.setText("");
        }
        for (int i = 0; i < datas.size(); i++) {
            String proPercentage = datas.get(i).poPercentage;
            if (TextUtils.isEmpty(proPercentage))
                continue;
            double _poPercentage = Double.parseDouble(proPercentage);
            datas.get(i).totalAmount = String.format("%.2f元", Double.parseDouble(money) * _poPercentage);
        }
        adapter.notifyDataSetChanged();
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
        if (object instanceof GetFundPoInfo4C) {
            GetFundPoInfo4C getFundPoInfo4C = ((GetFundPoInfo4C) object);
            GetFundPoInfo4C.FundPoInfo fundPoInfo = getFundPoInfo4C.fundPoInfo;
            datas.clear();
            datas.addAll(fundPoInfo.poFundList);
            adapter.setData(datas);
            listView.setAdapter(adapter);

            if (null == userPaymentInfo) {
                return;
            }
            String userPaymentId = userPaymentInfo.userPaymentId;
            // 绑过卡，购买过基金
            if (!TextUtils.isEmpty(userPaymentId)) {
                rlAddBank.setVisibility(View.GONE);
                rlBankCard.setVisibility(View.VISIBLE);
                ZRImageLoaderHelper.getInstance().loadImage(userPaymentInfo.bankLogo, imgBank, R.mipmap.icon_share_logo);
                textBankName.setText(userPaymentInfo.bankName);
                textCardInfo.setText("单笔限额：" + userPaymentInfo.maxRapidPayAmountPerTxn + "万" + " 日累计限额：" + userPaymentInfo.maxRapidPayAmountPerDay + "万");
                boolean enable = amount.isEmpty() && TextUtils.isEmpty(textBankName.getText().toString());
                btnNext.setEnabled(!enable);
            } else {
                rlAddBank.setVisibility(View.VISIBLE);
                rlBankCard.setVisibility(View.GONE);
                btnNext.setEnabled(false);
            }
        } else if (object instanceof BuyPo) {
            // 组合申购
            Intent intent = new Intent(this, ZRActivityApplyPurchaseResult.class);
            intent.putExtra("amount", amount);
            startActivityForResult(intent, REQUEST_CODE_APPLY_PURCHASE_RESULT);
        } else if (object instanceof EstimateBuyFundFee) {
            // 估算申购费用
            double buyFee = ((EstimateBuyFundFee) object).buyFee;
            String feePert = String.format("申购费 %.1f", (buyFee / Double.parseDouble(amount)) * 100) + "%";
            textBuyFee.setText(feePert + String.format("（申购费用%s元）", buyFee));
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
                case REQUEST_CODE_ADD_BANK_CARD:
                    bankCardList = (GetUserBankCards4C.UserBankCardList) data.getExtras().getSerializable("bankinfo");
                    rlAddBank.setVisibility(View.GONE);
                    rlBankCard.setVisibility(View.VISIBLE);
                    ZRImageLoaderHelper.getInstance().loadImage(bankCardList.bankLogo, imgBank, R.mipmap.icon_share_logo);
                    textBankName.setText(bankCardList.bankName);
                    textCardInfo.setText("单笔限额：" + bankCardList.maxPayAmountPerTxn + "万" + " 日累计限额：" + bankCardList.maxPayAmountPerDay + "万");
                    boolean enable = TextUtils.isEmpty(amount) && TextUtils.isEmpty(textBankName.getText().toString());
                    btnNext.setEnabled(!enable);
                    break;
                case REQUEST_CODE_APPLY_PURCHASE_RESULT:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
