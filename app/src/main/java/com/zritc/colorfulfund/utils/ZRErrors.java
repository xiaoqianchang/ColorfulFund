package com.zritc.colorfulfund.utils;

import android.content.Context;

/**
 * Local errors in application.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRErrors {
	public static final String SUCCESS = "1000";
	public static final String ERROR = "1001";
	public static final String CUP_COMPLETE = "0000";
	public static final String JSON_EXCEPTION = "5002";
	public static final String ERROR_LOGIN_TIMEOUT = "9000";

	public static final String ERROR_LOCAL_BEGIN = "10000";
	public static final String ERROR_NETWORK = "10001";
	public static final String ERROR_RESPONSE_CODE = "10002";
	public static final String ERROR_RESPONSE_FORMAT = "10003";
	public static final String ERROR_ENCRYPTION_KEY_TIMEOUT = "10004";
	public static final String ERROR_STORAGE_NOT_ENOUGHT = "10005";
	public static final String ERROR_NO_AREA = "10006";
	public static final String ERROR_DOWNLOAD_FILE = "10007";
	public static final String ERROR_UPLOAD_FILE = "10008";
	public static final String ERROR_LOCATING_TIMEOUT = "10009";
	public static final String ERROR_LOCATING_CONFIG = "10010";
	public static final String ERROR_HTTPS_TIME_SETTINGS = "10011";

	/**
	 * Get error detail message name by error id. The message name can be used
	 * in {@link UPStrings#get(String name)}
	 * 
	 * @param context
	 * @param error
	 *            The ID of the desired error.
	 * @return <b>String</b> The associated detail message String name.
	 */
	public static String getLocalErrorMsg(Context context, String error) {
		return ZRStrings.get(context, getLocalErrorMsgId(error));
	}

	private static String getLocalErrorMsgId(String error) {
		if (ERROR.equals(error)) {
			return "error_network";
		} else if (JSON_EXCEPTION.equals(error)) { // json异常
			return "json_exception";
		} else if (ERROR_NETWORK.equals(error)) {
			return "error_network";
		} else if (ERROR_RESPONSE_FORMAT.equals(error)) {
			return "error_server";
		} else if (ERROR_LOGIN_TIMEOUT.equals(error)) {
			return "error_key_time_out";
		} else if (ERROR_ENCRYPTION_KEY_TIMEOUT.equals(error)) {
			return "error_key_time_out";
		} else if (ERROR_STORAGE_NOT_ENOUGHT.equals(error)) {
			return "error_storage_not_enough";
		} else if (ERROR_NO_AREA.equals(error)) {
			return "error_no_area";
		} else if (ERROR_UPLOAD_FILE.equals(error)) {
			return "error_upload_file";
		} else if (ERROR_DOWNLOAD_FILE.equals(error)) {
			return "error_download_file";
		} else if (ERROR_LOCATING_TIMEOUT.equals(error)) {
			return "error_locating_timeout";
		} else if (ERROR_HTTPS_TIME_SETTINGS.equals(error)) {
			return "error_https_time";
		} else if (ERROR_LOCATING_CONFIG.equals(error)) {
			return "error_locating_config";
		} else {
			return "error_unrecognized";
		}
	}
}
