package com.dpwgc.document.query.app.command.category;

import com.dpwgc.document.query.sdk.model.category.CreateCategoryCommand;
import com.dpwgc.document.query.sdk.model.category.DeleteCategoryCommand;
import com.dpwgc.document.query.sdk.model.category.UpdateCategoryCommand;

public interface CategoryCommandService {

    String createCategory(CreateCategoryCommand createCategoryCommand);

    Boolean updateCategory(UpdateCategoryCommand updateCategoryCommand);

    Boolean deleteCategory(DeleteCategoryCommand deleteCategoryCommand);
}
