package com.zritc.colorfulfund.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.zritc.colorfulfund.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 应用启动图标未读消息数显示 工具类  (效果如：QQ、微信、未读短信 等应用图标)<br/>
 * 依赖于第三方手机厂商(如：ADWLauncher、Apex Launcher、HTC、Nova、Solid Launcher、Sony、小米、三星、华为)的Launcher定制、原生系统不支持该特性<br/>
 * 该类 支持的设备有 小米、三星、华为、索尼等【其中小米、三星、华为亲测有效、索尼等未验证】
 *
 * <p>
 * Created by Chang.Xiao on 2016/6/7.
 *
 * @version 1.0
 */
public final class ZRShortcutBadger {

    private static final List<Class<? extends Badger>> BADGERS = new LinkedList<Class<? extends Badger>>();

    static {
        BADGERS.add(AdwHomeBadger.class);
        BADGERS.add(ApexHomeBadger.class);
        BADGERS.add(NewHtcHomeBadger.class); // HTC Badge
        BADGERS.add(NovaHomeBadger.class); // Nova Badge
        BADGERS.add(SolidHomeBadger.class);
        BADGERS.add(SonyHomeBadger.class);
        BADGERS.add(XiaomiHomeBadger.class);
        BADGERS.add(AsusHomeLauncher.class);
        BADGERS.add(HuaweiHomeBadger.class); // Huawei Badge
    }

    private static Badger sShortcutBadger;
    private static ComponentName sComponentName;

    /**
     * Tries to update the notification count
     * @param context Caller context
     * @param badgeCount Desired badge count
     * @return true in case of success, false otherwise
     */
    public static boolean applyCount(Context context, int badgeCount) {
        try {
            applyCountOrThrow(context, badgeCount);
            return true;
        } catch (ShortcutBadgeException e) {
            ZRLog.e("Unable to execute badge:" + e.getMessage());
            return false;
        }
    }

    /**
     * Tries to update the notification count, throw a {@link ShortcutBadgeException} if it fails
     * @param context Caller context
     * @param badgeCount Desired badge count
     */
    public static void applyCountOrThrow(Context context, int badgeCount) throws ShortcutBadgeException {
        if (sShortcutBadger == null)
            initBadger(context);

        try {
            sShortcutBadger.executeBadge(context, sComponentName, badgeCount);
        } catch (Throwable e) {
            throw new ShortcutBadgeException("Unable to execute badge:" + e.getMessage());
        }
    }

    /**
     * Tries to remove the notification count
     * @param context Caller context
     * @return true in case of success, false otherwise
     */
    public static boolean removeCount(Context context) {
        return applyCount(context, 0);
    }

    /**
     * Tries to remove the notification count, throw a {@link ShortcutBadgeException} if it fails
     * @param context Caller context
     */
    public static void removeCountOrThrow(Context context) throws ShortcutBadgeException {
        applyCountOrThrow(context, 0);
    }

    private static void initBadger(Context context) {
        //find the home launcher Package
        try {
            sComponentName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent();

            ZRLog.d("Finding badger");

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
            String currentHomePackage = resolveInfo.activityInfo.packageName;

            for (Class<? extends Badger> badger : BADGERS) {
                Badger shortcutBadger = badger.newInstance();
                if (shortcutBadger.getSupportLaunchers().contains(currentHomePackage)) {
                    sShortcutBadger = shortcutBadger;
                    break;
                }
            }

            if (sShortcutBadger == null && Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                sShortcutBadger = new XiaomiHomeBadger();
                return;
            }
        } catch (Exception e) {
            ZRLog.e(e.getMessage(), e);
        }

        if (sShortcutBadger == null)
            sShortcutBadger = new DefaultBadger();


        ZRLog.d( "Current badger:" + sShortcutBadger.getClass().getCanonicalName());
    }

    // Avoid anybody to instantiate this class
    private ZRShortcutBadger() {

    }

    /**
     * 获取系统属性
     *
     * @param propName
     * @return
     */
    private static String getSystemProperty(String propName){
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            ZRLog.e("Unable to read sysprop " + propName, ex);
            return null;
        }
        finally {
            if(input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    ZRLog.e("Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    public interface Badger {

        /**
         * Called when user attempts to update notification count
         * @param context Caller context
         * @param componentName Component containing package and class name of calling application's
         *                      launcher activity
         * @param badgeCount Desired notification count
         * @throws ShortcutBadgeException
         */
        void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException;

        /**
         * Called to let {@link ZRShortcutBadger} knows which launchers are supported by this badger. It should return a
         * {@link List<String>} containing supported launchers package names
         */
        List<String> getSupportLaunchers();
    }

    public static final class ShortcutBadgeException extends Exception {
        public ShortcutBadgeException(String message) {
            super(message);
        }
    }

    /**
     * 向三星(其他未定义的机型)手机发送未读消息数广播
     */
    public static class DefaultBadger implements Badger {

        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            Intent intent = new Intent(INTENT_ACTION);
            intent.putExtra(INTENT_EXTRA_BADGE_COUNT, badgeCount);
            intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
            intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, componentName.getClassName());
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return new ArrayList<String>(0);
        }
    }

    public static final class AdwHomeBadger implements Badger {

        public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
        public static final String PACKAGENAME = "PNAME";
        public static final String COUNT = "COUNT";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {

            Intent intent = new Intent(INTENT_UPDATE_COUNTER);
            intent.putExtra(PACKAGENAME, componentName.getPackageName());
            intent.putExtra(COUNT, badgeCount);
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList(
                    "org.adw.launcher",
                    "org.adwfreak.launcher"
            );
        }
    }

    public static final class ApexHomeBadger implements Badger {

        private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
        private static final String PACKAGENAME = "package";
        private static final String COUNT = "count";
        private static final String CLASS = "class";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {

            Intent intent = new Intent(INTENT_UPDATE_COUNTER);
            intent.putExtra(PACKAGENAME, componentName.getPackageName());
            intent.putExtra(COUNT, badgeCount);
            intent.putExtra(CLASS, componentName.getClassName());
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.anddoes.launcher");
        }
    }

    public static final class AsusHomeLauncher implements Badger {

        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            Intent intent = new Intent(INTENT_ACTION);
            intent.putExtra(INTENT_EXTRA_BADGE_COUNT, badgeCount);
            intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
            intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, componentName.getClassName());
            intent.putExtra("badge_vip_count", 0);
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.asus.launcher");
        }
    }

    public static final class NewHtcHomeBadger implements Badger {

        public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
        public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
        public static final String PACKAGENAME = "packagename";
        public static final String COUNT = "count";
        public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
        public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {

            Intent intent1 = new Intent(INTENT_SET_NOTIFICATION);
            intent1.putExtra(EXTRA_COMPONENT, componentName.flattenToShortString());
            intent1.putExtra(EXTRA_COUNT, badgeCount);
            context.sendBroadcast(intent1);

            Intent intent = new Intent(INTENT_UPDATE_SHORTCUT);
            intent.putExtra(PACKAGENAME, componentName.getPackageName());
            intent.putExtra(COUNT, badgeCount);
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.htc.launcher");
        }
    }

    /**
     * Shortcut Badger support for Nova Launcher.
     * TeslaUnread must be installed.
     * User: Gernot Pansy
     * Date: 2014/11/03
     * Time: 7:15
     */
    public static final class NovaHomeBadger implements Badger {

        private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
        private static final String COUNT = "count";
        private static final String TAG = "tag";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
                contentValues.put(COUNT, badgeCount);
                context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
            } catch (IllegalArgumentException ex) {
            /* Fine, TeslaUnread is not installed. */
            } catch (Exception ex) {

            /* Some other error, possibly because the format
            of the ContentValues are incorrect. */

                throw new ShortcutBadgeException(ex.getMessage());
            }
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.teslacoilsw.launcher");
        }
    }

    public static final class SolidHomeBadger implements Badger {

        private static final String INTENT_ACTION_UPDATE_COUNTER = "com.majeur.launcher.intent.action.UPDATE_BADGE";
        private static final String INTENT_EXTRA_PACKAGENAME = "com.majeur.launcher.intent.extra.BADGE_PACKAGE";
        private static final String INTENT_EXTRA_COUNT = "com.majeur.launcher.intent.extra.BADGE_COUNT";
        private static final String INTENT_EXTRA_CLASS = "com.majeur.launcher.intent.extra.BADGE_CLASS";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            Intent intent = new Intent(INTENT_ACTION_UPDATE_COUNTER);
            intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
            intent.putExtra(INTENT_EXTRA_COUNT, badgeCount);
            intent.putExtra(INTENT_EXTRA_CLASS, componentName.getClassName());
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.majeur.launcher");
        }
    }

    public static final class SonyHomeBadger implements Badger {

        private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
        private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
        private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
        private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            Intent intent = new Intent(INTENT_ACTION);
            intent.putExtra(INTENT_EXTRA_PACKAGE_NAME, componentName.getPackageName());
            intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, componentName.getClassName());
            intent.putExtra(INTENT_EXTRA_MESSAGE, String.valueOf(badgeCount));
            intent.putExtra(INTENT_EXTRA_SHOW_MESSAGE, badgeCount > 0);
            context.sendBroadcast(intent);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList("com.sonyericsson.home");
        }
    }

    public static final class XiaomiHomeBadger implements Badger {

        public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
        public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
        public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            // MIUI7版本
            String miuiVersion = getSystemProperty("ro.miui.ui.version.name");
            if ("V7".equals(miuiVersion)) {
                showAtMIUI7(context, badgeCount);
                return;
            }
            try {
                Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
                Object miuiNotification = miuiNotificationClass.newInstance();
                Field field = miuiNotification.getClass().getDeclaredField("messageCount");
                field.setAccessible(true);
                field.set(miuiNotification, String.valueOf(badgeCount == 0 ? "" : badgeCount)); // 设置信息数-->这种发送必须是miui 6才行
            } catch (Exception e) {
                // miui 6之前的版本
                Intent localIntent = new Intent(
                        INTENT_ACTION);
                localIntent.putExtra(EXTRA_UPDATE_APP_COMPONENT_NAME, componentName.getPackageName() + "/" + componentName.getClassName());
                localIntent.putExtra(EXTRA_UPDATE_APP_MSG_TEXT, String.valueOf(badgeCount == 0 ? "" : badgeCount));
                context.sendBroadcast(localIntent);
            }
        }

        private void showAtMIUI7(Context context, int badgeCount) {
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle("多彩基金").setContentText("有" + badgeCount + "条新消息").setSmallIcon(R.mipmap.ic_launcher);
            Notification notification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notification = builder.build();
            }
            try {
                Field field = notification.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notification);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, badgeCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mNotificationManager.notify(0,notification);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList(
                    "com.miui.miuilite",
                    "com.miui.home",
                    "com.miui.miuihome",
                    "com.miui.miuihome2",
                    "com.miui.mihome",
                    "com.miui.mihome2"
            );
        }
    }

    public static final class HuaweiHomeBadger implements Badger {

        private static final String LOG_TAG = HuaweiHomeBadger.class.getSimpleName();

        @Override
        public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
            String launcherClassName = componentName.getClassName();
            if (launcherClassName == null) {
                ZRLog.d(LOG_TAG, "Main activity is null");
                return;
            }
            Bundle extra = new Bundle();
            extra.putString("package", context.getPackageName());
            extra.putString("class", launcherClassName);
            extra.putInt("badgenumber", badgeCount);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
        }

        @Override
        public List<String> getSupportLaunchers() {
            return Arrays.asList(
                    "com.huawei.android.launcher"
            );
        }
    }
}
