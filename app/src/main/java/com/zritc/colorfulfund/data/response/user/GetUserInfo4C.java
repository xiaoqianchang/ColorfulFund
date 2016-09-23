package com.zritc.colorfulfund.data.response.user;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Net Response Bean 获取用户信息
 *
 * package: 						com.zrt.dc.controllers.user
 * svcName(服务名): 					GetUserInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户信息
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/user/getUserInfo4C
 * comments(服务详细备注，可用于注释): 		获取用户信息
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserInfo4C implements Serializable {

	/**
	 * session id
	 */
	public String sid = "";

	/**
	 * 请求 id
	 */
	public String rid = "";

	/**
	 * 返回代码
	 */
	public String code = "";

	/**
	 * 返回信息
	 */
	public String msg = "";

	/**
	 * 接口类型
	 */
	public String optype = "";

	public UserInfo userInfo;
    
	/**
     * userInfo
     */
    public class UserInfo implements Serializable {

	/**
	 * 电话
	 */
	public String phone = "";

	/**
	 * 昵称
	 */
	public String nickname = "";

	/**
	 * 头像
	 */
	public String photoUrl = "";
		
		@Override
		public String toString() {
			return "UserInfo{" +
					"phone='" + phone + '\'' +
					", nickname='" + nickname + '\'' +
					", photoUrl='" + photoUrl + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetUserInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userInfo=" + userInfo +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserInfo4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("userInfo")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "userInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserInfo = jsonObject.optJSONObject("userInfo");
			UserInfo userInfo = new UserInfo();
		
	    	if (jsonObjectUserInfo .isNull("phone")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "phone" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.phone = jsonObjectUserInfo .optString("phone");
	    	if (jsonObjectUserInfo .isNull("nickname")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "nickname" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.nickname = jsonObjectUserInfo .optString("nickname");
	    	if (jsonObjectUserInfo .isNull("photoUrl")) {
	    		Log.d("GetUserInfo4C", "has no mapping for key " + "photoUrl" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.photoUrl = jsonObjectUserInfo .optString("photoUrl");
	    		
	    		this.userInfo = userInfo;
    	
    	return this;
    }
}
