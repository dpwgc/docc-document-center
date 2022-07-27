package com.dpwgc.document.query.domain.category;

public interface CategoryRepository {

    String createCategory(Category category);

    Boolean updateCategory(Category category);

    Boolean deleteCategory(String categoryId);
}
