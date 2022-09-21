package com.dpwgc.document.center.domain.comment;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论
 */
@Setter
@Getter
public class Comment {

    /**
     * 主键id
     */
    private String id;

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 所属文档id
     */
    private String documentId;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 作者（发布者）id
     */
    private String authorId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论附加内容（自定义）
     */
    private String extra;

    /**
     * 评论点赞数
     */
    private Long likeNum;

    /**
     * 评论收藏数
     */
    private Long loveNum;

    /**
     * 评论分享数
     */
    private Long shareNum;

    /**
     * 子评论数
     */
    private Long subCommentNum;

    /**
     * 评论属性（自定义，例：1-热门评论、0-普通评论）
     */
    private Integer attr;

    /**
     * 评论类型（自定义，例：1-置顶评论，0-普通评论）
     */
    private Integer type;

    /**
     * 评论状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 评论创建时间
     */
    private Long createTime;

    /**
     * 评论更新时间
     */
    private Long updateTime;

    protected Comment create(String appId, String documentId, String commentId, String authorId, String content, String extra, Integer attr, Integer type) {

        this.status = 1;

        this.appId = appId;
        this.documentId = documentId;
        this.commentId = commentId;
        this.authorId = authorId;
        this.content = content;
        this.extra = extra;

        this.attr = attr;
        this.type = type;

        this.likeNum = 0L;
        this.loveNum = 0L;
        this.shareNum = 0L;
        this.subCommentNum = 0L;

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        return this;
    }
}
