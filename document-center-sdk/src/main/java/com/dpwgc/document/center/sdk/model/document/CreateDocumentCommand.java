package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@ApiModel(value = "新建文档-接口参数")
@Getter
@Setter
public class CreateDocumentCommand {

    /**
     * 文档所属应用id
     */
    @ApiModelProperty(value = "文档所属应用id", required = true)
    @NotEmpty(message = "appId is empty")
    private String appId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;

    /**
     * 专栏id（允许文档被多个专栏收录）
     */
    @ApiModelProperty(value = "专栏id（允许文档被多个专栏收录）")
    private Set<String> columnId;

    /**
     * 作者id（允许多个作者联合发布文章）
     */
    @ApiModelProperty(value = "作者id（允许多个作者联合发布文章）", required = true)
    @NotEmpty(message = "authorId is empty")
    private Set<String> authorId;

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
    private Set<String> tag;

    /**
     * 文档备注
     */
    @ApiModelProperty(value = "文档备注")
    private String remarks;

    /**
     * 文档附加内容（自定义）
     */
    @ApiModelProperty(value = "文档附加内容（自定义）")
    private String extra;

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
     * 文档阅读过滤条件（自定义，允许多个，例：1-仅自己可见、2-所有人可见）
     */
    @ApiModelProperty(value = "文档阅读过滤条件（自定义，允许多个，例：1-仅自己可见、2-所有人可见）")
    private Set<Integer> filter;

    /**
     * 文档属性（自定义，允许多个，例：1-转载文章、2-原创文章）
     */
    @ApiModelProperty(value = "文档属性（自定义，允许多个，例：1-转载文章、2-原创文章）")
    private Set<Integer> attr;

    /**
     * 文档类型（自定义，例：0-普通文章，1-官方公告）
     */
    @ApiModelProperty(value = "文档类型（自定义，例：0-普通文章，1-官方公告）")
    private Integer type;
}
