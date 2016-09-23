package com.zritc.colorfulfund.ui;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.zritc.colorfulfund.utils.ZRResourceManager;

public class ZRListView extends ListView {

    private int mMaxHeight = -1;
    private ScrollView mParentScrollView = null;

    public ZRListView(Context context) {
        this(context, null);
        ViewCompat.setOverScrollMode(this, ViewCompat.OVER_SCROLL_NEVER);
    }

    public ZRListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewCompat.setOverScrollMode(this, ViewCompat.OVER_SCROLL_NEVER);
    }

    public ZRListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCacheColorHint(0x00000000);
        setSelector(ZRResourceManager.getResourceID("transparent", "color"));
        ViewCompat.setOverScrollMode(this, ViewCompat.OVER_SCROLL_NEVER);
    }

    public void setParentScrollView(ScrollView parentScrollView) {
        mParentScrollView = parentScrollView;
    }

    public int getMaxHeight() {
        return mMaxHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMaxHeight > -1) {
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight,
//                    MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setParentScrollAble(false);
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @param flag
     */
    private void setParentScrollAble(boolean flag) {
        if (null == mParentScrollView) {
            return;
        }
        mParentScrollView.requestDisallowInterceptTouchEvent(!flag);
    }

    public void setMaxHeight(int maxHeight) {
        mMaxHeight = maxHeight;
    }

    /**
     * This method should be called before
     * {@link #setAdapter(android.widget.ListAdapter)}
     */
    public void showBottomDivider() {
        View foot = new View(getContext());
        foot.setLayoutParams(new LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1));
        addFooterView(foot);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
