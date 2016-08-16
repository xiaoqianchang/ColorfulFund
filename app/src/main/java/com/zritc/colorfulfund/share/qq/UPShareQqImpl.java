package com.zritc.colorfulfund.share.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.zritc.colorfulfund.share.IUPSharePlatform;
import com.zritc.colorfulfund.share.IUPShareRequestCallback;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.share.UPShareManager;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2016-04-20
 * @lastUpdate 2016-04-20
 */
public class UPShareQqImpl implements IUPSharePlatform , IUiListener{

	Tencent mTencent;
	IUPShareRequestCallback mCallback;

	/*
	 * @hide
	 */
	public UPShareQqImpl(Context context, String appid) {
		mTencent = Tencent.createInstance(appid, context);
	}

	@Override
	public void share(Context context, int platform,
			IUPShareRequestCallback callback, String content) {
		mCallback = callback;

		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  content);
		mTencent.shareToQQ((Activity) context, params, this);
	}

	@Override
	public void share(Context context, int platform,
			IUPShareRequestCallback callback, UPMediaMessage msg) {
		mCallback = callback;
		
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, msg.getTitle2());
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  msg.getDescription());
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  msg.getUrl());
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,msg.getImageUrl());
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  msg.mAppName);
//		params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");
		mTencent.shareToQQ((Activity) context, params, this);
	}

	@Override
	public void onComplete(Object o) {
		mCallback.onShareSucceed(UPShareManager.SHARE_QQ);
	}

	@Override
	public void onError(UiError uiError) {
		mCallback.onShareFailed(UPShareManager.SHARE_QQ,
					uiError.errorCode, uiError.errorMessage);
	}

	@Override
	public void onCancel() {
		mCallback.onShareCancelled(UPShareManager.SHARE_QQ);
	}

}
