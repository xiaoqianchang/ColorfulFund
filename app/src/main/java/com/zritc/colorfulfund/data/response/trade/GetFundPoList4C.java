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
	public List<FundPoList> fundPoList;
    
	/**
	 *
	 */
	public List<UserPaymentInfo> userPaymentInfo;

	/**
	 * fundPoList
     */
	public class FundPoList implements Serializable {

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
			return "FundPoList{" +
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
		
		@Override
		public String toString() {
			return "PoFundList{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage='" + poPercentage + '\'' +
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
				", fundPoList=" + fundPoList +
				", userPaymentInfo=" + userPaymentInfo +
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
		if (jsonObject.isNull("fundPoList")) {
			Log.d("GetFundPoList4C", "has no mapping for key " + "fundPoList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
		JSONArray fundPoListArray = jsonObject.optJSONArray("fundPoList");
		this.fundPoList = new ArrayList<>();
			
		if (null != fundPoListArray && fundPoListArray.length() > 0) {
			for(int fundPoListi = 0; fundPoListi < fundPoListArray.length(); fundPoListi++) {
				JSONObject jsonObjectFundPoList = fundPoListArray.optJSONObject(fundPoListi);
				FundPoList fundPoList = new FundPoList();
		
				if (jsonObjectFundPoList.isNull("poName")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				fundPoList.poName = jsonObjectFundPoList.optString("poName");
				if (jsonObjectFundPoList.isNull("poCode")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				fundPoList.poCode = jsonObjectFundPoList.optString("poCode");
				if (jsonObjectFundPoList.isNull("poFundList")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poFundList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
				JSONArray poFundListArray = jsonObjectFundPoList.optJSONArray("poFundList");
				fundPoList.poFundList = new ArrayList<>();
			
				if (null != poFundListArray && poFundListArray.length() > 0) {
					for(int poFundListi = 0; poFundListi < poFundListArray.length(); poFundListi++) {
						JSONObject jsonObjectPoFundList = poFundListArray.optJSONObject(poFundListi);
						PoFundList poFundList = new PoFundList();
		
						if (jsonObjectPoFundList.isNull("fundCode")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						poFundList.fundCode = jsonObjectPoFundList.optString("fundCode");
						if (jsonObjectPoFundList.isNull("fundName")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						poFundList.fundName = jsonObjectPoFundList.optString("fundName");
						if (jsonObjectPoFundList.isNull("poPercentage")) {
	    		Log.d("GetFundPoList4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
						poFundList.poPercentage = jsonObjectPoFundList.optString("poPercentage");
					
						fundPoList.poFundList.add(poFundList);
				}
			}
			
					
				this.fundPoList.add(fundPoList);
			}
		}

		if (jsonObject.isNull("userPaymentInfo")) {
			Log.d("GetFundPoList4C", "has no mapping for key " + "userPaymentInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray userPaymentInfoArray = jsonObject.optJSONArray("userPaymentInfo");
		this.userPaymentInfo = new ArrayList<>();

		if (null != userPaymentInfoArray && userPaymentInfoArray.length() > 0) {
			for(int userPaymentInfoi = 0; userPaymentInfoi < userPaymentInfoArray.length(); userPaymentInfoi++) {
				JSONObject jsonObjectUserPaymentInfo = userPaymentInfoArray.optJSONObject(userPaymentInfoi);
				UserPaymentInfo userPaymentInfo = new UserPaymentInfo();

				if (jsonObjectUserPaymentInfo.isNull("userPaymentId")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.userPaymentId = jsonObjectUserPaymentInfo.optString("userPaymentId");
				if (jsonObjectUserPaymentInfo.isNull("bankCardNo")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.bankCardNo = jsonObjectUserPaymentInfo.optString("bankCardNo");
				if (jsonObjectUserPaymentInfo.isNull("bankName")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.bankName = jsonObjectUserPaymentInfo.optString("bankName");
				if (jsonObjectUserPaymentInfo.isNull("bankLogo")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.bankLogo = jsonObjectUserPaymentInfo.optString("bankLogo");
				if (jsonObjectUserPaymentInfo.isNull("paymentType")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.paymentType = jsonObjectUserPaymentInfo.optString("paymentType");
				if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerTxn")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.maxRapidPayAmountPerTxn = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerTxn");
				if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerDay")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.maxRapidPayAmountPerDay = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerDay");
				if (jsonObjectUserPaymentInfo.isNull("maxRapidPayAmountPerMonth")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.maxRapidPayAmountPerMonth = jsonObjectUserPaymentInfo.optString("maxRapidPayAmountPerMonth");
				if (jsonObjectUserPaymentInfo.isNull("maxRapidPayTxnCountPerDay")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userPaymentInfo.maxRapidPayTxnCountPerDay = jsonObjectUserPaymentInfo.optString("maxRapidPayTxnCountPerDay");

				this.userPaymentInfo.add(userPaymentInfo);
				}
			}
			
    	
    	return this;
    }
}
