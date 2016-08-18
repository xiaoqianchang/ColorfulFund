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
 * svcName(服务名): 					GetUserBankCards4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户已绑定的银行卡列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/getUserBCards
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserBankCards4C implements Serializable {

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
	public List<UserBankCardList4C> userBankCardList4C;
    
	/**
     * userBankCardList4C
     */
    public class UserBankCardList4C implements Serializable {

	/**
	 * 用户交易账号id，每个银行卡有一个交易账号id
	 */
	public String userPaymentId = "";

	/**
	 * 银行名称 如：工商银行
	 */
	public String bankName = "";

	/**
	 * 银行代码
	 */
	public String paymentType = "";

	/**
	 * 银行卡号
	 */
	public String bankCardNo = "";

	/**
	 * logo
	 */
	public String logoUrl = "";

	/**
	 * 每笔限额
	 */
	public double maxPayAmountPerTxn;

	/**
	 * 每天限额
	 */
	public double maxPayAmountPerDay;

	/**
	 * 每月限额
	 */
	public double maxPayAmountPerMonth;

	/**
	 * 每日最大次数
	 */
	public int maxPayTxnCountPerDay;

	/**
	 * 绑卡时间
	 */
	public long bindTime;
		
		@Override
		public String toString() {
			return "UserBankCardList4C{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", bankName='" + bankName + '\'' +
					", paymentType='" + paymentType + '\'' +
					", bankCardNo='" + bankCardNo + '\'' +
					", logoUrl='" + logoUrl + '\'' +
					", maxPayAmountPerTxn=" + maxPayAmountPerTxn +
					", maxPayAmountPerDay=" + maxPayAmountPerDay +
					", maxPayAmountPerMonth=" + maxPayAmountPerMonth +
					", maxPayTxnCountPerDay=" + maxPayTxnCountPerDay +
					", bindTime=" + bindTime +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetUserBankCards4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userBankCardList4C=" + userBankCardList4C +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserBankCards4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("userBankCardList4C")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "userBankCardList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userBankCardList4CArray = jsonObject.optJSONArray("userBankCardList4C");
			this.userBankCardList4C = new ArrayList<>();
			
			if (null != userBankCardList4CArray && userBankCardList4CArray.length() > 0) {
				for(int userBankCardList4Ci = 0; userBankCardList4Ci < userBankCardList4CArray.length(); userBankCardList4Ci++) {
					JSONObject jsonObjectUserBankCardList4C = userBankCardList4CArray.optJSONObject(userBankCardList4Ci);
			UserBankCardList4C userBankCardList4C = new UserBankCardList4C();
		
	    	if (jsonObjectUserBankCardList4C.isNull("userPaymentId")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.userPaymentId = jsonObjectUserBankCardList4C.optString("userPaymentId");
	    	if (jsonObjectUserBankCardList4C.isNull("bankName")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.bankName = jsonObjectUserBankCardList4C.optString("bankName");
	    	if (jsonObjectUserBankCardList4C.isNull("paymentType")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.paymentType = jsonObjectUserBankCardList4C.optString("paymentType");
	    	if (jsonObjectUserBankCardList4C.isNull("bankCardNo")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.bankCardNo = jsonObjectUserBankCardList4C.optString("bankCardNo");
	    	if (jsonObjectUserBankCardList4C.isNull("logoUrl")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "logoUrl" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.logoUrl = jsonObjectUserBankCardList4C.optString("logoUrl");
	    	if (jsonObjectUserBankCardList4C.isNull("maxPayAmountPerTxn")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "maxPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.maxPayAmountPerTxn = jsonObjectUserBankCardList4C.optDouble("maxPayAmountPerTxn");
	    	if (jsonObjectUserBankCardList4C.isNull("maxPayAmountPerDay")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "maxPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.maxPayAmountPerDay = jsonObjectUserBankCardList4C.optDouble("maxPayAmountPerDay");
	    	if (jsonObjectUserBankCardList4C.isNull("maxPayAmountPerMonth")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "maxPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.maxPayAmountPerMonth = jsonObjectUserBankCardList4C.optDouble("maxPayAmountPerMonth");
	    	if (jsonObjectUserBankCardList4C.isNull("maxPayTxnCountPerDay")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "maxPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.maxPayTxnCountPerDay = jsonObjectUserBankCardList4C.optInt("maxPayTxnCountPerDay");
	    	if (jsonObjectUserBankCardList4C.isNull("bindTime")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "bindTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userBankCardList4C.bindTime = jsonObjectUserBankCardList4C.optLong("bindTime");
					
					this.userBankCardList4C.add(userBankCardList4C);
				}
			}
			
    	
    	return this;
    }
}
