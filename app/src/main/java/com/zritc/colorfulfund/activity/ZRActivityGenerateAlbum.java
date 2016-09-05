package com.zritc.colorfulfund.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.base.ZRActivityBase;
import com.zritc.colorfulfund.iView.IGenerateAlbumView;
import com.zritc.colorfulfund.presenter.GenerateAlbumPresenter;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import java.io.File;
import java.util.ArrayList;

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

    /** 单选 */
    public static final int MODE_SINGLE = 0;
    /** 多选 */
    public static final int MODE_MULTI = 1;

    private ArrayList<String> resultList = new ArrayList<String>();
    private TextView mTextNext;
    private int mDefaultCount;

    private GenerateAlbumPresenter presenter;

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
        Intent intent = getIntent();
        mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, 9);
        int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            resultList = intent
                    .getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(ZRFragmentMultiImageSelector.EXTRA_SELECT_COUNT,
                mDefaultCount);
        bundle.putInt(ZRFragmentMultiImageSelector.EXTRA_SELECT_MODE, mode);
        bundle.putBoolean(ZRFragmentMultiImageSelector.EXTRA_SHOW_CAMERA, isShow);
        bundle.putStringArrayList(
                ZRFragmentMultiImageSelector.EXTRA_DEFAULT_SELECTED_LIST,
                resultList);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_grid,
                        Fragment.instantiate(this,
                                ZRFragmentMultiImageSelector.class.getName(),
                                bundle)).commit();

        // 完成按钮
        mTextNext = (TextView) findViewById(ZRResourceManager.getResourceID(
                "text_next", "id"));
        // if (resultList == null || resultList.size() <= 0) {
        // mSubmitButton.setText(R.string.action_done);
        // mSubmitButton.setEnabled(false);
        // } else {
        // updateDoneText();
        // mSubmitButton.setEnabled(true);
        // }
        mTextNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultList != null && resultList.size() > 0) {
                    // 返回已选择的图片数据
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, resultList);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_STYLE_CHOOSE_RESULT:
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

    }

    @Override
    public void onError(String msg) {

    }

    private void updateDoneText() {
        // mSubmitButton.setText(String.format("%s(%d/%d)",
        // getString(R.string.action_done), resultList.size(),
        // mDefaultCount));
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
