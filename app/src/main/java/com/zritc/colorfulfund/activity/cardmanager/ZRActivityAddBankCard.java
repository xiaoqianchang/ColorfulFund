package com.zritc.colorfulfund.activity.cardmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.BindPayment;
import com.zritc.colorfulfund.data.response.trade.PrepareBindPayment;
import com.zritc.colorfulfund.iView.IAddCardView;
import com.zritc.colorfulfund.presenter.AddCardPresenter;
import com.zritc.colorfulfund.ui.ZRCountDownButton;
import com.zritc.colorfulfund.ui.ZREditText;
import com.zritc.colorfulfund.ui.ZRGridPasswordView;
import com.zritc.colorfulfund.ui.ZRItemID;
import com.zritc.colorfulfund.ui.ZRItemMobile;
import com.zritc.colorfulfund.ui.ZRItemPan;
import com.zritc.colorfulfund.ui.ZRItemTextInput;
import com.zritc.colorfulfund.utils.ZRFileUtils;
import com.zritc.colorfulfund.utils.ZRPopupUtil;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * ZRActivityAddBankCard 绑定银行卡
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-8-15
 * @lastUpdate 2016-8-15
 */
public class ZRActivityAddBankCard extends ZRActivityToolBar<AddCardPresenter> implements IAddCardView {

    private static final int REQUEST_CODE_TRADE_PASSWORD_SET = 0x111;

    @Bind(R.id.id_rootview)
    View rootView;

    @Bind(R.id.id_edt_username)
    ZRItemTextInput edtUserName;

    @Bind(R.id.id_edt_iccard)
    ZRItemID edtICCard;

    @Bind(R.id.id_edt_bank_card)
    ZRItemPan edtBankCard;

    @Bind(R.id.id_edt_mobile)
    ZRItemMobile edtMobile;

    @Bind(R.id.id_ll_bank_card_type)
    View bankCardTypeView;

    @Bind(R.id.id_line)
    View line;

    @Bind(R.id.id_tab_line)
    View tabline;

    @Bind(R.id.id_txt_card_info)
    TextView txtCardInfo;

    @Bind(R.id.id_txt_trade_pro)
    TextView txtTradePro;

    @Bind(R.id.id_btn_next)
    Button btnNext;

    private AddCardPresenter addCardPresenter;
    private Dialog dialog;
    private ZRGridPasswordView gridPasswordView;
    private int visibleHeight = 0;

    private String realName = "";
    private String identityNo = "";
    private String paymentType = "";
    private String paymentNo = "";
    private String phone = "";
    private String vCode = "";

    ZREditText.ZRTextWatcher textWatcher = new ZREditText.ZRTextWatcher() {
        @Override
        public void onTextChanged(View view, CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(View view, CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(View view, Editable s) {
            realName = edtUserName.getValue().toString();
            identityNo = edtICCard.getValue().toString();
            paymentNo = edtBankCard.getValue().toString();
            phone = edtMobile.getValue().toString();

            // 获取银行卡代码
            String bankOwn = bankCode.compareBank(paymentNo);
            if (!TextUtils.isEmpty(bankOwn)) {
                bankCardTypeView.setVisibility(View.VISIBLE);
                txtCardInfo.setText(bankOwn);
                tabline.setVisibility(View.VISIBLE);
            } else {
                bankCardTypeView.setVisibility(View.GONE);
                txtCardInfo.setText("");
                tabline.setVisibility(View.GONE);
            }

            // 获取支付类型
            paymentType = bankInfo.getPaymentType(txtCardInfo.getText().toString());

            boolean enable = !TextUtils.isEmpty(realName) && edtICCard.isValidate() && !TextUtils.isEmpty(paymentNo) && paymentNo.length() > 15 && edtMobile.isValidate();
            btnNext.setEnabled(enable);
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    protected void initPresenter() {
        addCardPresenter = new AddCardPresenter(this, this);
        addCardPresenter.init();
        initBankCode();
        initBankInfo();
    }

    @Override
    public void initView() {

        setTitleText("添加银行卡");

        edtUserName.setBackgroundDrawable(null);
        edtUserName.setHint("请输入您的真实姓名");
        edtUserName.addTextChangedListener(textWatcher);

        edtICCard.setIDType(ZRItemID.TYPE_ID_CARD);
        edtICCard.setBackgroundDrawable(null);
        edtICCard.setHint("请输入您的身份证号");
        edtICCard.addTextChangedListener(textWatcher);

        edtBankCard.setBackgroundDrawable(null);
        edtBankCard.setHint("请输入银行卡号");
        edtBankCard.addTextChangedListener(textWatcher);

        edtMobile.setBackgroundDrawable(null);
        edtMobile.setHint("请输入银行卡预留手机号");
        edtMobile.addTextChangedListener(textWatcher);

        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    showProgress("处理中...");
                    prepareBindPayment();
                });

        // Test begin
        /*edtUserName.setValue("顾飞");
        edtICCard.setValue("321283198909203859");
        edtBankCard.setValue("6217230200004388585");
        edtMobile.setValue("18917212395");
        btnNext.setEnabled(true);

        realName = edtUserName.getValue().toString();
        identityNo = edtICCard.getValue().toString();
        paymentNo = edtBankCard.getValue().toString();
        phone = edtMobile.getValue().toString();*/
        // test end
    }

    private void prepareBindPayment() {
        addCardPresenter.prepareBindPayment(realName, identityNo, paymentType, paymentNo, phone);
    }

    private void openValidCodeView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_verification_code, null, false);
        gridPasswordView = (ZRGridPasswordView) view.findViewById(R.id.gpv_normal);
        gridPasswordView.togglePasswordVisibility();
        gridPasswordView.setOnPasswordChangedListener(new ZRGridPasswordView.OnPasswordChangedListener() {

            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                vCode = psw;
                addCardPresenter.bindPayment(realName, identityNo, paymentType, paymentNo, phone, vCode);
            }
        });

        Button btnSmsCode = (Button) view.findViewById(R.id.btn_send_verifycode);
        ZRCountDownButton timer = new ZRCountDownButton();
        timer.init(this, btnSmsCode);
        timer.setTickDrawable(this.getResources().getDrawable(R.drawable.btn_countdown_disable));
        timer.start();
        btnSmsCode.setOnClickListener(v -> {
            timer.start();
            retryPreBind = true;
            prepareBindPayment();
        });

        dialog = ZRPopupUtil.makeCenterPopup(this, view);
        dialog.setOnCancelListener((DialogInterface dialog) -> {

        });
        dialog.show();
    }

    /**
     * 3.自动弹出软键盘
     **/
    private void openSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(
                gridPasswordView, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void jump2TradePwdSet() {
        Intent intent = new Intent();
        intent.setClass(this, ZRActivityTradePasswordSet.class);
        startActivityForResult(intent, REQUEST_CODE_TRADE_PASSWORD_SET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TRADE_PASSWORD_SET:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    private boolean retryPreBind;

    @Override
    public void onSuccess(Object object) {
        if (object instanceof PrepareBindPayment) {
            if (retryPreBind)
                return;
            openValidCodeView();
            openSoftKeyboard();
        } else if (object instanceof BindPayment) {
            dialog.dismiss();
            jump2TradePwdSet();
        }
    }

    @Override
    public void onError(String msg) {
        hideProgress();
        showToast(msg);
    }

    private BankCode bankCode;
    private BankInfo bankInfo;

    private void initBankCode() {
        try {
            this.bankCode = new BankCode();
            InputStream inputStream = this.getAssets().open("bankcode.json");
            String s = ZRFileUtils.readStream(inputStream);
            JSONObject json = new JSONObject(s);
            Iterator it = json.keys();
            while (it.hasNext()) {
                String key = it.next().toString();
                String value = json.optString(key);
                BankCode bankCode = new BankCode();
                bankCode.setCode(key);
                bankCode.setName(value);
                this.bankCode.datas.add(bankCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initBankInfo() {
        try {
            InputStream inputStream = this.getAssets().open("bankinfo.json");
            String json = ZRFileUtils.readStream(inputStream);
            bankInfo = new Gson().fromJson(json, BankInfo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class BankCode {

        private String code;
        private String name;
        private List<BankCode> datas = new ArrayList<>();

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<BankCode> getDatas() {
            return datas;
        }

        public void setDatas(List<BankCode> datas) {
            this.datas = datas;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String compareBank(String code) {
            if (TextUtils.isEmpty(code))
                return "";
            for (BankCode x : datas) {
                if (code.startsWith(x.getCode())) {
                    return x.getName();
                }
            }
            return "";
        }

        @Override
        public String toString() {
            return "BankCode{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", datas=" + datas +
                    '}';
        }
    }

    private class BankInfo {

        private String name;
        private String paymentType;
        private String maxRapidPayAmountPerTxn;
        private String maxRapidPayAmountPerDay;
        private String maxRapidPayAmountPerMonth;
        private String maxRapidPayTxnCountPerDay;

        private List<BankInfo> datas = new ArrayList<>();

        public String getMaxRapidPayAmountPerDay() {
            return maxRapidPayAmountPerDay;
        }

        public void setMaxRapidPayAmountPerDay(String maxRapidPayAmountPerDay) {
            this.maxRapidPayAmountPerDay = maxRapidPayAmountPerDay;
        }

        public String getMaxRapidPayAmountPerMonth() {
            return maxRapidPayAmountPerMonth;
        }

        public void setMaxRapidPayAmountPerMonth(String maxRapidPayAmountPerMonth) {
            this.maxRapidPayAmountPerMonth = maxRapidPayAmountPerMonth;
        }

        public String getMaxRapidPayAmountPerTxn() {
            return maxRapidPayAmountPerTxn;
        }

        public void setMaxRapidPayAmountPerTxn(String maxRapidPayAmountPerTxn) {
            this.maxRapidPayAmountPerTxn = maxRapidPayAmountPerTxn;
        }

        public String getMaxRapidPayTxnCountPerDay() {
            return maxRapidPayTxnCountPerDay;
        }

        public void setMaxRapidPayTxnCountPerDay(String maxRapidPayTxnCountPerDay) {
            this.maxRapidPayTxnCountPerDay = maxRapidPayTxnCountPerDay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        @Override
        public String toString() {
            return "BankInfo{" +
                    "maxRapidPayAmountPerDay='" + maxRapidPayAmountPerDay + '\'' +
                    ", name='" + name + '\'' +
                    ", paymentType='" + paymentType + '\'' +
                    ", maxRapidPayAmountPerTxn='" + maxRapidPayAmountPerTxn + '\'' +
                    ", maxRapidPayAmountPerMonth='" + maxRapidPayAmountPerMonth + '\'' +
                    ", maxRapidPayTxnCountPerDay='" + maxRapidPayTxnCountPerDay + '\'' +
                    '}';
        }

        private String getPaymentType(String bankName) {
            if (TextUtils.isEmpty(bankName))
                return "";
            for (BankInfo x : datas) {
                if (bankName.equals(x.name)) {
                    return x.paymentType;
                }
            }
            return "";
        }
    }


}
