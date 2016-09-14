package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.data.response.edu.GetGrowingPicList4C;
import com.zritc.colorfulfund.iView.IGenerateAlbumView;
import com.zritc.colorfulfund.presenter.GenerateAlbumPresenter;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 成长相册
 *
 * Created by Chang.Xiao on 2016/9/2.
 *
 * @version 1.0
 */
public class ZRActivityGenerateAlbum extends ZRActivityToolBar<GenerateAlbumPresenter> implements IGenerateAlbumView, ZRFragmentMultiImageSelector.Callback {

    private static final int REQUEST_CODE_STYLE_CHOOSE_RESULT = 0x110;

    /** 最大图片选择次数，int类型，默认9 */
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    /** 图片选择模式，默认多选 */
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    /** 是否显示相机，默认显示 */
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    /** 选择结果，返回为 ArrayList&lt;String&gt; 图片路径集合 */
    public static final String EXTRA_RESULT = "select_result";
    /** 默认选择集 */
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    /** 适配器数据来自手机内存 */
    public static final String EXTRA_EXTERNAL_LIST = "external_list";
    /** 外部数据 */
    public static final String EXTRA_DATA_FROM_MOBILE = "data_from_mobile";

    /** 单选 */
    public static final int MODE_SINGLE = 0;
    /** 多选 */
    public static final int MODE_MULTI = 1;

    @Bind(R.id.btn_generate_preview)
    Button btnGeneratePreview;

    @Bind(R.id.ll_album_bg)
    LinearLayout llAlbumBg;

    // 外部数据源
    private List<String> externalList = new ArrayList<String>();
    private ArrayList<String> resultList = new ArrayList<String>();
    private TextView mTextNext;
    private int mDefaultCount; // 默认最大可选择图片的数量

    private GenerateAlbumPresenter presenter;
    private ZRFragmentMultiImageSelector multiImageSelector;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_generate_album;
    }

    @Override
    protected void initPresenter() {
        presenter = new GenerateAlbumPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        setTitleText("相片选择");
        presenter.doGrowingPicList();

        Intent intent = getIntent();
        mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, 9);
        int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            resultList = intent
                    .getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        }
//        if (intent.hasExtra(EXTRA_EXTERNAL_LIST)) {
//            externalList = intent.getStringArrayListExtra(EXTRA_EXTERNAL_LIST);
//        }

        Bundle bundle = new Bundle();
        bundle.putInt(ZRFragmentMultiImageSelector.EXTRA_SELECT_COUNT,
                mDefaultCount);
        bundle.putInt(ZRFragmentMultiImageSelector.EXTRA_SELECT_MODE, mode);
        bundle.putBoolean(ZRFragmentMultiImageSelector.EXTRA_SHOW_CAMERA, isShow);
        bundle.putStringArrayList(
                ZRFragmentMultiImageSelector.EXTRA_DEFAULT_SELECTED_LIST,
                resultList);
        bundle.putBoolean(ZRFragmentMultiImageSelector.EXTRA_DATA_FROM_MOBILE, false);
//        bundle.putStringArrayList(
//                ZRFragmentMultiImageSelector.EXTRA_EXTERNAL_LIST,
//                externalList);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_grid,
                        multiImageSelector = (ZRFragmentMultiImageSelector) Fragment.instantiate(this,
                                ZRFragmentMultiImageSelector.class.getName(),
                                bundle)).commit();

        if (externalList.size() > 0) {
            setTitleBarRightImageAndListener(0, "全选", v -> {
                multiImageSelector.setSelectAll();
                /*if (resultList != null && resultList.size() > 0) {
                    // 返回已选择的图片数据
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, resultList);
                    setResult(RESULT_OK, data);
                    finish();
                }*/
            });
            mNavRightText.setTextColor(getResources().getColor(R.color.album_select_all_normal));
            llAlbumBg.setVisibility(View.GONE);
        } else {
            llAlbumBg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_STYLE_CHOOSE_RESULT:
                break;
        }
    }

    @OnClick({R.id.btn_generate_preview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generate_preview:
                // 生成预览
                break;
        }
    }

    @Override
    public void showProgress(CharSequence message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Object object) {
        if (object instanceof GenerateAlbumPresenter) {
            presenter = (GenerateAlbumPresenter) object;
            // 相片列表
            externalList = presenter.photoUrls;
            multiImageSelector.setExternalList(externalList);
        }
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    private void updateDoneText() {
        btnGeneratePreview.setEnabled(resultList.size() > 0);
        // 设置全选按钮的颜色
        if (resultList.size() == mDefaultCount) {
            // 全选
            mNavRightText.setTextColor(getResources().getColor(R.color.album_select_all_checked));
            mNavRightText.setEnabled(false);
        } else {
            mNavRightText.setTextColor(getResources().getColor(R.color.album_select_all_normal));
            mNavRightText.setEnabled(true);
        }
        Log.e("xc", "---" + resultList.size());
    }

    @Override
    public void onSingleImageSelected(String path) {
        Intent data = new Intent();
        resultList.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, resultList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onImageSelected(String path) {
        if (!resultList.contains(path)) {
            resultList.add(path);
        }
        // 有图片之后，改变按钮状态
        if (resultList.size() > 0) {
            updateDoneText();
        }
    }

    @Override
    public void onImageUnselected(String path) {
        if (resultList.contains(path)) {
            resultList.remove(path);
        }
        updateDoneText();
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            // notify system
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(imageFile)));
            Intent data = new Intent();
            resultList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(EXTRA_RESULT, resultList);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
