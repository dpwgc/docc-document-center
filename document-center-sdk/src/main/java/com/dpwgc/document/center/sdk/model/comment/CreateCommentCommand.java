package com.dpwgc.document.center.sdk.model.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel("创建评论-接口参数")
public class CreateCommentCommand {

    /**
     * 所属应用id
     */
    @ApiModelProperty("所属应用id")
    @NotEmpty(message = "appId is empty")
    private String appId;

    /**
     * 所属文档id
     */
    @ApiModelProperty("所属文档id")
    @NotEmpty(message = "documentId is empty")
    private String documentId;

    /**
     * 作者（发布者）id
     */
    @ApiModelProperty("作者（发布者）id")
    @NotEmpty(message = "authorId is empty")
    private String authorId;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 评论附加内容（自定义）
     */
    @ApiModelProperty("评论附加内容（自定义）")
    private String extra;

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
}
