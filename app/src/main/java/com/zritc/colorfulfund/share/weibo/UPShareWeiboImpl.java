package com.zritc.colorfulfund.share.weibo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zritc.colorfulfund.share.IUPSharePlatform;
import com.zritc.colorfulfund.share.IUPShareRequestCallback;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.share.UPShareErrors;
import com.zritc.colorfulfund.share.util.Util;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.Utility;

import java.net.URL;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPShareWeiboImpl implements IUPSharePlatform {

    IWeiboShareAPI mShareApi;
    IUPShareRequestCallback mCallback;

    /*
     * @hide
     */
    public UPShareWeiboImpl(Context context, String appid) {
        mShareApi = WeiboShareSDK.createWeiboAPI(context, appid);
        try {
            mShareApi.registerApp();
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void share(Context context, int platform,
                      IUPShareRequestCallback callback, String content) {
        mCallback = callback;
        if (!checkApi(platform)) {
            return;
        }

        TextObject textObject = new TextObject();
        textObject.text = content;

        WeiboMessage message = new WeiboMessage();
        message.mediaObject = textObject;
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = message;

        mShareApi.sendRequest((Activity) context, request);
    }

    @Override
    public void share(final Context context, int platform,
                      IUPShareRequestCallback callback, final UPMediaMessage msg) {
        mCallback = callback;
        if (!checkApi(platform)) {
            return;
        }
        Thread thread = new Thread() {
            public void run() {
                try {
                    // 1. 初始化微博的分享消息
                    WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
//                    weiboMessage.textObject = getTextObj(msg);
                    weiboMessage.imageObject = getImageObj(msg);
                    // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
                    weiboMessage.mediaObject = getWebpageObj(msg);
                    // 2. 初始化从第三方到微博的消息请求
                    SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
                    // 用transaction唯一标识一个请求
                    request.transaction = String.valueOf(System.currentTimeMillis());
                    request.multiMessage = weiboMessage;

                    // 3. 发送请求消息到微博，唤起微博分享界面
                    mShareApi.sendRequest((Activity) context, request);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private TextObject getTextObj(UPMediaMessage msg) {
        TextObject textObject = new TextObject();
        textObject.text = msg.getDescription();
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private ImageObject getImageObj(UPMediaMessage msg) {
        ImageObject imageObject = new ImageObject();
        try {
            if (null != msg.getImageUrl()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new URL(msg.getImageUrl())
                        .openStream());
                // 压缩图片--宽度120 高度120的图像
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 120,
                        120, true);
                if (null != thumbBmp) {
                    imageObject.setImageObject(thumbBmp);
                }
            } else {
                Bitmap thumb = msg.getThumbData();
                if (null != thumb) {
                    imageObject.setImageObject(thumb);
                }
            }
        } catch (Exception ex) {
            Bitmap thumb = msg.getThumbData();
            if (null != thumb) {
                imageObject.setImageObject(thumb);
            }
        }
        return imageObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj(UPMediaMessage msg) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = msg.getTitle();
        mediaObject.description = msg.getDescription();
        try {
            if (null != msg.getImageUrl()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new URL(msg.getImageUrl())
                        .openStream());
                // 压缩图片--宽度120 高度120的图像
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 120,
                        120, true);
                bitmap.recycle();// 回收图片占用的内存资源
                if (null != thumbBmp) {
                    mediaObject.thumbData = Util.bmpToByteArray(thumbBmp, true);
                }
            } else {
                Bitmap thumb = msg.getThumbData();
                if (null != thumb) {
                    mediaObject.thumbData = Util.bmpToByteArray(thumb, true);
                }
            }
        } catch (Exception ex) {
            Bitmap thumb = msg.getThumbData();
            if (null != thumb) {
                mediaObject.thumbData = Util.bmpToByteArray(thumb, true);
            }
        }
        mediaObject.actionUrl = msg.getUrl();
        mediaObject.defaultText = msg.getDescription();
        return mediaObject;
    }

    private boolean checkApi(int platform) {
        if (!mShareApi.isWeiboAppInstalled()) {
            if (null != mCallback) {
                mCallback.onShareFailed(platform,
                        UPShareErrors.ERROR_UNINSTALLED, "");
            }
            return false;
        }
        if (!mShareApi.isWeiboAppSupportAPI()) {
            if (null != mCallback) {
                mCallback.onShareFailed(platform,
                        UPShareErrors.ERROR_UNSUPPORT, "");
            }
            return false;
        }
        return true;
    }

}
