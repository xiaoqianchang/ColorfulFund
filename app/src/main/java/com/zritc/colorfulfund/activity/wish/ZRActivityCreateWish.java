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
import com.zritc.colorfulfund.data.model.wish.WishCategory;
import com.zritc.colorfulfund.iView.ICreateWishView;
import com.zritc.colorfulfund.presenter.CreateWishPresenter;
import com.zritc.colorfulfund.ui.ZRGridView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;

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

    private static final int REQUEST_CODE_CREATEWISH = 0x411;

    @Bind(R.id.imgBtn_back)
    ImageButton imgBtnBack;

    @Bind(R.id.gv_create_wish)
    ZRGridView gvCreateWish;

    @Bind(R.id.btn_next)
    Button btnNext;

    private CreateWishPresenter presenter;
    private List<WishCategory> datas = new ArrayList<>();
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
        adapter = new CreateWishAdapter(this, datas, R.layout.gv_create_wish_item);
        gvCreateWish.setAdapter(adapter);

        gvCreateWish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ZRActivityWishSettingOne.class);
                intent.putExtra("wish", datas.get(position));
                startActivityForResult(intent, REQUEST_CODE_CREATEWISH);
            }
        });
        presenter.doGetWishListTypes();
    }

    @OnClick({R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
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
        datas.clear();
        datas.addAll(presenter.categoryList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CREATEWISH:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }

    class CreateWishAdapter extends ZRCommonAdapter<WishCategory> {

        public CreateWishAdapter(Context context, List<WishCategory> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, ZRViewHolder helper, WishCategory item) {
            helper.setImageResource(R.id.img_category_bg, item.imgUrl);
            helper.setText(R.id.tv_category_name, item.name);
        }
    }
}
