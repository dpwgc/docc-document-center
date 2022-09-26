package com.dpwgc.document.center.infrastructure.dal.comment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("sub_comment")
public class SubCommentPO {

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
     * 版本号（乐观锁字段，递增）
     */
    @Version
    private Long version;

    /**
     * 子评论创建时间
     */
    private Long createTime;

    /**
     * 子评论更新时间
     */
    private Long updateTime;
}
