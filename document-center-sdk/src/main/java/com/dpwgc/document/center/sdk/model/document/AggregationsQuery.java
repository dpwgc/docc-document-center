package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档数据聚合统计-接口参数")
public class AggregationsQuery {

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
}
