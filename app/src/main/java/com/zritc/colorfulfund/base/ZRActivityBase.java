package com.zritc.colorfulfund.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zritc.colorfulfund.R;
import com.zritc.colorfulfund.common.ZRAppActivityManager;
import com.zritc.colorfulfund.presenter.BasePresenter;
import com.zritc.colorfulfund.ui.ZRDialog;
import com.zritc.colorfulfund.ui.ZRTextView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRFileUtils;
import com.zritc.colorfulfund.utils.ZRPhotoPicker;
import com.zritc.colorfulfund.utils.ZRPopupUtil;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import java.io.File;

import butterknife.ButterKnife;


/**
 * 基类FragmentActivity，抽取通用的方法
 * <p>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public abstract class ZRActivityBase<T extends BasePresenter> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected T mPresenter;
    protected Context mContext;
    protected Activity mActivity;
    private Dialog mDialog;

    protected InputMethodManager mManager;
    protected File captureFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        initPresenter();
        ZRAppActivityManager.getAppManager().addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
        mManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected abstract int getContentViewId();

    protected abstract void initPresenter();

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void showLoadingDialog(CharSequence message) {
        showLoadingDialog(null, message);
    }

    protected void showLoadingDialog(CharSequence title, CharSequence message) {
        hideLoadingDialog();
        if (!isFinishing()) {
            mDialog = new ZRDialog(mContext, message, false, false);
            mDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    protected void showToast(String msg) {
        if (!TextUtils.isEmpty(msg) && !isFinishing())
            ZRToastFactory.getToast(this, msg).show();
    }

    protected void cancelToast() {
        ZRToastFactory.cancelToast();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    protected void hideSoftInput(IBinder token) {
        if (token != null) {
            mManager.hideSoftInputFromWindow(token, 0);
            hideSoftInputAction();
        }
    }

    protected void hideSoftInputAction() {

    }

    /**
     * 打开图片获取源选择框
     *
     * @param context
     */
    protected void openPhotoPicker(Context context) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(
                ZRResourceManager.getResourceID("view_popupwindows", "layout"),
                null, false);
        ZRTextView gallery = (ZRTextView) view.findViewById(ZRResourceManager
                .getResourceID("item_popupwindows_photo", "id"));
        ZRTextView capture = (ZRTextView) view.findViewById(ZRResourceManager
                .getResourceID("item_popupwindows_camera", "id"));
        ZRTextView cancel = (ZRTextView) view.findViewById(ZRResourceManager
                .getResourceID("item_popupwindows_cancel", "id"));

        gallery.setOnClickListener(mOnPhotoPickerClickListener);
        capture.setOnClickListener(mOnPhotoPickerClickListener);
        cancel.setOnClickListener(mOnPhotoPickerClickListener);

        mDialog = ZRPopupUtil.makePopup(context, view);
        mDialog.show();
    }

    private View.OnClickListener mOnPhotoPickerClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (mDialog != null) {
                mDialog.dismiss();
            }
            if (view.getId() == ZRResourceManager.getResourceID(
                    "item_popupwindows_photo", "id")) {
                // if (android.os.Build.VERSION.SDK_INT >=
                // android.os.Build.VERSION_CODES.KITKAT) {
                // ZRPhotoPicker.launchGallery(ZRActivityBase.this,
                // ZRConstant.ACTIVITY_REQUEST_SELECT_PIC_KITKAT);
                // } else {
                ZRPhotoPicker.launchGallery(ZRActivityBase.this,
                        ZRConstant.ACTIVITY_REQUEST_SEL_PICTURE);
                // }
            } else if (view.getId() == ZRResourceManager.getResourceID(
                    "item_popupwindows_camera", "id")) {
                captureFile = ZRFileUtils.getCaptureFile();
                ZRPhotoPicker.launchCamera(ZRActivityBase.this,
                        ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE, captureFile);
            }
        }

    };

    /**
     * 接收获取到的图片资源
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ZRConstant.ACTIVITY_REQUEST_SEL_PICTURE:
                    if (data != null) {
                        String path = ZRPhotoPicker.getPhotoPathByLocalUri(this,
                                data);
                        onGalleryComplete(path);
                    }
                    break;
                case ZRConstant.ACTIVITY_REQUEST_SELECT_PIC_KITKAT:
                    if (data != null) {
                        String path = ZRPhotoPicker.getPath(this, data.getData());
                        onGalleryComplete(path);
                    }
                    break;
                case ZRConstant.ACTIVITY_REQUEST_TAKE_PICTURE:
                    onCaptureComplete(captureFile);
                    break;
                case ZRConstant.ACTIVITY_REQUEST_CROP:
                    if (data != null) {
                        Bitmap bitmap = data.getParcelableExtra("data");
                        onCropComplete(bitmap);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 从相册获取返回
     *
     * @param path
     */
    protected void onGalleryComplete(String path) {

    }

    /**
     * 从拍照获取返回
     *
     * @param captureFile
     */
    protected void onCaptureComplete(File captureFile) {

    }

    protected void onCropComplete(Bitmap bitmap) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        ButterKnife.unbind(this);
        ZRAppActivityManager.getAppManager().finishActivity(this);
        // 取消网络请求
    }

}
