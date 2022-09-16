package com.dpwgc.document.center.sdk.model.comment.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "文档子评论查询-接口参数")
public class SubCommentQuery {

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
     * 子评论id
     */
    @ApiModelProperty("子评论id")
    private String subCommentId;

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

    @ApiModelProperty(value = "排序字段")
    private String sortField;

    @ApiModelProperty(value = "排序规则")
    private String sortOrder;

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
