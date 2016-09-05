package com.zritc.colorfulfund.activity.fund;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.data.response.trade.GetUserPoList4C;
import com.zritc.colorfulfund.iView.IFundListView;
import com.zritc.colorfulfund.presenter.FundListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 基金列表
 * 
 * Created by Chang.Xiao on 2016/8/19.
 *
 * @version 1.0
 */
public class ZRActivityFundList extends ZRActivityToolBar<FundListPresenter> implements IFundListView {

    private static final int REQUEST_CODE_GROUP_REDEMPTION = 0x110;

    @Bind(R.id.lv_fund_list)
    ListView lvFundList;

    private FundListPresenter fundListPresenter;
    private GetUserPoList4C userPoList4C;
    private MyAdapter myAdapter;
    private List<GetUserPoList4C.UserPoList> userPoList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fund_list;
    }

    @Override
    protected void initPresenter() {
        fundListPresenter = new FundListPresenter(this, this);
        fundListPresenter.init();
    }

    @Override
    public void initView() {
        setTitleText("我的基金列表");
        userPoList = new ArrayList<>();
        initData();
        myAdapter = new MyAdapter();
        lvFundList.setAdapter(myAdapter);
        lvFundList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetUserPoList4C.UserPoList userPoList = ZRActivityFundList.this.userPoList.get(position);
                Intent intent = new Intent(mContext, ZRActivityGroupRedemption.class);
                intent.putExtra("poCode", userPoList.poBase.poCode);
                startActivityForResult(intent, REQUEST_CODE_GROUP_REDEMPTION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_GROUP_REDEMPTION:
                    initData();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        fundListPresenter.doGetUserPoList4C();
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
        if (object instanceof GetUserPoList4C) {
            // 基金列表
            userPoList4C = (GetUserPoList4C) object;
            if (null != userPoList4C) {
                userPoList = userPoList4C.userPoList;
            }
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String msg) {

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userPoList != null ? userPoList.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return userPoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_fund_list_item, null);
            TextView tvName = (TextView) convertView.findViewById(R.id.tv_name_test);
            tvName.setText(userPoList.get(position).poBase.poName);
            return convertView;
        }
    }
}
