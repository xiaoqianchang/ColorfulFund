package com.zritc.colorfulfund.activity.CardManage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.IAddCardView;
import com.zritc.colorfulfund.presenter.AddCardPresenter;
import com.zritc.colorfulfund.ui.ZRCountDownButton;
import com.zritc.colorfulfund.ui.ZREditText;
import com.zritc.colorfulfund.ui.ZRGridPasswordView;
import com.zritc.colorfulfund.ui.ZRItemID;
import com.zritc.colorfulfund.ui.ZRItemMobile;
import com.zritc.colorfulfund.ui.ZRItemPan;
import com.zritc.colorfulfund.ui.ZRItemTextInput;
import com.zritc.colorfulfund.utils.ZRPopupUtil;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

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

    ZREditText.ZRTextWatcher textWatcher = new ZREditText.ZRTextWatcher() {
        @Override
        public void onTextChanged(View view, CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(View view, CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(View view, Editable s) {
            String username = edtUserName.getValue().toString();
            String iccard = edtICCard.getValue().toString();
            String bankcard = edtBankCard.getValue().toString();
            String mobile = edtMobile.getValue().toString();

            bankCardTypeView.setVisibility(bankcard.length() > 3 ? View.VISIBLE : View.GONE);
            txtCardInfo.setText("招商银行");
            tabline.setVisibility(bankcard.length() > 3 ? View.VISIBLE : View.GONE);

            boolean enable = username.isEmpty() || iccard.isEmpty() || bankcard.isEmpty() || mobile.isEmpty();
            btnNext.setEnabled(!enable);
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
                        openValidCodeView();
                });
    }

    private void openValidCodeView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_verification_code, null, false);
        gridPasswordView = (ZRGridPasswordView) view.findViewById(R.id.gpv_normal);
        gridPasswordView.togglePasswordVisibility();
        gridPasswordView.setOnPasswordChangedListener(new ZRGridPasswordView.OnPasswordChangedListener(){

            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                dialog.dismiss();
                jump2TradePwdSet();
            }
        });

        Button btnSmsCode = (Button) view.findViewById(R.id.btn_send_verifycode);
        ZRCountDownButton timer = new ZRCountDownButton();
        timer.init(this, btnSmsCode);
        timer.setTickDrawable(this.getResources().getDrawable(R.drawable.btn_countdown_disable));
        timer.start();
        btnSmsCode.setOnClickListener(v->{
            timer.start();
        });

        dialog = ZRPopupUtil.makeCenterPopup(this, view);
        dialog.setOnCancelListener((DialogInterface dialog) -> {

        });
        dialog.show();

        /** 3.自动弹出软键盘 **/
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(
//                gridPasswordView, InputMethodManager.RESULT_SHOWN);
//        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);

//        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
//            getKeyboardHeight();
//        });

    }

    /**
     * 计算软键盘的高度
     */
    private void getKeyboardHeight() {
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);

        int _visibleHeight = r.height();

        if (visibleHeight == 0) {
            visibleHeight = _visibleHeight;
            return;
        }

        if (visibleHeight == _visibleHeight) {
            return;
        }

        visibleHeight = _visibleHeight;
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
