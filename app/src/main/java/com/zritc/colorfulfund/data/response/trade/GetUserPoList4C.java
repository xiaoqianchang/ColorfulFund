package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户已绑定的某张银行卡所购买的基金组合详细列表包括，包括里面的详细基金列表及所占资产
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetUserPoList4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户已绑定的某张银行卡所购买的基金组合详细列表包括，包括里面的详细基金列表及所占资产
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/getUserPoList4C
 * comments(服务详细备注，可用于注释): 		仅供客户端调用的接口
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
	public List<UserPoList> userPoList;
    
	/**
	 * userPoList
     */
	public class UserPoList implements Serializable {

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
		public List<UserPoInfoPerBank> userPoInfoPerBank;
		
		@Override
		public String toString() {
			return "UserPoList{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					", userPoInfoPerBank=" + userPoInfoPerBank +
					'}';
		}
    }
	/**
	 * userPoInfoPerBank
     */
	public class UserPoInfoPerBank implements Serializable {

	/**
	 * 用户交易账号id
	 */
	public String userPaymentId = "";

	/**
		 * 用户银行卡号
		 */
		public String bankCardNo = "";

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
		public List<UserFundListPerBank> userFundListPerBank;
		
		@Override
		public String toString() {
			return "UserPoInfoPerBank{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", bankCardNo='" + bankCardNo + '\'' +
					", minRedeemAmount=" + minRedeemAmount +
					", maxRedeemAmount=" + maxRedeemAmount +
					", bankName='" + bankName + '\'' +
					", bankLogo='" + bankLogo + '\'' +
					", paymentType='" + paymentType + '\'' +
					", maxRapidPayAmountPerTxn=" + maxRapidPayAmountPerTxn +
					", maxRapidPayAmountPerDay=" + maxRapidPayAmountPerDay +
					", maxRapidPayAmountPerMonth=" + maxRapidPayAmountPerMonth +
					", maxRapidPayTxnCountPerDay=" + maxRapidPayTxnCountPerDay +
					", userFundListPerBank=" + userFundListPerBank +
					'}';
		}
    }
	/**
	 * userFundListPerBank
     */
	public class UserFundListPerBank implements Serializable {

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
			return "UserFundListPerBank{" +
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
				", userPoList=" + userPoList +
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
		if (jsonObject.isNull("userPoList")) {
			Log.d("GetUserPoList4C", "has no mapping for key " + "userPoList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		JSONArray userPoListArray = jsonObject.optJSONArray("userPoList");
		this.userPoList = new ArrayList<>();
			
		if (null != userPoListArray && userPoListArray.length() > 0) {
			for(int userPoListi = 0; userPoListi < userPoListArray.length(); userPoListi++) {
				JSONObject jsonObjectUserPoList = userPoListArray.optJSONObject(userPoListi);
				UserPoList userPoList = new UserPoList();
		
				if (jsonObjectUserPoList.isNull("poName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userPoList.poName = jsonObjectUserPoList.optString("poName");
				if (jsonObjectUserPoList.isNull("poCode")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				userPoList.poCode = jsonObjectUserPoList.optString("poCode");
				if (jsonObjectUserPoList.isNull("userPoInfoPerBank")) {
					Log.d("GetUserPoList4C", "has no mapping for key " + "userPoInfoPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				JSONArray userPoInfoPerBankArray = jsonObjectUserPoList.optJSONArray("userPoInfoPerBank");
				userPoList.userPoInfoPerBank = new ArrayList<>();
			
				if (null != userPoInfoPerBankArray && userPoInfoPerBankArray.length() > 0) {
					for(int userPoInfoPerBanki = 0; userPoInfoPerBanki < userPoInfoPerBankArray.length(); userPoInfoPerBanki++) {
						JSONObject jsonObjectUserPoInfoPerBank = userPoInfoPerBankArray.optJSONObject(userPoInfoPerBanki);
						UserPoInfoPerBank userPoInfoPerBank = new UserPoInfoPerBank();
		
						if (jsonObjectUserPoInfoPerBank.isNull("userPaymentId")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.userPaymentId = jsonObjectUserPoInfoPerBank.optString("userPaymentId");
						if (jsonObjectUserPoInfoPerBank.isNull("bankCardNo")) {
							Log.d("GetUserPoList4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						userPoInfoPerBank.bankCardNo = jsonObjectUserPoInfoPerBank.optString("bankCardNo");
						if (jsonObjectUserPoInfoPerBank.isNull("minRedeemAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "minRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.minRedeemAmount = jsonObjectUserPoInfoPerBank.optDouble("minRedeemAmount");
						if (jsonObjectUserPoInfoPerBank.isNull("maxRedeemAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.maxRedeemAmount = jsonObjectUserPoInfoPerBank.optDouble("maxRedeemAmount");
						if (jsonObjectUserPoInfoPerBank.isNull("bankName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.bankName = jsonObjectUserPoInfoPerBank.optString("bankName");
						if (jsonObjectUserPoInfoPerBank.isNull("bankLogo")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.bankLogo = jsonObjectUserPoInfoPerBank.optString("bankLogo");
						if (jsonObjectUserPoInfoPerBank.isNull("paymentType")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.paymentType = jsonObjectUserPoInfoPerBank.optString("paymentType");
						if (jsonObjectUserPoInfoPerBank.isNull("maxRapidPayAmountPerTxn")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.maxRapidPayAmountPerTxn = jsonObjectUserPoInfoPerBank.optDouble("maxRapidPayAmountPerTxn");
						if (jsonObjectUserPoInfoPerBank.isNull("maxRapidPayAmountPerDay")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.maxRapidPayAmountPerDay = jsonObjectUserPoInfoPerBank.optDouble("maxRapidPayAmountPerDay");
						if (jsonObjectUserPoInfoPerBank.isNull("maxRapidPayAmountPerMonth")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.maxRapidPayAmountPerMonth = jsonObjectUserPoInfoPerBank.optDouble("maxRapidPayAmountPerMonth");
						if (jsonObjectUserPoInfoPerBank.isNull("maxRapidPayTxnCountPerDay")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						userPoInfoPerBank.maxRapidPayTxnCountPerDay = jsonObjectUserPoInfoPerBank.optInt("maxRapidPayTxnCountPerDay");
						if (jsonObjectUserPoInfoPerBank.isNull("userFundListPerBank")) {
							Log.d("GetUserPoList4C", "has no mapping for key " + "userFundListPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						JSONArray userFundListPerBankArray = jsonObjectUserPoInfoPerBank.optJSONArray("userFundListPerBank");
						userPoInfoPerBank.userFundListPerBank = new ArrayList<>();
			
						if (null != userFundListPerBankArray && userFundListPerBankArray.length() > 0) {
							for(int userFundListPerBanki = 0; userFundListPerBanki < userFundListPerBankArray.length(); userFundListPerBanki++) {
								JSONObject jsonObjectUserFundListPerBank = userFundListPerBankArray.optJSONObject(userFundListPerBanki);
								UserFundListPerBank userFundListPerBank = new UserFundListPerBank();
		
								if (jsonObjectUserFundListPerBank.isNull("fundCode")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
								userFundListPerBank.fundCode = jsonObjectUserFundListPerBank.optString("fundCode");
								if (jsonObjectUserFundListPerBank.isNull("fundName")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
								userFundListPerBank.fundName = jsonObjectUserFundListPerBank.optString("fundName");
								if (jsonObjectUserFundListPerBank.isNull("poPercentage")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
								userFundListPerBank.poPercentage = jsonObjectUserFundListPerBank.optDouble("poPercentage");
								if (jsonObjectUserFundListPerBank.isNull("aviAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "aviAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
								userFundListPerBank.aviAmount = jsonObjectUserFundListPerBank.optDouble("aviAmount");
								if (jsonObjectUserFundListPerBank.isNull("totalAmount")) {
	    		Log.d("GetUserPoList4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
								userFundListPerBank.totalAmount = jsonObjectUserFundListPerBank.optDouble("totalAmount");
					
								userPoInfoPerBank.userFundListPerBank.add(userFundListPerBank);
				}
			}
			
					
						userPoList.userPoInfoPerBank.add(userPoInfoPerBank);
				}
			}
			
					
				this.userPoList.add(userPoList);
				}
			}
			
    	
    	return this;
    }
}
