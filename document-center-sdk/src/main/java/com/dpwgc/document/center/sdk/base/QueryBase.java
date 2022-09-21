package com.dpwgc.document.center.sdk.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询操作基础字段
 */
@Setter
@Getter
public class QueryBase {

    @ApiModelProperty(value = "排序字段")
    private String sortField;

    @ApiModelProperty(value = "排序规则")
    private String sortOrder;

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
