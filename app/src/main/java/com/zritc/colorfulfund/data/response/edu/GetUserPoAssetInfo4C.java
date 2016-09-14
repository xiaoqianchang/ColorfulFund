package com.zritc.colorfulfund.data.response.edu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户组合资产信息，包括基金列表信息，初始投资，定投金额，完成目标时间以及年化收益率
 * <p>
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetUserPoAssetInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户组合资产信息，包括基金列表信息，初始投资，定投金额，完成目标时间以及年化收益率
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		172.16.101.201/trade/getUserPoAssetInfo4C
 * comments(服务详细备注，可用于注释):
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserPoAssetInfo4C implements Serializable {

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

    public UserPoInvestInfo userPoInvestInfo;

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
     * userInitPoAssetInfo
     */
    public class UserInitPoAssetInfo implements Serializable {

        /**
         * 初始投资
         */
        public String initialtAmount = "";

        /**
         * 每月定投
         */
        public String profitbythismonth = "";

        /**
         * 目标完成预期时间
         */
        public long targetDate;

        /**
         * 目标资产
         */
        public String targetAmount = "";

        /**
         * 场景类型
         */
        public String sceneType = "";

        /**
         * 场景ID
         */
        public long sceneId;

        @Override
        public String toString() {
            return "UserInitPoAssetInfo{" +
                    "initialtAmount='" + initialtAmount + '\'' +
                    ", profitbythismonth='" + profitbythismonth + '\'' +
                    ", targetDate=" + targetDate +
                    ", targetAmount='" + targetAmount + '\'' +
                    ", sceneType='" + sceneType + '\'' +
                    ", sceneId=" + sceneId +
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
         * 基金百分比
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
     * userPoInvestInfo
     */
    public class UserPoInvestInfo implements Serializable {

        public UserInitPoAssetInfo userInitPoAssetInfo;

        public UserPoAsset userPoAsset;

        public FundPoInfo fundPoInfo;

        @Override
        public String toString() {
            return "UserPoInvestInfo{" +
                    "userInitPoAssetInfo=" + userInitPoAssetInfo +
                    ", userPoAsset=" + userPoAsset +
                    ", fundPoInfo=" + fundPoInfo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetUserPoAssetInfo4C{" +
                "sid='" + sid + '\'' +
                ", rid='" + rid + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", optype='" + optype + '\'' +
                ", userPoInvestInfo=" + userPoInvestInfo +
                '}';
    }

    /**
     * parse json
     */
    public synchronized GetUserPoAssetInfo4C parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        if (jsonObject.isNull("sid")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.sid = jsonObject.optString("sid");
        if (jsonObject.isNull("rid")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.rid = jsonObject.optString("rid");
        if (jsonObject.isNull("code")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.code = jsonObject.optString("code");
        if (jsonObject.isNull("msg")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.msg = jsonObject.optString("msg");
        if (jsonObject.isNull("optype")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.optype = jsonObject.optString("optype");
        if (jsonObject.isNull("userPoInvestInfo")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "userPoInvestInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectUserPoInvestInfo = jsonObject.optJSONObject("userPoInvestInfo");
        UserPoInvestInfo userPoInvestInfo = new UserPoInvestInfo();

        if (jsonObjectUserPoInvestInfo.isNull("userInitPoAssetInfo")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "userInitPoAssetInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectUserInitPoAssetInfo = jsonObjectUserPoInvestInfo.optJSONObject("userInitPoAssetInfo");
        UserInitPoAssetInfo userInitPoAssetInfo = new UserInitPoAssetInfo();

        if (jsonObjectUserInitPoAssetInfo.isNull("initialtAmount")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "initialtAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.initialtAmount = jsonObjectUserInitPoAssetInfo.optString("initialtAmount");
        if (jsonObjectUserInitPoAssetInfo.isNull("profitbythismonth")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "profitbythismonth" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.profitbythismonth = jsonObjectUserInitPoAssetInfo.optString("profitbythismonth");
        if (jsonObjectUserInitPoAssetInfo.isNull("targetDate")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "targetDate" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.targetDate = jsonObjectUserInitPoAssetInfo.optLong("targetDate");
        if (jsonObjectUserInitPoAssetInfo.isNull("targetAmount")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "targetAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.targetAmount = jsonObjectUserInitPoAssetInfo.optString("targetAmount");
        if (jsonObjectUserInitPoAssetInfo.isNull("sceneType")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "sceneType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.sceneType = jsonObjectUserInitPoAssetInfo.optString("sceneType");
        if (jsonObjectUserInitPoAssetInfo.isNull("sceneId")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "sceneId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userInitPoAssetInfo.sceneId = jsonObjectUserInitPoAssetInfo.optLong("sceneId");

        userPoInvestInfo.userInitPoAssetInfo = userInitPoAssetInfo;
        if (jsonObjectUserPoInvestInfo.isNull("userPoAsset")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "userPoAsset" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectUserPoAsset = jsonObjectUserPoInvestInfo.optJSONObject("userPoAsset");
        UserPoAsset userPoAsset = new UserPoAsset();

        if (jsonObjectUserPoAsset.isNull("totalProfit")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "totalProfit" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userPoAsset.totalProfit = jsonObjectUserPoAsset.optString("totalProfit");
        if (jsonObjectUserPoAsset.isNull("totalAmount")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "totalAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        userPoAsset.totalAmount = jsonObjectUserPoAsset.optString("totalAmount");

        userPoInvestInfo.userPoAsset = userPoAsset;
        if (jsonObjectUserPoInvestInfo.isNull("fundPoInfo")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "fundPoInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectFundPoInfo = jsonObjectUserPoInvestInfo.optJSONObject("fundPoInfo");
        FundPoInfo fundPoInfo = new FundPoInfo();

        if (jsonObjectFundPoInfo.isNull("poBase")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "poBase" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectPoBase = jsonObjectFundPoInfo.optJSONObject("poBase");
        PoBase poBase = new PoBase();

        if (jsonObjectPoBase.isNull("poCode")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        poBase.poCode = jsonObjectPoBase.optString("poCode");
        if (jsonObjectPoBase.isNull("poName")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        poBase.poName = jsonObjectPoBase.optString("poName");
        if (jsonObjectPoBase.isNull("expectedYearlyRoe")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "expectedYearlyRoe" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        poBase.expectedYearlyRoe = jsonObjectPoBase.optString("expectedYearlyRoe");
        if (jsonObjectPoBase.isNull("riskLevel")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "riskLevel" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        poBase.riskLevel = jsonObjectPoBase.optString("riskLevel");

        fundPoInfo.poBase = poBase;
        if (jsonObjectFundPoInfo.isNull("poFundList")) {
            Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "poFundList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        JSONArray poFundListArray = jsonObjectFundPoInfo.optJSONArray("poFundList");
        fundPoInfo.poFundList = new ArrayList<>();

        if (null != poFundListArray && poFundListArray.length() > 0) {
            for (int poFundListi = 0; poFundListi < poFundListArray.length(); poFundListi++) {
                JSONObject jsonObjectPoFundList = poFundListArray.optJSONObject(poFundListi);
                PoFundList poFundList = new PoFundList();

                if (jsonObjectPoFundList.isNull("fundCode")) {
                    Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poFundList.fundCode = jsonObjectPoFundList.optString("fundCode");
                if (jsonObjectPoFundList.isNull("fundName")) {
                    Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poFundList.fundName = jsonObjectPoFundList.optString("fundName");
                if (jsonObjectPoFundList.isNull("poPercentage")) {
                    Log.d("GetUserPoAssetInfo4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poFundList.poPercentage = jsonObjectPoFundList.optString("poPercentage");

                fundPoInfo.poFundList.add(poFundList);
            }
        }


        userPoInvestInfo.fundPoInfo = fundPoInfo;

        this.userPoInvestInfo = userPoInvestInfo;

        return this;
    }
}
