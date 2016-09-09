package com.zritc.colorfulfund.data.response.circle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取某个帖子的评论列表
 *
 * package: 						com.zrt.dc.controllers.circle
 * svcName(服务名): 					GetCommentList4C
 * svcCaption( 服务中文名，可用于注释): 	获取某个帖子的评论列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.202/play/circle/getCommentList4C
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetCommentList4C implements Serializable {

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
	public List<CommentList> commentList;
    
	/**
     * commentList
     */
    public class CommentList implements Serializable {

	public CommentInfo commentInfo;

	/**
	 * 该登录用户是否已申诉过此评论
	 */
	public boolean reportStatus;
		
		@Override
		public String toString() {
			return "CommentList{" +
					"commentInfo=" + commentInfo +
					", reportStatus=" + reportStatus +
					'}';
		}
    }
	/**
     * commentInfo
     */
    public class CommentInfo implements Serializable {

	/**
	 * commentId
	 */
	public long commentId;

	/**
	 * 评论内容
	 */
	public String content = "";

	public AuthorInfo authorInfo;

	/**
	 * 发布时间
	 */
	public long postTime;
		
		@Override
		public String toString() {
			return "CommentInfo{" +
					"commentId=" + commentId +
					", content='" + content + '\'' +
					", authorInfo=" + authorInfo +
					", postTime=" + postTime +
					'}';
		}
    }
	/**
     * authorInfo
     */
    public class AuthorInfo implements Serializable {

	/**
	 * userid
	 */
	public long userId;

	/**
	 * 作者昵称
	 */
	public String nickName = "";

	/**
	 * 作者头像
	 */
	public String photoURL = "";
		
		@Override
		public String toString() {
			return "AuthorInfo{" +
					"userId=" + userId +
					", nickName='" + nickName + '\'' +
					", photoURL='" + photoURL + '\'' +
					'}';
		}
    }
    
	@Override
	public String toString() {
		return "GetCommentList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", commentList=" + commentList +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetCommentList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("commentList")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "commentList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray commentListArray = jsonObject.optJSONArray("commentList");
			this.commentList = new ArrayList<>();
			
			if (null != commentListArray && commentListArray.length() > 0) {
				for(int commentListi = 0; commentListi < commentListArray.length(); commentListi++) {
					JSONObject jsonObjectCommentList = commentListArray.optJSONObject(commentListi);
			CommentList commentList = new CommentList();
		
	    	if (jsonObjectCommentList.isNull("commentInfo")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "commentInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectCommentInfo = jsonObjectCommentList.optJSONObject("commentInfo");
			CommentInfo commentInfo = new CommentInfo();
		
	    	if (jsonObjectCommentInfo.isNull("commentId")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "commentId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			commentInfo.commentId = jsonObjectCommentInfo.optLong("commentId");
	    	if (jsonObjectCommentInfo.isNull("content")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "content" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			commentInfo.content = jsonObjectCommentInfo.optString("content");
	    	if (jsonObjectCommentInfo.isNull("authorInfo")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "authorInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectAuthorInfo = jsonObjectCommentInfo.optJSONObject("authorInfo");
			AuthorInfo authorInfo = new AuthorInfo();
		
	    	if (jsonObjectAuthorInfo.isNull("userId")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.userId = jsonObjectAuthorInfo.optLong("userId");
	    	if (jsonObjectAuthorInfo.isNull("nickName")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.nickName = jsonObjectAuthorInfo.optString("nickName");
	    	if (jsonObjectAuthorInfo.isNull("photoURL")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.photoURL = jsonObjectAuthorInfo.optString("photoURL");
	    		
	    		commentInfo.authorInfo = authorInfo;
	    	if (jsonObjectCommentInfo.isNull("postTime")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "postTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			commentInfo.postTime = jsonObjectCommentInfo.optLong("postTime");
	    		
	    		commentList.commentInfo = commentInfo;
	    	if (jsonObjectCommentList.isNull("reportStatus")) {
	    		Log.d("GetCommentList4C", "has no mapping for key " + "reportStatus" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			commentList.reportStatus = jsonObjectCommentList.optBoolean("reportStatus");
					
					this.commentList.add(commentList);
				}
			}
			
    	
    	return this;
    }
}
