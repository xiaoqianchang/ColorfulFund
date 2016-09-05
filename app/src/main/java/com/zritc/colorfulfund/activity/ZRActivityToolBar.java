package com.zritc.colorfulfund.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.presenter.BasePresenter;
import com.zritc.colorfulfund.utils.ZRSystemUtils;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * 带Toolbar自定义Title的基类Activity
 * <p>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public abstract class ZRActivityToolBar<T extends BasePresenter> extends ZRActivityBase {

    @Bind(R.id.app_bar_layout)
    protected AppBarLayout mAppBarLayout;
    @Bind(R.id.status_bar)
    protected View mStatusBar;
    @Bind(R.id.toolBar)
    protected Toolbar mToolbar;

    @Bind(R.id.nav_left_text)
    protected TextView mNavLeftText;
    @Bind(R.id.center_title)
    protected TextView mCenterTitle;
    @Bind(R.id.nav_right_text)
    protected TextView mNavRightText;
    @Bind(R.id.nav_right_other_text)
    protected TextView mNavRightOtherText;

    protected T mPresenter;
    protected boolean isToolBarHiding = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        initToolBar();
    }

    protected void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = ZRSystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
    }

    protected void initToolBar() {
        if (null == mAppBarLayout || null == mToolbar) {
            throw new IllegalStateException("The subclass of ToolbarActivity must contain a toolbar.");
        }
        mToolbar.setOnClickListener(v -> onToolbarClick());
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (canBack()) {
            if (null != actionBar) {
                actionBar.setDisplayHomeAsUpEnabled(canBack()); // 设置返回箭头
            }
        } else {
            if (null != actionBar) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            mAppBarLayout.setElevation(10.6f);
        }

        RxView.clicks(mNavLeftText).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                    finish();
                });
    }

    /**
     * set title text
     *
     * @param title
     */
    protected void setTitleText(CharSequence title){
        mCenterTitle.setText(title);
    }

    protected void hideLeftBar(){
        mNavLeftText.setVisibility(View.INVISIBLE);
    }

    /**
     * onToolbarClick
     */
    protected void onToolbarClick() {
        // empty
    }

    /**
     * 设置 NavigationButton 是否可见
     *
     * @return
     */
    protected boolean canBack() {
        return false;
    }

    /**
     * 显示or隐藏导航栏
     *
     * @param visible
     */
    protected void showToolBar(int visible) {
        mToolbar.setVisibility(visible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setAppBarAlpha(float alpha) {
        mAppBarLayout.setAlpha(alpha);
    }

    /**
     * Control ToolBar show or hiden
     */
    protected void hideOrShowToolBar() {
        mAppBarLayout.animate()
                .translationY(isToolBarHiding ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isToolBarHiding = !isToolBarHiding;
    }
}
