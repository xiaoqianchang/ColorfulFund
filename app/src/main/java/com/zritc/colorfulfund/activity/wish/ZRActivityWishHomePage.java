package com.zritc.colorfulfund.activity.wish;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IWishHomePageView;
import com.zritc.colorfulfund.presenter.WishHomePagePresenter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemTypeSupport;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;

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

    @Bind(R.id.ll_look_position)
    LinearLayout lookPosition; // 查看仓位

    @Bind(R.id.ll_wish_tip)
    LinearLayout llWishTip; // 没有心愿时的模板

    private WishHomePagePresenter presenter;
    private List<Wish> datas;
    private WishAdapter adapter;

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
        initData();
        // 刷新组件
        wishSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        wishSwipeLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                setView();
                wishSwipeLayout.setRefreshing(false);
            }, 2000);
        });
        adapter = new WishAdapter(this, datas, new MultiItemTypeSupport() {
            @Override
            public int getLayoutId(int position, Object o) {
                if (position == 0) {
                    return R.layout.lv_wish_start_item;
                } else if (position == datas.size() - 1) {
                    return R.layout.lv_wish_end_item;
                } else {
                    return R.layout.lv_wish_center_item;
                }
            }

            @Override
            public int getViewTypeCount() {
                return 3;
            }

            @Override
            public int getItemViewType(int position, Object o) {
                if (position == 0) {
                    return 0;
                } else if (position == datas.size() - 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        wishListView.setAdapter(adapter);
        wishListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            // 创建Popupwindow
        });

        setView();

    }

    /**
     * 设置view显示情况
     */
    private void setView() {
        if (null != datas && datas.size() > 0) {
            imgTitleIcon.setVisibility(View.VISIBLE);
            llAddInvestment.setVisibility(View.VISIBLE);
            lookPosition.setVisibility(View.VISIBLE);
            llWishTip.setVisibility(View.GONE);
        } else {
            imgTitleIcon.setVisibility(View.GONE);
            llAddInvestment.setVisibility(View.GONE);
            lookPosition.setVisibility(View.GONE);
            llWishTip.setVisibility(View.VISIBLE);
        }
    }

    private void initTitle() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        RxView.clicks(imgBtnAddWish).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    startActivity(new Intent(this, ZRActivityCreateWish.class));
                });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            datas.add(new Wish("IWC表" + i, "10万", "未开始"));
        }
    }

    @OnClick({R.id.ll_add_investment, R.id.ll_look_position})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_investment: // 加仓定投
                break;
            case R.id.ll_look_position: // 查看仓位
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

    }

    @Override
    public void onError(String msg) {

    }

    class WishAdapter extends MultiItemCommonAdapter<Wish> {

        public WishAdapter(Context context, List<Wish> datas, MultiItemTypeSupport multiItemTypeSupport) {
            super(context, datas, multiItemTypeSupport);
        }

        @Override
        protected void convert(ViewHolder holder, Wish wish) {

        }
    }

    class Wish {
        private String name;
        private String money;
        private String status;

        public Wish(String name, String money, String status) {
            this.name = name;
            this.money = money;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
