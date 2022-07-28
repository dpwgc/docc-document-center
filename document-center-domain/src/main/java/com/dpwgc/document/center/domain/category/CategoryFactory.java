package com.dpwgc.document.center.domain.category;

public class CategoryFactory {

    public Category create(String appId, String categoryId, String parentId, String categoryName, String summary, Long score, Long createTime, Long updateTime) {
        return new Category().create(appId, categoryId, parentId, categoryName, summary, score, createTime, updateTime);
    }
}
