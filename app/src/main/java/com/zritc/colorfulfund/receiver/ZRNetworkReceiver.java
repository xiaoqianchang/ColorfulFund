package com.zritc.colorfulfund.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zritc.colorfulfund.utils.ZRToastFactory;

/**
 * Created by Midas on 16/7/5.
 */
public class ZRNetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (null == activeInfo)
            ZRToastFactory.getToast(context, "网络连接已断开").show();
        else {
            ZRToastFactory.getToast(context, "网络已连接").show();
        }
    }

}
