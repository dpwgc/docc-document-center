package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.sdk.model.category.CategoryDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryDetailDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryTreeDTO;
import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryAssembler {

    CategoryAssembler INSTANCE = Mappers.getMapper(CategoryAssembler.class);

    CategoryDTO assembleCategoryDTO(Category category);

    Category assembleCategoryFromUpdate(UpdateCategoryCommand updateCategoryCommand);

    CategoryDTO assembleCategoryDTO(CategoryPO categoryPO);

    CategoryDetailDTO assembleCategoryDetailDTO(CategoryPO categoryPO);

    CategoryTreeDTO assemblerCategoryTreeDTO(CategoryPO categoryPO);
}
