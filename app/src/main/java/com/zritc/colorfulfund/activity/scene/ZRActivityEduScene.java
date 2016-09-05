package com.zritc.colorfulfund.activity.scene;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.activity.ZRActivityToolBar;
import com.zritc.colorfulfund.adapter.ZRGroupUpSceneAdapter;
import com.zritc.colorfulfund.iView.IEduSceneView;
import com.zritc.colorfulfund.presenter.EduScenePresenter;
import com.zritc.colorfulfund.ui.ZRListView;
import com.zritc.colorfulfund.ui.adapter.ZRCommonAdapter;
import com.zritc.colorfulfund.ui.adapter.ZRViewHolder;
import com.zritc.colorfulfund.ui.adapter.abslistview.MultiItemTypeSupport;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshBase;
import com.zritc.colorfulfund.ui.pull2refresh.ZRPullToRefreshListView;
import com.zritc.colorfulfund.utils.ZRUtils;
import com.zritc.colorfulfund.view.animation.ComposerButtonAnimation;
import com.zritc.colorfulfund.view.animation.ComposerButtonGrowAnimationIn;
import com.zritc.colorfulfund.view.animation.ComposerButtonGrowAnimationOut;
import com.zritc.colorfulfund.view.animation.ComposerButtonShrinkAnimationOut;
import com.zritc.colorfulfund.view.animation.InOutAnimation;
import com.zritc.colorfulfund.view.animation.InOutImageButton;

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
public class ZRActivityEduScene extends ZRActivityToolBar<EduScenePresenter> implements IEduSceneView {

    @Bind(R.id.btn_left_back)
    ImageButton btnLeftBack;

    @Bind(R.id.pull_to_refresh_list_view)
    ZRPullToRefreshListView pullToRefreshListView;
    private ZRListView listView;
    private ZRGroupUpSceneAdapter adapter;
    private ZRCommonAdapter<EduScene> adapter1;

    private EduScenePresenter eduScenePresenter;

    private List<EduScene> datas = new ArrayList<>();
    private int pageIndex = 0;
    private boolean hasMoreData = false;


    private boolean areButtonsShowing;
    private ViewGroup composerButtonsWrapper;
    private View composerButtonsShowHideButtonIcon;
    private View composerButtonsShowHideButton;
    private Animation rotateStoryAddButtonIn;
    private Animation rotateStoryAddButtonOut;

    @OnClick({R.id.btn_left_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left_back:
                onBackPressed();
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
                pageIndex--;

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
    }

    private View topView;

    @Override
    public void initView() {
        showToolBar(View.GONE);
        // TestDatas start
        datas.add(new EduScene("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "16/01/20", "¥5000", "1岁时的样子"));
        datas.add(new EduScene("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "15/02/11", "¥3000", "半岁时的样子"));
        datas.add(new EduScene("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "16/01/20", "¥5000", "1岁时的样子"));
        datas.add(new EduScene("http://b.hiphotos.baidu.com/baike/whfpf%3D268%2C152%2C50/sign=eb739058fad3572c66b7cf9cec2e5111/50da81cb39dbb6fdb3c422970124ab18962b37e0.jpg", "15/02/11", "¥3000", "半岁时的样子"));
        // TestDatas end

        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);

        listView = pullToRefreshListView.getRefreshableView();
//      listView.setAdapter(adapter = new ZRGroupUpSceneAdapter(this, datas, multiItemTypeSupport));
        listView.setAdapter(adapter1 = new ZRCommonAdapter<EduScene>(this, datas, R.layout.cell_group_up_scene_item) {
            @Override
            public void convert(int position, ZRViewHolder helper, EduScene item) {

            }
        });

        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(null);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent();
//            if (TextUtils.isEmpty(datas.get(position).getDuring())) {
//                intent.setClass(getActivity(), ZRActivityArticleDetails.class);
//            } else {
//                intent.setClass(getActivity(), ZRActivityVideoDetails.class);
//            }
//            startActivity(intent);
        });
        pullToRefreshListView.setOnRefreshListener(onRefreshListener);

        topView = LayoutInflater.from(mContext).inflate(
                R.layout.view_scene_fund_info_header, null);

        // 初次进界面给与初始刷新时间，并自动触发下拉刷新请求
        setLastUpdateTime();

        // Test code
        onLoadComplete();

        composerButtonsWrapper = (ViewGroup) findViewById(R.id.composer_buttons_wrapper);
        composerButtonsShowHideButton = findViewById(R.id.composer_buttons_show_hide_button);
        composerButtonsShowHideButtonIcon = findViewById(R.id.composer_buttons_show_hide_button_icon);
        rotateStoryAddButtonIn = AnimationUtils.loadAnimation(this,
                R.anim.rotate_story_add_button_in);
        rotateStoryAddButtonOut = AnimationUtils.loadAnimation(this,
                R.anim.rotate_story_add_button_out);
        //
        composerButtonsShowHideButton.setOnClickListener(v ->
                toggleComposerButtons()
        );
        for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
            composerButtonsWrapper.getChildAt(i).setOnClickListener(
                    new ComposerLauncher(null, new Runnable() {

                        @Override
                        public void run() {
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(400);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    reshowComposer();
                                }
                            }).start();
                        }
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
            this.composerButtonsShowHideButtonIcon
                    .startAnimation(this.rotateStoryAddButtonIn);
        } else {
            ComposerButtonAnimation.startAnimations(
                    this.composerButtonsWrapper, InOutAnimation.Direction.OUT);
            this.composerButtonsShowHideButtonIcon
                    .startAnimation(this.rotateStoryAddButtonOut);
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
                button.startAnimation(growOut);
            }
        }
    }

    /**
     * 对个item类型
     */
    MultiItemTypeSupport multiItemTypeSupport = new MultiItemTypeSupport() {

        @Override
        public int getLayoutId(int position, Object o) {
            if (position == 0) {
                return R.layout.cell_group_up_scene_item1;
            } else if (position == 1) {
                return R.layout.cell_group_up_scene_item2;
            } else if (position == 2) {
                return R.layout.cell_group_up_scene_item3;
            } else {
                return R.layout.cell_group_up_scene_item4;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position, Object o) {
            if (position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else if (position == 2) {
                return 2;
            } else {
                return 3;
            }
        }
    };

    /**
     * 刷新控件
     */
    private void onLoadComplete() {
//        if (pageIndex == 0)
//            datas.clear();
        if (pageIndex == 0) {
            if (listView.getHeaderViewsCount() > 0) {
                listView.removeHeaderView(topView);
            }
            listView.addHeaderView(topView);
        }
//        adapter1.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(String msg) {

    }

    public class EduScene {
        private String title;
        private String money;
        private String date;
        private String album;
        private List<EduScene> datas = new ArrayList<>();

        public EduScene(String album, String date, String money, String title) {
            this.album = album;
            this.date = date;
            this.money = money;
            this.title = title;
        }

        public String getAlbum() {
            return album;
        }

        public String getDate() {
            return date;
        }

        public String getMoney() {
            return money;
        }

        public String getTitle() {
            return title;
        }
    }

}
