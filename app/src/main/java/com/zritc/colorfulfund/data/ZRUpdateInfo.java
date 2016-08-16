package com.zritc.colorfulfund.data;

import android.text.TextUtils;

import com.zritc.colorfulfund.utils.ZRConstant;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Update info.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public class ZRUpdateInfo implements Serializable {
	private static final long serialVersionUID = -8432255474138548764L;

	private boolean mForceUpdate;// 是否强制升级
	private String mUpdateCode;
	private String mUpdateDesc;
	private String mUpdateUrl;

	public boolean isForceUpdate() {
		return mForceUpdate;
	}

	public boolean hasUpdate() {
		try {
			// 本地version和服务器version一一比对，从第一位开始比大小
			return ZRUtils.compareVersion(ZRDeviceInfo.getClientVersionName(),
					mUpdateCode) < 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getUpdateCode() {
		return mUpdateCode;
	}

	public String getUpdateDesc() {
		return TextUtils.isEmpty(mUpdateDesc) ? "尊敬的用户：\n欢迎您的到来，弥达斯金融感到荣幸和踏实。"
				: mUpdateDesc;
	}

	public String getUpdateUrl() {
		return mUpdateUrl;
	}

	public ZRUpdateInfo initFromJson(JSONObject obj) throws JSONException {
		ZRUpdateInfo info = new ZRUpdateInfo();
		info.mForceUpdate = obj.optBoolean(ZRConstant.KEY_NEED_UPDATE);
		info.mUpdateCode = obj.optString(ZRConstant.KEY_VERSION);
		info.mUpdateDesc = obj.optString(ZRConstant.KEY_DESC)
				.replace("\\r", "\r").replace("\\n", "\n");
		info.mUpdateUrl = obj.optString(ZRConstant.KEY_UPDATE_URL);
		return info;
	}
}