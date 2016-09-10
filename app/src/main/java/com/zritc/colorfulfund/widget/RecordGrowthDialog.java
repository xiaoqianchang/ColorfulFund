package com.zritc.colorfulfund.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.ui.ZRCircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 记录成长
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class RecordGrowthDialog extends Dialog {

    @Bind(R.id.img_cancle)
    ImageView imgCancle;

    @Bind(R.id.img_avatar)
    CircleImageView imgAvatar;

    @Bind(R.id.edt_description)
    EditText edtDescription;

    @Bind(R.id.ll_save_money)
    LinearLayout llShowSaveMoney;

    @Bind(R.id.edt_money)
    EditText edtMoney;

    @Bind(R.id.btn_save_money)
    Button btnsavemoney;

    @Bind(R.id.btn_complete)
    Button btnComplete;

    private Context mContext;
    private boolean isShowSaveMoney = false;

    public RecordGrowthDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_record_growth);
        ButterKnife.bind(this);

        // 创建一个头像
        ZRCircleImageView avatarmageView = new ZRCircleImageView(mContext);
        avatarmageView.setImageResource(R.mipmap.icon_user);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0 ,0);
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = display.getWidth(); // 设置dialog的宽度为当前手机屏幕的宽度
        window.setWindowAnimations(R.style.animationBottomTranslate);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setAttributes(windowparams);

        imgAvatar.setImageResource(R.mipmap.ic_img_profile_bg);

        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String description = s.toString();
                btnComplete.setEnabled(!TextUtils.isEmpty(description));
            }
        });

        edtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString();
                btnsavemoney.setEnabled(!TextUtils.isEmpty(money));
            }
        });
    }

    @OnClick({R.id.img_cancle, R.id.ll_show_save_money, R.id.btn_save_money, R.id.btn_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_cancle: // 关闭
                dismiss();
                break;
            case R.id.ll_show_save_money:
                if (isShowSaveMoney) {
                    llShowSaveMoney.setVisibility(View.GONE);
                    isShowSaveMoney = false;
                } else {
                    llShowSaveMoney.setVisibility(View.VISIBLE);
                    isShowSaveMoney = true;
                }
                break;
            case R.id.btn_save_money: // 走基金申购流程
                break;
            case R.id.btn_complete: // 保存图片到服务端
                break;
        }
    }
}
