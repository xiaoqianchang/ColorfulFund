package com.zritc.colorfulfund.data.response.mine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 评估用户风险等级
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					EvaluateUserRiskLevel
 * svcCaption( 服务中文名，可用于注释): 	评估用户风险等级
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/evaluateUserRiskLevel
 * comments(服务详细备注，可用于注释): 		评估用户风险等级
 * <p>
 * Created by Chang.Xiao on .
 */
public class EvaluateUserRiskLevel implements Serializable {

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
	 * 用户风险等级
	 */
	public String riskLevel = "";
    
    
	@Override
	public String toString() {
		return "EvaluateUserRiskLevel{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", riskLevel='" + riskLevel + '\'' +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized EvaluateUserRiskLevel parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("riskLevel")) {
	    		Log.d("EvaluateUserRiskLevel", "has no mapping for key " + "riskLevel" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.riskLevel = jsonObject.optString("riskLevel");
    	
    	return this;
    }
}
