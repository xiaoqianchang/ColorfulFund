package com.zritc.colorfulfund.data.response.mine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户组合交易历史记录
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetUserTradeHistory4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户组合交易历史记录
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/GetUserTradeHistory4C
 * comments(服务详细备注，可用于注释): 		获取用户组合交易历史记录
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserTradeHistory4C implements Serializable {

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
	public List<UserTradeHistory> userTradeHistory;
    
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
     * tradeHistory
     */
    public class TradeHistory implements Serializable {

	/**
	 * 申购，赎回，调仓等等 这里是文字，不是数字代码
	 */
	public String actionType = "";

	/**
	 * 未确认， 已确认等等 这里是文字，不是数字代码
	 */
	public String status = "";

	/**
	 * 日期
	 */
	public long createDateOn;

	/**
	 * 交易额
	 */
	public String tradeAmount = "";
		
		@Override
		public String toString() {
			return "TradeHistory{" +
					"actionType='" + actionType + '\'' +
					", status='" + status + '\'' +
					", createDateOn=" + createDateOn +
					", tradeAmount='" + tradeAmount + '\'' +
					'}';
		}
    }
	/**
     * userTradeHistory
     */
    public class UserTradeHistory implements Serializable {

	public PoBase poBase;

	public UserPoAsset userPoAsset;

	/**
	 * 
	 */
	public List<TradeHistory> tradeHistory;
		
		@Override
		public String toString() {
			return "UserTradeHistory{" +
					"poBase=" + poBase +
					", userPoAsset=" + userPoAsset +
					", tradeHistory=" + tradeHistory +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetUserTradeHistory4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", userTradeHistory=" + userTradeHistory +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserTradeHistory4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("userTradeHistory")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "userTradeHistory" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userTradeHistoryArray = jsonObject.optJSONArray("userTradeHistory");
			this.userTradeHistory = new ArrayList<>();
			
			if (null != userTradeHistoryArray && userTradeHistoryArray.length() > 0) {
				for(int userTradeHistoryi = 0; userTradeHistoryi < userTradeHistoryArray.length(); userTradeHistoryi++) {
					JSONObject jsonObjectUserTradeHistory = userTradeHistoryArray.optJSONObject(userTradeHistoryi);
			UserTradeHistory userTradeHistory = new UserTradeHistory();
		
	    	if (jsonObjectUserTradeHistory.isNull("poBase")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPoBase = jsonObjectUserTradeHistory.optJSONObject("poBase");
			PoBase poBase = new PoBase();
		
	    	if (jsonObjectPoBase.isNull("poCode")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.poCode = jsonObjectPoBase.optString("poCode");
	    	if (jsonObjectPoBase.isNull("poName")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.poName = jsonObjectPoBase.optString("poName");
	    	if (jsonObjectPoBase.isNull("expectedYearlyRoe")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "expectedYearlyRoe" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.expectedYearlyRoe = jsonObjectPoBase.optString("expectedYearlyRoe");
	    	if (jsonObjectPoBase.isNull("riskLevel")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "riskLevel" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			poBase.riskLevel = jsonObjectPoBase.optString("riskLevel");
	    		
	    		userTradeHistory.poBase = poBase;
	    	if (jsonObjectUserTradeHistory.isNull("userPoAsset")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "userPoAsset" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserPoAsset = jsonObjectUserTradeHistory.optJSONObject("userPoAsset");
			UserPoAsset userPoAsset = new UserPoAsset();
		
	    	if (jsonObjectUserPoAsset.isNull("totalProfit")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "totalProfit" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoAsset.totalProfit = jsonObjectUserPoAsset.optString("totalProfit");
	    	if (jsonObjectUserPoAsset.isNull("totalAmount")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userPoAsset.totalAmount = jsonObjectUserPoAsset.optString("totalAmount");
	    		
	    		userTradeHistory.userPoAsset = userPoAsset;
	    	if (jsonObjectUserTradeHistory.isNull("tradeHistory")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "tradeHistory" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray tradeHistoryArray = jsonObjectUserTradeHistory.optJSONArray("tradeHistory");
			userTradeHistory.tradeHistory = new ArrayList<>();
			
			if (null != tradeHistoryArray && tradeHistoryArray.length() > 0) {
				for(int tradeHistoryi = 0; tradeHistoryi < tradeHistoryArray.length(); tradeHistoryi++) {
					JSONObject jsonObjectTradeHistory = tradeHistoryArray.optJSONObject(tradeHistoryi);
			TradeHistory tradeHistory = new TradeHistory();
		
	    	if (jsonObjectTradeHistory.isNull("actionType")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "actionType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tradeHistory.actionType = jsonObjectTradeHistory.optString("actionType");
	    	if (jsonObjectTradeHistory.isNull("status")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "status" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tradeHistory.status = jsonObjectTradeHistory.optString("status");
	    	if (jsonObjectTradeHistory.isNull("createDateOn")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "createDateOn" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tradeHistory.createDateOn = jsonObjectTradeHistory.optLong("createDateOn");
	    	if (jsonObjectTradeHistory.isNull("tradeAmount")) {
	    		Log.d("GetUserTradeHistory4C", "has no mapping for key " + "tradeAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tradeHistory.tradeAmount = jsonObjectTradeHistory.optString("tradeAmount");
					
					userTradeHistory.tradeHistory.add(tradeHistory);
				}
			}
			
					
					this.userTradeHistory.add(userTradeHistory);
				}
			}
			
    	
    	return this;
    }
}
