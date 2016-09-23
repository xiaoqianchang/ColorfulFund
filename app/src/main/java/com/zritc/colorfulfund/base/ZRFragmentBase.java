package com.zritc.colorfulfund.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.zritc.colorfulfund.presenter.BasePresenter;
import com.zritc.colorfulfund.ui.ZRDialog;
import com.zritc.colorfulfund.ui.ZRDialog.DialogListener;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import butterknife.ButterKnife;

/**
 * The base fragment in application.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2015-10-16
 * @lastUpdate 2015-10-16
 */
public abstract class ZRFragmentBase<T extends BasePresenter> extends Fragment {

    protected T mPresenter;
    protected Context mContext;
    protected Activity mActivity;
    protected View mMainView;
    private Dialog mDialog;
    private int mDialogAction;
    protected InputMethodManager mManager;

    /**
     * Dialog监听器
     */
    private DialogListener mDialogListener = new DialogListener() {

        @Override
        public void onConfirm() {
            mDialog.dismiss();
            onAlertConfirmed(mDialogAction);
        }

        @Override
        public void onCancel() {
            mDialog.dismiss();
            onAlertCanceled(mDialogAction);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity();
        mActivity = getActivity();
        mManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtraArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(getContentViewId(),
                container, false);
        ButterKnife.bind(this, mMainView);
        initPresenter();
        return mMainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);//解绑
//		 cancelToast();
        hideDialog();
        super.onDestroyView();
    }

    protected abstract void getExtraArguments();

    protected abstract int getContentViewId();

    protected abstract void initPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void showLoadingDialog(CharSequence message) {
        showLoadingDialog(null, message);
    }

    protected void showLoadingDialog(CharSequence title, CharSequence message) {
        hideDialog();
        if (!getActivity().isFinishing()) {
            mDialog = new ZRDialog(getActivity(), message, false, false);
            mDialog.show();
        }
    }

    protected void showAlertDialog(int action, CharSequence message) {
        showAlertDialog(action, message,
                ZRStrings.get(getActivity(), "btn_ok"),
                ZRStrings.get(getActivity(), "btn_cancel"));
    }

    protected void showAlertDialog(int action, CharSequence message,
                                   CharSequence confirm, CharSequence cancel) {
        showAlertDialog(action, null, message, confirm, cancel);
    }

    protected void showAlertDialog(int action, CharSequence title,
                                   CharSequence message, CharSequence confirm, CharSequence cancel) {
        showAlertDialog(action, title, message, confirm, cancel,
                ZRDialog.STYLE_NORMAL);
    }

    protected void showAlertDialog(int action, CharSequence title,
                                   CharSequence message, CharSequence confirm, CharSequence cancel,
                                   int style) {
        showAlertDialog(action, title, message, confirm, cancel, style, -1,
                false, false);
    }

    protected void showAlertDialog(int action, CharSequence title,
                                   CharSequence message, CharSequence confirm, CharSequence cancel,
                                   int style, boolean setCanceledOnTouchOutside, boolean setCancelable) {
        showAlertDialog(action, title, message, confirm, cancel, style, -1,
                setCanceledOnTouchOutside, setCancelable);
    }

    protected void showAlertDialog(int action, CharSequence title,
                                   CharSequence message, CharSequence confirm, CharSequence cancel,
                                   int style, int singleBtnTextStyle,
                                   boolean setCanceledOnTouchOutside, boolean setCancelable) {
        hideDialog();
        mDialogAction = action;
        mDialog = new ZRDialog(getActivity(), title, message, confirm, cancel,
                mDialogListener, style, singleBtnTextStyle,
                setCanceledOnTouchOutside, setCancelable);
        mDialog.show();
    }

    protected void hideDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void showToastSpecial(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(msg) && !getActivity().isFinishing())
                    ZRToastFactory.getLongToast(
                            getActivity().getApplicationContext(), msg).show();
            }
        });
    }

    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(msg) && !getActivity().isFinishing())
                    ZRToastFactory.getToast(
                            getActivity().getApplicationContext(), msg).show();
            }
        });
    }

    public void cancelToast() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ZRToastFactory.cancelToast();
            }
        });

    }


    protected void startShareEntryActivity(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(
                ZRResourceManager.getResourceID("anim_still", "anim"),
                ZRResourceManager.getResourceID("anim_still", "anim"));
    }

    protected void startMenuActivity(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(
                ZRResourceManager.getResourceID("slide_right_in", "anim"),
                ZRResourceManager.getResourceID("anim_still", "anim"));
    }

    protected void startMenuActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(
                ZRResourceManager.getResourceID("slide_right_in", "anim"),
                ZRResourceManager.getResourceID("anim_still", "anim"));
    }

    protected void startLoginActivity(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(
                ZRResourceManager.getResourceID("push_up_in", "anim"),
                ZRResourceManager.getResourceID("anim_still", "anim"));
    }

    protected void startLoginActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(
                ZRResourceManager.getResourceID("push_up_in", "anim"),
                ZRResourceManager.getResourceID("anim_still", "anim"));
    }

    /**
     * When the confirm button in dialog is clicked, this function will be
     * called.
     *
     * @param action
     */
    protected void onAlertConfirmed(int action) {
    }

    /**
     * When the cancel button in dialog is clicked, this function will be
     * called.
     *
     * @param action
     */
    protected void onAlertCanceled(int action) {
    }
}
