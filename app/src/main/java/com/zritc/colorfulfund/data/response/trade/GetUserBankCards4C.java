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
			return "UserBankCardList{" +
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
			
			
    	
    	return this;
    }
}
