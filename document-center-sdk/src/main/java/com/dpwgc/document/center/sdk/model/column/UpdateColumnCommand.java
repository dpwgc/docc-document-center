package com.dpwgc.document.center.sdk.model.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ApiModel(value = "更新文档专栏-接口参数")
public class UpdateColumnCommand {

    /**
     * 所属应用id
     */
    @ApiModelProperty(value = "所属应用id")
    @NotEmpty(message = "appId is empty")
    private String appId;

    /**
     * 专栏id
     */
    @ApiModelProperty(value = "专栏id")
    @NotEmpty(message = "columnId is empty")
    private String columnId;

    /**
     * 作者/发布者id
     */
    @ApiModelProperty(value = "作者/发布者id")
    private String authorId;

    /**
     * 专栏名称
     */
    @ApiModelProperty(value = "专栏名称")
    private String columnName;

    /**
     * 专栏详情
     */
    @ApiModelProperty(value = "专栏详情")
    private String detail;

    /**
     * 专栏附加内容（自定义）
     */
    @ApiModelProperty(value = "专栏附加内容（自定义）")
    private String extra;

    /**
     * 专栏推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）
     */
    @ApiModelProperty(value = "专栏推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）")
    private Long score;

    /**
     * 专栏下的文档总数（返回分类列表时可按此字段对分类进行排序）
     */
    @ApiModelProperty(value = "专栏下的文档总数（返回分类列表时可按此字段对分类进行排序）")
    private Long documentNum;

    /**
     * 专栏属性（自定义，例：1-热门专栏、0-普通专栏）
     */
    @ApiModelProperty(value = "专栏属性（自定义，例：1-热门专栏、0-普通专栏）")
    private Integer attr;

    /**
     * 专栏类型（自定义，例：1-官方专栏，0-普通专栏）
     */
    @ApiModelProperty(value = "专栏类型（自定义，例：1-官方专栏，0-普通专栏）")
    private Integer type;

    /**
     * 专栏状态（0-删除，1-正常）
     */
    @ApiModelProperty(value = "专栏状态（0-删除，1-正常）")
    private Integer status;
}
