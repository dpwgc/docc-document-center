package com.dpwgc.document.center.sdk.model.comment;

import com.dpwgc.document.center.sdk.base.QueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("评论查询-接口参数")
public class CommentQuery extends QueryBase {

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
     * 作者（发布者）id
     */
    @ApiModelProperty("作者（发布者）id")
    private String authorId;

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
