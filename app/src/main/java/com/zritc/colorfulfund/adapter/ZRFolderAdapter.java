package com.zritc.colorfulfund.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zritc.colorfulfund.data.ZRFolder;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹Adapter Created by Nereo on 2015/4/7. Updated by nereo on 2016/1/19.
 */
public class ZRFolderAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;

	private List<ZRFolder> mFolders = new ArrayList<ZRFolder>();

	int mImageSize;

	int lastSelected = 0;

	public ZRFolderAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageSize = mContext.getResources().getDimensionPixelOffset(
				ZRResourceManager.getResourceID("folder_cover_size", "dimen"));
	}

	/**
	 * 设置数据集
	 *
	 * @param folders
	 */
	public void setData(List<ZRFolder> folders) {
		if (folders != null && folders.size() > 0) {
			mFolders = folders;
		} else {
			mFolders.clear();
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mFolders.size() + 1;
	}

	@Override
	public ZRFolder getItem(int i) {
		if (i == 0)
			return null;
		return mFolders.get(i - 1);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		if (view == null) {
			view = mInflater.inflate(ZRResourceManager.getResourceID(
					"list_item_folder", "layout"), viewGroup, false);
			holder = new ViewHolder(view);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		if (holder != null) {
			if (i == 0) {
				holder.name.setText(ZRStrings.get(mContext, "folder_all"));
				holder.path.setText("/sdcard");
				holder.size.setText(String.format("%d%s", getTotalImageSize(),
						ZRStrings.get(mContext, "photo_unit")));
				if (mFolders.size() > 0) {
					ZRFolder f = mFolders.get(0);
					Picasso.with(mContext)
							.load(new File(f.cover.path))
							.error(ZRResourceManager.getResourceID(
									"default_error", "drawable"))
							.resizeDimen(
									ZRResourceManager.getResourceID(
											"folder_cover_size", "dimen"),
									ZRResourceManager.getResourceID(
											"folder_cover_size", "dimen"))
							.centerCrop().into(holder.cover);
				}
			} else {
				holder.bindData(getItem(i));
			}
			if (lastSelected == i) {
				holder.indicator.setVisibility(View.VISIBLE);
			} else {
				holder.indicator.setVisibility(View.INVISIBLE);
			}
		}
		return view;
	}

	private int getTotalImageSize() {
		int result = 0;
		if (mFolders != null && mFolders.size() > 0) {
			for (ZRFolder f : mFolders) {
				result += f.images.size();
			}
		}
		return result;
	}

	public void setSelectIndex(int i) {
		if (lastSelected == i)
			return;

		lastSelected = i;
		notifyDataSetChanged();
	}

	public int getSelectIndex() {
		return lastSelected;
	}

	class ViewHolder {
		ImageView cover;
		TextView name;
		TextView path;
		TextView size;
		ImageView indicator;

		ViewHolder(View view) {
			cover = (ImageView) view.findViewById(ZRResourceManager
					.getResourceID("cover", "id"));
			name = (TextView) view.findViewById(ZRResourceManager
					.getResourceID("name", "id"));
			path = (TextView) view.findViewById(ZRResourceManager
					.getResourceID("path", "id"));
			size = (TextView) view.findViewById(ZRResourceManager
					.getResourceID("size", "id"));
			indicator = (ImageView) view.findViewById(ZRResourceManager
					.getResourceID("indicator", "id"));
			view.setTag(this);
		}

		void bindData(ZRFolder data) {
			if (data == null) {
				return;
			}
			name.setText(data.name);
			path.setText(data.path);
			if (data.images != null) {
				size.setText(String.format("%d%s", data.images.size(),
						ZRStrings.get(mContext, "photo_unit")));
			} else {
				size.setText("*" + ZRStrings.get(mContext, "photo_unit"));
			}
			// 显示图片
			if (data.cover != null) {
				Picasso.with(mContext)
						.load(new File(data.cover.path))
						.placeholder(
								ZRResourceManager.getResourceID(
										"default_error", "drawable"))
						.resizeDimen(
								ZRResourceManager.getResourceID(
										"folder_cover_size", "dimen"),
								ZRResourceManager.getResourceID(
										"folder_cover_size", "dimen"))
						.centerCrop().into(cover);
			} else {
				cover.setImageResource(ZRResourceManager.getResourceID(
						"default_error", "drawable"));
			}
		}
	}

}
