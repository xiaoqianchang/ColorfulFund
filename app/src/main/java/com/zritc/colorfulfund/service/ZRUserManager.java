package com.zritc.colorfulfund.service;

import android.content.Context;

import com.zritc.colorfulfund.data.ZRDataEngine;

public class ZRUserManager {

    public static String LOGIN_ACTION_NAME = "com.zritc.colorfulfund.login.success.action";

    private static ZRUserManager sInstance = null;
    private static Context mContext;

    private ZRUserManager(Context context) {
        mContext = context;
    }

    public static final synchronized ZRUserManager getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new ZRUserManager(context);
        }
        return sInstance;
    }

    public boolean loginCheckAndJump(int reqid) {
        if (null == ZRDataEngine.getInstance().getUserInfo()) {
            // 未登录，进入登录界面
            jumpToLogin(reqid);
            return false;
        }
        return true;
    }

    public void jumpToLogin(int reqid) {
//        Intent intent = new Intent();
//        intent.setClass(mContext, ZRActivityLoginRegister.class);
//        ((Activity) mContext).startActivityForResult(intent, reqid);
//        ((Activity) mContext).overridePendingTransition(
//                ZRResourceManager.getResourceID("push_up_in", "anim"),
//                ZRResourceManager.getResourceID("anim_still", "anim"));
    }
}
