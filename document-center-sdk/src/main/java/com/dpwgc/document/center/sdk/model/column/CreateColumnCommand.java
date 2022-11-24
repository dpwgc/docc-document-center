package com.dpwgc.document.center.sdk.model.column;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateColumnCommand {

    /**
     * 所属应用id
     */
    private String appId;

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
     * 专栏属性（自定义，例：1-热门专栏、0-普通专栏）
     */
    private Integer attr;

    /**
     * 专栏类型（自定义，例：1-官方专栏，0-普通专栏）
     */
    private Integer type;
}
