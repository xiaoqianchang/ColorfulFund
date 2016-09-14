package com.zritc.colorfulfund.data.response.wish;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 取回心愿资金
 *
 * package: 						com.zrt.dc.controllers.trade.wish
 * svcName(服务名): 					WithdrawAssetFromWishlist4C
 * svcCaption( 服务中文名，可用于注释): 	取回心愿资金
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		172.16.101.201/tradewish/withdrawAssetFromWishlist4C
 * comments(服务详细备注，可用于注释): 		
 * <p>
 * Created by Chang.Xiao on .
 */
public class WithdrawAssetFromWishlist4C implements Serializable {

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
	 * 组合代码
	 */
	public String poCode = "";

	/**
	 * 场景类型
	 */
	public String sceneType = "";

	/**
	 * 场景ID
	 */
	public String sceneId = "";

	/**
	 * 
	 */
	public String drawAssetAmount = "";
    
    
	@Override
	public String toString() {
		return "WithdrawAssetFromWishlist4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", poCode='" + poCode + '\'' +
				", sceneType='" + sceneType + '\'' +
				", sceneId='" + sceneId + '\'' +
				", drawAssetAmount='" + drawAssetAmount + '\'' +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized WithdrawAssetFromWishlist4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("poCode")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.poCode = jsonObject.optString("poCode");
	    	if (jsonObject.isNull("sceneType")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "sceneType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sceneType = jsonObject.optString("sceneType");
	    	if (jsonObject.isNull("sceneId")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "sceneId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sceneId = jsonObject.optString("sceneId");
	    	if (jsonObject.isNull("drawAssetAmount")) {
	    		Log.d("WithdrawAssetFromWishl", "has no mapping for key " + "drawAssetAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.drawAssetAmount = jsonObject.optString("drawAssetAmount");
    	
    	return this;
    }
}
