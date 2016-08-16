package com.zritc.colorfulfund.ui.pull2refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
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
@SuppressLint("NewApi")
public class ZRRotateLoadingLayout extends ZRLoadingLayout {
	/** 旋转动画的时间 */
	static final int ROTATION_ANIMATION_DURATION = 1200;
	/** 动画插值 */
	static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
	/** Header的容器 */
	private RelativeLayout mHeaderContainer;
	/** 箭头图片 */
	private ImageView mArrowImageView;
	/** 状态提示TextView */
	private TextView mHintTextView;
	/** 最后更新时间的TextView */
	private TextView mHeaderTimeView;
	/** 最后更新时间的标题 */
	private TextView mHeaderTimeViewTitle;
	/** 旋转的动画 */
	private Animation mRotateAnimation;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            context
	 */
	public ZRRotateLoadingLayout(Context context) {
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
	public ZRRotateLoadingLayout(Context context, AttributeSet attrs) {
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
		mHeaderContainer = (RelativeLayout) findViewById(ZRResourceManager
				.getResourceID("pull_to_refresh_header_content", "id"));
		mArrowImageView = (ImageView) findViewById(ZRResourceManager
				.getResourceID("pull_to_refresh_header_arrow", "id"));
		mHintTextView = (TextView) findViewById(ZRResourceManager
				.getResourceID("pull_to_refresh_header_hint_textview", "id"));
		mHeaderTimeView = (TextView) findViewById(ZRResourceManager
				.getResourceID("pull_to_refresh_header_time", "id"));
		mHeaderTimeViewTitle = (TextView) findViewById(ZRResourceManager
				.getResourceID("pull_to_refresh_last_update_time_text", "id"));

		mArrowImageView.setScaleType(ScaleType.CENTER);
		mArrowImageView.setImageResource(ZRResourceManager.getResourceID(
				"default_ptr_rotate", "drawable"));

		float pivotValue = 0.5f; // SUPPRESS CHECKSTYLE
		float toDegree = 720.0f; // SUPPRESS CHECKSTYLE
		mRotateAnimation = new RotateAnimation(0.0f, toDegree,
				Animation.RELATIVE_TO_SELF, pivotValue,
				Animation.RELATIVE_TO_SELF, pivotValue);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
		mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
		mRotateAnimation.setRepeatCount(Animation.INFINITE);
		mRotateAnimation.setRepeatMode(Animation.RESTART);
	}

	@Override
	protected View createLoadingView(Context context, AttributeSet attrs) {
		View container = LayoutInflater.from(context).inflate(
				ZRResourceManager.getResourceID("pull_to_refresh_header",
						"layout"), null);
		return container;
	}

	@Override
	public void setLastUpdatedLabel(CharSequence label) {
		// 如果最后更新的时间的文本是空的话，隐藏前面的标题
		mHeaderTimeViewTitle
				.setVisibility(TextUtils.isEmpty(label) ? View.INVISIBLE
						: View.VISIBLE);
		mHeaderTimeView.setText(label);
	}

	@Override
	public int getContentSize() {
		if (null != mHeaderContainer) {
			return mHeaderContainer.getHeight();
		}

		return (int) (getResources().getDisplayMetrics().density * 60);
	}

	@Override
	protected void onStateChanged(ZRILoadingLayout.State curState, ZRILoadingLayout.State oldState) {
		super.onStateChanged(curState, oldState);
	}

	@Override
	protected void onReset() {
		resetRotation();
		mHintTextView.setText(ZRResourceManager.getResourceID(
				"tip_pull_to_refresh_down", "string"));
	}

	@Override
	protected void onReleaseToRefresh() {
		mHintTextView.setText(ZRResourceManager.getResourceID(
				"tip_pull_to_refresh_release", "string"));
	}

	@Override
	protected void onPullToRefresh() {
		mHintTextView.setText(ZRResourceManager.getResourceID(
				"tip_pull_to_refresh_down", "string"));
	}

	@Override
	protected void onRefreshing() {
		resetRotation();
		mArrowImageView.startAnimation(mRotateAnimation);
		mHintTextView.setText(ZRResourceManager.getResourceID(
				"tip_listview_more_loading", "string"));
	}

	@Override
	public void onPull(float scale) {
		float angle = scale * 180f; // SUPPRESS CHECKSTYLE
		mArrowImageView.setRotation(angle);
	}

	/**
	 * 重置动画
	 */
	private void resetRotation() {
		mArrowImageView.clearAnimation();
		mArrowImageView.setRotation(0);
	}
}
