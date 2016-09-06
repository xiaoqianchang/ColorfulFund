package com.zritc.colorfulfund.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zritc.colorfulfund.activity.ZRFragmentMultiImageSelector;
import com.zritc.colorfulfund.data.ZRPhotoImage;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片Adapter Created by Nereo on 2015/4/7. Updated by nereo on 2016/1/19.
 */
public class ZRImageGridAdapter extends BaseAdapter {

	private static final int TYPE_CAMERA = 0;
	private static final int TYPE_NORMAL = 1;

	private Context mContext;

	private LayoutInflater mInflater;
	private boolean showCamera = true;
	private boolean showSelectIndicator = true;

	private List<ZRPhotoImage> mImages = new ArrayList<ZRPhotoImage>();
	private List<ZRPhotoImage> mSelectedImages = new ArrayList<ZRPhotoImage>();

	final int mGridWidth;

	public ZRImageGridAdapter(Context context, boolean showCamera, int column) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.showCamera = showCamera;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int width = 0;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point size = new Point();
			wm.getDefaultDisplay().getSize(size);
			width = size.x;
		} else {
			width = wm.getDefaultDisplay().getWidth();
		}
		mGridWidth = width / column;
	}

	/**
	 * 显示选择指示器
	 * 
	 * @param b
	 */
	public void showSelectIndicator(boolean b) {
		showSelectIndicator = b;
	}

	public void setShowCamera(boolean b) {
		if (showCamera == b)
			return;

		showCamera = b;
		notifyDataSetChanged();
	}

	public boolean isShowCamera() {
		return showCamera;
	}

	/**
	 * 选择某个图片，改变选择状态
	 * 
	 * @param image
	 */
	public void select(ZRPhotoImage image) {
		if (mSelectedImages.contains(image)) {
			mSelectedImages.remove(image);
		} else {
			mSelectedImages.add(image);
		}
		notifyDataSetChanged();
	}

	/**
	 * 通过图片路径设置默认选择
	 * 
	 * @param resultList
	 */
	public void setDefaultSelected(ArrayList<String> resultList) {
		for (String path : resultList) {
			ZRPhotoImage image = getImageByPath(path);
			if (image != null) {
				mSelectedImages.add(image);
			}
		}
		if (mSelectedImages.size() > 0) {
			notifyDataSetChanged();
		}
	}

	private ZRPhotoImage getImageByPath(String path) {
		if (mImages != null && mImages.size() > 0) {
			for (ZRPhotoImage image : mImages) {
				if (image.path.equalsIgnoreCase(path)) {
					return image;
				}
			}
		}
		return null;
	}

	/**
	 * 设置数据集
	 * 
	 * @param images
	 */
	public void setData(List<ZRPhotoImage> images) {
		mSelectedImages.clear();

		if (images != null && images.size() > 0) {
			mImages = images;
		} else {
			mImages.clear();
		}
		notifyDataSetChanged();
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (showCamera) {
			return position == 0 ? TYPE_CAMERA : TYPE_NORMAL;
		}
		return TYPE_NORMAL;
	}

	@Override
	public int getCount() {
		return showCamera ? mImages.size() + 1 : mImages.size();
	}

	@Override
	public ZRPhotoImage getItem(int i) {
		if (showCamera) {
			if (i == 0) {
				return null;
			}
			return mImages.get(i - 1);
		} else {
			return mImages.get(i);
		}
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		if (isShowCamera()) {
			if (i == 0) {
				view = mInflater.inflate(ZRResourceManager.getResourceID(
						"list_item_camera", "layout"), viewGroup, false);
				return view;
			}
		}

		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(ZRResourceManager.getResourceID(
					"list_item_image", "layout"), viewGroup, false);
			holder = new ViewHolder(view);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (holder != null) {
			holder.bindData(getItem(i));
		}

		return view;
	}

	class ViewHolder {
		ImageView image;
		ImageView indicator;
		View mask;

		ViewHolder(View view) {
			image = (ImageView) view.findViewById(ZRResourceManager
					.getResourceID("image", "id"));
			indicator = (ImageView) view.findViewById(ZRResourceManager
					.getResourceID("checkmark", "id"));
			mask = view.findViewById(ZRResourceManager.getResourceID("mask",
					"id"));
			view.setTag(this);
		}

		void bindData(final ZRPhotoImage data) {
			if (data == null)
				return;
			// 处理单选和多选状态
			if (showSelectIndicator) {
				indicator.setVisibility(View.VISIBLE);
				if (mSelectedImages.contains(data)) {
					// 设置选中状态
					indicator.setImageResource(ZRResourceManager.getResourceID(
							"btn_selected", "mipmap"));
					mask.setVisibility(View.VISIBLE);
				} else {
					// 未选择
					indicator.setImageResource(ZRResourceManager.getResourceID(
							"btn_unselected", "mipmap"));
					mask.setVisibility(View.GONE);
				}
			} else {
				indicator.setVisibility(View.GONE);
			}
			File imageFile = new File(data.path);
			if (imageFile.exists()) {
				// 显示图片
				Picasso.with(mContext)
						.load(imageFile)
						.placeholder(
								ZRResourceManager.getResourceID(
										"default_error", "mipmap"))
						.tag(ZRFragmentMultiImageSelector.TAG)
						.resize(mGridWidth, mGridWidth).centerCrop()
						.into(image);
			} else {
				image.setImageResource(ZRResourceManager.getResourceID(
						"default_error", "mipmap"));
			}
		}
	}

}
