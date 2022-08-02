package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "更新文档主要内容-接口参数")
@Getter
@Setter
public class UpdateDocumentMainCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
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
     * 文档总结摘要
     */
    @ApiModelProperty(value = "文档总结摘要")
    private String summary;
}
