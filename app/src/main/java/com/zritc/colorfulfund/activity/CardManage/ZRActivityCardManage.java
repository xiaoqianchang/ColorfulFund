package com.zritc.colorfulfund.activity.CardManage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.Fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.GetUserBankCards4C;
import com.zritc.colorfulfund.data.response.trade.UnbindPayment;
import com.zritc.colorfulfund.iView.ICardManageView;
import com.zritc.colorfulfund.presenter.CardManagePresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.ZRTextView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;

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
    ZRListView listView;

    private PopupWindows mPopupWindows;

    private ZRCommonAdapter<GetUserBankCards4C.UserBankCardList> adapter;
    private List<GetUserBankCards4C.UserBankCardList> datas = new ArrayList<>();
    private CardManagePresenter cardManagePresenter;

    private boolean fromBuyPro;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    if (null != adapter && null != swipeRefreshLayout) {
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    }
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
        cardManagePresenter.userBankCards4C();
    }

    private void getExtraData() {
        String fromWhitchActivity = getIntent().getStringExtra(ZRConstant.KEY_FROM_WHICH_ACTIVITY);
        if (null != fromWhitchActivity && fromWhitchActivity.equals(ZRActivityMultiFundApplyPurchase.class.getName())) {
            fromBuyPro = true;
        } else {
            fromBuyPro = false;
        }
    }

    @Override
    public void initView() {

        setTitleText("我的银行卡");

        {
            getExtraData();
            if (!fromBuyPro) {
        View footView = LayoutInflater.from(this).inflate(R.layout.cell_add_bankcard_item, null, false);
        listView.addFooterView(footView);

                RxView.clicks(footView).throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(aVoid -> {
                            jump2BindBankCard();
                        });
            }
        }

        listView.setAdapter(adapter = new ZRCommonAdapter<GetUserBankCards4C.UserBankCardList>(this, datas, R.layout.cell_card_manage_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, GetUserBankCards4C.UserBankCardList item) {
                helper.getView(R.id.id_bg_bank_card).setBackgroundResource(getBankBackground(item.bankName));
                helper.setImageByUrl(R.id.id_img_card, item.bankLogo);
                helper.setText(R.id.id_txt_bank_name, item.bankName);
                helper.setText(R.id.id_txt_card_number, ZRUtils.getFormatCardNum(item.bankCardNo));
                helper.setText(R.id.id_txt_single_quota, "单笔限额：" + item.maxPayAmountPerTxn + "万");
                helper.setText(R.id.id_txt_day_quota, "日累计限额：" + item.maxPayAmountPerDay + "万");
            }
        });
        listView.setDivider(null);
        listView.setDividerHeight(this.getResources().getDimensionPixelSize(R.dimen.padding_30));
        if (!fromBuyPro) {
        listView.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
                unbindIndex = position;
                mPopupWindows = new PopupWindows(mContext,
                        view);
                return false;
            });
        }
        if (fromBuyPro) {
            listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
                Intent intent = new Intent();
                intent.putExtra("bankinfo", datas.get(position));
                setResult(RESULT_OK, intent);
                finish();
            });
        }
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (listView != null && listView.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        cardManagePresenter.userBankCards4C();
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);
    }

    private class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.view_unbind_bankcard, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_in));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.bottom_in));

            setWidth(android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            view.setOnClickListener((View v) ->
                    onPopupDismiss()
            );
            view.setOnKeyListener((View arg0, int arg1, KeyEvent arg2) -> {
                if (arg1 == KeyEvent.KEYCODE_BACK) {
                    onPopupDismiss();
                }
                return false;
                });

            ZRTextView unbind = (ZRTextView) view
                    .findViewById(R.id.item_popupwindows_unbind);
            ZRTextView cancel = (ZRTextView) view
                    .findViewById(R.id.item_popupwindows_cancel);
            unbind.setOnClickListener(popItemClickListener);
            cancel.setOnClickListener(popItemClickListener);
        }
    }

    private int unbindIndex = 0;

    private View.OnClickListener popItemClickListener = new View.OnClickListener() {

    @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_popupwindows_unbind:
                    cardManagePresenter.unbindPayment(datas.get(unbindIndex).bankCardNo);
                    onPopupDismiss();
                    break;
                case R.id.item_popupwindows_cancel:
                    onPopupDismiss();
                    break;
            }
        }
    };

    /**
     * 关闭POP
     */
    private void onPopupDismiss() {
        if (null != mPopupWindows) {
            mPopupWindows.dismiss();
        }
    }

    private void jump2BindBankCard() {
        Intent intent = new Intent();
        intent.setClass(this, ZRActivityAddBankCard.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_BANKCARD);
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
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GetUserBankCards4C){
            datas.clear();
            datas.addAll(((GetUserBankCards4C) object).userBankCardList);
            adapter.notifyDataSetChanged();
        } else if (object instanceof UnbindPayment){
            datas.remove(unbindIndex);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String message) {
        showToast(message);
    }

    private int getBankBackground(String bankName) {
        String[] redArray = this.getResources().getStringArray(R.array.bank_with_red_bg);
        String[] blueArray = this.getResources().getStringArray(R.array.bank_with_blue_bg);
        String[] greenArray = this.getResources().getStringArray(R.array.bank_with_green_bg);
        int resId = 0;
        for (String s : redArray) {
            if (s.contains(bankName)) {
                resId = R.mipmap.bg_red;
                break;
            }
        }
        for (String s : blueArray) {
            if (s.contains(bankName)) {
                resId = R.mipmap.bg_blue;
                break;
            }
        }
        for (String s : greenArray) {
            if (s.contains(bankName)) {
                resId = R.mipmap.bg_green;
                break;
            }
        }
        return resId;
    }
}
