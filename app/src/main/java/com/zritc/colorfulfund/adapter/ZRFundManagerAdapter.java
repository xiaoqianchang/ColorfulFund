package com.zritc.colorfulfund.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.data.model.fund.FundManager;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.widget.TimeLineMarker;

import java.util.List;

/**
 * ZRFundManagerAdapter 基金动态适配器
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-09-22
 * @lastUpdate 2016-09-22
 */
public class ZRFundManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<FundManager> datas;

    //建立枚举4个item类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2,
        ITEM3,
        ITEM4,
        ITEM5,
        ITEM6
    }

    public ZRFundManagerAdapter(Context context, List<FundManager> datas) {
        this.datas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c0, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM2.ordinal()) {
            return new Item2ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c1, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM3.ordinal()) {
            return new Item3ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c2, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM4.ordinal()) {
            return new Item4ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c3, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM5.ordinal()) {
            return new Item5ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c4, parent, false));
        } else
            return new Item6ViewHolder(layoutInflater.inflate(R.layout.cell_fund_news_c5, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item1ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item1ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_reminder);
            ((Item1ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item1ViewHolder) holder).textTitle.setText(datas.get(position).getAdjustTitle());
            ((Item1ViewHolder) holder).textFundName.setText(datas.get(position).getFundName());
            ((Item1ViewHolder) holder).textAdjustUpdate.setText(datas.get(position).getAdjustUpdate());
            if (datas.size() == 0) {
                ((Item1ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item1ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item1ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item1ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
            ((Item1ViewHolder) holder).imgAction.setOnClickListener(v -> {

            });
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item2ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item2ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_paper);
            ((Item2ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item2ViewHolder) holder).textProft.setText(datas.get(position).getCurrentMonthProft());
            ((Item2ViewHolder) holder).textInvestment.setText(datas.get(position).getCurrentMonthInvestment());
            ((Item2ViewHolder) holder).textTrend.setText(datas.get(position).getCurrentTrend());
            if (datas.size() == 0) {
                ((Item2ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item2ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item2ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item2ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
        } else if (holder instanceof Item3ViewHolder) {
            ((Item3ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item3ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item3ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_timeline_ex_fb);
            ((Item3ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item3ViewHolder) holder).textTitle.setText(datas.get(position).getExpertOpinionTitle());
            if (datas.size() == 0) {
                ((Item3ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item3ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item3ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item3ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
        } else if (holder instanceof Item4ViewHolder) {
            ((Item4ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item4ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item4ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_timeline_finish);
            ((Item4ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item4ViewHolder) holder).textWishName.setText(datas.get(position).getEduTitle());
            ((Item4ViewHolder) holder).textWishDate.setText(ZRUtils.formatTime(datas.get(position).getEduDate(), ZRUtils.TIME_FORMAT3));
            if (datas.size() == 0) {
                ((Item4ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item4ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item4ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item4ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
            ((Item4ViewHolder) holder).imgAction.setOnClickListener(v -> {

            });
        } else if (holder instanceof Item5ViewHolder) {
            ((Item5ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item5ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item5ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_reminder);
            ((Item5ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item5ViewHolder) holder).textWishName.setText(datas.get(position).getFundReminderTitle());
            ((Item5ViewHolder) holder).textWishPer.setText(datas.get(position).getFundReminderPer());
            ((Item5ViewHolder) holder).textWishDate.setText(ZRUtils.formatTime(datas.get(position).getFundReminderDate(), ZRUtils.TIME_FORMAT3));
            if (datas.size() == 0) {
                ((Item5ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item5ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item5ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item5ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
            ((Item5ViewHolder) holder).imgAction.setOnClickListener(v -> {

            });
        } else if (holder instanceof Item6ViewHolder) {
            ((Item6ViewHolder) holder).textTimeDd.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[0]);
            ((Item6ViewHolder) holder).textTimeHh.setText(ZRUtils.getTimestampStringArray(datas.get(position).getDate())[1]);
            ((Item6ViewHolder) holder).imgLogo.setImageResource(R.mipmap.icon_timeline_finish);
            ((Item6ViewHolder) holder).textCategory.setText(datas.get(position).getCategoryName());
            ((Item6ViewHolder) holder).textWishName.setText(datas.get(position).getWishTitle());
            ((Item6ViewHolder) holder).textWishDate.setText(ZRUtils.formatTime(datas.get(position).getWishDate(), ZRUtils.TIME_FORMAT3));
            if (datas.size() == 0) {
                ((Item6ViewHolder) holder).timeLineMarker.setBeginLine(null);
                ((Item6ViewHolder) holder).timeLineMarker.setEndLine(null);
            } else if (position == 0) {
                ((Item6ViewHolder) holder).timeLineMarker.setBeginLine(null);
            } else if (position == datas.size()) {
                ((Item6ViewHolder) holder).timeLineMarker.setEndLine(null);
            }
            ((Item6ViewHolder) holder).imgAction.setOnClickListener(v -> {

            });
        }
    }

    //设置ITEM类型
    @Override
    public int getItemViewType(int position) {
        int ordinal = ITEM_TYPE.ITEM1.ordinal();
        switch (datas.get(position).getCategory()) {
            case 0:
                // 调仓提醒
                ordinal = ITEM_TYPE.ITEM1.ordinal();
                break;
            case 1:
                // 投资简报
                ordinal = ITEM_TYPE.ITEM2.ordinal();
                break;
            case 2:
                // 专家意见
                ordinal = ITEM_TYPE.ITEM3.ordinal();
                break;
            case 3:
                // 教育基金完成
                ordinal = ITEM_TYPE.ITEM4.ordinal();
                break;
            case 4:
                // 心愿基金完成
                ordinal = ITEM_TYPE.ITEM5.ordinal();
                break;
            case 5:
                // 定投提醒
                ordinal = ITEM_TYPE.ITEM6.ordinal();
                break;
        }
        return ordinal;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //TODO-调仓提醒的ViewHolder
    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textTitle;
        private TextView textFundName;
        private TextView textAdjustUpdate;
        private ImageView imgAction;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            textFundName = (TextView) itemView.findViewById(R.id.text_fund_name);
            textAdjustUpdate = (TextView) itemView.findViewById(R.id.text_adjust_update);
            imgAction = (ImageView) itemView.findViewById(R.id.img_action);
        }
    }

    //TODO－投资简报的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textProft;
        private TextView textInvestment;
        private TextView textTrend;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textProft = (TextView) itemView.findViewById(R.id.text_proft);
            textInvestment = (TextView) itemView.findViewById(R.id.text_investment);
            textTrend = (TextView) itemView.findViewById(R.id.text_trend);
        }
    }

    //item3 的ViewHolder
    public static class Item3ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textTitle;

        public Item3ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
        }
    }

    //TODO－心愿完成的ViewHolder
    public static class Item4ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textWishName;
        private TextView textWishDate;
        private ImageView imgAction;

        public Item4ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textWishName = (TextView) itemView.findViewById(R.id.text_wish_name);
            textWishDate = (TextView) itemView.findViewById(R.id.text_wish_date);
            imgAction = (ImageView) itemView.findViewById(R.id.img_action);
        }
    }

    //TODO－定投提醒的ViewHolder
    public static class Item5ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textWishName;
        private TextView textWishPer;
        private TextView textWishDate;
        private ImageView imgAction;

        public Item5ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textWishName = (TextView) itemView.findViewById(R.id.text_wish_name);
            textWishPer = (TextView) itemView.findViewById(R.id.text_wish_per);
            textWishDate = (TextView) itemView.findViewById(R.id.text_wish_date);
            imgAction = (ImageView) itemView.findViewById(R.id.img_action);
        }
    }

    //TODO－心愿完成的ViewHolder
    public static class Item6ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTimeDd;
        private TextView textTimeHh;
        private TimeLineMarker timeLineMarker;
        private ImageView imgLogo;
        private TextView textCategory;
        private TextView textWishName;
        private TextView textWishDate;
        private ImageView imgAction;

        public Item6ViewHolder(View itemView) {
            super(itemView);
            timeLineMarker = (TimeLineMarker) itemView.findViewById(R.id.time_line_mark);
            textTimeDd = (TextView) itemView.findViewById(R.id.text_time_dd);
            textTimeHh = (TextView) itemView.findViewById(R.id.text_time_hh);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
            textWishName = (TextView) itemView.findViewById(R.id.text_wish_name);
            textWishDate = (TextView) itemView.findViewById(R.id.text_wish_date);
            imgAction = (ImageView) itemView.findViewById(R.id.img_action);
        }
    }
}
