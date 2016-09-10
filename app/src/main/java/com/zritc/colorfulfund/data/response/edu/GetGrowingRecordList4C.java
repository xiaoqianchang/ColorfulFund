package com.zritc.colorfulfund.data.response.edu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取成长记录信息列表
 *
 * package: 						com.zrt.dc.controllers.trade.edu
 * svcName(服务名): 					GetGrowingRecordList4C
 * svcCaption( 服务中文名，可用于注释): 	获取成长记录信息列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.202/play/tradeedu/getGrowingRecordList4C
 * comments(服务详细备注，可用于注释): 		
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetGrowingRecordList4C implements Serializable {

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
	public List<GrowingRecordList> growingRecordList;
    
	/**
     * growingRecordList
     */
    public class GrowingRecordList implements Serializable {

	/**
	 * 投资金额
	 */
	public String investmentAmount = "";

	/**
	 * 目标完成预期时间
	 */
	public long targetDate;

	/**
	 * 成长描述
	 */
	public String growingDesc = "";

	public PhotoUrlInfo photoUrlInfo;
		
		@Override
		public String toString() {
			return "GrowingRecordList{" +
					"investmentAmount='" + investmentAmount + '\'' +
					", targetDate=" + targetDate +
					", growingDesc='" + growingDesc + '\'' +
					", photoUrlInfo=" + photoUrlInfo +
					'}';
		}
    }
	/**
     * photoUrlInfo
     */
    public class PhotoUrlInfo implements Serializable {

	/**
	 * 图片文件路径
	 */
	public String photoUrl = "";
		
		@Override
		public String toString() {
			return "PhotoUrlInfo{" +
					"photoUrl='" + photoUrl + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetGrowingRecordList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", growingRecordList=" + growingRecordList +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetGrowingRecordList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("growingRecordList")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "growingRecordList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray growingRecordListArray = jsonObject.optJSONArray("growingRecordList");
			this.growingRecordList = new ArrayList<>();
			
			if (null != growingRecordListArray && growingRecordListArray.length() > 0) {
				for(int growingRecordListi = 0; growingRecordListi < growingRecordListArray.length(); growingRecordListi++) {
					JSONObject jsonObjectGrowingRecordList = growingRecordListArray.optJSONObject(growingRecordListi);
			GrowingRecordList growingRecordList = new GrowingRecordList();
		
	    	if (jsonObjectGrowingRecordList.isNull("investmentAmount")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "investmentAmount" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			growingRecordList.investmentAmount = jsonObjectGrowingRecordList.optString("investmentAmount");
	    	if (jsonObjectGrowingRecordList.isNull("targetDate")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "targetDate" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			growingRecordList.targetDate = jsonObjectGrowingRecordList.optLong("targetDate");
	    	if (jsonObjectGrowingRecordList.isNull("growingDesc")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "growingDesc" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			growingRecordList.growingDesc = jsonObjectGrowingRecordList.optString("growingDesc");
	    	if (jsonObjectGrowingRecordList.isNull("photoUrlInfo")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "photoUrlInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPhotoUrlInfo = jsonObjectGrowingRecordList.optJSONObject("photoUrlInfo");
			PhotoUrlInfo photoUrlInfo = new PhotoUrlInfo();
		
	    	if (jsonObjectPhotoUrlInfo.isNull("photoUrl")) {
	    		Log.d("GetGrowingRecordList4C", "has no mapping for key " + "photoUrl" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			photoUrlInfo.photoUrl = jsonObjectPhotoUrlInfo.optString("photoUrl");
	    		
	    		growingRecordList.photoUrlInfo = photoUrlInfo;
					
					this.growingRecordList.add(growingRecordList);
				}
			}
			
    	
    	return this;
    }
}
