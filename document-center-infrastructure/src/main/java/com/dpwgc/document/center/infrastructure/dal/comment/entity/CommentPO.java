package com.dpwgc.document.center.infrastructure.dal.comment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("comment")
public class CommentPO {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
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
     * 版本号（乐观锁字段，递增）
     */
    @Version
    private Long version;

    /**
     * 评论创建时间
     */
    private Long createTime;

    /**
     * 评论更新时间
     */
    private Long updateTime;
}
