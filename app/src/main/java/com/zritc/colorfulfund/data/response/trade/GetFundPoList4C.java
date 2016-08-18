package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户已绑定的银行卡列表
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetFundPoList4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户已绑定的银行卡列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/getFundPoList4C
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetFundPoList4C implements Serializable {

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
	 * 
	 */
	public List<FundPoList4C> fundPoList4C;
    
	/**
     * fundPoList4C
     */
    public class FundPoList4C implements Serializable {

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
		
		@Override
		public String toString() {
			return "FundPoList4C{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					", poFundList4C=" + poFundList4C +
					'}';
		}
    }
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
		return "GetFundPoList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", fundPoList4C=" + fundPoList4C +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetFundPoList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("fundPoList4C")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "fundPoList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray fundPoList4CArray = jsonObject.optJSONArray("fundPoList4C");
			this.fundPoList4C = new ArrayList<>();
			
			if (null != fundPoList4CArray && fundPoList4CArray.length() > 0) {
				for(int fundPoList4Ci = 0; fundPoList4Ci < fundPoList4CArray.length(); fundPoList4Ci++) {
					JSONObject jsonObjectFundPoList4C = fundPoList4CArray.optJSONObject(fundPoList4Ci);
			FundPoList4C fundPoList4C = new FundPoList4C();
		
	    	if (jsonObjectFundPoList4C.isNull("poName")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			fundPoList4C.poName = jsonObjectFundPoList4C.optString("poName");
	    	if (jsonObjectFundPoList4C.isNull("poCode")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			fundPoList4C.poCode = jsonObjectFundPoList4C.optString("poCode");
	    	if (jsonObjectFundPoList4C.isNull("poFundList4C")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poFundList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray poFundList4CArray = jsonObjectFundPoList4C.optJSONArray("poFundList4C");
			fundPoList4C.poFundList4C = new ArrayList<>();
			
			if (null != poFundList4CArray && poFundList4CArray.length() > 0) {
				for(int poFundList4Ci = 0; poFundList4Ci < poFundList4CArray.length(); poFundList4Ci++) {
					JSONObject jsonObjectPoFundList4C = poFundList4CArray.optJSONObject(poFundList4Ci);
			PoFundList4C poFundList4C = new PoFundList4C();
		
	    	if (jsonObjectPoFundList4C.isNull("fundCode")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.fundCode = jsonObjectPoFundList4C.optString("fundCode");
	    	if (jsonObjectPoFundList4C.isNull("fundName")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.fundName = jsonObjectPoFundList4C.optString("fundName");
	    	if (jsonObjectPoFundList4C.isNull("poPercentage")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poFundList4C.poPercentage = jsonObjectPoFundList4C.optDouble("poPercentage");
					
					fundPoList4C.poFundList4C.add(poFundList4C);
				}
			}
			
					
					this.fundPoList4C.add(fundPoList4C);
				}
			}
			
    	
    	return this;
    }
}
