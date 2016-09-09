package com.zritc.colorfulfund.data.response.circle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取某个帖子的详情
 *
 * package: 						com.zrt.dc.controllers.circle
 * svcName(服务名): 					GetPostInfo4C
 * svcCaption( 服务中文名，可用于注释): 	获取某个帖子的详情
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.202/play/circle/getPostInfo4C
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetPostInfo4C implements Serializable {

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

	public Result result;
    
	/**
     * result
     */
    public class Result implements Serializable {

	public PostInfo postInfo;

	/**
	 * 该用户是否点过赞
	 */
	public boolean thumbStatus;

	/**
	 * 该用户是否收藏过
	 */
	public boolean collectionStatus;

	/**
	 * 
	 */
	public List<ReferList> referList;
		
		@Override
		public String toString() {
			return "Result{" +
					"postInfo=" + postInfo +
					", thumbStatus=" + thumbStatus +
					", collectionStatus=" + collectionStatus +
					", referList=" + referList +
					'}';
		}
    }
	/**
     * referList
     */
    public class ReferList implements Serializable {

	/**
	 * 
	 */
	public String articleId = "";

	/**
	 * 封面图片URL
	 */
	public String coverImgURL = "";

	/**
	 * 
	 */
	public List<TagList> tagList;

	/**
	 * 标题
	 */
	public String title = "";

	/**
	 * 帖子类型：1 - 文章 2 - 视频
	 */
	public String articleType = "";

	/**
	 * 如果是文章 则存储文章内容富文本， 如果是视频，则存储视频的URL
	 */
	public String content = "";

	/**
	 * 引文富文本，可选
	 */
	public String quote = "";

	/**
	 * 发布时间
	 */
	public long postTime;

	/**
	 * 点赞数
	 */
	public long thumbNumber;

	/**
	 * 评论数
	 */
	public long commentNumber;

	/**
	 * 访问次数或播放次数
	 */
	public long visitNumber;

	public AuthorInfo authorInfo;
		
		@Override
		public String toString() {
			return "ReferList{" +
					"articleId='" + articleId + '\'' +
					", coverImgURL='" + coverImgURL + '\'' +
					", tagList=" + tagList +
					", title='" + title + '\'' +
					", articleType='" + articleType + '\'' +
					", content='" + content + '\'' +
					", quote='" + quote + '\'' +
					", postTime=" + postTime +
					", thumbNumber=" + thumbNumber +
					", commentNumber=" + commentNumber +
					", visitNumber=" + visitNumber +
					", authorInfo=" + authorInfo +
					'}';
		}
    }
	/**
     * tagList
     */
    public class TagList implements Serializable {

	/**
	 * 
	 */
	public long tagId;

	/**
	 * 
	 */
	public String tagName = "";

	/**
	 * 
	 */
	public String color = "";
		
		@Override
		public String toString() {
			return "TagList{" +
					"tagId=" + tagId +
					", tagName='" + tagName + '\'' +
					", color='" + color + '\'' +
					'}';
		}
    }
	/**
     * postInfo
     */
    public class PostInfo implements Serializable {

	/**
	 * 
	 */
	public String articleId = "";

	/**
	 * 封面图片URL
	 */
	public String coverImgURL = "";

	/**
	 * 
	 */
	public List<TagList> tagList;

	/**
	 * 标题
	 */
	public String title = "";

	/**
	 * 帖子类型：1 - 文章 2 - 视频
	 */
	public String articleType = "";

	/**
	 * 如果是文章 则存储文章内容富文本， 如果是视频，则存储视频的URL
	 */
	public String content = "";

	/**
	 * 引文富文本，可选
	 */
	public String quote = "";

	/**
	 * 发布时间
	 */
	public long postTime;

	/**
	 * 点赞数
	 */
	public long thumbNumber;

	/**
	 * 评论数
	 */
	public long commentNumber;

	/**
	 * 访问次数或播放次数
	 */
	public long visitNumber;

	public AuthorInfo authorInfo;
		
		@Override
		public String toString() {
			return "PostInfo{" +
					"articleId='" + articleId + '\'' +
					", coverImgURL='" + coverImgURL + '\'' +
					", tagList=" + tagList +
					", title='" + title + '\'' +
					", articleType='" + articleType + '\'' +
					", content='" + content + '\'' +
					", quote='" + quote + '\'' +
					", postTime=" + postTime +
					", thumbNumber=" + thumbNumber +
					", commentNumber=" + commentNumber +
					", visitNumber=" + visitNumber +
					", authorInfo=" + authorInfo +
					'}';
		}
    }
	/**
     * authorInfo
     */
    public class AuthorInfo implements Serializable {

	/**
	 * 
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
		return "GetPostInfo4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", result=" + result +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetPostInfo4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("result")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "result" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectResult = jsonObject.optJSONObject("result");
			Result result = new Result();
		
	    	if (jsonObjectResult.isNull("postInfo")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "postInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPostInfo = jsonObjectResult.optJSONObject("postInfo");
			PostInfo postInfo = new PostInfo();
		
	    	if (jsonObjectPostInfo.isNull("articleId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "articleId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.articleId = jsonObjectPostInfo.optString("articleId");
	    	if (jsonObjectPostInfo.isNull("coverImgURL")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "coverImgURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.coverImgURL = jsonObjectPostInfo.optString("coverImgURL");
	    	if (jsonObjectPostInfo.isNull("tagList")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray tagListArray = jsonObjectPostInfo.optJSONArray("tagList");
			postInfo.tagList = new ArrayList<>();
			
			if (null != tagListArray && tagListArray.length() > 0) {
				for(int tagListi = 0; tagListi < tagListArray.length(); tagListi++) {
					JSONObject jsonObjectTagList = tagListArray.optJSONObject(tagListi);
			TagList tagList = new TagList();
		
	    	if (jsonObjectTagList.isNull("tagId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagId = jsonObjectTagList.optLong("tagId");
	    	if (jsonObjectTagList.isNull("tagName")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagName = jsonObjectTagList.optString("tagName");
	    	if (jsonObjectTagList.isNull("color")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "color" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.color = jsonObjectTagList.optString("color");
					
					postInfo.tagList.add(tagList);
				}
			}
			
	    	if (jsonObjectPostInfo.isNull("title")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "title" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.title = jsonObjectPostInfo.optString("title");
	    	if (jsonObjectPostInfo.isNull("articleType")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "articleType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.articleType = jsonObjectPostInfo.optString("articleType");
	    	if (jsonObjectPostInfo.isNull("content")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "content" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.content = jsonObjectPostInfo.optString("content");
	    	if (jsonObjectPostInfo.isNull("quote")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "quote" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.quote = jsonObjectPostInfo.optString("quote");
	    	if (jsonObjectPostInfo.isNull("postTime")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "postTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.postTime = jsonObjectPostInfo.optLong("postTime");
	    	if (jsonObjectPostInfo.isNull("thumbNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "thumbNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.thumbNumber = jsonObjectPostInfo.optLong("thumbNumber");
	    	if (jsonObjectPostInfo.isNull("commentNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "commentNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.commentNumber = jsonObjectPostInfo.optLong("commentNumber");
	    	if (jsonObjectPostInfo.isNull("visitNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "visitNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.visitNumber = jsonObjectPostInfo.optLong("visitNumber");
	    	if (jsonObjectPostInfo.isNull("authorInfo")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "authorInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectAuthorInfo = jsonObjectPostInfo.optJSONObject("authorInfo");
			AuthorInfo authorInfo = new AuthorInfo();
		
	    	if (jsonObjectAuthorInfo.isNull("userId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.userId = jsonObjectAuthorInfo.optLong("userId");
	    	if (jsonObjectAuthorInfo.isNull("nickName")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.nickName = jsonObjectAuthorInfo.optString("nickName");
	    	if (jsonObjectAuthorInfo.isNull("photoURL")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.photoURL = jsonObjectAuthorInfo.optString("photoURL");
	    		
	    		postInfo.authorInfo = authorInfo;
	    		
	    		result.postInfo = postInfo;
	    	if (jsonObjectResult.isNull("thumbStatus")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "thumbStatus" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			result.thumbStatus = jsonObjectResult.optBoolean("thumbStatus");
	    	if (jsonObjectResult.isNull("collectionStatus")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "collectionStatus" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			result.collectionStatus = jsonObjectResult.optBoolean("collectionStatus");
	    	if (jsonObjectResult.isNull("referList")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "referList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray referListArray = jsonObjectResult.optJSONArray("referList");
			result.referList = new ArrayList<>();
			
			if (null != referListArray && referListArray.length() > 0) {
				for(int referListi = 0; referListi < referListArray.length(); referListi++) {
					JSONObject jsonObjectReferList = referListArray.optJSONObject(referListi);
			ReferList referList = new ReferList();
		
	    	if (jsonObjectReferList.isNull("articleId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "articleId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.articleId = jsonObjectReferList.optString("articleId");
	    	if (jsonObjectReferList.isNull("coverImgURL")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "coverImgURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.coverImgURL = jsonObjectReferList.optString("coverImgURL");
	    	if (jsonObjectReferList.isNull("tagList")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray tagListArray1 = jsonObjectReferList.optJSONArray("tagList");
			referList.tagList = new ArrayList<>();
			
			if (null != tagListArray1 && tagListArray1.length() > 0) {
				for(int tagListi = 0; tagListi < tagListArray1.length(); tagListi++) {
					JSONObject jsonObjectTagList = tagListArray1.optJSONObject(tagListi);
			TagList tagList = new TagList();
		
	    	if (jsonObjectTagList.isNull("tagId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagId = jsonObjectTagList.optLong("tagId");
	    	if (jsonObjectTagList.isNull("tagName")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "tagName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagName = jsonObjectTagList.optString("tagName");
	    	if (jsonObjectTagList.isNull("color")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "color" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.color = jsonObjectTagList.optString("color");
					
					referList.tagList.add(tagList);
				}
			}
			
	    	if (jsonObjectReferList.isNull("title")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "title" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.title = jsonObjectReferList.optString("title");
	    	if (jsonObjectReferList.isNull("articleType")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "articleType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.articleType = jsonObjectReferList.optString("articleType");
	    	if (jsonObjectReferList.isNull("content")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "content" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.content = jsonObjectReferList.optString("content");
	    	if (jsonObjectReferList.isNull("quote")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "quote" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.quote = jsonObjectReferList.optString("quote");
	    	if (jsonObjectReferList.isNull("postTime")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "postTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.postTime = jsonObjectReferList.optLong("postTime");
	    	if (jsonObjectReferList.isNull("thumbNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "thumbNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.thumbNumber = jsonObjectReferList.optLong("thumbNumber");
	    	if (jsonObjectReferList.isNull("commentNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "commentNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.commentNumber = jsonObjectReferList.optLong("commentNumber");
	    	if (jsonObjectReferList.isNull("visitNumber")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "visitNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			referList.visitNumber = jsonObjectReferList.optLong("visitNumber");
	    	if (jsonObjectReferList.isNull("authorInfo")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "authorInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectAuthorInfo1 = jsonObjectReferList.optJSONObject("authorInfo");
			AuthorInfo authorInfo1 = new AuthorInfo();
		
	    	if (jsonObjectAuthorInfo1.isNull("userId")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
					authorInfo1.userId = jsonObjectAuthorInfo1.optLong("userId");
	    	if (jsonObjectAuthorInfo1.isNull("nickName")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
					authorInfo1.nickName = jsonObjectAuthorInfo1.optString("nickName");
	    	if (jsonObjectAuthorInfo1.isNull("photoURL")) {
	    		Log.d("GetPostInfo4C", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
					authorInfo1.photoURL = jsonObjectAuthorInfo1.optString("photoURL");
	    		
	    		referList.authorInfo = authorInfo1;
					
					result.referList.add(referList);
				}
			}
			
	    		
	    		this.result = result;
    	
    	return this;
    }
}
