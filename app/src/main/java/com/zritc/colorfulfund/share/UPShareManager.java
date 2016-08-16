package com.zritc.colorfulfund.share;

import android.content.Context;

import com.zritc.colorfulfund.share.email.UPShareEmailImpl;
import com.zritc.colorfulfund.share.sms.UPShareSmsImpl;
import com.zritc.colorfulfund.share.weibo.UPShareWeiboImpl;
import com.zritc.colorfulfund.share.weixin.UPShareWeixinImpl;
import com.zritc.colorfulfund.share.qq.UPShareQqImpl;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPShareManager {

    public static final String SHARE_SINA_ID = "836630448";
    public static final String SHARE_WEIXIN_ID = "wx70360c923988a2b6";
    public static final String SHARE_QQ_ID = "1105251129";

    public static final int SHARE_WEIBO = 0;
    public static final int SHARE_WEIXIN_SINGLE = 1;
    public static final int SHARE_WEIXIN_GROUP = 2;
    public static final int SHARE_SMS = 3;
    public static final int SHARE_EMAIL = 4;
    public static final int SHARE_QQ = 5;
    public static final int SHARE_XUEQIU = 6;

    private static UPShareManager sInstance;

    private Context mContext;
    private UPShareWeiboImpl mWeiboImpl;
    private UPShareWeixinImpl mWeixinImpl;
    private UPShareQqImpl mQqImpl;
    private UPShareSmsImpl mSmsImpl;
    private UPShareEmailImpl mEmailImpl;

    public static synchronized UPShareManager getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new UPShareManager(context);
        }
        return sInstance;
    }

    public void initPlatform(int platform, String appID) {
        switch (platform) {
            case SHARE_WEIBO:
//                if (null == mWeiboImpl) {
                    mWeiboImpl = new UPShareWeiboImpl(mContext, appID);
//                }
                break;
            case SHARE_WEIXIN_SINGLE:
            case SHARE_WEIXIN_GROUP:
                if (null == mWeixinImpl) {
                    mWeixinImpl = new UPShareWeixinImpl(mContext, appID);
                }
                break;
            case SHARE_QQ:
                if (null == mQqImpl) {
                    mQqImpl = new UPShareQqImpl(mContext, appID);
                }
                break;
        }
    }

    public void initPlatform(int[] platforms, String[] appIDs) {
        for (int i = 0; i < platforms.length; ++i) {
            initPlatform(platforms[i], appIDs[i]);
        }
    }

    /*
     * @hide
     */
    public final IUPSharePlatform getPlatformImpl(int platform) {
        switch (platform) {
            case SHARE_WEIBO:
                return mWeiboImpl;
            case SHARE_WEIXIN_SINGLE:
            case SHARE_WEIXIN_GROUP:
                return mWeixinImpl;
            case SHARE_QQ:
                return mQqImpl;
            case SHARE_SMS:
                return mSmsImpl;
            case SHARE_EMAIL:
                return mEmailImpl;
        }
        return null;
    }

    public final void share(int platform, IUPShareRequestCallback callback,
                            String content) {
        getPlatformImpl(platform).share(mContext, platform, callback, content);
    }

    public final void share(int platform, IUPShareRequestCallback callback,
                            UPMediaMessage msg) {
        getPlatformImpl(platform).share(mContext, platform, callback, msg);
    }

    private UPShareManager(Context context) {
        mContext = context;
        mSmsImpl = new UPShareSmsImpl();
        mEmailImpl = new UPShareEmailImpl();
    }

    public static void clearShareInstance() {
        sInstance = null;
    }
}
