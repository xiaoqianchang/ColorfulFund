package com.zritc.colorfulfund.share;

/**
 * @author Midas.
 * @version 1.0
 * @createDate 2015-11-23
 * @lastUpdate 2015-11-23
 */
public interface IUPShareRequestCallback {
	public void onShareSucceed(int platform);

	public void onShareFailed(int platform, int errorCode, String errorMsg);

	public void onShareCancelled(int platform);
}
