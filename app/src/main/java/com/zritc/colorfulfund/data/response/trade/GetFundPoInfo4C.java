package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




/**
 * Net Response Bean
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetFundPoInfo4C implements Serializable {

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
	 * 组合名称
	 */
	public String poName = "";

	/**
	 * 组合代码
	 */
	public String poCode = "";

	/**
	 * 
	 */
	public List<PoFundList4C> poFundList4C;
    
	/**
     * poFundList4C
     */
    public class PoFundList4C implements Serializable {

	/**
	 * 基金代码
	 */
	public String fundCode = "";

	/**
	 * 基金名称
	 */
	public String fundName = "";

	/**
	 * 用户购买组合中基金资产占比
	 */
	public double poPercentage;
		
		@Override
		public String toString() {
			return "PoFundList4C{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage=" + poPercentage +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetFundPoInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", poName='" + poName + '\'' +
				", poCode='" + poCode + '\'' +
				", poFundList4C=" + poFundList4C +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetFundPoInfo4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("poName")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.poName = jsonObject.optString("poName");
	    	if (jsonObject.isNull("poCode")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.poCode = jsonObject.optString("poCode");
	    	if (jsonObject.isNull("poFundList4C")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poFundList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray poFundList4CArray = jsonObject.optJSONArray("poFundList4C");
			this.poFundList4C = new ArrayList<>();
			
			if (null != poFundList4CArray && poFundList4CArray.length() > 0) {
				for(int poFundList4Ci = 0; poFundList4Ci < poFundList4CArray.length(); poFundList4Ci++) {
					JSONObject jsonObjectPoFundList4C = poFundList4CArray.optJSONObject(poFundList4Ci);
			PoFundList4C poFundList4C = new PoFundList4C();
		
	    	if (jsonObjectPoFundList4C.isNull("fundCode")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.fundCode = jsonObjectPoFundList4C.optString("fundCode");
	    	if (jsonObjectPoFundList4C.isNull("fundName")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.fundName = jsonObjectPoFundList4C.optString("fundName");
	    	if (jsonObjectPoFundList4C.isNull("poPercentage")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.poPercentage = jsonObjectPoFundList4C.optDouble("poPercentage");
					
					this.poFundList4C.add(poFundList4C);
				}
			}
			
    	
    	return this;
    }
}
