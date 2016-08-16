package com.zritc.colorfulfund.ui;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zritc.colorfulfund.utils.ZRResourceManager;

/**
 * Custom dialog.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRDialog extends Dialog {
	public static final int STYLE_NORMAL = 0;
	public static final int STYLE_IMPORTANT = 1;
	public static final int STYLE_VERY_IMPORTANT = 2;
	private boolean isCancelable = true;
	private TextView mDialogInfo;
	private DialogListener mDialogLisener;

	public interface DialogListener {
		public void onCancel();

		public void onConfirm();
	}

	private void init(boolean setCanceledOnTouchOutside, boolean setCancelable) {
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		setCanceledOnTouchOutside(setCanceledOnTouchOutside);
		setCancelable(setCancelable);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
		lp.gravity = Gravity.CENTER;
		getWindow().setAttributes(lp);
	}

	// 黑色背景，网络请求时候的弹出框
	public ZRDialog(Context context, CharSequence message,
			boolean setCanceledOnTouchOutside, boolean setCancelable) {
		super(context, ZRResourceManager.getResourceID("ZRDialog.Progress",
				"style"));
		setContentView(ZRResourceManager.getResourceID("view_dialog_loading",
				"layout"));
		mDialogInfo = (ZRTextView) findViewById(ZRResourceManager
				.getResourceID("dialog_msg_title", "id"));
		mDialogInfo.setText(message);
		init(setCanceledOnTouchOutside, setCancelable);
	}

	// 普通Alert弹出框
	public ZRDialog(Context context, CharSequence title, CharSequence message,
			CharSequence confirm, CharSequence cancel,
			DialogListener dialogListener, int style, int singleBtnTextStyle,
			boolean setCanceledOnTouchOutside, boolean setCancelable) {
		super(context, ZRResourceManager.getResourceID("ZRDialog", "style"));
		mDialogLisener = dialogListener;
		setContentView(ZRResourceManager.getResourceID("view_dialog", "layout"));

		// 窗口
		init(setCanceledOnTouchOutside, setCancelable);

		// title
		ZRTextView dialogTitle = (ZRTextView) findViewById(ZRResourceManager
				.getResourceID("dialog_title", "id"));
		View dialogContainer = findViewById(ZRResourceManager.getResourceID(
				"dialog_title_container", "id"));

		if (!TextUtils.isEmpty(title)) {
			dialogContainer.setVisibility(View.VISIBLE);
			dialogTitle.setText(title);
		} else {
			dialogContainer.setVisibility(View.GONE);
		}

		// msgall
		LinearLayout msgAllContainer = (LinearLayout) findViewById(ZRResourceManager
				.getResourceID("dlg_all_ll", "id"));
		ZRTextView dialogMessage = (ZRTextView) findViewById(ZRResourceManager
				.getResourceID("dialog_msg_detail", "id"));
		if (!TextUtils.isEmpty(message)) {
			dialogMessage.setText(message);
		} else {
			msgAllContainer.setVisibility(View.GONE);
			findViewById(ZRResourceManager.getResourceID("image_line", "id"))
					.setVisibility(View.GONE);
		}

		boolean showConfirm = !TextUtils.isEmpty(confirm);
		boolean showCancel = !TextUtils.isEmpty(cancel);
		boolean singleBtn = !showConfirm || !showCancel;
		View btnDivider = findViewById(ZRResourceManager.getResourceID(
				"view_btn_divider", "id"));
		ZRButton confirmBtn = (ZRButton) findViewById(ZRResourceManager
				.getResourceID("btn_ok", "id"));
		if (showConfirm) {
			confirmBtn.setText(confirm);
			confirmBtn.setVisibility(View.VISIBLE);
			if (singleBtn) {
				confirmBtn.setStyle(context, singleBtnTextStyle);
				btnDivider.setVisibility(View.GONE);
			} else {
				btnDivider.setVisibility(View.VISIBLE);
				confirmBtn.setStyle(context, ZRButton.STYLE_DIALOG_LEFT);
			}
			if (null != mDialogLisener) {
				confirmBtn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						mDialogLisener.onConfirm();
					}
				});
			}
		} else {
			confirmBtn.setVisibility(View.GONE);
		}

		ZRButton cancelBtn = (ZRButton) findViewById(ZRResourceManager
				.getResourceID("btn_cancel", "id"));
		if (showCancel) {
			cancelBtn.setText(cancel);
			cancelBtn.setVisibility(View.VISIBLE);
			if (singleBtn) {
				btnDivider.setVisibility(View.GONE);
				cancelBtn.setStyle(context, ZRButton.STYLE_DIALOG_SINGLE);
			} else {
				btnDivider.setVisibility(View.VISIBLE);
				cancelBtn.setStyle(context, ZRButton.STYLE_DIALOG_RIGHT);
			}
			if (null != mDialogLisener) {
				cancelBtn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						mDialogLisener.onCancel();
					}
				});
			}
		} else {
			cancelBtn.setVisibility(View.GONE);
		}
	}

	@Override
	public void setCancelable(boolean cancelable) {
		isCancelable = cancelable;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isCancelable) {
				dismiss();
				if (null != mDialogLisener) {
					mDialogLisener.onCancel();
				}
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
