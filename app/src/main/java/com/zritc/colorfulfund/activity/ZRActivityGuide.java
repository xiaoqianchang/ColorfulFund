package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityGuide 引导页
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-12-18
 * @lastUpdate 2014-12-18
 */
public class ZRActivityGuide extends ZRActivityBase {

	private String mFrom;

	@Override
	protected int getContentViewId() {
		return R.layout.activity_guide;
	}

	@Override
	protected void initPresenter() {
		initBundleData();
		saveFirstStartState();
		initView();
	}

	public void initView() {

	}

	@OnClick(R.id.ib_stepover)
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ib_stepover:
				Intent intent = new Intent(this, ZRActivityMain.class);
				break;
		}
	}


	/**
	 * 接收前一个界面传来的参数
	 */
	private void initBundleData() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (null != bundle) {
				mFrom = bundle.getString(ZRConstant.KEY_FROM_WHICH_ACTIVITY);
			}
		}
	}

	/**
	 * 更新第一次启动的标记位
	 * 
	 * @param
	 */
	private void saveFirstStartState() {
		ZRSharePreferenceKeeper.keepStringValue(this,
				ZRConstant.KEY_EXTRA_CURRENT_VERSION,
				ZRDeviceInfo.getClientVersion(), false);
	}

	/**
	 * 跳转到新的Activity
	 */
	private void gotoNewActivity() {
		Intent intent = new Intent(this, ZRActivityMain.class);
		if (!TextUtils.isEmpty(mFrom)) {
			intent.putExtra(ZRConstant.KEY_FROM_WHICH_ACTIVITY, mFrom);
		}
		if (!mFrom.equals("com.zritc.colorfulfund.activity.mine.ZRActivityAbout")) {
			startActivity(intent);
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}
		finish();
	}

	/**
	 * 重写监听硬键盘按键消息
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			gotoNewActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
