package com.zritc.colorfulfund.data.model.mine;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏模型
 * <p>
 * Created by Chang.Xiao on 2016/9/20.
 *
 * @version 1.0
 */
public class Collection {

    /**
     * 当前是第几页
     */
    public int pageIndex;

    /**
     * 帖子列表
     */
    public List<Post> postList = new ArrayList<>();

    /**
     * 单个帖子
     *
     * Created by Chang.Xiao on 2016/9/20.
     *
     * @version 1.0
     */
    public class Post {

        /**
         *
         */
        public String articleId = "";

        /**
         * 封面图片URL
         */
        public String coverImgURL = "";

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

        /**
         *
         */
        public List<Tag> tagList = new ArrayList<>();
    }

    public class Tag {
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
    }
}
