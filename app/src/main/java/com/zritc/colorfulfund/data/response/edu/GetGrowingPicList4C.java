package com.zritc.colorfulfund.data.response.edu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取成长记录图片列表
 *
 * package: 						com.zrt.dc.controllers.trade.edu
 * svcName(服务名): 					GetGrowingPicList4C
 * svcCaption( 服务中文名，可用于注释): 	获取成长记录图片列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.202/play/tradeedu/getGrowingPicList4C
 * comments(服务详细备注，可用于注释): 		
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetGrowingPicList4C implements Serializable {

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
	public List<GrowingPicList> growingPicList;
    
	/**
     * growingPicList
     */
    public class GrowingPicList implements Serializable {

	/**
	 * 图片文件路径
	 */
	public String photoUrl = "";
		
		@Override
		public String toString() {
			return "GrowingPicList{" +
					"photoUrl='" + photoUrl + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetGrowingPicList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", growingPicList=" + growingPicList +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetGrowingPicList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("growingPicList")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "growingPicList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray growingPicListArray = jsonObject.optJSONArray("growingPicList");
			this.growingPicList = new ArrayList<>();
			
			if (null != growingPicListArray && growingPicListArray.length() > 0) {
				for(int growingPicListi = 0; growingPicListi < growingPicListArray.length(); growingPicListi++) {
					JSONObject jsonObjectGrowingPicList = growingPicListArray.optJSONObject(growingPicListi);
			GrowingPicList growingPicList = new GrowingPicList();
		
	    	if (jsonObjectGrowingPicList.isNull("photoUrl")) {
	    		Log.d("GetGrowingPicList4C", "has no mapping for key " + "photoUrl" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			growingPicList.photoUrl = jsonObjectGrowingPicList.optString("photoUrl");
					
					this.growingPicList.add(growingPicList);
				}
			}
			
    	
    	return this;
    }
}
