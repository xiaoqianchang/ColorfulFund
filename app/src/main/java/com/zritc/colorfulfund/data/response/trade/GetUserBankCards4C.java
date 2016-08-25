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
	public List<UserBankCardList> userBankCardList;
    
	/**
	 * userBankCardList
     */
	public class UserBankCardList implements Serializable {

	/**
	 * 用户交易账号id，每个银行卡有一个交易账号id
	 */
	public String userPaymentId = "";

	/**
		 * 银行卡号
		 */
		public String bankCardNo = "";

		/**
	 * 银行名称 如：工商银行
	 */
	public String bankName = "";

	/**
	 * 银行代码
	 */
	public String paymentType = "";

	/**
	 * logo
	 */
		public String bankLogo = "";

	/**
	 * 每笔限额
	 */
		public String maxRapidPayAmountPerTxn = "";

	/**
	 * 每天限额
	 */
		public String maxRapidPayAmountPerDay = "";

	/**
	 * 每月限额
	 */
		public String maxRapidPayAmountPerMonth = "";

	/**
	 * 每日最大次数
	 */
		public String maxRapidPayTxnCountPerDay = "";
		
		@Override
		public String toString() {
			return "UserBankCardList{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", bankCardNo='" + bankCardNo + '\'' +
					", bankName='" + bankName + '\'' +
					", paymentType='" + paymentType + '\'' +
					", bankLogo='" + bankLogo + '\'' +
					", maxRapidPayAmountPerTxn='" + maxRapidPayAmountPerTxn + '\'' +
					", maxRapidPayAmountPerDay='" + maxRapidPayAmountPerDay + '\'' +
					", maxRapidPayAmountPerMonth='" + maxRapidPayAmountPerMonth + '\'' +
					", maxRapidPayTxnCountPerDay='" + maxRapidPayTxnCountPerDay + '\'' +
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
				", userBankCardList=" + userBankCardList +
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
		if (jsonObject.isNull("userBankCardList")) {
			Log.d("GetUserBankCards4C", "has no mapping for key " + "userBankCardList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		JSONArray userBankCardListArray = jsonObject.optJSONArray("userBankCardList");
		this.userBankCardList = new ArrayList<>();
			
		if (null != userBankCardListArray && userBankCardListArray.length() > 0) {
			for(int userBankCardListi = 0; userBankCardListi < userBankCardListArray.length(); userBankCardListi++) {
				JSONObject jsonObjectUserBankCardList = userBankCardListArray.optJSONObject(userBankCardListi);
				UserBankCardList userBankCardList = new UserBankCardList();
		
				if (jsonObjectUserBankCardList.isNull("userPaymentId")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.userPaymentId = jsonObjectUserBankCardList.optString("userPaymentId");
				if (jsonObjectUserBankCardList.isNull("bankCardNo")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userBankCardList.bankCardNo = jsonObjectUserBankCardList.optString("bankCardNo");
				if (jsonObjectUserBankCardList.isNull("bankName")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.bankName = jsonObjectUserBankCardList.optString("bankName");
				if (jsonObjectUserBankCardList.isNull("paymentType")) {
	    		Log.d("GetUserBankCards4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.paymentType = jsonObjectUserBankCardList.optString("paymentType");
				if (jsonObjectUserBankCardList.isNull("bankLogo")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.bankLogo = jsonObjectUserBankCardList.optString("bankLogo");
				if (jsonObjectUserBankCardList.isNull("maxRapidPayAmountPerTxn")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.maxRapidPayAmountPerTxn = jsonObjectUserBankCardList.optString("maxRapidPayAmountPerTxn");
				if (jsonObjectUserBankCardList.isNull("maxRapidPayAmountPerDay")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.maxRapidPayAmountPerDay = jsonObjectUserBankCardList.optString("maxRapidPayAmountPerDay");
				if (jsonObjectUserBankCardList.isNull("maxRapidPayAmountPerMonth")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.maxRapidPayAmountPerMonth = jsonObjectUserBankCardList.optString("maxRapidPayAmountPerMonth");
				if (jsonObjectUserBankCardList.isNull("maxRapidPayTxnCountPerDay")) {
					Log.d("GetUserBankCards4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userBankCardList.maxRapidPayTxnCountPerDay = jsonObjectUserBankCardList.optString("maxRapidPayTxnCountPerDay");
					
				this.userBankCardList.add(userBankCardList);
				}
			}
			
    	
    	return this;
    }
}
