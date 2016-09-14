package com.zritc.colorfulfund.data.response.circle;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Net Response Bean 获取帖子列表
 * <p>
 * package: 						com.zrt.dc.controllers.circle
 * svcName(服务名): 					GetPostList4C
 * svcCaption( 服务中文名，可用于注释): 	获取帖子列表
 * mode(http_get or http_post): 	HTTP_POST
 * target(与init里的key相对应): 		http://172.16.101.202/play/circle/getPostList4C
 * comments(服务详细备注，可用于注释): 		供客户端调用的接口
 * <p>
 * Created by Chang.Xiao on .
 */
public class GetPostList4C implements Serializable {

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

    public PostListPerPage postListPerPage;
    
	/**
     * postList
     */
    public class PostList implements Serializable {

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
            return "PostList4C{" +
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
     * postListPerPage
     */
    public class PostListPerPage implements Serializable {

        /**
         * 当前是第几页
         */
        public long pageindex;

        /**
         *
         */
        public List<PostList> postList;

        @Override
        public String toString() {
            return "PostListPerPage{" +
                    "pageindex=" + pageindex +
                    ", postList=" + postList +
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
		return "GetPostList4C{" +
				"sid='" + sid + '\'' +
				", rid='" + rid + '\'' +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", optype='" + optype + '\'' +
                ", postListPerPage=" + postListPerPage +
				'}';
	}
    
    /**
     * parse json
     */
    public synchronized GetPostList4C parseJson(String json) throws JSONException {
    	JSONObject jsonObject = new JSONObject(json);
		
	    	if (jsonObject.isNull("sid")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "sid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.sid = jsonObject.optString("sid");
	    	if (jsonObject.isNull("rid")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "rid" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.rid = jsonObject.optString("rid");
	    	if (jsonObject.isNull("code")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "code" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.code = jsonObject.optString("code");
	    	if (jsonObject.isNull("msg")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "msg" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.msg = jsonObject.optString("msg");
	    	if (jsonObject.isNull("optype")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "optype" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			this.optype = jsonObject.optString("optype");
        if (jsonObject.isNull("postListPerPage")) {
            Log.d("GetPostList4C", "has no mapping for key " + "postListPerPage" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }

        JSONObject jsonObjectPostListPerPage = jsonObject.optJSONObject("postListPerPage");
        PostListPerPage postListPerPage = new PostListPerPage();

        if (jsonObjectPostListPerPage.isNull("pageindex")) {
            Log.d("GetPostList4C", "has no mapping for key " + "pageindex" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
        }
        postListPerPage.pageindex = jsonObjectPostListPerPage.optLong("pageindex");
        if (jsonObjectPostListPerPage.isNull("postList")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "postList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
        JSONArray postListArray = jsonObjectPostListPerPage.optJSONArray("postList");
        postListPerPage.postList = new ArrayList<>();
			
			if (null != postListArray && postListArray.length() > 0) {
				for(int postListi = 0; postListi < postListArray.length(); postListi++) {
					JSONObject jsonObjectPostList = postListArray.optJSONObject(postListi);
			PostList postList = new PostList();
		
	    	if (jsonObjectPostList.isNull("articleId")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "articleId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.articleId = jsonObjectPostList.optString("articleId");
	    	if (jsonObjectPostList.isNull("coverImgURL")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "coverImgURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.coverImgURL = jsonObjectPostList.optString("coverImgURL");
	    	if (jsonObjectPostList.isNull("tagList")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "tagList" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			JSONArray tagListArray = jsonObjectPostList.optJSONArray("tagList");
			postList.tagList = new ArrayList<>();
			
			if (null != tagListArray && tagListArray.length() > 0) {
				for(int tagListi = 0; tagListi < tagListArray.length(); tagListi++) {
					JSONObject jsonObjectTagList = tagListArray.optJSONObject(tagListi);
			TagList tagList = new TagList();
		
	    	if (jsonObjectTagList.isNull("tagId")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "tagId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagId = jsonObjectTagList.optLong("tagId");
	    	if (jsonObjectTagList.isNull("tagName")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "tagName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.tagName = jsonObjectTagList.optString("tagName");
	    	if (jsonObjectTagList.isNull("color")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "color" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			tagList.color = jsonObjectTagList.optString("color");
					
					postList.tagList.add(tagList);
				}
			}
			
	    	if (jsonObjectPostList.isNull("title")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "title" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.title = jsonObjectPostList.optString("title");
	    	if (jsonObjectPostList.isNull("articleType")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "articleType" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.articleType = jsonObjectPostList.optString("articleType");
	    	if (jsonObjectPostList.isNull("content")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "content" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.content = jsonObjectPostList.optString("content");
	    	if (jsonObjectPostList.isNull("quote")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "quote" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.quote = jsonObjectPostList.optString("quote");
	    	if (jsonObjectPostList.isNull("postTime")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "postTime" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.postTime = jsonObjectPostList.optLong("postTime");
	    	if (jsonObjectPostList.isNull("thumbNumber")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "thumbNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.thumbNumber = jsonObjectPostList.optLong("thumbNumber");
	    	if (jsonObjectPostList.isNull("commentNumber")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "commentNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.commentNumber = jsonObjectPostList.optLong("commentNumber");
	    	if (jsonObjectPostList.isNull("visitNumber")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "visitNumber" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			postList.visitNumber = jsonObjectPostList.optLong("visitNumber");
	    	if (jsonObjectPostList.isNull("authorInfo")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "authorInfo" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			
	    	JSONObject jsonObjectAuthorInfo = jsonObjectPostList.optJSONObject("authorInfo");
			AuthorInfo authorInfo = new AuthorInfo();
		
	    	if (jsonObjectAuthorInfo.isNull("userId")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "userId" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.userId = jsonObjectAuthorInfo.optLong("userId");
	    	if (jsonObjectAuthorInfo.isNull("nickName")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "nickName" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.nickName = jsonObjectAuthorInfo.optString("nickName");
	    	if (jsonObjectAuthorInfo.isNull("photoURL")) {
	    		Log.d("GetPostList4C", "has no mapping for key " + "photoURL" + " on " + new Throwable().getStackTrace()[0].getClassName() + ", line number " + new Throwable().getStackTrace()[0].getLineNumber());
	    	}
			authorInfo.photoURL = jsonObjectAuthorInfo.optString("photoURL");
	    		
	    		postList.authorInfo = authorInfo;
					
                postListPerPage.postList.add(postList);
				}
			}
			
    	
        this.postListPerPage = postListPerPage;

    	return this;
    }
}

