package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import cn.jpush.android.api.JPushInterface;

/**
 * ZRActivityWelcome 启动界面
 *
 * @author gufei
 * @version 1.0
 * @createDate 2014-12-18
 * @lastUpdate 2014-12-18
 */
public class ZRActivityWelcome extends ZRActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jumpToMain();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        JPushInterface.onResume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取第一次启动的标记
     *
     * @return
     */
    private boolean getFirstStartState() {
        String version = ZRSharePreferenceKeeper.getStringValue(this,
                ZRConstant.KEY_EXTRA_CURRENT_VERSION, "", false);
        return !ZRDeviceInfo.getClientVersion().equalsIgnoreCase(version);
    }

    private void jumpToMain() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = null;
                if (getFirstStartState()) {
                    intent = new Intent(ZRActivityWelcome.this,
                            ZRActivityGuide.class);
                } else {
                    intent = new Intent(ZRActivityWelcome.this,
                            TestNetApiActivity.class);
                }
                intent.putExtra(ZRConstant.KEY_FROM_WHICH_ACTIVITY,
                        TestNetApiActivity.class.getName());
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}
