package com.zritc.colorfulfund.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * CommonAdapter
 * 
 * @author eric
 * @param <T>
 * @version 1.0
 * @createDate 2014-01-15
 * @lastUpdate 2014-01-15
 */
public abstract class ZRCommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public ZRCommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return null == mDatas? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    public void setData(List<T> list) {
        mDatas = null;
        mDatas = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ZRViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(position, viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(int position, ZRViewHolder helper, T item);

    private ZRViewHolder getViewHolder(int position, View convertView,
            ViewGroup parent) {
        return ZRViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

}
