package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户已绑定的某张银行卡所购买的基金组合详细信息包括，包括里面的详细基金列表及所占资产
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetUserPoInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户已绑定的某张银行卡所购买的基金组合详细信息包括，包括里面的详细基金列表及所占资产
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/getUserPoInfo4C
 * comments(服务详细备注，可用于注释): 		仅供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserPoInfo4C implements Serializable {

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
	public List<UserPoInfoPerBank4C> userPoInfoPerBank4C;
    
	/**
     * userPoInfoPerBank4C
     */
    public class UserPoInfoPerBank4C implements Serializable {

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
	public List<UserFundListPerBank4C> userFundListPerBank4C;
		
		@Override
		public String toString() {
			return "UserPoInfoPerBank4C{" +
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
					", userFundListPerBank4C=" + userFundListPerBank4C +
					'}';
		}
    }
	/**
     * userFundListPerBank4C
     */
    public class UserFundListPerBank4C implements Serializable {

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
			return "UserFundListPerBank4C{" +
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
		return "GetUserPoInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", poName='" + poName + '\'' +
				", poCode='" + poCode + '\'' +
				", userPoInfoPerBank4C=" + userPoInfoPerBank4C +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserPoInfo4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("poName")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.poName = jsonObject.optString("poName");
	    	if (jsonObject.isNull("poCode")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.poCode = jsonObject.optString("poCode");
	    	if (jsonObject.isNull("userPoInfoPerBank4C")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "userPoInfoPerBank4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userPoInfoPerBank4CArray = jsonObject.optJSONArray("userPoInfoPerBank4C");
			this.userPoInfoPerBank4C = new ArrayList<>();
			
			if (null != userPoInfoPerBank4CArray && userPoInfoPerBank4CArray.length() > 0) {
				for(int userPoInfoPerBank4Ci = 0; userPoInfoPerBank4Ci < userPoInfoPerBank4CArray.length(); userPoInfoPerBank4Ci++) {
					JSONObject jsonObjectUserPoInfoPerBank4C = userPoInfoPerBank4CArray.optJSONObject(userPoInfoPerBank4Ci);
			UserPoInfoPerBank4C userPoInfoPerBank4C = new UserPoInfoPerBank4C();
		
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("userPaymentId")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.userPaymentId = jsonObjectUserPoInfoPerBank4C.optString("userPaymentId");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("minRedeemAmount")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "minRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.minRedeemAmount = jsonObjectUserPoInfoPerBank4C.optDouble("minRedeemAmount");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("maxRedeemAmount")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.maxRedeemAmount = jsonObjectUserPoInfoPerBank4C.optDouble("maxRedeemAmount");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("bankName")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.bankName = jsonObjectUserPoInfoPerBank4C.optString("bankName");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("bankLogo")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.bankLogo = jsonObjectUserPoInfoPerBank4C.optString("bankLogo");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("paymentType")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.paymentType = jsonObjectUserPoInfoPerBank4C.optString("paymentType");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("maxRapidPayAmountPerTxn")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.maxRapidPayAmountPerTxn = jsonObjectUserPoInfoPerBank4C.optDouble("maxRapidPayAmountPerTxn");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("maxRapidPayAmountPerDay")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.maxRapidPayAmountPerDay = jsonObjectUserPoInfoPerBank4C.optDouble("maxRapidPayAmountPerDay");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("maxRapidPayAmountPerMonth")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.maxRapidPayAmountPerMonth = jsonObjectUserPoInfoPerBank4C.optDouble("maxRapidPayAmountPerMonth");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("maxRapidPayTxnCountPerDay")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoInfoPerBank4C.maxRapidPayTxnCountPerDay = jsonObjectUserPoInfoPerBank4C.optInt("maxRapidPayTxnCountPerDay");
	    	if (jsonObjectUserPoInfoPerBank4C.isNull("userFundListPerBank4C")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "userFundListPerBank4C" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userFundListPerBank4CArray = jsonObjectUserPoInfoPerBank4C.optJSONArray("userFundListPerBank4C");
			userPoInfoPerBank4C.userFundListPerBank4C = new ArrayList<>();
			
			if (null != userFundListPerBank4CArray && userFundListPerBank4CArray.length() > 0) {
				for(int userFundListPerBank4Ci = 0; userFundListPerBank4Ci < userFundListPerBank4CArray.length(); userFundListPerBank4Ci++) {
					JSONObject jsonObjectUserFundListPerBank4C = userFundListPerBank4CArray.optJSONObject(userFundListPerBank4Ci);
			UserFundListPerBank4C userFundListPerBank4C = new UserFundListPerBank4C();
		
	    	if (jsonObjectUserFundListPerBank4C.isNull("fundCode")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4C.fundCode = jsonObjectUserFundListPerBank4C.optString("fundCode");
	    	if (jsonObjectUserFundListPerBank4C.isNull("fundName")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4C.fundName = jsonObjectUserFundListPerBank4C.optString("fundName");
	    	if (jsonObjectUserFundListPerBank4C.isNull("poPercentage")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4C.poPercentage = jsonObjectUserFundListPerBank4C.optDouble("poPercentage");
	    	if (jsonObjectUserFundListPerBank4C.isNull("aviAmount")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "aviAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4C.aviAmount = jsonObjectUserFundListPerBank4C.optDouble("aviAmount");
	    	if (jsonObjectUserFundListPerBank4C.isNull("totalAmount")) {
	    		Log.d("GetUserPoInfo4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userFundListPerBank4C.totalAmount = jsonObjectUserFundListPerBank4C.optDouble("totalAmount");
					
					userPoInfoPerBank4C.userFundListPerBank4C.add(userFundListPerBank4C);
				}
			}
			
					
					this.userPoInfoPerBank4C.add(userPoInfoPerBank4C);
				}
			}
			
    	
    	return this;
    }
}
