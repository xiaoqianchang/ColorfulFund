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

	public CommonFundPoInfo commonFundPoInfo;

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
		 * 基金名称
		 */
		public String totalAmount = "";

		/**
		 * 用户购买组合中基金资产占比
		 */
		public String poPercentage = "";

		@Override
		public String toString() {
			return "PoFund{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage='" + poPercentage + '\'' +
					'}';
		}
	}
	/**
	 * userPaymentInfoListPerBank
	 */
	public class UserPaymentInfoListPerBank implements Serializable {

		public PaymentBankInfo paymentBankInfo;

		public BankInfo bankInfo;

		@Override
		public String toString() {
			return "UserPaymentInfoListPerBank{" +
					"paymentBankInfo=" + paymentBankInfo +
					", bankInfo=" + bankInfo +
					'}';
		}
	}
	/**
	 * commonFundPoInfo
	 */
	public class CommonFundPoInfo implements Serializable {

		public FundPoInfo fundPoInfo;

		public UserPaymentInfo userPaymentInfo;

		@Override
		public String toString() {
			return "CommonFundPoInfo{" +
					"fundPoInfo=" + fundPoInfo +
					", userPaymentInfo=" + userPaymentInfo +
					'}';
		}
	}
	/**
	 * fundPoInfo
	 */
	public class FundPoInfo implements Serializable {

		public PoBase poBase;

		/**
		 *
		 */
		public List<PoFundList> poFundList;

		@Override
		public String toString() {
			return "FundPoInfo{" +
					"poBase=" + poBase +
					", poFundList=" + poFundList +
					'}';
		}
	}
	/**
	 * userPaymentInfo
	 */
	public class UserPaymentInfo implements Serializable {

		public PoBase1 poBase;

		/**
		 *
		 */
		public List<UserPaymentInfoListPerBank> userPaymentInfoListPerBank;

		@Override
		public String toString() {
			return "UserPaymentInfo{" +
					"poBase=" + poBase +
					", userPaymentInfoListPerBank=" + userPaymentInfoListPerBank +
					'}';
		}
	}
	/**
	 * poBase
	 */
	public class PoBase implements Serializable {

		/**
		 * 组合名称
		 */
		public String poName = "";

		/**
		 * 组合代码
		 */
		public String poCode = "";

		@Override
		public String toString() {
			return "PoBase{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					'}';
		}
	}
	/**
	 * poBase
	 */
	public class PoBase1 implements Serializable {

		/**
		 * 组合名称
		 */
		public String poName = "";

		/**
		 * 组合代码
		 */
		public String poCode = "";

		@Override
		public String toString() {
			return "PoBase{" +
					"poName='" + poName + '\'' +
					", poCode='" + poCode + '\'' +
					'}';
		}
	}
	/**
	 * paymentBankInfo
	 */
	public class PaymentBankInfo implements Serializable {

		/**
		 * 用户交易账号id，每个银行卡有一个交易账号id
		 */
		public String userPaymentId = "";

		/**
		 * 银行卡号
		 */
		public String bankCardNo = "";

		@Override
		public String toString() {
			return "PaymentBankInfo{" +
					"userPaymentId='" + userPaymentId + '\'' +
					", bankCardNo='" + bankCardNo + '\'' +
					'}';
		}
	}
	/**
	 * bankInfo
	 */
	public class BankInfo implements Serializable {

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
			return "BankInfo{" +
					"bankName='" + bankName + '\'' +
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
		return "GetFundPoInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", commonFundPoInfo=" + commonFundPoInfo +
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
		if (jsonObject.isNull("commonFundPoInfo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "commonFundPoInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectCommonFundPoInfo = jsonObject.optJSONObject("commonFundPoInfo");
		CommonFundPoInfo commonFundPoInfo = new CommonFundPoInfo();

		if (jsonObjectCommonFundPoInfo.isNull("fundPoInfo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "fundPoInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectFundPoInfo = jsonObjectCommonFundPoInfo.optJSONObject("fundPoInfo");
		FundPoInfo fundPoInfo = new FundPoInfo();

		if (jsonObjectFundPoInfo.isNull("poBase")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectPoBase = jsonObjectFundPoInfo.optJSONObject("poBase");
		PoBase poBase = new PoBase();

		if (jsonObjectPoBase.isNull("poName")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poName = jsonObjectPoBase.optString("poName");
		if (jsonObjectPoBase.isNull("poCode")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poCode = jsonObjectPoBase.optString("poCode");

		fundPoInfo.poBase = poBase;

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


		commonFundPoInfo.fundPoInfo = fundPoInfo;
		if (jsonObjectCommonFundPoInfo.isNull("userPaymentInfo")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "userPaymentInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectUserPaymentInfo = jsonObjectCommonFundPoInfo.optJSONObject("userPaymentInfo");
		UserPaymentInfo userPaymentInfo = new UserPaymentInfo();

		if (jsonObjectUserPaymentInfo.isNull("poBase")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectPoBase1 = jsonObjectUserPaymentInfo.optJSONObject("poBase");
		PoBase1 poBase1 = new PoBase1();

		if (jsonObjectPoBase1.isNull("poName")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase1.poName = jsonObjectPoBase1.optString("poName");
		if (jsonObjectPoBase1.isNull("poCode")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase1.poCode = jsonObjectPoBase1.optString("poCode");

		userPaymentInfo.poBase = poBase1;

		if (jsonObjectUserPaymentInfo.isNull("userPaymentInfoListPerBank")) {
			Log.d("GetFundPoInfo4C", "has no mapping for key " + "userPaymentInfoListPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray userPaymentInfoListPerBankArray = jsonObjectUserPaymentInfo.optJSONArray("userPaymentInfoListPerBank");
		userPaymentInfo.userPaymentInfoListPerBank = new ArrayList<>();

		if (null != userPaymentInfoListPerBankArray && userPaymentInfoListPerBankArray.length() > 0) {
			for(int userPaymentInfoListPerBanki = 0; userPaymentInfoListPerBanki < userPaymentInfoListPerBankArray.length(); userPaymentInfoListPerBanki++) {
				JSONObject jsonObjectUserPaymentInfoListPerBank = userPaymentInfoListPerBankArray.optJSONObject(userPaymentInfoListPerBanki);
				UserPaymentInfoListPerBank userPaymentInfoListPerBank = new UserPaymentInfoListPerBank();

				if (jsonObjectUserPaymentInfoListPerBank.isNull("paymentBankInfo")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "paymentBankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectPaymentBankInfo = jsonObjectUserPaymentInfoListPerBank.optJSONObject("paymentBankInfo");
				PaymentBankInfo paymentBankInfo = new PaymentBankInfo();

				if (jsonObjectPaymentBankInfo.isNull("userPaymentId")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				paymentBankInfo.userPaymentId = jsonObjectPaymentBankInfo.optString("userPaymentId");
				if (jsonObjectPaymentBankInfo.isNull("bankCardNo")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				paymentBankInfo.bankCardNo = jsonObjectPaymentBankInfo.optString("bankCardNo");

				userPaymentInfoListPerBank.paymentBankInfo = paymentBankInfo;
				if (jsonObjectUserPaymentInfoListPerBank.isNull("bankInfo")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectBankInfo = jsonObjectUserPaymentInfoListPerBank.optJSONObject("bankInfo");
				BankInfo bankInfo = new BankInfo();

				if (jsonObjectBankInfo.isNull("bankName")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.bankName = jsonObjectBankInfo.optString("bankName");
				if (jsonObjectBankInfo.isNull("paymentType")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.paymentType = jsonObjectBankInfo.optString("paymentType");
				if (jsonObjectBankInfo.isNull("bankLogo")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.bankLogo = jsonObjectBankInfo.optString("bankLogo");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerTxn")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerTxn = jsonObjectBankInfo.optString("maxRapidPayAmountPerTxn");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerDay")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerDay = jsonObjectBankInfo.optString("maxRapidPayAmountPerDay");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerMonth")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerMonth = jsonObjectBankInfo.optString("maxRapidPayAmountPerMonth");
				if (jsonObjectBankInfo.isNull("maxRapidPayTxnCountPerDay")) {
					Log.d("GetFundPoInfo4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayTxnCountPerDay = jsonObjectBankInfo.optString("maxRapidPayTxnCountPerDay");

				userPaymentInfoListPerBank.bankInfo = bankInfo;

				userPaymentInfo.userPaymentInfoListPerBank.add(userPaymentInfoListPerBank);
			}
		}


		commonFundPoInfo.userPaymentInfo = userPaymentInfo;

		this.commonFundPoInfo = commonFundPoInfo;

		return this;
	}
}
