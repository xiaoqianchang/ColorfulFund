package com.zritc.colorfulfund.activity.wish;

import android.content.Intent;
import android.widget.Button;

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
public class ZRActivityWishSettingOne extends ZRActivityBase {

    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wish_setting_one;
    }

    @Override
    protected void initPresenter() {
        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    Intent intent = new Intent(this, ZRActivityWishSetting.class);
                    intent.putExtra("wish", getIntent().getSerializableExtra("wish"));
                    startActivity(intent);
                });
    }
}
