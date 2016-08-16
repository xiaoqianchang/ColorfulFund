package com.zritc.colorfulfund.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.fragment.ZRFragmentImageDetail;
import com.zritc.colorfulfund.ui.ZRTextView;
import com.zritc.colorfulfund.ui.ZRViewPager;
import com.zritc.colorfulfund.utils.ZRConstant;

/**
 * 图片查看器
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2015-12-25
 * @lastUpdate 2015-12-25
 */
public class ZRActivityImagePager extends ZRActivityBase implements
		OnClickListener {

	private static final String STATE_POSITION = "STATE_POSITION";

	private ZRViewPager mViewPager;
	private ZRTextView mTxtIndicator;
	private int mPagerPosition;

	@Override
	public void onClick(View view) {
		finish();
	}

	private class ImagePagerAdapter extends FragmentStatePagerAdapter {

		public String[] fileList;

		public ImagePagerAdapter(FragmentManager fm, String[] fileList) {
			super(fm);
			this.fileList = fileList;
		}

		@Override
		public int getCount() {
			return fileList == null ? 0 : fileList.length;
		}

		@Override
		public Fragment getItem(int position) {
			String url = fileList[position];
			return ZRFragmentImageDetail.newInstance(url);
		}

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_pager);

		Bundle bundle = getIntent().getExtras();
		assert bundle != null;
		String[] imageUrls = bundle
				.getStringArray(ZRConstant.KEY_EXTRA_IMAGE_LIST);
		mPagerPosition = bundle.getInt(ZRConstant.KEY_EXTRA_IMAGE_INDEX, 0);

		if (savedInstanceState != null) {
			mPagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		mViewPager = (ZRViewPager) findViewById(R.id.pager);

		ImagePagerAdapter mAdapter = new ImagePagerAdapter(
				getSupportFragmentManager(), imageUrls);
		mViewPager.setAdapter(mAdapter);

		mTxtIndicator = (ZRTextView) findViewById(R.id.indicator);
		CharSequence text = getString(R.string.lable_viewpager_indicator, 1,
				mViewPager.getAdapter().getCount());
		mTxtIndicator.setText(text);
		// 更新下标
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				CharSequence text = getString(
						R.string.lable_viewpager_indicator, arg0 + 1,
						mViewPager.getAdapter().getCount());
				mTxtIndicator.setText(text);
			}

		});
		if (savedInstanceState != null) {
			mPagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		mViewPager.setCurrentItem(mPagerPosition);
	}

	@Override
	protected int getContentViewId() {
		return 0;
	}

	@Override
	protected void initPresenter() {

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, mViewPager.getCurrentItem());
	}

}