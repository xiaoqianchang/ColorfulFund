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
import com.zritc.colorfulfund.activity.fund.ZRActivityFundGroupDetail;
import com.zritc.colorfulfund.activity.fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.activity.scene.ZRActivityEduScene;
import com.zritc.colorfulfund.activity.scene.ZRActivityTargetSetting;
import com.zritc.colorfulfund.base.ZRFragmentBase;
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

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initPresenter() {
        fundProListPresenter = new FundProListPresenter(getActivity(), this);
        fundProListPresenter.init();
        fundProListPresenter.fundPoList4C();
    }

    @OnClick({R.id.btn_card, R.id.btn_single_redemption, R.id.btn_user_po_list4C})
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
        }
    }

    @Override
    public void initView(){

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


            Button btn3 = new Button(getActivity());
            btn3.setTextColor(Color.parseColor("#000000"));
            btn3.setText("基金组合详情");
            btn3.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(),
                                ZRActivityFundGroupDetail.class);
                        //                Bundle bundle = new Bundle();
                        //                bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                        //                intent.putExtras(bundle);
                        startActivity(intent);
                    }
            );
            viewContent.addView(btn3);

            Button btn5 = new Button(getActivity());
            btn5.setTextColor(Color.parseColor("#000000"));
            btn5.setText("目标设定");
            btn5.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(),
                                ZRActivityTargetSetting.class);
                        //                Bundle bundle = new Bundle();
                        //                bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                        //                intent.putExtras(bundle);
                        startActivity(intent);
                    }
            );
            viewContent.addView(btn5);

            Button btn6 = new Button(getActivity());
            btn6.setTextColor(Color.parseColor("#000000"));
            btn6.setText("教育场景");
            btn6.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(),
                                ZRActivityEduScene.class);
                        //                Bundle bundle = new Bundle();
                        //                bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                        //                intent.putExtras(bundle);
                        startActivity(intent);
                    }
            );
            viewContent.addView(btn6);
        }
    }

    @Override
    public void onError(String msg) {
        hideProgress();
        showToast(msg);


//        double a = 3600;//每年定投金额
//        double x = 0.15;// 收益率
//        int n = 35;//定投期数(公式中为n次方)
//        double m = 0;//预期收益
//
//        m = a * (1 + x) * (-1 + Math.pow(1 + x, n)) / x;
//        Button btn = new Button(getActivity());
//        btn.setTextColor(Color.parseColor("#000000"));
//        btn.setText("每年定投金额：" + a + "元\n收益率：" + x + "\n定投期数(公式中为n次方)：" + n + "年\n预期收益：" + ZRUtils.getDecimalFormat(m) + "元");
//        viewContent.addView(btn);
//
//        m = 10000000;//预期收益1000万
//        a = m / ((1 + x) * (-1 + Math.pow((1 + x), n) / x));
//        Button btn1 = new Button(getActivity());
//        btn1.setTextColor(Color.parseColor("#000000"));
//        btn1.setText("无首付\n每年定投金额：" + ZRUtils.getDecimalFormat(a) + "元\n收益率：" + x + "\n定投期数(公式中为n次方)：" + n + "年\n预期收益：1000万");
//        viewContent.addView(btn1);
//
//        int s = 10000;//首付10000
//        m = 10000000;//预期收益1000万
//        a = (m - s * Math.pow((1 + x), n)) / ((1 + x) * (-1 + Math.pow((1 + x), n)) / x);
//        Button btn2 = new Button(getActivity());
//        btn2.setTextColor(Color.parseColor("#000000"));
//        btn2.setText("首付10000\n每年定投金额：" + ZRUtils.getDecimalFormat(a) + "元\n收益率：" + x + "\n定投期数(公式中为n次方)：" + n + "年\n预期收益：1000万");
//        viewContent.addView(btn2);

//        Button btn4 = new Button(getActivity());
//        btn4.setTextColor(Color.parseColor("#000000"));
//        btn4.setText("1.15的35次方：" + Math.pow((1 + .15), 35));
//        viewContent.addView(btn4);

    }
}
