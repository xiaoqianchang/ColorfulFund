package com.zritc.colorfulfund.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRFragmentBase;
import com.zritc.colorfulfund.ui.photoview.PhotoViewAttacher;
import com.zritc.colorfulfund.ui.photoview.PhotoViewAttacher.OnPhotoTapListener;
import com.zritc.colorfulfund.utils.ZRLog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 图片查看片段
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2015-12-25
 * @lastUpdate 2015-12-25
 */
public class ZRFragmentImageDetail extends ZRFragmentBase {

	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;

	private PhotoViewAttacher mAttacher;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.cell_pager_image_item,
				container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		mAttacher = new PhotoViewAttacher(mImageView);

		mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {

			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});

		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		return v;
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
	protected void lazyLoad() {

	}

	@Override
	public String getFragmentName() {
		return null;
	}

}
