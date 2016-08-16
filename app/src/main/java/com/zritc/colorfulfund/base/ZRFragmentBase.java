package com.zritc.colorfulfund.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.zritc.colorfulfund.io.ZRFileDownloadTask;
import com.zritc.colorfulfund.io.ZRFileDownloadTask.TFDownloadProgressCallback;
import com.zritc.colorfulfund.io.ZRHttpTask;
import com.zritc.colorfulfund.io.ZRRequestID;
import com.zritc.colorfulfund.io.ZRTaskCallback;
import com.zritc.colorfulfund.io.ZRTaskDelegate;
import com.zritc.colorfulfund.ui.ZRDialog;
import com.zritc.colorfulfund.ui.ZRDialog.DialogListener;
import com.zritc.colorfulfund.ui.ZRTextView;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRErrors;
import com.zritc.colorfulfund.utils.ZRResourceManager;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;
import com.zritc.colorfulfund.utils.ZRStrings;
import com.zritc.colorfulfund.utils.ZRToastFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * The base fragment in application.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2015-10-16
 * @lastUpdate 2015-10-16
 */
public abstract class ZRFragmentBase extends Fragment {

	protected static Context mContext;
	private Dialog mDialog;
	private View mNullView;
	private View mEmptyView;
	private View mLoadingView;
	private View mContentView;
	private View mLoadFailView;
	private View mLoadingContainer;
	private ZRTextView mEmptyTip;
	private ImageView mEmptyImage;
	private ZRTextView mLoadingTip;
	private ZRTextView mLoadingFailTip;
	private int mDialogAction;
	protected boolean isVisible;
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

	/**
	 * 网络请求callback
	 */
	protected ZRTaskCallback mTaskCallback = new ZRTaskCallback() {

		@Override
		public void onResult(ZRRequestID requestID, String result) {
			if (!getActivity().isFinishing()) {
				ZRFragmentBase.this.onResult(requestID, result);
			}
		}

		@Override
		public void onError(ZRRequestID requestID, String errorCode,
				String errorDesc) {
			if (!getActivity().isFinishing()) {
				if (TextUtils.isEmpty(errorDesc)) {
					ZRFragmentBase.this.onError(requestID, errorCode);
				} else {
					ZRFragmentBase.this
							.onError(requestID, errorCode, errorDesc);
				}
			}
		}
	};

	protected TFDownloadProgressCallback mDownloadProgressCallback = new TFDownloadProgressCallback() {

		@Override
		public void onDownloadProgress(ZRRequestID requestID, int progress) {
			if (!getActivity().isFinishing()) {
				ZRFragmentBase.this.onDownloadProgress(requestID, progress);
			}
		}
	};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = getActivity();
		mManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		// cancelToast();
		hideDialog();
		if (Util.isOnMainThread()) {
			Glide.get(getActivity()).clearMemory();
		}
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	protected boolean onNetConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		} else {
			return false;
		}
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

	protected void showLoadingView(View view) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mContentView.setVisibility(View.INVISIBLE);
		mEmptyView.setVisibility(View.GONE);
		mNullView.setVisibility(View.GONE);
	}

	protected void showLoadingView(View view, String loadingTip) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingTip.setText(loadingTip);
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);
		mNullView.setVisibility(View.GONE);
	}

	protected void showLoadFailView(View view) {
		showLoadFailView(view, ZRStrings.get(mContext, "tip_reload"));
	}

	protected void showLoadFailView(View view, CharSequence failTip) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingFailTip.setText(failTip);
		mLoadingFailTip.setLineSpacing(3, (float) 1.5);
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);
		mNullView.setVisibility(View.GONE);
	}

	protected void showContentView(View view) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingContainer.setVisibility(View.INVISIBLE);
		mContentView.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);
		mNullView.setVisibility(View.GONE);
	}

	protected void showEmptyView(View view, String tip) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.VISIBLE);
		mEmptyTip.setText(tip);
	}

	protected void showEmptyView(View view, int imgID, String tip) {
		showEmptyView(view, getResources().getDrawable(imgID), tip);
	}

	protected void showEmptyView(View view, Drawable drawable, String tip) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.VISIBLE);
		mNullView.setVisibility(View.GONE);
		mEmptyTip.setText(tip);
		mEmptyImage.setImageDrawable(drawable);
	}

	protected void showLoadingNullView(View view) {
		if (null == mLoadingView) {
			initLoadingView(view);
		}
		mLoadingContainer.setVisibility(View.VISIBLE);
		mLoadFailView.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);
		mNullView.setVisibility(View.VISIBLE);
	}

	private void initLoadingView(final View view) {
		mLoadingContainer = view.findViewById(ZRResourceManager.getResourceID(
				"view_loading_container", "id"));
		mLoadingView = view.findViewById(ZRResourceManager.getResourceID(
				"view_loading", "id"));
		mNullView = view.findViewById(ZRResourceManager.getResourceID(
				"view_loading_null", "id"));
		mLoadFailView = view.findViewById(ZRResourceManager.getResourceID(
				"view_loading_fail", "id"));
		mContentView = view.findViewById(ZRResourceManager.getResourceID(
				"view_content_container", "id"));
		mLoadFailView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showLoadingView(view);
				onReloadContent();
			}
		});
		mEmptyView = view.findViewById(ZRResourceManager.getResourceID(
				"view_empty", "id"));
		mEmptyView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showLoadingView(view);
				onReloadContent();
			}
		});
		mEmptyTip = (ZRTextView) view.findViewById(ZRResourceManager
				.getResourceID("text_empty", "id"));
		mEmptyImage = (ImageView) view.findViewById(ZRResourceManager
				.getResourceID("img_empty", "id"));
		mLoadingTip = (ZRTextView) view.findViewById(ZRResourceManager
				.getResourceID("tv_loading_tip", "id"));
		mLoadingFailTip = (ZRTextView) view.findViewById(ZRResourceManager
				.getResourceID("tv_loadingfail_tip", "id"));
	}

	public void postMessage(int requestID, String server, String method,
			String encryption, String msg) {
		postMessage(new ZRRequestID(requestID), server, method, encryption, msg);
	}

	public void postMessage(ZRRequestID requestID, String server,
			String method, String encryption, String msg) {
		ZRHttpTask task = new ZRHttpTask(requestID, mTaskCallback);
		try {
			ZRTaskDelegate.execute(task, server, method, encryption, msg);
		} catch (IOException e) {
			onError(requestID, ZRErrors.ERROR_NETWORK);
		}
	}

	public void downloadFile(int requestID, boolean isUTF8, String url,
			String target) {
		downloadFile(new ZRRequestID(requestID), isUTF8, url, target);
	}

	public void downloadFile(ZRRequestID requestID, boolean isUTF8, String url,
			String target) {
		ZRFileDownloadTask task = new ZRFileDownloadTask(requestID, isUTF8,
				mTaskCallback, mDownloadProgressCallback);
		try {
			ZRTaskDelegate.execute(task, url, target);
		} catch (IOException e) {
			onError(requestID, ZRErrors.ERROR_NETWORK);
		}
	}

	protected void onError(ZRRequestID requestID, String errorCode) {
		String errorDesc = ZRErrors.getLocalErrorMsg(mContext, errorCode);
		onError(requestID, errorCode, errorDesc);
	}

	protected void onError(ZRRequestID requestID, String errorCode,
			String errorDesc) {
		try {
			if (errorDesc != null && !TextUtils.isEmpty(errorDesc))
				showToast(errorDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected JSONObject getParamsFromResp(ZRRequestID requestID, String result)
			throws JSONException {
		JSONObject json = new JSONObject(result);
		String respCode = json.optString(ZRConstant.KEY_RESP_CODE);
		String sid = json.optString(ZRConstant.KEY_SID);
		if (!"0".equals(sid))
			ZRSharePreferenceKeeper.keepStringValue(getActivity(),
					ZRConstant.KEY_EXTRA_SID, sid);
		if (ZRErrors.SUCCESS.equals(respCode)) {
			JSONObject obj = json.optJSONObject(ZRConstant.KEY_RESULT);
			return obj;
		} else {
			String respDesc = json.optString(ZRConstant.KEY_RESP_DESC);
			onError(requestID, respCode, respDesc);
			hideDialog();
			return null;
		}
	}

	protected JSONObject getParamsFromResp2(ZRRequestID requestID, String result)
			throws JSONException {
		JSONObject json = new JSONObject(result);
		String respCode = json.optString(ZRConstant.KEY_RESP_CODE);
		String sid = json.optString(ZRConstant.KEY_SID);
		if (!"0".equals(sid))
			ZRSharePreferenceKeeper.keepStringValue(getActivity(),
					ZRConstant.KEY_EXTRA_SID, sid);
		if (ZRErrors.SUCCESS.equals(respCode)) {
			return json;
		} else {
			String respDesc = json.optString(ZRConstant.KEY_RESP_DESC);
			onError(requestID, respCode, respDesc);
			hideDialog();
			return null;
		}
	}

	protected JSONObject getParamsFromResp3(ZRRequestID requestID, String result)
			throws JSONException {
		JSONObject json = new JSONObject(result);
		return json;
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
	 * When the reload button in load fail view is pressed, this function will
	 * be called.
	 */
	protected void onReloadContent() {
	}

	protected void onResult(ZRRequestID requestID, String result) {
	}

	protected void onUploadProgress(ZRRequestID requestID, int progress) {

	}

	protected void onDownloadProgress(ZRRequestID requestID, int progress) {

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

	/**
	 * 在这里实现Fragment数据的缓加载.
	 * 
	 * @param isVisibleToUser
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	protected void onVisible() {
		lazyLoad();
	}

	protected abstract void lazyLoad();

	protected void onInvisible() {
	}

	/**
	 * fragment name
	 */
	public abstract String getFragmentName();
}
