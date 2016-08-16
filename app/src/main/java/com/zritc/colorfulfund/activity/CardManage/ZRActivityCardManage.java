package com.zritc.colorfulfund.activity.CardManage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.ZRCardInfo;
import com.zritc.colorfulfund.iView.ICardManageView;
import com.zritc.colorfulfund.presenter.CardManagePresenter;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

public class ZRActivityCardManage extends ZRActivityToolBar<CardManagePresenter> implements ICardManageView, SwipeRefreshLayout.OnRefreshListener {

    private static final int REFRESH_COMPLETE = 0x110;
    private static final int REQUEST_CODE_ADD_BANKCARD = 0x111;

    @Bind(R.id.id_swipe_ly)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.id_listview)
    ListView listView;
    @Bind(R.id.id_add_bankcard_item)
    View addBankCardItem;

    private ZRCommonAdapter<ZRCardInfo> adapter;
    private List<ZRCardInfo> datas = new ArrayList<>();
    private CardManagePresenter cardManagePresenter;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_card_manage;
    }

    @Override
    protected void initPresenter() {
        cardManagePresenter = new CardManagePresenter(this, this);
        cardManagePresenter.init();
        addBankCardNotify();
    }

    private void addBankCardNotify() {
        AlertDialog.Builder
                builder = new AlertDialog.Builder(
                this);
        builder.setTitle("提示");
        builder.setMessage("您还没绑定银行卡，赶紧去绑一张吧？");
        builder.setCancelable(true);
        builder.setNegativeButton("取消", (DialogInterface dialog, int which) -> {
            dialog.cancel();
        });
        builder.setPositiveButton("去绑卡", (DialogInterface dialog, int which) -> {
            jump2BindBankCard();
        });
        builder.create().show();
    }

    @Override
    public void initView() {

        setTitleText("银行卡管理");

        listView.setAdapter(adapter = new ZRCommonAdapter<ZRCardInfo>(this, datas, R.layout.cell_card_manage_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, ZRCardInfo item) {
                helper.setImageByUrl(R.id.id_img_card, item.getCardImage());
                helper.setText(R.id.id_txt_bank_card, item.getCardName());
                helper.setText(R.id.id_txt_single_quota, item.getSingleQuota());
                helper.setText(R.id.id_txt_day_quota, item.getDayQuota());
            }
        });

        listView.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            AlertDialog.Builder
                    builder = new AlertDialog.Builder(
                    this);
            builder.setTitle("提示");
            builder.setMessage(String.format("解绑“%s”？", datas.get(position).getCardName()));
            builder.setCancelable(true);
            builder.setNegativeButton("取消", (DialogInterface dialog, int which) -> {
                dialog.cancel();
            });
            builder.setPositiveButton("确定", (DialogInterface dialog, int which) -> {
                datas.remove(position);
                adapter.notifyDataSetChanged();
                dialog.cancel();
            });
            builder.create().show();
            return false;
        });

        RxView.clicks(addBankCardItem).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    jump2BindBankCard();
                });

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() ->
                swipeRefreshLayout.setRefreshing(true)
        );
        // 假数据
        initData();
        onRefresh();
        addBankCardItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        ZRLog.e(Thread.currentThread().getName());
        // UI Thread
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);
    }

    private void jump2BindBankCard() {
        Intent intent = new Intent();
        intent.setClass(this, ZRActivityAddBankCard.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_BANKCARD);
    }

    private void initData() {
        datas.clear();
        ZRCardInfo cardInfo = new ZRCardInfo();
        cardInfo.setCardId(1);
        cardInfo.setCardImage("http://www.zzbxlc.com/Uploads/2014/10/11/543933ffd5bb1.jpg");
        cardInfo.setCardName("招商银行（尾号8228）");
        cardInfo.setSingleQuota("单笔限额30万");
        cardInfo.setDayQuota("日累计限额300万");
        datas.add(cardInfo);

        ZRCardInfo cardInfo1 = new ZRCardInfo();
        cardInfo1.setCardId(2);
        cardInfo1.setCardImage("http://minsheng.qingdaonews.com/images/attachement/png/site1/20160114/089e01cc7a3d18018ec50a.png");
        cardInfo1.setCardName("工商银行（尾号8126）");
        cardInfo1.setSingleQuota("单笔限额50万");
        cardInfo1.setDayQuota("日累计限额600万");
        datas.add(cardInfo1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_BANKCARD:
                    swipeRefreshLayout.post(() ->
                            swipeRefreshLayout.setRefreshing(true)
                    );
                    onRefresh();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }
}
