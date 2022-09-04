package com.dpwgc.document.center.sdk.model.comment.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("新建子评论-接口参数")
public class CreateSubCommentCommand {

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
     * 所属评论id
     */
    @ApiModelProperty("所属评论id")
    private String commentId;

    /**
     * 作者（发布者）id
     */
    @ApiModelProperty("作者（发布者）id")
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
}
