package com.zritc.colorfulfund.data;

import com.zritc.colorfulfund.utils.ZRUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRDownloadAppInfo extends ZRDataCell implements Serializable {

	private static final long serialVersionUID = 4681781299024648522L;

	public static final int DOWNLOAD_PROGRESS_MAX = 100;
	public static final int DOWNLOAD_PROGRESS_MIN = 0;
	public static final int DOWNLOAD_PROGRESS_NOT_STARTED = -1;

	public static final int APP_TYPE_CLIENT = 0;
	public static final int APP_TYPE_RECOMMEND = 1;

	private int mPositionID;
	private String mAppID;
	private String mIconUrl;
	private String mAppName;
	private String mDesc;
	private String mSrc;
	private String mDownloadUrl;
	private String mDownloadTimes;
	private String mFileName;
	private int mProgress = DOWNLOAD_PROGRESS_NOT_STARTED; // 当前下载进度
	private int mSize; // app 大小
	// private String mDownloadCount;
	private int mType = APP_TYPE_RECOMMEND;

	public int getPositionID() {
		return mPositionID;
	}

	@Override
	public String getID() {
		return mAppID;
	}

	@Override
	public void setID(String id) {
		mAppID = id;
	}

	public String getIconUrl() {
		return mIconUrl;
	}

	public void setName(String appName) {
		mAppName = appName;
	}

	public void setDownloadUrl(String downloadUrl) {
		mDownloadUrl = downloadUrl;
		mFileName = ZRUtils.getMD5(mDownloadUrl);
	}

	public int getType() {
		return mType;
	}

	public void setType(int type) {
		mType = type;
	}

	public boolean isClient() {
		return mType == APP_TYPE_CLIENT;
	}

	public boolean isRecommend() {
		return mType == APP_TYPE_RECOMMEND;
	}

	public String getName() {
		return mAppName;
	}

	public String getDesc() {
		return mDesc;
	}

	public String getSrc() {
		return mSrc;
	}

	public String getDownloadUrl() {
		return mDownloadUrl;
	}

	public String getFileName() {
		return mFileName;
	}

	public void addDownloadTimes() {
		mDownloadTimes = String.valueOf(Integer.parseInt(mDownloadTimes) + 1);
	}

	public String getDownloadTimes() {
		return mDownloadTimes;
	}

	public int getProgress() {
		return mProgress;
	}

	public void setProgress(int progress) {
		mProgress = progress;
	}

	public int getSize() {
		return mSize;
	}

	public void setSize(int mSize) {
		this.mSize = mSize;
	}

	// public String getDownloadCount() {
	// return mDownloadCount;
	// }

	@Override
	public ZRDownloadAppInfo initFromJsonString(String str)
			throws JSONException {
		JSONObject json = new JSONObject(str);
		// mAppID = json.getString(UPConstant.KEY_ID);
		// mIconUrl = json.getString(UPConstant.KEY_ICON_URL);
		// mAppName = json.getString(UPConstant.KEY_NAME);
		// mDesc = json.getString(UPConstant.KEY_DESC);
		// mSrc = json.getString(UPConstant.KEY_SRC);
		// mDownloadUrl = json.getString(UPConstant.KEY_DOWNLOAD_URL);
		// mFileName = UPUtils.getMD5(mDownloadUrl);
		// String times = json.optString(UPConstant.KEY_DOWNLOAD_TIMES);
		// mDownloadTimes = TextUtils.isEmpty(times) ? "0" : times;
		// mDownloadCount = json.getString(UPConstant.KEY_DOWNLOAD_COUNT);
		return this;
	}

	@Override
	public String getJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		// json.put(UPConstant.KEY_ID, mAppID);
		// json.put(UPConstant.KEY_ICON_URL, mIconUrl);
		// json.put(UPConstant.KEY_NAME, mAppName);
		// json.put(UPConstant.KEY_DESC, mDesc);
		// json.put(UPConstant.KEY_SRC, mSrc);
		// json.put(UPConstant.KEY_DOWNLOAD_URL, mDownloadUrl);
		// json.put(UPConstant.KEY_DOWNLOAD_TIMES, mDownloadTimes);
		// json.put(UPConstant.KEY_DOWNLOAD_COUNT, mDownloadCount);
		return json.toString();
	}

}
