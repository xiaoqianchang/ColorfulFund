package com.zritc.colorfulfund.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * DeviceInfo class
 *
 * @author Midas.
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRDeviceInfo {

    public static final int IMAGE_TYPE_150 = 0;
    public static final int IMAGE_TYPE_640 = 1;
    public static final int IMAGE_TYPE_1080 = 2;
    public static final int IMAGE_TYPE_default = 3;

    private static Context mContext;
    private static String mVersion;
    private static String mVersionName;
    private static String mDeviceID;
    private static int mImageType = -1;

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * system type
     *
     * @return android
     */
    public static String getOSName() {
        return "android";
    }

    /**
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getOSCode() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * @return
     */
    public static String getDeviceName() {
        return android.os.Build.MANUFACTURER + "_" + android.os.Build.PRODUCT;
    }

    /**
     * 固件版本
     *
     * @return
     */
    public static String getVersionRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * @return
     */
    public static String getVersionCodename() {
        return android.os.Build.VERSION.CODENAME;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * @return
     */
    public static String getDeviceID() {
        if (null == mDeviceID) {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            mDeviceID = tm.getDeviceId();
        }
        return null == mDeviceID ? "" : mDeviceID;
    }

    /**
     * @return versionCode
     */
    public static String getClientVersion() {
        if (null == mVersion) {
            try {
                mVersion = String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null == mVersion ? "" : mVersion;
    }

    /**
     * @return versionName
     */
    public static String getClientVersionName() {
        if (null == mVersionName) {
            try {
                mVersionName = String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null == mVersionName ? "" : mVersionName;
    }

    public static String getApiVersion() {
        String apiVer = getClientVersionName();
//        return apiVer.substring(0, 3);
        return "qtest";
//        return "91";
    }

    /**
     * @return
     */
    public static String getResolution() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width + "*" + height;
    }

    /**
     * @return
     */
    public static int getDeviceHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * @return
     */
    public static int getDeviceWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(out);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            out.set(width, height);
        }
        return out;
    }

    /**
     * @return
     */
    public static int getDeviceImageType() {
        if (mImageType == -1) {
            int width = mContext.getResources().getDisplayMetrics().widthPixels;
            if (width <= 640) {
                mImageType = IMAGE_TYPE_640;
            } else if (width > 640 && width <= 1080) {
                mImageType = IMAGE_TYPE_1080;
            } else {
                mImageType = IMAGE_TYPE_default;
            }
        }
        return mImageType;
    }

    public static int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

//	/**
//	 * 40063电信插卡机
//	 * 
//	 * @return
//	 */
//	public static String getIMEI(Context context) {
//		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		return telephonyManager.getDeviceId();
//	}

    public static String getUserAgent(Context context) {
        String ua = null;
        try {
            WebView webview;
            webview = new WebView(context);
            webview.layout(0, 0, 0, 0);
            WebSettings settings = webview.getSettings();
            ua = settings.getUserAgentString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ua;
    }

    static String sid = "31343773039E38373503834F33363985F32066396C23766A616321D3935839642BD34382353823D6263635372DD3866462373773739E30383583963F2B77695623105F363C56639A3362612382D861363B0312D23432635352D661323D8342D433326743338E37326556633F3335";
    static String deviceid = "2";

    // extends methods
    public static String getSid() {
//        return ZRSharePreferenceKeeper.getStringValue(mContext, ZRConstant.KEY_EXTRA_SID, "");
        return sid;
    }

    public static String getServerDeviceId() {
//        return ZRSharePreferenceKeeper.getStringValue(mContext, ZRConstant.KEY_EXTRA_SERVER_DEVICE_ID, "", false);
        return deviceid;
    }

    public static String getRid() {
        return String.valueOf(ZRUtils.getCurrentLongTime());
    }
}
