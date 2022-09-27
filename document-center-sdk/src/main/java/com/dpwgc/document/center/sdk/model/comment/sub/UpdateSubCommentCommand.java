package com.dpwgc.document.center.sdk.model.comment.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel("更新子评论-接口参数")
public class UpdateSubCommentCommand {

    /**
     * 所属应用id
     */
    @ApiModelProperty(value = "所属应用id", required = true)
    @NotEmpty(message = "appId is empty")
    private String appId;

    /**
     * 所属文档id
     */
    @ApiModelProperty(value = "所属文档id", required = true)
    @NotEmpty(message = "documentId is empty")
    private String documentId;

    /**
     * 所属评论id
     */
    @ApiModelProperty(value = "所属评论id", required = true)
    @NotEmpty(message = "commentId is empty")
    private String commentId;

    /**
     * 子评论id
     */
    @ApiModelProperty(value = "子评论id", required = true)
    @NotEmpty(message = "subCommentId is empty")
    private String subCommentId;

    /**
     * 子评论附加内容（自定义）
     */
    @ApiModelProperty("子评论附加内容（自定义）")
    private String extra;

    /**
     * 子评论点赞数
     */
    @ApiModelProperty("子评论点赞数")
    private Long likeNum;

    /**
     * 子评论收藏数
     */
    @ApiModelProperty("子评论收藏数")
    private Long loveNum;

    /**
     * 子评论状态（0-删除，1-正常）
     */
    @ApiModelProperty("子评论状态（0-删除，1-正常）")
    private Integer status;
}
