package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档数据聚合统计-DTO")
public class DocumentAggregationsDTO {

    @ApiModelProperty("收藏总数统计")
    private Long loveTotal;

    @ApiModelProperty("点赞总数统计")
    private Long likeTotal;

    @ApiModelProperty("阅读总数统计")
    private Long readTotal;

    @ApiModelProperty("评论总数统计")
    private Long commentTotal;

    @ApiModelProperty("文档总数统计")
    private Long documentTotal;
}
