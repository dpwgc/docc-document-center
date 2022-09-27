package com.dpwgc.document.center.sdk.model.comment.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel("新建子评论-接口参数")
public class CreateSubCommentCommand {

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
     * 作者（发布者）id
     */
    @ApiModelProperty(value = "作者（发布者）id", required = true)
    @NotEmpty(message = "authorId is empty")
    private String authorId;

    /**
     * 回复对象id（为空表示该评论没有@其他人）
     */
    @ApiModelProperty("回复对象id（为空表示该评论没有@其他人）")
    private String replyTo;

    /**
     * 子评论内容
     */
    @ApiModelProperty("子评论内容")
    private String content;

    /**
     * 子评论附加内容（自定义）
     */
    @ApiModelProperty("子评论附加内容（自定义）")
    private String extra;

    /**
     * 子评论属性（自定义，例：1-热门子评论、0-普通子评论）
     */
    @ApiModelProperty("子评论属性（自定义，例：1-热门子评论、0-普通子评论）")
    private Integer attr;

    /**
     * 子评论类型（自定义，例：1-置顶子评论，0-普通子评论）
     */
    @ApiModelProperty("子评论类型（自定义，例：1-置顶子评论，0-普通子评论）")
    private Integer type;
}
