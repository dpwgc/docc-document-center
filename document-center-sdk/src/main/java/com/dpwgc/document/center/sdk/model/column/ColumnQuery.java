package com.dpwgc.document.center.sdk.model.column;

import com.dpwgc.document.center.sdk.base.QueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档专栏查询-接口参数")
public class ColumnQuery extends QueryBase {

    /**
     * 所属应用id
     */
    @ApiModelProperty(value = "所属应用id")
    private String appId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;

    /**
     * 作者/发布者id
     */
    @ApiModelProperty(value = "作者/发布者id")
    private String authorId;

    /**
     * 检索关键词
     */
    @ApiModelProperty(value = "检索关键词")
    private String keyword;

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
     * 是否显示专栏详情
     */
    @ApiModelProperty(value = "是否显示专栏详情")
    private Boolean showDetail = false;
}
