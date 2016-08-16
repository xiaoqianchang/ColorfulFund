package com.zritc.colorfulfund.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * ViewHolder
 * 
 * @author eric
 * @version 1.0
 * @createDate 2014-01-15
 * @lastUpdate 2014-01-15
 */
public class ZRViewHolder {
	private Context mContext;
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private ZRViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		mContext = context;
		mPosition = position;
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		// setTag
		mConvertView.setTag(this);
	}

	/**
	 * 拿到一个ViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ZRViewHolder get(Context context, View convertView,
								   ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ZRViewHolder(context, parent, layoutId, position);
		}
		return (ZRViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ZRViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	public ZRViewHolder setTextColor(int viewId, int resid) {
		TextView view = getView(viewId);
		view.setTextColor(resid);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ZRViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * @param viewId
	 * @param url
     * @return
     */
	public ZRViewHolder setImageByUrl(int viewId, String url) {
		ImageLoader.getInstance()
				.displayImage(url, (ImageView) getView(viewId));
		return this;
	}

	public ZRViewHolder setButtonBackground(int viewId, int resourceId) {
		Button button = getView(viewId);
		button.setBackgroundResource(resourceId);
		return this;
	}

	public ZRViewHolder setButtonText(int viewId, int resourceId) {
		Button button = getView(viewId);
		button.setText(resourceId);
		return this;
	}

	public ZRViewHolder setButtonTextColor(int viewId, int resourceId) {
		Button button = getView(viewId);
		button.setTextColor(resourceId);
		return this;
	}

	public ZRViewHolder setCheckBox(int viewId, boolean isChecked) {
		CheckBox checkBox = getView(viewId);
		checkBox.setChecked(isChecked);
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

}
