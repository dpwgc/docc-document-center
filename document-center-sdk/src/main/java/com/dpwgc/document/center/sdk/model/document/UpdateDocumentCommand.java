package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel(value = "更新文档主要内容-接口参数")
@Getter
@Setter
public class UpdateDocumentCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
    @NotEmpty(message = "id is empty")
    private String id;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;

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
     * 文档标签
     */
    @ApiModelProperty(value = "文档标签")
    private List<String> tags;

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
     * 文档收藏数（可按此字段对文档进行排序）
     */
    @ApiModelProperty(value = "文档收藏数（可按此字段对文档进行排序）")
    private Long loveNum;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    @ApiModelProperty(value = "文档点赞数（可按此字段对文档进行排序）")
    private Long likeNum;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    @ApiModelProperty(value = "文档阅读数（可按此字段对文档进行排序）")
    private Long readNum;

    /**
     * 文档分享数
     */
    @ApiModelProperty(value = "文档分享数（可按此字段对文档进行排序）")
    private Long shareNum;

    /**
     * 文档评论总数（可按此字段对文档进行排序）
     */
    @ApiModelProperty(value = "文档评论总数（可按此字段对文档进行排序）")
    private Long commentNum;

    /**
     * 文档检索过滤条件（自定义，例：1-仅自己可见、2-所有人可见）
     */
    @ApiModelProperty(value = "文档检索过滤条件（自定义，例：1-仅自己可见、2-所有人可见）")
    private Integer filter;

    /**
     * 文档属性（自定义，例：1-转载文章、2-原创文章）
     */
    @ApiModelProperty(value = "文档属性（自定义，例：1-转载文章、2-原创文章）")
    private Integer attr;

    /**
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    @ApiModelProperty(value = "文档类型（自定义，例：0-普通文档，1-置顶文档）")
    private Integer type;

    /**
     * 文档状态（0-删除，1-正常）
     */
    @ApiModelProperty(value = "文档状态（0-删除，1-正常）")
    private Integer status;
}
