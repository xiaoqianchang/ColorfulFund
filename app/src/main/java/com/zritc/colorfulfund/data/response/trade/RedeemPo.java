package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 组合赎回
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					RedeemPo
 * svcCaption( 服务中文名，可用于注释): 	组合赎回
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/redeemPo
 * comments(服务详细备注，可用于注释): 		赎回比例ratio 和赎回金额amount至少填一个，同时存在以赎回比例为准
 * <p>
 * Created by Chang.Xiao on .
 */
public class RedeemPo implements Serializable {

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
	 * 预计确认日期
	 */
	public long expectedConfirmDate;

	/**
	 * 预计到账日
	 */
	public long expectedTransferIntoDate;
    
    
	@Override
	public String toString() {
		return "RedeemPo{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", expectedConfirmDate=" + expectedConfirmDate +
				", expectedTransferIntoDate=" + expectedTransferIntoDate +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized RedeemPo parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("expectedConfirmDate")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "expectedConfirmDate" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.expectedConfirmDate = jsonObject.optLong("expectedConfirmDate");
	    	if (jsonObject.isNull("expectedTransferIntoDate")) {
	    		Log.d("RedeemPo", "has no mapping for key " + "expectedTransferIntoDate" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.expectedTransferIntoDate = jsonObject.optLong("expectedTransferIntoDate");
    	
    	return this;
    }
}
