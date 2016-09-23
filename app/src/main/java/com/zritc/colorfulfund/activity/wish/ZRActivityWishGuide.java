package com.zritc.colorfulfund.activity.wish;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 心愿设置
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class ZRActivityWishGuide extends ZRActivityBase {

    private static final int REQUEST_CODE_WISHGUIDE = 0x511;

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wish_setting_one;
    }

    @Override
    protected void initPresenter() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(this, ZRActivityCreateWish.class);
                    startActivityForResult(intent, REQUEST_CODE_WISHGUIDE);
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_WISHGUIDE:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }
}
