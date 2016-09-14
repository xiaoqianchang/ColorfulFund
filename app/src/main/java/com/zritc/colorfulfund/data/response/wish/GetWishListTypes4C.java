package com.zritc.colorfulfund.data.response.wish;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取心愿类型列表
 *
 * package: 						com.zrt.dc.controllers.trade.wish
 * svcName(服务名): 					GetWishListTypes4C
 * svcCaption( 服务中文名，可用于注释): 	获取心愿类型列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		172.16.101.201/tradewish/getWishListTypes4C
 * comments(服务详细备注，可用于注释): 		
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetWishListTypes4C implements Serializable {

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
	public List<WishListTypes> wishListTypes;
    
	/**
     * wishListTypes
     */
    public class WishListTypes implements Serializable {

	/**
	 * 心愿类型唯一标识ID
	 */
	public String wishTypeId = "";

	/**
	 * 心愿类型名称
	 */
	public String wishTypeName = "";
		
		@Override
		public String toString() {
			return "WishListTypes{" +
					"wishTypeId='" + wishTypeId + '\'' +
					", wishTypeName='" + wishTypeName + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetWishListTypes4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", wishListTypes=" + wishListTypes +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetWishListTypes4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("wishListTypes")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "wishListTypes" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray wishListTypesArray = jsonObject.optJSONArray("wishListTypes");
			this.wishListTypes = new ArrayList<>();
			
			if (null != wishListTypesArray && wishListTypesArray.length() > 0) {
				for(int wishListTypesi = 0; wishListTypesi < wishListTypesArray.length(); wishListTypesi++) {
					JSONObject jsonObjectWishListTypes = wishListTypesArray.optJSONObject(wishListTypesi);
			WishListTypes wishListTypes = new WishListTypes();
		
	    	if (jsonObjectWishListTypes.isNull("wishTypeId")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "wishTypeId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			wishListTypes.wishTypeId = jsonObjectWishListTypes.optString("wishTypeId");
	    	if (jsonObjectWishListTypes.isNull("wishTypeName")) {
	    		Log.d("GetWishListTypes4C", "has no mapping for key " + "wishTypeName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			wishListTypes.wishTypeName = jsonObjectWishListTypes.optString("wishTypeName");
					
					this.wishListTypes.add(wishListTypes);
				}
			}
			
    	
    	return this;
    }
}
