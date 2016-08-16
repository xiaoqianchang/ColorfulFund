package com.zritc.colorfulfund.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Net utils used for application.
 * <p/>
 * Created by Chang.Xiao on 2016/7/1.
 *
 * @version 1.0
 */
public class ZRNetUtils {

    /**
     * 是否有可用网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network != null) {
            return network.isAvailable();
        }
        return false;
    }

    /**
     * Wifi是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnable(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * 判断wifi是否连接
     *
     * @param inContext
     * @return
     */
    public static boolean isWifiConnected(Context inContext) {
        Context context = inContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean iswifiActivity = false;
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo element : info) {
                    if (element.getTypeName().equals("WIFI")
                            && element.isConnected()) {
                        iswifiActivity = true;
                        break;
                    }
                }
            }
        }
        return iswifiActivity;
    }

    public static String getApnType(Context context) {
        String apntype = "ctnet";
        try {
            if (isWifiConnected(context)) {
                return "wifi";
            }
            Uri PREFERRED_APN_URI = Uri
                    .parse("content://telephony/carriers/preferapn");
            Cursor c = context.getContentResolver().query(PREFERRED_APN_URI,
                    null, null, null, null);
            c.moveToFirst();
            String s = "";
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getColumnName(i) + "," + c.getString(i) + "|";
            }
            String user = c.getString(c.getColumnIndex("user"));
            if (user.startsWith("ctnet")) {
                apntype = "ctnet";
            } else if (user.startsWith("ctwap")) {
                apntype = "ctwap";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apntype;
    }

    /**
     * 获得本机IP地址
     */
    public static String getLocalIpAddress() {
        String ipaddress = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().toLowerCase().equals("eth0") || intf.getName().toLowerCase().equals("wlan0")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::")) {// ipV6的地址
                                return ipaddress;
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipaddress;
    }
}
