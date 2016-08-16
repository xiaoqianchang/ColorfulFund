package com.zritc.colorfulfund.share.weixin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zritc.colorfulfund.share.IUPSharePlatform;
import com.zritc.colorfulfund.share.IUPShareRequestCallback;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.share.UPShareErrors;
import com.zritc.colorfulfund.share.UPShareManager;
import com.zritc.colorfulfund.share.util.Util;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.net.URL;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPShareWeixinImpl implements IUPSharePlatform {
    IWXAPI mShareApi;
    IUPShareRequestCallback mCallback;

    /*
     * @hide
     */
    public UPShareWeixinImpl(Context context, String appid) {
        mShareApi = WXAPIFactory.createWXAPI(context, appid, true);
        mShareApi.registerApp(appid);
    }

    @Override
    public void share(Context context, int platform,
                      IUPShareRequestCallback callback, String content) {
        mCallback = callback;
        if (!checkApi(platform)) {
            return;
        }
        WXTextObject textObject = new WXTextObject();
        textObject.text = content;

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = textObject;
        message.description = content;

        SendMessageToWX.Req request = new SendMessageToWX.Req();
        request.transaction = buildTransaction("text");
        request.message = message;
        request.scene = platform == UPShareManager.SHARE_WEIXIN_SINGLE ? SendMessageToWX.Req.WXSceneSession
                : SendMessageToWX.Req.WXSceneTimeline;

        mShareApi.sendReq(request);
    }

    @Override
    public void share(Context context, final int platform,
                      IUPShareRequestCallback callback, final UPMediaMessage msg) {
        mCallback = callback;
        if (!checkApi(platform)) {
            return;
        }
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                WXMediaMessage message = new WXMediaMessage();
                message.title = msg.getTitle();
                message.description = msg.getDescription();
                try {
                    if (null != msg.getImageUrl()) {
                        Bitmap bitmap = BitmapFactory.decodeStream(new URL(msg.getImageUrl())
                                .openStream());
                        // 压缩图片--宽度120 高度120的图像
                        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 120,
                                120, true);
//                        bitmap.recycle();// 回收图片占用的内存资源
                        if (null != thumbBmp) {
                            message.thumbData = Util.bmpToByteArray(thumbBmp, true);
                        }
                    } else {
                        Bitmap thumb = msg.getThumbData();
                        if (null != thumb) {
                            message.thumbData = Util.bmpToByteArray(thumb, true);
                        }
                    }
                } catch (Exception ex) {
                    Bitmap thumb = msg.getThumbData();
                    if (null != thumb) {
                        message.thumbData = Util.bmpToByteArray(thumb, true);
                    }
                }

                WXWebpageObject mediaObject = new WXWebpageObject();
                mediaObject.webpageUrl = msg.getUrl();
                message.mediaObject = mediaObject;

                SendMessageToWX.Req request = new SendMessageToWX.Req();
                request.transaction = buildTransaction("webpage");
                request.message = message;
                request.scene = platform == UPShareManager.SHARE_WEIXIN_SINGLE ? SendMessageToWX.Req.WXSceneSession
                        : SendMessageToWX.Req.WXSceneTimeline;

                mShareApi.sendReq(request);
            }
        });

        thread.start();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    private boolean checkApi(int platform) {
        if (!mShareApi.isWXAppInstalled()) {
            if (null != mCallback) {
                mCallback.onShareFailed(platform,
                        UPShareErrors.ERROR_UNINSTALLED, "");
            }
            return false;
        }
        if (!mShareApi.isWXAppSupportAPI()) {
            if (null != mCallback) {
                mCallback.onShareFailed(platform,
                        UPShareErrors.ERROR_UNSUPPORT, "");
            }
            return false;
        }
        if (platform != UPShareManager.SHARE_WEIXIN_SINGLE
                && mShareApi.getWXAppSupportAPI() < 0x21020001) {
            if (null != mCallback) {
                mCallback.onShareFailed(platform,
                        UPShareErrors.ERROR_UNSUPPORT, "");
            }
            return false;
        }
        return true;
    }
}
