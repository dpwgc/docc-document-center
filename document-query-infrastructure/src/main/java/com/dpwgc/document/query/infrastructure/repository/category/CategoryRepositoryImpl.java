package com.dpwgc.document.query.infrastructure.repository.category;

import com.dpwgc.document.query.domain.category.Category;
import com.dpwgc.document.query.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public String createCategory(Category category) {
        return null;
    }

    @Override
    public Boolean updateCategory(Category category) {
        return false;
    }

    @Override
    public Boolean deleteCategory(String categoryId) {
        return false;
    }
}
