package com.zritc.colorfulfund.utils;

/**
 * Global configuration for application.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2015-10-19
 * @lastUpdate 2015-10-19
 */
public class ZRAppConfig {

    public static final String SERVER_URL_INIT_1 = "http://f.midasjr.com";
    public static final String SERVER_URL_INIT_2 = "http://b.midasjr.com";
    public static final String SERVER_URL_INIT_3 = "http://139.198.2.165";

    public static final String INIT_M_PATH = "/html/init/a/";
    public static final String INIT_L_PATH = "/initinfo.shtml";

    public static String initinfo1 = SERVER_URL_INIT_1 + INIT_M_PATH + ZRDeviceInfo.getApiVersion() + INIT_L_PATH;
    public static String initinfo2 = SERVER_URL_INIT_2 + INIT_M_PATH + ZRDeviceInfo.getApiVersion() + INIT_L_PATH;
    public static String initinfo3 = SERVER_URL_INIT_3 + INIT_M_PATH + ZRDeviceInfo.getApiVersion() + INIT_L_PATH;

    public static String WORK_FOLDER = ZRFileUtils.getWorkFolder();

    public static String APP_IMG_DIR = ZRFileUtils.getImageFolder("image/");

    public static String APP_DOWNLOAD_DIR = WORK_FOLDER + "download/";

    public static String APP_LOG_DIR = WORK_FOLDER + "mdsjrLog/%s/";

    public static String APP_CRASH_LOG_DIR = APP_LOG_DIR + "crash/";

    public static final boolean CRASH_LOG_TO_FILE = true;

    public static final boolean LOG_TO_FILE = true;

    public static final boolean DEBUG = true;

}
