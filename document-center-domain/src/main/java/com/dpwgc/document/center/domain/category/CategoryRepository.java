package com.dpwgc.document.center.domain.category;

public interface CategoryRepository {

    String createCategory(Category category);

    Boolean updateCategory(Category category);

    Boolean deleteCategory(String categoryId);
}
