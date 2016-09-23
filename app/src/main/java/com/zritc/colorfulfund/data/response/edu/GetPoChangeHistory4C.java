package com.zritc.colorfulfund.data.response.edu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取组合变更历史记录
 * <p>
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetPoChangeHistory4C
 * svcCaption( 服务中文名，可用于注释): 	获取组合变更历史记录
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		172.16.101.201/trade/getPoChangeHistory4C
 * comments(服务详细备注，可用于注释):
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetPoChangeHistory4C implements Serializable {

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
    public List<PoChangeHistory> poChangeHistory;

    /**
     * poChangeHistory
     */
    public class PoChangeHistory implements Serializable {

        /**
         * 组合代码
         */
        public String poCode = "";

        /**
         * 组合列表
         */
        public String poName = "";

        /**
         * 激进型等等
         */
        public String riskType = "";

        /**
         * 年化收益率
         */
        public String expectedYearlyRoe = "";

        /**
         * 变更时间
         */
        public long updateTime;
        public String version;

        /**
         *
         */
        public List<PoFundList> poFundList;

        @Override
        public String toString() {
            return "PoChangeHistory{" +
                    "poCode='" + poCode + '\'' +
                    ", poName='" + poName + '\'' +
                    ", riskType='" + riskType + '\'' +
                    ", expectedYearlyRoe='" + expectedYearlyRoe + '\'' +
                    ", updateTime=" + updateTime +
                    ", poFundList=" + poFundList +
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
            return "PoFundList{" +
                    "fundCode='" + fundCode + '\'' +
                    ", fundName='" + fundName + '\'' +
                    ", poPercentage='" + poPercentage + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetPoChangeHistory4C{" +
                "sid='" + sid + '\'' +
                ", rid='" + rid + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", optype='" + optype + '\'' +
                ", poChangeHistory=" + poChangeHistory +
                '}';
    }

    /**
     * parse json
     */
    public synchronized GetPoChangeHistory4C parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        if (jsonObject.isNull("sid")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.sid = jsonObject.optString("sid");
        if (jsonObject.isNull("rid")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.rid = jsonObject.optString("rid");
        if (jsonObject.isNull("code")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.code = jsonObject.optString("code");
        if (jsonObject.isNull("msg")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.msg = jsonObject.optString("msg");
        if (jsonObject.isNull("optype")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        this.optype = jsonObject.optString("optype");
        if (jsonObject.isNull("poChangeHistory")) {
            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "poChangeHistory" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        JSONArray poChangeHistoryArray = jsonObject.optJSONArray("poChangeHistory");
        this.poChangeHistory = new ArrayList<>();

        if (null != poChangeHistoryArray && poChangeHistoryArray.length() > 0) {
            for(int poChangeHistoryi = 0; poChangeHistoryi < poChangeHistoryArray.length(); poChangeHistoryi++) {
                JSONObject jsonObjectPoChangeHistory = poChangeHistoryArray.optJSONObject(poChangeHistoryi);
                PoChangeHistory poChangeHistory = new PoChangeHistory();

                if (jsonObjectPoChangeHistory.isNull("poCode")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "poCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.poCode = jsonObjectPoChangeHistory.optString("poCode");
                if (jsonObjectPoChangeHistory.isNull("poName")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "poName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.poName = jsonObjectPoChangeHistory.optString("poName");
                if (jsonObjectPoChangeHistory.isNull("riskType")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "riskType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.riskType = jsonObjectPoChangeHistory.optString("riskType");
                if (jsonObjectPoChangeHistory.isNull("expectedYearlyRoe")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "expectedYearlyRoe" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.expectedYearlyRoe = jsonObjectPoChangeHistory.optString("expectedYearlyRoe");
                if (jsonObjectPoChangeHistory.isNull("updateTime")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "updateTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.updateTime = jsonObjectPoChangeHistory.optLong("updateTime");
                if (jsonObjectPoChangeHistory.isNull("version")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "version" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                poChangeHistory.version = jsonObjectPoChangeHistory.optString("version");
                if (jsonObjectPoChangeHistory.isNull("poFundList")) {
                    Log.d("GetPoChangeHistory4C", "has no mapping for key " + "poFundList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                }
                JSONArray poFundListArray = jsonObjectPoChangeHistory.optJSONArray("poFundList");
                poChangeHistory.poFundList = new ArrayList<>();

                if (null != poFundListArray && poFundListArray.length() > 0) {
                    for(int poFundListi = 0; poFundListi < poFundListArray.length(); poFundListi++) {
                        JSONObject jsonObjectPoFundList = poFundListArray.optJSONObject(poFundListi);
                        PoFundList poFundList = new PoFundList();

                        if (jsonObjectPoFundList.isNull("fundCode")) {
                            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "fundCode" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                        }
                        poFundList.fundCode = jsonObjectPoFundList.optString("fundCode");
                        if (jsonObjectPoFundList.isNull("fundName")) {
                            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "fundName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                        }
                        poFundList.fundName = jsonObjectPoFundList.optString("fundName");
                        if (jsonObjectPoFundList.isNull("poPercentage")) {
                            Log.d("GetPoChangeHistory4C", "has no mapping for key " + "poPercentage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
                        }
                        poFundList.poPercentage = jsonObjectPoFundList.optString("poPercentage");

                        poChangeHistory.poFundList.add(poFundList);
                    }
                }


                this.poChangeHistory.add(poChangeHistory);
            }
        }


        return this;
    }

}
