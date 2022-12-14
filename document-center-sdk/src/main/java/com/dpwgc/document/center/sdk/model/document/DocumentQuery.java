package com.dpwgc.document.center.sdk.model.document;

import com.dpwgc.document.center.sdk.base.QueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@ApiModel(value = "文档检索-接口参数")
public class DocumentQuery extends QueryBase {

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "专栏id")
    private String columnId;

    @ApiModelProperty(value = "作者id")
    private String authorId;

    @ApiModelProperty(value = "文档类型")
    private Integer type;

    @ApiModelProperty(value = "文档阅读过滤条件")
    private Integer filter;

    @ApiModelProperty(value = "文档属性")
    private Integer attr;

    @ApiModelProperty(value = "文档标签")
    private String tag;

    @ApiModelProperty(value = "检索关键词")
    private String keyword;

    @ApiModelProperty(value = "访问权限等级")
    private Integer authLevel = 0;

    @ApiModelProperty(value = "更新时间区间开始")
    private Long startUpdateTime = 0L;

    @ApiModelProperty(value = "更新时间区间结尾")
    private Long endUpdateTime = System.currentTimeMillis();

    @ApiModelProperty(value = "是否显示文档主体内容")
    private Boolean showContent = false;
}
