package com.zritc.colorfulfund.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.ui.photoview.PhotoViewAttacher;
import com.zritc.colorfulfund.ui.photoview.PhotoViewAttacher.OnPhotoTapListener;
import com.zritc.colorfulfund.utils.ZRLog;

import butterknife.Bind;

/**
 * 图片查看片段
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2015-12-25
 * @lastUpdate 2015-12-25
 */
public class ZRFragmentImageDetail extends ZRFragmentBase {

	@Bind(R.id.image)
	ImageView mImageView;

	@Bind(R.id.loading)
	ProgressBar progressBar;

	private PhotoViewAttacher mAttacher;
	private String mImageUrl;

	public static ZRFragmentImageDetail newInstance(String imageUrl) {
		final ZRFragmentImageDetail f = new ZRFragmentImageDetail();
		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url")
				: null;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ImageLoader.getInstance().displayImage(mImageUrl, mImageView,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						progressBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						String message = null;
						switch (failReason.getType()) {
						case IO_ERROR:
							message = "下载错误";
							break;
						case DECODING_ERROR:
							message = "图片无法显示";
							break;
						case NETWORK_DENIED:
							message = "网络有问题，无法下载";
							break;
						case OUT_OF_MEMORY:
							message = "图片太大无法显示";
							break;
						case UNKNOWN:
							message = "未知的错误";
							break;
						}
						// showToast(message);
						ZRLog.e(imageUri + " >> " + message);
						progressBar.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						progressBar.setVisibility(View.GONE);
						mAttacher.update();
					}
				});
	}

	@Override
	protected int getContentViewId() {
		return R.layout.cell_pager_image_item;
	}

	@Override
	protected void initPresenter() {
		mAttacher = new PhotoViewAttacher(mImageView);

		mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {

	@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});
	}

}
