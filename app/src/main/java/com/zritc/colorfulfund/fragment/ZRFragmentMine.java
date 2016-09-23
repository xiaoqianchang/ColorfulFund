package com.zritc.colorfulfund.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.cardmanager.ZRActivityCardManage;
import com.zritc.colorfulfund.activity.mine.ZRActivityCollection;
import com.zritc.colorfulfund.activity.mine.ZRActivityHelp;
import com.zritc.colorfulfund.activity.mine.ZRActivityMyProperty;
import com.zritc.colorfulfund.activity.mine.ZRActivityRiskEvaluation;
import com.zritc.colorfulfund.activity.mine.ZRActivityRiskEvaluationGuide;
import com.zritc.colorfulfund.activity.mine.ZRActivitySetting;
import com.zritc.colorfulfund.activity.mine.ZRActivityTradeBill;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.model.mine.PersonalInfo;
import com.zritc.colorfulfund.iView.IMineView;
import com.zritc.colorfulfund.presenter.MinePresenter;
import com.zritc.colorfulfund.widget.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的
 * <p>
 * Created by Chang.Xiao on 2016/9/19.
 *
 * @version 1.0
 */
public class ZRFragmentMine extends ZRFragmentBase<MinePresenter> implements IMineView {

    private static final int REQUEST_CODE_FRAGMENT_MINE = 0x111;

    @Bind(R.id.img_head_icon)
    CircleImageView imgHeadIcon; // 头像

    @Bind(R.id.tv_nickname)
    TextView tvNickname; // 昵称

    @Bind(R.id.tv_my_property)
    TextView tvMyProperty; // 我的资产

    @Bind(R.id.tv_bank_cards_num)
    TextView tvBankCardsNum; // 银行卡数

    @Bind(R.id.tv_risk_evaluation_type)
    TextView tvRiskEvaluationType; // 风险评估

    private MinePresenter presenter;
    private PersonalInfo personalInfo; // 个人信息

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initPresenter() {
        presenter = new MinePresenter(getActivity(), this);
        presenter.init();
        // 获取数据
        presenter.doGetUserMainPageInfo();
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.ll_headicon_container, R.id.rl_my_property, R.id.rl_trade_bill, R.id.rl_binding_bank_cards, R.id.rl_risk_evaluation
            , R.id.rl_collection, R.id.rl_help})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ll_headicon_container: // 进入设置
                intent = new Intent(getActivity(), ZRActivitySetting.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("personalInfo", personalInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.rl_my_property: // 我的资产
                intent = new Intent(getActivity(), ZRActivityMyProperty.class);
                Bundle bundleMyProperty = new Bundle();
                bundleMyProperty.putSerializable("myProperty", personalInfo.myProperty);
                intent.putExtras(bundleMyProperty);
                startActivity(intent);
                break;
            case R.id.rl_trade_bill: // 交易账单
                startActivity(new Intent(getActivity(), ZRActivityTradeBill.class));
                break;
            case R.id.rl_binding_bank_cards: // 绑定银行卡
//                startActivity(new Intent(getActivity(), ZRActivityBindingBankCards.class));
                startActivity(new Intent(getActivity(), ZRActivityCardManage.class));
                break;
            case R.id.rl_risk_evaluation: // 风险评估
                if ("未评估".equals(tvRiskEvaluationType.getText().toString())) {
                    intent = new Intent(getActivity(), ZRActivityRiskEvaluationGuide.class);
                    startActivityForResult(intent, REQUEST_CODE_FRAGMENT_MINE);
                } else {
                    intent = new Intent(getActivity(), ZRActivityRiskEvaluation.class);
                    Bundle bundleRiskEvaluation = new Bundle();
                    bundleRiskEvaluation.putSerializable("personalInfo", personalInfo);
                    intent.putExtras(bundleRiskEvaluation);
                    startActivityForResult(intent, REQUEST_CODE_FRAGMENT_MINE);
                }
                break;
            case R.id.rl_collection: // 收藏
                startActivity(new Intent(getActivity(), ZRActivityCollection.class));
                break;
            case R.id.rl_help: // 帮助
                startActivity(new Intent(getActivity(), ZRActivityHelp.class));
                break;
        }
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof PersonalInfo) { // 我的界面
            personalInfo = (PersonalInfo) object;
            refreshView(personalInfo);
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == mActivity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FRAGMENT_MINE:
                    // 创建成功，刷新
                    if (null != data) {
                        personalInfo.riskEvaluationType = data.getStringExtra("riskEvaluationType");
                        refreshView(personalInfo);
                    }
                    break;
            }
        }
    }

    /**
     * 刷新界面
     */
    private void refreshView(PersonalInfo personalInfo) {
        if (!TextUtils.isEmpty(personalInfo.avatar)) {
            Picasso.with(mContext).load(personalInfo.avatar).placeholder(R.mipmap.icon_header).into(imgHeadIcon);
        } else {
            Picasso.with(mContext).load(R.mipmap.icon_header).into(imgHeadIcon);
        }
        tvNickname.setText(personalInfo.nickName);
        tvMyProperty.setText(String.format("￥%s", personalInfo.myPropertyAmount));
        if (TextUtils.isEmpty(personalInfo.bankCardNum) || "0".equals(personalInfo.bankCardNum)) {
            tvBankCardsNum.setText("未绑定");
            tvBankCardsNum.setTextColor(Color.RED);
        } else {
            tvBankCardsNum.setText(personalInfo.bankCardNum);
            tvBankCardsNum.setTextColor(Color.BLACK);
        }
        if (!TextUtils.isEmpty(personalInfo.riskEvaluationType) && !"未评估".equals(personalInfo.riskEvaluationType)) {
            tvRiskEvaluationType.setText(personalInfo.riskEvaluationType);
            tvRiskEvaluationType.setTextColor(getResources().getColor(R.color.ltgray));
        } else {
            tvRiskEvaluationType.setText("未评估");
            tvRiskEvaluationType.setTextColor(Color.RED);
        }
    }
}
