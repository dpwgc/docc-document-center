package com.dpwgc.document.center.domain.category;

public class CategoryFactory {

    public Category create(String appId, String categoryId, String parentId, String categoryName) {
        return new Category().create(appId, categoryId, parentId, categoryName);
    }
}
