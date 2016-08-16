package com.zritc.colorfulfund.share;

import android.content.Context;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public interface IUPSharePlatform {
	public void share(Context context, int platform,
					  IUPShareRequestCallback callback, String content);

	public void share(Context context, int platform,
					  IUPShareRequestCallback callback, UPMediaMessage msg);
}
