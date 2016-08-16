package com.zritc.colorfulfund.utils;

import android.content.Context;

/**
 * String util used for application. Almost all Strings used for application are
 * come from a xml file, you can get the String with a defined String name or
 * resource ID.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRStrings {

//	private static Context mContext;

	/**
	 * Get String by string name.
	 * 
	 * @param name
	 *            The name of the desired String.
	 * @return <b>String</b> The associated String.
	 */
	public static String get(Context mContext, String name) {
//		mContext = context;
		int id;
		try {
			id = mContext.getResources().getIdentifier(name, "string",
					mContext.getPackageName());
			if (0 != id) {
				return mContext.getString(id);
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			ZRLog.w("String get exception:" + name);
			return "";
		}
	}

//	/**
//	 * Get String by string resource ID.
//	 * 
//	 * @param resID
//	 *            The resource ID of the desired String.
//	 * @return <b>String</b> The associated String.
//	 */
//	public static String get(int resID) {
//		String resFullName = mContext.getResources().getResourceName(resID);
//		String resName = resFullName
//				.substring(resFullName.lastIndexOf("/") + 1);
//		return resName;
//	}
}
