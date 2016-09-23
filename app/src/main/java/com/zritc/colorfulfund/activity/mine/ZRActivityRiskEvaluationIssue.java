package com.zritc.colorfulfund.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.mine.RiskEvaluationIssue;
import com.zritc.colorfulfund.fragment.mine.ZRFragmentRiskEvaluationIssue;
import com.zritc.colorfulfund.fragment.mine.ZRFragmentRiskEvaluationIssueComplete;
import com.zritc.colorfulfund.iView.IRiskEvaluationIssueView;
import com.zritc.colorfulfund.presenter.RiskEvaluationIssuePresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.utils.ZRConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 风险评估问题界面
 * <p>
 * Created by Chang.Xiao on 2016/9/21.
 *
 * @version 1.0
 */
public class ZRActivityRiskEvaluationIssue extends ZRActivityBase<RiskEvaluationIssuePresenter> implements IRiskEvaluationIssueView {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    private RiskEvaluationIssuePresenter presenter;
    private RiskEvaluationIssue riskEvaluationIssue;
    private IssuePagerAdapter issuePagerAdapter;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private ZRFragmentRiskEvaluationIssueComplete riskEvaluationIssueCompleteFragment;
    private List<RiskEvaluationIssue.Issue> issueList;
    private int mFragementIndex;
    private String answerId; // 答案Id, 传递的是一个字符串,每个answerId使用逗号进行分隔

    @Override
    protected int getContentViewId() {
        return R.layout.activity_risk_evaluation_issue;
    }

    @Override
    protected void initPresenter() {
        presenter = new RiskEvaluationIssuePresenter(this, this);
        presenter.init();
        // 获取数据
        presenter.getSurveyList();
    }

    @Override
    public void initView() {
        issueList = new ArrayList<>();

        // 关闭预加载，默认一次只加载一个Fragment
        mViewPager.setOffscreenPageLimit(1);
        issuePagerAdapter = new IssuePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(issuePagerAdapter);
        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setCurrentItem(mFragementIndex);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFragementIndex = position;
                mViewPager.setCurrentItem(mFragementIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 上一页
     */
    public void lastPage() {
        if (mFragementIndex > 0) {
            mViewPager.setCurrentItem(--mFragementIndex);
        }
    }

    /**
     * 下一页
     */
    public void nextPage() {
        if (mFragementIndex < issueList.size()) {
            mViewPager.setCurrentItem(++mFragementIndex);
        }
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        if (TextUtils.isEmpty(this.answerId)) { // 第一次
            this.answerId = answerId;
        } else {
            this.answerId = this.answerId + "," + answerId;
        }
    }

    /**
     * 完成测评
     */
    public void completeRiskEvaluation() {
        Intent intent = new Intent();
        intent.putExtra("riskEvaluationType", riskEvaluationIssueCompleteFragment.getRiskEvaluationType());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof RiskEvaluationIssue) {
            riskEvaluationIssue = (RiskEvaluationIssue) object;
            issueList.clear();
            issueList.addAll(riskEvaluationIssue.issueList);
            initFragment();
            issuePagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mFragments.clear();// 清空
        int count = issueList.size();
        for (int i = 0; i < count; i++) {
            ZRFragmentRiskEvaluationIssue fragment = new ZRFragmentRiskEvaluationIssue();
            Bundle data = new Bundle();
            data.putSerializable("issue", issueList.get(i));
            data.putString("index", String.valueOf(i + 1));
            data.putString("issueNum", riskEvaluationIssue.issueNum);
            fragment.setArguments(data);
            mFragments.add(fragment);
        }
        riskEvaluationIssueCompleteFragment = new ZRFragmentRiskEvaluationIssueComplete();
        mFragments.add(riskEvaluationIssueCompleteFragment);
    }

    class IssuePagerAdapter extends FragmentStatePagerAdapter {

        public IssuePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return mClazzArray[position];
        }*/

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
