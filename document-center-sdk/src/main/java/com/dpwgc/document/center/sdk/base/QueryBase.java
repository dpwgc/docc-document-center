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
    private String sortField = "update_time";

    @ApiModelProperty(value = "排序规则")
    private String sortOrder = "desc";

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize = 10;

    /**
     * pageIndex转换
     */
    public void pageIndexConvert() {
        this.pageIndex = ( this.pageIndex - 1 ) * this.pageSize;
    }
}
