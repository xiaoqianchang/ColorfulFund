package com.zritc.colorfulfund.ui.pull2refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zritc.colorfulfund.utils.ZRResourceManager;

/**
 * 这个类封装了下拉刷新的布局
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRFooterLoadingLayout extends ZRLoadingLayout {
	/** 进度条 */
	private ProgressBar mProgressBar;
	/** 显示的文本 */
	private TextView mHintView;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 */
	public ZRFooterLoadingLayout(Context context) {
		super(context);
		init(context);
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 * @param attrs
	 *            attrs
	 */
	public ZRFooterLoadingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 *            context
	 */
	private void init(Context context) {
		mProgressBar = (ProgressBar) findViewById(ZRResourceManager
				.getResourceID("pull_to_load_footer_progressbar", "id"));
		mHintView = (TextView) findViewById(ZRResourceManager.getResourceID(
				"pull_to_load_footer_hint_textview", "id"));
		setState(State.RESET);
	}

	@Override
	protected View createLoadingView(Context context, AttributeSet attrs) {
		View container = LayoutInflater.from(context)
				.inflate(
						ZRResourceManager.getResourceID("pull_to_load_footer",
								"layout"), null);
		return container;
	}

	@Override
	public void setLastUpdatedLabel(CharSequence label) {
	}

	@Override
	public int getContentSize() {
		View view = findViewById(ZRResourceManager.getResourceID(
				"pull_to_load_footer_content", "layout"));
		if (null != view) {
			return view.getHeight();
		}

		return (int) (getResources().getDisplayMetrics().density * 40);
	}

	@Override
	protected void onStateChanged(State curState, State oldState) {
		mProgressBar.setVisibility(View.GONE);
		mHintView.setVisibility(View.INVISIBLE);

		super.onStateChanged(curState, oldState);
	}

	@Override
	protected void onReset() {
		mHintView.setText(ZRResourceManager.getResourceID(
				"tip_listview_more_loading", "string"));
	}

	@Override
	protected void onPullToRefresh() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(ZRResourceManager.getResourceID(
				"tip_pull_to_refresh_up", "string"));
	}

	@Override
	protected void onReleaseToRefresh() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(ZRResourceManager.getResourceID(
				"tip_pull_to_refresh_release", "string"));
	}

	@Override
	protected void onRefreshing() {
		mProgressBar.setVisibility(View.VISIBLE);
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(ZRResourceManager.getResourceID(
				"tip_listview_more_loading", "string"));
	}

	@Override
	protected void onNoMoreData() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(ZRResourceManager.getResourceID("tip_no_more_msg",
				"string"));
	}
}
