package com.zritc.colorfulfund.data.response.trade;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取通用基金组合的详细信息，包括里面的各个基金的占比
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetFundPoInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取通用基金组合的详细信息，包括里面的各个基金的占比
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/getFundPoInfo4C
 * comments(服务详细备注，可用于注释): 		仅供客户端调用的接口。如果userPaymentInfo的结果为空，说明用户是第一次购买此组合；否则说明用户已经买过了。
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

	public FundPoInfo fundPoInfo;

	public UserPaymentInfo userPaymentInfo;

	/**
	 * poFundList
	 */
	public class PoFundList implements Serializable {

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
		public String poPercentage = "";

		public String totalAmount = "";

		@Override
		public String toString() {
			return "PoFundList{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage='" + poPercentage + '\'' +
					'}';
		}
	}
	/**
	 * fundPoInfo
	 */
	public class FundPoInfo implements Serializable {

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
		public List<PoFundList> poFundList;
    
		@Override
		public String toString() {
			return "FundPoInfo{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					", poFundList=" + poFundList +
					'}';
		}
	}
	/**
	 * userPaymentInfo
     */
	public class UserPaymentInfo implements Serializable {

	/**
		 * 用户交易账号id
		 */
		public String userPaymentId = "";

		/**
		 * 用户银行卡号
		 */
		public String bankCardNo = "";

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
		public String maxRapidPayAmountPerTxn = "";

		/**
		 * 每天最大限额
	 */
		public String maxRapidPayAmountPerDay = "";

	/**
		 * 每天最大限额
	 */
		public String maxRapidPayAmountPerMonth = "";

	/**
		 * 每天最大交易次数
	 */
		public String maxRapidPayTxnCountPerDay = "";
		
		@Override
		public String toString() {
			return "UserPaymentInfo{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", bankCardNo='" + bankCardNo + '\'' +
					", bankName='" + bankName + '\'' +
					", bankLogo='" + bankLogo + '\'' +
					", paymentType='" + paymentType + '\'' +
					", maxRapidPayAmountPerTxn='" + maxRapidPayAmountPerTxn + '\'' +
					", maxRapidPayAmountPerDay='" + maxRapidPayAmountPerDay + '\'' +
					", maxRapidPayAmountPerMonth='" + maxRapidPayAmountPerMonth + '\'' +
					", maxRapidPayTxnCountPerDay='" + maxRapidPayTxnCountPerDay + '\'' +
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
				", fundPoInfo=" + fundPoInfo +
				", userPaymentInfo=" + userPaymentInfo +
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
		if (jsonObject.isNull("fundPoInfo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundPoInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectFundPoInfo = jsonObject.optJSONObject("fundPoInfo");
		FundPoInfo fundPoInfo = new FundPoInfo();

		if (jsonObjectFundPoInfo.isNull("poName")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		fundPoInfo.poName = jsonObjectFundPoInfo.optString("poName");
		if (jsonObjectFundPoInfo.isNull("poCode")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		fundPoInfo.poCode = jsonObjectFundPoInfo.optString("poCode");
		if (jsonObjectFundPoInfo.isNull("poFundList")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poFundList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		JSONArray poFundListArray = jsonObjectFundPoInfo.optJSONArray("poFundList");
		fundPoInfo.poFundList = new ArrayList<>();
			
		if (null != poFundListArray && poFundListArray.length() > 0) {
			for(int poFundListi = 0; poFundListi < poFundListArray.length(); poFundListi++) {
				JSONObject jsonObjectPoFundList = poFundListArray.optJSONObject(poFundListi);
				PoFundList poFundList = new PoFundList();
		
				if (jsonObjectPoFundList.isNull("fundCode")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				poFundList.fundCode = jsonObjectPoFundList.optString("fundCode");
				if (jsonObjectPoFundList.isNull("fundName")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				poFundList.fundName = jsonObjectPoFundList.optString("fundName");
				if (jsonObjectPoFundList.isNull("poPercentage")) {
	    		Log.d("GetFundPoInfo4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				poFundList.poPercentage = jsonObjectPoFundList.optString("poPercentage");
					
				fundPoInfo.poFundList.add(poFundList);
				}
			}
			
    	
		this.fundPoInfo = fundPoInfo;
		if (jsonObject.isNull("userPaymentInfo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "userPaymentInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectUserPaymentInfo = jsonObject.optJSONObject("userPaymentInfo");
		UserPaymentInfo userPaymentInfo = new UserPaymentInfo();

		if (jsonObjectUserPaymentInfo.isNull("userPaymentId")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.userPaymentId = jsonObjectUserPaymentInfo.optString("userPaymentId");
		if (jsonObjectUserPaymentInfo.isNull("bankCardNo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.bankCardNo = jsonObjectUserPaymentInfo.optString("bankCardNo");
		if (jsonObjectUserPaymentInfo.isNull("bankName")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.bankName = jsonObjectUserPaymentInfo.optString("bankName");
		if (jsonObjectUserPaymentInfo.isNull("bankLogo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.bankLogo = jsonObjectUserPaymentInfo.optString("bankLogo");
		if (jsonObjectUserPaymentInfo.isNull("paymentType")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.paymentType = jsonObjectUserPaymentInfo.optString("paymentType");
		if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerTxn")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.maxRapidPayAmountPerTxn = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerTxn");
		if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerDay")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.maxRapidPayAmountPerDay = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerDay");
		if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerMonth")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.maxRapidPayAmountPerMonth = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerMonth");
		if (jsonObjectUserPaymentInfo.isNull("maxRapidPayTxnCountPerDay")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		userPaymentInfo.maxRapidPayTxnCountPerDay = jsonObjectUserPaymentInfo.optString("maxRapidPayTxnCountPerDay");

		this.userPaymentInfo = userPaymentInfo;

    	return this;
    }
}
