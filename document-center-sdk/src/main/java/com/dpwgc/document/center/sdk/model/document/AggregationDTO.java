package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "文档数据聚合统计-DTO")
public class AggregationDTO {

    @ApiModelProperty("收藏总数统计")
    private Double loveTotal;

    @ApiModelProperty("点赞总数统计")
    private Double likeTotal;

    @ApiModelProperty("阅读总数统计")
    private Double readTotal;

    @ApiModelProperty("分享总数统计")
    private Double shareTotal;

    @ApiModelProperty("评论总数统计")
    private Double commentTotal;

    @ApiModelProperty("文档总数统计")
    private Double documentTotal;
}
