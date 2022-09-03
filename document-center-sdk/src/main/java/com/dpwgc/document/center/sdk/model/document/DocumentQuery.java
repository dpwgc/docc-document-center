package com.dpwgc.document.center.sdk.model.document;

import co.elastic.clients.elasticsearch._types.SortOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档检索-接口参数")
public class DocumentQuery {

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "作者id")
    private String authorId;

    @ApiModelProperty(value = "文档类型")
    private Integer type;

    @ApiModelProperty(value = "文档检索过滤条件（自定义，例：1-仅自己可见、2-所有人可见）")
    private Integer filter;

    @ApiModelProperty(value = "文档属性（自定义，例：1-转载文章、2-原创文章）")
    private Integer attr;

    @ApiModelProperty(value = "文档标签")
    private String tag;

    @ApiModelProperty(value = "检索关键词")
    private String keyword;

    @ApiModelProperty(value = "访问权限等级")
    private Integer authLevel;

    @ApiModelProperty(value = "排序字段")
    private String sortField;

    @ApiModelProperty(value = "排序规则")
    private SortOrder sortOrder;

    @ApiModelProperty(value = "更新时间区间开始")
    private Long startUpdateTime;

    @ApiModelProperty(value = "更新时间区间结尾")
    private Long endUpdateTime;

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "是否显示文档主体内容")
    private Boolean showContent;
}
