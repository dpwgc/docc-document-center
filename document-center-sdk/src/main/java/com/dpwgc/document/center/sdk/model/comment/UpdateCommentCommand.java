package com.dpwgc.document.center.sdk.model.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("更新评论-接口参数")
public class UpdateCommentCommand {

    /**
     * 所属应用id
     */
    @ApiModelProperty("所属应用id")
    private String appId;

    /**
     * 所属文档id
     */
    @ApiModelProperty("所属文档id")
    private String documentId;

    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private String commentId;

    /**
     * 评论附加内容（自定义）
     */
    @ApiModelProperty("评论附加内容（自定义）")
    private String extra;

    /**
     * 评论点赞数
     */
    @ApiModelProperty("评论点赞数")
    private Long likeNum;

    /**
     * 评论收藏数
     */
    @ApiModelProperty("评论收藏数")
    private Long loveNum;

    /**
     * 子评论数
     */
    @ApiModelProperty("子评论数")
    private Long subCommentNum;

    /**
     * 评论属性（自定义，例：1-热门评论、0-普通评论）
     */
    @ApiModelProperty("评论属性（自定义，例：1-热门评论、0-普通评论）")
    private Integer attr;

    /**
     * 评论类型（自定义，例：1-置顶评论，0-普通评论）
     */
    @ApiModelProperty("评论类型（自定义，例：1-置顶评论，0-普通评论）")
    private Integer type;

    /**
     * 评论状态（0-删除，1-正常）
     */
    @ApiModelProperty("评论状态（0-删除，1-正常）")
    private Integer status;
}
