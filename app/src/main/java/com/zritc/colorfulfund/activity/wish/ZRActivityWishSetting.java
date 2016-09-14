package com.zritc.colorfulfund.activity.wish;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundGroupDetail;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.model.wish.WishCategory;
import com.zritc.colorfulfund.data.response.edu.CreateUserInvestmentPlan4Edu;
import com.zritc.colorfulfund.data.response.wish.CreateUserWishList4C;
import com.zritc.colorfulfund.iView.IWishSettingView;
import com.zritc.colorfulfund.presenter.CreateWishPresenter;
import com.zritc.colorfulfund.presenter.WishSettingPresenter;
import com.zritc.colorfulfund.utils.ZRUtils;
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

    @Bind(R.id.text_each_money)
    TextView textEachMoney;

    @Bind(R.id.btn_next)
    Button btnNext;

    private WishSettingPresenter presenter;
    private WishCategory wish;
    private String poCode = "487";
    private int targetDate = 1; // 定投期数年(公式中为n次方)
    private double targetAmount = 10000; // 预期收益元
    private double initialAmount = 0;//首付款元
    private double eachMoney = 0;//每年定投金额元
    private double x = 0;// 收益率
    private int typeId; // 类型ID

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wish_setting;
    }

    @Override
    protected void initPresenter() {
        presenter = new WishSettingPresenter(this, this);
        presenter.init();
        // 获取年化率
        presenter.getUserPoAssetInfo4C(poCode);
    }

    @Override
    public void initView() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        RxView.clicks(btnNext).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    presenter.doCreateUserWish(typeId, edtEditWish.getText().toString(), String.valueOf(targetAmount), /**String.valueOf(targetDate)*/
                            /*mReachDate.getMiddleValue().split(".")[0] + mReachDate.getMiddleValue().split(".")[1] + "." + ZRUtils.getCurrentDay()*/"20171122");
                });
        Intent intent = getIntent();
        if (null != intent) {
            wish = (WishCategory) intent.getSerializableExtra("wish");
            if (null != wish) {
                typeId = Integer.parseInt(wish.wishTypeId);
                if ("自定义".equals(wish.name))
                    wish = null;
            }
        }
        if (null != wish) {
            llWishSettingTitle.setVisibility(View.VISIBLE);
//            edtEditWish.setVisibility(View.GONE);
            imgCategoryIcon.setImageResource(wish.imgUrl);
            tvCategoryName.setText(wish.name);
        } else {
            llWishSettingTitle.setVisibility(View.GONE);
            edtEditWish.setVisibility(View.VISIBLE);
        }
        mTargetMoney.newBuilder()
                .setSmallTickMarkNum(10)
                .setEachCircleTotal(new double[] {10000, 20000, 100000, 500000})
                .setCurrentValue(0)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setCircleColor(0xFFE4358C)
                .setCircleRingColor(0xFFE4358C)
                .setInnerCircleColor(0xFFAF2D75)
                .setGapBetweenCircleAndLine(getResources().getDimension(R.dimen.padding_2))
                .setLineLength(getResources().getDimension(R.dimen.padding_8))
                .setCircleRingStrokeWidth((int) getResources().getDimension(R.dimen.padding_20))
                .setMiddleText("目标金额")
                .setMiddleValue("0")
                .build();

        mReachDate.newBuilder()
                .setSmallTickMarkNum(12)
                .setEachCircleTotal(new double[] {12, 6 * 12, 12 * 12}) // 12个月、6年、12年
                .setCurrentValue(0)
                .setCircleButtonColor(Color.WHITE)
                .setMiddleValueSize(getResources().getDimensionPixelSize(R.dimen.font_size_16))
                .setMiddleTextSize(getResources().getDimensionPixelSize(R.dimen.font_size_18))
                .setCircleColor(0xFF52D7E0)
                .setCircleRingColor(0xFF52D7E0)
                .setInnerCircleColor(0xFF41B8BE)
                .setGapBetweenCircleAndLine(getResources().getDimension(R.dimen.padding_2))
                .setLineLength(getResources().getDimension(R.dimen.padding_8))
                .setCircleRingStrokeWidth((int) getResources().getDimension(R.dimen.padding_20))
                .setMiddleText("达成日期")
                .setMiddleValue(String.valueOf(ZRUtils.getCurrentYear()) + "." + (ZRUtils.getCurrentMonth() < 10 ? "0" + ZRUtils.getCurrentMonth() : ZRUtils.getCurrentMonth()))
                .build();

        mTargetMoney.setDraggableCircleListener(new DraggableCircleView.OnDraggableCircleListener() {
            @Override
            public void onDraggableCircleValueChange(double currentValue) {
            }

            @Override
            public void onDraggableCircleValueChanged(double currentValue) {
                targetAmount = currentValue;
                cal();
            }
        });

        mReachDate.setDraggableCircleListener(new DraggableCircleView.OnDraggableCircleListener() {
            @Override
            public void onDraggableCircleValueChange(double currentValue) {
            }

            @Override
            public void onDraggableCircleValueChanged(double currentValue) {
                targetDate = (int) currentValue;
                if (0 == targetDate)
                    targetDate = 1;
                cal();
            }
        });
    }

    private void cal() {
        if (initialAmount == 0) {
            eachMoney = targetAmount / ((1 + x) * (-1 + Math.pow((1 + x), targetDate) / x));
        } else {
            eachMoney = (targetAmount - initialAmount * Math.pow((1 + x), targetDate)) / ((1 + x) * (-1 + Math.pow((1 + x), targetDate)) / x);
        }
        textEachMoney.setText(String.format("建议每月定投%s元", ZRUtils.getDecimalFormat(eachMoney / 12)));
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
        if (object instanceof UserPoAssetInfo) {
            UserPoAssetInfo userPoAssetInfo = ((UserPoAssetInfo) object);
            if (TextUtils.isEmpty(userPoAssetInfo.expectedYearlyRoe))
                x = 0;
            else
                x = Double.valueOf(userPoAssetInfo.expectedYearlyRoe);
        } else if (object instanceof CreateUserWishList4C) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
