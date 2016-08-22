package com.zritc.colorfulfund.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.CardManage.ZRActivityCardManage;
import com.zritc.colorfulfund.activity.Fund.ZRActivityFundList;
import com.zritc.colorfulfund.activity.Fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.Fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.Fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.data.response.trade.GetFundPoList4C;
import com.zritc.colorfulfund.iView.IFundProListView;
import com.zritc.colorfulfund.presenter.FundProListPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        fundProListPresenter = new FundProListPresenter(getActivity(), this);
        fundProListPresenter.fundPoList4C();
        return view;
    }

    @OnClick({R.id.btn_card, R.id.btn_buy_pro, R.id.btn_single_redemption, R.id.btn_user_po_list4C})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_card:
                Intent intent1 = new Intent();
                intent1 = new Intent(getActivity(),
                        ZRActivityCardManage.class);
                startActivity(intent1);
                break;
            case R.id.btn_buy_pro:
                Intent intent2 = new Intent(getActivity(),
                        ZRActivityMultiFundApplyPurchase.class);
                Bundle bundle = new Bundle();
                bundle.putString("poCode", "ZH000484");
                bundle.putString("amount", "12400.00");
                intent2.putExtras(bundle);
                startActivity(intent2);
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
    protected void lazyLoad() {
    }

    @Override
    public String getFragmentName() {
        return null;
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

            for (GetFundPoList4C.FundPoList x : ((GetFundPoList4C) object).fundPoList) {
                Button btn = new Button(getActivity());
                btn.setTextColor(Color.parseColor("#000000"));
                btn.setText(x.poName);
                btn.setId(btn.hashCode());
                btn.setTag(x);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GetFundPoList4C.FundPoList pro = (GetFundPoList4C.FundPoList) v.getTag();
                        Intent intent = new Intent(getActivity(),
                                ZRActivityMultiFundApplyPurchase.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("UserPaymentInfo", ((GetFundPoList4C) object).userPaymentInfo.get(0));
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

    @Override
    public void initView() {

    }
}
