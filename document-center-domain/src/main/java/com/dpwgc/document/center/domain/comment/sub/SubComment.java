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
    private String likeNum;

    /**
     * 子评论收藏数
     */
    private String loveNum;

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
}
