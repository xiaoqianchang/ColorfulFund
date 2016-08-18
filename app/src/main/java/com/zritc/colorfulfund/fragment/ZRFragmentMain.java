package com.zritc.colorfulfund.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.CardManage.ZRActivityCardManage;
import com.zritc.colorfulfund.activity.Fund.ZRActivityGroupRedemption;
import com.zritc.colorfulfund.activity.Fund.ZRActivitySingleRedemption;
import com.zritc.colorfulfund.base.ZRFragmentBase;

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
public class ZRFragmentMain extends ZRFragmentBase {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.btn_card, R.id.btn_single_redemption, R.id.btn_group_redemption})
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
            case R.id.btn_group_redemption: // // 组合赎回
                Intent intent = new Intent(mContext, ZRActivityGroupRedemption.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void lazyLoad() {
//        showLoadingView(mMainView);
    }

    @Override
    public String getFragmentName() {
        return null;
    }
}
