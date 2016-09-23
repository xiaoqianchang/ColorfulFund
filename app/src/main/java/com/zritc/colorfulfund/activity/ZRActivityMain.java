package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.common.ZRAppActivityManager;
import com.zritc.colorfulfund.data.ZRDataEngine;
import com.zritc.colorfulfund.fragment.ZRFragmentFortuneGroupList;
import com.zritc.colorfulfund.fragment.ZRFragmentFundManager;
import com.zritc.colorfulfund.fragment.ZRFragmentMain;
import com.zritc.colorfulfund.fragment.ZRFragmentMine;
import com.zritc.colorfulfund.iView.IMainView;
import com.zritc.colorfulfund.presenter.MainPresenter;
import com.zritc.colorfulfund.receiver.JPushMessageReceiver;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRLog;
import com.zritc.colorfulfund.utils.ZRToastFactory;
import com.zritc.colorfulfund.view.ZRTabWidget;
import com.zritc.colorfulfund.view.ZRTabWidget.OnTabSelectedListener;

/**
 * ZRActivityMain 主界面
 *
 * @author gufei
 * @version 1.0
 * @createDate 2015-06-24
 * @lastUpdate 2015-06-24
 */
public class ZRActivityMain extends ZRActivityToolBar<MainPresenter> implements IMainView,
        OnTabSelectedListener, ZRFragmentFundManager.OnFragmentInteractionListener {

    private static final int DIALOG_ACTION_FORCE_UPDATE = 1;
    private static final int DOUBLE_CLICK_DURATION = 1000;

    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static final String MESSAGE_RECEIVED_ACTION = "com.zritc.colorfulfund.MESSAGE_RECEIVED_ACTION";

    // for receive customer msg from jpush server
    private JPushMessageReceiver mMessageReceiver;

    private ZRTabWidget mTabWidget;
    private ZRFragmentMain mainFragment;
    private ZRFragmentFundManager fundManagerFragment;
    private ZRFragmentFortuneGroupList fortuneGroupListFragment;
    private ZRFragmentMine mineFragment;
    private int mFragmentIndex = 0;
    private FragmentManager mFragmentManager;

    private final Handler mHandler = new Handler();
    private boolean isDoubleClick = false;
    public static boolean isForeground = false;

    private MainPresenter mainPresenter;


    private final Runnable mCancelRunable = new Runnable() {

        @Override
        public void run() {
            isDoubleClick = false;
            ZRToastFactory.cancelToast();
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.init();
    }

    @Override
    public void initView() {

        hideLeftBar();

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            String from = bundle.getString(ZRConstant.KEY_FROM_WHICH_ACTIVITY);
        }
        init();
        initEvents();
        registerMessageReceiver();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("midas", "onNewIntent执行了");
        setIntent(intent);
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        mTabWidget = (ZRTabWidget) findViewById(R.id.tab_widget);
    }

    private void initEvents() {
        mTabWidget.setOnTabSelectedListener(this);
    }

    @Override
    public void onPause() {
        isForeground = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        isForeground = true;
        if (1 != mFragmentIndex) {
            onTabSelected(mFragmentIndex);
            mTabWidget.setTabsDisplay(mFragmentIndex);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        ZRLog.e("android main page onDestroy");
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onTabSelected(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                setTitleText("多彩基金");
                if (null == mainFragment) {
                    mainFragment = new ZRFragmentMain();
                    transaction.add(R.id.center_layout, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case 1:
                setTitleText("基金管家");
                if (null == fundManagerFragment) {
                    fundManagerFragment = ZRFragmentFundManager.newInstance("");
                    transaction.add(R.id.center_layout, fundManagerFragment);
                } else {
                    transaction.show(fundManagerFragment);
                }
                break;
            case 2:
                setTitleText("财富圈");
                if (null == fortuneGroupListFragment) {
                    fortuneGroupListFragment = ZRFragmentFortuneGroupList.newInstance("");
                    transaction.add(R.id.center_layout, fortuneGroupListFragment);
                } else {
                    transaction.show(fortuneGroupListFragment);
                }
                break;
            case 3:
                setTitleText("我的");
                if (null == mineFragment) {
                    mineFragment = new ZRFragmentMine();
                    transaction.add(R.id.center_layout, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        mFragmentIndex = index;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != mainFragment) {
            transaction.hide(mainFragment);
        }
        if (null != fundManagerFragment) {
            transaction.hide(fundManagerFragment);
        }
        if (null != fortuneGroupListFragment) {
            transaction.hide(fortuneGroupListFragment);
        }
        if (null != mineFragment) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
        // super.onSaveInstanceState(outState);
        outState.putInt("index", mFragmentIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // super.onRestoreInstanceState(savedInstanceState);
        mFragmentIndex = savedInstanceState.getInt("index");
    }

    protected void clearBeforeExit() {
        ZRAppActivityManager.getAppManager().finishAllActivity();
        ZRDataEngine.clear();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isDoubleClick) {
                isDoubleClick = true;
                ZRToastFactory.getToast(this, R.string.toast_exit).show();
                mHandler.postDelayed(mCancelRunable, DOUBLE_CLICK_DURATION);
                return true;
            } else {
                clearBeforeExit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new JPushMessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
