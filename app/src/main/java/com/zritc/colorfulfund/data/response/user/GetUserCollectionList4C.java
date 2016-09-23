package com.zritc.colorfulfund.data.response.user;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取用户收藏列表
 *
 * package: 						com.zrt.dc.controllers.user
 * svcName(服务名): 					GetUserCollectionList4C
 * svcCaption( 服务中文名，可用于注释): 	获取用户收藏列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.201:9006/user/getUserCollectionList4C
 * comments(服务详细备注，可用于注释): 		获取用户信息
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetUserCollectionList4C implements Serializable {

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

	public PageInfo pageInfo;
    
	/**
     * userCollectionList
     */
    public class UserCollectionList implements Serializable {

	/**
	 * 
	 */
	public long collectionTime;

	/**
	 * 
	 */
	public String collectionType = "";

	public PostInfo postInfo;
		
		@Override
		public String toString() {
			return "UserCollectionList{" +
					"collectionTime=" + collectionTime +
					", collectionType='" + collectionType + '\'' +
					", postInfo=" + postInfo +
					'}';
		}
    }
	/**
     * pageInfo
     */
    public class PageInfo implements Serializable {

	/**
	 * 
	 */
	public String pageIndex = "";

	/**
	 * 
	 */
	public List<UserCollectionList> userCollectionList;

	public UserInfo userInfo;
		
		@Override
		public String toString() {
			return "PageInfo{" +
					"pageIndex='" + pageIndex + '\'' +
					", userCollectionList=" + userCollectionList +
					", userInfo=" + userInfo +
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
     * userInfo
     */
    public class UserInfo implements Serializable {

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
			return "UserInfo{" +
					"userId=" + userId +
					", nickName='" + nickName + '\'' +
					", photoURL='" + photoURL + '\'' +
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
		return "GetUserCollectionList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
				", pageInfo=" + pageInfo +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetUserCollectionList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
	    	if (jsonObject.isNull("pageInfo")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "pageInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPageInfo = jsonObject.optJSONObject("pageInfo");
			PageInfo pageInfo = new PageInfo();
		
	    	if (jsonObjectPageInfo.isNull("pageIndex")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "pageIndex" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			pageInfo.pageIndex = jsonObjectPageInfo.optString("pageIndex");
	    	if (jsonObjectPageInfo.isNull("userCollectionList")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "userCollectionList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray userCollectionListArray = jsonObjectPageInfo.optJSONArray("userCollectionList");
			pageInfo.userCollectionList = new ArrayList<>();
			
			if (null != userCollectionListArray && userCollectionListArray.length() > 0) {
				for(int userCollectionListi = 0; userCollectionListi < userCollectionListArray.length(); userCollectionListi++) {
					JSONObject jsonObjectUserCollectionList = userCollectionListArray.optJSONObject(userCollectionListi);
			UserCollectionList userCollectionList = new UserCollectionList();
		
	    	if (jsonObjectUserCollectionList.isNull("collectionTime")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "collectionTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userCollectionList.collectionTime = jsonObjectUserCollectionList.optLong("collectionTime");
	    	if (jsonObjectUserCollectionList.isNull("collectionType")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "collectionType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userCollectionList.collectionType = jsonObjectUserCollectionList.optString("collectionType");
	    	if (jsonObjectUserCollectionList.isNull("postInfo")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "postInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectPostInfo = jsonObjectUserCollectionList.optJSONObject("postInfo");
			PostInfo postInfo = new PostInfo();
		
	    	if (jsonObjectPostInfo.isNull("articleId")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "articleId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.articleId = jsonObjectPostInfo.optString("articleId");
	    	if (jsonObjectPostInfo.isNull("coverImgURL")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "coverImgURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.coverImgURL = jsonObjectPostInfo.optString("coverImgURL");
	    	if (jsonObjectPostInfo.isNull("tagList")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "tagList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray tagListArray = jsonObjectPostInfo.optJSONArray("tagList");
			postInfo.tagList = new ArrayList<>();
			
			if (null != tagListArray && tagListArray.length() > 0) {
				for(int tagListi = 0; tagListi < tagListArray.length(); tagListi++) {
					JSONObject jsonObjectTagList = tagListArray.optJSONObject(tagListi);
			TagList tagList = new TagList();
		
	    	if (jsonObjectTagList.isNull("tagId")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "tagId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagId = jsonObjectTagList.optLong("tagId");
	    	if (jsonObjectTagList.isNull("tagName")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "tagName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagName = jsonObjectTagList.optString("tagName");
	    	if (jsonObjectTagList.isNull("color")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "color" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.color = jsonObjectTagList.optString("color");
					
					postInfo.tagList.add(tagList);
				}
			}
			
	    	if (jsonObjectPostInfo.isNull("title")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "title" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.title = jsonObjectPostInfo.optString("title");
	    	if (jsonObjectPostInfo.isNull("articleType")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "articleType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.articleType = jsonObjectPostInfo.optString("articleType");
	    	if (jsonObjectPostInfo.isNull("content")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "content" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.content = jsonObjectPostInfo.optString("content");
	    	if (jsonObjectPostInfo.isNull("quote")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "quote" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.quote = jsonObjectPostInfo.optString("quote");
	    	if (jsonObjectPostInfo.isNull("postTime")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "postTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.postTime = jsonObjectPostInfo.optLong("postTime");
	    	if (jsonObjectPostInfo.isNull("thumbNumber")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "thumbNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.thumbNumber = jsonObjectPostInfo.optLong("thumbNumber");
	    	if (jsonObjectPostInfo.isNull("commentNumber")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "commentNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.commentNumber = jsonObjectPostInfo.optLong("commentNumber");
	    	if (jsonObjectPostInfo.isNull("visitNumber")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "visitNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postInfo.visitNumber = jsonObjectPostInfo.optLong("visitNumber");
	    	if (jsonObjectPostInfo.isNull("authorInfo")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "authorInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectAuthorInfo = jsonObjectPostInfo.optJSONObject("authorInfo");
			AuthorInfo authorInfo = new AuthorInfo();
		
	    	if (jsonObjectAuthorInfo.isNull("userId")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.userId = jsonObjectAuthorInfo.optLong("userId");
	    	if (jsonObjectAuthorInfo.isNull("nickName")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.nickName = jsonObjectAuthorInfo.optString("nickName");
	    	if (jsonObjectAuthorInfo.isNull("photoURL")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.photoURL = jsonObjectAuthorInfo.optString("photoURL");
	    		
	    		postInfo.authorInfo = authorInfo;
	    		
	    		userCollectionList.postInfo = postInfo;
					
					pageInfo.userCollectionList.add(userCollectionList);
				}
			}
			
	    	if (jsonObjectPageInfo.isNull("userInfo")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "userInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectUserInfo = jsonObjectPageInfo.optJSONObject("userInfo");
			UserInfo userInfo = new UserInfo();
		
	    	if (jsonObjectUserInfo.isNull("userId")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.userId = jsonObjectUserInfo.optLong("userId");
	    	if (jsonObjectUserInfo.isNull("nickName")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.nickName = jsonObjectUserInfo.optString("nickName");
	    	if (jsonObjectUserInfo.isNull("photoURL")) {
	    		Log.d("GetUserCollectionList4", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			userInfo.photoURL = jsonObjectUserInfo.optString("photoURL");
	    		
	    		pageInfo.userInfo = userInfo;
	    		
	    		this.pageInfo = pageInfo;
    	
    	return this;
    }
}
