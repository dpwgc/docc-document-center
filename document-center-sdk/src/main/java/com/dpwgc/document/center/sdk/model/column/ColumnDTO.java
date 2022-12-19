package com.dpwgc.document.center.sdk.model.column;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ColumnDTO {

    /**
     * 专栏id
     */
    private String columnId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 作者/发布者id
     */
    private String authorId;

    /**
     * 专栏名称
     */
    private String columnName;

    /**
     * 专栏详情
     */
    private String detail;

    /**
     * 专栏附加内容（自定义）
     */
    private String extra;

    /**
     * 专栏推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）
     */
    private Long score;

    /**
     * 专栏下的文档总数（返回分类列表时可按此字段对分类进行排序）
     */
    private Long documentNum;

    /**
     * 专栏属性（自定义，例：1-热门专栏、0-普通专栏）
     */
    private Integer attr;

    /**
     * 专栏类型（自定义，例：1-官方专栏，0-普通专栏）
     */
    private Integer type;

    /**
     * 专栏创建时间
     */
    private Long createTime;

    /**
     * 专栏更新时间
     */
    private Long updateTime;
}
