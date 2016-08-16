package com.zritc.colorfulfund.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Custom crash handler. Catch uncaught exceptions, write them to file, show a
 * toast and then close application.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRCrashHandler implements UncaughtExceptionHandler {
    private static final int TIME_WAIT = 3000;
    private static ZRCrashHandler sInstance = new ZRCrashHandler();

    private Context mContext;
    private UncaughtExceptionHandler mOriHandler;

    public static ZRCrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
        mOriHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();

        if (ZRAppConfig.CRASH_LOG_TO_FILE) {
            ZRLog.writeCrashFile(ex);
        }

        NotificationManager manager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,
                        ZRStrings.get(mContext, "error_crash"),
                        Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(TIME_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != mOriHandler) {
            mOriHandler.uncaughtException(thread, ex);
        }
    }
}
