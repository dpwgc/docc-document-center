package com.dpwgc.document.center.sdk.model.column;

import com.dpwgc.document.center.sdk.base.QueryBase;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ColumnQuery extends QueryBase {

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 作者/发布者id
     */
    private String authorId;

    /**
     * 检索关键词
     */
    private String keyword;

    /**
     * 专栏属性（自定义，例：1-热门专栏、0-普通专栏）
     */
    private Integer attr;

    /**
     * 专栏类型（自定义，例：1-官方专栏，0-普通专栏）
     */
    private Integer type;
}
