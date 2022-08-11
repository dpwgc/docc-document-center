package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档数据聚合统计")
public class AggregationsDocumentQuery {

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "作者id")
    private String authorId;

    @ApiModelProperty(value = "文档类型")
    private Integer type;

    @ApiModelProperty(value = "文档标签")
    private String tag;
}
