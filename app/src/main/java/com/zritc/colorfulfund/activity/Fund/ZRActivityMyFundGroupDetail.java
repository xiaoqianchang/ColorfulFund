package com.zritc.colorfulfund.activity.fund;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.edu.PoChangeHistory;
import com.zritc.colorfulfund.data.model.edu.PoFund;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.iView.IFundGroupDetailView;
import com.zritc.colorfulfund.presenter.FundGroupDetailPresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityFundGroupDetail 我的历史基金投资详情
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-30
 * @lastUpdate 2016-08-30
 */
public class ZRActivityMyFundGroupDetail extends ZRActivityBase<FundGroupDetailPresenter> implements IFundGroupDetailView, View.OnTouchListener,
        GestureDetector.OnGestureListener {

    @Bind(R.id.btn_left_back)
    ImageButton btnBack;

    @Bind(R.id.text_fund_name)
    TextView textFundName;

    @Bind(R.id.text_fund_type)
    TextView textFundType;

    @Bind(R.id.text_fund_total_amount)
    TextView textFundTotalAmount;

    @Bind(R.id.text_fund_year)
    TextView textFundYear;

    @Bind(R.id.text_fund_date)
    TextView textFundDate;

    @Bind(R.id.text_fund_year_rate_return)
    TextView textFundYearRateReturn;

    @Bind(R.id.text_fund_rate_return)
    TextView textFundRateReturn;

    @Bind(R.id.text_n_group)
    TextView textNGroup;

    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Bind(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @Bind(R.id.view_circle_container)
    LinearLayout circleContainer;

    @Bind(R.id.btn_store_money)
    ImageView btnStroMoney;

    @Bind(R.id.btn_back_money)
    ImageView btnBackMoney;

    private GestureDetector gestureDetector; // 手势识别
    private static final int FLING_MIN_DISTANCE = 2;
    private static final int FLING_MIN_VELOCITY = 200;
    private int currentGuidePage = 0;

    private ZRCommonAdapter<PoFund> adapter;

    private FundGroupDetailPresenter fundGroupDetailPresenter;

    private List<PoChangeHistory> allDatas = new ArrayList<>();
    private List<PoFund> datas = new ArrayList<>();

    private String poCode = "";
    private String totalAmount = "";
    private String initialtAmount = "";

    @OnClick({R.id.btn_left_back, R.id.btn_store_money, R.id.btn_back_money})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_left_back:
                onBackPressed();
                break;
            case R.id.btn_store_money:
                intent.setClass(this, ZRActivityMultiFundApplyPurchase.class);
                intent.putExtra("poCode", poCode);
                intent.putExtra("money", initialtAmount);
                startActivity(intent);
                break;
            case R.id.btn_back_money:
                intent.setClass(this, ZRActivityGroupRedemption.class);
                intent.putExtra("poCode", poCode);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getActivityType(){
        return ACTIVITY_TYPE_NO_DISPATCH_TOUCH;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_fund_group_detail;
    }

    @Override
    protected void initPresenter() {
        fundGroupDetailPresenter = new FundGroupDetailPresenter(this, this);
        fundGroupDetailPresenter.init();
        fundGroupDetailPresenter.getUserPoAssetInfo4C(poCode);
        fundGroupDetailPresenter.getPoChangeHistory4C(poCode);
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            poCode = bundle.getString("poCode");
        }
    }

    @Override
    public void initView() {
        getExtraData();
    }

    private void initViewFlipper() {

        initCircleViews();

        viewFlipper.setLongClickable(true);
        gestureDetector = new GestureDetector(this);

        for (int i = 0; i < allDatas.size(); i++) {
            datas.clear();
            datas.addAll(allDatas.get(i).poFundList);
            ZRListView listView = new ZRListView(this);
            listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            listView.setAdapter(adapter = new ZRCommonAdapter<PoFund>(this, datas, R.layout.cell_fund_detail_item) {
                @Override
                public void convert(int position, ZRViewHolder helper, PoFund item) {
                    helper.setText(R.id.text_name, item.fundName);
                    helper.setText(R.id.text_per, item.poPercentage);
                    double money = 0;
                    if (!TextUtils.isEmpty(item.poPercentage) && !TextUtils.isEmpty(totalAmount)) {
                        money = Double.parseDouble(item.poPercentage) * Double.parseDouble(totalAmount);
                    }
                    helper.setText(R.id.text_money, ZRUtils.getDecimalFormat(money));
                }
            });

            listView.setDivider(null);
            listView.setMaxHeight(100);
            listView.setListViewHeightBasedOnChildren(listView);
            listView.setParentScrollView(scrollView);
            listView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ZRActivityMyFundGroupDetail.this.gestureDetector.onTouchEvent(event);
                    return false;
                }

            });
            viewFlipper.addView(listView);
        }
    }

    /**
     * 初始化圆圈小点
     */
    private void initCircleViews() {
        int pageCount = allDatas.size();
        int padding = getResources().getDimensionPixelSize(R.dimen.padding_20) / 2;
        for (int i = 0; i < pageCount; ++i) {
            ImageView circle = new ImageView(this);
            if (currentGuidePage == i) {
                circle.setImageResource(R.mipmap.icon_dot_unselect);
            } else {
                circle.setImageResource(R.mipmap.icon_dot_select);
            }
            circle.setPadding(padding, 0, padding, 0);
            circleContainer.addView(circle);
        }
        if (1 >= pageCount) {
            circleContainer.setVisibility(View.INVISIBLE);
        } else {
            circleContainer.setVisibility(View.VISIBLE);
        }
    }

    public void onPageSelected(int newPage) {
        if (currentGuidePage >= 0
                && currentGuidePage < allDatas.size()) {
            ((ImageView) (circleContainer.getChildAt(currentGuidePage)))
                    .setImageResource(R.mipmap.icon_dot_select);
        }
        ((ImageView) (circleContainer.getChildAt(newPage)))
                .setImageResource(R.mipmap.icon_dot_unselect);
        currentGuidePage = newPage;
        circleContainer.invalidate();
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof UserPoAssetInfo) {
            UserPoAssetInfo userPoAssetInfo = ((UserPoAssetInfo) object);
            this.initialtAmount = userPoAssetInfo.initialtAmount;//初始投资
            this.totalAmount = userPoAssetInfo.totalAmount;//基金总额
            String level = userPoAssetInfo.getRiskLevel();
            textFundName.setText("宝宝基金");
            textFundType.setText(level);
            textFundTotalAmount.setText("¥" + this.totalAmount);

            textFundYear.setText(ZRUtils.getDateLength(ZRUtils.getCurrentTime("yyyyMMdd"), userPoAssetInfo.targetDate)[0] + "年");
            textFundDate.setText(ZRUtils.formatMyDate2(userPoAssetInfo.targetDate));
            textFundYearRateReturn.setText(userPoAssetInfo.expectedYearlyRoe + "%");
            textFundRateReturn.setText(userPoAssetInfo.expectedYearlyRoe + "%");
        } else if (object instanceof PoChangeHistory) {
            PoChangeHistory poChangeHistory = ((PoChangeHistory) object);
            allDatas.addAll(poChangeHistory.poChangeHistory);
            int len = poChangeHistory.poChangeHistory.size();
            if (len > 0) {
                initViewFlipper();
            }
            textNGroup.setText(len + "次甄选组合");
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left_out));
            this.viewFlipper.showNext();
        } else if (e1.getX() - e2.getX() < -120) {
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right_out));
            this.viewFlipper.showPrevious();
        }
        onPageSelected(viewFlipper.getDisplayedChild());
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
