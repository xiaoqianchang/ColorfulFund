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
public class GetUserPoList4C implements Serializable {

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
	public List<UserPostList4C> userPostList4C;
    
	/**
     * userPostList4C
     */
    public class UserPostList4C implements Serializable {

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
	public List<UserInfoPerBank4PoList4C> userInfoPerBank4PoList4C;
		
		@Override
		public String toString() {
			return "UserPostList4C{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					", userInfoPerBank4PoList4C=" + userInfoPerBank4PoList4C +
					'}';
		}
    }
	/**
     * userInfoPerBank4PoList4C
     */
    public class UserInfoPerBank4PoList4C implements Serializable {

	/**
	 * 用户交易账号id
	 */
	public String userPaymentId = "";

	/**
	 * 允许赎回最小金额
	 */
	public double minRedeemAmount;

	/**
	 * 允许赎回最大金额，如果允许赎回的最小和最大金额都为空，则只能允许全额赎回
	 */
	public double maxRedeemAmount;

	/**
	 * 银行名称
	 */
	public String bankName = "";

	/**
	 * 银行logo URL
	 */
	public String bankLogo = "";

	/**
	 * 支付类型 如工商银行 为bank：002
	 */
	public String paymentType = "";

	/**
	 * 每笔最大限额
	 */
	public double maxRapidPayAmountPerTxn;

	/**
	 * 每天最大限额
	 */
	public double maxRapidPayAmountPerDay;

	/**
	 * 每天最大限额
	 */
	public double maxRapidPayAmountPerMonth;

	/**
	 * 每天最大交易次数
	 */
	public int maxRapidPayTxnCountPerDay;

	/**
	 * 
	 */
	public List<UserFundListPerBank4PoList4C> userFundListPerBank4PoList4C;
		
		@Override
		public String toString() {
			return "UserInfoPerBank4PoList4C{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", minRedeemAmount=" + minRedeemAmount +
					", maxRedeemAmount=" + maxRedeemAmount +
					", bankName='" + bankName + '\'' +
					", bankLogo='" + bankLogo + '\'' +
					", paymentType='" + paymentType + '\'' +
					", maxRapidPayAmountPerTxn=" + maxRapidPayAmountPerTxn +
					", maxRapidPayAmountPerDay=" + maxRapidPayAmountPerDay +
					", maxRapidPayAmountPerMonth=" + maxRapidPayAmountPerMonth +
					", maxRapidPayTxnCountPerDay=" + maxRapidPayTxnCountPerDay +
					", userFundListPerBank4PoList4C=" + userFundListPerBank4PoList4C +
					'}';
		}
    }
	/**
     * userFundListPerBank4PoList4C
     */
    public class UserFundListPerBank4PoList4C implements Serializable {

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

	/**
	 * 可赎回的金额
	 */
	public double aviAmount;

	/**
	 * 用户购买组合中的基金当前资产占比
	 */
	public double totalAmount;
		
		@Override
		public String toString() {
			return "UserFundListPerBank4PoList4C{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage=" + poPercentage +
					", aviAmount=" + aviAmount +
					", totalAmount=" + totalAmount +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetUserPoList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userPostList4C=" + userPostList4C +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserPoList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("userPostList4C")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "userPostList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userPostList4CArray = jsonObject.optJSONArray("userPostList4C");
			this.userPostList4C = new ArrayList<>();
			
			if (null != userPostList4CArray && userPostList4CArray.length() > 0) {
				for(int userPostList4Ci = 0; userPostList4Ci < userPostList4CArray.length(); userPostList4Ci++) {
					JSONObject jsonObjectUserPostList4C = userPostList4CArray.optJSONObject(userPostList4Ci);
			UserPostList4C userPostList4C = new UserPostList4C();
		
	    	if (jsonObjectUserPostList4C.isNull("poName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPostList4C.poName = jsonObjectUserPostList4C.optString("poName");
	    	if (jsonObjectUserPostList4C.isNull("poCode")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPostList4C.poCode = jsonObjectUserPostList4C.optString("poCode");
	    	if (jsonObjectUserPostList4C.isNull("userInfoPerBank4PoList4C")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "userInfoPerBank4PoList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userInfoPerBank4PoList4CArray = jsonObjectUserPostList4C.optJSONArray("userInfoPerBank4PoList4C");
			userPostList4C.userInfoPerBank4PoList4C = new ArrayList<>();
			
			if (null != userInfoPerBank4PoList4CArray && userInfoPerBank4PoList4CArray.length() > 0) {
				for(int userInfoPerBank4PoList4Ci = 0; userInfoPerBank4PoList4Ci < userInfoPerBank4PoList4CArray.length(); userInfoPerBank4PoList4Ci++) {
					JSONObject jsonObjectUserInfoPerBank4PoList4C = userInfoPerBank4PoList4CArray.optJSONObject(userInfoPerBank4PoList4Ci);
			UserInfoPerBank4PoList4C userInfoPerBank4PoList4C = new UserInfoPerBank4PoList4C();
		
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("userPaymentId")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.userPaymentId = jsonObjectUserInfoPerBank4PoList4C.optString("userPaymentId");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("minRedeemAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "minRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.minRedeemAmount = jsonObjectUserInfoPerBank4PoList4C.optDouble("minRedeemAmount");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("maxRedeemAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.maxRedeemAmount = jsonObjectUserInfoPerBank4PoList4C.optDouble("maxRedeemAmount");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("bankName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.bankName = jsonObjectUserInfoPerBank4PoList4C.optString("bankName");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("bankLogo")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.bankLogo = jsonObjectUserInfoPerBank4PoList4C.optString("bankLogo");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("paymentType")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.paymentType = jsonObjectUserInfoPerBank4PoList4C.optString("paymentType");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("maxRapidPayAmountPerTxn")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.maxRapidPayAmountPerTxn = jsonObjectUserInfoPerBank4PoList4C.optDouble("maxRapidPayAmountPerTxn");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("maxRapidPayAmountPerDay")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.maxRapidPayAmountPerDay = jsonObjectUserInfoPerBank4PoList4C.optDouble("maxRapidPayAmountPerDay");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("maxRapidPayAmountPerMonth")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.maxRapidPayAmountPerMonth = jsonObjectUserInfoPerBank4PoList4C.optDouble("maxRapidPayAmountPerMonth");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("maxRapidPayTxnCountPerDay")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfoPerBank4PoList4C.maxRapidPayTxnCountPerDay = jsonObjectUserInfoPerBank4PoList4C.optInt("maxRapidPayTxnCountPerDay");
	    	if (jsonObjectUserInfoPerBank4PoList4C.isNull("userFundListPerBank4PoList4C")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "userFundListPerBank4PoList4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userFundListPerBank4PoList4CArray = jsonObjectUserInfoPerBank4PoList4C.optJSONArray("userFundListPerBank4PoList4C");
			userInfoPerBank4PoList4C.userFundListPerBank4PoList4C = new ArrayList<>();
			
			if (null != userFundListPerBank4PoList4CArray && userFundListPerBank4PoList4CArray.length() > 0) {
				for(int userFundListPerBank4PoList4Ci = 0; userFundListPerBank4PoList4Ci < userFundListPerBank4PoList4CArray.length(); userFundListPerBank4PoList4Ci++) {
					JSONObject jsonObjectUserFundListPerBank4PoList4C = userFundListPerBank4PoList4CArray.optJSONObject(userFundListPerBank4PoList4Ci);
			UserFundListPerBank4PoList4C userFundListPerBank4PoList4C = new UserFundListPerBank4PoList4C();
		
	    	if (jsonObjectUserFundListPerBank4PoList4C.isNull("fundCode")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4PoList4C.fundCode = jsonObjectUserFundListPerBank4PoList4C.optString("fundCode");
	    	if (jsonObjectUserFundListPerBank4PoList4C.isNull("fundName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4PoList4C.fundName = jsonObjectUserFundListPerBank4PoList4C.optString("fundName");
	    	if (jsonObjectUserFundListPerBank4PoList4C.isNull("poPercentage")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4PoList4C.poPercentage = jsonObjectUserFundListPerBank4PoList4C.optDouble("poPercentage");
	    	if (jsonObjectUserFundListPerBank4PoList4C.isNull("aviAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "aviAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4PoList4C.aviAmount = jsonObjectUserFundListPerBank4PoList4C.optDouble("aviAmount");
	    	if (jsonObjectUserFundListPerBank4PoList4C.isNull("totalAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4PoList4C.totalAmount = jsonObjectUserFundListPerBank4PoList4C.optDouble("totalAmount");
					
					userInfoPerBank4PoList4C.userFundListPerBank4PoList4C.add(userFundListPerBank4PoList4C);
				}
			}
			
					
					userPostList4C.userInfoPerBank4PoList4C.add(userInfoPerBank4PoList4C);
				}
			}
			
					
					this.userPostList4C.add(userPostList4C);
				}
			}
			
    	
    	return this;
    }
}
