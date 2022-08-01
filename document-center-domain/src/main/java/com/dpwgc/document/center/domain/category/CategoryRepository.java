package com.dpwgc.document.center.domain.category;

public interface CategoryRepository {

    Category queryCategoryByCategoryId(String appId, String categoryId);

    String createCategory(Category category);

    Boolean updateCategory(Category category);

    Boolean deleteCategory(String appId, String categoryId);
}
