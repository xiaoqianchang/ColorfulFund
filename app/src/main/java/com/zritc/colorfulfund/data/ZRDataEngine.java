package com.zritc.colorfulfund.data;

import android.content.Context;
import android.os.Bundle;

import com.zritc.colorfulfund.utils.ZRAppConfig;
import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRFileUtils;
import com.zritc.colorfulfund.utils.ZRLog;
import com.zritc.colorfulfund.utils.ZRSharePreferenceKeeper;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

/**
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRDataEngine {

    private static final String LOCAL_DATA_FOLDER = ZRAppConfig.WORK_FOLDER
            + "data/";

    private static Context mContext;
    private static ZRDataEngine sInstance = null;
    private ZRSysinfo mSysinfo = null;
    private ZRUserInfo mUserInfo = null;
    private ZRUpdateInfo mUpdateInfo = null;
    private String mPromotionUrl = "http://www.baidu.com"; // test url
    private String mUploadUrl;
    private String mDownloadUrl;

    private boolean needRestore = true;

    private String sessionId = "-1";

    public static void init(Context ctx) {
        mContext = ctx;
    }

    public static final synchronized ZRDataEngine getInstance() {
        if (null == sInstance) {
            sInstance = new ZRDataEngine();
        }
        return sInstance;
    }

    public static final synchronized void clear() {
        sInstance = null;
    }

    public final synchronized void setUserInfo(ZRUserInfo userInfo) {
        mUserInfo = userInfo;
    }

    public final synchronized ZRUserInfo getUserInfo() {
        return mUserInfo;
    }

    public final synchronized void setSysinfo(ZRSysinfo sysInfo) {
        mSysinfo = sysInfo;
    }

    public final synchronized ZRSysinfo getSysInfo() {
        if (null == mSysinfo) {
            try {
                String json = ZRSharePreferenceKeeper.getStringValue(mContext,
                        "sysconfig_json");
                mSysinfo = new ZRSysinfo().initFromSysConfigJson(json);
                ZRLog.d("sysinfo is null , parase cache json:\n" + json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mSysinfo;
    }

    public synchronized String getSessionId() {
        return sessionId;
    }

    public synchronized void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public final synchronized ZRUpdateInfo getUpdateInfo() {
        return mUpdateInfo;
    }

    public final synchronized void setUpdateInfo(ZRUpdateInfo updateInfo) {
        mUpdateInfo = updateInfo;
    }

    public final synchronized String getPromotionUrl() {
        return mPromotionUrl;
    }

    public final synchronized void setPromotionUrl(String promotionUrl) {
        mPromotionUrl = promotionUrl;
    }

    public final synchronized void setNeedRestore(boolean need) {
        needRestore = need;
    }

    public final synchronized boolean needRestore() {
        return needRestore;
    }

    public final synchronized void setUploadUrl(String mUploadUrl) {
        this.mUploadUrl = mUploadUrl;
    }

    public final synchronized void setDownloadUrl(String mDownloadUrl) {
        this.mDownloadUrl = mDownloadUrl;
    }

    public final synchronized String getUploadUrl() {
        return mUploadUrl;
    }

    public final synchronized String getDownloadUrl() {
        return mDownloadUrl;
    }

    public final synchronized Bundle saveTempData() {
        Bundle data = new Bundle();
        data.putSerializable(ZRConstant.KEY_SYS_INFO, mSysinfo);
        data.putSerializable(ZRConstant.KEY_USER_INFO, mUserInfo);
        data.putSerializable(ZRConstant.KEY_CLIENT_INFO, mUpdateInfo);
        data.putString(ZRConstant.KEY_UPLOAD_URL, mUploadUrl);
        data.putString(ZRConstant.KEY_DOWNLOAD_URL, mDownloadUrl);
        return data;
    }

    public final synchronized void loadTempData(Bundle data) {
        mSysinfo = (ZRSysinfo) data.getSerializable(ZRConstant.KEY_SYS_INFO);
        mUserInfo = (ZRUserInfo) data.getSerializable(ZRConstant.KEY_USER_INFO);
        mUpdateInfo = (ZRUpdateInfo) data
                .getSerializable(ZRConstant.KEY_CLIENT_INFO);
        mUploadUrl = data.getString(ZRConstant.KEY_UPLOAD_URL);
        mDownloadUrl = data.getString(ZRConstant.KEY_DOWNLOAD_URL);
    }

    public final synchronized boolean saveData(String key, String data) {
        ZRLog.e("saveData:" + key + " " + this);
        File file = new File(LOCAL_DATA_FOLDER, key);
        try {
            ZRFileUtils.saveFile(data, file);
            return true;
        } catch (Exception e) {
            ZRLog.e("saveData", "exception:" + key + " " + this, e);
            e.printStackTrace();
            deleteData(key);
        }
        return false;
    }

    public final synchronized String getData(String key) {
        ZRLog.e("getData:" + key + " " + this);
        try {
            String data = getStringData(key);
            if (null != data) {
                return data;
            }
        } catch (Exception e) {
            ZRLog.e("getData", "exception:" + key + " " + this, e);
            e.printStackTrace();
            deleteData(key);
        }
        return null;
    }

    public final synchronized boolean deleteData(String key) {
        File file = new File(LOCAL_DATA_FOLDER, key);
        if (!file.exists()) {
            return true;
        }
        return ZRFileUtils.deleteFile(file);
    }

    public final synchronized boolean isDataExist(String key) {
        File file = new File(LOCAL_DATA_FOLDER, key);
        return file.exists();
    }

    private final synchronized String getStringData(String key) {
        ZRLog.e("getStringData:" + key + " " + this);
        File file = new File(LOCAL_DATA_FOLDER, key);
        if (!file.exists()) {
            ZRLog.e("file not exist:" + key + " " + this);
            return null;
        }
        try {
            return ZRFileUtils.readFile(file);
        } catch (IOException e) {
            ZRLog.e("getStringData", "ioexception:" + key + " " + this, e);
            e.printStackTrace();
        }
        return null;
    }

    private final boolean isPersonalData(String key) {
        return true;
        // return !KEY_DOWNLOAD_APP.equals(key) && !KEY_APPINFO.equals(key);
    }
}
