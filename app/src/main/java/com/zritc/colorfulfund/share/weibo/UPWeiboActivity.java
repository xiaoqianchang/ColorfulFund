package com.zritc.colorfulfund.share.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zritc.colorfulfund.share.UPShareErrors;
import com.zritc.colorfulfund.share.UPShareManager;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.constant.WBConstants;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPWeiboActivity extends Activity implements IWeiboHandler.Response {
    UPShareWeiboImpl mWeiboImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWeiboImpl = (UPShareWeiboImpl) UPShareManager.getInstance(this)
                .getPlatformImpl(UPShareManager.SHARE_WEIBO);
        // 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        // 失败返回 false，不调用上述回调
        if (savedInstanceState != null) {
            if (!mWeiboImpl.mShareApi.handleWeiboResponse(getIntent(), this)) {
                if (null != mWeiboImpl.mCallback) {
                    mWeiboImpl.mCallback.onShareFailed(UPShareManager.SHARE_WEIBO,
                            UPShareErrors.ERROR_UNKNOW, "");
                }
                finish();
            }
        } else {
            finish();
        }
    }

    /**
     * @see {@link Activity#onNewIntent}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
        mWeiboImpl.mShareApi.handleWeiboResponse(intent, this);
    }

    @Override
    public void onResponse(BaseResponse baseResp) {
        if (null != mWeiboImpl.mCallback) {
            if (baseResp != null) {
                switch (baseResp.errCode) {
                    case WBConstants.ErrorCode.ERR_OK:
                        mWeiboImpl.mCallback.onShareSucceed(UPShareManager.SHARE_WEIBO);
                        break;
                    case WBConstants.ErrorCode.ERR_CANCEL:
                        mWeiboImpl.mCallback.onShareCancelled(UPShareManager.SHARE_WEIBO);
                        break;
                    case WBConstants.ErrorCode.ERR_FAIL:
                        mWeiboImpl.mCallback.onShareFailed(UPShareManager.SHARE_WEIBO,
                                baseResp.errCode, baseResp.errMsg);
                        break;
                    default:
                        mWeiboImpl.mCallback.onShareFailed(UPShareManager.SHARE_WEIBO,
                                baseResp.errCode, baseResp.errMsg);
                        break;
                }
            }
        }
        finish();
    }
}
