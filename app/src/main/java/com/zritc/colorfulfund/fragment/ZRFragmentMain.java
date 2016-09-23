package com.zritc.colorfulfund.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.cardmanager.ZRActivityCardManage;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.activity.scene.ZRActivityEduScene;
import com.zritc.colorfulfund.activity.scene.ZRActivityTargetSetting;
import com.zritc.colorfulfund.activity.wish.ZRActivityWishGuide;
import com.zritc.colorfulfund.activity.wish.ZRActivityWishHomePage;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.ZRUserInfo;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.iView.IFundProListView;
import com.zritc.colorfulfund.presenter.FundProListPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主页
 *
 * @author Midas
 * @version 1.0
 * @createDate 2015-11-11
 * @lastUpdate 2015-11-11
 */
public class ZRFragmentMain extends ZRFragmentBase implements IFundProListView {

    @Bind(R.id.view_content_container)
    ViewGroup viewContent;

    private FundProListPresenter fundProListPresenter;

    private String poCode = "ZH000484";
    private boolean isFirstBuy;
    private boolean isWishFirstBuy;

    @Override
    protected void getExtraArguments() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initPresenter() {
        fundProListPresenter = new FundProListPresenter(getActivity(), this);
        fundProListPresenter.init();
        fundProListPresenter.fundPoList4C();

        ZRUserInfo.getInstance().getEduUserPoAssetInfo(new ZRUserInfo.UserInfoCallBack() {
            @Override
            public void onUserInfo(Object object) {
                // 资产配置中没有配置信息，说明是第一次购买，进入目标设定界面，否则进入资产配置信息界面
                isFirstBuy = ZRUserInfo.getInstance().isEduFirstBuy();
            }

            @Override
            public void onUserInfoError(String message) {

            }
        });

        ZRUserInfo.getInstance().getWishUserPoAssetInfo(new ZRUserInfo.UserInfoCallBack() {
            @Override
            public void onUserInfo(Object object) {
                isWishFirstBuy = ZRUserInfo.getInstance().isWishFirstBuy();
            }

            @Override
            public void onUserInfoError(String message) {

            }
        });
    }

    @OnClick({R.id.btn_card, R.id.btn_single_redemption, R.id.btn_user_po_list4C, R.id.btn_education, R.id.btn_wish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_card:
                Intent intent1 = new Intent();
                intent1 = new Intent(getActivity(),
                        ZRActivityCardManage.class);
                startActivity(intent1);
                break;
            case R.id.btn_single_redemption: // 单个赎回
                startActivity(new Intent(mContext, ZRActivitySingleRedemption.class));
                break;
            case R.id.btn_user_po_list4C: // // 组合赎回
                Intent intent = new Intent(mContext, ZRActivityFundList.class);
                startActivity(intent);
                break;
            case R.id.btn_education: // 教育场景
                if (isFirstBuy) {
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), ZRActivityTargetSetting.class);
                    intent2.putExtra("poCode", "ZH000484");
                    startActivity(intent2);
                } else {
                    Intent intent3 = new Intent();
                    intent3.setClass(getActivity(), ZRActivityEduScene.class);
                    intent3.putExtra("poCode", "ZH000484");
                    startActivity(intent3);
                }
                break;
            case R.id.btn_wish: // 心愿场景
                if (isWishFirstBuy) {
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), ZRActivityWishGuide.class);
                    intent2.putExtra("poCode", "ZH000484");
                    startActivity(intent2);
                } else {
                    Intent intent3 = new Intent();
                    intent3.setClass(getActivity(), ZRActivityWishHomePage.class);
                    intent3.putExtra("poCode", "ZH000484");
                    startActivity(intent3);
                }
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GetFundPoList4C) {
            TextView textView = new TextView(getActivity());
            textView.setText("基金组合列表：");
            textView.setTextColor(Color.parseColor("#000000"));
            viewContent.addView(textView);

            for (GetFundPoList4C.FundPoList x : ((GetFundPoList4C) object).commonFundPoList.fundPoList) {
                Button btn = new Button(getActivity());
                btn.setTextColor(Color.parseColor("#000000"));
                btn.setText(x.poBase.poName);
                btn.setId(btn.hashCode());
                btn.setTag(x);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GetFundPoList4C.FundPoList pro = (GetFundPoList4C.FundPoList) v.getTag();
                        Intent intent = new Intent(getActivity(),
                                ZRActivityMultiFundApplyPurchase.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                viewContent.addView(btn);
            }
        }
    }

    @Override
    public void onError(String msg) {
        hideProgress();
        showToast(msg);
    }
}
