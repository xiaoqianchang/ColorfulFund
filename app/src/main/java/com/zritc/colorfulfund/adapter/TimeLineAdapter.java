package com.zritc.colorfulfund.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.widget.TimeLineMarker;

import java.util.List;

/**
 * Created by Chang.Xiao on 2016/3/19 22:23.
 *
 * @version 1.0
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public TimeLineAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    /**
     * 得到View的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int size = mDatas.size() - 1;
        if (size == 0) {
            return ItemType.ATOM.getValue();
        } else if (position == 0) {
            return ItemType.START.getValue();
        } else if (position == size) {
            return ItemType.END.getValue();
        } else {
            return ItemType.NORMAL.getValue();
        }
    }

    /**
     * 创建、渲染视图并初始化
     * @param parent
     * @param viewType   ListView的每一个条目布局唯一，然而Recycler的每一个条目可以有不同布局
     * @return
     */
    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.time_line_item, null);
        return new TimeLineViewHolder(view, viewType);
    }

    /**
     * 给渲染的视图绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        String timeLineModel = mDatas.get(position);
        holder.setData(timeLineModel);
    }

    /**
     * 得到条目的数量
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 这个ViewHolder相当于ListView的ViewHolder
    class TimeLineViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public TimeLineViewHolder(View itemView, int type) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_time_line_txt);
            TimeLineMarker timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.item_time_line_mark);
            if (type == ItemType.ATOM.getValue()) {
                timeLineMarker.setBeginLine(null);
                timeLineMarker.setEndLine(null);
            } else if (type == ItemType.START.getValue()) {
                timeLineMarker.setBeginLine(null);
            } else if (type == ItemType.END.getValue()) {
                timeLineMarker.setEndLine(null);
            }

            // 动态设置marker的颜色(drawable的shap)
            timeLineMarker.setMarkerSize(60);
            timeLineMarker.setMarkerDrawable(itemView.getContext().getResources().getDrawable(R.mipmap.icon_weixin));
        }

        public void setData(String timeLineModel) {
            name.setText(timeLineModel);
        }
    }

    /**
     * Created by Chang.Xiao on 2016/3/19 22:59.
     *
     * @version 1.0
     */
    public enum ItemType {
        // 利用构造函数传参
        NORMAL(0),
        START(1),
        END(2),
        ATOM(3); // 只有一条数据---前后都没有轴

        // 定义私有变量
        private final int mCode;

        // 构造函数，枚举类型只能为私有
        private ItemType(int _nCode) {
            this.mCode = _nCode;
        }

        public int getValue() {
            return mCode;
        }

        @Override
        public String toString() {
            return String.valueOf(mCode);
        }
    }
}
