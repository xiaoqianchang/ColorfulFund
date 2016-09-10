package com.zritc.colorfulfund.activity.wish;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IWishSettingView;
import com.zritc.colorfulfund.presenter.WishSettingPresenter;
import com.zritc.colorfulfund.widget.DraggableCircleView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 心愿设置
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class ZRActivityWishSetting extends ZRActivityBase<WishSettingPresenter> implements IWishSettingView {

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.ll_wish_setting_title)
    LinearLayout llWishSettingTitle;

    @Bind(R.id.img_category_icon)
    ImageView imgCategoryIcon;

    @Bind(R.id.tv_category_name)
    TextView tvCategoryName;

    @Bind(R.id.edt_edit_wish)
    EditText edtEditWish;

    @Bind(R.id.target_money)
    DraggableCircleView mTargetMoney;

    @Bind(R.id.reach_date)
    DraggableCircleView mReachDate;

    private WishSettingPresenter presenter;
    private ZRActivityCreateWish.CreateWish wish;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wish_setting;
    }

    @Override
    protected void initPresenter() {
        presenter = new WishSettingPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        Intent intent = getIntent();
        if (null != intent) {
            wish = (ZRActivityCreateWish.CreateWish) intent.getSerializableExtra("wish");
        }
        if (null != wish) {
            llWishSettingTitle.setVisibility(View.VISIBLE);
            edtEditWish.setVisibility(View.GONE);
            imgCategoryIcon.setImageResource(wish.getBgResId());
            tvCategoryName.setText(wish.getName());
        } else {
            llWishSettingTitle.setVisibility(View.GONE);
            edtEditWish.setVisibility(View.VISIBLE);
        }
        mTargetMoney.newBuilder()
                .setSmallTickMarkNum(360)
                .setEachCircleTotal(new double[] {1000 * 360, 10000 * 360})
                .setCurrentValue(0)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleHintText("目标金额")
                .build();

        mReachDate.newBuilder()
                .setSmallTickMarkNum(360)
                .setEachCircleTotal(new double[] {1000 * 360, 10000 * 360})
                .setCurrentValue(0)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleHintText("达成日期")
                .build();
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

    @Override
    public void onError(String msg) {

    }
}
