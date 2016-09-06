package com.zritc.colorfulfund.activity.wish;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IWishHomePageView;
import com.zritc.colorfulfund.presenter.WishHomePagePresenter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemTypeSupport;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 心愿首页
 *
 * Created by Chang.Xiao on 2016/9/6.
 *
 * @version 1.0
 */
public class ZRWishHomePage extends ZRActivityBase<WishHomePagePresenter> implements IWishHomePageView {

    @Bind(R.id.wish_swipe_layout)
    SwipeRefreshLayout wishSwipeLayout;

    @Bind(R.id.wish_list_view)
    ListView wishListView;

    @Bind(R.id.ll_create_wish)
    LinearLayout createWish; // 创建心愿

    @Bind(R.id.ll_look_position)
    LinearLayout lookPosition; // 查看仓位

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
        datas = new ArrayList<>();
        initData();
        // 刷新组件
        wishSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        wishSwipeLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                wishSwipeLayout.setRefreshing(false);
            }, 2000);
        });
        adapter = new WishAdapter(this, datas, new MultiItemTypeSupport() {
            @Override
            public int getLayoutId(int position, Object o) {
                if (position != 0 && position % 2 != 0) { // 奇数
                    return R.layout.lv_wish_center_item;
                } else if (position == 0 || (position / 2) % 2 == 0) { // 如0 4 8
                    return R.layout.lv_wish_right_item;
                } else if ((position / 2) % 2 != 0) { // 如2 6 10
                    return R.layout.lv_wish_left_item;
                }
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 3;
            }

            @Override
            public int getItemViewType(int position, Object o) {
                if (position != 0 && position % 2 != 0) { // 奇数
                    return 1;
                } else if (position == 0 || (position / 2) % 2 == 0) { // 如0 4 8
                    return 0;
                } else if ((position / 2) % 2 != 0) { // 如2 6 10
                    return 2;
                }
                return 0;
            }
        });
        wishListView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            datas.add(new Wish("IWC表" + i, "10万", "未开始"));
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
