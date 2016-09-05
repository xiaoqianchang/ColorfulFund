package com.zritc.colorfulfund.activity.scene;

import android.view.View;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.iView.ITargetSettingView;
import com.zritc.colorfulfund.presenter.TargetSettingPresenter;
import com.zritc.colorfulfund.widget.DraggableCircleView;

import butterknife.Bind;

/**
 * ZRActivityTargetSetting 目标设定
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-31
 * @lastUpdate 2016-08-31
 */
public class ZRActivityTargetSetting extends ZRActivityToolBar<TargetSettingPresenter> implements ITargetSettingView {

    @Bind(R.id.circle_target_money)
    DraggableCircleView circleTargetMoney;

    @Bind(R.id.circle_target_date)
    DraggableCircleView circleTargetDate;


    private TargetSettingPresenter targetSettingPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_target_setting;
    }

    @Override
    protected void initPresenter() {
        targetSettingPresenter = new TargetSettingPresenter(this, this);
        targetSettingPresenter.init();
    }

    @Override
    public void initView() {
        showToolBar(View.GONE);

        DraggableCircleView.Builder builder1 =  circleTargetMoney.newBuilder();
        builder1.setMiddleValueSize(25);
        builder1.setMiddleTextSize(15);
        builder1.build();

        DraggableCircleView.Builder builder2 =  circleTargetMoney.newBuilder();
        builder2.setMiddleValueSize(25);
        builder2.setMiddleTextSize(15);
        builder2.build();

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
