package com.zritc.colorfulfund.data.response.wish;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取心愿列表
 *
 * package: 						com.zrt.dc.controllers.trade.wish
 * svcName(服务名): 					GetUserWishLists4C
 * svcCaption( 服务中文名，可用于注释): 	获取心愿列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9008/tradewish/GetUserWishLists4C
 * comments(服务详细备注，可用于注释):
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserWishLists4C implements Serializable {

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

	public UserWishlistInfo userWishlistInfo;

	/**
	 * investmentInfo
	 */
	public class InvestmentInfo implements Serializable {

		/**
		 * 定投金额
		 */
		public String periodicalAmount = "";

		@Override
		public String toString() {
			return "InvestmentInfo{" +
					"periodicalAmount='" + periodicalAmount + '\'' +
					'}';
		}
	}
	/**
	 * userWishLists
	 */
	public class UserWishLists implements Serializable {

		/**
		 * 心愿名称
		 */
		public String wishName = "";

		/**
		 * 心愿ID
		 */
		public String wishId = "";

		/**
		 * 目标金额
		 */
		public String targetAmount = "";

		/**
		 * 当前投入金额
		 */
		public String currentInvestmentAmount = "";

		/**
		 * 心愿状态
		 */
		public String wishStatus = "";

		/**
		 * 目标完成预期时间
		 */
		public long targetDate;

		@Override
		public String toString() {
			return "UserWishLists{" +
					"wishName='" + wishName + '\'' +
					", wishId='" + wishId + '\'' +
					", targetAmount='" + targetAmount + '\'' +
					", currentInvestmentAmount='" + currentInvestmentAmount + '\'' +
					", wishStatus='" + wishStatus + '\'' +
					", targetDate=" + targetDate +
					'}';
		}
	}
	/**
	 * userWishlistInfo
	 */
	public class UserWishlistInfo implements Serializable {

		public PoBase poBase;

		public InvestmentInfo investmentInfo;

		/**
		 *
		 */
		public List<UserWishLists> userWishLists;

		@Override
		public String toString() {
			return "UserWishlistInfo{" +
					"poBase=" + poBase +
					", investmentInfo=" + investmentInfo +
					", userWishLists=" + userWishLists +
					'}';
		}
	}
	/**
	 * poBase
	 */
	public class PoBase implements Serializable {

		/**
		 * 组合代码
		 */
		public String poCode = "";

		/**
		 * 组合列表
		 */
		public String poName = "";

		/**
		 * 年化收益率
		 */
		public String expectedYearlyRoe = "";

		/**
		 * 组合风险级别
		 */
		public String riskLevel = "";

		@Override
		public String toString() {
			return "PoBase{" +
					"poCode='" + poCode + '\'' +
					", poName='" + poName + '\'' +
					", expectedYearlyRoe='" + expectedYearlyRoe + '\'' +
					", riskLevel='" + riskLevel + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		return "GetUserWishLists4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userWishlistInfo=" + userWishlistInfo +
				'}';
	}

	/**
	 * parse json
	 */
	public synchronized GetUserWishLists4C parseJson(String json) throws JSONException {
		JSONObject jsonObject = new JSONObject(json);

		if (jsonObject.isNull("sid")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		this.sid = jsonObject.optString("sid");
		if (jsonObject.isNull("rid")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		this.rid = jsonObject.optString("rid");
		if (jsonObject.isNull("code")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		this.code = jsonObject.optString("code");
		if (jsonObject.isNull("msg")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		this.msg = jsonObject.optString("msg");
		if (jsonObject.isNull("optype")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		this.optype = jsonObject.optString("optype");
		if (jsonObject.isNull("userWishlistInfo")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "userWishlistInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectUserWishlistInfo = jsonObject.optJSONObject("userWishlistInfo");
		UserWishlistInfo userWishlistInfo = new UserWishlistInfo();

		if (jsonObjectUserWishlistInfo.isNull("poBase")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectPoBase = jsonObjectUserWishlistInfo.optJSONObject("poBase");
		PoBase poBase = new PoBase();

		if (jsonObjectPoBase.isNull("poCode")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poCode = jsonObjectPoBase.optString("poCode");
		if (jsonObjectPoBase.isNull("poName")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.poName = jsonObjectPoBase.optString("poName");
		if (jsonObjectPoBase.isNull("expectedYearlyRoe")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "expectedYearlyRoe" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.expectedYearlyRoe = jsonObjectPoBase.optString("expectedYearlyRoe");
		if (jsonObjectPoBase.isNull("riskLevel")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "riskLevel" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		poBase.riskLevel = jsonObjectPoBase.optString("riskLevel");

		userWishlistInfo.poBase = poBase;
		if (jsonObjectUserWishlistInfo.isNull("investmentInfo")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "investmentInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}

		JSONObject jsonObjectInvestmentInfo = jsonObjectUserWishlistInfo.optJSONObject("investmentInfo");
		InvestmentInfo investmentInfo = new InvestmentInfo();

		if (jsonObjectInvestmentInfo.isNull("periodicalAmount")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "periodicalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		investmentInfo.periodicalAmount = jsonObjectInvestmentInfo.optString("periodicalAmount");

		userWishlistInfo.investmentInfo = investmentInfo;
		if (jsonObjectUserWishlistInfo.isNull("userWishLists")) {
			Log.d("GetUserWishLists4C", "has no mapping for key " + "userWishLists" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
		}
		JSONArray userWishListsArray = jsonObjectUserWishlistInfo.optJSONArray("userWishLists");
		userWishlistInfo.userWishLists = new ArrayList<>();

		if (null != userWishListsArray && userWishListsArray.length() > 0) {
			for(int userWishListsi = 0; userWishListsi < userWishListsArray.length(); userWishListsi++) {
				JSONObject jsonObjectUserWishLists = userWishListsArray.optJSONObject(userWishListsi);
				UserWishLists userWishLists = new UserWishLists();

				if (jsonObjectUserWishLists.isNull("wishName")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "wishName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.wishName = jsonObjectUserWishLists.optString("wishName");
				if (jsonObjectUserWishLists.isNull("wishId")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "wishId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.wishId = jsonObjectUserWishLists.optString("wishId");
				if (jsonObjectUserWishLists.isNull("targetAmount")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "targetAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.targetAmount = jsonObjectUserWishLists.optString("targetAmount");
				if (jsonObjectUserWishLists.isNull("currentInvestmentAmount")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "currentInvestmentAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.currentInvestmentAmount = jsonObjectUserWishLists.optString("currentInvestmentAmount");
				if (jsonObjectUserWishLists.isNull("wishStatus")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "wishStatus" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.wishStatus = jsonObjectUserWishLists.optString("wishStatus");
				if (jsonObjectUserWishLists.isNull("targetDate")) {
					Log.d("GetUserWishLists4C", "has no mapping for key " + "targetDate" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
				}
				userWishLists.targetDate = jsonObjectUserWishLists.optLong("targetDate");

				userWishlistInfo.userWishLists.add(userWishLists);
			}
		}


		this.userWishlistInfo = userWishlistInfo;

		return this;
	}
}
