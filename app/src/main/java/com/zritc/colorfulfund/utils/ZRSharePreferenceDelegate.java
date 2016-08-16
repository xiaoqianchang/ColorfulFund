package com.zritc.colorfulfund.utils;


import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-10-23
 * @lastUpdate 2015-10-23
 */
public class ZRSharePreferenceDelegate {
	@SuppressLint("NewApi")
	public static final void commit(Editor editor) {
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.GINGERBREAD) {
			editor.commit();
		} else {
			editor.apply();
		}
	}
}
