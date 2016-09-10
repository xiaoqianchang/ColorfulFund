package com.zritc.colorfulfund.activity.wish;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.ICreateWishView;
import com.zritc.colorfulfund.presenter.CreateWishPresenter;
import com.zritc.colorfulfund.ui.ZRGridView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 建立心愿
 * <p>
 * Created by Chang.Xiao on 2016/9/10.
 *
 * @version 1.0
 */
public class ZRActivityCreateWish extends ZRActivityBase<CreateWishPresenter> implements ICreateWishView {

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.gv_create_wish)
    ZRGridView gvCreateWish;

    @Bind(R.id.btn_next)
    Button btnNext;

    private CreateWishPresenter presenter;
    private List<CreateWish> datas = new ArrayList<>();
    private CreateWishAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_create_wish;
    }

    @Override
    protected void initPresenter() {
        presenter = new CreateWishPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        RxView.clicks(imgBtnBack).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
        initData();
        adapter = new CreateWishAdapter(this, datas, R.layout.gv_create_wish_item);
        gvCreateWish.setAdapter(adapter);

        gvCreateWish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ZRActivityWishSettingOne.class);
                if (position == datas.size() - 1) {
                    // 自定义
                } else {
                    intent.putExtra("wish", datas.get(position));
                }
                startActivity(intent);
            }
        });
    }

    private void initData() {
        datas.add(new CreateWish(R.mipmap.packet, "包包"));
        datas.add(new CreateWish(R.mipmap.car, "汽车"));
        datas.add(new CreateWish(R.mipmap.watch, "手表"));
        datas.add(new CreateWish(R.mipmap.phone, "手机"));
        datas.add(new CreateWish(R.mipmap.travel, "旅游"));
        datas.add(new CreateWish(R.mipmap.custom, "自定义"));
    }

    @OnClick({R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                // 进入心愿设置
                startActivity(new Intent(this, ZRActivityWishSettingOne.class));
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

    class CreateWishAdapter extends ZRCommonAdapter<CreateWish> {

        public CreateWishAdapter(Context context, List<CreateWish> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, CreateWish item) {
            helper.setImageResource(R.id.img_category_bg, item.getBgResId());
            helper.setText(R.id.tv_category_name, item.getName());
        }
    }

    static class CreateWish implements Serializable {
        private int bgResId;
        private String name;

        public CreateWish(int bgResId, String name) {
            this.bgResId = bgResId;
            this.name = name;
        }

        public int getBgResId() {
            return bgResId;
        }

        public void setBgResId(int bgResId) {
            this.bgResId = bgResId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
