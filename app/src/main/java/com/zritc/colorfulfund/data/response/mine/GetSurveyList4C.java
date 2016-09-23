package com.zritc.colorfulfund.data.response.mine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取风险等级评测信息列表
 *
 * package: 						com.zrt.dc.controllers.trade
 * svcName(服务名): 					GetSurveyList4C
 * svcCaption( 服务中文名，可用于注释): 	获取风险等级评测信息列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/trade/GetSurveyList4C
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetSurveyList4C implements Serializable {

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
	public List<SurveyQuestionList> surveyQuestionList;
    
	/**
     * surveyQuestionList
     */
    public class SurveyQuestionList implements Serializable {

	/**
	 * 问题Id
	 */
	public long questionId;

	/**
	 * 问题描述
	 */
	public String questionDesc = "";

	/**
	 * 
	 */
	public List<Answers> answers;
		
		@Override
		public String toString() {
			return "SurveyQuestionList{" +
					"questionId=" + questionId +
					", questionDesc='" + questionDesc + '\'' +
					", answers=" + answers +
					'}';
		}
    }
	/**
     * answers
     */
    public class Answers implements Serializable {

	/**
	 * 答案Id
	 */
	public String answerId = "";

	/**
	 * 答案描述
	 */
	public String answerDesc = "";
		
		@Override
		public String toString() {
			return "Answers{" +
					"answerId='" + answerId + '\'' +
					", answerDesc='" + answerDesc + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetSurveyList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", surveyQuestionList=" + surveyQuestionList +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetSurveyList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("surveyQuestionList")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "surveyQuestionList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray surveyQuestionListArray = jsonObject.optJSONArray("surveyQuestionList");
			this.surveyQuestionList = new ArrayList<>();
			
			if (null != surveyQuestionListArray && surveyQuestionListArray.length() > 0) {
				for(int surveyQuestionListi = 0; surveyQuestionListi < surveyQuestionListArray.length(); surveyQuestionListi++) {
					JSONObject jsonObjectSurveyQuestionList = surveyQuestionListArray.optJSONObject(surveyQuestionListi);
			SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
		
	    	if (jsonObjectSurveyQuestionList.isNull("questionId")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "questionId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			surveyQuestionList.questionId = jsonObjectSurveyQuestionList.optLong("questionId");
	    	if (jsonObjectSurveyQuestionList.isNull("questionDesc")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "questionDesc" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			surveyQuestionList.questionDesc = jsonObjectSurveyQuestionList.optString("questionDesc");
	    	if (jsonObjectSurveyQuestionList.isNull("answers")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "answers" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray answersArray = jsonObjectSurveyQuestionList.optJSONArray("answers");
			surveyQuestionList.answers = new ArrayList<>();
			
			if (null != answersArray && answersArray.length() > 0) {
				for(int answersi = 0; answersi < answersArray.length(); answersi++) {
					JSONObject jsonObjectAnswers = answersArray.optJSONObject(answersi);
			Answers answers = new Answers();
		
	    	if (jsonObjectAnswers.isNull("answerId")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "answerId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			answers.answerId = jsonObjectAnswers.optString("answerId");
	    	if (jsonObjectAnswers.isNull("answerDesc")) {
	    		Log.d("GetSurveyList4C", "has no mapping for key " + "answerDesc" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			answers.answerDesc = jsonObjectAnswers.optString("answerDesc");
					
					surveyQuestionList.answers.add(answers);
				}
			}
			
					
					this.surveyQuestionList.add(surveyQuestionList);
				}
			}
			
    	
    	return this;
    }
}
