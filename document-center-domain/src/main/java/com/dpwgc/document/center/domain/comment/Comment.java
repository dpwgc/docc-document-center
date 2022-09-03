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
     * 应用id
     */
    private String appId;

    /**
     * 文档id
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
    private String likeNum;

    /**
     * 评论收藏数
     */
    private String loveNum;

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
}
