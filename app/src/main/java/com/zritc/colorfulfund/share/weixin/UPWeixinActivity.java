package com.zritc.colorfulfund.share.weixin;

import android.app.Activity;
import android.os.Bundle;

import com.zritc.colorfulfund.share.UPShareErrors;
import com.zritc.colorfulfund.share.UPShareManager;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPWeixinActivity extends Activity implements IWXAPIEventHandler {
	UPShareWeixinImpl mWeixinImpl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mWeixinImpl = (UPShareWeixinImpl) UPShareManager.getInstance(this)
				.getPlatformImpl(UPShareManager.SHARE_WEIXIN_SINGLE);
		if (!mWeixinImpl.mShareApi.handleIntent(getIntent(), this)) {
			if (null != mWeixinImpl.mCallback) {
				mWeixinImpl.mCallback.onShareFailed(
						UPShareManager.SHARE_WEIXIN_SINGLE,
						UPShareErrors.ERROR_UNKNOW, "");
			}
			finish();
		}
	}

	@Override
	public void onReq(BaseReq baseReq) {
	}

	@Override
	public void onResp(BaseResp baseResp) {
		if (null != mWeixinImpl.mCallback) {
			switch (baseResp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				mWeixinImpl.mCallback
						.onShareSucceed(UPShareManager.SHARE_WEIXIN_SINGLE);
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				mWeixinImpl.mCallback
						.onShareCancelled(UPShareManager.SHARE_WEIXIN_SINGLE);
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
			case BaseResp.ErrCode.ERR_COMM:
			case BaseResp.ErrCode.ERR_SENT_FAILED:
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				mWeixinImpl.mCallback.onShareFailed(
						UPShareManager.SHARE_WEIXIN_SINGLE, baseResp.errCode,
						baseResp.errStr);
				break;
			default:
				mWeixinImpl.mCallback.onShareFailed(
						UPShareManager.SHARE_WEIXIN_SINGLE, baseResp.errCode,
						baseResp.errStr);
				break;
			}
		}
		finish();
	}
}
