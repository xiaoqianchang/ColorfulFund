package com.zritc.colorfulfund.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.iView.IFundManagerView;
import com.zritc.colorfulfund.presenter.FundManagerPresenter;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRFragmentFundManager 基金管家首页
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public class ZRFragmentFundManager extends ZRFragmentBase<FundManagerPresenter> implements IFundManagerView, ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";

    @Bind(R.id.ll_tab_view)
    LinearLayout tabView;

    @Bind(R.id.tab_combination_analysis)
    TextView tabCombinationAnalysis;

    @Bind(R.id.tab_fund_news)
    TextView tabFundNews;

    @Bind(R.id.view_tab_line)
    View tabViewLine;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private FundManagerPresenter fundManagerPresenter;
    private OnFragmentInteractionListener mListener;

    private ZRFragmentFundCombinAnalysis fragmentFundCombinAnalysis;
    private ZRFragmentFundNews fragmentFundNews;
    private List<Fragment> fragments = new ArrayList<>();

    private SlidTabPagerAdapter tabAdapter;
    private String[] clazzArray = new String[]{"组合分析", "管家动态"};

    private int lineWidth = 0;

    private String param;

    public ZRFragmentFundManager() {
    }

    @OnClick({R.id.tab_combination_analysis, R.id.tab_fund_news})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_combination_analysis:
                viewPager.setCurrentItem(0);
                changeState(0);
                break;
            case R.id.tab_fund_news:
                viewPager.setCurrentItem(1);
                changeState(1);
                break;
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter.
     * @return A new instance of fragment ZRFragmentFundManager.
     */
    // TODO: Rename and change types and number of parameters
    public static ZRFragmentFundManager newInstance(String param) {
        ZRFragmentFundManager fragment = new ZRFragmentFundManager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getExtraArguments() {
        param = getArguments().getString(ARG_PARAM);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_fortune_manager;
    }

    @Override
    protected void initPresenter() {
        fundManagerPresenter = new FundManagerPresenter(getActivity(), this);
        fundManagerPresenter.init();
    }

    @Override
    public void initView() {
        ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleX(1.1f).setDuration(0);
        ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleY(1.1f).setDuration(0);

        lineWidth = (ZRDeviceInfo.getDeviceWidth() - 54 * 2) / 2;
        tabViewLine.getLayoutParams().width = lineWidth;
        tabViewLine.requestLayout();

        fragments.clear();
        fragments.add(fragmentFundCombinAnalysis = ZRFragmentFundCombinAnalysis.newInstance(""));
        fragments.add(fragmentFundNews = ZRFragmentFundNews.newInstance(""));

        // 关闭预加载，默认一次只加载一个Fragment
        viewPager.setOffscreenPageLimit(2);
        tabAdapter = new SlidTabPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float tagerX = position * lineWidth + positionOffsetPixels / 2;
        ViewPropertyAnimator.animate(tabViewLine).translationX(tagerX).setDuration(0);
    }

    @Override
    public void onPageSelected(int position) {
        changeState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeState(int arg0) {
        if (arg0 == 0) {
            tabCombinationAnalysis.setTextColor(Color.parseColor("#e5864b"));
            tabFundNews.setTextColor(Color.parseColor("#5b4e74"));
            ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleX(1.1f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleY(1.1f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabFundNews).scaleX(1.0f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabFundNews).scaleY(1.0f)
                    .setDuration(200);
        } else if (arg0 == 1) {
            tabFundNews.setTextColor(Color.parseColor("#e5864b"));
            tabCombinationAnalysis.setTextColor(Color.parseColor("#5b4e74"));
            ViewPropertyAnimator.animate(tabFundNews).scaleX(1.1f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabFundNews).scaleY(1.1f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleX(1.0f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(tabCombinationAnalysis).scaleY(1.0f)
                    .setDuration(200);
        }
    }

    /**
     * Tab标签适配器
     */
    public class SlidTabPagerAdapter extends FragmentStatePagerAdapter {

        public SlidTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return clazzArray[position];
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
