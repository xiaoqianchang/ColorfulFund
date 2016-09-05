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

	public UserPoInfo userPoInfo;

	/**
	 * userPoInfoListPerBank
	 */
	public class UserPoInfoListPerBank implements Serializable {

		public PaymentBankInfo paymentBankInfo;

		public BankInfo bankInfo;

		public PoRedeemableAsset poRedeemableAsset;

		/**
		 *
		 */
		public List<UserFundListPerBank> userFundListPerBank;

		@Override
		public String toString() {
			return "UserPoInfoListPerBank{" +
					"paymentBankInfo=" + paymentBankInfo +
					", bankInfo=" + bankInfo +
					", poRedeemableAsset=" + poRedeemableAsset +
					", userFundListPerBank=" + userFundListPerBank +
					'}';
		}
	}
	/**
	 * userPoInfo
	 */
	public class UserPoInfo implements Serializable {

		public PoBase poBase;

		/**
		 *
		 */
		public List<UserPoInfoListPerBank> userPoInfoListPerBank;

		@Override
		public String toString() {
			return "UserPoInfo{" +
					"poBase=" + poBase +
					", userPoInfoListPerBank=" + userPoInfoListPerBank +
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
	 * userFundListPerBank
	 */
	public class UserFundListPerBank implements Serializable {

		public FundInfo fundInfo;

		public PoFundAssetInfo poFundAssetInfo;

		@Override
		public String toString() {
			return "UserFundListPerBank{" +
					"fundInfo=" + fundInfo +
					", poFundAssetInfo=" + poFundAssetInfo +
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
	 * poRedeemableAsset
	 */
	public class PoRedeemableAsset implements Serializable {

		/**
		 * 允许赎回最小金额
		 */
		public double minRedeemAmount;

		/**
		 * 允许赎回最大金额，如果允许赎回的最小和最大金额都为空，则只能允许全额赎回
		 */
		public double maxRedeemAmount;

		@Override
		public String toString() {
			return "PoRedeemableAsset{" +
					"minRedeemAmount=" + minRedeemAmount +
					", maxRedeemAmount=" + maxRedeemAmount +
					'}';
		}
	}
	/**
	 * fundInfo
	 */
	public class FundInfo implements Serializable {

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

		@Override
		public String toString() {
			return "FundInfo{" +
					"fundCode='" + fundCode + '\'' +
					", fundName='" + fundName + '\'' +
					", poPercentage=" + poPercentage +
					'}';
		}
	}
	/**
	 * poFundAssetInfo
	 */
	public class PoFundAssetInfo implements Serializable {

		/**
		 * 可赎回的金额
		 */
		public double aviAmount;

		/**
		 * 用户购买组合中的基金资产
		 */
		public double totalAmount;

		@Override
		public String toString() {
			return "PoFundAssetInfo{" +
					"aviAmount=" + aviAmount +
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
				", userPoInfo=" + userPoInfo +
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
		if (jsonObject.isNull("userPoInfo")) {
			Log.d("GetUserPoInfo4C", "has no mapping for key " + "userPoInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectUserPoInfo = jsonObject.optJSONObject("userPoInfo");
		UserPoInfo userPoInfo = new UserPoInfo();

		if (jsonObjectUserPoInfo.isNull("poBase")) {
			Log.d("GetUserPoInfo4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectPoBase = jsonObjectUserPoInfo.optJSONObject("poBase");
		PoBase poBase = new PoBase();

		if (jsonObjectPoBase.isNull("poName")) {
			Log.d("GetUserPoInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poName = jsonObjectPoBase.optString("poName");
		if (jsonObjectPoBase.isNull("poCode")) {
			Log.d("GetUserPoInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poCode = jsonObjectPoBase.optString("poCode");

		userPoInfo.poBase = poBase;
		if (jsonObjectUserPoInfo.isNull("userPoInfoListPerBank")) {
			Log.d("GetUserPoInfo4C", "has no mapping for key " + "userPoInfoListPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray userPoInfoListPerBankArray = jsonObjectUserPoInfo.optJSONArray("userPoInfoListPerBank");
		userPoInfo.userPoInfoListPerBank = new ArrayList<>();

		if (null != userPoInfoListPerBankArray && userPoInfoListPerBankArray.length() > 0) {
			for(int userPoInfoListPerBanki = 0; userPoInfoListPerBanki < userPoInfoListPerBankArray.length(); userPoInfoListPerBanki++) {
				JSONObject jsonObjectUserPoInfoListPerBank = userPoInfoListPerBankArray.optJSONObject(userPoInfoListPerBanki);
				UserPoInfoListPerBank userPoInfoListPerBank = new UserPoInfoListPerBank();

				if (jsonObjectUserPoInfoListPerBank.isNull("paymentBankInfo")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "paymentBankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectPaymentBankInfo = jsonObjectUserPoInfoListPerBank.optJSONObject("paymentBankInfo");
				PaymentBankInfo paymentBankInfo = new PaymentBankInfo();

				if (jsonObjectPaymentBankInfo.isNull("userPaymentId")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				paymentBankInfo.userPaymentId = jsonObjectPaymentBankInfo.optString("userPaymentId");
				if (jsonObjectPaymentBankInfo.isNull("bankCardNo")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				paymentBankInfo.bankCardNo = jsonObjectPaymentBankInfo.optString("bankCardNo");

				userPoInfoListPerBank.paymentBankInfo = paymentBankInfo;
				if (jsonObjectUserPoInfoListPerBank.isNull("bankInfo")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectBankInfo = jsonObjectUserPoInfoListPerBank.optJSONObject("bankInfo");
				BankInfo bankInfo = new BankInfo();

				if (jsonObjectBankInfo.isNull("bankName")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.bankName = jsonObjectBankInfo.optString("bankName");
				if (jsonObjectBankInfo.isNull("paymentType")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.paymentType = jsonObjectBankInfo.optString("paymentType");
				if (jsonObjectBankInfo.isNull("bankLogo")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.bankLogo = jsonObjectBankInfo.optString("bankLogo");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerTxn")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerTxn = jsonObjectBankInfo.optString("maxRapidPayAmountPerTxn");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerDay")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerDay = jsonObjectBankInfo.optString("maxRapidPayAmountPerDay");
				if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerMonth")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayAmountPerMonth = jsonObjectBankInfo.optString("maxRapidPayAmountPerMonth");
				if (jsonObjectBankInfo.isNull("maxRapidPayTxnCountPerDay")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				bankInfo.maxRapidPayTxnCountPerDay = jsonObjectBankInfo.optString("maxRapidPayTxnCountPerDay");

				userPoInfoListPerBank.bankInfo = bankInfo;
				if (jsonObjectUserPoInfoListPerBank.isNull("poRedeemableAsset")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "poRedeemableAsset" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}

				JSONObject jsonObjectPoRedeemableAsset = jsonObjectUserPoInfoListPerBank.optJSONObject("poRedeemableAsset");
				PoRedeemableAsset poRedeemableAsset = new PoRedeemableAsset();

				if (jsonObjectPoRedeemableAsset.isNull("minRedeemAmount")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "minRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poRedeemableAsset.minRedeemAmount = jsonObjectPoRedeemableAsset.optDouble("minRedeemAmount");
				if (jsonObjectPoRedeemableAsset.isNull("maxRedeemAmount")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "maxRedeemAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				poRedeemableAsset.maxRedeemAmount = jsonObjectPoRedeemableAsset.optDouble("maxRedeemAmount");

				userPoInfoListPerBank.poRedeemableAsset = poRedeemableAsset;
				if (jsonObjectUserPoInfoListPerBank.isNull("userFundListPerBank")) {
					Log.d("GetUserPoInfo4C", "has no mapping for key " + "userFundListPerBank" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				JSONArray userFundListPerBankArray = jsonObjectUserPoInfoListPerBank.optJSONArray("userFundListPerBank");
				userPoInfoListPerBank.userFundListPerBank = new ArrayList<>();

				if (null != userFundListPerBankArray && userFundListPerBankArray.length() > 0) {
					for(int userFundListPerBanki = 0; userFundListPerBanki < userFundListPerBankArray.length(); userFundListPerBanki++) {
						JSONObject jsonObjectUserFundListPerBank = userFundListPerBankArray.optJSONObject(userFundListPerBanki);
						UserFundListPerBank userFundListPerBank = new UserFundListPerBank();

						if (jsonObjectUserFundListPerBank.isNull("fundInfo")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "fundInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}

						JSONObject jsonObjectFundInfo = jsonObjectUserFundListPerBank.optJSONObject("fundInfo");
						FundInfo fundInfo = new FundInfo();

						if (jsonObjectFundInfo.isNull("fundCode")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						fundInfo.fundCode = jsonObjectFundInfo.optString("fundCode");
						if (jsonObjectFundInfo.isNull("fundName")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						fundInfo.fundName = jsonObjectFundInfo.optString("fundName");
						if (jsonObjectFundInfo.isNull("poPercentage")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						fundInfo.poPercentage = jsonObjectFundInfo.optDouble("poPercentage");

						userFundListPerBank.fundInfo = fundInfo;
						if (jsonObjectUserFundListPerBank.isNull("poFundAssetInfo")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "poFundAssetInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}

						JSONObject jsonObjectPoFundAssetInfo = jsonObjectUserFundListPerBank.optJSONObject("poFundAssetInfo");
						PoFundAssetInfo poFundAssetInfo = new PoFundAssetInfo();

						if (jsonObjectPoFundAssetInfo.isNull("aviAmount")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "aviAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						poFundAssetInfo.aviAmount = jsonObjectPoFundAssetInfo.optDouble("aviAmount");
						if (jsonObjectPoFundAssetInfo.isNull("totalAmount")) {
							Log.d("GetUserPoInfo4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
						}
						poFundAssetInfo.totalAmount = jsonObjectPoFundAssetInfo.optDouble("totalAmount");

						userFundListPerBank.poFundAssetInfo = poFundAssetInfo;

						userPoInfoListPerBank.userFundListPerBank.add(userFundListPerBank);
					}
				}


				userPoInfo.userPoInfoListPerBank.add(userPoInfoListPerBank);
			}
		}


		this.userPoInfo = userPoInfo;

		return this;
	}
}
