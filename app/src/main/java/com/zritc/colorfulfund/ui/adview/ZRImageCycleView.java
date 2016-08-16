package com.zritc.colorfulfund.ui.adview;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRLog;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.util.ArrayList;

/**
 * 轮播图片控件
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2016-01-13
 * @lastUpdate 2016-01-13
 */
public class ZRImageCycleView extends LinearLayout {

	private final long delayMills = 3000;

	private Context mContext;
	// 图片轮播视图
	private ViewPager mAdvPager = null;
	// 滚动图片视图适配
	private ImageCycleAdapter mAdvAdapter;
	// 图片轮播指示器控件
	private ViewGroup mGroup;
	// 图片轮播指示个图
	private ImageView mImageView = null;
	// 滚动图片指示视图列表
	private ImageView[] mImageViews = null;
	// 手机密度
	private float mScale;
	private boolean isStop;

	public ZRImageCycleView(Context context) {
		super(context);
		init(context);
	}

	public ZRImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mScale = context.getResources().getDisplayMetrics().density;
		LayoutInflater.from(context).inflate(
				ZRResourceManager.getResourceID("view_ad_container", "layout"),
				this);
		mAdvPager = (ViewPager) findViewById(ZRResourceManager.getResourceID(
				"adv_pager", "id"));

		for (int i = 0; i < mAdvPager.getChildCount(); i++) {
			View view = mAdvPager.getChildAt(0);
			ZRLog.d("" + view.getLayoutParams());
		}
		mAdvPager.setOnPageChangeListener(new GuidePageChangeListener());
		mAdvPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					// 开始图片滚动
					startImageTimerTask();
					break;
				default:
					// 停止图片滚动
					stopImageTimerTask();
					break;
				}
				return false;
			}
		});
		// 滚动图片右下指示器视
		mGroup = (ViewGroup) findViewById(ZRResourceManager.getResourceID(
				"layout_circles", "id"));
	}

	/**
	 * 装填图片数据
	 * 
	 * @param imageUrlList
	 * @param imageCycleViewListener
	 */
	public void setImageResources(ArrayList<String> imageUrlList,
			ImageCycleViewListener imageCycleViewListener) {
		if (imageUrlList != null && imageUrlList.size() > 0) {
			this.setVisibility(View.VISIBLE);
		} else {
			this.setVisibility(View.GONE);
			return;
		}

		// 清除
		mGroup.removeAllViews();
		// 图片广告数量
		final int imageCount = imageUrlList.size();
		mImageViews = new ImageView[imageCount];
		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			int imageParams = (int) (mScale * 6 + 0.5f);// XP与DP转换，适应不同分辨率
			int imagePadding = (int) (mScale * 5 + 0.5f);
			LayoutParams params = new LayoutParams(imageParams, imageParams);
			params.leftMargin = ZRDeviceInfo.dp2px(10);
			mImageView.setLayoutParams(params);
			mImageView.setPadding(imagePadding, imagePadding, imagePadding,
					imagePadding);
			mImageViews[i] = mImageView;
			mImageViews[i].setBackgroundResource(ZRResourceManager
					.getResourceID(i == 0 ? "bg_white_point" : "bg_gray_point",
							"drawable"));
			mGroup.addView(mImageViews[i]);
		}

		mAdvAdapter = new ImageCycleAdapter(mContext, imageUrlList,
				imageCycleViewListener);
		mAdvPager.setAdapter(mAdvAdapter);
		startImageTimerTask();
	}

	/**
	 * 图片轮播(手动控制自动轮播与否，便于资源控件）
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播—用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 图片滚动任务
	 */
	private void startImageTimerTask() {
		stopImageTimerTask();
		// 图片滚动
		mHandler.postDelayed(mImageTimerTask, delayMills);
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		isStop = true;
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {
		@Override
		public void run() {
			if (mImageViews != null) {
				mAdvPager.setCurrentItem(mAdvPager.getCurrentItem() + 1);
				if (!isStop) { // if isStop=true //当你退出后 要把这个给停下来 不然 这个一直存在
								// 就一直在后台循环
					mHandler.postDelayed(mImageTimerTask, delayMills);
				}

			}
		}
	};

	/**
	 * 轮播图片监听
	 * 
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			index = index % mImageViews.length;
			// 设置图片滚动指示器
			mImageViews[index].setBackgroundResource(ZRResourceManager
					.getResourceID("bg_white_point", "drawable"));
			for (int i = 0; i < mImageViews.length; i++) {
				if (index != i) {
					mImageViews[i].setBackgroundResource(ZRResourceManager
							.getResourceID("bg_gray_point", "drawable"));
				}
			}
		}
	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<ImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList<String> mAdList = new ArrayList<String>();

		/**
		 * 广告图片点击监听
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList<String> adList,
				ImageCycleViewListener imageCycleViewListener) {
			this.mContext = context;
			this.mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList<ImageView>();
		}

		@Override
		public int getCount() {
			// return mAdList.size();
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = mAdList.get(position % mAdList.size());
			ImageView imageView;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				imageView.setScaleType(ScaleType.CENTER_CROP);
			} else {
				imageView = mImageViewCacheList.remove(0);
			}
			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(
							position % mAdList.size(), v);
				}
			});
			imageView.setTag(imageUrl);
			container.addView(imageView);
			mImageCycleViewListener.displayImage(imageUrl, imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			mAdvPager.removeView(view);
			mImageViewCacheList.add(view);
		}

	}

	public void setAdvTouchListener(final SwipeRefreshLayout swipeRefreshLayout) {
		mAdvPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					swipeRefreshLayout.setEnabled(false);
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL:
					swipeRefreshLayout.setEnabled(true);
					break;
				}
				return false;
			}
		});
	}

	/**
	 * 轮播控件的监听事件
	 * 
	 * 
	 */
	public interface ImageCycleViewListener {
		/**
		 * 加载图片资源
		 * 
		 * @param imageURL
		 * @param imageView
		 */
		void displayImage(String imageURL, ImageView imageView);

		/**
		 * 单击图片事件
		 * 
		 * @param position
		 * @param imageView
		 */
		void onImageClick(int position, View imageView);
	}

}
