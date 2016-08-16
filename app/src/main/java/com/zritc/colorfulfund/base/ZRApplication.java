package com.zritc.colorfulfund.base;

import android.app.Application;
import android.content.Context;

import com.zritc.colorfulfund.data.ZRDataEngine;
import com.zritc.colorfulfund.io.ZRHttpManager;
import com.zritc.colorfulfund.utils.ZRCrashHandler;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRHttpResourceCache;
import com.zritc.colorfulfund.utils.ZRImageLoaderHelper;
import com.zritc.colorfulfund.utils.ZRResourceManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Custom application.
 *
 * @author Midas.
 * @version 1.0
 * @createDate 2015-10-16
 * @lastUpdate 2015-10-16
 */
public class ZRApplication extends Application {

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        // 初始化程序崩溃捕捉类
        ZRCrashHandler.getInstance().init(this);
        ZRHttpManager.init(this);
        // 初始化设备信息类
        ZRDeviceInfo.init(this);
        // 初始化资源缓冲类
        ZRHttpResourceCache.init(this);
        // 初始化资源管理类
        ZRResourceManager.init(this);
        // 初始化图片缓冲类
        ZRImageLoaderHelper.init(this);
        ZRDataEngine.init(this);
        // 极光推送
        JPushInterface.init(this);
        // 发布设置为false
        JPushInterface.setDebugMode(false);
    }
}