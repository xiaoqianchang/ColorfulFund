package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 准备绑定支付
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					PrepareBindPayment
 * svcCaption( 服务中文名，可用于注释): 	准备绑定支付
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/prepareBindPayment
 * comments(服务详细备注，可用于注释): 		prepareBindPayment接口的作用是让用户填入绑卡要素信息，并向用户手机发出带有验证码的短信。           bindPayment接口的作用是让用户填入绑卡要素信息和短信验证码。           两个接口的调用顺序必须保证先调用prepareBindPayment，拿到验证码后调用bindPayment。
 * <p>
 * Created by Chang.Xiao on .
 */
public class PrepareBindPayment implements Serializable {

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

	/**
	 * 该用户是否为首次请求绑卡，如果是，返回true，前端会提示用户做交易密码设置。
	 */
	public boolean isFirstBind;
    
    
	@Override
	public String toString() {
		return "PrepareBindPayment{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", isFirstBind=" + isFirstBind +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized PrepareBindPayment parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("isFirstBind")) {
	    		Log.d("PrepareBindPayment", "has no mapping for key " + "isFirstBind" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.isFirstBind = jsonObject.optBoolean("isFirstBind");
    	
    	return this;
    }
}
