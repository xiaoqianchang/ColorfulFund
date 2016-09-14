package com.zritc.colorfulfund.data.model.circle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 圈子列表
 */
public class PostList4C implements Serializable {
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
     * 当前是第几页
     */
    public long pageindex;

    /**
     *
     */
    public List<PostList4C> postList = new ArrayList<>();

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

