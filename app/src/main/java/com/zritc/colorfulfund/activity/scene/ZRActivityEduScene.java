package com.zritc.colorfulfund.activity.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityGenerateAlbum;
import com.zritc.colorfulfund.activity.fund.ZRActivityMyFundGroupDetail;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.model.edu.GrowingRecord;
import com.zritc.colorfulfund.data.model.edu.UserPoAssetInfo;
import com.zritc.colorfulfund.data.model.file.UploadFile;
import com.zritc.colorfulfund.iView.IEduSceneView;
import com.zritc.colorfulfund.presenter.EduScenePresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshListView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.view.animation.ComposerButtonAnimation;
import com.zritc.colorfulfund.view.animation.ComposerButtonGrowAnimationIn;
import com.zritc.colorfulfund.view.animation.ComposerButtonGrowAnimationOut;
import com.zritc.colorfulfund.view.animation.ComposerButtonShrinkAnimationOut;
import com.zritc.colorfulfund.view.animation.InOutAnimation;
import com.zritc.colorfulfund.view.animation.InOutImageButton;
import com.zritc.colorfulfund.widget.RecordGrowthDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ZRActivityEduScene 教育场景
 *
 * @author gufei
 * @version 1.0
 * @createDate 2016-08-31
 * @lastUpdate 2016-08-31
 */
public class ZRActivityEduScene extends ZRActivityBase<EduScenePresenter> implements IEduSceneView {

    @Bind(R.id.btn_left_back)
    ImageButton btnLeftBack;

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;

    @Bind(R.id.view_shaow)
    View viewShaow;

    @Bind(R.id.composer_buttons_wrapper)
    ViewGroup composerButtonsWrapper;

    @Bind(R.id.composer_buttons_show_hide_button)
    View composerButtonsShowHideButton;

    @Bind(R.id.composer_buttons_show_hide_button_icon)
    ImageView composerButtonsShowHideButtonIcon;

    private Animation rotateStoryAddButtonIn;
    private Animation rotateStoryAddButtonOut;

    private View topView;
    private TextView textTotalAmount;
    private TextView textTotalProfit;
    private TextView textRemainAmount;
    private View eduSceneInfo;
    private View addSceneFirstPhoto;

    private ZRListView listView;
    private ZRCommonAdapter<List<GrowingRecord>> adapter;

    private EduScenePresenter eduScenePresenter;

    private List<List<GrowingRecord>> datas = new ArrayList<>();
    private int pageIndex = 0;
    private boolean hasMoreData = false;
    private String poCode = "";
    private String sceneId; // 场景ID

    /**
     * 目标资产
     */
    public String targetAmount = "";

    /**
     * 当前收益
     */
    public String totalProfit = "";

    /**
     * 距离目标金额
     */
    public double remainAmount;

    private boolean areButtonsShowing;

    @OnClick({R.id.btn_left_back, R.id.view_shaow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left_back:
                onBackPressed();
                break;
            case R.id.view_shaow:
                toggleComposerButtons();
                break;
        }
    }

    /**
     * 执行上拉/下拉刷新操作
     */
    private ZRPullToRefreshBase.OnRefreshListener<ZRListView> onRefreshListener = new ZRPullToRefreshBase.OnRefreshListener<ZRListView>() {
        /**
         * 执行下拉刷新
         *
         * @param refreshView
         */
        @Override
        public void onPullDownToRefresh(
                ZRPullToRefreshBase<ZRListView> refreshView) {
            // 刷新的时候清空列表重新获取第一页数据
            pageIndex = 0;
            eduScenePresenter.getGrowingRecordList4C();
        }

        /**
         * 执行上拉刷新
         *
         * @param refreshView
         */
        @Override
        public void onPullUpToRefresh(
                ZRPullToRefreshBase<ZRListView> refreshView) {
            if (hasMoreData) {
                pageIndex++;
                eduScenePresenter.getGrowingRecordList4C();
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edu_scene;
    }

    @Override
    protected void initPresenter() {
        eduScenePresenter = new EduScenePresenter(this, this);
        eduScenePresenter.init();
        eduScenePresenter.getUserPoAssetInfo4C();
    }

    private void getExtraData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            poCode = bundle.getString("poCode");
        }
    }

    @Override
    public void initView() {

        getExtraData();

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);

        listView = pullToRefreshListView.getRefreshableView();
        listView.setAdapter(adapter = new ZRCommonAdapter<List<GrowingRecord>>(this, datas, R.layout.cell_group_up_scene_item) {
            @Override
            public void convert(int position, ZRViewHolder holder, List<GrowingRecord> item) {
                for (int i = 0; i < ((ViewGroup) holder.getView(R.id.rl_scene_photo_cell)).getChildCount(); i++) {
                    View cell = ((ViewGroup) holder.getView(R.id.rl_scene_photo_cell)).getChildAt(i);
                    if (i < item.size()) {
                        cell.setVisibility(View.VISIBLE);
                        ((TextView) cell.findViewById(R.id.text_title)).setText(item.get(i).growingDesc);
                        ((TextView) cell.findViewById(R.id.text_money)).setText(item.get(i).investmentAmount);
                        ((TextView) cell.findViewById(R.id.text_date)).setText(ZRUtils.formatTime(item.get(i).targetDate, "yy/MM/dd"));
                    } else {
                        cell.setVisibility(View.GONE);
                    }
                }
            }
        });

        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(null);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {

        });
        pullToRefreshListView.setOnRefreshListener(onRefreshListener);

        topView = LayoutInflater.from(mContext).inflate(
                R.layout.view_scene_fund_info_header, null);
        textTotalAmount = (TextView) topView.findViewById(R.id.text_total_amount);
        textTotalProfit = (TextView) topView.findViewById(R.id.text_current_profit);
        textRemainAmount = (TextView) topView.findViewById(R.id.text_target_amount);

        addSceneFirstPhoto = topView.findViewById(R.id.rl_add_scene_first_photo);
        addSceneFirstPhoto.setOnClickListener(v -> {
            openPhotoPicker(this);
        });

        eduSceneInfo = topView.findViewById(R.id.rl_edu_info);
        eduSceneInfo.setOnClickListener(v ->
                {
                    Intent intent = new Intent();
                    intent.setClass(ZRActivityEduScene.this, ZRActivityMyFundGroupDetail.class);
                    intent.putExtra("poCode", poCode);
                    startActivity(intent);
                }
        );

        // 初次进界面给与初始刷新时间，并自动触发下拉刷新请求
        setLastUpdateTime();
        pullToRefreshListView.doPullRefreshing(true, 1000);

        rotateStoryAddButtonIn = AnimationUtils.loadAnimation(this,
                R.anim.rotate_story_add_button_in);
        rotateStoryAddButtonOut = AnimationUtils.loadAnimation(this,
                R.anim.rotate_story_add_button_out);
        // 点击右下角菜单
        composerButtonsShowHideButton.setOnClickListener(v ->
                toggleComposerButtons()
        );

        for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
            InOutImageButton view = (InOutImageButton)composerButtonsWrapper.getChildAt(i);
            view.setOnClickListener(
                    new ComposerLauncher(null, () -> {
                        switch (view.getId()) {
                            case R.id.composer_button_store_money:
                                startActivity(new Intent(this, ZRActivityTargetSetting.class));
                                break;
                            case R.id.composer_button_groupup:
                                openImageSelector();
                                break;
                            case R.id.composer_button_photos:
//                                RecordGrowthDialog recordGrowthDialog = new RecordGrowthDialog(this);
//                                recordGrowthDialog.show();
                                openPhotoPicker(mContext);
                                break;
                        }
                        reshowComposer();
                    }));
        }
        composerButtonsShowHideButton
                .startAnimation(new ComposerButtonGrowAnimationIn(200));

    }

    private void reshowComposer() {
        Animation growIn = new ComposerButtonGrowAnimationIn(300);
        growIn.setInterpolator(new OvershootInterpolator(2.0F));
        this.composerButtonsShowHideButton.startAnimation(growIn);
    }

    private void toggleComposerButtons() {
        if (!areButtonsShowing) {
            ComposerButtonAnimation.startAnimations(
                    this.composerButtonsWrapper, InOutAnimation.Direction.IN);
            this.composerButtonsShowHideButtonIcon.setImageResource(R.mipmap.icon_menu_scene);
            this.composerButtonsShowHideButtonIcon
                    .startAnimation(this.rotateStoryAddButtonIn);
            viewShaow.setVisibility(View.VISIBLE);
        } else {
            ComposerButtonAnimation.startAnimations(
                    this.composerButtonsWrapper, InOutAnimation.Direction.OUT);
            this.composerButtonsShowHideButtonIcon.setImageResource(R.mipmap.icon_menu_scene_normal);
            this.composerButtonsShowHideButtonIcon
                    .startAnimation(this.rotateStoryAddButtonOut);
            viewShaow.setVisibility(View.GONE);
        }
        areButtonsShowing = !areButtonsShowing;
    }



    private class ComposerLauncher implements View.OnClickListener {

        public final Runnable DEFAULT_RUN = new Runnable() {

            @Override
            public void run() {
                ZRActivityEduScene.this
                        .startActivityForResult(
                                new Intent(
                                        ZRActivityEduScene.this,
                                        ZRActivityEduScene.ComposerLauncher.this.cls),
                                1);
            }
        };
        private final Class<? extends Activity> cls;
        private final Runnable runnable;

        private ComposerLauncher(Class<? extends Activity> c, Runnable runnable) {
            this.cls = c;
            this.runnable = runnable;
        }

        @Override
        public void onClick(View paramView) {
            ZRActivityEduScene.this.startComposerButtonClickedAnimations(
                    paramView, runnable);
        }
    }

    private void startComposerButtonClickedAnimations(View view,
                                                      final Runnable runnable) {
        this.areButtonsShowing = false;
        Animation shrinkOut1 = new ComposerButtonShrinkAnimationOut(300);
        Animation shrinkOut2 = new ComposerButtonShrinkAnimationOut(300);
        Animation growOut = new ComposerButtonGrowAnimationOut(300);
        shrinkOut1.setInterpolator(new AnticipateInterpolator(2.0F));
        shrinkOut1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                ZRActivityEduScene.this.composerButtonsShowHideButtonIcon
                        .clearAnimation();
                if (runnable != null) {
                    runnable.run();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
        this.composerButtonsShowHideButton.startAnimation(shrinkOut1);
        for (int i = 0; i < this.composerButtonsWrapper.getChildCount(); i++) {
            final View button = this.composerButtonsWrapper.getChildAt(i);
            if (!(button instanceof InOutImageButton))
                continue;
            if (button.getId() != view.getId())
                // 其他按钮缩小消失
                button.setAnimation(shrinkOut2);
            else {
                // 被点击按钮放大消失
//                button.startAnimation(growOut);
            }
        }
    }

    /**
     * 刷新控件
     */
    private void onLoadComplete() {
        if (pageIndex == 0) {
            if (listView.getHeaderViewsCount() > 0) {
                listView.removeHeaderView(topView);
            }
            listView.addHeaderView(topView);
        }
        adapter.notifyDataSetChanged();
        pullToRefreshListView.onPullUpRefreshComplete();
        pullToRefreshListView.onPullDownRefreshComplete();
        pullToRefreshListView.setHasMoreData(hasMoreData);
        setLastUpdateTime();
    }

    private void setLastUpdateTime() {
        pullToRefreshListView
                .setLastUpdatedLabel(ZRUtils.getCurrentTime("MM-dd HH:mm"));
    }

    @Override
    public void showProgress(CharSequence message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GrowingRecord) {
            if (pageIndex == 0)
                datas.clear();
            GrowingRecord growingRecord = (GrowingRecord) object;
            datas.addAll(growingRecord.growingRecordLists);
            if (datas.size() == 1) {
                GrowingRecord x = growingRecord.growingRecordLists.get(0).get(0);
                if (TextUtils.isEmpty(x.growingDesc) && x.targetDate == 0 && x.investmentAmount.equals("")) {
                    datas.clear();
                }
            }
            addSceneFirstPhoto.setVisibility(datas.size() == 0 ? View.VISIBLE : View.GONE);
            hasMoreData = false;
            onLoadComplete();
        } else if (object instanceof UserPoAssetInfo) {
            UserPoAssetInfo userPoAssetInfo = ((UserPoAssetInfo) object);
            targetAmount = userPoAssetInfo.targetAmount;//目标资产
            totalProfit = userPoAssetInfo.totalProfit;//当前收益
            String totalAmount = userPoAssetInfo.totalAmount;//当前资产
            sceneId = String.valueOf(userPoAssetInfo.sceneId);
            remainAmount = Double.parseDouble(TextUtils.isEmpty(targetAmount) ? "0" : targetAmount) - Double.parseDouble(TextUtils.isEmpty(totalAmount) ? "0" : totalAmount);
            textTotalAmount.setText(ZRUtils.getDecimalFormat(Double.parseDouble(TextUtils.isEmpty(totalAmount) ? "0" : totalAmount) / 10000) + "万元");
            textTotalProfit.setText("¥" + totalProfit + "元");
            textRemainAmount.setText("¥" + ZRUtils.getDecimalFormat(remainAmount / 10000));
        } else if (object instanceof UploadFile) {
            RecordGrowthDialog recordGrowthDialog = new RecordGrowthDialog(this);
            recordGrowthDialog.setImgAvatar(((UploadFile) object).filePath);
            recordGrowthDialog.setPoCode(poCode); // poCode
            recordGrowthDialog.setSceneId(sceneId);
            recordGrowthDialog.show();
        }
    }

    @Override
    public void onError(String msg) {

    }

    /**
     * 从相册获取返回
     *
     * @param path
     */
    @Override
    protected void onGalleryComplete(String path) {
        super.onGalleryComplete(path);
        if (!TextUtils.isEmpty(path)) {
            eduScenePresenter.uploadImage(path);
        }
    }

    /**
     * 从拍照获取返回
     *
     * @param captureFile
     */
    @Override
    protected void onCaptureComplete(File captureFile) {
        super.onCaptureComplete(captureFile);
        if (null != captureFile) {
            eduScenePresenter.uploadFile(captureFile);
        }
    }

    private ArrayList<String> mSelectPath = new ArrayList<String>();

    private void openImageSelector() {
        // 外部图片资源
        ArrayList<String> externalList = new ArrayList<>();
        externalList.add("http://scimg.jb51.net/allimg/160813/103-160Q3143110P5.jpg");
        externalList.add("http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg");
        externalList.add("http://img2.imgtn.bdimg.com/it/u=1509312158,1202655144&fm=21&gp=0.jpg");
        externalList.add("http://pic24.nipic.com/20121029/5056611_120019351000_2.jpg");
        externalList.add("http://www.pptbz.com/pptpic/uploadfiles_6909/201202/2012022917310499.jpg");
        externalList.add("http://pic14.nipic.com/20110610/7181928_110502231129_2.jpg");
        externalList.add("http://img.taopic.com/uploads/allimg/120423/107913-12042323220753.jpg");
        externalList.add("http://pic51.nipic.com/file/20141016/24066_130156779281_2.jpg");
        externalList.add("http://www.xxjxsj.cn/article/uploadpic/2012-4/201241221251481736.jpg");
        externalList.add("http://pic4.nipic.com/20090910/2302530_144753008092_2.jpg");
        externalList.add("http://img102.mypsd.com.cn/20120929/1/Mypsd_13953_201209291653040031B.jpg");
        Intent intent = new Intent();
        int selectedMode = ZRActivityGenerateAlbum.MODE_MULTI;
        int maxNum = externalList.size(); // 最大可选择图片的数量
        intent.setClass(mContext, ZRActivityGenerateAlbum.class);
        // 是否显示拍摄图片
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SHOW_CAMERA, false);
        // 最大可选择图片数量
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SELECT_COUNT,
                maxNum);
        // 选择模式
        intent.putExtra(
                ZRActivityGenerateAlbum.EXTRA_SELECT_MODE,
                selectedMode);
        // 默认选择
        if (mSelectPath != null && mSelectPath.size() > 0) {
            intent.putExtra(
                    ZRActivityGenerateAlbum.EXTRA_DEFAULT_SELECTED_LIST,
                    mSelectPath);
        }
        //        intent.putExtra(
        //                ZRActivityGenerateAlbum.EXTRA_EXTERNAL_LIST,
        //                externalList);
        startActivityForResult(intent,
                ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE:
                    mSelectPath = data
                            .getStringArrayListExtra(ZRActivityGenerateAlbum.EXTRA_RESULT);
                    break;
            }
        }
    }*/

}
