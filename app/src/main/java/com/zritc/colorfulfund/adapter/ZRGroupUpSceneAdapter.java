package com.zritc.colorfulfund.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.scene.ZRActivityEduScene;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemTypeSupport;
import com.zritc.colorfulfund.ui.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by gufei on 16/9/2.
 */
public class ZRGroupUpSceneAdapter extends MultiItemCommonAdapter<ZRActivityEduScene.EduScene> {

    public ZRGroupUpSceneAdapter(Context context, List<ZRActivityEduScene.EduScene> datas, MultiItemTypeSupport multiItemTypeSupport) {
        super(context, datas, multiItemTypeSupport);
    }

    @Override
    protected void convert(ViewHolder holder, ZRActivityEduScene.EduScene eduScene) {
        ((TextView)holder.getView(R.id.text_title)).setText(eduScene.getTitle());
        ((TextView)holder.getView(R.id.text_money)).setText(eduScene.getMoney());
        ((TextView)holder.getView(R.id.text_date)).setText(eduScene.getDate());
    }
}
