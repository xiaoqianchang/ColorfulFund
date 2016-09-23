package com.zritc.colorfulfund.data.response.mine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取我的主页信息
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetUserMainPageInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取我的主页信息
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/GetUserMainPageInfo4C
 * comments(服务详细备注，可用于注释): 		获取用户 我的 首页信息，包括资产，银行卡，风险评估结果
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserMainPageInfo4C implements Serializable {

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

	public UserMainPageInfo userMainPageInfo;
    
	/**
     * userBankCardInfo
     */
    public class UserBankCardInfo implements Serializable {

	public PaymentBankInfo paymentBankInfo;

	public BankInfo bankInfo;
		
		@Override
		public String toString() {
			return "UserBankCardInfo{" +
					"paymentBankInfo=" + paymentBankInfo +
					", bankInfo=" + bankInfo +
					'}';
		}
    }
	/**
     * userAsset
     */
    public class UserAsset implements Serializable {

	public PoBase poBase;

	public UserPoAsset userPoAsset;
		
		@Override
		public String toString() {
			return "UserAsset{" +
					"poBase=" + poBase +
					", userPoAsset=" + userPoAsset +
					'}';
		}
    }
	/**
     * userMainPageInfo
     */
    public class UserMainPageInfo implements Serializable {

	public UserInfo userInfo;

	/**
	 * 
	 */
	public List<UserAsset> userAsset;

	/**
	 * 
	 */
	public List<UserBankCardList> userBankCardList;

	public UserRiskLevelInfo userRiskLevelInfo;
		
		@Override
		public String toString() {
			return "UserMainPageInfo{" +
					"userInfo=" + userInfo +
					", userAsset=" + userAsset +
					", userBankCardList=" + userBankCardList +
					", userRiskLevelInfo=" + userRiskLevelInfo +
					'}';
		}
    }
	/**
     * userInfo
     */
    public class UserInfo implements Serializable {

	/**
	 * 用户Id
	 */
	public String userId = "";

	/**
	 * 电话
	 */
	public String phone = "";

	/**
	 * 昵称
	 */
	public String nickName = "";

	/**
	 * 头像
	 */
	public String photoURL = "";
		
		@Override
		public String toString() {
			return "UserInfo{" +
					"userId='" + userId + '\'' +
					", phone='" + phone + '\'' +
					", nickName='" + nickName + '\'' +
					", photoURL='" + photoURL + '\'' +
					'}';
		}
    }
	/**
     * userBankCardList
     */
    public class UserBankCardList implements Serializable {

	public UserBankCardInfo userBankCardInfo;
		
		@Override
		public String toString() {
			return "UserBankCardList{" +
					"userBankCardInfo=" + userBankCardInfo +
					'}';
		}
    }
	/**
     * userPoAsset
     */
    public class UserPoAsset implements Serializable {

	/**
	 * 当前收益
	 */
	public String totalProfit = "";

	/**
	 * 当前资产
	 */
	public String totalAmount = "";
		
		@Override
		public String toString() {
			return "UserPoAsset{" +
					"totalProfit='" + totalProfit + '\'' +
					", totalAmount='" + totalAmount + '\'' +
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
     * userRiskLevelInfo
     */
    public class UserRiskLevelInfo implements Serializable {

	/**
	 * 风险类型
	 */
	public String riskLevelType = "";

	/**
	 * 描述
	 */
	public String riskLevelDesc = "";
		
		@Override
		public String toString() {
			return "UserRiskLevelInfo{" +
					"riskLevelType='" + riskLevelType + '\'' +
					", riskLevelDesc='" + riskLevelDesc + '\'' +
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
    
	@Override
	public String toString() {
		return "GetUserMainPageInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userMainPageInfo=" + userMainPageInfo +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserMainPageInfo4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("userMainPageInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userMainPageInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserMainPageInfo = jsonObject.optJSONObject("userMainPageInfo");
			UserMainPageInfo userMainPageInfo = new UserMainPageInfo();
		
	    	if (jsonObjectUserMainPageInfo.isNull("userInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserInfo = jsonObjectUserMainPageInfo.optJSONObject("userInfo");
			UserInfo userInfo = new UserInfo();
		
	    	if (jsonObjectUserInfo.isNull("userId")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.userId = jsonObjectUserInfo.optString("userId");
	    	if (jsonObjectUserInfo.isNull("phone")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "phone" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.phone = jsonObjectUserInfo.optString("phone");
	    	if (jsonObjectUserInfo.isNull("nickName")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.nickName = jsonObjectUserInfo.optString("nickName");
	    	if (jsonObjectUserInfo.isNull("photoURL")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.photoURL = jsonObjectUserInfo.optString("photoURL");
	    		
	    		userMainPageInfo.userInfo = userInfo;
	    	if (jsonObjectUserMainPageInfo.isNull("userAsset")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userAsset" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userAssetArray = jsonObjectUserMainPageInfo.optJSONArray("userAsset");
			userMainPageInfo.userAsset = new ArrayList<>();
			
			if (null != userAssetArray && userAssetArray.length() > 0) {
				for(int userAsseti = 0; userAsseti < userAssetArray.length(); userAsseti++) {
					JSONObject jsonObjectUserAsset = userAssetArray.optJSONObject(userAsseti);
			UserAsset userAsset = new UserAsset();
		
	    	if (jsonObjectUserAsset.isNull("poBase")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPoBase = jsonObjectUserAsset.optJSONObject("poBase");
			PoBase poBase = new PoBase();
		
	    	if (jsonObjectPoBase.isNull("poCode")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.poCode = jsonObjectPoBase.optString("poCode");
	    	if (jsonObjectPoBase.isNull("poName")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.poName = jsonObjectPoBase.optString("poName");
	    	if (jsonObjectPoBase.isNull("expectedYearlyRoe")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "expectedYearlyRoe" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.expectedYearlyRoe = jsonObjectPoBase.optString("expectedYearlyRoe");
	    	if (jsonObjectPoBase.isNull("riskLevel")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "riskLevel" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.riskLevel = jsonObjectPoBase.optString("riskLevel");
	    		
	    		userAsset.poBase = poBase;
	    	if (jsonObjectUserAsset.isNull("userPoAsset")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userPoAsset" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserPoAsset = jsonObjectUserAsset.optJSONObject("userPoAsset");
			UserPoAsset userPoAsset = new UserPoAsset();
		
	    	if (jsonObjectUserPoAsset.isNull("totalProfit")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "totalProfit" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoAsset.totalProfit = jsonObjectUserPoAsset.optString("totalProfit");
	    	if (jsonObjectUserPoAsset.isNull("totalAmount")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoAsset.totalAmount = jsonObjectUserPoAsset.optString("totalAmount");
	    		
	    		userAsset.userPoAsset = userPoAsset;
					
					userMainPageInfo.userAsset.add(userAsset);
				}
			}
			
	    	if (jsonObjectUserMainPageInfo.isNull("userBankCardList")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userBankCardList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userBankCardListArray = jsonObjectUserMainPageInfo.optJSONArray("userBankCardList");
			userMainPageInfo.userBankCardList = new ArrayList<>();
			
			if (null != userBankCardListArray && userBankCardListArray.length() > 0) {
				for(int userBankCardListi = 0; userBankCardListi < userBankCardListArray.length(); userBankCardListi++) {
					JSONObject jsonObjectUserBankCardList = userBankCardListArray.optJSONObject(userBankCardListi);
			UserBankCardList userBankCardList = new UserBankCardList();
		
	    	if (jsonObjectUserBankCardList.isNull("userBankCardInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userBankCardInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserBankCardInfo = jsonObjectUserBankCardList.optJSONObject("userBankCardInfo");
			UserBankCardInfo userBankCardInfo = new UserBankCardInfo();
		
	    	if (jsonObjectUserBankCardInfo.isNull("paymentBankInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "paymentBankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPaymentBankInfo = jsonObjectUserBankCardInfo.optJSONObject("paymentBankInfo");
			PaymentBankInfo paymentBankInfo = new PaymentBankInfo();
		
	    	if (jsonObjectPaymentBankInfo.isNull("userPaymentId")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userPaymentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			paymentBankInfo.userPaymentId = jsonObjectPaymentBankInfo.optString("userPaymentId");
	    	if (jsonObjectPaymentBankInfo.isNull("bankCardNo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "bankCardNo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			paymentBankInfo.bankCardNo = jsonObjectPaymentBankInfo.optString("bankCardNo");
	    		
	    		userBankCardInfo.paymentBankInfo = paymentBankInfo;
	    	if (jsonObjectUserBankCardInfo.isNull("bankInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "bankInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectBankInfo = jsonObjectUserBankCardInfo.optJSONObject("bankInfo");
			BankInfo bankInfo = new BankInfo();
		
	    	if (jsonObjectBankInfo.isNull("bankName")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "bankName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.bankName = jsonObjectBankInfo.optString("bankName");
	    	if (jsonObjectBankInfo.isNull("paymentType")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "paymentType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.paymentType = jsonObjectBankInfo.optString("paymentType");
	    	if (jsonObjectBankInfo.isNull("bankLogo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "bankLogo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.bankLogo = jsonObjectBankInfo.optString("bankLogo");
	    	if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerTxn")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "maxRapidPayAmountPerTxn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.maxRapidPayAmountPerTxn = jsonObjectBankInfo.optString("maxRapidPayAmountPerTxn");
	    	if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerDay")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "maxRapidPayAmountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.maxRapidPayAmountPerDay = jsonObjectBankInfo.optString("maxRapidPayAmountPerDay");
	    	if (jsonObjectBankInfo.isNull("maxRapidPayAmountPerMonth")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "maxRapidPayAmountPerMonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.maxRapidPayAmountPerMonth = jsonObjectBankInfo.optString("maxRapidPayAmountPerMonth");
	    	if (jsonObjectBankInfo.isNull("maxRapidPayTxnCountPerDay")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "maxRapidPayTxnCountPerDay" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			bankInfo.maxRapidPayTxnCountPerDay = jsonObjectBankInfo.optString("maxRapidPayTxnCountPerDay");
	    		
	    		userBankCardInfo.bankInfo = bankInfo;
	    		
	    		userBankCardList.userBankCardInfo = userBankCardInfo;
					
					userMainPageInfo.userBankCardList.add(userBankCardList);
				}
			}
			
	    	if (jsonObjectUserMainPageInfo.isNull("userRiskLevelInfo")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "userRiskLevelInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserRiskLevelInfo = jsonObjectUserMainPageInfo.optJSONObject("userRiskLevelInfo");
			UserRiskLevelInfo userRiskLevelInfo = new UserRiskLevelInfo();
		
	    	if (jsonObjectUserRiskLevelInfo.isNull("riskLevelType")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "riskLevelType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userRiskLevelInfo.riskLevelType = jsonObjectUserRiskLevelInfo.optString("riskLevelType");
	    	if (jsonObjectUserRiskLevelInfo.isNull("riskLevelDesc")) {
	    		Log.d("GetUserMainPageInfo4C", "has no mapping for key " + "riskLevelDesc" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userRiskLevelInfo.riskLevelDesc = jsonObjectUserRiskLevelInfo.optString("riskLevelDesc");
	    		
	    		userMainPageInfo.userRiskLevelInfo = userRiskLevelInfo;
	    		
	    		this.userMainPageInfo = userMainPageInfo;
    	
    	return this;
    }
}
