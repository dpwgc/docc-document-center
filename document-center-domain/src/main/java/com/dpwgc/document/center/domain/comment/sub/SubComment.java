package com.dpwgc.document.center.domain.comment.sub;

import lombok.Getter;
import lombok.Setter;

/**
 * 子评论
 */
@Setter
@Getter
public class SubComment {

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
     * 所属评论id
     */
    private String commentId;

    /**
     * 子评论id
     */
    private String subCommentId;

    /**
     * 作者（发布者）id
     */
    private String authorId;

    /**
     * 回复对象id（为空表示该评论没有@其他人）
     */
    private String replyTo;

    /**
     * 子评论内容
     */
    private String content;

    /**
     * 子评论附加内容（自定义）
     */
    private String extra;

    /**
     * 子评论点赞数
     */
    private Long likeNum;

    /**
     * 子评论收藏数
     */
    private Long loveNum;

    /**
     * 子评论分享数
     */
    private Long shareNum;

    /**
     * 子评论属性（自定义，例：1-热门子评论、0-普通子评论）
     */
    private Integer attr;

    /**
     * 子评论类型（自定义，例：1-置顶子评论，0-普通子评论）
     */
    private Integer type;

    /**
     * 子评论状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 子评论创建时间
     */
    private Long createTime;

    /**
     * 子评论更新时间
     */
    private Long updateTime;

    protected SubComment create(String appId, String documentId, String commentId ,String subCommentId, String authorId, String replyTo, String content, String extra, Integer attr, Integer type) {

        this.status = 1;

        this.appId = appId;
        this.documentId = documentId;
        this.commentId = commentId;
        this.subCommentId = subCommentId;
        this.authorId = authorId;
        this.replyTo = replyTo;
        this.content = content;
        this.extra = extra;

        this.attr = attr;
        this.type = type;

        this.likeNum = 0L;
        this.loveNum = 0L;
        this.shareNum = 0L;

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        return this;
    }
}
