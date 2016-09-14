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

	public CommonFundPoList commonFundPoList;

	/**
	 * fundPoList
	 */
	public class FundPoList implements Serializable {

		public PoBase poBase;

		/**
		 *
		 */
		public List<PoFundList> poFundList;

		@Override
		public String toString() {
			return "FundPoList{" +
					"poBase=" + poBase +
					", poFundList=" + poFundList +
					'}';
		}
	}
	/**
	 * userPaymentPoList
	 */
	public class UserPaymentPoList implements Serializable {

		public PoBase1 poBase;

		/**
		 *
		 */
		public List<UserPaymentInfoListPerBank> userPaymentInfoListPerBank;

		@Override
		public String toString() {
			return "UserPaymentPoList{" +
					"poBase=" + poBase +
					", userPaymentInfoListPerBank=" + userPaymentInfoListPerBank +
					'}';
		}
	}
	/**
	 * commonFundPoList
	 */
	public class CommonFundPoList implements Serializable {

		/**
		 *
		 */
		public List<FundPoList> fundPoList;

		/**
		 *
		 */
		public List<UserPaymentPoList> userPaymentPoList;

		@Override
		public String toString() {
			return "CommonFundPoList{" +
					"fundPoList=" + fundPoList +
					", userPaymentPoList=" + userPaymentPoList +
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
			return "PoFund{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage='" + poPercentage + '\'' +
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

	@Override
	public String toString() {
		return "GetFundPoList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", commonFundPoList=" + commonFundPoList +
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
		if (jsonObject.isNull("commonFundPoList")) {
			Log.d("GetFundPoList4C", "has no mapping for key " + "commonFundPoList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectCommonFundPoList = jsonObject.optJSONObject("commonFundPoList");
		CommonFundPoList commonFundPoList = new CommonFundPoList();

		if (jsonObjectCommonFundPoList.isNull("fundPoList")) {
			Log.d("GetFundPoList4C", "has no mapping for key " + "fundPoList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray fundPoListArray = jsonObjectCommonFundPoList.optJSONArray("fundPoList");
		commonFundPoList.fundPoList = new ArrayList<>();

		if (null != fundPoListArray && fundPoListArray.length() > 0) {
			for(int fundPoListi = 0; fundPoListi < fundPoListArray.length(); fundPoListi++) {
				JSONObject jsonObjectFundPoList = fundPoListArray.optJSONObject(fundPoListi);
				FundPoList fundPoList = new FundPoList();

				if (jsonObjectFundPoList.isNull("poBase")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectPoBase = jsonObjectFundPoList.optJSONObject("poBase");
				PoBase poBase = new PoBase();

				if (jsonObjectPoBase.isNull("poName")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poBase.poName = jsonObjectPoBase.optString("poName");
				if (jsonObjectPoBase.isNull("poCode")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poBase.poCode = jsonObjectPoBase.optString("poCode");

				fundPoList.poBase = poBase;
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


				commonFundPoList.fundPoList.add(fundPoList);
			}
		}

		if (jsonObjectCommonFundPoList.isNull("userPaymentPoList")) {
			Log.d("GetFundPoList4C", "has no mapping for key " + "userPaymentPoList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray userPaymentPoListArray = jsonObjectCommonFundPoList.optJSONArray("userPaymentPoList");
		commonFundPoList.userPaymentPoList = new ArrayList<>();

		if (null != userPaymentPoListArray && userPaymentPoListArray.length() > 0) {
			for(int userPaymentPoListi = 0; userPaymentPoListi < userPaymentPoListArray.length(); userPaymentPoListi++) {
				JSONObject jsonObjectUserPaymentPoList = userPaymentPoListArray.optJSONObject(userPaymentPoListi);
				UserPaymentPoList userPaymentPoList = new UserPaymentPoList();

				if (jsonObjectUserPaymentPoList.isNull("poBase")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectPoBase = jsonObjectUserPaymentPoList.optJSONObject("poBase");
				PoBase1 poBase = new PoBase1();

				if (jsonObjectPoBase.isNull("poName")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poBase.poName = jsonObjectPoBase.optString("poName");
				if (jsonObjectPoBase.isNull("poCode")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poBase.poCode = jsonObjectPoBase.optString("poCode");

				userPaymentPoList.poBase = poBase;
				if (jsonObjectUserPaymentPoList.isNull("userPaymentInfoListPerBank")) {
					Log.d("GetFundPoList4C", "has no mapping for key " + "userPaymentInfoListPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				JSONArray userPaymentInfoListPerBankArray = jsonObjectUserPaymentPoList.optJSONArray("userPaymentInfoListPerBank");
				userPaymentPoList.userPaymentInfoListPerBank = new ArrayList<>();

				if (null != userPaymentInfoListPerBankArray && userPaymentInfoListPerBankArray.length() > 0) {
					for(int userPaymentInfoListPerBanki = 0; userPaymentInfoListPerBanki < userPaymentInfoListPerBankArray.length(); userPaymentInfoListPerBanki++) {
						JSONObject jsonObjectUserPaymentInfoListPerBank = userPaymentInfoListPerBankArray.optJSONObject(userPaymentInfoListPerBanki);
						UserPaymentInfoListPerBank userPaymentInfoListPerBank = new UserPaymentInfoListPerBank();

						if (jsonObjectUserPaymentInfoListPerBank.isNull("paymentBankInfo")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "paymentBankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}

						JSONObject jsonObjectPaymentBankInfo = jsonObjectUserPaymentInfoListPerBank.optJSONObject("paymentBankInfo");
						PaymentBankInfo paymentBankInfo = new PaymentBankInfo();

						if (jsonObjectPaymentBankInfo.isNull("userPaymentId")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						paymentBankInfo.userPaymentId = jsonObjectPaymentBankInfo.optString("userPaymentId");
						if (jsonObjectPaymentBankInfo.isNull("bankCardNo")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						paymentBankInfo.bankCardNo = jsonObjectPaymentBankInfo.optString("bankCardNo");

						userPaymentInfoListPerBank.paymentBankInfo = paymentBankInfo;
						if (jsonObjectUserPaymentInfoListPerBank.isNull("bankInfo")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "bankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}

						JSONObject jsonObjectBankInfo = jsonObjectUserPaymentInfoListPerBank.optJSONObject("bankInfo");
						BankInfo bankInfo = new BankInfo();

						if (jsonObjectBankInfo.isNull("bankName")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.bankName = jsonObjectBankInfo.optString("bankName");
						if (jsonObjectBankInfo.isNull("paymentType")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.paymentType = jsonObjectBankInfo.optString("paymentType");
						if (jsonObjectBankInfo.isNull("bankLogo")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.bankLogo = jsonObjectBankInfo.optString("bankLogo");
						if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerTxn")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.maxRapidPayAmountPerTxn = jsonObjectBankInfo.optString("maxRapidPayAmountPerTxn");
						if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerDay")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.maxRapidPayAmountPerDay = jsonObjectBankInfo.optString("maxRapidPayAmountPerDay");
						if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerMonth")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.maxRapidPayAmountPerMonth = jsonObjectBankInfo.optString("maxRapidPayAmountPerMonth");
						if (jsonObjectBankInfo.isNull("maxRapidPayTxnCountPerDay")) {
							Log.d("GetFundPoList4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						bankInfo.maxRapidPayTxnCountPerDay = jsonObjectBankInfo.optString("maxRapidPayTxnCountPerDay");

						userPaymentInfoListPerBank.bankInfo = bankInfo;

						userPaymentPoList.userPaymentInfoListPerBank.add(userPaymentInfoListPerBank);
					}
				}


				commonFundPoList.userPaymentPoList.add(userPaymentPoList);
			}
		}


		this.commonFundPoList = commonFundPoList;

		return this;
	}
}
