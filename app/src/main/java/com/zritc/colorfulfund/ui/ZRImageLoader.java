package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Custom ImageView, whose content can be set as a URL.
 * 
 * @author Midas
 * @version 1.0
 * @createDate 2016-03-29
 * @lastUpdate 2016-03-29
 */
public class ZRImageLoader extends ImageView {

	/**
	 * @IMAGE_TYPE_NORMAL{普通
	 * @IMAGE_TYPE_CIRCLE{圆角
	 * @IAMGE_TYPE_ROUND{弧度切角,default:5dp
	 */
	public enum ImageType {
		IMAGE_TYPE_NORMAL, IMAGE_TYPE_CIRCLE, IAMGE_TYPE_ROUND;
	}

	private static final int SIZE_SRC = -1;
	private int imageWidth = SIZE_SRC;
	private int imageHeight = SIZE_SRC;
	private String imageUrl;
	private float radius = 5f; // 切角度数，默认5dp
	private ImageType imageType = ImageType.IMAGE_TYPE_NORMAL;

	public ZRImageLoader(Context context) {
		this(context, null);
	}

	public ZRImageLoader(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZRImageLoader(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public void setImageWidth(int width) {
		imageWidth = width;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageHeight(int height) {
		imageHeight = height;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageRadius(float radius) {
		this.radius = radius;
	}

	public void loadImage(final String url, int defaultResId) {
		String trimedUrl = url.trim();
//		ZRImageLoaderHelper.getInstance().mImageLoader.displayImage(trimedUrl,
//				this, ZRImageLoaderHelper.getDisplayImageOptions(defaultResId),
//				new ImageLoadingListener() {
//
//					@Override
//					public void onLoadingStarted(String imageUri, View view) {
//
//					}
//
//					@Override
//					public void onLoadingFailed(String imageUri, View view,
//							FailReason failReason) {
//
//					}
//
//					@Override
//					public void onLoadingComplete(String imageUri, View view,
//							Bitmap loadedImage) {
//						ZRLog.d("testarcher",
//								"onLoadingComplete loadedImage = " + url
//										+ "------" + loadedImage.getWidth()
//										+ " * " + loadedImage.getHeight());
//					}
//
//					@Override
//					public void onLoadingCancelled(String imageUri, View view) {
//
//					}
//				});
	}
}
