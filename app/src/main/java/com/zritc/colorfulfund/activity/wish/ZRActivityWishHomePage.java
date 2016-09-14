package com.zritc.colorfulfund.activity.wish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.fund.ZRActivityMultiFundApplyPurchase;
import com.zritc.colorfulfund.activity.fund.ZRActivityMyFundGroupDetail;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.wish.Wish;
import com.zritc.colorfulfund.data.model.wish.WishPoBase;
import com.zritc.colorfulfund.data.response.wish.DeleteUserWishList4C;
import com.zritc.colorfulfund.data.response.wish.GetUserWishLists4C;
import com.zritc.colorfulfund.iView.IWishHomePageView;
import com.zritc.colorfulfund.presenter.WishHomePagePresenter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemTypeSupport;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.widget.RoundProgressBar;
import com.zritc.colorfulfund.widget.SateliteMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 心愿首页
 *
 * Created by Chang.Xiao on 2016/9/6.
 *
 * @version 1.0
 */
public class ZRActivityWishHomePage extends ZRActivityBase<WishHomePagePresenter> implements IWishHomePageView {

    private static final int REQUEST_CODE_WISH_HOME_PAGE = 0x311;

    private static final String NOT_START = "1"; // 未开始
    private static final String PROGRESSING = "2"; // 进行中
    private static final String completed = "3"; // 已完成

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.img_title_icon)
    ImageView imgTitleIcon;

    @Bind(R.id.imgBtn_add_wish)
    ImageButton imgBtnAddWish;

    @Bind(R.id.wish_swipe_layout)
    SwipeRefreshLayout wishSwipeLayout;

    @Bind(R.id.wish_list_view)
    ListView wishListView;

    @Bind(R.id.ll_add_investment)
    LinearLayout llAddInvestment; // 加仓定投

    @Bind(R.id.tv_investment_money)
    TextView tvInvestmentMoney; // 加仓定投金额

    @Bind(R.id.ll_look_position)
    LinearLayout lookPosition; // 查看仓位

    @Bind(R.id.ll_wish_tip)
    LinearLayout llWishTip; // 没有心愿时的模板

    private WishHomePagePresenter presenter;
    private List<Wish> datas;
    private WishAdapter adapter;
    public WishPoBase wishPoBase;
    private String investmentMoney; // 加仓定投金额

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wish_home_page;
    }

    @Override
    protected void initPresenter() {
        presenter = new WishHomePagePresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        initTitle();
        datas = new ArrayList<>();
        // 刷新组件
        wishSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        wishSwipeLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                presenter.doGetUserWishLists();
                setView();
                wishSwipeLayout.setRefreshing(false);
            }, 2000);
        });
        adapter = new WishAdapter(this, datas, new MultiItemTypeSupport() {
            @Override
            public int getLayoutId(int position, Object o) {
                Wish wish = datas.get(position);
                if (wish.wishStatus.equals(PROGRESSING)) {
                    return R.layout.lv_wish_start_item;
                } else if (wish.wishStatus.equals(NOT_START)) {
                    return R.layout.lv_wish_center_item;
                }else if (wish.wishStatus.equals(completed)) {
                    return R.layout.lv_wish_end_item;
                }
                return R.layout.lv_wish_center_item;
            }

            @Override
            public int getViewTypeCount() {
                return 3;
            }

            @Override
            public int getItemViewType(int position, Object o) {
                Wish wish = datas.get(position);
                if (wish.wishStatus.equals(PROGRESSING)) {
                    return 0;
                } else if (wish.wishStatus.equals(NOT_START)) {
                    return 1;
                }else if (wish.wishStatus.equals(completed)) {
                    return 2;
                }
                return 1;
            }
        });
        wishListView.setAdapter(adapter);
        wishListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            // 创建Popupwindow
        });

        presenter.doGetUserWishLists();
        setView();

    }

    /**
     * 设置view显示情况并刷新
     */
    private void setView() {
        if (null != datas && datas.size() > 0) {
            imgTitleIcon.setVisibility(View.VISIBLE);
            llAddInvestment.setVisibility(View.VISIBLE);
            lookPosition.setVisibility(View.VISIBLE);
            llWishTip.setVisibility(View.GONE);
            updateView();
        } else {
            imgTitleIcon.setVisibility(View.GONE);
            llAddInvestment.setVisibility(View.GONE);
            lookPosition.setVisibility(View.GONE);
            llWishTip.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 刷新UI
     */
    private void updateView() {
        investmentMoney = ZRUtils.getFormatDecimal(presenter.investmentMoney);
        tvInvestmentMoney.setText(String.format("%s元", investmentMoney));
    }

    private void initTitle() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        RxView.clicks(imgBtnAddWish).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivityForResult(new Intent(this, ZRActivityCreateWish.class), REQUEST_CODE_WISH_HOME_PAGE);
                });
    }

    @OnClick({R.id.ll_add_investment, R.id.ll_look_position})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_add_investment: // 加仓定投
                intent.setClass(this, ZRActivityMultiFundApplyPurchase.class);
                Bundle bundle = new Bundle();
//                GetFundPoList4C.FundPoList pro = new GetFundPoList4C().new FundPoList();
//                pro.poBase = new GetFundPoList4C().new PoBase();
//                bundle.putSerializable("GetFundPoList4C.FundPoList", pro);
                bundle.putString(ZRConstant.INTENT_FROM_WHERE, ZRActivityWishHomePage.class.getName());
                bundle.putString("poCode", wishPoBase.poCode);
                bundle.putString("money", investmentMoney);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.ll_look_position: // 查看仓位
                intent.setClass(this, ZRActivityMyFundGroupDetail.class);
                intent.putExtra("poCode", wishPoBase.poCode);
                startActivity(intent);
                break;
        }
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
        if (object instanceof GetUserWishLists4C) {
            // 获取心愿列表
            wishPoBase = presenter.wishPoBase;
            datas.clear();
            datas.addAll(presenter.wishList);
            setView();
            adapter.notifyDataSetChanged();
        }
        if (object instanceof DeleteUserWishList4C) {
            // 删除成功
            presenter.doGetUserWishLists();
            showToast("删除成功");
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_WISH_HOME_PAGE:
                    // 创建成功，刷新
                    presenter.doGetUserWishLists();
                    break;
            }
        }
    }

    class WishAdapter extends MultiItemCommonAdapter<Wish> {

        public WishAdapter(Context context, List<Wish> datas, MultiItemTypeSupport multiItemTypeSupport) {
            super(context, datas, multiItemTypeSupport);
        }

        @Override
        protected void convert(ViewHolder holder, Wish wish) {
            if (wish.wishStatus.equals(PROGRESSING)) {
                holder.setText(R.id.tv_progress, String.valueOf(wish.currentProgress));
                RoundProgressBar roundProgressBar = holder.getView(R.id.roundProgressBar);
                roundProgressBar.setProgress(wish.currentProgress);
            } else {
                holder.setText(R.id.tv_status, wish.wishStatus);
            }
            holder.setText(R.id.tv_name, wish.wishName);
            holder.setText(R.id.tv_target_money, String.format("%s元", ZRUtils.getFormatDecimal(wish.targetMoney)));
            // 处理最后的line marker的显示与隐藏
            TextView tvEndMarker = holder.getView(R.id.tv_end_marker);
            if (null != tvEndMarker) {
                tvEndMarker.setVisibility(View.GONE);
            }
            // 菜单处理
            SateliteMenu sateliteMenu = holder.getView(R.id.sateliteMenu);
            if (null != sateliteMenu) {
                sateliteMenu.setOnMenuItemClickListener(new SateliteMenu.onMenuItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // 可以使用标记来判断点击的Viwew，当然必须给ChildViewItem设置了Tag
                        if ("1".equals(view.getTag())) { // 删除
                            presenter.doDeleteUserWish(Long.valueOf(wish.wishId));
                        } else if ("2".equals(view.getTag())) { // 赎回
                        }
                    }
                });
            }
        }
    }
}
