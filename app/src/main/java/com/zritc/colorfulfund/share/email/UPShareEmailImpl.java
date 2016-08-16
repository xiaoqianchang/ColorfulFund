package com.zritc.colorfulfund.share.email;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zritc.colorfulfund.share.IUPSharePlatform;
import com.zritc.colorfulfund.share.IUPShareRequestCallback;
import com.zritc.colorfulfund.share.UPMediaMessage;
import com.zritc.colorfulfund.share.UPShareErrors;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public class UPShareEmailImpl implements IUPSharePlatform {
	private static final String SEND_EMAIL_URI = "mailto:";

	@Override
	public void share(Context context, int platform,
			IUPShareRequestCallback callback, String content) {
		Uri uri = Uri.parse(SEND_EMAIL_URI);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra(Intent.EXTRA_TEXT, content);

		try {
			context.startActivity(intent);
			if (null != callback) {
				callback.onShareSucceed(platform);
			}
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			if (null != callback) {
				callback.onShareFailed(platform, UPShareErrors.ERROR_UNSUPPORT,
						"");
			}
		}
	}

	@Override
	public void share(Context context, int platform,
			IUPShareRequestCallback callback, UPMediaMessage msg) {
		throw new UnsupportedOperationException();
	}

}
