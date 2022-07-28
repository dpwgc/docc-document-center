package com.dpwgc.document.center.app.command.category;

import com.dpwgc.document.center.sdk.model.category.CreateCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.DeleteCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;

public interface CategoryCommandService {

    String createCategory(CreateCategoryCommand createCategoryCommand);

    Boolean updateCategory(UpdateCategoryCommand updateCategoryCommand);

    Boolean deleteCategory(DeleteCategoryCommand deleteCategoryCommand);
}
