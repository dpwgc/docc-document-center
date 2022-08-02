package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "新建文档-接口参数")
@Getter
@Setter
public class CreateDocumentCommand {

    /**
     * 文档所属应用id
     */
    @ApiModelProperty(value = "文档所属应用id")
    private String appId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;

    /**
     * 作者id
     */
    @ApiModelProperty(value = "作者id")
    private String authorId;

    /**
     * 文档标题
     */
    @ApiModelProperty(value = "文档标题")
    private String title;

    /**
     * 文档内容
     */
    @ApiModelProperty(value = "文档内容")
    private String content;

    /**
     * 文档标签（数组格式传入，例：["tag1","tag2","tag3"]）
     */
    @ApiModelProperty(value = "文档标签（数组格式传入，例：[\"tag1\",\"tag2\",\"tag3\"]）")
    private List<String> tags;

    /**
     * 文档总结摘要
     */
    @ApiModelProperty(value = "文档总结摘要")
    private String summary;

    /**
     * 文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）
     */
    @ApiModelProperty(value = "文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）")
    private Integer authLevel;

    /**
     * 文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）
     */
    @ApiModelProperty(value = "文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）")
    private Long score;

    /**
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    @ApiModelProperty(value = "文档类型（自定义，例：0-普通文档，1-置顶文档）")
    private Integer type;
}
